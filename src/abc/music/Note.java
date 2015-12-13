package abc.music;

import abc.sound.Pitch;
import abc.sound.SequencePlayer;

/**
 * Note represents a note played in the piece of music.
 */
public class Note implements Music {
    
    private final double duration;
    private final Pitch pitch;
    
    // Abstraction function:
    //   duration represents the length of the note to be played
    //   which is represented by number of beats.
    //   pitch represents note's pitch (frequency)
    // Rep invariant:
    //   duration must be nonnegative;
    //   pitch may not be null
    // Rep exposure:
    //   all the fields are private, final and immutable;
    //   no mutable data is returned;
    
    /**
     * Checks rep invariants
     */
    private void checkRep() {
        assert duration >= 0;
        assert pitch != null;
    }
    
    /**
     * Construct a Note played for duration beats.
     * @param duration duration in beats, must be >= 0
     * @param pitch pitch to play
     */
    public Note(double duration, Pitch pitch) {
        this.duration = duration;
        this.pitch = pitch;
        checkRep();
    }
    
    /**
     * @return pitch of this note
     */
    public Pitch pitch() {
        return pitch;
    }
    
    /**
     * @return duration of this note
     */
    public double duration() {
        return duration;
    }
    
    /**
     * Transpose this note.
     */
    public Note transpose(int semitonesUp) {
        return new Note(duration, pitch.transpose(semitonesUp));
    }
    
    /**
     * Play this note.
     */
    public void play(SequencePlayer player, double atBeat) {
        player.addNote(pitch.toMidiNote(), atBeat, duration);
    }

    @Override
    public int hashCode() {
        long durationBits = Double.doubleToLongBits(duration);
        return (int) (durationBits ^ (durationBits >>> 32))
                + pitch.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Note other = (Note) obj;
        return duration == other.duration
                && pitch.equals(other.pitch);
    }

    @Override
    public String toString() {
        return pitch.toString() + duration;
    }
}
