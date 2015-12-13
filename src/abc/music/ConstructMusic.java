package abc.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import abc.parser.MusicGrammarListener;
import abc.parser.MusicGrammarParser.AbclineContext;
import abc.parser.MusicGrammarParser.AbcmusicContext;
import abc.parser.MusicGrammarParser.AccidentalContext;
import abc.parser.MusicGrammarParser.BarlineContext;
import abc.parser.MusicGrammarParser.CommentContext;
import abc.parser.MusicGrammarParser.ElementContext;
import abc.parser.MusicGrammarParser.EndoflineContext;
import abc.parser.MusicGrammarParser.FieldvoiceContext;
import abc.parser.MusicGrammarParser.HighoctaveContext;
import abc.parser.MusicGrammarParser.LowoctaveContext;
import abc.parser.MusicGrammarParser.MidtunefieldContext;
import abc.parser.MusicGrammarParser.MultinoteContext;
import abc.parser.MusicGrammarParser.NoteContext;
import abc.parser.MusicGrammarParser.NoteelementContext;
import abc.parser.MusicGrammarParser.NotelengthContext;
import abc.parser.MusicGrammarParser.NoteorrestContext;
import abc.parser.MusicGrammarParser.NthrepeatContext;
import abc.parser.MusicGrammarParser.OctaveContext;
import abc.parser.MusicGrammarParser.PitchContext;
import abc.parser.MusicGrammarParser.RestContext;
import abc.parser.MusicGrammarParser.RootContext;
import abc.parser.MusicGrammarParser.TextContext;
import abc.parser.MusicGrammarParser.TupletelementContext;
import abc.parser.MusicGrammarParser.TupletspecContext;

/**
 * Constructs the music from the parser tree of music body
 * using the header information passed in constructor.
 */
public class ConstructMusic implements MusicGrammarListener {
    
    private Stack<Music> musicStack = new Stack<>();
    private Stack<String> stringStack = new Stack<>();
    private Stack<Music> music2Stack = new Stack<>();
    
    private HeaderInfo header;
    private String defaultRatio;
    private String currentRatio;
    
    private Map<String,String> currentState = new HashMap<String,String>();
    private String currentVoice = "";
    private MusicLanguage musicLanguage = new MusicLanguage();
    
    private Map<String,Music> musicMap = new HashMap<String,Music>();
    
    /**
     * Constructor initializing values based on header information
     * @param header passes header information
     */
    public ConstructMusic(HeaderInfo header) {
        this.header = header;
        
        String[] note1Split = header.getL().split("/");
        String[] note2Split = header.getQNoteLength().split("/");
        
        int num1 = Integer.valueOf(note1Split[0]);
        int num2 = Integer.valueOf(note2Split[0]);
        int den1 = Integer.valueOf(note1Split[1]);
        int den2 = Integer.valueOf(note2Split[1]);
        
        String noteRatio = String.valueOf((num1*den2))+"/"+String.valueOf((den1*num2));
                
        this.defaultRatio = noteRatio;
        this.currentRatio = noteRatio;  
    }
    
    /**
     * Multiply 2 ratios in string formt together, and return a string ratio
     * @param ratio1 first multiplier
     * @param ratio2 second multiplier
     * @return ratio representing the product of passed ratios
     */
    private String multiplyRatios(String ratio1, String ratio2) {
        String[] splitRatio1 = ratio1.split("/");
        String[] splitRatio2 = ratio2.split("/");
        
        Integer numer = Integer.valueOf(splitRatio1[0])*Integer.valueOf(splitRatio2[0]);
        Integer denom = Integer.valueOf(splitRatio1[1])*Integer.valueOf(splitRatio2[1]);
        
        return String.valueOf(numer)+"/"+String.valueOf(denom);
    }
    
    /**
     * Convert lowercase note to Uppercase note if lowercase note is in lower octave (has a , attached)
     * @param baseNote initial note
     * @param octave representing the octave change to be made (will be one or more ,)
     * @return new note in the lower octave, with additional octaves attached (ex: a,, would be A,)
     */
    private String convertUpperBasenote(String baseNote, String octave) {
        if (octave.length() == 1 ) {
            return baseNote.toUpperCase();
        }
        else {
            return baseNote.toUpperCase()+octave.substring(1,octave.length());
        }
    }
    
