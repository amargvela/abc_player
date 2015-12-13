package abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import abc.music.Chord;
import abc.music.ConstructMusic;
import abc.music.HeaderInfo;
import abc.music.Merge;
import abc.music.Music;
import abc.music.Note;
import abc.music.Parallel;
import abc.music.RepeatHandler;
import abc.music.Rest;
import abc.parser.MusicGrammarLexer;
import abc.parser.MusicGrammarParser;
import abc.sound.Pitch;
import abc.sound.SequencePlayer;



/**
 * Test Cases for Music ADT:
 * 
 * Music:
 * - Duration
 *      Partitions: 
 *          - empty music, nonempty
 *          - single note, >1 note 
 *          - rests, no rests 
 *          - large case: row row row your boat
 *      Test Cases:
 *          - musicDurationEmptyTest(): empty music
 *          - musicDurationSingleNoteTest(): single note
 *          - musicDurationLargeCaseTest(): row row row your boat
 * - Transpose
 *      Partitions: 
 *          - empty music, nonempty
 *          - single note, >1 note 
 *          - rests, no rests 
 *          - single voice, >1 voice
 *          - large case: row row row your boat
 *      Test Cases:
 *          - musicTransposeEmptyTest(): empty music
 *          - musicTransposeSingleNoteTest(): single note
 *          - musicTransposeLargeCaseTest(): row row row your boat
 * - Play
 *      Partitions:
 *          - empty music, nonempty
 *          - single note, >1 note 
 *          - rests, no rests 
 *          - large case: row row row your boat
 *      Test Cases:
 *          - musicPlayEmptyTest(): empty music
 *          - musicPlaySingleNoteTest(): single note, no rests 
 *          - musicPlayLargeCaseTest(): row row row your boat
 * 
 * Note:
 * - Constructor
 *      Partitions: check proper functionality 
 *      Test Cases: noteConstructorTest()
 * - Pitch
 *      Partitions: check proper functionality
 *      Test Cases: notePitchTest()
 * - Duration
 *      Partitions: whole number duration, decimal duration
 *      Test Cases: noteDurationTest()
 * - Transpose
 *      Partitions: transpose up, transpose down
 *      Test Cases: noteTransposeTest()
 * - Play
 *      Partitions: 
 *          - long note, short note
 *          - high pitch, low pitch
 *      Test Cases: notePlayTest(): covers all partitions
 * - hashCode
 *      Partitions: equals
 *      Test Cases: noteHashCodeTest()
 * - Equals
 *      Partitions:
 *          - True: same pitch and duration
 *          - False:
 *              - same pitch, different duration
 *              - same duration, different pitch
 *              - different duration, different pitch
 *      Test Cases: 
 *          - noteEqualsTestTrue(): same pitch and duration; tests reflexivity, symmetry, and transitivity
 *          - noteEqualsTestFalse(): covers all false partitions 
 * - toString
 *      Partitions: 
 *          - whole number duration, decimal duration 
 *      Test Cases:
 *          - noteToStringTest(): integer duration, decimal duration 
 * 
 * Rest:
 * - Constructor
 *      Partitions: test proper functionality
 *      Test Cases: restConstructorTest()
 * - Duration
 *      Partitions: whole number duration, decimal duration
 *      Test Cases: restDurationTest()
 * - Transpose
 *      Partitions: test proper functionality
 *      Test Cases: restTransposeTest()
 * - Play
 *      Partitions: whole number duration, decimal duration
 *      Test Cases: restPlayTest()
 * - hashCode
 *      Partitions: equals
 *      Test Cases: restEqualsTest()
 * - Equals
 *      Partitions: 
 *          - True: same duration
 *          - False different duration
 *      Test Cases: 
 *          -restEqualsTrueTest(): same duration; tests reflexivity, symmetry, and transitivity
 *          -restEqualsFalseTest(): different durations
 * - toString
 *      Partitions: whole number duration, decimal duration
 *      Test Cases: restToStringTest()
 * 
 * Parallel:
 * - Constructor
 *      Partitions: tests proper functionality
 *      Test Cases: parallelConstructorTest()
 * - Duration
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1, m2 same duration
 *          - m1, m2 different duration: 
 *                  - m1.duration > m2.duration
 *                  - m1.duration < m2.duration
 *      Test Cases:
 *          - parallelEmptyTest(): m1 and/or m2 empty
 *          - parallelSameDurationTest(): m1, m2 same duration
 *          - parallelDifferentDurationTest(): m1, m2 different duration
 * - Transpose
 *      Partitions:
 *          - m1 and/or m2 empty
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *      Test Cases:
 *          - parallelTransposeEmptyTest(): m1 and/or m2 empty
 *          - parallelTransposeSameNotesTest(): m1, m2 same notes
 *          - parallelTransposeDifferentNotesTest(): m1, m2 different notes
 * - Play
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *          - m1.duration != m2.duration
 *          - m1 = m2
 *      Test Cases:
 *          - parallelPlayEmptyTest(): m1 and/or m2 empty
 *          - parallelPlaySameNotesTest(): m1, m2 same notes
 *          - parallelPlayDifferentNotesTest(): m1, m2 different notes
 *          - parallelPlayDifferentDurationsTest(): m1.duration != m2.duration
 *          - parallelPlayEqualsTest(): m1 = m2
 * - hashCode
 *      Partitions: equals
 *      Test Cases: parallelHashCodeTest()
 * - Equals
 *      Partitions: 
 *      let m1 and m2 be Musics of Parallel 1 
 *      and m1' and m2' be Musics of Parallel 2
 *          - True:
 *              - m1 = m2 = m1' = m2'
 *              - m1 = m1' != m2 = m2'
 *              - all musics are empty
 *          - False:
 *              - m1 = m1' and m2 != m2'
 *              - m1 != m1' and m2 != m2'
 *              - m1 != m1' != m2 != m2'
 *              - m1 = m2' and m2 = m1' (musics are identical but in wrong order)
 *      Test Cases:
 *          - parallelEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
 *          - parallelEqualsFalseTest(): all false partitions covered except last
 *          - parallelEqualsFalseWrongOrderTest(): musics are identical but in wrong order
 * - toString
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1 and m2 equals
 *          - m1 and m2 non-equals
 *      Test Cases:
 *          - parallelToStringEmptyTest(): m1 and/or m2 empty
 *          - parallelToStringEqualsTest(): m1 and m2 equals
 *          - parallelToStringDifferentTest(): m1 and m2 non-equals
 * 
 * Chord:
 * - Constructor
 *      Partitions: tests proper functionality
 *      Test Cases: chordConstructorTest()
 * - Duration
 *      Partitions:
 *          - m1, m2 same duration
 *          - m1, m2 different durations: 
 *                  - m1.duration > m2.duration
 *                  - m1.duration < m2.duration
 *      Test Cases:
 *          - chordSameDurationTest(): m1, m2 same duration
 *          - chordlDifferentDurationTest(): m1, m2 different duration
 * - Transpose
 *      Partitions:
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *      Test Cases:
 *          - chordTransposeSameNotesTest(): m1, m2 same notes
 *          - chordTransposeDifferentNotesTest(): m1, m2 different notes
 * - Play
 *      Partitions: 
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *          - m1.duration != m2.duration
 *          - m1 = m2
 *      Test Cases:
 *          - chordPlaySameNotesTest(): m1, m2 same notes
 *          - chordPlayDifferentNotesTest(): m1, m2 different notes
 *          - chordPlayDifferentDurationsTest(): m1.duration != m2.duration
 *          - chordPlayEqualsTest(): m1 = m2
 * - hashCode
 *      Partitions: equals
 *      Test Cases: chordHashCodeTest()
 * - Equals
 *      Partitions: 
 *      let m1 and m2 be Musics of chord 1 
 *      and m1' and m2' be Musics of chord 2
 *          - True:
 *              - m1 = m2 = m1' = m2'
 *              - m1 = m1' != m2 = m2'
 *              - all musics are empty
 *          - False:
 *              - m1 = m1' and m2 != m2'
 *              - m1 != m1' and m2 != m2'
 *              - m1 != m1' != m2 != m2'
 *              - m1 = m2' and m2 = m1' (musics are identical but in wrong order)
 *      Test Cases:
 *          - chordEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
 *          - chordEqualsFalseTest(): all false partitions covered except last
 *          - chordEqualsFalseWrongOrderTest(): musics are identical but in wrong order
 * - toString
 *      Partitions: 
 *          - m1 and m2 equals
 *          - m1 and m2 non-equals
 *      Test Cases:
 *          - chordToStringEqualsTest(): m1 and m2 equals
 *          - chordToStringDifferentTest(): m1 and m2 non-equals
 * 
 * Merge:
 * - Constructor
 *      Partitions: check proper functionality
 *      Test Cases: mergeConstructorTest()
 * - Duration
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1, m2 same duration
 *          - m1, m2 different duration: 
 *                  - m1.duration > m2.duration
 *                  - m1.duration < m2.duration
 *      Test Cases:
 *          - mergeEmptyTest(): m1 and/or m2 empty
 *          - mergeSameDurationTest(): m1, m2 same duration
 *          - mergeDifferentDurationTest(): m1, m2 different duration
 * - Transpose
 *      Partitions:
 *          - m1 and/or m2 empty
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *      Test Cases:
 *          - mergeTransposeEmptyTest(): m1 and/or m2 empty
 *          - mergeTransposeSameNotesTest(): m1, m2 same notes
 *          - mergeTransposeDifferentNotesTest(): m1, m2 different notes
 * - Play
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1, m2 same notes
 *          - m1, m2 different notes
 *          - m1.duration != m2.duration
 *          - m1 = m2
 *      Test Cases:
 *          - mergePlayEmptyTest(): m1 and/or m2 empty
 *          - mergePlaySameNotesTest(): m1, m2 same notes
 *          - mergePlayDifferentNotesTest(): m1, m2 different notes
 *          - mergePlayDifferentDurationsTest(): m1.duration != m2.duration
 *          - mergePlayEqualsTest(): m1 = m2
 * - hashCode
 *      Partitions: equals
 *      Test Cases: mergeHashCodeTest()
 * - Equals
 *      Partitions: 
 *      let m1 and m2 be Musics of Parallel 1 
 *      and m1' and m2' be Musics of Parallel 2
 *          - True:
 *              - m1 = m2 = m1' = m2'
 *              - m1 = m1' != m2 = m2'
 *              - all musics are empty
 *          - False:
 *              - m1 = m1' and m2 != m2'
 *              - m1 != m1' and m2 != m2'
 *              - m1 != m1' != m2 != m2'
 *              - m1 = m2' and m2 = m1' (musics are identical but in wrong order)
 *      Test Cases:
 *          - mergeEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
 *          - mergeEqualsFalseTest(): all false partitions covered except the last 
 *          - mergeEqualsFalseWrongOrderTest(): musics are identical but in wrong order
 * - toString
 *      Partitions: 
 *          - m1 and/or m2 empty
 *          - m1 and m2 equals
 *          - m1 and m2 non-equals
 *      Test Cases:
 *          - mergeToStringEmptyTest(): m1 and/or m2 empty
 *          - mergeToStringEqualsTest(): m1 and m2 equals
 *          - mergeToStringDifferentTest(): m1 and m2 non-equals
 * 
 */

