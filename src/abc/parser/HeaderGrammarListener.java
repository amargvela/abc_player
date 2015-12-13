// Generated from HeaderGrammar.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HeaderGrammarParser}.
 */
public interface HeaderGrammarListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(HeaderGrammarParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(HeaderGrammarParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#header}.
   * @param ctx the parse tree
   */
  void enterHeader(HeaderGrammarParser.HeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#header}.
   * @param ctx the parse tree
   */
  void exitHeader(HeaderGrammarParser.HeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldnumber}.
   * @param ctx the parse tree
   */
  void enterFieldnumber(HeaderGrammarParser.FieldnumberContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldnumber}.
   * @param ctx the parse tree
   */
  void exitFieldnumber(HeaderGrammarParser.FieldnumberContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldtitle}.
   * @param ctx the parse tree
   */
  void enterFieldtitle(HeaderGrammarParser.FieldtitleContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldtitle}.
   * @param ctx the parse tree
   */
  void exitFieldtitle(HeaderGrammarParser.FieldtitleContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#otherfields}.
   * @param ctx the parse tree
   */
  void enterOtherfields(HeaderGrammarParser.OtherfieldsContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#otherfields}.
   * @param ctx the parse tree
   */
  void exitOtherfields(HeaderGrammarParser.OtherfieldsContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldcomposer}.
   * @param ctx the parse tree
   */
  void enterFieldcomposer(HeaderGrammarParser.FieldcomposerContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldcomposer}.
   * @param ctx the parse tree
   */
  void exitFieldcomposer(HeaderGrammarParser.FieldcomposerContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fielddefaultlength}.
   * @param ctx the parse tree
   */
  void enterFielddefaultlength(HeaderGrammarParser.FielddefaultlengthContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fielddefaultlength}.
   * @param ctx the parse tree
   */
  void exitFielddefaultlength(HeaderGrammarParser.FielddefaultlengthContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldmeter}.
   * @param ctx the parse tree
   */
  void enterFieldmeter(HeaderGrammarParser.FieldmeterContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldmeter}.
   * @param ctx the parse tree
   */
  void exitFieldmeter(HeaderGrammarParser.FieldmeterContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldtempo}.
   * @param ctx the parse tree
   */
  void enterFieldtempo(HeaderGrammarParser.FieldtempoContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldtempo}.
   * @param ctx the parse tree
   */
  void exitFieldtempo(HeaderGrammarParser.FieldtempoContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldvoice}.
   * @param ctx the parse tree
   */
  void enterFieldvoice(HeaderGrammarParser.FieldvoiceContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldvoice}.
   * @param ctx the parse tree
   */
  void exitFieldvoice(HeaderGrammarParser.FieldvoiceContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#fieldkey}.
   * @param ctx the parse tree
   */
  void enterFieldkey(HeaderGrammarParser.FieldkeyContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#fieldkey}.
   * @param ctx the parse tree
   */
  void exitFieldkey(HeaderGrammarParser.FieldkeyContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#key}.
   * @param ctx the parse tree
   */
  void enterKey(HeaderGrammarParser.KeyContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#key}.
   * @param ctx the parse tree
   */
  void exitKey(HeaderGrammarParser.KeyContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#keynote}.
   * @param ctx the parse tree
   */
  void enterKeynote(HeaderGrammarParser.KeynoteContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#keynote}.
   * @param ctx the parse tree
   */
  void exitKeynote(HeaderGrammarParser.KeynoteContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#keyaccidental}.
   * @param ctx the parse tree
   */
  void enterKeyaccidental(HeaderGrammarParser.KeyaccidentalContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#keyaccidental}.
   * @param ctx the parse tree
   */
  void exitKeyaccidental(HeaderGrammarParser.KeyaccidentalContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#modeminor}.
   * @param ctx the parse tree
   */
  void enterModeminor(HeaderGrammarParser.ModeminorContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#modeminor}.
   * @param ctx the parse tree
   */
  void exitModeminor(HeaderGrammarParser.ModeminorContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#meter}.
   * @param ctx the parse tree
   */
  void enterMeter(HeaderGrammarParser.MeterContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#meter}.
   * @param ctx the parse tree
   */
  void exitMeter(HeaderGrammarParser.MeterContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#meterfraction}.
   * @param ctx the parse tree
   */
  void enterMeterfraction(HeaderGrammarParser.MeterfractionContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#meterfraction}.
   * @param ctx the parse tree
   */
  void exitMeterfraction(HeaderGrammarParser.MeterfractionContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#notelengthstrict}.
   * @param ctx the parse tree
   */
  void enterNotelengthstrict(HeaderGrammarParser.NotelengthstrictContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#notelengthstrict}.
   * @param ctx the parse tree
   */
  void exitNotelengthstrict(HeaderGrammarParser.NotelengthstrictContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#tempo}.
   * @param ctx the parse tree
   */
  void enterTempo(HeaderGrammarParser.TempoContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#tempo}.
   * @param ctx the parse tree
   */
  void exitTempo(HeaderGrammarParser.TempoContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#text}.
   * @param ctx the parse tree
   */
  void enterText(HeaderGrammarParser.TextContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#text}.
   * @param ctx the parse tree
   */
  void exitText(HeaderGrammarParser.TextContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#comment}.
   * @param ctx the parse tree
   */
  void enterComment(HeaderGrammarParser.CommentContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#comment}.
   * @param ctx the parse tree
   */
  void exitComment(HeaderGrammarParser.CommentContext ctx);
  /**
   * Enter a parse tree produced by {@link HeaderGrammarParser#endofline}.
   * @param ctx the parse tree
   */
  void enterEndofline(HeaderGrammarParser.EndoflineContext ctx);
  /**
   * Exit a parse tree produced by {@link HeaderGrammarParser#endofline}.
   * @param ctx the parse tree
   */
  void exitEndofline(HeaderGrammarParser.EndoflineContext ctx);
}