    /**
     * Convert uppercase note to lowercase note if uppercase note is in higher octave (has a ' attached)
     * @param baseNote initial note
     * @param octave representing the octave change to be made (will be one or more ')
     * @return new note in the upper octave, with additional octaves attached (Ex: A'' would be a')
     */
    private String convertLowerBasenote(String baseNote, String octave) {
        if (octave.length() == 1 ) {
            return baseNote.toLowerCase();
        }
        else {
            return baseNote.toLowerCase()+octave.substring(1,octave.length());
        }
    }
    
    /**
     * Convert string ratio to double
     * @param value ratio to be converted. denominator may not be zero.
     * @return double value of the ratio
     */
    private double changeToDouble(String value) {
        String[] splitString = value.split("/");
        String first = splitString[0];
        String last = splitString[1];
        
        return Double.valueOf(first)/Double.valueOf(last);
    }
    
    /**
     * Merge music based on their index in the list;
     * music having smaller index is scheduled earlier in merge.
     * @param musicList passed list of music
     * @return music representing the whole merge (music played in series)
     */
    private Music stringMusicTogether(List<Music> musicList) {
        if (musicList.size() >1) {
            Merge mergedMusic = new Merge(musicList.get(0),musicList.get(1));
            for (int i=2; i < musicList.size(); i++) {
                mergedMusic = new Merge(mergedMusic, musicList.get(i));           
            }
            return mergedMusic;
        }
        return musicList.get(0);
    }
    
    /**
     * Create music being passed music in parallel.
     * @param musicSet set of music objects 
     * @return music being all the music in the passed collection played together (in parallel)
     */
    private Music playMusicParallel(Collection<Music> musicSet) {
        List<Music> musicList = new ArrayList<Music>();
        for (Music music: musicSet) {
            musicList.add(music);
        }
        if (musicList.size() > 1) {
            Parallel mergedMusic = new Parallel(musicList.get(0),musicList.get(1));
            for (int i=2; i < musicList.size(); i++) {
                mergedMusic = new Parallel(mergedMusic, musicList.get(i));
            }
            return mergedMusic;
        }
        return musicList.get(0);  
    }
    
    /**
     * Create the chord from passed collection of music
     * that has ordering since always list is passed as collection.
     * @param musicSet set of music (in an exact order provided by list passed in)
     * @return chord constructed as described above, with order given by order of elements in passed in list
     */
    private Music playMusicChord(Collection<Music> musicSet) {
        List<Music> musicList = new ArrayList<Music>();
        for (Music music: musicSet) {
            musicList.add(music);
        }
        Chord mergedMusic = new Chord(musicList.get(0),musicList.get(1));
        for (int i=2; i < musicList.size(); i++) {
            mergedMusic = new Chord(mergedMusic, musicList.get(i));           
        }
        return mergedMusic;
    }
    
    /**
     * @returns a musical piece representing all music from voices played in parallel
     */
    public Music getExpression() {
        musicMap.put(currentVoice,music2Stack.pop());
        Music parallelMusic = playMusicParallel(musicMap.values());
        return parallelMusic;
    }

    /**
     * At this point, we push all music onto a new music stack.
     * This music stack represents all the music to be played in the piece. It will be played in parallel. 
     * Exciting this node symbolizes finishing parsing the music.
     */
    @Override
    public void exitAbcmusic(AbcmusicContext ctx) {
        // matched the abcline+ rule
        List<AbclineContext> abclines = ctx.abcline();
        assert music2Stack.size() <= abclines.size();

        // the pattern above always has at least 1 child;
        // pop the last child
        assert abclines.size() > 0;

        // pop the older children, one by one, and add them on
        List<Music> poppedList = new ArrayList<Music>(Arrays.asList(music2Stack.pop()));
        while (music2Stack.size()>0) {
            poppedList.add(0,music2Stack.pop());
        }
        Music musicalPiece;
        musicalPiece = stringMusicTogether(poppedList);
        
        // the result is this subtree's Sum
        music2Stack.push(musicalPiece); 
    }

    /**
     * Represents exciting a musical line within the piece. If the line contained a piece(s) of music,
     * join these pieces of music in series and add it to the music stack. This represents the construction of a musical
     * line within the piece.
     */
    @Override
    public void exitAbcline(AbclineContext ctx) {
        if (ctx.midtunefield() != null) {
            //do nothing, music of other voice has already been accounted for
        }
        else if (ctx.comment() != null) {
            //do nothing, we don't need the comments to play music
        }
        else {
            // matched the element+ NEWLINE rule
            List<ElementContext> elements = ctx.element();
            assert musicStack.size() <= elements.size();

            // the pattern above always has at least 1 child;
            // pop the last child
            assert elements.size() > 0;

            // pop the older children, one by one, and add them on
            List<Music> poppedList = new ArrayList<Music>(Arrays.asList(musicStack.pop()));
            while (musicStack.size()>0) {
                poppedList.add(0,musicStack.pop());
            }
            Music musicalPiece;
            musicalPiece = stringMusicTogether(poppedList);

            // the result is this subtree's Sum
            music2Stack.push(musicalPiece);
        }    
    }