public class MusicTest {
    
    Music phrase1 = new Merge( new Note(12./12, new Pitch('C')),
    new Merge (new Note(12./12, new Pitch('C')),
    new Merge (new Note(9./12, new Pitch('C')), 
    new Merge (new Note(3./12, new Pitch('D')),
    new Merge (new Note(12./12, new Pitch('E')),
    new Merge (new Note(9./12, new Pitch('E')),
    new Merge (new Note(3./12, new Pitch('D')),
    new Merge (new Note(9./12, new Pitch('E')),
    new Merge (new Note(3./12, new Pitch('F')),
    new Note(24./12, new Pitch('G')))))))))));
            
    Music phrase2 = new Merge(new Note(4./12, new Pitch('C').transpose(Pitch.OCTAVE)),
    new Merge(new Note(4./12, new Pitch('C').transpose(Pitch.OCTAVE)),
    new Merge(new Note(4./12, new Pitch('C').transpose(Pitch.OCTAVE)),
    new Merge(new Note(4./12, new Pitch('G')),
    new Merge(new Note(4./12, new Pitch('G')),
    new Merge(new Note(4./12, new Pitch('G')),
    new Merge(new Note(4./12, new Pitch('E')),
    new Merge(new Note(4./12, new Pitch('E')),
    new Merge(new Note(4./12, new Pitch('E')),
    new Merge(new Note(4./12, new Pitch('C')),
    new Merge(new Note(4./12, new Pitch('C')),
    new Note(4./12, new Pitch('C')))))))))))));
    
