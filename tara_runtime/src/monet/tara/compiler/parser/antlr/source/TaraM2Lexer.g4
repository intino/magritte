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
        if (token.getType() == EOF) eof();
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

    private boolean isWhiteLineOrEOF() {
        int character = _input.LA(1);
        return (character == -1 || (char) character == '\n');
    }

    private String getTextSpaces(String text){
        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
    }

    private void newlinesAndSpaces() {
        if (!isWhiteLineOrEOF()){
            blockManager.newlineAndSpaces(getTextSpaces(getText()));
            sendTokens();
        }
        else
            skip();
    }

    private void openBracket() {
        blockManager.openBracket(getText().length());
        sendTokens();
    }

    private void closeBracket() {
        blockManager.closeBracket(getText().length());
        sendTokens();
    }

    private void semicolon(){
        blockManager.semicolon(getText().length());
        sendTokens();
    }

    private void eof(){
        blockManager.eof();
        sendTokens();
    }

    private void sendTokens(){
        blockManager.actions();
        for (BlockManager.Token token : blockManager.actions())
            emitToken(translate(token));
    }

    private int translate (BlockManager.Token token){
        if (token.toString().equals("NEWLINE")) return NEWLINE;
        if (token.toString().equals("DEDENT")) return DEDENT;
        if (token.toString().equals("NEWLINE_INDENT")) return NEW_LINE_INDENT;
        return UNKNOWN_TOKEN;
    }
}

CONCEPT    : 'Concept';
AS         : 'as';
FINAL      : 'final';
ABSTRACT   : 'abstract';
MULTIPLE   : 'multiple';
OPTIONAL   : 'optional';
HAS_CODE   : 'has-code';
EXTENSIBLE : 'extensible';
VAR        : 'var';
ROOT       : 'root';
SINGLETON  : 'singleton';
NEW        : 'new';
POLYMORPHIC: 'polymorphic';
MORPH      : 'morph';
WORD       : 'Word';

LIST: LEFT_SQUARE RIGHT_SQUARE;
LEFT_SQUARE : '[';
RIGHT_SQUARE: ']';

OPEN_BRACKET : '{' {  openBracket(); };
CLOSE_BRACKET: '}' { closeBracket(); };

OPEN_AN : '<';
CLOSE_AN: '>';

COMMA        : ',';
DOT          : '.';
ASSIGN       : '=';
DOUBLE_COMMAS: '"';
SEMICOLON    : ';'+ { semicolon(); };

POSITIVE: '+';
NEGATIVE: '-';

UID_TYPE    : 'Uid';
INT_TYPE    : 'Integer';
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

NEWLINE: NL+ SP* { newlinesAndSpaces(); };

SPACES: SP+ EOF? -> channel(HIDDEN);

DOC : '\'' .*? NL;

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\n');

NEW_LINE_INDENT: 'indent';
DEDENT         : 'dedent';

UNKNOWN_TOKEN: . ;