// Generated from HeaderGrammar.g4 by ANTLR 4.5.1

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
public class HeaderGrammarLexer extends Lexer {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, TEXTCHAR=14, DIGIT=15, BASENOTE=16, 
    NEWLINE=17, BCHAR=18, CCHAR=19, MCHAR=20, SPACES=21;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
    "T__9", "T__10", "T__11", "T__12", "TEXTCHAR", "DIGIT", "BASENOTE", 
    "NEWLINE", "BCHAR", "CCHAR", "MCHAR", "SPACES"
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


  public HeaderGrammarLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "HeaderGrammar.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27j\b\1\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
      "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
      "\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3"+
      "\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
      "\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
      "\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\5\22Z\n\22\5\22\\\n\22\3"+
      "\23\3\23\3\24\3\24\3\25\3\25\3\26\6\26e\n\26\r\26\16\26f\3\26\3\26"+
      "\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
      "\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\5\t\2\13\f\17\17\"\""+
      "\62;CIcioo\6\2CDFIccei\4\2\13\13\"\"l\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
      "\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
      "\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
      "\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
      "\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\60\3\2\2\2\7\63\3"+
      "\2\2\2\t\66\3\2\2\2\139\3\2\2\2\r<\3\2\2\2\17?\3\2\2\2\21B\3\2\2\2"+
      "\23E\3\2\2\2\25G\3\2\2\2\27J\3\2\2\2\31L\3\2\2\2\33N\3\2\2\2\35P\3"+
      "\2\2\2\37R\3\2\2\2!T\3\2\2\2#[\3\2\2\2%]\3\2\2\2\'_\3\2\2\2)a\3\2"+
      "\2\2+d\3\2\2\2-.\7Z\2\2./\7<\2\2/\4\3\2\2\2\60\61\7V\2\2\61\62\7<"+
      "\2\2\62\6\3\2\2\2\63\64\7E\2\2\64\65\7<\2\2\65\b\3\2\2\2\66\67\7N"+
      "\2\2\678\7<\2\28\n\3\2\2\29:\7O\2\2:;\7<\2\2;\f\3\2\2\2<=\7S\2\2="+
      ">\7<\2\2>\16\3\2\2\2?@\7X\2\2@A\7<\2\2A\20\3\2\2\2BC\7M\2\2CD\7<\2"+
      "\2D\22\3\2\2\2EF\7%\2\2F\24\3\2\2\2GH\7E\2\2HI\7~\2\2I\26\3\2\2\2"+
      "JK\7\61\2\2K\30\3\2\2\2LM\7?\2\2M\32\3\2\2\2NO\7\'\2\2O\34\3\2\2\2"+
      "PQ\n\2\2\2Q\36\3\2\2\2RS\4\62;\2S \3\2\2\2TU\t\3\2\2U\"\3\2\2\2V\\"+
      "\7\f\2\2WY\7\17\2\2XZ\7\f\2\2YX\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[V\3\2"+
      "\2\2[W\3\2\2\2\\$\3\2\2\2]^\7d\2\2^&\3\2\2\2_`\7E\2\2`(\3\2\2\2ab"+
      "\7o\2\2b*\3\2\2\2ce\t\4\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2"+
      "\2gh\3\2\2\2hi\b\26\2\2i,\3\2\2\2\6\2Y[f\3\b\2\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}