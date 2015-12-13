package abc.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parses a file and divides it into the header and music body.
 * Replaces all in sentence (like title, composer name) whitespace
 * with '@' character.
 */
public class FileSeparator {
    
    private final String header;
    private final String music;
    
    // Abstraction function:
    //   header and music represent header and music body
    //   respectively of the music being inside the file.
    // Rep invariant:
    //   neither header nor music may be null.
    //   (Them having the correct structure will later be 
    //    checked by the grammar parsers).
    // Rep exposure:
    //   all the fields are private, final and immutable;
    //   no mutable data is returned;
    //   There is no producer that uses passed mutable data.
    
    /**
     * Construct FileSepartor based on the given file
     * @param file file to be parsed
     */
    public FileSeparator(File file) {
        List<String> splitFile;
        try {
            splitFile = split(file);
        }
        catch (IOException ioe) {
            splitFile = new ArrayList<String>(Arrays.asList("",""));
        }
        this.header = convertHeaderSpaces(splitFile.get(0));
        this.music = splitFile.get(1);
        checkRep();
    }
    
    /**
     * Checks the rep invariants
     */
    private void checkRep() {
        assert ( header != null);
        assert (music != null);
    }
    
    /**
     * If the string starts with the given token, 
     * function trims the rest of the string and
     * then replaces all the spaces inside with '@'. 
     * @param line given string 
     * @param token given suffix
     * @return resulted string
     */
    private String convertLine(String line, String token) {
        if (line.startsWith(token)) {
            String text = line.substring(token.length()).trim();
            text = text.replace(' ', '@');
            line = line.substring(0, token.length()) + text;
        }
        return line;
    }
    
    /**
     * Changes all the spaces with '@' inside a composer 
     * name or title in the header.
     * @return resulted header
     */
    private String convertHeaderSpaces(String header) {
        String[] lines = header.split("\\r?\\n");
        String result = "";
        for (int i=0; i<lines.length; i++) {
            String currLine = convertLine(lines[i], "T:");
            currLine = convertLine(currLine, "C:");
            result += currLine + '\n';
        }
        return result;
    }
        
    /**
     * Read from the file and split it into the header and music body
     * @param abcFile file to be parsed
     * @return list whose first element represents header and 
     *         second one represents the music body
     * @throws IOException if the file can't be found/read
     */
    private List<String> split(File abcFile) throws IOException{
        if (!(abcFile.canRead() && abcFile.exists())) {
            throw new RuntimeException("can't read or open file");
        }
        else {

            String headerStrings = "";
            String musicStrings = "";
            FileReader reader = new FileReader(abcFile);
            BufferedReader lineReader = new BufferedReader(reader);
            String musicString = lineReader.readLine().trim();

            while (!(musicString.startsWith("K:"))) {
                headerStrings = headerStrings + musicString + "\n";
                musicString = lineReader.readLine().trim();
            }

            headerStrings = headerStrings + musicString.trim() + "\n";
            musicString = lineReader.readLine();

            while (!(musicString == null)) {
                musicStrings = musicStrings + musicString.trim() + "\n";
                musicString = lineReader.readLine();
            }

            lineReader.close();
            
            String[] splitMusic = musicStrings.split(" ");
            String musicStrings2 = "";
            for (String elem: splitMusic) {
                if (!(elem.equals(""))) {
                    if (elem.contains("V: ")) {
                        elem = elem.replace("V: ", "V:");
                    }
                    musicStrings2 = musicStrings2 + " " + elem;
                }
            }
            
            String[] splitHeader = headerStrings.split(" ");
            String headerStrings2 = "";
            for (String elem: splitHeader) {
                if (!(elem.equals(""))) {
                    headerStrings2 = headerStrings2 + " " + elem;
                }
            }
            
            return new ArrayList<String>(Arrays.asList(headerStrings,musicStrings));
        }     
    }
    
    /**
     * @return header of the separated file
     */
    public String getHeader() {
        return this.header;
    }
    
    /**
     * @return music body of hte separated file
     */
    public String getMusic() {
        return this.music;
    }  
    
}