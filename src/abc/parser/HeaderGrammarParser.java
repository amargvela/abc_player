// Generated from HeaderGrammar.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HeaderGrammarParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, TEXTCHAR=14, DIGIT=15, BASENOTE=16, 
    NEWLINE=17, BCHAR=18, CCHAR=19, MCHAR=20, SPACES=21;
  public static final int
    RULE_root = 0, RULE_header = 1, RULE_fieldnumber = 2, RULE_fieldtitle = 3, 
    RULE_otherfields = 4, RULE_fieldcomposer = 5, RULE_fielddefaultlength = 6, 
    RULE_fieldmeter = 7, RULE_fieldtempo = 8, RULE_fieldvoice = 9, RULE_fieldkey = 10, 
    RULE_key = 11, RULE_keynote = 12, RULE_keyaccidental = 13, RULE_modeminor = 14, 
    RULE_meter = 15, RULE_meterfraction = 16, RULE_notelengthstrict = 17, 
    RULE_tempo = 18, RULE_text = 19, RULE_comment = 20, RULE_endofline = 21;
  public static final String[] ruleNames = {
    "root", "header", "fieldnumber", "fieldtitle", "otherfields", "fieldcomposer", 
    "fielddefaultlength", "fieldmeter", "fieldtempo", "fieldvoice", "fieldkey", 
    "key", "keynote", "keyaccidental", "modeminor", "meter", "meterfraction", 
    "notelengthstrict", "tempo", "text", "comment", "endofline"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'X:'", "'T:'", "'C:'", "'L:'", "'M:'", "'Q:'", "'V:'", "'K:'", 
    "'#'", "'C|'", "'/'", "'='", "'%'", null, null, null, null, "'b'", "'C'", 
    "'m'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, "TEXTCHAR", "DIGIT", "BASENOTE", "NEWLINE", "BCHAR", "CCHAR", 
    "MCHAR", "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "HeaderGrammar.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the parser stop running if it encounters
      // invalid input and throw a RuntimeException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol, 
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public HeaderGrammarParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public HeaderContext header() {
      return getRuleContext(HeaderContext.class,0);
    }
    public TerminalNode EOF() { return getToken(HeaderGrammarParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(44);
      header();
      setState(45);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class HeaderContext extends ParserRuleContext {
    public FieldnumberContext fieldnumber() {
      return getRuleContext(FieldnumberContext.class,0);
    }
    public FieldtitleContext fieldtitle() {
      return getRuleContext(FieldtitleContext.class,0);
    }
    public FieldkeyContext fieldkey() {
      return getRuleContext(FieldkeyContext.class,0);
    }
    public List<CommentContext> comment() {
      return getRuleContexts(CommentContext.class);
    }
    public CommentContext comment(int i) {
      return getRuleContext(CommentContext.class,i);
    }
    public List<OtherfieldsContext> otherfields() {
      return getRuleContexts(OtherfieldsContext.class);
    }
    public OtherfieldsContext otherfields(int i) {
      return getRuleContext(OtherfieldsContext.class,i);
    }
    public HeaderContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_header; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterHeader(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitHeader(this);
    }
  }

  public final HeaderContext header() throws RecognitionException {
    HeaderContext _localctx = new HeaderContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_header);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(47);
      fieldnumber();
      setState(51);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__12) {
        {
        {
        setState(48);
        comment();
        }
        }
        setState(53);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(54);
      fieldtitle();
      setState(58);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__12))) != 0)) {
        {
        {
        setState(55);
        otherfields();
        }
        }
        setState(60);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(61);
      fieldkey();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldnumberContext extends ParserRuleContext {
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public List<TerminalNode> DIGIT() { return getTokens(HeaderGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(HeaderGrammarParser.DIGIT, i);
    }
    public FieldnumberContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldnumber; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldnumber(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldnumber(this);
    }
  }

  public final FieldnumberContext fieldnumber() throws RecognitionException {
    FieldnumberContext _localctx = new FieldnumberContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_fieldnumber);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(63);
      match(T__0);
      setState(65); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(64);
        match(DIGIT);
        }
        }
        setState(67); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      setState(69);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldtitleContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldtitleContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldtitle; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldtitle(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldtitle(this);
    }
  }

  public final FieldtitleContext fieldtitle() throws RecognitionException {
    FieldtitleContext _localctx = new FieldtitleContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_fieldtitle);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(71);
      match(T__1);
      setState(72);
      text();
      setState(73);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class OtherfieldsContext extends ParserRuleContext {
    public FieldcomposerContext fieldcomposer() {
      return getRuleContext(FieldcomposerContext.class,0);
    }
    public FielddefaultlengthContext fielddefaultlength() {
      return getRuleContext(FielddefaultlengthContext.class,0);
    }
    public FieldmeterContext fieldmeter() {
      return getRuleContext(FieldmeterContext.class,0);
    }
    public FieldtempoContext fieldtempo() {
      return getRuleContext(FieldtempoContext.class,0);
    }
    public FieldvoiceContext fieldvoice() {
      return getRuleContext(FieldvoiceContext.class,0);
    }
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public OtherfieldsContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_otherfields; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterOtherfields(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitOtherfields(this);
    }
  }

  public final OtherfieldsContext otherfields() throws RecognitionException {
    OtherfieldsContext _localctx = new OtherfieldsContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_otherfields);
    try {
      setState(81);
      switch (_input.LA(1)) {
      case T__2:
        enterOuterAlt(_localctx, 1);
        {
        setState(75);
        fieldcomposer();
        }
        break;
      case T__3:
        enterOuterAlt(_localctx, 2);
        {
        setState(76);
        fielddefaultlength();
        }
        break;
      case T__4:
        enterOuterAlt(_localctx, 3);
        {
        setState(77);
        fieldmeter();
        }
        break;
      case T__5:
        enterOuterAlt(_localctx, 4);
        {
        setState(78);
        fieldtempo();
        }
        break;
      case T__6:
        enterOuterAlt(_localctx, 5);
        {
        setState(79);
        fieldvoice();
        }
        break;
      case T__12:
        enterOuterAlt(_localctx, 6);
        {
        setState(80);
        comment();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldcomposerContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldcomposerContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldcomposer; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldcomposer(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldcomposer(this);
    }
  }

  public final FieldcomposerContext fieldcomposer() throws RecognitionException {
    FieldcomposerContext _localctx = new FieldcomposerContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_fieldcomposer);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(83);
      match(T__2);
      setState(84);
      text();
      setState(85);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FielddefaultlengthContext extends ParserRuleContext {
    public NotelengthstrictContext notelengthstrict() {
      return getRuleContext(NotelengthstrictContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FielddefaultlengthContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fielddefaultlength; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFielddefaultlength(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFielddefaultlength(this);
    }
  }

  public final FielddefaultlengthContext fielddefaultlength() throws RecognitionException {
    FielddefaultlengthContext _localctx = new FielddefaultlengthContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_fielddefaultlength);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(87);
      match(T__3);
      setState(88);
      notelengthstrict();
      setState(89);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldmeterContext extends ParserRuleContext {
    public MeterContext meter() {
      return getRuleContext(MeterContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldmeterContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldmeter; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldmeter(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldmeter(this);
    }
  }

  public final FieldmeterContext fieldmeter() throws RecognitionException {
    FieldmeterContext _localctx = new FieldmeterContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_fieldmeter);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(91);
      match(T__4);
      setState(92);
      meter();
      setState(93);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldtempoContext extends ParserRuleContext {
    public TempoContext tempo() {
      return getRuleContext(TempoContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldtempoContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldtempo; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldtempo(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldtempo(this);
    }
  }

  public final FieldtempoContext fieldtempo() throws RecognitionException {
    FieldtempoContext _localctx = new FieldtempoContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_fieldtempo);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(95);
      match(T__5);
      setState(96);
      tempo();
      setState(97);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldvoiceContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldvoiceContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldvoice; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldvoice(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldvoice(this);
    }
  }

  public final FieldvoiceContext fieldvoice() throws RecognitionException {
    FieldvoiceContext _localctx = new FieldvoiceContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_fieldvoice);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(99);
      match(T__6);
      setState(100);
      text();
      setState(101);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldkeyContext extends ParserRuleContext {
    public KeyContext key() {
      return getRuleContext(KeyContext.class,0);
    }
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public FieldkeyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldkey; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterFieldkey(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitFieldkey(this);
    }
  }

  public final FieldkeyContext fieldkey() throws RecognitionException {
    FieldkeyContext _localctx = new FieldkeyContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_fieldkey);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(103);
      match(T__7);
      setState(104);
      key();
      setState(105);
      endofline();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeyContext extends ParserRuleContext {
    public KeynoteContext keynote() {
      return getRuleContext(KeynoteContext.class,0);
    }
    public ModeminorContext modeminor() {
      return getRuleContext(ModeminorContext.class,0);
    }
    public KeyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_key; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterKey(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitKey(this);
    }
  }

  public final KeyContext key() throws RecognitionException {
    KeyContext _localctx = new KeyContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_key);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(107);
      keynote();
      setState(109);
      _la = _input.LA(1);
      if (_la==MCHAR) {
        {
        setState(108);
        modeminor();
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeynoteContext extends ParserRuleContext {
    public TerminalNode CCHAR() { return getToken(HeaderGrammarParser.CCHAR, 0); }
    public TerminalNode BASENOTE() { return getToken(HeaderGrammarParser.BASENOTE, 0); }
    public TerminalNode BCHAR() { return getToken(HeaderGrammarParser.BCHAR, 0); }
    public KeyaccidentalContext keyaccidental() {
      return getRuleContext(KeyaccidentalContext.class,0);
    }
    public KeynoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_keynote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterKeynote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitKeynote(this);
    }
  }

  public final KeynoteContext keynote() throws RecognitionException {
    KeynoteContext _localctx = new KeynoteContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_keynote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(111);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASENOTE) | (1L << BCHAR) | (1L << CCHAR))) != 0)) ) {
      _errHandler.recoverInline(this);
      } else {
        consume();
      }
      setState(113);
      _la = _input.LA(1);
      if (_la==T__8 || _la==BCHAR) {
        {
        setState(112);
        keyaccidental();
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeyaccidentalContext extends ParserRuleContext {
    public TerminalNode BCHAR() { return getToken(HeaderGrammarParser.BCHAR, 0); }
    public KeyaccidentalContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_keyaccidental; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterKeyaccidental(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitKeyaccidental(this);
    }
  }

  public final KeyaccidentalContext keyaccidental() throws RecognitionException {
    KeyaccidentalContext _localctx = new KeyaccidentalContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_keyaccidental);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(115);
      _la = _input.LA(1);
      if ( !(_la==T__8 || _la==BCHAR) ) {
      _errHandler.recoverInline(this);
      } else {
        consume();
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ModeminorContext extends ParserRuleContext {
    public TerminalNode MCHAR() { return getToken(HeaderGrammarParser.MCHAR, 0); }
    public ModeminorContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_modeminor; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterModeminor(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitModeminor(this);
    }
  }

  public final ModeminorContext modeminor() throws RecognitionException {
    ModeminorContext _localctx = new ModeminorContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_modeminor);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(117);
      match(MCHAR);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MeterContext extends ParserRuleContext {
    public TerminalNode CCHAR() { return getToken(HeaderGrammarParser.CCHAR, 0); }
    public MeterfractionContext meterfraction() {
      return getRuleContext(MeterfractionContext.class,0);
    }
    public MeterContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_meter; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterMeter(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitMeter(this);
    }
  }

  public final MeterContext meter() throws RecognitionException {
    MeterContext _localctx = new MeterContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_meter);
    try {
      setState(122);
      switch (_input.LA(1)) {
      case CCHAR:
        enterOuterAlt(_localctx, 1);
        {
        setState(119);
        match(CCHAR);
        }
        break;
      case T__9:
        enterOuterAlt(_localctx, 2);
        {
        setState(120);
        match(T__9);
        }
        break;
      case DIGIT:
        enterOuterAlt(_localctx, 3);
        {
        setState(121);
        meterfraction();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MeterfractionContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(HeaderGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(HeaderGrammarParser.DIGIT, i);
    }
    public MeterfractionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_meterfraction; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterMeterfraction(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitMeterfraction(this);
    }
  }

  public final MeterfractionContext meterfraction() throws RecognitionException {
    MeterfractionContext _localctx = new MeterfractionContext(_ctx, getState());
    enterRule(_localctx, 32, RULE_meterfraction);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(125); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(124);
        match(DIGIT);
        }
        }
        setState(127); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      setState(129);
      match(T__10);
      setState(131); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(130);
        match(DIGIT);
        }
        }
        setState(133); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class NotelengthstrictContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(HeaderGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(HeaderGrammarParser.DIGIT, i);
    }
    public NotelengthstrictContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_notelengthstrict; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterNotelengthstrict(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitNotelengthstrict(this);
    }
  }

  public final NotelengthstrictContext notelengthstrict() throws RecognitionException {
    NotelengthstrictContext _localctx = new NotelengthstrictContext(_ctx, getState());
    enterRule(_localctx, 34, RULE_notelengthstrict);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(136); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(135);
        match(DIGIT);
        }
        }
        setState(138); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      setState(140);
      match(T__10);
      setState(142); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(141);
        match(DIGIT);
        }
        }
        setState(144); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class TempoContext extends ParserRuleContext {
    public MeterfractionContext meterfraction() {
      return getRuleContext(MeterfractionContext.class,0);
    }
    public List<TerminalNode> DIGIT() { return getTokens(HeaderGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(HeaderGrammarParser.DIGIT, i);
    }
    public TempoContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_tempo; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterTempo(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitTempo(this);
    }
  }

  public final TempoContext tempo() throws RecognitionException {
    TempoContext _localctx = new TempoContext(_ctx, getState());
    enterRule(_localctx, 36, RULE_tempo);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(146);
      meterfraction();
      setState(147);
      match(T__11);
      setState(149); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(148);
        match(DIGIT);
        }
        }
        setState(151); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class TextContext extends ParserRuleContext {
    public List<TerminalNode> TEXTCHAR() { return getTokens(HeaderGrammarParser.TEXTCHAR); }
    public TerminalNode TEXTCHAR(int i) {
      return getToken(HeaderGrammarParser.TEXTCHAR, i);
    }
    public List<TerminalNode> DIGIT() { return getTokens(HeaderGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(HeaderGrammarParser.DIGIT, i);
    }
    public List<TerminalNode> BASENOTE() { return getTokens(HeaderGrammarParser.BASENOTE); }
    public TerminalNode BASENOTE(int i) {
      return getToken(HeaderGrammarParser.BASENOTE, i);
    }
    public List<TerminalNode> BCHAR() { return getTokens(HeaderGrammarParser.BCHAR); }
    public TerminalNode BCHAR(int i) {
      return getToken(HeaderGrammarParser.BCHAR, i);
    }
    public List<TerminalNode> CCHAR() { return getTokens(HeaderGrammarParser.CCHAR); }
    public TerminalNode CCHAR(int i) {
      return getToken(HeaderGrammarParser.CCHAR, i);
    }
    public List<TerminalNode> MCHAR() { return getTokens(HeaderGrammarParser.MCHAR); }
    public TerminalNode MCHAR(int i) {
      return getToken(HeaderGrammarParser.MCHAR, i);
    }
    public TextContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_text; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterText(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitText(this);
    }
  }

  public final TextContext text() throws RecognitionException {
    TextContext _localctx = new TextContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_text);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(154); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(153);
        _la = _input.LA(1);
        if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXTCHAR) | (1L << DIGIT) | (1L << BASENOTE) | (1L << BCHAR) | (1L << CCHAR) | (1L << MCHAR))) != 0)) ) {
        _errHandler.recoverInline(this);
        } else {
          consume();
        }
        }
        }
        setState(156); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXTCHAR) | (1L << DIGIT) | (1L << BASENOTE) | (1L << BCHAR) | (1L << CCHAR) | (1L << MCHAR))) != 0) );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class CommentContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public TerminalNode NEWLINE() { return getToken(HeaderGrammarParser.NEWLINE, 0); }
    public CommentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_comment; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterComment(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitComment(this);
    }
  }

  public final CommentContext comment() throws RecognitionException {
    CommentContext _localctx = new CommentContext(_ctx, getState());
    enterRule(_localctx, 40, RULE_comment);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(158);
      match(T__12);
      setState(159);
      text();
      setState(160);
      match(NEWLINE);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class EndoflineContext extends ParserRuleContext {
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public TerminalNode NEWLINE() { return getToken(HeaderGrammarParser.NEWLINE, 0); }
    public EndoflineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_endofline; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).enterEndofline(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof HeaderGrammarListener ) ((HeaderGrammarListener)listener).exitEndofline(this);
    }
  }

  public final EndoflineContext endofline() throws RecognitionException {
    EndoflineContext _localctx = new EndoflineContext(_ctx, getState());
    enterRule(_localctx, 42, RULE_endofline);
    try {
      setState(164);
      switch (_input.LA(1)) {
      case T__12:
        enterOuterAlt(_localctx, 1);
        {
        setState(162);
        comment();
        }
        break;
      case NEWLINE:
        enterOuterAlt(_localctx, 2);
        {
        setState(163);
        match(NEWLINE);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27\u00a9\4\2\t"+
      "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
      "\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
      "\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3"+
      "\2\3\2\3\3\3\3\7\3\64\n\3\f\3\16\3\67\13\3\3\3\3\3\7\3;\n\3\f\3\16"+
      "\3>\13\3\3\3\3\3\3\4\3\4\6\4D\n\4\r\4\16\4E\3\4\3\4\3\5\3\5\3\5\3"+
      "\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6T\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
      "\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
      "\3\f\3\r\3\r\5\rp\n\r\3\16\3\16\5\16t\n\16\3\17\3\17\3\20\3\20\3\21"+
      "\3\21\3\21\5\21}\n\21\3\22\6\22\u0080\n\22\r\22\16\22\u0081\3\22\3"+
      "\22\6\22\u0086\n\22\r\22\16\22\u0087\3\23\6\23\u008b\n\23\r\23\16"+
      "\23\u008c\3\23\3\23\6\23\u0091\n\23\r\23\16\23\u0092\3\24\3\24\3\24"+
      "\6\24\u0098\n\24\r\24\16\24\u0099\3\25\6\25\u009d\n\25\r\25\16\25"+
      "\u009e\3\26\3\26\3\26\3\26\3\27\3\27\5\27\u00a7\n\27\3\27\2\2\30\2"+
      "\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\5\4\2\22\22\24\25"+
      "\4\2\13\13\24\24\4\2\20\22\24\26\u00a5\2.\3\2\2\2\4\61\3\2\2\2\6A"+
      "\3\2\2\2\bI\3\2\2\2\nS\3\2\2\2\fU\3\2\2\2\16Y\3\2\2\2\20]\3\2\2\2"+
      "\22a\3\2\2\2\24e\3\2\2\2\26i\3\2\2\2\30m\3\2\2\2\32q\3\2\2\2\34u\3"+
      "\2\2\2\36w\3\2\2\2 |\3\2\2\2\"\177\3\2\2\2$\u008a\3\2\2\2&\u0094\3"+
      "\2\2\2(\u009c\3\2\2\2*\u00a0\3\2\2\2,\u00a6\3\2\2\2./\5\4\3\2/\60"+
      "\7\2\2\3\60\3\3\2\2\2\61\65\5\6\4\2\62\64\5*\26\2\63\62\3\2\2\2\64"+
      "\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\2"+
      "8<\5\b\5\29;\5\n\6\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2"+
      "\2\2><\3\2\2\2?@\5\26\f\2@\5\3\2\2\2AC\7\3\2\2BD\7\21\2\2CB\3\2\2"+
      "\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5,\27\2H\7\3\2\2\2IJ"+
      "\7\4\2\2JK\5(\25\2KL\5,\27\2L\t\3\2\2\2MT\5\f\7\2NT\5\16\b\2OT\5\20"+
      "\t\2PT\5\22\n\2QT\5\24\13\2RT\5*\26\2SM\3\2\2\2SN\3\2\2\2SO\3\2\2"+
      "\2SP\3\2\2\2SQ\3\2\2\2SR\3\2\2\2T\13\3\2\2\2UV\7\5\2\2VW\5(\25\2W"+
      "X\5,\27\2X\r\3\2\2\2YZ\7\6\2\2Z[\5$\23\2[\\\5,\27\2\\\17\3\2\2\2]"+
      "^\7\7\2\2^_\5 \21\2_`\5,\27\2`\21\3\2\2\2ab\7\b\2\2bc\5&\24\2cd\5"+
      ",\27\2d\23\3\2\2\2ef\7\t\2\2fg\5(\25\2gh\5,\27\2h\25\3\2\2\2ij\7\n"+
      "\2\2jk\5\30\r\2kl\5,\27\2l\27\3\2\2\2mo\5\32\16\2np\5\36\20\2on\3"+
      "\2\2\2op\3\2\2\2p\31\3\2\2\2qs\t\2\2\2rt\5\34\17\2sr\3\2\2\2st\3\2"+
      "\2\2t\33\3\2\2\2uv\t\3\2\2v\35\3\2\2\2wx\7\26\2\2x\37\3\2\2\2y}\7"+
      "\25\2\2z}\7\f\2\2{}\5\"\22\2|y\3\2\2\2|z\3\2\2\2|{\3\2\2\2}!\3\2\2"+
      "\2~\u0080\7\21\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2"+
      "\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\7\r\2\2\u0084"+
      "\u0086\7\21\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085"+
      "\3\2\2\2\u0087\u0088\3\2\2\2\u0088#\3\2\2\2\u0089\u008b\7\21\2\2\u008a"+
      "\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d"+
      "\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\7\r\2\2\u008f\u0091\7\21"+
      "\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092"+
      "\u0093\3\2\2\2\u0093%\3\2\2\2\u0094\u0095\5\"\22\2\u0095\u0097\7\16"+
      "\2\2\u0096\u0098\7\21\2\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2"+
      "\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\'\3\2\2\2\u009b\u009d"+
      "\t\4\2\2\u009c\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2"+
      "\2\u009e\u009f\3\2\2\2\u009f)\3\2\2\2\u00a0\u00a1\7\17\2\2\u00a1\u00a2"+
      "\5(\25\2\u00a2\u00a3\7\23\2\2\u00a3+\3\2\2\2\u00a4\u00a7\5*\26\2\u00a5"+
      "\u00a7\7\23\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7-\3\2"+
      "\2\2\20\65<ESos|\u0081\u0087\u008c\u0092\u0099\u009e\u00a6";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}