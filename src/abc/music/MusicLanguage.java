package abc.music;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import abc.sound.Pitch;

/**
 * MusicLanguage defines static methods for constructing and manipulating Music expressions.
 */
public class MusicLanguage {

    // Store key signature adjustments for transpositions of pitches into proper keys
    private final Map<String, Map<Character, Integer>> keyDict; 
    
    // Abstraction function: 
    //   Represents a string music expression reader where keyDict is 
    //   used to transpose notes into their proper key signature. 
    // Rep invariant:
    //   keyDict is not null
    // Rep exposure:
    //   All the fields are private and final. 
    //   Although keyDict is  mutable dictionary, the client does not have
    //   access to it because it's private and function that returns it
    //   is also private. All the returned data is immutable.
    
    /**
     * Adapted from 6.005 Exercise 26 Music Language
     * 
     * Make Music from a string using a variant of abc notation
     *    (see http://www.walshaw.plus.com/abc/examples/).
     * 
     * The notation consists of whitespace-delimited symbols representing either
     * notes or rests. The vertical bar | may be used as a delimiter 
     * for measures; notes() treats it as a space.
     * Grammar:
     *     notes ::= symbol*
     *     symbol ::= . duration          // for a rest
     *              | pitch duration      // for a note
     *     pitch ::= accidental letter octave*
     *     accidental ::= empty string    // for natural,
     *                  | _               // for flat,
     *                  | ^               // for sharp
     *     letter ::= [A-G]
     *     octave ::= '                   // to raise one octave
     *              | ,                   // to lower one octave
     *     duration ::= empty string      // for 1-beat duration
     *                | /n                // for 1/n-beat duration
     *                | n                 // for n-beat duration
     *                | n/m               // for n/m-beat duration
     * 
     * Examples (assuming 4/4 common time, i.e. 4 beats per measure):
     *     C     quarter note, middle C
     *     A'2   half note, high A
     *     _D/2  eighth note, middle D flat
     * 
     * @param notes string of notes and rests in simplified abc notation given above
     * @param key key signature of music
     *      Must be a standard major or minor key, represented as 
     *      a string of up to three characters, matching regex 
     *      "([ABCDEFG])(b|#)?(m)?" where:
     *          -The first character indicates the major key
     *          -The second character (if present) indicates
     *              -b --> flat
     *              -# --> sharp
     *          -The third character m (if present) indicates 
     *           key is minor 
     */     
    
    
    /**
     * Creates an instance of MusicLanguage. 
     * 
     * Initializes key signature map, which stores key signature adjustments 
     * for transpositions of pitches into proper keys
     */
    public MusicLanguage(){
        this.keyDict = generateKeySignatureMap();
        checkRep();
    }
    
    /**
     * Takes in a string of symbols representing music (according to class spec) and
     * constructs corresponding Music ADT object. Transposes the notes from 
     * C into the key signature key.
     * 
     * @param notes a String representing a voice of music
     * @param key key signature to transpose Music into from C
     * @return Music object corresponding to music represented by notes
     */
    public Music notes(String notes, String key) { 
        System.out.println(notes);
        assert(key.matches("([ABCDEFG])(b|#)?(m)?")); // make sure key is valid
        String[] symbols = notes.split("[\\s|]+");
        // rest(0) represents empty music 
        if (symbols.length == 0) 
            return rest(0);
        Music music = parseSymbol(symbols[0], key);
        for (int i=1; i<symbols.length; i++) {
            if (!symbols[i].isEmpty()) {
                music = concat(music, parseSymbol(symbols[i], key));
            }
        }
        return music;
    }
    
    /**
     * Asserts the rep invariant
     */
    private void checkRep() {
        assert this.keyDict != null;
    }
    
    /**
     * Parse a symbol into a Note or a Rest
     * 
     * @param symbol, a collection of characters obeying spec at top of class
     * @param key key signature of piece obeying spec at top of class
     * @throws IllegalArgumentException if symbol is invalid 
     */
    public Music parseSymbol(String symbol, String key) {
        Matcher m = Pattern.compile("([^/0-9]*)([0-9]+)?(/[0-9]+)?").matcher(symbol);
        if (!m.matches()) throw new IllegalArgumentException("couldn't understand " + symbol);

        String pitchSymbol = m.group(1);

        double duration = 1.0;
        if (m.group(2) != null) duration *= Integer.valueOf(m.group(2));
        if (m.group(3) != null) duration /= Integer.valueOf(m.group(3).substring(1));

        if (pitchSymbol.equals(".")) return rest(duration);
        else return note(duration, parsePitch(pitchSymbol, key));
    }
    
