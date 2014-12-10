lexer grammar TaraLexer;

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

    private void inline() {
        blockManager.openBracket(getText().length());
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

METAIDENTIFIER      : 'Concept';
SUB                 : 'sub';

USE                 : 'use';

VAR                 : 'var';
AS                  : 'as';
HAS                 : 'has';
ON                  : 'on';
IS                  : 'is';
WITH                : 'with';
EXTENDS             : 'extends';

//annotations
ABSTRACT            : 'abstract';
SINGLE              : 'single';
REQUIRED            : 'required';
COMPONENT           : 'component';
FACET               : 'facet';
INTENTION           : 'intention';
TERMINAL            : 'terminal';
NAMED               : 'named';
PROPERTY            : 'property';
LOCAL               : 'local';
ALWAYS              : 'always';
ADDRESSED           : 'addressed';
AGGREGATED          : 'aggregated';
COMPOSED            : 'composed';
MULTIPLE            : 'multiple';
ROOT                : 'root';

LEFT_PARENTHESIS    : '(';
RIGHT_PARENTHESIS   : ')';
LEFT_SQUARE         : '[';
RIGHT_SQUARE        : ']';
LIST                : '...';
INLINE              : '>'       { inline(); };
CLOSE_INLINE        : '<';

AMPERSAND           : '&';
COLON               : ':';
COMMA               : ',';
DOT                 : '.';
EQUALS              : '=';
APHOSTROPHE         : '\'';
SEMICOLON           : ';'+      { semicolon(); };
STAR                : '*';
POSITIVE            : '+';
WORD                : 'word';
RESOURCE            : 'resource';
INT_TYPE            : 'integer';
NATURAL_TYPE        : 'natural';
DOUBLE_TYPE         : 'double';
STRING_TYPE         : 'string';
BOOLEAN_TYPE        : 'boolean';
DATE_TYPE           : 'date';
EMPTY               : 'empty';

MANTISA             : 'E' (POSITIVE | DASH)? DIGIT+;
BOOLEAN_VALUE       : 'true' | 'false';
NATURAL_VALUE       : POSITIVE? DIGIT+;
NEGATIVE_VALUE      : DASH DIGIT+ ;
DOUBLE_VALUE        : (POSITIVE | DASH)? DIGIT+ DOT DIGIT+;
STRING_VALUE        : APHOSTROPHE (~'\'')* APHOSTROPHE;
STRING_MULTILINE_VALUE_KEY   : DASHES  (~'-')* DASHES;
ADDRESS_VALUE       : AMPERSAND DIGIT DIGIT DIGIT (DOT DIGIT DIGIT DIGIT)+;
DATE_VALUE          : ((NATURAL_VALUE DASH)+ NATURAL_VALUE) | NATURAL_VALUE;
IDENTIFIER          : LETTER (DIGIT | LETTER | DASH | UNDERDASH)*;

MEASURE_VALUE       : (UNDERDASH | BY | DIVIDED_BY | PERCENTAGE | DOLLAR | EURO | GRADE | LETTER|DIGIT)+;

NEWLINE: NL+ SP* { newlinesAndSpaces(); };

SPACES: SP+ EOF? -> channel(HIDDEN);

DOC : '#' .*? NL;

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\r');

NEW_LINE_INDENT: 'indent';
DEDENT         : 'dedent';

UNKNOWN_TOKEN: . ;

fragment DOLLAR              : '$';
fragment EURO                : '€';
fragment PERCENTAGE          : '%';
fragment GRADE               : 'º';
fragment BY                  : '·';
fragment DIVIDED_BY          : '/';
fragment DASH                : '-';
fragment DASHES              : DASH DASH+;
fragment UNDERDASH           : '_';
fragment DIGIT               : [0-9];
fragment LETTER              : 'a'..'z' | 'A'..'Z' | 'ñ' | 'Ñ';