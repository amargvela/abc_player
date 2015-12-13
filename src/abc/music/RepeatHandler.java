package abc.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function for concatenating voices and handling repeats. 
 * Called before .abc file is parsed into music. 
 */

public class RepeatHandler{
    
    /**
     * Filters out empty string elements from a String array. It will remove all empty string elements from 
     * the string array.
     * @param array an array of strings
     * @return a list of strings, representing all of the string elements in the string array (preserving their original order in the string array)
     *      with all empty strings removed
     */
    private static List<String> filterOutEmpty(String[] array) {
        List<String> stringList = new ArrayList<String>();
        for (String element: array) {
            if (!element.equals("")) {
                stringList.add(element);
            }
        }
        return stringList;
    }
    
    /**
     * Takes a list of strings and join the list into one single string separated by spaces.
     * @param stringList a list of strings
     * @return a string representing a concatenation of all of the strings in stringList (preserving their order) and with a single space
     *      in between elements for each concatenation
     */
    private static String joinStringList(List<String> stringList) {
        String finalString = stringList.get(0);
        for (int i=1; i<stringList.size();i++) {
            finalString = finalString + " "+stringList.get(i);
        }
        return finalString;
    }
    
    /**
     * Takes an input with one or more voices of music. Musical lines represented by identical voices
     * are concatenated together, and then a new string is produced whereby the voices are each represented
     * in a continuous string. Repeats are removed according to spec in handleRepeats. 
     * @param input, .abc file body as large string
     * @return string representing concatenating of individual voices and removal of repeats
     */
    public static String mergeVoices(String input) {
        // Store hastable representing individual voices as keys and music played by voice as values
        Map<String,String> voices = new HashMap<String,String>();
        
        // Split input by lines 
        String[] stringSplit = input.split("\n");
        // Assume music has single voice until find evidence otherwise
        String voice = "DefaultNone";
        String currentMusic = "";
        
        // Iterate through rows
        for (int i=0; i < stringSplit.length; i++) {
            // Evidence that piece has multiple voices 
            if (stringSplit[i].contains("V:") && i==0) {
                String[] splitBySpace = stringSplit[i].split(" ");
                List<String> stringList = filterOutEmpty(splitBySpace);
                voice = joinStringList(stringList);
            }
            // Concatenate music string played by voice
            else if (stringSplit[i].contains("V:") && i>0) {
                // Voice seen before
                if (voices.containsKey(voice) && !(currentMusic.equals(""))) {
                    voices.put(voice, voices.get(voice)+" "+currentMusic);
                }
                // Voice not seen before
                else {
                    if (!(currentMusic.equals(""))) {
                        voices.put(voice, currentMusic);
                    }
                }
                
                // Set voice and reset current music
                String[] splitBySpace = stringSplit[i].split(" ");
                List<String> stringList = filterOutEmpty(splitBySpace);
                voice = joinStringList(stringList);
                
                currentMusic = ""; 
            }
            // Piece has one voice
            else {
                currentMusic = currentMusic + " " +stringSplit[i];
            }
        }
        
        // Final line in music 
        if (voices.keySet().contains(voice)) {
            voices.put(voice, voices.get(voice) + " " + currentMusic);
        }
        else {
            voices.put(voice, currentMusic);
        }
        
        // If piece has single voice, remove repeats for current music
        if (voice.equals("DefaultNone")) {
            return handleRepeats(currentMusic)+"\n";
        }
        // If piece has multiple voices, remove repeats for each individual voice
        else {
            String finalString = "";
            for (String voiceKey: voices.keySet()) {
                finalString = finalString + "\n" + voiceKey + "\n" + handleRepeats(voices.get(voiceKey));
            }
            return finalString.substring(1,finalString.length()).replace("%", "")+"\n";
        }
    }


