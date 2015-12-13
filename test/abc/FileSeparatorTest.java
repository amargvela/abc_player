package abc;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import abc.player.FileSeparator;

/**
 * Testing class for the FileSeparator.
 */
public class FileSeparatorTest {
    
    /**
     * Testing strategy for FileSeparator class:
     *   Header:
     *     header containing optional fields:
     *       yes;
     *       no;
     *     spaces appearing:
     *       in the beginning of the line:
     *         one space;
     *         multiple consecutive spaces;
     *       immediately after the colon;
     *         one space;
     *         multiple consecutive spaces;
     *       inside the T expression (title):
     *         cluster of spaces:
     *           one;
     *           more than one;
     *         number of spaces in a cluster:
     *           one;
     *           more than one;
     *       inside the C expression (composer):
     *         cluster of spaces:
     *           one;
     *           more than one;
     *         number of spaces in a cluster:
     *           one;
     *           more than one;
     *       at the end of the line:
     *         one space;
     *         multiple consecutive spaces;
     *   Music body:
     *     number of lines:
     *       zero;
     *       one;
     *       more than one;
     *     line with number of characters:
     *       one; 
     *       more than one;
     */

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testNoOptionalInHeader() {
        String fileName = "test/test_abc/separator1.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X: 123\nT:Ding@dong@song\nK: Am\n", header);
        assertEquals("", body);       
    }
    
    @Test
    public void testOptionalInHeader() {
        String fileName = "test/test_abc/separator2.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X: -1\nT:12@angry@song\nM: 4/4\nL:1/4\nK: Am\n", header);
        assertTrue(body.equals("| D D A A|B B A2|G G F F|E/2E/2E/2E/2 D2|\n"));       
    }
    
    @Test
    public void testMuchSpacingInHeader() {
        String fileName = "test/test_abc/separator3.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X:   -1\nT:12@@@angry@@song\nM:   4/4\nL:1/4\nK:   Am\n", header);
        assertTrue(body.equals("| D D A A|B B A2|G G F F|E/2E/2E/2E/2 D2|]\n"));       
    }
    
    @Test
    public void testFewSpacingInHeader() {
        String fileName = "test/test_abc/separator4.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X:1 2\nT:angryBird\nC:Nino@Joxadze\nM:4/4\nL:1/4\nK:Am\n", header);
        assertTrue(body.equals("| D D A A|B B A2|G G F F|E/2E/2E/2E/2 D2|]\n"));       
    }
    
    @Test
    public void testNoCharLine() {
        String fileName = "test/test_abc/separator5.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X:1\nT:angryBird\nK:Am\n", header);
        assertTrue(body.equals("\n"));       
    }
    
    @Test
    public void testSingleCharLine() {
        String fileName = "test/test_abc/separator6.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X:1\nT:angryBird\nK:Am\n", header);
        assertTrue(body.equals("|\n"));       
    }
    
    @Test
    public void testMultipleLineBody() {
        String fileName = "test/test_abc/separator7.abc";
        FileSeparator separator = new FileSeparator(new File(fileName));
        String header = separator.getHeader();
        String body = separator.getMusic();
        assertEquals("X:0\nT:Prelude@No.@1\nC:J.@S.@Bach\nM:4/4\nL:1/16\nQ:1/4=100\nV: upper\nV: middle\nV: lower\nK:C\n", header);
        assertEquals("V: upper\nz2 Gc eGce z2 Gc eGce | z2 Ad fAdf z2 Ad fAdf |\n" + 
                "V: middle\nz E7 z E7 | z D7 z D7 |\nV: lower\nC8 C8 | C8 C8 |\n" + 
                "V: upper\nz2 Gd fGdf z2 Gd fGdf | z2 Ae aAea z2 Ae aAea |]\n" + 
                "V: middle\nz D7 z D7 | z E7 z E7 |]\nV: lower\nB,8 B,8 | C8 C8 |]\n", body);       
    }
    
}

