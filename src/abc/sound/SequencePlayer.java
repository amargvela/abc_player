package abc.sound;

import java.text.MessageFormat;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * Schedules and plays a sequence of notes at given time steps (or "ticks").
 */
public class SequencePlayer {

    // MIDI channel
    private static final int DEFAULT_CHANNEL = 0;
    // the volume
    private static final int DEFAULT_VELOCITY = 100;
    // Default ticks in a beat
    private static final int DEFAULT_TICKS_PER_BEAT = 1152;

    // the "end_of_track" meta message type
    private static final int META_END_OF_TRACK = 47;

    private final Sequencer sequencer;
    private final Track track;
    private final int beatsPerMinute;
    private final int ticksPerBeat;
    
    /*
     * Rep invariant:
     *   sequencer and track are non-null,
     *   beatsPerMinute is positive
     */

    private void checkRep() {
        assert sequencer != null : "sequencer should be non-null";
        assert track != null : "track should be non-null";
        assert beatsPerMinute >= 0 : "should be positive number of beats per minute";
    }

    /**
     * Make a new MIDI sequence player.
     * 
     * @param beatsPerMinute the number of beats per minute
     * @param ticksPerBeat the number of ticks per beat; every note plays for an integer number of ticks.
     *                     if it equals -1, than ticksPerBeat is substitute by DEFAULT_TICKS_PER_BEAT.
     *                     otherwise it has to be positive integer
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     */
    public SequencePlayer(int beatsPerMinute, int ticksPerBeat)
            throws MidiUnavailableException, InvalidMidiDataException {
        this.sequencer = MidiSystem.getSequencer();
        if (ticksPerBeat == -1)
            ticksPerBeat = DEFAULT_TICKS_PER_BEAT;
        assert (ticksPerBeat > 0);
        // create a sequence object with with tempo-based timing, where
        // the resolution of the time step is based on ticks per beat
        Sequence sequence = new Sequence(Sequence.PPQ, ticksPerBeat);
        this.beatsPerMinute = beatsPerMinute;
        this.ticksPerBeat = ticksPerBeat;

        // create an empty track; notes will be added to this track
        this.track = sequence.createTrack();

        sequencer.setSequence(sequence);

        checkRep();
    }

    /**
     * Schedule a note to be played starting at startBeat for the duration of numBeats.
     *
     * @param note the pitch value for the note to be played; must be a valid note
     * @param startBeat the starting beat; must be >= 0
     * @param numBeats the number of beats for which this note should be played; must be >= 0
     */
    public void addNote(int note, double startBeat, double numBeats) {
        try {
            // schedule two events in the track, one for starting a note and
            // the other for ending the note.
            addMidiNoteEvent(ShortMessage.NOTE_ON, note, (int) (startBeat * ticksPerBeat));
            addMidiNoteEvent(ShortMessage.NOTE_OFF, note, (int) ((startBeat + numBeats) * ticksPerBeat));
        } catch (InvalidMidiDataException imde) {
            String msg = MessageFormat.format("Cannot add note with the pitch {0} at beat {1} " +
                                              "for duration {2}", note, startBeat, numBeats);
            throw new RuntimeException(msg, imde);
        }
    }

    /**
     * Schedule a MIDI note event.
     * 
     * @param eventType valid MidiMessage type in ShortMessage
     * @param note valid pitch value
     * @param tick tick >= 0
     * @throws InvalidMidiDataException
     */
    private void addMidiNoteEvent(int eventType, int note, int tick) throws InvalidMidiDataException {
        ShortMessage msg = new ShortMessage(eventType, DEFAULT_CHANNEL, note, DEFAULT_VELOCITY);
        this.track.add(new MidiEvent(msg, tick));
    }

    /**
     * Open the MIDI sequencer and play the scheduled music.
     * 
     * @throws MidiUnavailableException if the sequencer cannot be opened
     */
    public void play() throws MidiUnavailableException {
        sequencer.open();
        sequencer.setTempoInBPM(this.beatsPerMinute);

        sequencer.addMetaEventListener(new MetaEventListener() {
            public void meta(MetaMessage meta) {
                if (meta.getType() == META_END_OF_TRACK) {
                    // allow the sequencer to finish
                    try { Thread.sleep(1000); } catch (InterruptedException ie) { }
                    // stop & close the sequencer
                    sequencer.stop();
                    sequencer.close();
                }
            }
        });

        // start playing!
        sequencer.start();
    }

    /**
     * @return a string that displays the entire track information as a
     *         sequence of MIDI events, where each event is either turning on
     *         or off a note at a certain tick, or the end of
     *         the track
     */
    @Override
    public String toString() {
        String trackInfo = "";

        for (int i = 0; i < track.size(); i++) {
            final MidiEvent e = track.get(i);
            final MidiMessage msg = e.getMessage();
            final String msgString;

            if (msg instanceof ShortMessage) {
                final ShortMessage smg = (ShortMessage) msg;
                final int command = smg.getCommand();
                final String commandName;

                if (command == ShortMessage.NOTE_OFF) {
                    commandName = "NOTE_OFF";
                } else if (command == ShortMessage.NOTE_ON) {
                    commandName = "NOTE_ON ";
                } else {
                    commandName = "Unknown command " + command;
                }

                msgString = "Event: " + commandName + " Pitch: " + smg.getData1() + " ";

            } else if (msg instanceof MetaMessage) {
                final MetaMessage mmg = (MetaMessage) msg;
                final int type = mmg.getType();
                final String typeName;

                if (type == META_END_OF_TRACK) {
                    typeName = "END_OF_TRACK";
                } else {
                    typeName = "Unknown type " + type;
                }

                msgString = "Meta event: " + typeName;

            } else {
                msgString = "Unknown event";
            }

            trackInfo += msgString + " Tick: " + e.getTick() + "\n";
        }

        return trackInfo;
    }

    /**
     * Play an octave up and back down starting from middle C.
     * addNote(base, beat, duration) schedules a note with pitch value 'base'
     * starting at 'beat' to be played for 'duration' number of beats. For example,
     * addNote(new Pitch('C').toMidiNote(), 10, 0.5) plays the middle C at
     * time step 10 (10 beats from beginning) for half the duration of a beat.
     */
    public static void main(String[] args) {
        try {
            SequencePlayer player = new SequencePlayer(120, 2);

            player.addNote(new Pitch('C').toMidiNote(), 0, 1./2);
            player.addNote(new Pitch('D').toMidiNote(), 1./2, 1./2);
            player.addNote(new Pitch('E').toMidiNote(), 2./2, 1./2);
            player.addNote(new Pitch('F').toMidiNote(), 3./2, 1./2);
            player.addNote(new Pitch('G').toMidiNote(), 4./2, 1./2);
            player.addNote(new Pitch('A').toMidiNote(), 5./2, 1./2);
            player.addNote(new Pitch('B').toMidiNote(), 6./2, 1./2);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 7./2, 1./2);
            player.addNote(new Pitch('B').toMidiNote(), 8./2, 1./2);
            player.addNote(new Pitch('A').toMidiNote(), 9./2, 1./2);
            player.addNote(new Pitch('G').toMidiNote(), 10./2, 1./2);
            player.addNote(new Pitch('F').toMidiNote(), 11./2, 1./2);
            player.addNote(new Pitch('E').toMidiNote(), 12./2, 1./2);
            player.addNote(new Pitch('D').toMidiNote(), 13./2, 1./2);
            player.addNote(new Pitch('C').toMidiNote(), 14./2, 1./2);

            System.out.println(player);

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

        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        }
    }

}
