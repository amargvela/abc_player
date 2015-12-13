package abc.player;

import java.io.File;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import abc.music.ConstructMusic;
import abc.music.FetchHeader;
import abc.music.HeaderInfo;
import abc.music.Music;
import abc.music.RepeatHandler;
import abc.parser.HeaderGrammarLexer;
import abc.parser.HeaderGrammarParser;
import abc.parser.MusicGrammarLexer;
import abc.parser.MusicGrammarParser;
import abc.sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {
    
    private static final long THREAD_MULTIPLIER = 120000;

    /**
     * Helper method for constructing HeaderInfo objects from 
     * String header representing header of .abc file
     * @param header represents header of .abc file
     */
    private static HeaderInfo headerParserHelper(String header){
        CharStream stream = new ANTLRInputStream(header);
        HeaderGrammarLexer lexer = new HeaderGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        HeaderGrammarParser parser = new HeaderGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        FetchHeader listener = new FetchHeader();
        walker.walk(listener, tree);
        return listener.getHeader();
    }
    
    /**
     * Helper method for constructing Music object from 
     * String body representing body of .abc file
     * @param body represents body of .abc file 
     */
    private static Music bodyParserHelper(String body, HeaderInfo header){
        CharStream stream = new ANTLRInputStream(body);
        MusicGrammarLexer lexer = new MusicGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        MusicGrammarParser parser = new MusicGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        ParseTree tree = parser.root();
        ParseTreeWalker walker = new ParseTreeWalker();
        ConstructMusic listener = new ConstructMusic(header);
        walker.walk(listener, tree);
        return listener.getExpression();
    }
    
    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * (Your code should not exit the application abnormally using
     * System.exit().)
     * 
     * @param file the name of input abc file
     */
    public static void play(String file) {
        FileSeparator fs = new FileSeparator(new File(file));
        String musicString = fs.getMusic();
        String headerString = fs.getHeader();
        HeaderInfo header = headerParserHelper(headerString);
        System.out.println(headerString.replace('@', ' '));
        Music music = bodyParserHelper(RepeatHandler.mergeVoices(musicString), header);
        
        try {
            SequencePlayer player = new SequencePlayer(header.getQBeats(), -1);
            music.play(player, 0);
            player.play();
            Thread.sleep((long)(music.duration()/header.getQBeats()) * THREAD_MULTIPLIER);
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0)
            play(args[0]);
    }

}
