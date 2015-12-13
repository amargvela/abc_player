// Generated from MusicGrammar.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MusicGrammarParser}.
 */
public interface MusicGrammarListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(MusicGrammarParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(MusicGrammarParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#abcmusic}.
   * @param ctx the parse tree
   */
  void enterAbcmusic(MusicGrammarParser.AbcmusicContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#abcmusic}.
   * @param ctx the parse tree
   */
  void exitAbcmusic(MusicGrammarParser.AbcmusicContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#abcline}.
   * @param ctx the parse tree
   */
  void enterAbcline(MusicGrammarParser.AbclineContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#abcline}.
   * @param ctx the parse tree
   */
  void exitAbcline(MusicGrammarParser.AbclineContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#element}.
   * @param ctx the parse tree
   */
  void enterElement(MusicGrammarParser.ElementContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#element}.
   * @param ctx the parse tree
   */
  void exitElement(MusicGrammarParser.ElementContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#noteelement}.
   * @param ctx the parse tree
   */
  void enterNoteelement(MusicGrammarParser.NoteelementContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#noteelement}.
   * @param ctx the parse tree
   */
  void exitNoteelement(MusicGrammarParser.NoteelementContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#note}.
   * @param ctx the parse tree
   */
  void enterNote(MusicGrammarParser.NoteContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#note}.
   * @param ctx the parse tree
   */
  void exitNote(MusicGrammarParser.NoteContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#noteorrest}.
   * @param ctx the parse tree
   */
  void enterNoteorrest(MusicGrammarParser.NoteorrestContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#noteorrest}.
   * @param ctx the parse tree
   */
  void exitNoteorrest(MusicGrammarParser.NoteorrestContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#pitch}.
   * @param ctx the parse tree
   */
  void enterPitch(MusicGrammarParser.PitchContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#pitch}.
   * @param ctx the parse tree
   */
  void exitPitch(MusicGrammarParser.PitchContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#octave}.
   * @param ctx the parse tree
   */
  void enterOctave(MusicGrammarParser.OctaveContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#octave}.
   * @param ctx the parse tree
   */
  void exitOctave(MusicGrammarParser.OctaveContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#highoctave}.
   * @param ctx the parse tree
   */
  void enterHighoctave(MusicGrammarParser.HighoctaveContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#highoctave}.
   * @param ctx the parse tree
   */
  void exitHighoctave(MusicGrammarParser.HighoctaveContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#lowoctave}.
   * @param ctx the parse tree
   */
  void enterLowoctave(MusicGrammarParser.LowoctaveContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#lowoctave}.
   * @param ctx the parse tree
   */
  void exitLowoctave(MusicGrammarParser.LowoctaveContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#notelength}.
   * @param ctx the parse tree
   */
  void enterNotelength(MusicGrammarParser.NotelengthContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#notelength}.
   * @param ctx the parse tree
   */
  void exitNotelength(MusicGrammarParser.NotelengthContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#accidental}.
   * @param ctx the parse tree
   */
  void enterAccidental(MusicGrammarParser.AccidentalContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#accidental}.
   * @param ctx the parse tree
   */
  void exitAccidental(MusicGrammarParser.AccidentalContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#rest}.
   * @param ctx the parse tree
   */
  void enterRest(MusicGrammarParser.RestContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#rest}.
   * @param ctx the parse tree
   */
  void exitRest(MusicGrammarParser.RestContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#tupletelement}.
   * @param ctx the parse tree
   */
  void enterTupletelement(MusicGrammarParser.TupletelementContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#tupletelement}.
   * @param ctx the parse tree
   */
  void exitTupletelement(MusicGrammarParser.TupletelementContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#tupletspec}.
   * @param ctx the parse tree
   */
  void enterTupletspec(MusicGrammarParser.TupletspecContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#tupletspec}.
   * @param ctx the parse tree
   */
  void exitTupletspec(MusicGrammarParser.TupletspecContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#multinote}.
   * @param ctx the parse tree
   */
  void enterMultinote(MusicGrammarParser.MultinoteContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#multinote}.
   * @param ctx the parse tree
   */
  void exitMultinote(MusicGrammarParser.MultinoteContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#barline}.
   * @param ctx the parse tree
   */
  void enterBarline(MusicGrammarParser.BarlineContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#barline}.
   * @param ctx the parse tree
   */
  void exitBarline(MusicGrammarParser.BarlineContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#nthrepeat}.
   * @param ctx the parse tree
   */
  void enterNthrepeat(MusicGrammarParser.NthrepeatContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#nthrepeat}.
   * @param ctx the parse tree
   */
  void exitNthrepeat(MusicGrammarParser.NthrepeatContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#midtunefield}.
   * @param ctx the parse tree
   */
  void enterMidtunefield(MusicGrammarParser.MidtunefieldContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#midtunefield}.
   * @param ctx the parse tree
   */
  void exitMidtunefield(MusicGrammarParser.MidtunefieldContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#fieldvoice}.
   * @param ctx the parse tree
   */
  void enterFieldvoice(MusicGrammarParser.FieldvoiceContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#fieldvoice}.
   * @param ctx the parse tree
   */
  void exitFieldvoice(MusicGrammarParser.FieldvoiceContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#comment}.
   * @param ctx the parse tree
   */
  void enterComment(MusicGrammarParser.CommentContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#comment}.
   * @param ctx the parse tree
   */
  void exitComment(MusicGrammarParser.CommentContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#endofline}.
   * @param ctx the parse tree
   */
  void enterEndofline(MusicGrammarParser.EndoflineContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#endofline}.
   * @param ctx the parse tree
   */
  void exitEndofline(MusicGrammarParser.EndoflineContext ctx);
  /**
   * Enter a parse tree produced by {@link MusicGrammarParser#text}.
   * @param ctx the parse tree
   */
  void enterText(MusicGrammarParser.TextContext ctx);
  /**
   * Exit a parse tree produced by {@link MusicGrammarParser#text}.
   * @param ctx the parse tree
   */
  void exitText(MusicGrammarParser.TextContext ctx);
}