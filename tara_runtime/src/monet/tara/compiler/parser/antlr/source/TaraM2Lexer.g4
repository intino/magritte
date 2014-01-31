lexer grammar TaraM2Lexer;

@header{
    package m2LexerGrammar;
}

@lexer::members{
    private static java.util.Queue<Token>   queue = new java.util.LinkedList<Token>();
    private static java.util.Stack<Integer> stack = new java.util.Stack<java.lang.Integer>();
    private static int brackets = 0;

/*
    @Override
    public void recover(final LexerNoViableAltException error) {
        throw new RuntimeException(error);
    }
*/

    @Override
    public void reset(){
        super.reset();
        queue.clear();
        stack.clear();
        brackets=0;
    }

    @Override
    public void emit(Token token) {
        if (token.getType() == EOF) endReached();
        queue.offer(token);
        setToken(token);
    }

    @Override
    public Token nextToken() {
        super.nextToken();
        return queue.isEmpty() ? emitEOF() : queue.poll();
    }

    private void emitToken(int ttype) {
        setType(ttype);
        emit();
    }

    private int getTextLength(String text){
        int value = 0;
        for(int i = 0; i < text.length(); i++)
            if (text.charAt(i) == ('\t')) value += 4;
            else value += 1;
        return value;
    }

    private boolean isTextIndented(int textLength){
        if (!stack.empty())
            return textLength > stack.peek();
        return false;
    }

    private boolean isTextDedented(int textLength){
        if (!stack.empty())
            return textLength < stack.peek();
        return false;
    }

    private void calculateIndentationToken(String text) {
        int textLength = getTextLength(text);
        if (stack.empty() || isTextIndented(textLength)){
            stack.push(textLength);
            emitToken(INDENT);
        } else {
            while(isTextDedented(textLength)) {
                stack.pop();
                emitToken(DEDENT);
            }
        }
    }

    private boolean nextCharEqualTo(char character) {
        char nextCharacter = (char) _input.LA(1);
        return (nextCharacter == character)? true : false;
    }

    private boolean firstTokenInLine() {
       return (_tokenStartCharPositionInLine==0);
    }

    private void endReached() {
        if (brackets>0) emitToken(UNKNOWN_TOKEN);
        else emitToken(NEWLINE);
        while (!stack.empty()) {
            stack.pop();
            emitToken(DEDENT);
        }
    }

    private void evaluateSpaces() {
        if (firstTokenInLine() && !nextCharEqualTo('\n'))
            calculateIndentationToken(getText());
        setChannel(HIDDEN);
    }

    private void evaluateNewline() {
        if (brackets>0) emitToken(UNKNOWN_TOKEN);
        else{
            if (!nextCharEqualTo(' ') && !nextCharEqualTo('\t'))
                endReached();
        }
    }

    private void openBracket() {
        brackets++;
        emitToken(NEWLINE);
        emitToken(INDENT);
    }

    private void closeBracket() {
        if (brackets<=0) emitToken(UNKNOWN_TOKEN);
        else{
            brackets--;
            emitToken(DEDENT);
        }
    }

    private void semicolon(){
        if (brackets>0)
            emitToken(NEWLINE);
        else
            emitToken(UNKNOWN_TOKEN);
    }

/*
    private void calculateIndentationTokenInline(int indentSpaces) {
        int textLength = (stack.empty())? indentSpaces : stack.peek()+indentSpaces;
        textLength = (textLength<0)? 0 : textLength;
        calculateIndentationToken(textLength);
    }
*/


}

CONCEPT   : 'Concept';
FROM      : 'from';
AS        : 'as';
FINAL     : 'final';
ABSTRACT  : 'abstract';
MULTIPLE  : 'multiple';
OPTIONAL  : 'optional';
HAS_CODE  : 'has-code';
EXTENSIBLE: 'extensible';
WORD      : 'Word';
VAR       : 'var';
ROOT      : 'root';
SINGLETON : 'singleton';

LIST: LEFT_BRACKET RIGHT_BRACKET;
LEFT_BRACKET : '[';
RIGHT_BRACKET: ']';

INDENT: '(';
DEDENT: ')';

OPEN_BRACKET : '{' { openBracket(); };
CLOSE_BRACKET: '}' NEWLINE? { closeBracket(); };

OPEN_AN : '<';
CLOSE_AN: '>';

COMMA        : ',';
DOT          : '.';
ASSIGN       : ':';
DOUBLE_COMMAS: '"';
SEMICOLON    : ';' { semicolon(); };

POSITIVE: '+';
NEGATIVE: '-';

UID_TYPE    : 'Uid';
INT_TYPE    : 'Int';
NATURAL_TYPE: 'Natural';
DOUBLE_TYPE : 'Double';
STRING_TYPE : 'String';
BOOLEAN_TYPE: 'Boolean';

BOOLEAN_VALUE : 'true' | 'false';
POSITIVE_VALUE: POSITIVE? DIGIT+;
NEGATIVE_VALUE: NEGATIVE DIGIT+ ;
DOUBLE_VALUE  : (POSITIVE | NEGATIVE)? DIGIT+ DOT DIGIT+;
STRING_VALUE  : DOUBLE_COMMAS (DIGIT | LETTER | ACCENTED_LETTER | UNKNOWN_TOKEN)+ DOUBLE_COMMAS;


IDENTIFIER: LETTER (DIGIT | LETTER)*;

DIGIT : [0-9];

LETTER: 'a'..'z'
      | 'A'..'Z'
      ;

ACCENTED_LETTER: 'Á' | 'á'
               | 'É' | 'é'
               | 'Í' | 'í'
               | 'Ó' | 'ó'
               | 'Ú' | 'ú';

WHITE_LINE: SP+ EOF -> channel(HIDDEN);

SPACES: SP+ { evaluateSpaces(); };

NEWLINE: NL+ { evaluateNewline(); };

DOC_BLOCK: '/?' .*? '?/' NL?;
DOC_LINE : '??' .*? (NL|EOF);

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\n');

UNKNOWN_TOKEN: . ;