package abc.music;

import abc.sound.SequencePlayer;

/**
 * Merge represents two pieces of music played one after the other.
 */
public class Merge implements Music {
    
    private final Music first;
    private final Music second;
    
    // Abstraction function:
    //   first represents a starting music. second
    //   represents the final music that should be
    //   started immediately after first finishes.
    // Rep invariant:
    //   neither first nor second may be null
    // Rep exposure:
    //   all the fields are private, final and immutable;
    //   no mutable data is returned;
    //   There is not producer using passed mutable data.
    
    /**
     * Checks the rep invariant
     */
    private void checkRep() {
        assert first != null;
        assert second != null;
    }
    
    /**
     * Construct a Music sequence that plays m1 followed by m2.
     * @param m1 music to play first
     * @param m2 music to play second
     */
    public Merge(Music m1, Music m2) {
        this.first = m1;
        this.second = m2;
        checkRep();
    }
    
    /**
     * @return first piece in this merge
     */
    public Music first() {
        return first;
    }
    
    /**
     * @return second piece in this merge
     */
    public Music second() {
        return second;
    }
    
    /**
     * @return duration of this merge
     */
    public double duration() {
        return first.duration() + second.duration();
    }
    
    /**
     * Transpose the pieces in this merge.
     */
    public Music transpose(int semitonesUp) {
        return new Merge(first.transpose(semitonesUp), second.transpose(semitonesUp));
    }
    
    /**
     * Play this merge.
     */
    public void play(SequencePlayer player, double atBeat) {
        first.play(player, atBeat);
        second.play(player, atBeat + first.duration());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return first.hashCode() + prime * second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Merge other = (Merge) obj;
        return first.equals(other.first) && second.equals(other.second);
    }

    @Override
    public String toString() {
        return first + " " + second;
    }
}
