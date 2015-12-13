/*
 * Compile all your grammars using
 *       java -jar ../../../lib/antlr.jar *.g4
 * then Refresh in Eclipse.
 */
grammar MusicGrammar;

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

// WHITESPACE is explicit in the body, don't automatically ignore it

root: abcmusic EOF;

abcmusic: abcline+;
abcline: element+ NEWLINE | midtunefield | comment;
element: noteelement | tupletelement | barline | nthrepeat | WHITESPACE; 

noteelement: note | multinote;

// note is either a pitch or a rest
note: noteorrest notelength?;
noteorrest: pitch | rest;
pitch: accidental? BASENOTE octave?;
octave: highoctave | lowoctave;
highoctave: '\''+;
lowoctave: ','+;
notelength: (DIGIT+ '/') | (DIGIT+ '/' DIGIT+) | ('/' DIGIT+) | (DIGIT+) | '/';

//"^" is sharp, "_" is flat, and "=" is neutral
accidental: '^' | '^^' | '_' | '__' | '=';

BASENOTE: 'C' | 'D' | 'E' | 'F' | 'G' | 'A' | 'B' | 'c' | 'd' | 'e' | 'f' | 'g' | 'a' | 'b';

rest: 'z';

// tuplets
tupletelement: tupletspec noteelement+;
tupletspec: '(' DIGIT; 

// chords
multinote: '[' note+ ']';

barline: '|' | '||' | '[|' | '|]' | ':|' | '|:';
nthrepeat: '[1' | '[2' | '[3' | '[4' | '[5' | '[6' | '[7' | '[8' | '[9';

// A voice field might reappear in the middle of a piece
// to indicate the change of a voice
midtunefield: fieldvoice;
fieldvoice: 'V:' WHITESPACE* ((text)+ WHITESPACE*)* endofline;

comment: '%' (text)* NEWLINE;
endofline: comment | NEWLINE;

TEXTCHAR: ~('0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | ' ' | '\t' | '\n' | '\r' | '%');
DIGIT: ('0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9');
NEWLINE: '\n' | '\r' '\n'?;
WHITESPACE: ' ' | '\t';
text: TEXTCHAR | BASENOTE | DIGIT;
