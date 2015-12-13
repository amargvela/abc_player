package abc;

import static org.junit.Assert.*;

/**
 * Test Cases for Repeat Handler
 * 
 * Partitions:
 *  - No repeat
 *  - Simple repeat
 *      - Repeat from major section, repeat from beginning of piece
 *      - Single repeat, multiple repeats
 *  - Repeat with alternate ending
 *      - Repeat from major section, repeat from beginning of piece
 *      - Single repeat, multiple repeats 
 *  - Mix of simple repeats and alternate ending repeats
 * 
 * Test Cases:
 *  - noRepeatTest():
 *  - simpleRepeatMajorSectionTest()
 *  - simpleRepeatBeginningOfPieceTest()
 *  - multipleSimpleRepeatsTest()
 *  - alternateEndingRepeatMajorSectionTest()
 *  - alternateEndingRepeatBegnningOfPieceTest()
 *  - multipleAlternateEndingRepeatsTest()
 *  - repeatTypeMixTest()
 */

import org.junit.Test;

import abc.music.RepeatHandler;

public class RepeatHandlerTest {
    
    //Partitions for handleRepeats:
    //
    //  Number of Repeats: 0 repeats, 1 repeat, 2 or more Repeats
    //  Beginning repeat bar present: yes, no
    //  Repeat Go back to: last major bar (|]), beginning of music
    //  Location of repeat: beginning of music, middle of music, end of music
    //  Location of repeats relative to one another: adjacent to one another, not adjacent to one another
    //  Number of Elements to repeat: 0 elements, 1 element, 2 or more elements
    //  Type of elements to repeat: chords, tuplets, notes, rests, combination
    //  Alternate endings present: yes, no
    //  Length of Alternate ending: length 0, length 1, length 2 or more
    //  Types of bars present: || present, | present, |] present


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // noRepeatTest()
    @Test public void noRepeatTest(){
        String input = "A B C D | A B C D";
        String output = RepeatHandler.handleRepeats(input);
        assertEquals(input.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    
    // simpleRepeatMajorSectionTest()
    @Test public void simpleRepeatMajorSectionTest(){
        String input = "D a c f || A B C d || C D E F | G A B c :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "D a c f || A B C d || C D E F | G A B c | C D E F | G A B c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    
    // simpleRepeatBeginningOfPieceTest()
    @Test public void simpleRepeatBeginningOfPieceTest(){
        String input = "C D E F | G A B c :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "C D E F | G A B c | C D E F | G A B c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // multipleSimpleRepeatsTest()
    @Test public void multipleSimpleRepeatsTest(){
        String input = "C D E F |: G A B c :| a b c d | a b d e | a e d f |: F E D b :| a b d c";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "C D E F | G A B c | G A B c | a b c d | a b d e | a e d f | F E D b | F E D b | a b d c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // alternateEndingRepeatMajorSectionTest()
    @Test public void alternateEndingRepeatMajorSectionTest(){
        String input1 = "|: C D E F |[1 G A B c | G A B B :|[2 F E D C |";
        String output1 = RepeatHandler.handleRepeats(input1);
        String desiredOutput1 = "C D E F | G A B c | G A B B | C D E F | F E D C";
        assertEquals(desiredOutput1.replaceAll("\\s+",""), output1.replaceAll("\\s+",""));
        
        String input2 = "A B C D || C D E F |[1 G A B c | G A B B :|[2 F E D C |";
        String output2 = RepeatHandler.handleRepeats(input2);
        String desiredOutput2 = "A B C D || C D E F | G A B c | G A B B | C D E F | F E D C";
        assertEquals(desiredOutput2.replaceAll("\\s+",""), output2.replaceAll("\\s+",""));
    }
    
    // alternateEndingRepeatBegnningOfPieceTest()
    @Test public void alternateEndingRepeatBeginningOfPieceTest(){
        String input = "C D E F |[1 G A B c | G A B B :|[2 F E D C |";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "C D E F | G A B c | G A B B | C D E F | F E D C";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // multipleAlternateEndingRepeatsTest()
    @Test public void multipleAlternateEndingsRepeatsTest(){
        String input = "C D E F |[1 G A B c | G A B B :|[2 F E D C |: C D E F |[1 G A B c | G A B B :|[2 F E D C |";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "C D E F | G A B c | G A B B | C D E F | F E D C | C D E F | G A B c | G A B B | C D E F | F E D C";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // repeatTypeMixTest()
    @Test public void repeatTypeMixTest(){
        String input = "|: A B C D :| D A C D |: C D E F |[1 G A B c | G A B B :|[2 F E D C |";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "A B C D | A B C D | D A C D | C D E F | G A B c | G A B B | C D E F | F E D C";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // repeatNothingTest
    @Test public void repeatNothingTest(){
        String input = "|:           :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "|";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    // repeatOneElementTest()
    @Test public void repeatOneElementTest(){
        String input = "c :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "c | c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // backToBackRepeatTest()
    @Test public void backToBackRepeatTest(){
        String input = "|:D a c f | A B C d :||:C D E F | G A B c :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "D a c f | A B C d | D a c f | A B C d | C D E F | G A B c | C D E F | G A B c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // repeatBackToBackAlternateTest()
    @Test public void repeatBackToBackAlternateTest(){
        String input = "D a c f [1  r :|[2 A B C d ||: C D E F | G A B c :|";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "D a c f | r | D a c f | A B C d || C D E F | G A B c | C D E F | G A B c";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // lengthOfAlternateEndingTest()
    @Test public void lengthOfAlternateEndingsTest(){
        String input = "D |: a c f [1 :| [2 g A E |]";
        String output = RepeatHandler.handleRepeats(input);
        String desiredOutput = "D | a c f | | a c f | g A E |]";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //Partitions for mergeVoices:
    //
    //  Number of voices present: 0 voices, 1 or more voice
    //  Repeats present: yes, no
    //  Voices are: are in one spot, split into multiple lines
    //  Repeats split across lines
    //  Name of voices is: length 0, length 1, length more than 1
    
    // noVoicesTest()
    @Test public void noVoicesTest(){
        String input = "D |: a c f [1 :| [2 g A E |]";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "D | a c f | | a c f | g A E |]";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
 // oneVoiceTest()
    @Test public void onceVoiceTest(){
        String input = "V:1\n D | a c f | g A E ";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: 1\nD | a c f | g A E";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
 // twoVoicesTest()
    @Test public void twoVoicesTest(){
        String input = "V:upper\nD | a c f \nV:lower\ng A E ";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: upper\nD | a c f\nV: lower\ng A E";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
 // lengthOfVoiceNameTest()
    @Test public void lengthOfVoiceNameTest(){
        String input = "V: \nD | a c      f \nV:lowering\n g A E ";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: lowering\ng A E\nV:  \nD | a c f";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // splitVoiceTest()
    @Test public void splitVoiceTest(){
        String input = "V: \nD | a c      f \nV:lowering\n g A E\n"+"V: \nD | a c      f \nV:lowering\n g A E ";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: lowering\ng A E\ng A E\nV:  \nD | a c f\nD | a c f";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }         
    
    // RepeatMultipleLinesTest()
    @Test public void repeatMultipleLinesTest(){
        String input = "V: \n|:D | a c      f \nV:lowering\n g A E\n"+"V: \nD | a c      f :|\nV:lowering\n g A E ";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: lowering\ng A E\ng A E\nV:  \nD | a c f\nD | a c f | D | a c f\nD | a c f";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
    
    // moreVoicesTest()
    @Test public void moreVoicesTest(){
        String input = "V: \n| D | a c      f \nV:lowering\n g A E\n"+"V: \nD | a c      f |\nV:lowering\n g A E\n"+"V:upper\nD | a c      f |\n";
        String output = RepeatHandler.mergeVoices(input);
        String desiredOutput = "V: lowering\ng A E g A E\nV:  \n| D | a c f\nD | a c f |\nV: upper\nD | a c f |";
        assertEquals(desiredOutput.replaceAll("\\s+",""), output.replaceAll("\\s+",""));
    }
}