    /**
     * Create new Music instance which is the merge of
     * all of its sub-music already being as Music instances
     * inside the stack. 
     * It can be either noteelement or tupletelement or nthrepeat.
     * I nthrepeat appears, find "|:", ":|", "[1", "[2"
     * appearances in order to find which pieces of the music should be repeated.
     * Create the Music instance for each of the piece and set the whole nthrepeat 
     * instance of Music to be them appropriately merged with each other.
     * Only then merge it with rest of the appropriate Music instances appeared in the stack.
     * Add final result into the stack
     */
    @Override
    public void exitElement(ElementContext ctx) {
        if (ctx.noteelement() != null) {
            //do nothing, note element is already on the stack
        }
        else if (ctx.tupletelement() != null) {
            //do nothing, tuplet element is already on the stack
        }
        else if (ctx.barline() != null) {
            //do nothing, we dont need to consider the barline in the Music ADT
        }
        else if (ctx.nthrepeat() != null){
            //do nothing, tuplet element is already on the stack
        }
        else {
            //do nothing, this is just whitespace
        }
    }

    /**
     * Do nothing here because note/multinote are already on the stack.
     */
    @Override
    public void exitNoteelement(NoteelementContext ctx) {
        //do nothing, note or multinote are already on the stack 
    }

    /**
     * Create new Note type of music and add to the stack.
     * It's based on pitch and measure included inside the string.
     */
    @Override
    public void exitNote(NoteContext ctx) {
        if (ctx.notelength() != null) {
            String noteLengthPart = stringStack.pop();
            String notePart = stringStack.pop();
            
            if (notePart.equals("z")) {
                Music rest = new Rest(changeToDouble(multiplyRatios(noteLengthPart,currentRatio)));
                musicStack.push(rest);
            }
            else {
                Music note = musicLanguage.parseSymbol(notePart+multiplyRatios(noteLengthPart,currentRatio), header.getK());
                musicStack.push(note);
            }
        }
        
        else {
            String notePart = stringStack.pop();
            String noteLengthPart = "1/1";  
            
            if (notePart.equals("z")) {
                Music rest = new Rest(changeToDouble(multiplyRatios(noteLengthPart,currentRatio)));
                musicStack.push(rest);
            }
            else {
                Music note = musicLanguage.parseSymbol(notePart+multiplyRatios(noteLengthPart,currentRatio), header.getK());
                musicStack.push(note);
            }
        }  
    }

    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitNoteorrest(NoteorrestContext ctx) {
        //do nothing, rest or pitch are already on the stack
    }

    /**
     * Construct a note symbol and add this to the string stack. The note symbol will have a basenote, and accidentals
     * will be added to the front and octaves to the back of this basenote symbol. Accidentals and octaves are optional, 
     * but the baseNote must occur. Push this string object onto the front of the string stack.
     */
    @Override
    public void exitPitch(PitchContext ctx) {
        String fullNote = "";
        String accidentalAdd = "";
        String octaveAdd = "";
        String baseNote = ctx.BASENOTE().getText();
        
        if (ctx.octave() != null) {
            octaveAdd = stringStack.pop();
            if (baseNote.matches("[abcdefg]") && (octaveAdd.contains(","))) {
                octaveAdd = convertUpperBasenote(baseNote, octaveAdd);
                fullNote = octaveAdd+fullNote;
            }
            else if (baseNote.matches("[ABCDEFG]") && (octaveAdd.contains("'"))) {
                octaveAdd = convertLowerBasenote(baseNote, octaveAdd);
                fullNote = octaveAdd + fullNote;
            }
            else {
                fullNote = baseNote+octaveAdd+fullNote; 
            }
        }
        else {
            fullNote = baseNote+fullNote;
        }        
        
        if (ctx.accidental() != null) {
            accidentalAdd = stringStack.pop();
            if (!(accidentalAdd.equals("="))) {
                fullNote=accidentalAdd+fullNote;
            }
            currentState.put(baseNote,accidentalAdd);
        }
        else {
            if (currentState.containsKey(baseNote) && !(currentState.get(baseNote).equals("="))) {
                fullNote=currentState.get(baseNote)+fullNote;
            }
        }
        stringStack.push(fullNote);
    }

    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitOctave(OctaveContext ctx) {
        //do nothing, octaves are already on stack       
    }

