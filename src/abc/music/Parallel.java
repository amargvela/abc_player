package abc.music;

import abc.sound.SequencePlayer;

/**
 * Parallel represents two pieces of music playing at the same time.
 * The pieces start at the same instant, but may end at different times
 * due to the different durations of the pieces.
 */
public class Parallel implements Music {
    
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
     * Construct a Parallel of two pieces of music.
     * @param m1 one piece of music
     * @param m2 another piece of music
     */
    public Parallel(Music m1, Music m2) {
        this.m1 = m1;
        this.m2 = m2;
        checkRep();
    }
    
    /**
     * @return one of the pieces of music in this parallel
     */
    public Music top() {
        return m1;
    }
    
    /**
     * @return the other piece of music in this parallel
     */
    public Music bottom() {
        return m2;
    }
    
    /**
     * @return duration of this piece of music, the minimum time for both
     *         pieces to play
     */
    public double duration() {
        return Math.max(m1.duration(), m2.duration());
    }
    
    /**
     * Transpose the pieces in this Parallel.
     */
    public Music transpose(int semitonesUp) {
        return new Parallel(m1.transpose(semitonesUp), m2.transpose(semitonesUp));
    }
    
    /**
     * Play the pieces of this Parallel, in parallel.
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
        
        final Parallel other = (Parallel) obj;
        return m1.equals(other.m1) && m2.equals(other.m2);
    }

    @Override
    public String toString() {
        return "parallel(" + m1 + " |||| " + m2 + ")";
    }
}
