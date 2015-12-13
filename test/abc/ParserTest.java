package abc;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import abc.music.ConstructMusic;
import abc.music.FetchHeader;
import abc.music.HeaderInfo;
import abc.music.Music;
import abc.music.MusicLanguage;
import abc.music.RepeatHandler;
import abc.parser.HeaderGrammarLexer;
import abc.parser.HeaderGrammarParser;
import abc.parser.MusicGrammarLexer;
import abc.parser.MusicGrammarParser;
import abc.player.FileSeparator;
import abc.sound.SequencePlayer;

/**
 * Test Cases for Music ADT Parser
 * 
 * Partitions:
 *  - HEADER:
 *      - Check the correctness of each field in header after 
 *        reading music from the file and parsing it.
 *      - Key signature (K) changes
 *          - Key of C (base case)
 *          - Minor keys
 *          - Major keys
 *          - Flat keys
 *          - Sharp keys
 *      - Meter (M) changes
 *          - When M ommitted, default meter 4/4
 *          - C --> common time (4/4)
 *          - C| --> cut common time (2/2)
 *      - Tempo (Q) changes 
 *          - When Q omitted, default tempo is 100 bpm
 *          - When Q specified, it includes beat length --> tempo not based on L
 *      - Duration of note (L) changes 
 *          - If L < 0.75 --> default length is sixteenth note
 *          - If L >= .75 --> default length is eighth note
 *          - If L omitted, default note length is eighth
 *  - BODY:
 *      - Notes (C is middle C):
 *          - CDEFGAB
 *          - cdefgab (one octave up)
 *          - octave up '
 *          - octave down ,
 *      - Rests: z
 *      - Durations: 
 *          - /n for 1/n-beat duration
 *          - / for 1/2-beat duration
 *          - empty string for 1-beat duration
 *          - n for n-beat duration
 *          - n/m for n/m-beat duration
 *      - Accidentals:
 *          - Sharp ^
 *          - Flat _
 *          - Naturals = 
 *          - Double sharp ^^
 *          - Double flat __
 *          - Ensure accidentals / naturals are handled within each measure
 *          - Ensure accidentals / naturals only affect a single octave in each bar
 *          - Overridden accidentals within each bar 
 *      - End of music indicators: [|, ||, |]
 *      - Repeats: |: ... :|
 *          ex) |: C D E F | G A B c :|
 *          played as C D E F G A B c C D E F G A B c
 *          - If begin bar |: omitted, repeat from beginning of section
 *              - |] or beginning of piece 
 *          - Alternate ending, no alternate ending 
 *              - [1, [2 
 *                  ex) |: C D E F |[1 G A B c | G A B B :|[2 F E D C |
 *                  played as C D E F G A B c G A B B C D E F F E D C
 *      - Chords: [CEG]
 *          - Do not contain rests or tuplets
 *          - Duration of chord specified by first note in chord
 *          - Size = 1, size > 1
 *          - Accidentals, no accidentals
 *          - Single duration, multiple durations 
 *      - Tuplets: (3GAB
 *          - Duplets (each note 3/2 time), triplets (each note 2/3 time), quadruplets (each note 3/2 time)
 *          - No rests
 *          - No chords, chords
 *          - Notes of same length, notes of different lengths
 *          - Accidentals, no accidentals 
 *      - Voices: 
 *          - Single voice, multiple voices
 *          - Voice identifiers not repeated, voice identifiers repeated
 *          
 *  Test Cases:
 *      --> Each of these cases cover all partitions listed in corresponding section above. 
 *          General cases are for testing selection of sample pieces by ear. 
 *          
 *      - HEADER:
 *          - keySignaturesTest()
 *          - metersTest()
 *          - temposTest()
 *          - noteLengthsTest()
 *      - BODY:
 *          - notesTest()
 *          - restsTest()
 *          - durationsTest()
 *          - accidentalsTest()
 *          - repeatsTest()
 *          - chordsTest()
 *          - tupletsTest()
 *          - voicesTest()
 *       - General:
 *          - furEliseTest()
 *          - scaleTest()
 *          - waxiesDargleTest()
 *          - warmupsTest()
 *          - preludeTest()
 *
 */
