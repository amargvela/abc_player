// Generated from MusicGrammar.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MusicGrammarLexer extends Lexer {
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
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
    "T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
    "T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
    "T__25", "T__26", "T__27", "T__28", "BASENOTE", "TEXTCHAR", "DIGIT", 
    "NEWLINE", "WHITESPACE"
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


  public MusicGrammarLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "MusicGrammar.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00a1\b\1\4\2"+
      "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
      "\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
      "\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4"+
      "\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
      "\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\3\2\3\2\3\3\3\3\3\4\3\4\3"+
      "\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
      "\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
      "\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3"+
      "\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3"+
      "\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3"+
      "\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\5\"\u009c\n\"\5\"\u009e\n\"\3#\3"+
      "#\2\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
      "\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
      "\67\359\36;\37= ?!A\"C#E$\3\2\5\4\2CIci\t\2\13\f\17\17\"\"\'\'\62"+
      ";CIci\4\2\13\13\"\"\u00a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
      "\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
      "\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
      "\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2"+
      ")\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
      "\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
      "\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5I\3\2\2\2\7K\3"+
      "\2\2\2\tM\3\2\2\2\13O\3\2\2\2\rR\3\2\2\2\17T\3\2\2\2\21W\3\2\2\2\23"+
      "Y\3\2\2\2\25[\3\2\2\2\27]\3\2\2\2\31_\3\2\2\2\33a\3\2\2\2\35c\3\2"+
      "\2\2\37f\3\2\2\2!i\3\2\2\2#l\3\2\2\2%o\3\2\2\2\'r\3\2\2\2)u\3\2\2"+
      "\2+x\3\2\2\2-{\3\2\2\2/~\3\2\2\2\61\u0081\3\2\2\2\63\u0084\3\2\2\2"+
      "\65\u0087\3\2\2\2\67\u008a\3\2\2\29\u008d\3\2\2\2;\u0090\3\2\2\2="+
      "\u0092\3\2\2\2?\u0094\3\2\2\2A\u0096\3\2\2\2C\u009d\3\2\2\2E\u009f"+
      "\3\2\2\2GH\7)\2\2H\4\3\2\2\2IJ\7.\2\2J\6\3\2\2\2KL\7\61\2\2L\b\3\2"+
      "\2\2MN\7`\2\2N\n\3\2\2\2OP\7`\2\2PQ\7`\2\2Q\f\3\2\2\2RS\7a\2\2S\16"+
      "\3\2\2\2TU\7a\2\2UV\7a\2\2V\20\3\2\2\2WX\7?\2\2X\22\3\2\2\2YZ\7|\2"+
      "\2Z\24\3\2\2\2[\\\7*\2\2\\\26\3\2\2\2]^\7]\2\2^\30\3\2\2\2_`\7_\2"+
      "\2`\32\3\2\2\2ab\7~\2\2b\34\3\2\2\2cd\7~\2\2de\7~\2\2e\36\3\2\2\2"+
      "fg\7]\2\2gh\7~\2\2h \3\2\2\2ij\7~\2\2jk\7_\2\2k\"\3\2\2\2lm\7<\2\2"+
      "mn\7~\2\2n$\3\2\2\2op\7~\2\2pq\7<\2\2q&\3\2\2\2rs\7]\2\2st\7\63\2"+
      "\2t(\3\2\2\2uv\7]\2\2vw\7\64\2\2w*\3\2\2\2xy\7]\2\2yz\7\65\2\2z,\3"+
      "\2\2\2{|\7]\2\2|}\7\66\2\2}.\3\2\2\2~\177\7]\2\2\177\u0080\7\67\2"+
      "\2\u0080\60\3\2\2\2\u0081\u0082\7]\2\2\u0082\u0083\78\2\2\u0083\62"+
      "\3\2\2\2\u0084\u0085\7]\2\2\u0085\u0086\79\2\2\u0086\64\3\2\2\2\u0087"+
      "\u0088\7]\2\2\u0088\u0089\7:\2\2\u0089\66\3\2\2\2\u008a\u008b\7]\2"+
      "\2\u008b\u008c\7;\2\2\u008c8\3\2\2\2\u008d\u008e\7X\2\2\u008e\u008f"+
      "\7<\2\2\u008f:\3\2\2\2\u0090\u0091\7\'\2\2\u0091<\3\2\2\2\u0092\u0093"+
      "\t\2\2\2\u0093>\3\2\2\2\u0094\u0095\n\3\2\2\u0095@\3\2\2\2\u0096\u0097"+
      "\4\62;\2\u0097B\3\2\2\2\u0098\u009e\7\f\2\2\u0099\u009b\7\17\2\2\u009a"+
      "\u009c\7\f\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e"+
      "\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u0099\3\2\2\2\u009eD\3\2\2\2\u009f"+
      "\u00a0\t\4\2\2\u00a0F\3\2\2\2\5\2\u009b\u009d\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}