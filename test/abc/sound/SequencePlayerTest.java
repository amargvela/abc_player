package abc.sound;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import org.junit.Test;

/**
 * Test some music playing of samples pieces 1 and 2 from the ABC Music Player Warm-up.
 * (first is "Row, row, row your boat" and second piece is excerpt from Mario theme song
 * @category no_didit
 */
public class SequencePlayerTest {

    //tests first music piece from warm-up -- "Row,row,row your boat"
    @Test
    public void abc1NoteTest(){
        try {
            SequencePlayer player = new SequencePlayer(140, 12);

            player.addNote(new Pitch('C').toMidiNote(), 0./12, 12./12);
            player.addNote(new Pitch('C').toMidiNote(), 12./12, 12./12);
            player.addNote(new Pitch('C').toMidiNote(), 24./12, 9./12);
            player.addNote(new Pitch('D').toMidiNote(), 33./12, 3./12);
            player.addNote(new Pitch('E').toMidiNote(), 36./12, 12./12);
            player.addNote(new Pitch('E').toMidiNote(), 48./12, 9./12);
            player.addNote(new Pitch('D').toMidiNote(), 57./12, 3./12);
            player.addNote(new Pitch('E').toMidiNote(), 60./12, 9./12);
            player.addNote(new Pitch('F').toMidiNote(), 69./12, 3./12);
            player.addNote(new Pitch('G').toMidiNote(), 72./12, 24./12);
            
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96./12, 4./12);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100./12, 4./12);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104./12, 4./12);
            
            player.addNote(new Pitch('G').toMidiNote(), 108./12, 4./12);
            player.addNote(new Pitch('G').toMidiNote(), 112./12, 4./12);
            player.addNote(new Pitch('G').toMidiNote(), 116./12, 4./12);
            
            player.addNote(new Pitch('E').toMidiNote(), 120./12, 4./12);
            player.addNote(new Pitch('E').toMidiNote(), 124./12, 4./12);
            player.addNote(new Pitch('E').toMidiNote(), 128./12, 4./12);
           
            player.addNote(new Pitch('C').toMidiNote(), 132./12, 4./12);
            player.addNote(new Pitch('C').toMidiNote(), 136./12, 4./12);
            player.addNote(new Pitch('C').toMidiNote(), 140./12, 4./12);
            
            player.addNote(new Pitch('G').toMidiNote(), 144./12, 9./12);
            player.addNote(new Pitch('F').toMidiNote(), 153./12, 3./12);
            player.addNote(new Pitch('E').toMidiNote(), 156./12, 9./12);
            player.addNote(new Pitch('D').toMidiNote(), 165./12, 3./12);
            player.addNote(new Pitch('C').toMidiNote(), 168./12, 24./12);

            System.out.println("Piece 1:");
            System.out.println(player.toString());

            assert(true);
            // play!
            player.play();

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);
            
            Thread.sleep(8000);

        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } 
    }
    
    //tests second music piece from warm-up -- excerpt from Mario theme song
    @Test
    public void abc2NoteTest(){
        
        //in this test:
        //commented out lines represent rests - in the actual ADT, rests will be represented by Music objects
        
        try {
            SequencePlayer player = new SequencePlayer(200, -1);
            
            // Upper voice 
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0./2, 1./2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 1./2, 1./2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 3./2, 1./2);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 5./2, 1./2);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 6./2, 2./2);
            
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 8./2, 2./2);
                   
            player.addNote(new Pitch('B').toMidiNote(), 48./12, 12./12);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96./12, 18./12);
            player.addNote(new Pitch('G').toMidiNote(), 114./12, 6./12);
            player.addNote(new Pitch('E').toMidiNote(), 132./12, 12./12);
            
            // Middle voice
            player.addNote(new Pitch('E').toMidiNote(), 144./12, 6./12);
            player.addNote(new Pitch('A').toMidiNote(), 150./12, 12./12);
            player.addNote(new Pitch('B').toMidiNote(), 152./12, 12./12);
            player.addNote(new Pitch('B').transpose(-1).toMidiNote(), 164./12, 6./12);
            player.addNote(new Pitch('A').toMidiNote(), 170./12, 12./12);
            player.addNote(new Pitch('G').toMidiNote(), 182./12, 8./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 190./12, 8./12);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 198./12, 8./12);
            
            player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 206./12, 12./12);
            player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 218./12, 6./12);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 224./12, 6./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 236./12, 12./12);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 248./12, 6./12);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 254./12, 6./12);
            player.addNote(new Pitch('B').toMidiNote(), 260./12, 9./12);

            // Lower voice
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 0./2, 1./2);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 1./2, 1./2);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 3./2, 1./2);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 5./2, 1./2);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 6./2, 2./2);
            
            player.addNote(new Pitch('G').toMidiNote(), 8./2, 2./2);
            player.addNote(new Pitch('G').toMidiNote(), 12./2, 2./2);

            System.out.println("Piece 2:");
            System.out.println(player.toString());

            assert(true);
            
            // play!
            player.play();

            /*
              Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).*/
            // System.exit(0);
            
            //System.in.read();
            Thread.sleep(8000);
            
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
    
    //tests high pitch turn from furElise -- used this Midi test to make sure our code was right
    @Test
    public void furNoteTest(){
        try {
            SequencePlayer player = new SequencePlayer(140, 12);

            player.addNote(new Pitch('B').toMidiNote(), 0./12, 12./12);
            player.addNote(new Pitch('E').toMidiNote(), 18./12, 24./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 24./12, 30./12);          
            
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 42./12, 48./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).transpose(Pitch.OCTAVE).toMidiNote(), 48./12, 54./12);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).transpose(1).toMidiNote(), 66./12, 72./12);
            
            
            player.addNote(new Pitch('E').transpose(-1).transpose(-1).toMidiNote(), 0./12, 6./12);
            player.addNote(new Pitch('E').transpose(-1).toMidiNote(), 6./12, 12./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 12./12, 18./12);   
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 30./12, 36./12); 
            
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 36./12, 42./12);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).transpose(1).toMidiNote(), 54./12, 60./12);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 60./12, 66./12);

            System.out.println("Piece 1:");
            System.out.println(player.toString());

            assert(true);
            // play!
            player.play();

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);
            
            Thread.sleep(8000);

        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } 
    }

}
