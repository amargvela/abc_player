/*
 * Compile all your grammars using
 *       java -jar ../../../lib/antlr.jar *.g4
 * then Refresh in Eclipse.
 */
grammar HeaderGrammar;

// This puts a package statement at the top of the output Java files.
@header {
package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.
}

// This adds code to the generated parser.
@members {
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
}

// ignore WHITESPACE between terminals in the header

root: header EOF;

header: fieldnumber comment* fieldtitle otherfields* fieldkey;

fieldnumber: 'X:' DIGIT+ endofline;
fieldtitle: 'T:' text endofline;
otherfields: fieldcomposer | fielddefaultlength | fieldmeter | fieldtempo | fieldvoice | comment;
fieldcomposer: 'C:' text endofline;
fielddefaultlength: 'L:' notelengthstrict endofline;
fieldmeter: 'M:' meter endofline;
fieldtempo: 'Q:' tempo endofline;
fieldvoice: 'V:' text endofline;
fieldkey: 'K:' key endofline;

key: keynote modeminor?;
keynote: (CCHAR | BASENOTE | BCHAR) keyaccidental?;
keyaccidental: '#' | BCHAR;
modeminor: MCHAR;

meter: CCHAR | 'C|' | meterfraction;
meterfraction: DIGIT+ '/' DIGIT+;
notelengthstrict: DIGIT+ '/' DIGIT+;

tempo: meterfraction '=' DIGIT+;

text: (TEXTCHAR | DIGIT | BASENOTE | BCHAR | CCHAR | MCHAR)+;

comment: '%' text NEWLINE;
endofline: comment | NEWLINE;


TEXTCHAR: ~('0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | ' ' | '\t' | '\n' | '\r' | 'm' | 'b');
DIGIT: ('0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9');
BASENOTE: ('D' | 'E' | 'F' | 'G' | 'A' | 'B' | 'c' | 'd' | 'e' | 'f' | 'g' | 'a');
NEWLINE: '\n' | '\r' '\n'?;
BCHAR: 'b';
CCHAR: 'C';
MCHAR: 'm';

/* tell Antlr to ignore spaces around tokens. */
SPACES : [ \t]+ -> skip;