public class ParserTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /**
     * Helper method for constructing HeaderInfo objects from 
     * String header representing header of .abc file
     * @param header represents header of .abc file
     */
    private static HeaderInfo headerParserHelper(String header){
        CharStream stream = new ANTLRInputStream(header);
        HeaderGrammarLexer lexer = new HeaderGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        HeaderGrammarParser parser = new HeaderGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        FetchHeader listener = new FetchHeader();
        walker.walk(listener, tree);
        return listener.getHeader();
    }
    
    /**
     * Helper method for constructing Music object from 
     * String body representing body of .abc file
     * @param body represents body of .abc file 
     */
    private static Music bodyParserHelper(String body, HeaderInfo header){
        CharStream stream = new ANTLRInputStream(body);
        MusicGrammarLexer lexer = new MusicGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        MusicGrammarParser parser = new MusicGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        ConstructMusic listener = new ConstructMusic(header);
        walker.walk(listener, tree);
        return listener.getExpression();
    }
    
    // Tests FetchHeader (Q not included)
    @Test
    public void testHeaderFetch1() {
        String filename = "sample_abc/fur_elise.abc";
        String headerInfo = (new FileSeparator(new File(filename))).getHeader();
        HeaderInfo header = headerParserHelper(headerInfo);
        assertEquals("Bagatelle No.25 in A, WoO.59", header.getT());
        assertEquals("Ludwig van Beethoven", header.getC());
        assertEquals("Am", header.getK());
        assertEquals("1/16", header.getL());
        assertEquals("1/8=140", header.getQ());
        assertEquals("3/8", header.getM());
    }
    
    // Tests FetchHeader (Q included)
    @Test
    public void testHeaderFetch2() {
        String filename = "sample_abc/abc_song.abc";
        String headerInfo = (new FileSeparator(new File(filename))).getHeader();
        HeaderInfo header = headerParserHelper(headerInfo);
        assertEquals("Alphabet Song", header.getT());
        assertEquals("Traditional Kid's Song", header.getC());
        assertEquals("D", header.getK());
        assertEquals("1/4", header.getL());
        assertEquals("1/4=100", header.getQ());
        assertEquals("4/4", header.getM());
    }
    
    MusicLanguage ml = new MusicLanguage();
    
    // keySignaturesTest()
    @Test public void keySignaturesTest(){
        String file1 = "test/test_abc/key1.abc";
        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1, header1);
        assertEquals("C", header1.getK());
        assertEquals("C1.0", music1.toString());
        
        String file2 = "test/test_abc/key2.abc";
        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(musicString2, header2);
        assertEquals("D", header2.getK());
        assertEquals("^C1.0", music2.toString());
        
        String file3 = "test/test_abc/key3.abc";
        FileSeparator fs3 = new FileSeparator(new File(file3));
        String musicString3 = fs3.getMusic();
        String headerString3 = fs3.getHeader();
        HeaderInfo header3 = headerParserHelper(headerString3);
        Music music3 = bodyParserHelper(musicString3, header3);
        assertEquals("Cb", header3.getK());
        assertEquals("B,1.0", music3.toString());
        
        String file4 = "test/test_abc/key4.abc";
        FileSeparator fs4 = new FileSeparator(new File(file4));
        String musicString4 = fs4.getMusic();
        String headerString4 = fs4.getHeader();
        HeaderInfo header4 = headerParserHelper(headerString4);
        Music music4 = bodyParserHelper(musicString4, header4);
        assertEquals("F#", header4.getK());
        assertEquals("^C1.0", music4.toString());
    }
    
    // metersTest()
    @Test public void metersTest(){
        String file1 = "test/test_abc/meters1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        assertEquals("4/4", header1.getM());
        
        String file2 = "test/test_abc/meters2.abc";
        FileSeparator fs2 = new FileSeparator(new File(file2));
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        assertEquals("4/4", header2.getM());
        
        String file3 = "test/test_abc/meters3.abc";
        FileSeparator fs3 = new FileSeparator(new File(file3));
        String headerString3 = fs3.getHeader();
        HeaderInfo header3 = headerParserHelper(headerString3);
        assertEquals("2/2", header3.getM());
        
        String file4 = "test/test_abc/meters4.abc";
        FileSeparator fs4 = new FileSeparator(new File(file4));
        String headerString4 = fs4.getHeader();
        HeaderInfo header4 = headerParserHelper(headerString4);
        assertEquals("3/8", header4.getM());
    }
    
    // temposTest()
    @Test public void temposTest(){
        String file1 = "test/test_abc/tempos1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        assertEquals(100, header1.getQBeats());
        

        String file2 = "test/test_abc/tempos2.abc";

        FileSeparator fs2 = new FileSeparator(new File(file2));
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        assertEquals(120, header2.getQBeats());
    }
    
    // noteLengthsTest()
    @Test public void noteLengthsTest(){
        String file1 = "test/test_abc/noteLengths1.abc";
        FileSeparator fs1 = new FileSeparator(new File(file1));
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        assertEquals("1/2", header1.getL());
        

        String file2 = "test/test_abc/noteLengths2.abc";

        FileSeparator fs2 = new FileSeparator(new File(file2));
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        assertEquals("1/8", header2.getL());
        
        String file3 = "test/test_abc/noteLengths3.abc";

        FileSeparator fs3 = new FileSeparator(new File(file3));
        String headerString3 = fs3.getHeader();
        HeaderInfo header3 = headerParserHelper(headerString3);
        assertEquals("1/8", header3.getL());
        
        String file4 = "test/test_abc/noteLengths4.abc";

        FileSeparator fs4 = new FileSeparator(new File(file4));
        String headerString4 = fs4.getHeader();
        HeaderInfo header4 = headerParserHelper(headerString4);
        assertEquals("1/8", header4.getL());
    }
    
    // notesTest()
    @Test public void notesTest(){
        String file1 = "test/test_abc/notes1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1, header1);
        String musicStringTest = "C,, D,, E,, F,, | G,, A,, B,, C, | "
                + "C, D, E, F, | G, A, B, C | "
                + "C D E F | G A B c "
                + "| c d e f | g a b c' | "
                + "c' d' e' f' | g' a' b' c''";
        Music musicTest =
                ml.notes(musicStringTest,
                    "C");
        assertEquals(musicTest, music1);
    }
    
    // restsTest()
    @Test public void restsTest(){
        String file1 = "test/test_abc/rests1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1, header1);

        String desiredMusicString = "C0.5 .1.5 C0.25 .0.5 C0.25 C1.0"; 
        assertEquals(desiredMusicString, music1.toString());

    }
    
    
    // durationsTest()
    @Test public void durationsTest(){
        String file1 = "test/test_abc/durations1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1, header1);

        String musicStringTest = "A1/4 A1/4 A1/2 A A2 A3 A4 A6 A8";
        Music musicTest =
                ml.notes(musicStringTest,
                    "C");        
        assertEquals(musicTest, music1);
    }
    
    // accidentalsTest()
    @Test public void accidentalsTest(){
        String file1 = "test/test_abc/accidentals1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1, header1);
        String musicStringTest1 = "_E E F ^F G";
        Music musicTest1 =
                ml.notes(musicStringTest1,
                    "C");  
        assertEquals(musicTest1, music1);
        
        String file2 = "test/test_abc/accidentals2.abc";

        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(musicString2, header2);
        String musicStringTest2 = "C ^C ^C ^C C _C _C _C C ^C _C _C C ^C C C C ^B C ^B ^C C' ^C, ^C C D C C";
        Music musicTest2 =
                ml.notes(musicStringTest2,
                    "C");        
        assertEquals(musicTest2, music2);
    }
    
    // repeatsTest()
    @Test public void repeatsTest(){
        String file1 = "test/test_abc/repeats1.abc";

        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(RepeatHandler.mergeVoices(musicString1), header1);
        String musicStringTest1 = "A B C D C D E F G A B c C D E F G A B c";
        Music musicTest1 =
                ml.notes(musicStringTest1,
                    "C");        
        assertEquals(musicTest1, music1);
        
        String file2 = "test/test_abc/repeats2.abc";

        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(RepeatHandler.mergeVoices(musicString2), header2);
        String musicStringTest2 = "C D E F G A B c G A B B C D E F F E D C";
        Music musicTest2 =
                ml.notes(musicStringTest2,
                    "C");        
        assertEquals(musicTest2, music2);
        

        String file3 = "test/test_abc/repeats3.abc";

        FileSeparator fs3 = new FileSeparator(new File(file3));
        String musicString3 = fs3.getMusic();
        String headerString3 = fs3.getHeader();
        HeaderInfo header3 = headerParserHelper(headerString3);
        Music music3 = bodyParserHelper(RepeatHandler.mergeVoices(musicString3), header3);
        String musicStringTest3 = "A B C D A B C D";
        Music musicTest3 =
                ml.notes(musicStringTest3,
                    "C");        
        assertEquals(musicTest3, music3);
        
        String file4 = "test/test_abc/repeats4.abc";

        FileSeparator fs4 = new FileSeparator(new File(file4));
        String musicString4 = fs4.getMusic();
        String headerString4 = fs4.getHeader();
        HeaderInfo header4 = headerParserHelper(headerString4);
        Music music4 = bodyParserHelper(RepeatHandler.mergeVoices(musicString4), header4);
        String musicStringTest4 = "a b c d A B C D A B C D";
        Music musicTest4 =
                ml.notes(musicStringTest4,
                    "C");        
        assertEquals(musicTest4, music4);
    }
    
    // chordsTest()
    
    // listen for Little Night Music, which involves chords
    @Test public void chordsTest1(){        
        String file = "sample_abc/little_night_music.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(96000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    // Verifies basic functionality of chords. Listen for a chord with 6 notes being played
    @Test public void chordsTest2(){
        String file1 = "test/test_abc/chords1.abc";
        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1,header1);
        String musicStringTest1 = "chord(E1.0 |||| C1.0)";   
        assertEquals(musicStringTest1, music1.toString());
        
        String file2 = "test/test_abc/chords2.abc";
        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(musicString2,header2);
        String musicStringTest2 = "chord(E1.0 |||| C0.5)";
        assertEquals(musicStringTest2, music2.toString());

        String file = "test/test_abc/chords3.abc";

        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(5000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    // tupletsTest()
    // Duplets (each note 3/2 time), triplets (each note 2/3 time), quadruplets (each note 3/4 time)
    @Test public void tupletsTest(){

        String file1 = "test/test_abc/tuplets1.abc";
        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(musicString1,header1);
        // (3GAB A B C (2^A_B a b c (4abcd 
        String musicStringTest1 = "G2/3 A2/3 B2/3 A B C ^A3/2 _B3/2 a b c a3/4 b3/4 c3/2 d3/4";
        Music musicTest1 =
                ml.notes(musicStringTest1,
                    "C");       
        assertEquals(musicTest1.toString(), music1.toString());
        

        String file2 = "test/test_abc/tuplets2.abc";
        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(musicString2,header2);
        // (3[CG][D^F]E
        String musicStringTest2 = "chord(C0.6666666666666666 |||| G0.6666666666666666) chord(D0.6666666666666666 "
                + "|||| ^F0.6666666666666666) E0.6666666666666666";
        assertEquals(musicStringTest2, music2.toString());
    }
    
    
    // voicesTest()
    @Test public void voicesTest(){
        String file = "sample_abc/invention.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(78000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    // furEliseTest()
    @Test public void furEliseTest(){
        String file = "sample_abc/fur_elise.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(170000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    // scaleTest()
    @Test public void scaleTest(){
        String file = "sample_abc/scale.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(12000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    // waxiesDargleTest()
    @Test public void waxiesDargleTest(){
        String file = "sample_abc/waxies_dargle.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(23000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    // warmupsTests()
    @Test public void warmupsTest1(){
        // Row Row Row Your Boat
        String file1 = "sample_abc/piece1.abc";
        FileSeparator fs1 = new FileSeparator(new File(file1));
        String musicString1 = fs1.getMusic();
        String headerString1 = fs1.getHeader();
        HeaderInfo header1 = headerParserHelper(headerString1);
        Music music1 = bodyParserHelper(RepeatHandler.mergeVoices(musicString1), header1);
        
        try {
            SequencePlayer player = new SequencePlayer(header1.getQBeats(), -1);
            music1.play(player, 0);
            player.play();
            Thread.sleep(9000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Test public void warmupsTest2(){
        // Mario Theme
        String file2 = "sample_abc/piece2.abc";
        FileSeparator fs2 = new FileSeparator(new File(file2));
        String musicString2 = fs2.getMusic();
        String headerString2 = fs2.getHeader();
        HeaderInfo header2 = headerParserHelper(headerString2);
        Music music2 = bodyParserHelper(RepeatHandler.mergeVoices(musicString2), header2);
        
        try {
            SequencePlayer player = new SequencePlayer(header2.getQBeats(), -1);
            music2.play(player, 0);
            player.play();
            Thread.sleep(8000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        
    }
    
    // preludeTest()
    @Test public void preludeTest(){
        String file = "sample_abc/prelude.abc";
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep(121000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
