lexer grammar TaraM2Lexer;

@header{
    package AntlrM2;
}

@lexer::members{
    BlockManager blockManager = new BlockManager();
    private static java.util.Queue<Token> queue = new java.util.LinkedList<>();

    @Override
    public void reset(){
        super.reset();
        queue.clear();
        blockManager.reset();
    }

    @Override
    public void emit(Token token) {
        if (token.getType() == EOF) EOF();
        queue.offer(token);
        setToken(token);
    }

    @Override
    public Token nextToken() {
        super.nextToken();
        return queue.isEmpty()? emitEOF() : queue.poll();
    }

    private void emitToken(int ttype) {
        setType(ttype);
        emit();
    }

    private String getTextSpaces(String text){
        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
    }

    private void newlinesAndSpaces() {
        blockManager.spaces(getTextSpaces(getText()));
        sendTokens();
    }

    private void openBracket() {
        blockManager.openBracket();
        sendTokens();
    }

    private void closeBracket() {
        blockManager.closeBracket();
        sendTokens();
    }

    private void semicolon(){
        blockManager.addSemicolon(getText().length());
        sendTokens();
    }

    private void EOF(){
        blockManager.spaces("");
        sendTokens();
    }

    private void sendTokens(){
        blockManager.actions();
        for (BlockManager.Token token : blockManager.actions())
            emitToken(translate(token));
    }

    private int translate (BlockManager.Token token){
        if (token.toString() == "NEWLINE") return NEWLINE;
        if (token.toString() == "DEDENT") return DEDENT;
        if (token.toString() == "INDENT") return INDENT;
        return UNKNOWN_TOKEN;
    }
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

OPEN_BRACKET : '{' {  openBracket(); };
CLOSE_BRACKET: '}' { closeBracket(); };

OPEN_AN : '<';
CLOSE_AN: '>';

COMMA        : ',';
DOT          : '.';
ASSIGN       : ':';
DOUBLE_COMMAS: '"';
SEMICOLON    : ';'+ { semicolon(); };

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
STRING_VALUE  : DOUBLE_COMMAS (~'"')* DOUBLE_COMMAS;

IDENTIFIER: LETTER (DIGIT | LETTER)*;

DIGIT : [0-9];

LETTER: 'a'..'z'
      | 'A'..'Z'
      ;

WHITE_LINE: NL+ SP+ (NL+|EOF) -> channel(HIDDEN);

NEWLINE: NL+ SP* { newlinesAndSpaces(); };

SPACES: SP+ EOF? -> channel(HIDDEN);

DOC_BLOCK: '/?' .*? '?/' NL?;
DOC_LINE : '??' .*? (NL|EOF);

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\n');

INDENT: '(';
DEDENT: ')';

UNKNOWN_TOKEN: . ;