    /**
     * Function for handling repeats. Takes in a string representing 
     * a single voiced piece of music and outputs a string representing 
     * the music as played, with repeat indicators removed. Any indicators
     * representing a repeat (|: or :|) are replaced with | in the output.
     * 
     * Follows the following rules:
     * - A section of music enclosed within |: and :| is repeated once
     * - Begin bar |: can be omitted, and in this case, repeat is from 
     *   beginning of major section (bar follows |] or from beginning of music)
     * - Repeated section 
     * 
     * @param input a string representing a single-voiced piece of music
     * @return string representing music as played with repeat indicators
     * (|: or :|) replaced by |
     */
    public static String handleRepeats(String input){
        
        //check to see if input has any repeats in it
        //if no repeats, simply return the input
        if (!(input.contains(":|"))) {
            return input;
        }
        
        //initialize a variable representing the input and a string to store the output
        String inputProcessed = input;
        String output = "";

        // store indices for: start of repeated section, start after beginning of music or last repeat, start of first alternate ending
        // store boolean representing whether we are currently in a repeat or not
        // store boolean indicating whether we have alternate endings or not
        int repeatStart = 0;
        int previousStart = 0;
        int alternateEndingIndex = 0;
        boolean inRepeat = false;
        boolean alternateEnding = false;
        
        // iterate through the characters in the input
        for (int i = 1; i < inputProcessed.length(); i++){
            String substring = inputProcessed.substring(i-1, i+1);

            // check to see if substring matches the beginning repeat bar
            if (substring.equals("|:")){
                
                //store start of repeated segment index, change mode to in repeat, append to the output the nonrepeated section
                //      between previousStart and current, update previousStart
                repeatStart = i+1; 
                inRepeat = true;
                if (i-1 >= previousStart) {
                    output = output + inputProcessed.substring(previousStart,i-1) + " | ";
                    previousStart = i+1;
                }
            }
            
            // check if substring matches || or |]
            else if (!inRepeat && (substring.equals("||") || substring.equals("|]"))){ 
                
                // update repeat start and previous start
                repeatStart = i+1;
                
                if (substring.equals("||") && i-1>=previousStart) {
                    //append to output all music prior to the ||
                    output = output + inputProcessed.substring(previousStart,i-1) + " || ";
                }
                else if (i-1>=previousStart){
                    //append to output all music prior to |]
                    output = output + inputProcessed.substring(previousStart,i-1) + " |] ";
                }
                previousStart = i+1;
            }

            //check to see if first alternate ending
            else if (substring.equals("[1")){
                //change mode to had alternate ending, store index
                alternateEnding = true;
                alternateEndingIndex = i+1;
            }
            
            //check to see if second alternate ending
            else if (substring.equals("[2")) {
                //update previous start index
                previousStart = i+1;
            }

            //check to see if matches end of repeat
            else if (substring.equals(":|")) {
                previousStart = i+1;
                
                //if no alternate ending exists: add to output repeated segment
                if (!alternateEnding){
                    String repeatedSubstring = inputProcessed.substring(repeatStart, i-1);
                    output = output + repeatedSubstring + " | " + repeatedSubstring + " | ";
                }

                //if alternate ending exists, add to output repeated segment, first ending, and then repeated segment
                else {             
                    String repeatedSubstring = inputProcessed.substring(repeatStart, alternateEndingIndex-3); 
                    String alternateEndingSubstring = inputProcessed.substring(alternateEndingIndex, i-1);
                    
                    output = output + repeatedSubstring + " | " + alternateEndingSubstring + " | " + repeatedSubstring + " | ";
                    
                }
                
                //update variables to reflect no longer being in repeat mode and/or having alternate endings
                inRepeat = false;
                alternateEnding = false;
            }
        }
        
        //effectively add second ending to output (second ending is same as rest of music when no alternate endings present)
        if (previousStart <= inputProcessed.length()) {
            output = output + inputProcessed.substring(previousStart); 
        }
        
        //remove extra spaces between elements in output, and add back a single space between these elements
        String[] stringSplit = output.split(" ");
        String finalOutput = "";
        
        for (String elem: stringSplit) {
            if (elem.equals("")) {
                continue;
            }
            else {
                finalOutput = finalOutput + " " + elem;
            }
        }
        
        //remove bar at end of musical piece
        if (finalOutput.charAt(finalOutput.length()-1) == '|') {
            finalOutput = finalOutput.substring(0, finalOutput.length()-2);
        }
        
        //remove bar at beginning of musical piece
        if (finalOutput.charAt(1) == '|') {
            finalOutput = finalOutput.substring(2, finalOutput.length());
        }
        
        //return the final musical piece, without repeat symbols (repeated music placed in string already)
        return finalOutput.substring(1); 
    }
}