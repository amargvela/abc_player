// Generated from MusicGrammar.g4 by ANTLR 4.5.1

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
public class MusicGrammarParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, 
    T__16=17, T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, 
    T__23=24, T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, BASENOTE=30, 
    TEXTCHAR=31, DIGIT=32, NEWLINE=33, WHITESPACE=34;
  public static final int
    RULE_root = 0, RULE_abcmusic = 1, RULE_abcline = 2, RULE_element = 3, 
    RULE_noteelement = 4, RULE_note = 5, RULE_noteorrest = 6, RULE_pitch = 7, 
    RULE_octave = 8, RULE_highoctave = 9, RULE_lowoctave = 10, RULE_notelength = 11, 
    RULE_accidental = 12, RULE_rest = 13, RULE_tupletelement = 14, RULE_tupletspec = 15, 
    RULE_multinote = 16, RULE_barline = 17, RULE_nthrepeat = 18, RULE_midtunefield = 19, 
    RULE_fieldvoice = 20, RULE_comment = 21, RULE_endofline = 22, RULE_text = 23;
  public static final String[] ruleNames = {
    "root", "abcmusic", "abcline", "element", "noteelement", "note", "noteorrest", 
    "pitch", "octave", "highoctave", "lowoctave", "notelength", "accidental", 
    "rest", "tupletelement", "tupletspec", "multinote", "barline", "nthrepeat", 
    "midtunefield", "fieldvoice", "comment", "endofline", "text"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'''", "','", "'/'", "'^'", "'^^'", "'_'", "'__'", "'='", "'z'", 
    "'('", "'['", "']'", "'|'", "'||'", "'[|'", "'|]'", "':|'", "'|:'", 
    "'[1'", "'[2'", "'[3'", "'[4'", "'[5'", "'[6'", "'[7'", "'[8'", "'[9'", 
    "'V:'", "'%'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, null, "BASENOTE", "TEXTCHAR", "DIGIT", 
    "NEWLINE", "WHITESPACE"
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
  public String getGrammarFileName() { return "MusicGrammar.g4"; }

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

  public MusicGrammarParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public AbcmusicContext abcmusic() {
      return getRuleContext(AbcmusicContext.class,0);
    }
    public TerminalNode EOF() { return getToken(MusicGrammarParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(48);
      abcmusic();
      setState(49);
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

  public static class AbcmusicContext extends ParserRuleContext {
    public List<AbclineContext> abcline() {
      return getRuleContexts(AbclineContext.class);
    }
    public AbclineContext abcline(int i) {
      return getRuleContext(AbclineContext.class,i);
    }
    public AbcmusicContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_abcmusic; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterAbcmusic(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitAbcmusic(this);
    }
  }

  public final AbcmusicContext abcmusic() throws RecognitionException {
    AbcmusicContext _localctx = new AbcmusicContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_abcmusic);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(52); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(51);
        abcline();
        }
        }
        setState(54); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << BASENOTE) | (1L << WHITESPACE))) != 0) );
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

  public static class AbclineContext extends ParserRuleContext {
    public TerminalNode NEWLINE() { return getToken(MusicGrammarParser.NEWLINE, 0); }
    public List<ElementContext> element() {
      return getRuleContexts(ElementContext.class);
    }
    public ElementContext element(int i) {
      return getRuleContext(ElementContext.class,i);
    }
    public MidtunefieldContext midtunefield() {
      return getRuleContext(MidtunefieldContext.class,0);
    }
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public AbclineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_abcline; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterAbcline(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitAbcline(this);
    }
  }

  public final AbclineContext abcline() throws RecognitionException {
    AbclineContext _localctx = new AbclineContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_abcline);
    int _la;
    try {
      setState(65);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__9:
      case T__10:
      case T__12:
      case T__13:
      case T__14:
      case T__15:
      case T__16:
      case T__17:
      case T__18:
      case T__19:
      case T__20:
      case T__21:
      case T__22:
      case T__23:
      case T__24:
      case T__25:
      case T__26:
      case BASENOTE:
      case WHITESPACE:
        enterOuterAlt(_localctx, 1);
        {
        setState(57); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(56);
          element();
          }
          }
          setState(59); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << BASENOTE) | (1L << WHITESPACE))) != 0) );
        setState(61);
        match(NEWLINE);
        }
        break;
      case T__27:
        enterOuterAlt(_localctx, 2);
        {
        setState(63);
        midtunefield();
        }
        break;
      case T__28:
        enterOuterAlt(_localctx, 3);
        {
        setState(64);
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

  public static class ElementContext extends ParserRuleContext {
    public NoteelementContext noteelement() {
      return getRuleContext(NoteelementContext.class,0);
    }
    public TupletelementContext tupletelement() {
      return getRuleContext(TupletelementContext.class,0);
    }
    public BarlineContext barline() {
      return getRuleContext(BarlineContext.class,0);
    }
    public NthrepeatContext nthrepeat() {
      return getRuleContext(NthrepeatContext.class,0);
    }
    public TerminalNode WHITESPACE() { return getToken(MusicGrammarParser.WHITESPACE, 0); }
    public ElementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_element; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterElement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitElement(this);
    }
  }

  public final ElementContext element() throws RecognitionException {
    ElementContext _localctx = new ElementContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_element);
    try {
      setState(72);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__10:
      case BASENOTE:
        enterOuterAlt(_localctx, 1);
        {
        setState(67);
        noteelement();
        }
        break;
      case T__9:
        enterOuterAlt(_localctx, 2);
        {
        setState(68);
        tupletelement();
        }
        break;
      case T__12:
      case T__13:
      case T__14:
      case T__15:
      case T__16:
      case T__17:
        enterOuterAlt(_localctx, 3);
        {
        setState(69);
        barline();
        }
        break;
      case T__18:
      case T__19:
      case T__20:
      case T__21:
      case T__22:
      case T__23:
      case T__24:
      case T__25:
      case T__26:
        enterOuterAlt(_localctx, 4);
        {
        setState(70);
        nthrepeat();
        }
        break;
      case WHITESPACE:
        enterOuterAlt(_localctx, 5);
        {
        setState(71);
        match(WHITESPACE);
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

  public static class NoteelementContext extends ParserRuleContext {
    public NoteContext note() {
      return getRuleContext(NoteContext.class,0);
    }
    public MultinoteContext multinote() {
      return getRuleContext(MultinoteContext.class,0);
    }
    public NoteelementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteelement; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterNoteelement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitNoteelement(this);
    }
  }

  public final NoteelementContext noteelement() throws RecognitionException {
    NoteelementContext _localctx = new NoteelementContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_noteelement);
    try {
      setState(76);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case BASENOTE:
        enterOuterAlt(_localctx, 1);
        {
        setState(74);
        note();
        }
        break;
      case T__10:
        enterOuterAlt(_localctx, 2);
        {
        setState(75);
        multinote();
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

  public static class NoteContext extends ParserRuleContext {
    public NoteorrestContext noteorrest() {
      return getRuleContext(NoteorrestContext.class,0);
    }
    public NotelengthContext notelength() {
      return getRuleContext(NotelengthContext.class,0);
    }
    public NoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_note; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterNote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitNote(this);
    }
  }

  public final NoteContext note() throws RecognitionException {
    NoteContext _localctx = new NoteContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_note);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(78);
      noteorrest();
      setState(80);
      _la = _input.LA(1);
      if (_la==T__2 || _la==DIGIT) {
        {
        setState(79);
        notelength();
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

  public static class NoteorrestContext extends ParserRuleContext {
    public PitchContext pitch() {
      return getRuleContext(PitchContext.class,0);
    }
    public RestContext rest() {
      return getRuleContext(RestContext.class,0);
    }
    public NoteorrestContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteorrest; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterNoteorrest(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitNoteorrest(this);
    }
  }

  public final NoteorrestContext noteorrest() throws RecognitionException {
    NoteorrestContext _localctx = new NoteorrestContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_noteorrest);
    try {
      setState(84);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case BASENOTE:
        enterOuterAlt(_localctx, 1);
        {
        setState(82);
        pitch();
        }
        break;
      case T__8:
        enterOuterAlt(_localctx, 2);
        {
        setState(83);
        rest();
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

  public static class PitchContext extends ParserRuleContext {
    public TerminalNode BASENOTE() { return getToken(MusicGrammarParser.BASENOTE, 0); }
    public AccidentalContext accidental() {
      return getRuleContext(AccidentalContext.class,0);
    }
    public OctaveContext octave() {
      return getRuleContext(OctaveContext.class,0);
    }
    public PitchContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_pitch; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterPitch(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitPitch(this);
    }
  }

  public final PitchContext pitch() throws RecognitionException {
    PitchContext _localctx = new PitchContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_pitch);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(87);
      _la = _input.LA(1);
      if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) {
        {
        setState(86);
        accidental();
        }
      }

      setState(89);
      match(BASENOTE);
      setState(91);
      _la = _input.LA(1);
      if (_la==T__0 || _la==T__1) {
        {
        setState(90);
        octave();
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

  public static class OctaveContext extends ParserRuleContext {
    public HighoctaveContext highoctave() {
      return getRuleContext(HighoctaveContext.class,0);
    }
    public LowoctaveContext lowoctave() {
      return getRuleContext(LowoctaveContext.class,0);
    }
    public OctaveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_octave; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterOctave(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitOctave(this);
    }
  }

  public final OctaveContext octave() throws RecognitionException {
    OctaveContext _localctx = new OctaveContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_octave);
    try {
      setState(95);
      switch (_input.LA(1)) {
      case T__0:
        enterOuterAlt(_localctx, 1);
        {
        setState(93);
        highoctave();
        }
        break;
      case T__1:
        enterOuterAlt(_localctx, 2);
        {
        setState(94);
        lowoctave();
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

  public static class HighoctaveContext extends ParserRuleContext {
    public HighoctaveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_highoctave; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterHighoctave(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitHighoctave(this);
    }
  }

  public final HighoctaveContext highoctave() throws RecognitionException {
    HighoctaveContext _localctx = new HighoctaveContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_highoctave);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(98); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(97);
        match(T__0);
        }
        }
        setState(100); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==T__0 );
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

  public static class LowoctaveContext extends ParserRuleContext {
    public LowoctaveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_lowoctave; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterLowoctave(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitLowoctave(this);
    }
  }

  public final LowoctaveContext lowoctave() throws RecognitionException {
    LowoctaveContext _localctx = new LowoctaveContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_lowoctave);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(103); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(102);
        match(T__1);
        }
        }
        setState(105); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==T__1 );
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

  public static class NotelengthContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(MusicGrammarParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(MusicGrammarParser.DIGIT, i);
    }
    public NotelengthContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_notelength; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterNotelength(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitNotelength(this);
    }
  }

  public final NotelengthContext notelength() throws RecognitionException {
    NotelengthContext _localctx = new NotelengthContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_notelength);
    int _la;
    try {
      setState(136);
      switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        {
        setState(108); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(107);
          match(DIGIT);
          }
          }
          setState(110); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        setState(112);
        match(T__2);
        }
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        {
        setState(114); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(113);
          match(DIGIT);
          }
          }
          setState(116); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        setState(118);
        match(T__2);
        setState(120); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(119);
          match(DIGIT);
          }
          }
          setState(122); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        }
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        {
        setState(124);
        match(T__2);
        setState(126); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(125);
          match(DIGIT);
          }
          }
          setState(128); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        }
        }
        break;
      case 4:
        enterOuterAlt(_localctx, 4);
        {
        {
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
        break;
      case 5:
        enterOuterAlt(_localctx, 5);
        {
        setState(135);
        match(T__2);
        }
        break;
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

  public static class AccidentalContext extends ParserRuleContext {
    public AccidentalContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_accidental; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterAccidental(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitAccidental(this);
    }
  }

  public final AccidentalContext accidental() throws RecognitionException {
    AccidentalContext _localctx = new AccidentalContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_accidental);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(138);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
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

  public static class RestContext extends ParserRuleContext {
    public RestContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_rest; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterRest(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitRest(this);
    }
  }

  public final RestContext rest() throws RecognitionException {
    RestContext _localctx = new RestContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_rest);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(140);
      match(T__8);
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

  public static class TupletelementContext extends ParserRuleContext {
    public TupletspecContext tupletspec() {
      return getRuleContext(TupletspecContext.class,0);
    }
    public List<NoteelementContext> noteelement() {
      return getRuleContexts(NoteelementContext.class);
    }
    public NoteelementContext noteelement(int i) {
      return getRuleContext(NoteelementContext.class,i);
    }
    public TupletelementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_tupletelement; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterTupletelement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitTupletelement(this);
    }
  }

  public final TupletelementContext tupletelement() throws RecognitionException {
    TupletelementContext _localctx = new TupletelementContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_tupletelement);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
      setState(142);
      tupletspec();
      setState(144); 
      _errHandler.sync(this);
      _alt = 1;
      do {
        switch (_alt) {
        case 1:
          {
          {
          setState(143);
          noteelement();
          }
          }
          break;
        default:
          throw new NoViableAltException(this);
        }
        setState(146); 
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input,18,_ctx);
      } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

  public static class TupletspecContext extends ParserRuleContext {
    public TerminalNode DIGIT() { return getToken(MusicGrammarParser.DIGIT, 0); }
    public TupletspecContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_tupletspec; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterTupletspec(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitTupletspec(this);
    }
  }

  public final TupletspecContext tupletspec() throws RecognitionException {
    TupletspecContext _localctx = new TupletspecContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_tupletspec);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(148);
      match(T__9);
      setState(149);
      match(DIGIT);
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

  public static class MultinoteContext extends ParserRuleContext {
    public List<NoteContext> note() {
      return getRuleContexts(NoteContext.class);
    }
    public NoteContext note(int i) {
      return getRuleContext(NoteContext.class,i);
    }
    public MultinoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_multinote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterMultinote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitMultinote(this);
    }
  }

  public final MultinoteContext multinote() throws RecognitionException {
    MultinoteContext _localctx = new MultinoteContext(_ctx, getState());
    enterRule(_localctx, 32, RULE_multinote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(151);
      match(T__10);
      setState(153); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(152);
        note();
        }
        }
        setState(155); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << BASENOTE))) != 0) );
      setState(157);
      match(T__11);
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

  public static class BarlineContext extends ParserRuleContext {
    public BarlineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_barline; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterBarline(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitBarline(this);
    }
  }

  public final BarlineContext barline() throws RecognitionException {
    BarlineContext _localctx = new BarlineContext(_ctx, getState());
    enterRule(_localctx, 34, RULE_barline);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(159);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17))) != 0)) ) {
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

  public static class NthrepeatContext extends ParserRuleContext {
    public NthrepeatContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_nthrepeat; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterNthrepeat(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitNthrepeat(this);
    }
  }

  public final NthrepeatContext nthrepeat() throws RecognitionException {
    NthrepeatContext _localctx = new NthrepeatContext(_ctx, getState());
    enterRule(_localctx, 36, RULE_nthrepeat);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(161);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
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

  public static class MidtunefieldContext extends ParserRuleContext {
    public FieldvoiceContext fieldvoice() {
      return getRuleContext(FieldvoiceContext.class,0);
    }
    public MidtunefieldContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_midtunefield; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterMidtunefield(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitMidtunefield(this);
    }
  }

  public final MidtunefieldContext midtunefield() throws RecognitionException {
    MidtunefieldContext _localctx = new MidtunefieldContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_midtunefield);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(163);
      fieldvoice();
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
    public EndoflineContext endofline() {
      return getRuleContext(EndoflineContext.class,0);
    }
    public List<TerminalNode> WHITESPACE() { return getTokens(MusicGrammarParser.WHITESPACE); }
    public TerminalNode WHITESPACE(int i) {
      return getToken(MusicGrammarParser.WHITESPACE, i);
    }
    public List<TextContext> text() {
      return getRuleContexts(TextContext.class);
    }
    public TextContext text(int i) {
      return getRuleContext(TextContext.class,i);
    }
    public FieldvoiceContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldvoice; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterFieldvoice(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitFieldvoice(this);
    }
  }

  public final FieldvoiceContext fieldvoice() throws RecognitionException {
    FieldvoiceContext _localctx = new FieldvoiceContext(_ctx, getState());
    enterRule(_localctx, 40, RULE_fieldvoice);
    int _la;
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
      setState(165);
      match(T__27);
      setState(169);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==WHITESPACE) {
        {
        {
        setState(166);
        match(WHITESPACE);
        }
        }
        setState(171);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(185);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASENOTE) | (1L << TEXTCHAR) | (1L << DIGIT))) != 0)) {
        {
        {
        setState(173); 
        _errHandler.sync(this);
        _alt = 1;
        do {
          switch (_alt) {
          case 1:
            {
            {
            setState(172);
            text();
            }
            }
            break;
          default:
            throw new NoViableAltException(this);
          }
          setState(175); 
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input,21,_ctx);
        } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
        setState(180);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la==WHITESPACE) {
          {
          {
          setState(177);
          match(WHITESPACE);
          }
          }
          setState(182);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        }
        }
        setState(187);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(188);
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

  public static class CommentContext extends ParserRuleContext {
    public TerminalNode NEWLINE() { return getToken(MusicGrammarParser.NEWLINE, 0); }
    public List<TextContext> text() {
      return getRuleContexts(TextContext.class);
    }
    public TextContext text(int i) {
      return getRuleContext(TextContext.class,i);
    }
    public CommentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_comment; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterComment(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitComment(this);
    }
  }

  public final CommentContext comment() throws RecognitionException {
    CommentContext _localctx = new CommentContext(_ctx, getState());
    enterRule(_localctx, 42, RULE_comment);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(190);
      match(T__28);
      setState(194);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASENOTE) | (1L << TEXTCHAR) | (1L << DIGIT))) != 0)) {
        {
        {
        setState(191);
        text();
        }
        }
        setState(196);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(197);
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
    public TerminalNode NEWLINE() { return getToken(MusicGrammarParser.NEWLINE, 0); }
    public EndoflineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_endofline; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterEndofline(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitEndofline(this);
    }
  }

  public final EndoflineContext endofline() throws RecognitionException {
    EndoflineContext _localctx = new EndoflineContext(_ctx, getState());
    enterRule(_localctx, 44, RULE_endofline);
    try {
      setState(201);
      switch (_input.LA(1)) {
      case T__28:
        enterOuterAlt(_localctx, 1);
        {
        setState(199);
        comment();
        }
        break;
      case NEWLINE:
        enterOuterAlt(_localctx, 2);
        {
        setState(200);
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

  public static class TextContext extends ParserRuleContext {
    public TerminalNode TEXTCHAR() { return getToken(MusicGrammarParser.TEXTCHAR, 0); }
    public TerminalNode BASENOTE() { return getToken(MusicGrammarParser.BASENOTE, 0); }
    public TerminalNode DIGIT() { return getToken(MusicGrammarParser.DIGIT, 0); }
    public TextContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_text; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).enterText(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof MusicGrammarListener ) ((MusicGrammarListener)listener).exitText(this);
    }
  }

  public final TextContext text() throws RecognitionException {
    TextContext _localctx = new TextContext(_ctx, getState());
    enterRule(_localctx, 46, RULE_text);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(203);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASENOTE) | (1L << TEXTCHAR) | (1L << DIGIT))) != 0)) ) {
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

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u00d0\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
      "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
      "\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
      "\30\4\31\t\31\3\2\3\2\3\2\3\3\6\3\67\n\3\r\3\16\38\3\4\6\4<\n\4\r"+
      "\4\16\4=\3\4\3\4\3\4\3\4\5\4D\n\4\3\5\3\5\3\5\3\5\3\5\5\5K\n\5\3\6"+
      "\3\6\5\6O\n\6\3\7\3\7\5\7S\n\7\3\b\3\b\5\bW\n\b\3\t\5\tZ\n\t\3\t\3"+
      "\t\5\t^\n\t\3\n\3\n\5\nb\n\n\3\13\6\13e\n\13\r\13\16\13f\3\f\6\fj"+
      "\n\f\r\f\16\fk\3\r\6\ro\n\r\r\r\16\rp\3\r\3\r\6\ru\n\r\r\r\16\rv\3"+
      "\r\3\r\6\r{\n\r\r\r\16\r|\3\r\3\r\6\r\u0081\n\r\r\r\16\r\u0082\3\r"+
      "\6\r\u0086\n\r\r\r\16\r\u0087\3\r\5\r\u008b\n\r\3\16\3\16\3\17\3\17"+
      "\3\20\3\20\6\20\u0093\n\20\r\20\16\20\u0094\3\21\3\21\3\21\3\22\3"+
      "\22\6\22\u009c\n\22\r\22\16\22\u009d\3\22\3\22\3\23\3\23\3\24\3\24"+
      "\3\25\3\25\3\26\3\26\7\26\u00aa\n\26\f\26\16\26\u00ad\13\26\3\26\6"+
      "\26\u00b0\n\26\r\26\16\26\u00b1\3\26\7\26\u00b5\n\26\f\26\16\26\u00b8"+
      "\13\26\7\26\u00ba\n\26\f\26\16\26\u00bd\13\26\3\26\3\26\3\27\3\27"+
      "\7\27\u00c3\n\27\f\27\16\27\u00c6\13\27\3\27\3\27\3\30\3\30\5\30\u00cc"+
      "\n\30\3\31\3\31\3\31\2\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
      " \"$&(*,.\60\2\6\3\2\6\n\3\2\17\24\3\2\25\35\3\2 \"\u00d8\2\62\3\2"+
      "\2\2\4\66\3\2\2\2\6C\3\2\2\2\bJ\3\2\2\2\nN\3\2\2\2\fP\3\2\2\2\16V"+
      "\3\2\2\2\20Y\3\2\2\2\22a\3\2\2\2\24d\3\2\2\2\26i\3\2\2\2\30\u008a"+
      "\3\2\2\2\32\u008c\3\2\2\2\34\u008e\3\2\2\2\36\u0090\3\2\2\2 \u0096"+
      "\3\2\2\2\"\u0099\3\2\2\2$\u00a1\3\2\2\2&\u00a3\3\2\2\2(\u00a5\3\2"+
      "\2\2*\u00a7\3\2\2\2,\u00c0\3\2\2\2.\u00cb\3\2\2\2\60\u00cd\3\2\2\2"+
      "\62\63\5\4\3\2\63\64\7\2\2\3\64\3\3\2\2\2\65\67\5\6\4\2\66\65\3\2"+
      "\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29\5\3\2\2\2:<\5\b\5\2;:\3\2"+
      "\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\7#\2\2@D\3\2\2\2AD"+
      "\5(\25\2BD\5,\27\2C;\3\2\2\2CA\3\2\2\2CB\3\2\2\2D\7\3\2\2\2EK\5\n"+
      "\6\2FK\5\36\20\2GK\5$\23\2HK\5&\24\2IK\7$\2\2JE\3\2\2\2JF\3\2\2\2"+
      "JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\t\3\2\2\2LO\5\f\7\2MO\5\"\22\2NL\3"+
      "\2\2\2NM\3\2\2\2O\13\3\2\2\2PR\5\16\b\2QS\5\30\r\2RQ\3\2\2\2RS\3\2"+
      "\2\2S\r\3\2\2\2TW\5\20\t\2UW\5\34\17\2VT\3\2\2\2VU\3\2\2\2W\17\3\2"+
      "\2\2XZ\5\32\16\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[]\7 \2\2\\^\5\22\n"+
      "\2]\\\3\2\2\2]^\3\2\2\2^\21\3\2\2\2_b\5\24\13\2`b\5\26\f\2a_\3\2\2"+
      "\2a`\3\2\2\2b\23\3\2\2\2ce\7\3\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2f"+
      "g\3\2\2\2g\25\3\2\2\2hj\7\4\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3"+
      "\2\2\2l\27\3\2\2\2mo\7\"\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2"+
      "\2qr\3\2\2\2r\u008b\7\5\2\2su\7\"\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2"+
      "\2vw\3\2\2\2wx\3\2\2\2xz\7\5\2\2y{\7\"\2\2zy\3\2\2\2{|\3\2\2\2|z\3"+
      "\2\2\2|}\3\2\2\2}\u008b\3\2\2\2~\u0080\7\5\2\2\177\u0081\7\"\2\2\u0080"+
      "\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3"+
      "\2\2\2\u0083\u008b\3\2\2\2\u0084\u0086\7\"\2\2\u0085\u0084\3\2\2\2"+
      "\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
      "\u008b\3\2\2\2\u0089\u008b\7\5\2\2\u008an\3\2\2\2\u008at\3\2\2\2\u008a"+
      "~\3\2\2\2\u008a\u0085\3\2\2\2\u008a\u0089\3\2\2\2\u008b\31\3\2\2\2"+
      "\u008c\u008d\t\2\2\2\u008d\33\3\2\2\2\u008e\u008f\7\13\2\2\u008f\35"+
      "\3\2\2\2\u0090\u0092\5 \21\2\u0091\u0093\5\n\6\2\u0092\u0091\3\2\2"+
      "\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
      "\37\3\2\2\2\u0096\u0097\7\f\2\2\u0097\u0098\7\"\2\2\u0098!\3\2\2\2"+
      "\u0099\u009b\7\r\2\2\u009a\u009c\5\f\7\2\u009b\u009a\3\2\2\2\u009c"+
      "\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f"+
      "\3\2\2\2\u009f\u00a0\7\16\2\2\u00a0#\3\2\2\2\u00a1\u00a2\t\3\2\2\u00a2"+
      "%\3\2\2\2\u00a3\u00a4\t\4\2\2\u00a4\'\3\2\2\2\u00a5\u00a6\5*\26\2"+
      "\u00a6)\3\2\2\2\u00a7\u00ab\7\36\2\2\u00a8\u00aa\7$\2\2\u00a9\u00a8"+
      "\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2"+
      "\2\u00ac\u00bb\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b0\5\60\31\2\u00af"+
      "\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2"+
      "\3\2\2\2\u00b2\u00b6\3\2\2\2\u00b3\u00b5\7$\2\2\u00b4\u00b3\3\2\2"+
      "\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
      "\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00af\3\2\2\2\u00ba\u00bd"+
      "\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2"+
      "\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\5.\30\2\u00bf+\3\2\2\2\u00c0\u00c4"+
      "\7\37\2\2\u00c1\u00c3\5\60\31\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3"+
      "\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2"+
      "\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7#\2\2\u00c8-\3\2\2\2\u00c9\u00cc"+
      "\5,\27\2\u00ca\u00cc\7#\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2"+
      "\2\u00cc/\3\2\2\2\u00cd\u00ce\t\5\2\2\u00ce\61\3\2\2\2\348=CJNRVY"+
      "]afkpv|\u0082\u0087\u008a\u0094\u009d\u00ab\u00b1\u00b6\u00bb\u00c4"+
      "\u00cb";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}