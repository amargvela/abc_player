package abc.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Represents the header and all of the information in the header of a musical piece.
 */
public class HeaderInfo {
    
    //Abstraction Function:
    //  Represents the header information of a given musical piece. Has fields C, K, L, M, Q, and T which
    //  are defined here:
    //  
    //  C: represents the composer of the musical piece
    //  K: represents the key signature of the musical piece
    //  L: represents the default length of a note of the musical piece
    //  M: represents the meter of the musical piece
    //  Q: represents the tempo (number of beats of the given length to play per minute) of the musical piece
    //  T: represents the title of the musical piece of the musical piece
    //
    //Rep Invariant:
    //  C, K, L, M, Q, T are all non-null
    //  K must be one of: 
    //      -Major: C, G, D, A, E, B, F#, Cb, Gb, Db, Ab, Eb, Bb, F
    //      -Minor: Am, Em, Bm, F#m, C#m, G#m, D#m, Ebm, Bbm, Fm, Cm, Gm, Dm
    //  L must be less than or equal to 1/1
    //  Q is greater than to zero
    //  M must be less than or equal to 1/1
    //
    //Rep Exposure:
    //  All fields are immutable so passing a pointer to the client to these immutable objects is okay.
    //  CheckRep() is used to make sure the constructor follows the rep invariant
    //
    
    private String C;
    private String K;
    private String L;
    private String M;
    private String Q;
    private String T;
    private List<String> validKeys = new ArrayList<String>(Arrays.asList(
            "C", "G", "D", "A", "E", "B", "F#", "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F",
            "Am", "Em", "Bm", "F#m", "C#m", "G#m", "D#m", "Ebm", "Bbm", "Fm", "Cm", "Gm", "Dm"
            ));
    
    /**
     * Construct HeaderInfo if nothing is passed.
     * Will initialize C,K,L,M,Q,T values
     */
    public HeaderInfo() {
        this.C = "Unknown";
        this.K = "C";
        this.T = "No Title Available";
        this.M = "4/4";
        
        String[] tempoSplit = M.split("/");
        
        if (Double.valueOf(tempoSplit[0])/Double.valueOf(tempoSplit[1]) >= 0.75) {
            this.L = "1/8";
        }
        else {
            this.L = "1/16";
        }
        
        this.Q = L+"="+"100"; 
        checkRep();
    }
    
    /**
     * Constructs HeaderInfo based on another one
     * @param header HeaderInfo to be copied
     */
    public HeaderInfo(HeaderInfo header) {
        this.C = header.getC();
        this.K = header.getK();
        this.L = header.getL();
        this.M = header.getM();
        this.Q = header.getQ();
        this.T = header.getT();
        checkRep();
    }
    
    /**
     * Checks the rep invariants
     */
    private void checkRep() {
        assert (C != null && K != null && L!= null && M != null && Q != null && T != null);
        assert validKeys.contains(K);
       
        String[] LSplit = L.split("/");
        assert (Double.valueOf(LSplit[0])/Double.valueOf(LSplit[1]) <= 1);
        
        int QValue = Integer.valueOf(Q.substring(Q.indexOf('=') + 1));
        assert (QValue > 0);
        
        String[] MSplit = M.split("/");
        assert (Double.valueOf(MSplit[0])/Double.valueOf(MSplit[1]) <= 1.0);
    }
    
    /**
     * @return C, as defined in the abstraction function above
     */
    public String getC() {
        return C;
    }
    
    /**
     * Sets the passed value to the C. Also modifies 
     * all the appearances of '@' with ' '.
     * @param newC new C value
     * @return true iff C value has been changed
     */
    public boolean setC(String newC) {
        Boolean isEqual = this.C.equals(newC);
        this.C = newC.replace('@', ' ');
        checkRep();
        return isEqual;
    }
    
    /**
     * @return K, as defined in the abstraction function above
     */
    public String getK() {
        return K;
    }
    
    /**
     * Sets the passed value to the K
     * @param newK new K value
     * @return true iff K value has been changed
     */
    public boolean setK(String newK) {
        Boolean isEqual = this.K.equals(newK);
        this.K = newK;
        checkRep();
        return isEqual;
    }
    
    /**
     * @return L, as defined in the abstraction function above
     */
    public String getL() {
        return L;
    }
    
    /**
     * Sets the passed value to the L
     * @param newL new L value
     * @return true iff L value has been changed
     */
    public boolean setL(String newL) {
        Boolean isEqual = this.L.equals(newL);
        this.L = newL;
        checkRep();
        return isEqual;
    }
    
    /**
     * @return M, as defined in the abstraction function above
     */
    public String getM() {
        return M;
    }
    
    /**
     * Sets the passed value to the M
     * @param newM new M value
     * @return true iff M value has been changed
     */
    public boolean setM(String newM) {
        Boolean isEqual = this.M.equals(newM);
        this.M = newM;
        if (M.equals("C")) {
            M = "4/4";
        }
        else if (M.equals("C|")) {
            M = "2/2";
        }
        checkRep();
        return isEqual;
    }
    
    /**
     * @return Q, as defined in the abstraction function above
     */
    
    /**
     * Returns note length based on Q
     * @param Q string containing '=' sign followed by integer
     * @return note length that is before the '=' inside Q
     */
    public String getQNoteLength() {
        return Q.split("=")[0];
    }
    
    /**
     * Returns number of beats per minute based on Q
     * @param Q string containing '=' sign followed by integer
     * @return integer that is followed to '=' inside Q
     */
    public int getQBeats() {
        return Integer.parseInt(Q.split("=")[1]);
    }
    
    
    
    public String getQ() {
        return Q; 
    }
    
    /**
     * Sets the passed value to the Q
     * @param newQ new Q value
     * @return true iff Q value has been changed
     */
    public boolean setQ(String newQ) {
        Boolean isEqual = this.Q.equals(newQ);
        this.Q = newQ;
        checkRep();
        return isEqual;
    }
    
    /**
     * @return T, as defined in the abstraction function above
     */
    public String getT() {
        return T;
    }
    
    /**
     * Sets the passed value to the T
     * @param newT new T value
     * @return true iff T value has been changed
     */
    public boolean setT(String newT) {
        Boolean isEqual = this.T.equals(newT);
        this.T = newT.replace('@', ' ');
        checkRep();
        return isEqual;
    }
}
