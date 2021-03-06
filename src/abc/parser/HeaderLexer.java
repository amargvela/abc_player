// Generated from Abc.g4 by ANTLR 4.5.1

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
public class HeaderLexer extends Lexer {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, 
    TEXTCHAR=17, DIGIT=18, BASENOTE=19, NEWLINE=20, WHITESPACE=21, SPACES=22;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
    "T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "TEXTCHAR", 
    "DIGIT", "BASENOTE", "NEWLINE", "WHITESPACE", "SPACES"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'X: '", "'T: '", "'C: '", "'L: '", "'M: '", "'Q: '", "'V: '", 
    "'K: '", "'#'", "'b'", "'m'", "'C'", "'C|'", "'/'", "'='", "'%'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, "TEXTCHAR", "DIGIT", "BASENOTE", "NEWLINE", 
    "WHITESPACE", "SPACES"
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


  public HeaderLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "Abc.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30v\b\1\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
      "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
      "\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2"+
      "\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3"+
      "\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13"+
      "\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
      "\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\5\25j\n\25\5\25l\n\25"+
      "\3\26\3\26\3\27\6\27q\n\27\r\27\16\27r\3\27\3\27\2\2\30\3\3\5\4\7"+
      "\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
      "!\22#\23%\24\'\25)\26+\27-\30\3\2\7\5\2\62;CI``\3\2\62;\3\2CI\4\2"+
      "\13\13\"\"\3\2\"\"x\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
      "\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
      "\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
      "\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
      "\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\63\3\2\2\2\7\67\3\2\2\2\t"+
      ";\3\2\2\2\13?\3\2\2\2\rC\3\2\2\2\17G\3\2\2\2\21K\3\2\2\2\23O\3\2\2"+
      "\2\25Q\3\2\2\2\27S\3\2\2\2\31U\3\2\2\2\33W\3\2\2\2\35Z\3\2\2\2\37"+
      "\\\3\2\2\2!^\3\2\2\2#`\3\2\2\2%b\3\2\2\2\'d\3\2\2\2)k\3\2\2\2+m\3"+
      "\2\2\2-p\3\2\2\2/\60\7Z\2\2\60\61\7<\2\2\61\62\7\"\2\2\62\4\3\2\2"+
      "\2\63\64\7V\2\2\64\65\7<\2\2\65\66\7\"\2\2\66\6\3\2\2\2\678\7E\2\2"+
      "89\7<\2\29:\7\"\2\2:\b\3\2\2\2;<\7N\2\2<=\7<\2\2=>\7\"\2\2>\n\3\2"+
      "\2\2?@\7O\2\2@A\7<\2\2AB\7\"\2\2B\f\3\2\2\2CD\7S\2\2DE\7<\2\2EF\7"+
      "\"\2\2F\16\3\2\2\2GH\7X\2\2HI\7<\2\2IJ\7\"\2\2J\20\3\2\2\2KL\7M\2"+
      "\2LM\7<\2\2MN\7\"\2\2N\22\3\2\2\2OP\7%\2\2P\24\3\2\2\2QR\7d\2\2R\26"+
      "\3\2\2\2ST\7o\2\2T\30\3\2\2\2UV\7E\2\2V\32\3\2\2\2WX\7E\2\2XY\7~\2"+
      "\2Y\34\3\2\2\2Z[\7\61\2\2[\36\3\2\2\2\\]\7?\2\2] \3\2\2\2^_\7\'\2"+
      "\2_\"\3\2\2\2`a\t\2\2\2a$\3\2\2\2bc\t\3\2\2c&\3\2\2\2de\t\4\2\2e("+
      "\3\2\2\2fl\7\f\2\2gi\7\17\2\2hj\7\f\2\2ih\3\2\2\2ij\3\2\2\2jl\3\2"+
      "\2\2kf\3\2\2\2kg\3\2\2\2l*\3\2\2\2mn\t\5\2\2n,\3\2\2\2oq\t\6\2\2p"+
      "o\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\b\27\2\2u.\3\2"+
      "\2\2\6\2ikr\3\b\2\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}