    /**
     * Account for note lengths within the musical piece. If either denom or numer are
     * left empty, we adjust such that default numerator is 1 and default denominator is 2.
     * If no / is included, the notelength is 1/1.
     */
    @Override
    public void exitNotelength(NotelengthContext ctx) {
        String ratioNote =ctx.getText();
        if (ratioNote.charAt(0) == '/') {
            ratioNote = "1"+ratioNote;
        }
        if (ratioNote.charAt(ratioNote.length()-1) == '/') {
            ratioNote = ratioNote+"2";
        }
        if (ratioNote.length() >= 1 && !(ratioNote.contains("/"))) {
            ratioNote = ratioNote + "/1";
        }
        stringStack.push(ratioNote);
    }

    /**
     * Push an accidental onto the stringStack if it appears.
     */
    @Override
    public void exitAccidental(AccidentalContext ctx) {
        stringStack.push(ctx.getText());
        
    }

    /**
     * Push a higher octave symbol onto the stringStack if it appears.
     */
    @Override
    public void exitHighoctave(HighoctaveContext ctx) {
        stringStack.push(ctx.getText());    
    }

    /**
     * Push a lower octave symbol onto the stringStack if it appears.
     */
    @Override
    public void exitLowoctave(LowoctaveContext ctx) {
        stringStack.push(ctx.getText());
        
    }

    /**
     * Create new Rest type of music and add to the stack
     */
    @Override
    public void exitRest(RestContext ctx) {
        stringStack.push("z");
        
    }

    /**
     * Return the ratio to its default value. Pop children off of the
     * string stack, create a musical piece that plays these notes in series, 
     * and push this piece of music onto the music stack. Represents the creation of a music object.
     */
    @Override
    public void exitTupletelement(TupletelementContext ctx) {
        currentRatio = defaultRatio;
        
        // matched the noteelement+ rule
        List<NoteelementContext> noteelements = ctx.noteelement();
        assert musicStack.size() >= noteelements.size();

        // the pattern above always has at least 1 child;
        // pop the last child
        assert noteelements.size() > 0;

        // pop the older children, one by one, and add them on
        List<Music> poppedList = new ArrayList<Music>(Arrays.asList(musicStack.pop()));
        for (int i = 1; i < noteelements.size(); ++i) {
            poppedList.add(0,musicStack.pop());
        }
        Music musicalPiece = stringMusicTogether(poppedList);

        // the result is this subtree's Sum
        musicStack.push(musicalPiece);
    }

    /**
     * Exit the tuplet node. At this point, we know how many notes are in the tuplet. Because we know
     * how many notes are in the tuplet, we can set the current ratio such that when multiplied by the length
     * of each note in the tuplet, we get the correct note length.
     */
    @Override
    public void exitTupletspec(TupletspecContext ctx) {   
        Integer numNotes = Integer.valueOf(ctx.DIGIT().getText());
        String ratio;
        if (numNotes == 2) {
            ratio = "3/2";
        }
        else if (numNotes == 3) {
            ratio = "2/3";
        }
        else {
            ratio = "3/4";
        }  
        currentRatio = multiplyRatios(ratio,defaultRatio);
    }
    
    /**
     * Create individual Note Music instances for each note inside
     * the chord. Add constructed music in the music stack.
     */
    @Override
    public void exitMultinote(MultinoteContext ctx) {
        // matched the "[" note+ "]" rule
        List<NoteContext> notes = ctx.note();
        assert musicStack.size() >= notes.size();

        // the pattern above always has at least 1 child;
        // pop the last child
        assert notes.size() > 0;

        // pop the older children, one by one, and add them on
        List<Music> poppedList = new ArrayList<Music>(Arrays.asList(musicStack.pop()));
        for (int i = 1; i < notes.size(); ++i) {
            poppedList.add(0,musicStack.pop());
        }
        Music musicalPiece = playMusicChord(poppedList);

        // the result is this subtree's Sum
        musicStack.push(musicalPiece);
    }