    Music phrase3 = new Merge(new Note(9./12, new Pitch('G')),
    new Merge(new Note(3./12, new Pitch('F')),
    new Merge(new Note(9./12, new Pitch('E')),
    new Merge(new Note(3./12, new Pitch('D')),
    new Note(24./12, new Pitch('C'))))));
    
    Music rowRowRowYourBoat = new Merge(phrase1, new Merge(phrase2, phrase3));
    
    @Test
    public void sampleTest() {
        String input = "C,, D,, E,, F,, | G,, A,, B,, C, | "
                + "C, D, E, F, | G, A, B, C | "
                + "C D E F | G A B c "
                + "| c d e f | g a b c' | "
                + "c' d' e' f' | g' a' b' c''\n";
        
        CharStream stream = new ANTLRInputStream(input);
        MusicGrammarLexer lexer = new MusicGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        MusicGrammarParser parser = new MusicGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        ConstructMusic listener = new ConstructMusic(new HeaderInfo());
        walker.walk(listener, tree);
        Music sample = listener.getExpression();
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            sample.play(player,0);
            player.play();
            Thread.sleep((long) sample.duration()*1000);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (InterruptedException uie) {
            uie.printStackTrace();
        }  
    }
    
    @Test
    public void sampleTest2() {
        String input = "d^f^f ^cee|de^f g^fe|d^f^f ^cee|d^fe dBA|\n"+
                "d^f^f ^cee|de^f g^fe|^fa^f g^fe|[1 d^fe dBA:|[2 d^fe d^cB|]\n"+
                        "A3 B3|g^fe ^fdB|A^FA B2^c|d^fe d^cB|\n"+
                        "A3 B3|e^fe e^fg|^fa^f g^fe|[1 d^fe d^cB:|[2 d^fe dBA|]\n"+
                        "^fAA eAA| de^f g^fe|^fAA eAA|d^fe dBA|\n"+
                        "^fAA eAA| de^f g^fe|^fa^f g^fe|d^fe dBA:|\n";
        
        CharStream stream = new ANTLRInputStream(RepeatHandler.handleRepeats(input));
        MusicGrammarLexer lexer = new MusicGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        MusicGrammarParser parser = new MusicGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        ConstructMusic listener = new ConstructMusic(new HeaderInfo());
        walker.walk(listener, tree);
        Music sample = listener.getExpression();
        try {
            SequencePlayer player = new SequencePlayer(200, 12);
            sample.play(player,0);
            player.play();
            Thread.sleep((long) sample.duration()*1000);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (InterruptedException uie) {
            uie.printStackTrace();
        }  
    }
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // musicDurationEmptyTest(): empty music
    @Test public void musicDurationEmptyTest() {
        Music empty = new Parallel(new Rest(0), new Merge(new Rest(0), new Rest(0))); 
        assertTrue(empty.duration() == 0);
    }
    
    // musicDurationSingleNoteTest(): single note
    @Test public void musicDurationSingleNoteTest() {
        Music singleNote = new Parallel(new Rest(0), new Merge(new Note(5.5, new Pitch(66)), new Rest(0))); 
        assertTrue(singleNote.duration() == 5.5);
    }
    
    // musicDurationLargeCaseTest(): row row row your boat
    @Test public void musicDurationLargeCaseTest() {
        assertTrue(16.0 == rowRowRowYourBoat.duration());
    }
    
    // musicTransposeEmptyTest(): empty music
    @Test public void musicTransposeEmptyTest() {
        Music empty = new Parallel(new Rest(0), new Merge(new Rest(0), new Rest(0)));
        Music emptyTransposed = empty.transpose(10);
        assertEquals(empty, emptyTransposed);
    }
    
    // musicTransposeSingleNoteTest(): single note
    @Test public void musicTransposeSingleNoteTest() {
        Music a = new Note(1, new Pitch('A'));
        Music c = new Note(1, new Pitch('C'));
        assertEquals(a.transpose(2), c.transpose(11));
    }
    
    // musicTransposeLargeCaseTest(): row row row your boat
    @Test public void musicTransposeLargeCaseTest() {
        String transposedRowRowRowYourBoatString = 
                "D1.0 D1.0 D0.75 E0.25 ^F1.0 ^F0.75 E0.25 ^F0.75 G0.25 A2.0 "
                + "D'0.3333333333333333 D'0.3333333333333333 D'0.3333333333333333 "
                + "A0.3333333333333333 A0.3333333333333333 A0.3333333333333333 "
                + "^F0.3333333333333333 ^F0.3333333333333333 ^F0.3333333333333333 "
                + "D0.3333333333333333 D0.3333333333333333 D0.3333333333333333 "
                + "A0.75 G0.25 ^F0.75 E0.25 D2.0";
        assertEquals(transposedRowRowRowYourBoatString, rowRowRowYourBoat.transpose(2).toString());
    }
    
    // musicPlayEmptyTest(): empty music
    @Test public void musicPlayEmptyTest() {
        SequencePlayer player;
        try {
            player = new SequencePlayer(140, 12);
            Music empty = new Parallel(new Rest(0), new Merge(new Rest(0), new Rest(0)));
            empty.play(player, 0);
            assertEquals("Meta event: END_OF_TRACK Tick: 0\n", player.toString());
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    // musicPlaySingleNoteTest(): single note
    @Test public void musicPlaySingleNoteTest() {
        SequencePlayer player;
        try {
            player = new SequencePlayer(140, 12);
            Music a = new Note(1, new Pitch('A'));
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 69  Tick: 0\n" 
                    +"Event: NOTE_OFF Pitch: 69  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    // musicPlayLargeCaseTest(): row row row your boat
    @Test public void musicPlayLargeCaseTest() {
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            rowRowRowYourBoat.play(player, .125);
            player.play();
            Thread.sleep(10000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    // noteConstructorTest()
    @Test public void noteConstructorTest() {
        Note a = new Note(.1/5, new Pitch('A'));
        assertEquals("A0.02", a.toString());
    }
    
    // notePitchTest()
    @Test public void notePitchTest() {
        Note a = new Note(2, new Pitch('A'));
        assertEquals(new Pitch('A'), a.pitch());
    }
    
    // noteDurationTest()
    @Test public void noteDurationTest() {
        Note a = new Note(2, new Pitch('A'));
        assertTrue(2 == a.duration());
    }
    
    // noteTransposeTest()
    @Test public void noteTransposeTest() {
        Note a = new Note(2, new Pitch('A'));
        assertEquals(new Pitch('B'), a.transpose(2).pitch());
    }
    
    // notePlayTest(): covers all partitions
    @Test public void notePlayTest() {
        Note shortNote = new Note(.5, new Pitch('A'));
        Note highNote = new Note(20, new Pitch('A')).transpose(20);
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            shortNote.play(player, 0);
            highNote.play(player, .5);
            assertEquals("Event: NOTE_ON  Pitch: 69  Tick: 0\n" 
                   + "Event: NOTE_OFF Pitch: 69  Tick: 6\n" 
                   + "Event: NOTE_ON  Pitch: 89  Tick: 6\n"
                   + "Event: NOTE_OFF Pitch: 89  Tick: 246\n"
                   + "Meta event: END_OF_TRACK Tick: 246\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // noteHashCodeTest()
    @Test public void noteHashCodeTest() {
        Note a = new Note(2, new Pitch('F'));
        Note b = new Note(2.0, new Pitch('E'));
        assertEquals(a.transpose(-1).hashCode(), b.hashCode());
    }
    
    // noteEqualsTrueTest(): same pitch and duration; tests reflexivity, symmetry, and transitivity
    @Test public void noteEqualsTrueTest() {
        Note a = new Note(2, new Pitch('A'));
        Note a2 = new Note(2.0, new Pitch('E')).transpose(5);
        Note a3 = new Note(2.0, new Pitch('B')).transpose(-2);
        //reflexivity
        assertEquals(a,a);
        //symmetry
        assertEquals(a,a2);
        assertEquals(a2,a);
        //transitivity
        assertEquals(a,a2);
        assertEquals(a2,a3);
        assertEquals(a3,a);
    }
    
    // noteEqualsFalseTest(): covers all false partitions 
    // same pitch, different duration
    // same duration, different pitch
    // different duration, different pitch
    @Test public void noteEqualsFalseTest() {
        Note a2 = new Note(2, new Pitch('A'));
        Note a3 = new Note(3, new Pitch('A'));
        Note d3 = new Note(3, new Pitch('D'));
        assertFalse(a2.equals(a3));
        assertFalse(a3.equals(d3));
        assertFalse(a2.equals(d3));
    }
    
    // noteToStringTest(): integer duration, decimal duration 
    @Test public void noteToStringTest() {
        Note a2 = new Note(2, new Pitch('A'));
        Note a3 = new Note(.25, new Pitch('A'));
        assertEquals("A2.0", a2.toString());
        assertEquals("A0.25", a3.toString());
    }
        
    
    // restConstructorTest()
    @Test public void restConstructorTest() {
        Rest a = new Rest(5);
        assertEquals(".5.0", a.toString());
    }
    
    // restDurationTest()
    @Test public void restDurationTest() {
        Rest a = new Rest(5);
        assertTrue(5 == a.duration());
    }
    
    // restTransposeTest()
    @Test public void restTransposeTest() {
        Rest a = new Rest(5);
        Rest b = new Rest(5);
        assertEquals(a, b.transpose(2));
    }
    
    // restPlayTest(): 
    @Test public void restPlayTest() {
        Rest a = new Rest(5);
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Meta event: END_OF_TRACK Tick: 0\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // restHashCodeTest()
    @Test public void restHashCodeTest() {
        Rest a = new Rest(5);
        Rest b = new Rest(5);
        assertEquals(a.hashCode(), b.hashCode());
    }
    
    // restEqualsTrueTest(): same duration; tests reflexivity, symmetry, and transitivity
    @Test public void restEqualsTrueTest() {
        Rest a = new Rest(5);
        Rest b = new Rest(5);
        Rest c = new Rest(5);
        //reflexivity
        assertEquals(a,a);
        //symmetry
        assertEquals(a,b);
        assertEquals(b,a);
        //transitivity
        assertEquals(a,b);
        assertEquals(b,c);
        assertEquals(a,c);
    }
    
    // restEqualsFalseTest(): covers all false partitions 
    @Test public void restEqualsFalseTest() {
        Rest a = new Rest(5);
        Rest b = new Rest(10);
        assertFalse(a.equals(b));
    }
    
    // restToStringTest(): integer duration, decimal duration 
    @Test public void restToStringTest() {
        Rest a = new Rest(5);
        Rest b = new Rest(4.5);
        assertEquals(".5.0", a.toString());
        assertEquals(".4.5", b.toString());
    }
    
    // chordConstructorTest()
    @Test public void chordConstructorTest() {
        Chord a = new Chord(new Note(2., new Pitch('C')), new Note(1., new Pitch('F')));
        assertEquals("chord(C2.0 |||| F1.0)", a.toString());
    }
    
    
    // chordSameDurationTest(): m1, m2 same duration
    @Test public void chordSameDurationTest() {
        Chord a = new Chord(new Note(5, new Pitch('B')), new Note(5, new Pitch('A')));
        assertTrue(5==a.duration());
    }
    
    
    // chordDifferentDurationTest(): m1, m2 different duration
    @Test public void chordDifferentDurationTest() {
        Chord a = new Chord(new Note(1, new Pitch('C')), new Note(5, new Pitch('A')));
        assertTrue(a.duration()==a.top().duration());
        Chord b = new Chord(a, new Note(3, new Pitch('D')));
        assertTrue(b.top().duration()==b.duration());
        
        
    }
    
    // chordTransposeSameNotesTest(): m1, m2 same notes
    @Test public void chordTransposeSameNotesTest() {
        Chord a = new Chord(new Note(2, new Pitch('A')), new Note(5, new Pitch('A')));
        Music b = new Chord(new Note(2, new Pitch('B')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // chordTransposeDifferentNotesTest(): m1, m2 different notes
    @Test public void chordTransposeDifferentNotesTest() {
        Chord a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Chord(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
   
    
    // chordPlaySameNotesTest(): m1, m2 same notes
    @Test public void chordPlaySameNotesTest() {
        Chord a = new Chord(new Note(1, new Pitch('C')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // chordPlayDifferentNotesTest(): m1, m2 different notes
    @Test public void chordPlayDifferentNotesTest() {
        Chord a = new Chord(new Note(1, new Pitch('D')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    // chordPlayDifferentDurationsTest(): m1.duration != m2.duration
    @Test public void chordPlayDifferentDurationsTest() {
        Chord a = new Chord(new Note(1, new Pitch('D')), new Note(2, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                    + "Meta event: END_OF_TRACK Tick: 24\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // chordPlayEqualsTest(): m1 = m2
    @Test public void chordPlayEqualsTest() {
        Chord a = new Chord(new Note(1, new Pitch('C')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // chordlHashCodeTest()
    @Test public void chordHashCodeTest() {
        Chord a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Chord(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a.hashCode(),b.hashCode());
    }
    
    // chordEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
    @Test public void chordEqualsTrueTest() {
        Chord a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Chord(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Chord(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('A')));
        //reflexivity
        assertEquals(a,a);
        //symmetry
        assertEquals(a,b);
        assertEquals(b,a);
        //transitivity
        assertEquals(a,b);
        assertEquals(b,c);
        assertEquals(a,c);
    }
    
    // chordEqualsFalseTest(): all false partitions covered except last
    @Test public void chordEqualsFalseTest() {
        Music a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Chord(new Note(3, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Chord(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('B')));
        assertFalse(a.equals(b));
        assertFalse(b.equals(c));
        assertFalse(a.equals(c));
    }
    
    // chordEqualsFalseWrongOrderTest(): musics are identical but in wrong order
    @Test public void chordEqualsFalseWrongOrderTest() {
        Chord a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Chord b = new Chord(new Note(5, new Pitch('A')), new Note(2, new Pitch('C')));
        assertFalse(a.equals(b));
    }
    
    // chordToStringEmptyTest(): m1 and/or m2 empty
    @Test public void chordToStringEmptyTest() {
        Chord a = new Chord(new Rest(0), new Note(5, new Pitch('A')));
        assertEquals("chord(.0.0 |||| A5.0)", a.toString());
    }
    
    // chordToStringEqualsTest(): m1 and m2 equals
    @Test public void chordToStringEqualsTest() {
        Chord a = new Chord(new Note(5, new Pitch('A')), new Note(5, new Pitch('A')));
        assertEquals("chord(A5.0 |||| A5.0)", a.toString());
    }
    
    // chordToStringDifferentTest(): m1 and m2 non-equals
    @Test public void chordToStringDifferentTest() {
        Chord a = new Chord(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        assertEquals("chord(C2.0 |||| A5.0)", a.toString());
    }
    
    // parallelConstructorTest()
    @Test public void parallelConstructorTest() {
        Parallel a = new Parallel(new Note(2., new Pitch('C')), new Note(1., new Pitch('F')));
        assertEquals("parallel(C2.0 |||| F1.0)", a.toString());
    }
    
    // parallelEmptyTest(): m1 and/or m2 empty
    @Test public void parallelEmptyTest() {
        Parallel a = new Parallel(new Rest(0), new Rest(0));
        assertEquals("parallel(.0.0 |||| .0.0)", a.toString());
    }
    
    // parallelSameDurationTest(): m1, m2 same duration
    @Test public void parallelSameDurationTest() {
        Parallel a = new Parallel(new Rest(5), new Note(5, new Pitch('A')));
        assertTrue(5==a.duration());
    }
    
    
    // parallelDifferentDurationTest(): m1, m2 different duration
    @Test public void parallelDifferentDurationTest() {
        Parallel a = new Parallel(new Rest(1), new Note(5, new Pitch('A')));
        assertTrue(5==a.duration());
    }
    
    // parallelTransposeEmptyTest(): m1 and/or m2 empty
    @Test public void parallelTransposeEmptyTest() {
        Parallel a = new Parallel(new Rest(0), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Rest(0), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // parallelTransposeSameNotesTest(): m1, m2 same notes
    @Test public void parallelTransposeSameNotesTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('A')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(2, new Pitch('B')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // parallelTransposeDifferentNotesTest(): m1, m2 different notes
    @Test public void parallelTransposeDifferentNotesTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // parallelPlayEmptyTest(): m1 and/or m2 empty
    @Test public void parallelPlayEmptyTest() {
        Parallel a = new Parallel(new Rest(0), new Rest(0));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Meta event: END_OF_TRACK Tick: 0\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // parallelPlaySameNotesTest(): m1, m2 same notes
    @Test public void parallelPlaySameNotesTest() {
        Parallel a = new Parallel(new Note(1, new Pitch('C')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // parallelPlayDifferentNotesTest(): m1, m2 different notes
    @Test public void parallelPlayDifferentNotesTest() {
        Parallel a = new Parallel(new Note(1, new Pitch('D')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    // parallelPlayDifferentDurationsTest(): m1.duration != m2.duration
    @Test public void parallelPlayDifferentDurationsTest() {
        Parallel a = new Parallel(new Note(1, new Pitch('D')), new Note(2, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                    + "Meta event: END_OF_TRACK Tick: 24\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // parallelPlayEqualsTest(): m1 = m2
    @Test public void parallelPlayEqualsTest() {
        Parallel a = new Parallel(new Note(1, new Pitch('C')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Meta event: END_OF_TRACK Tick: 12\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // parallelHashCodeTest()
    @Test public void parallelHashCodeTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a.hashCode(),b.hashCode());
    }
    
    // parallelEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
    @Test public void parallelEqualsTrueTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Parallel(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('A')));
        //reflexivity
        assertEquals(a,a);
        //symmetry
        assertEquals(a,b);
        assertEquals(b,a);
        //transitivity
        assertEquals(a,b);
        assertEquals(b,c);
        assertEquals(a,c);
    }
    
    // parallelEqualsFalseTest(): all false partitions covered except last
    @Test public void parallelEqualsFalseTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(3, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Parallel(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('B')));
        assertFalse(a.equals(b));
        assertFalse(b.equals(c));
        assertFalse(a.equals(c));
    }
    
    // parallelEqualsFalseWrongOrderTest(): musics are identical but in wrong order
    @Test public void parallelEqualsFalseWrongOrderTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Parallel(new Note(5, new Pitch('A')), new Note(2, new Pitch('C')));
        assertFalse(a.equals(b));
    }
    
    // parallelToStringEmptyTest(): m1 and/or m2 empty
    @Test public void parallelToStringEmptyTest() {
        Parallel a = new Parallel(new Rest(0), new Note(5, new Pitch('A')));
        assertEquals("parallel(.0.0 |||| A5.0)", a.toString());
    }
    
    // parallelToStringEqualsTest(): m1 and m2 equals
    @Test public void parallelToStringEqualsTest() {
        Parallel a = new Parallel(new Note(5, new Pitch('A')), new Note(5, new Pitch('A')));
        assertEquals("parallel(A5.0 |||| A5.0)", a.toString());
    }
    
    // parallelToStringDifferentTest(): m1 and m2 non-equals
    @Test public void parallelToStringDifferentTest() {
        Parallel a = new Parallel(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        assertEquals("parallel(C2.0 |||| A5.0)", a.toString());
    }
    
    
    
    // mergeConstructorTest()
    @Test public void mergeConstructorTest() {
        Merge a = new Merge(new Note(2., new Pitch('C')), new Note(1., new Pitch('F')));
        assertEquals("C2.0 F1.0", a.toString());
    }
    
    // mergeEmptyTest(): m1 and/or m2 empty
    @Test public void mergeEmptyTest() {
        Merge a = new Merge(new Rest(0), new Rest(0));
        assertEquals(".0.0 .0.0", a.toString());
    }
    
    // mergeSameDurationTest(): m1, m2 same duration
    @Test public void mergeSameDurationTest() {
        Merge a = new Merge(new Rest(5), new Note(5, new Pitch('A')));
        assertTrue(10==a.duration());
    }
    
    
    // mergeDifferentDurationTest(): m1, m2 different duration
    @Test public void mergeDifferentDurationTest() {
        Merge a = new Merge(new Rest(1), new Note(5, new Pitch('A')));
        assertTrue(6==a.duration());
    }
    
    // mergeTransposeEmptyTest(): m1 and/or m2 empty
    @Test public void mergeTransposeEmptyTest() {
        Music a = new Merge(new Rest(0), new Note(5, new Pitch('A')));
        Music b = new Merge(new Rest(0), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // mergeTransposeSameNotesTest(): m1, m2 same notes
    @Test public void mergeTransposeSameNotesTest() {
        Music a = new Merge(new Note(2, new Pitch('A')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(2, new Pitch('B')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // mergeTransposeDifferentNotesTest(): m1, m2 different notes
    @Test public void mergeTransposeDifferentNotesTest() {
        Music a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a,b);
    }
    
    // mergePlayEmptyTest(): m1 and/or m2 empty
    @Test public void mergePlayEmptyTest() {
        Merge a = new Merge(new Rest(0), new Rest(0));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Meta event: END_OF_TRACK Tick: 0\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // mergePlaySameNotesTest(): m1, m2 same notes
    @Test public void mergePlaySameNotesTest() {
        Merge a = new Merge(new Note(1, new Pitch('C')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                    + "Meta event: END_OF_TRACK Tick: 24\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // mergePlayDifferentNotesTest(): m1, m2 different notes
    @Test public void mergePlayDifferentNotesTest() {
        Merge a = new Merge(new Note(1, new Pitch('D')), new Note(1, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                    + "Meta event: END_OF_TRACK Tick: 24\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    // mergePlayDifferentDurationsTest(): m1.duration != m2.duration
    @Test public void mergePlayDifferentDurationsTest() {
        Merge a = new Merge(new Note(1, new Pitch('D')), new Note(2, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 62  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 62  Tick: 12\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 36\n"
                    + "Meta event: END_OF_TRACK Tick: 36\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // mergePlayEqualsTest(): m1 = m2
    @Test public void mergePlayEqualsTest() {
        Merge a = new Merge(new Note(1.0, new Pitch('C')), new Note(1.0, new Pitch('C')));
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            a.play(player, 0);
            assertEquals("Event: NOTE_ON  Pitch: 60  Tick: 0\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_ON  Pitch: 60  Tick: 12\n"
                    + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                    + "Meta event: END_OF_TRACK Tick: 24\n", player.toString());
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } 
    }
    
    // mergeHashCodeTest()
    @Test public void mergeHashCodeTest() {
        Music a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        assertEquals(a.hashCode(),b.hashCode());
    }
    
    // mergeEqualsTrueTest(): all true partitions covered; tests reflexivity, symmetry, and transitivity
    @Test public void mergeEqualsTrueTest() {
        Music a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(2, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Merge(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('A')));
        //reflexivity
        assertEquals(a,a);
        //symmetry
        assertEquals(a,b);
        assertEquals(b,a);
        //transitivity
        assertEquals(a,b);
        assertEquals(b,c);
        assertEquals(a,c);
    }
    
    // mergeEqualsFalseTest(): all false partitions covered except last
    @Test public void mergeEqualsFalseTest() {
        Music a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(3, new Pitch('D')), new Note(5, new Pitch('B'))).transpose(-2);
        Music c = new Merge(new Note(2, new Pitch('D')).transpose(-2), new Note(5, new Pitch('B')));
        assertFalse(a.equals(b));
        assertFalse(b.equals(c));
        assertFalse(a.equals(c));
    }
    
    // mergeEqualsFalseWrongOrderTest(): musics are identical but in wrong order
    @Test public void mergeEqualsFalseWrongOrderTest() {
        Music a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        Music b = new Merge(new Note(5, new Pitch('A')), new Note(2, new Pitch('C')));
        assertFalse(a.equals(b));
    }
    
    // mergeToStringEmptyTest(): m1 and/or m2 empty
    @Test public void mergeToStringEmptyTest() {
        Merge a = new Merge(new Rest(0), new Note(5, new Pitch('A')));
        assertEquals(".0.0 A5.0", a.toString());
    }
    
    // mergeToStringEqualsTest(): m1 and m2 equals
    @Test public void mergeToStringEqualsTest() {
        Merge a = new Merge(new Note(5, new Pitch('A')), new Note(5, new Pitch('A')));
        assertEquals("A5.0 A5.0", a.toString());
    }
    
    // mergeToStringDifferentTest(): m1 and m2 non-equals
    @Test public void mergeToStringDifferentTest() {
        Merge a = new Merge(new Note(2, new Pitch('C')), new Note(5, new Pitch('A')));
        assertEquals("C2.0 A5.0", a.toString());
    }
}