    /**
     * Parse a symbol into a pitch in the proper key. 
     * Assumes the symbol represents a note in the key of C and then
     * adds / subtracts a semitone if the pitch represented by the note
     * has an accidental in the key signature represented by key
     * 
     * @param symbol obeying grammar in spec at top of class 
     * @param key key signature obeying grammar in spec at top of class
     * @return Pitch transposed to proper key 
     * @throws IllegalArgumentException if symbol is invalid 
     */
    public Pitch parsePitch(String symbol, String key) {
        if (symbol.endsWith("'")) return parsePitch(symbol.substring(0, symbol.length()-1), key).transpose(Pitch.OCTAVE);
        else if (symbol.endsWith(",")) return parsePitch(symbol.substring(0, symbol.length()-1), key).transpose(-Pitch.OCTAVE);
        else if (symbol.startsWith("^")) return parsePitch(symbol.substring(1), key).transpose(1);
        else if (symbol.startsWith("_")) return parsePitch(symbol.substring(1), key).transpose(-1);
        else if (symbol.length() != 1) throw new IllegalArgumentException("can't understand " + symbol);
        else{
            char letter = symbol.charAt(0);
            int amountToTranspose = 0;
            // handles specification that lower case symbols represent notes an octave up
            if (Character.isLowerCase(letter)){
                amountToTranspose = amountToTranspose + Pitch.OCTAVE;
            }
            int keyAdjustment = this.keyDict.get(key).get(Character.toUpperCase(letter));
            amountToTranspose = amountToTranspose + keyAdjustment;
            
            return new Pitch(Character.toUpperCase(letter)).transpose(amountToTranspose);

        }
    }
    
