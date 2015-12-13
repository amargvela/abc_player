package abc.music;

import abc.sound.SequencePlayer;

/**
 * Chord represents two pieces of music playing at the same time but
 * duration of the music is said to be the duration of the FIRST piece 
 * of music.
 * The pieces start at the same instant, but may end at different times
 * due to the different durations of the pieces.
 */
public class Chord implements Music {
    
    private Music m1;
    private Music m2;
    
    // Abstraction function:
    //   m1 and m2 represent 2 music that are played
    //   together (in parallel).
    // Rep invariant:
    //   neither m1 nor m2 may be null
    // Rep exposure:
    //   all the fields are private, final and immutable;
    //   no mutable data is returned;
    //   There is no producer that uses passed mutable data.

    /**
     * Checks rep invariant
     */
    private void checkRep() {
        assert m1 != null;
        assert m2 != null;
    }
    
    /**
     * Construct a Chord of two pieces of music.
     * @param m1 one piece of music
     * @param m2 another piece of music
     */
    public Chord(Music m1, Music m2) {
        this.m1 = m1;
        this.m2 = m2;
        checkRep();
    }
    
    /**
     * @return one of the pieces of music in this chord
     */
    public Music top() {
        return m1;
    }
    
    /**
     * @return the other piece of music in this chord
     */
    public Music bottom() {
        return m2;
    }
    
    /**
     * @return duration of this piece of music, the duration of the first note in the 
     * chord
     */
    public double duration() {
        return m1.duration();
    }
    
    /**
     * Transpose the pieces in this chord.
     */
    public Chord transpose(int semitonesUp) {
        return new Chord(m1.transpose(semitonesUp), m2.transpose(semitonesUp));
    }
    
    /**
     * Play the pieces of this Parallel, in chord.
     */
    public void play(SequencePlayer player, double atBeat) {
        m1.play(player, atBeat);
        m2.play(player, atBeat);
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        return m1.hashCode() + prime * m2.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Chord other = (Chord) obj;
        return m1.equals(other.top()) && m2.equals(other.bottom());
    }

    @Override
    public String toString() {
        return "chord(" + m1 + " |||| " + m2 + ")";
    }
}