    /**
     * End of a measure, so clear all accidentals that are still active.
     */
    @Override
    public void exitBarline(BarlineContext ctx) {
        currentState.clear();
    }

    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitNthrepeat(NthrepeatContext ctx) {
      //do nothing, repeats are handled before being passed into parser  
    }

    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitMidtunefield(MidtunefieldContext ctx) {
        //do nothing because change of voices was handled in exitFieldVoice    
    }

    /**
     * At this point, take all of the music from the musicStack and make one musical piece in series. 
     * Then store this object in the voices dictionary under the key of the current voice. This will
     * be the method that creates musical pieces for different voices, and then we will play these pieces in parallel later. 
     * After creating the musical piece is finished, the musicStack should be empty again, and ready to start constructing
     * the music of another voice.
     */
    @Override
    public void exitFieldvoice(FieldvoiceContext ctx) {
        List<Music> musics = new ArrayList<Music>();
        while (music2Stack.size() > 0) {
            musics.add(0,music2Stack.pop());
        }
        if (musics.size() > 0) {
            Music musicSequence = stringMusicTogether(musics);
            if (musicMap.containsKey(currentVoice)) {
                musicMap.put(currentVoice, new Merge(musicMap.get(currentVoice),musicSequence));
            }
            else {
                musicMap.put(currentVoice, musicSequence);
            }
            String voice = stringStack.pop();
            currentVoice = voice;
        }
        else {
            String voice = stringStack.pop();
            currentVoice = voice;
        }
    }

    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitEndofline(EndoflineContext ctx) {
        //do nothing, because we don't care about end of line for playing music
    }

    /**
     * Push all characters that are children of textContext onto the stringStack. Items are appending to 
     * the front of the stringStack.
     */
    @Override
    public void exitText(TextContext ctx) {
        if (ctx.TEXTCHAR() != null) {
            // matched the TEXTCHAR alternative
            String textChar = ctx.TEXTCHAR().getText();
            stringStack.push(textChar);
        } 
        else if (ctx.DIGIT() != null) {
            // matched the DIGIT alternative
            String digit = ctx.DIGIT().getText();
            stringStack.push(digit);
        }
        else if (ctx.BASENOTE() != null) {
         // matched the BASENOTE alternative
            String baseNote = ctx.BASENOTE().getText();
            stringStack.push(baseNote);
        }
    }
    
    /**
     * Do nothing since rest or pitch are already on the stack.
     */
    @Override
    public void exitComment(CommentContext ctx) {
        //do nothing
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void enterEndofline(EndoflineContext ctx) { }
    
    @Override
    public void enterText(TextContext ctx) { }
    
    @Override
    public void enterComment(CommentContext ctx) { }
    
    @Override
    public void enterFieldvoice(FieldvoiceContext ctx) { }
    
    @Override
    public void enterMidtunefield(MidtunefieldContext ctx) { }
    
    @Override
    public void enterNthrepeat(NthrepeatContext ctx) { }
    
    @Override
    public void enterNoteorrest(NoteorrestContext ctx) { }
    
    @Override
    public void enterPitch(PitchContext ctx) {  }
    
    @Override
    public void enterOctave(OctaveContext ctx) { }
    
    @Override
    public void enterNotelength(NotelengthContext ctx) { }
    
    @Override
    public void enterAccidental(AccidentalContext ctx) { }
    
    @Override
    public void enterHighoctave(HighoctaveContext ctx) { }
    
    @Override
    public void enterLowoctave(LowoctaveContext ctx) { }
    
    @Override
    public void enterRest(RestContext ctx) {  }
    
    @Override
    public void enterAbcline(AbclineContext ctx) {  }
    
    @Override
    public void enterEveryRule(ParserRuleContext arg0) { }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {  }

    @Override
    public void visitErrorNode(ErrorNode arg0) {  }

    @Override
    public void visitTerminal(TerminalNode arg0) { }

    @Override
    public void enterRoot(RootContext ctx) { }

    @Override
    public void exitRoot(RootContext ctx) { }
    
    @Override
    public void enterElement(ElementContext ctx) { }
    
    @Override
    public void enterNoteelement(NoteelementContext ctx) { }
    
    @Override
    public void enterNote(NoteContext ctx) { }
    
    @Override
    public void enterBarline(BarlineContext ctx) { }
    
    @Override
    public void enterMultinote(MultinoteContext ctx) { }
    
    @Override
    public void enterTupletspec(TupletspecContext ctx) { }
    
    @Override
    public void enterTupletelement(TupletelementContext ctx) { }
    
    @Override
    public void enterAbcmusic(AbcmusicContext ctx) { }

}