    /**
     * @return a nested map representing the integer amount to transpose a pitch in the key of C 
     * to another key signature. 
     * 
     * Assume each semitone has value 1. Upwards transpositions have positive value while downwards
     * transpositions have negative value. 
     * 
     * The structure of the map is as follows: 
     *      key (String obeying grammar for key signature) --> (pitch (Character in upper case), amount to transpose (integer))
     *      
     * This function handles all major and minor keys. 
     * 
     * Keys supported: 
     *  -Major: C, G, D, A, E, B, F#, Cb, Gb, Db, Ab, Eb, Bb, F
     *  -Minor: Am, Em, Bm, F#m, C#m, G#m, D#m, Ebm, Bbm, Fm, Cm, Gm, Dm
     * 
     * Pitches supported: A, B, C, D, E, F, G
     */
    private Map<String, Map<Character, Integer>> generateKeySignatureMap(){
        final Map<String, Map<Character, Integer>> keyDict = new HashMap<String, Map<Character, Integer>>();
        
        final Map<Character, Integer> cMajor = new HashMap<Character, Integer>();
        cMajor.put('A', 0);
        cMajor.put('B', 0);
        cMajor.put('C', 0);
        cMajor.put('D', 0);
        cMajor.put('E', 0);
        cMajor.put('F', 0);
        cMajor.put('G', 0);
        
        final Map<Character, Integer> gMajor = new HashMap<Character, Integer>();
        gMajor.put('A', 0);
        gMajor.put('B', 0);
        gMajor.put('C', 0);
        gMajor.put('D', 0);
        gMajor.put('E', 0);
        gMajor.put('F', 1);
        gMajor.put('G', 0);
        
        final Map<Character, Integer> dMajor = new HashMap<Character, Integer>();
        dMajor.put('A', 0);
        dMajor.put('B', 0);
        dMajor.put('C', 1);
        dMajor.put('D', 0);
        dMajor.put('E', 0);
        dMajor.put('F', 1);
        dMajor.put('G', 0);
        
        final Map<Character, Integer> aMajor = new HashMap<Character, Integer>();
        aMajor.put('A', 0);
        aMajor.put('B', 0);
        aMajor.put('C', 1);
        aMajor.put('D', 0);
        aMajor.put('E', 0);
        aMajor.put('F', 1);
        aMajor.put('G', 1);
        
        final Map<Character, Integer> eMajor = new HashMap<Character, Integer>();
        eMajor.put('A', 0);
        eMajor.put('B', 0);
        eMajor.put('C', 1);
        eMajor.put('D', 1);
        eMajor.put('E', 0);
        eMajor.put('F', 1);
        eMajor.put('G', 1);
        
        final Map<Character, Integer> bMajor = new HashMap<Character, Integer>();
        bMajor.put('A', 1);
        bMajor.put('B', 0);
        bMajor.put('C', 1);
        bMajor.put('D', 1);
        bMajor.put('E', 0);
        bMajor.put('F', 1);
        bMajor.put('G', 1);
        
        final Map<Character, Integer> fSharpMajor = new HashMap<Character, Integer>();
        fSharpMajor.put('A', 1);
        fSharpMajor.put('B', 0);
        fSharpMajor.put('C', 1);
        fSharpMajor.put('D', 1);
        fSharpMajor.put('E', 1);
        fSharpMajor.put('F', 1);
        fSharpMajor.put('G', 1);
        
        final Map<Character, Integer> cSharpMajor = new HashMap<Character, Integer>();
        cSharpMajor.put('A', 1);
        cSharpMajor.put('B', 1);
        cSharpMajor.put('C', 1);
        cSharpMajor.put('D', 1);
        cSharpMajor.put('E', 1);
        cSharpMajor.put('F', 1);
        cSharpMajor.put('G', 1);
        
        final Map<Character, Integer> fMajor = new HashMap<Character, Integer>();
        fMajor.put('A', 0);
        fMajor.put('B', -1);
        fMajor.put('C', 0);
        fMajor.put('D', 0);
        fMajor.put('E', 0);
        fMajor.put('F', 0);
        fMajor.put('G', 0);
        
        final Map<Character, Integer> bFlatMajor = new HashMap<Character, Integer>();
        bFlatMajor.put('A', 0);
        bFlatMajor.put('B', -1);
        bFlatMajor.put('C', 0);
        bFlatMajor.put('D', 0);
        bFlatMajor.put('E', -1);
        bFlatMajor.put('F', 0);
        bFlatMajor.put('G', 0);
        
        final Map<Character, Integer> eFlatMajor = new HashMap<Character, Integer>();
        eFlatMajor.put('A', -1);
        eFlatMajor.put('B', -1);
        eFlatMajor.put('C', 0);
        eFlatMajor.put('D', 0);
        eFlatMajor.put('E', -1);
        eFlatMajor.put('F', 0);
        eFlatMajor.put('G', 0);
        
        final Map<Character, Integer> aFlatMajor = new HashMap<Character, Integer>();
        aFlatMajor.put('A', -1);
        aFlatMajor.put('B', -1);
        aFlatMajor.put('C', 0);
        aFlatMajor.put('D', -1);
        aFlatMajor.put('E', -1);
        aFlatMajor.put('F', 0);
        aFlatMajor.put('G', 0);
        
        final Map<Character, Integer> dFlatMajor = new HashMap<Character, Integer>();
        dFlatMajor.put('A', -1);
        dFlatMajor.put('B', -1);
        dFlatMajor.put('C', 0);
        dFlatMajor.put('D', -1);
        dFlatMajor.put('E', -1);
        dFlatMajor.put('F', 0);
        dFlatMajor.put('G', -1);
        
        final Map<Character, Integer> gFlatMajor = new HashMap<Character, Integer>();
        gFlatMajor.put('A', -1);
        gFlatMajor.put('B', -1);
        gFlatMajor.put('C', -1);
        gFlatMajor.put('D', -1);
        gFlatMajor.put('E', -1);
        gFlatMajor.put('F', 0);
        gFlatMajor.put('G', -1);
        
        final Map<Character, Integer> cFlatMajor = new HashMap<Character, Integer>();
        cFlatMajor.put('A', -1);
        cFlatMajor.put('B', -1);
        cFlatMajor.put('C', -1);
        cFlatMajor.put('D', -1);
        cFlatMajor.put('E', -1);
        cFlatMajor.put('F', -1);
        cFlatMajor.put('G', -1);
        
        // Construct final map along with keys for relative minors as well as 
        // different representations (i.e. E flat major = A major)
        
        keyDict.put("C", cMajor);
        keyDict.put("G", gMajor);
        keyDict.put("D", dMajor);
        keyDict.put("A", aMajor);
        keyDict.put("E", eMajor);
        keyDict.put("B", bMajor);
        keyDict.put("F#", fSharpMajor);
        keyDict.put("C#", cSharpMajor);
        
        keyDict.put("Am", cMajor);
        keyDict.put("Em", gMajor);
        keyDict.put("Bm", dMajor);
        keyDict.put("F#m", aMajor);
        keyDict.put("C#m", eMajor);
        keyDict.put("G#m", bMajor);
        keyDict.put("D#m", fSharpMajor);
        keyDict.put("A#m", cSharpMajor);
        
        keyDict.put("F", fMajor);
        keyDict.put("Bb", bFlatMajor);
        keyDict.put("Eb", eFlatMajor);
        keyDict.put("Ab", aFlatMajor);
        keyDict.put("Db", dFlatMajor);
        keyDict.put("Gb", gFlatMajor);
        keyDict.put("Cb", cFlatMajor);
        
        keyDict.put("Dm", fMajor);
        keyDict.put("Gm", bFlatMajor);
        keyDict.put("Cm", eFlatMajor);
        keyDict.put("Fm", aFlatMajor);
        keyDict.put("Bbm", dFlatMajor);
        keyDict.put("Ebm,", gFlatMajor);
        keyDict.put("Abm", cFlatMajor);
        
        return keyDict;
    }
    
    /**
     * Create a note with specified pitch and duration
     * 
     * @param duration duration in beats, must be >= 0
     * @param pitch pitch to play
     * @return pitch played for duration beats
     */
    public static Music note(double duration, Pitch pitch) {
        return new Note(duration, pitch);
    }
    
    /**
     * Create a rest of a specified duration
     * 
     * @param duration duration in beats, must be >= 0
     * @return rest that lasts for duration beats
     */
    public static Music rest(double duration) {
        return new Rest(duration);
    }
    
    ////////////////////////////////////////////////////
    // Producers
    ////////////////////////////////////////////////////
    
    /**
     * Concatenate two pieces of music as a new object
     * 
     * @param m1 first piece of music
     * @param m2 second piece of music
     * @return m1 followed by m2
     */
    public static Music concat(Music m1, Music m2) {
        return new Merge(m1, m2);
    }
}
