package abc.music;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import abc.parser.HeaderGrammarListener;
import abc.parser.HeaderGrammarParser.CommentContext;
import abc.parser.HeaderGrammarParser.EndoflineContext;
import abc.parser.HeaderGrammarParser.FieldcomposerContext;
import abc.parser.HeaderGrammarParser.FielddefaultlengthContext;
import abc.parser.HeaderGrammarParser.FieldkeyContext;
import abc.parser.HeaderGrammarParser.FieldmeterContext;
import abc.parser.HeaderGrammarParser.FieldnumberContext;
import abc.parser.HeaderGrammarParser.FieldtempoContext;
import abc.parser.HeaderGrammarParser.FieldtitleContext;
import abc.parser.HeaderGrammarParser.FieldvoiceContext;
import abc.parser.HeaderGrammarParser.HeaderContext;
import abc.parser.HeaderGrammarParser.KeyContext;
import abc.parser.HeaderGrammarParser.KeyaccidentalContext;
import abc.parser.HeaderGrammarParser.KeynoteContext;
import abc.parser.HeaderGrammarParser.MeterContext;
import abc.parser.HeaderGrammarParser.MeterfractionContext;
import abc.parser.HeaderGrammarParser.ModeminorContext;
import abc.parser.HeaderGrammarParser.NotelengthstrictContext;
import abc.parser.HeaderGrammarParser.OtherfieldsContext;
import abc.parser.HeaderGrammarParser.RootContext;
import abc.parser.HeaderGrammarParser.TempoContext;
import abc.parser.HeaderGrammarParser.TextContext;

/**
 * Construct header while walking through parsing tree of the header string.
 */
public class FetchHeader implements HeaderGrammarListener {    
    
    private final HeaderInfo header = new HeaderInfo();
    
    /**
     * @return header of the musical piece file 
     */
    public HeaderInfo getHeader() {
        return new HeaderInfo(header);
    }
    
    @Override
    public void enterEveryRule(ParserRuleContext arg0) {        
    }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {
    }

    @Override
    public void visitErrorNode(ErrorNode arg0) {
    }

    @Override
    public void visitTerminal(TerminalNode arg0) {
    }

    @Override
    public void enterRoot(RootContext ctx) {
    }

    @Override
    public void exitRoot(RootContext ctx) {
    }

    @Override
    public void enterHeader(HeaderContext ctx) {
    }

    @Override
    public void exitHeader(HeaderContext ctx) {
    }

    @Override
    public void enterFieldnumber(FieldnumberContext ctx) {
    }

    @Override
    public void exitFieldnumber(FieldnumberContext ctx) {
    }

    @Override
    public void enterFieldtitle(FieldtitleContext ctx) {
    }

    /**
     * Get the T value of the header, as defined in HeaderInfo class
     */
    @Override
    public void exitFieldtitle(FieldtitleContext ctx) {
        TextContext text = ctx.text();
        header.setT(text.getText());
    }

    @Override
    public void enterOtherfields(OtherfieldsContext ctx) {
    }

    @Override
    public void exitOtherfields(OtherfieldsContext ctx) {
    }

    @Override
    public void enterFieldcomposer(FieldcomposerContext ctx) {
    }

    /**
     * Get the C value of the header, as defined in HeaderInfo class
     */
    @Override
    public void exitFieldcomposer(FieldcomposerContext ctx) {
        TextContext text = ctx.text();
        header.setC(text.getText());
    }

    @Override
    public void enterFielddefaultlength(FielddefaultlengthContext ctx) {
    }

    /**
     * Get the L value of the header, as defined in HeaderInfo class 
     */
    @Override
    public void exitFielddefaultlength(FielddefaultlengthContext ctx) {
        NotelengthstrictContext text = ctx.notelengthstrict();
        header.setL(text.getText());
    }

    @Override
    public void enterFieldmeter(FieldmeterContext ctx) {
    }

    /**
     * Get the M value of the header, as defined in HeaderInfo class
     */
    @Override
    public void exitFieldmeter(FieldmeterContext ctx) {
        MeterContext text = ctx.meter();
        header.setM(text.getText());
    }

    @Override
    public void enterFieldtempo(FieldtempoContext ctx) {
    }

    /**
     * Get the Q value of the header, as defined in HeaderInfo class
     */
    @Override
    public void exitFieldtempo(FieldtempoContext ctx) {
        TempoContext text = ctx.tempo();
        header.setQ(text.getText());
    }

    @Override
    public void enterFieldvoice(FieldvoiceContext ctx) {
    }

    @Override
    public void exitFieldvoice(FieldvoiceContext ctx) {
    }

    @Override
    public void enterFieldkey(FieldkeyContext ctx) {
    }
    
    /**
     * Get the K value of the header, as defined in HeaderInfo class
     */
    @Override
    public void exitFieldkey(FieldkeyContext ctx) {
        KeyContext text = ctx.key();
        header.setK(text.getText());
    }

    @Override
    public void enterKey(KeyContext ctx) {
    }

    @Override
    public void exitKey(KeyContext ctx) {
    }

    @Override
    public void enterKeynote(KeynoteContext ctx) {
    }

    @Override
    public void exitKeynote(KeynoteContext ctx) {
    }

    @Override
    public void enterKeyaccidental(KeyaccidentalContext ctx) {
    }

    @Override
    public void exitKeyaccidental(KeyaccidentalContext ctx) {
    }

    @Override
    public void enterModeminor(ModeminorContext ctx) {
    }

    @Override
    public void exitModeminor(ModeminorContext ctx) {
    }

    @Override
    public void enterMeter(MeterContext ctx) {
    }

    @Override
    public void exitMeter(MeterContext ctx) {
    }

    @Override
    public void enterMeterfraction(MeterfractionContext ctx) {
    }

    @Override
    public void exitMeterfraction(MeterfractionContext ctx) {
    }

    @Override
    public void enterNotelengthstrict(NotelengthstrictContext ctx) {   
    }

    @Override
    public void exitNotelengthstrict(NotelengthstrictContext ctx) {
    }

    @Override
    public void enterTempo(TempoContext ctx) {
    }

    @Override
    public void exitTempo(TempoContext ctx) {
    }

    @Override
    public void enterText(TextContext ctx) {
        
    }

    @Override
    public void exitText(TextContext ctx) {
    }

    @Override
    public void enterComment(CommentContext ctx) {
    }

    @Override
    public void exitComment(CommentContext ctx) {
    }

    @Override
    public void enterEndofline(EndoflineContext ctx) {
    }

    @Override
    public void exitEndofline(EndoflineContext ctx) {
    }
    
}
