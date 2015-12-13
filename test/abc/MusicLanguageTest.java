package abc;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import abc.music.Merge;
import abc.music.Music;
import abc.music.MusicLanguage;
import abc.music.Parallel;
import abc.music.Rest;
import abc.sound.SequencePlayer;

/**
 * Test Cases for MusicLanguage Class 
 * 
 * Covers the following partitions:
 * - Parsing strings of notes:
 *      - Correct pitches (A,B,C,D,E,F,G)
 *      - Appropriate durations
 *          -/n for 1/n-beat duration
 *          -n for n-beat duration
 *          -n/m for n/m-beat duration
 *      - Accidentals (sharp ^, flat _, double sharp ^^)
 *      - Octave modifiers (upward ' and downward ,)
 * - Parsing rests (.) with correct durations
 * - Key changes (major keys, minor keys, flat keys, sharp keys, key of C [base case])
 */

public class MusicLanguageTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Initialize instance of MusicLanguage for instance access to the key change dictionary
    MusicLanguage ml = new MusicLanguage();

    // Covers reading notes, reading rests, sharps, varying durations
    @Test public void pachelbelTest(){
        Music pachelbelMelody =
                ml.notes("^F'2 E'2 | D'2 ^C'2 | B2 A2 | B2 ^C'2 |"
                    + "D'2 ^C'2 | B2 A2 | G2 ^F2 | G2 E2 |"
                    + "D ^F A G | ^F D ^F E | D B, D A | G B A G |"
                    + "^F D E ^C' | D' ^F' A' A | B G A ^F | D D' D3/2 .1/2 |",
                      "C");
        Music pachelbelBass = ml.notes("D,2 A,,2 | B,,2 ^F,,2 | G,,2 D,,2 | G,,2 A,,2", "C");
        Music baseLine = new Merge(new Merge(pachelbelBass, pachelbelBass), new Merge(pachelbelBass, pachelbelBass));
        Music entirePiece = new Parallel(pachelbelMelody, baseLine);
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            entirePiece.play(player, 0.125);
            player.play();
            Thread.sleep(30000);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    // Covers key signature changes: 
    //      - base case (key of C)
    //      - major key
    //      - minor key
    //      - flat key
    //      - sharp key 
    // Covers all duration parsings: 
    //      /n --> for 1/n-beat duration
    //      n --> for n-beat duration
    //      n/m --> for n/m-beat duration
    @Test public void rowRowRowYourBoatKeyChangeTest(){
        Music rowYourBoatCMajor =
              ml.notes("C C C3/4 D/4 E |"
                  + "E3/4 D/4 E3/4 F/4 G2 |"
                  + "C'/3 C'/3 C'/3 G/3 G/3 G/3 E/3 E/3 E/3 C/3 C/3 C/3 |"
                  + "G3/4 F/4 E3/4 D/4 C2",
                  "C");
        Music rowYourBoatGMajor =
                ml.notes("C C C3/4 D/4 E |"
                    + "E3/4 D/4 E3/4 _F/4 G2 |"
                    + "C'/3 C'/3 C'/3 G/3 G/3 G/3 E/3 E/3 E/3 C/3 C/3 C/3 |"
                    + "G3/4 _F/4 E3/4 D/4 C2",
                    "G");
        Music rowYourBoatAFlatMinor =
                ml.notes("^C ^C ^C3/4 ^D/4 ^E |"
                    + "^E3/4 ^D/4 ^E3/4 ^F/4 ^G2 |"
                    + "^C'/3 ^C'/3 ^C'/3 ^G/3 ^G/3 ^G/3 ^E/3 ^E/3 ^E/3 ^C/3 ^C/3 ^C/3 |"
                    + "^G3/4 ^F/4 ^E3/4 ^D/4 ^C2",
                    "Abm");
        Music rowYourBoatCSharpMinor = ml.notes("_C _C _C3/4 _D/4 E |"
                + "E3/4 _D/4 E3/4 _F/4 _G2 |"
                + "_C'/3 _C'/3 _C'/3 _G/3 _G/3 _G/3 E/3 E/3 E/3 _C/3 _C/3 _C/3 |"
                + "_G3/4 _F/4 E3/4 _D/4 _C2",
                "C#m");
        
        Music rowYourBoatFirst = new Parallel(rowYourBoatCMajor, rowYourBoatGMajor.transpose(-12));
        Music rowYourBoatSecond = new Merge(new Rest(4), new Parallel(rowYourBoatAFlatMinor, rowYourBoatCSharpMinor.transpose(-12)));
        Music rowYourBoatPiece = new Parallel(rowYourBoatFirst, rowYourBoatSecond);
                
        try {
            SequencePlayer player = new SequencePlayer(140, 12);
            rowYourBoatPiece.play(player, 0.125);
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

  // Covers octave modifiers and all accidentals.
  // Also covers rest parsing. 
  @Test public void accidentalsAndNaturalsTest(){
      Music accidentalsTest =
              ml.notes("^C C _C . |"
                  + "C'' C' C'' C' |"
                  + "^^C, ^C, C, _C, |",
                  "C");
      String sequencerResult = "Event: NOTE_ON  Pitch: 61  Tick: 0\n" 
                              + "Event: NOTE_OFF Pitch: 61  Tick: 12\n"
                              + "Event: NOTE_ON  Pitch: 60  Tick: 12\n"
                              + "Event: NOTE_OFF Pitch: 60  Tick: 24\n"
                              + "Event: NOTE_ON  Pitch: 59  Tick: 24\n"
                              + "Event: NOTE_OFF Pitch: 59  Tick: 36\n"
                              + "Event: NOTE_ON  Pitch: 84  Tick: 48\n"
                              + "Event: NOTE_OFF Pitch: 84  Tick: 60\n"
                              + "Event: NOTE_ON  Pitch: 72  Tick: 60\n"
                              + "Event: NOTE_OFF Pitch: 72  Tick: 72\n"
                              + "Event: NOTE_ON  Pitch: 84  Tick: 72\n"
                              + "Event: NOTE_OFF Pitch: 84  Tick: 84\n"
                              + "Event: NOTE_ON  Pitch: 72  Tick: 84\n"
                              + "Event: NOTE_OFF Pitch: 72  Tick: 96\n"
                              + "Event: NOTE_ON  Pitch: 50  Tick: 96\n"
                              + "Event: NOTE_OFF Pitch: 50  Tick: 108\n"
                              + "Event: NOTE_ON  Pitch: 49  Tick: 108\n"
                              + "Event: NOTE_OFF Pitch: 49  Tick: 120\n"
                              + "Event: NOTE_ON  Pitch: 48  Tick: 120\n"
                              + "Event: NOTE_OFF Pitch: 48  Tick: 132\n"
                              + "Event: NOTE_ON  Pitch: 47  Tick: 132\n"
                              + "Event: NOTE_OFF Pitch: 47  Tick: 144\n"
                              + "Meta event: END_OF_TRACK Tick: 144\n";      
      try {
          SequencePlayer player = new SequencePlayer(140, 12);
          accidentalsTest.play(player, 0);
          assertEquals(sequencerResult, player.toString());
      } catch (MidiUnavailableException mue) {
          mue.printStackTrace();
      } catch (InvalidMidiDataException imde) {
          imde.printStackTrace();
      } 
    }
  
    
}
