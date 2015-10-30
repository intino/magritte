lexer grammar TaraLexer;
@header{import tara.compiler.parser.antlr.BlockManager;}
@lexer::members {
    BlockManager blockManager = new BlockManager();
    private static java.util.Queue<Token> queue = new java.util.LinkedList<>();

    @Override
    public void reset() {
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

    private void newlinesAndSpaces() {
        if (!isWhiteLineOrEOF()){
            blockManager.newlineAndSpaces(getTextSpaces(getText()));
            sendTokens();
        }
        else skip();
    }

    private String getTextSpaces(String text) {
        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
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
DSL                 : 'dsl';

VAR                 : 'var';
AS                  : 'as';
HAS                 : 'has';
ON                  : 'on';
IS                  : 'is';
INTO                : 'into';
WITH                : 'with';
ANY                 : 'any';
EXTENDS             : 'extends';

//annotations
ABSTRACT            : 'abstract';

SINGLE              : 'single';

REQUIRED            : 'required';

TERMINAL            : 'terminal';
MAIN                : 'main';
NAMED               : 'named';
DEFINITION          : 'definition';

PROTOTYPE           : 'prototype';
FEATURE             : 'feature';

FINAL               : 'final';
ENCLOSED            : 'enclosed';
PRIVATE             : 'private';

FACET               : 'facet';

LEFT_PARENTHESIS    : '(';
RIGHT_PARENTHESIS   : ')';
LEFT_SQUARE         : '[';
RIGHT_SQUARE        : ']';
LEFT_CURLY          : '{';
RIGHT_CURLY         : '}';
INLINE              : '>'       { inline(); };
CLOSE_INLINE        : '<';

HASHTAG             : '#';
COLON               : ':';
COMMA               : ',';
DOT                 : '.';
EQUALS              : '=';
STAR                : '*';

SEMICOLON           : ';'+      { semicolon(); };
PLUS                : '+';
WORD                : 'word';
RESOURCE            : 'file';
INT_TYPE            : 'integer';
TUPLE_TYPE          : 'tuple';
NATURAL_TYPE        : 'natural';
NATIVE_TYPE         : 'native';
DOUBLE_TYPE         : 'double';
STRING_TYPE         : 'string';
BOOLEAN_TYPE        : 'boolean';
MEASURE_TYPE        : 'measure';
RATIO_TYPE          : 'ratio';
DATE_TYPE           : 'date';
TIME_TYPE           : 'time';
EMPTY               : 'empty';

BLOCK_COMMENT       : '/*' .*? '*/' -> skip;
LINE_COMMENT        : ('\r' | '\n')* ([ ] | [\t])* '//'  ~('\r' | '\n')* -> skip;
SCIENCE_NOT         : 'E' (PLUS | DASH)? DIGIT+;
BOOLEAN_VALUE       : 'true' | 'false';
NATURAL_VALUE       : PLUS? DIGIT+;
NEGATIVE_VALUE      : DASH DIGIT+ ;
DOUBLE_VALUE        : (PLUS | DASH)? DIGIT+ DOT DIGIT+;

APHOSTROPHE         : '"' {setType(QUOTE_BEGIN);} -> mode(QUOTED);
STRING_MULTILINE    : EQUALS EQUALS+  {setType(QUOTE_BEGIN);} -> mode(MULTILINE);

SINGLE_QUOTE        : '\'' {setType(EXPRESSION_BEGIN);} -> mode(EXPRESSION_QUOTED);
EXPRESSION_MULTILINE: DASH DASH+  {setType(EXPRESSION_BEGIN);} -> mode(EXPRESSION_MULTILINE_MODE);

PLATE_VALUE         : HASHTAG LETTER+;
IDENTIFIER          : LETTER (DIGIT | LETTER | DASH)*;

MEASURE_VALUE       : (LETTER| PERCENTAGE | DOLLAR | EURO | GRADE) (UNDERDASH | BY | DIVIDED_BY | PERCENTAGE | DOLLAR | EURO | GRADE | LETTER | DIGIT)*;

NEWLINE: NL+ SP* { newlinesAndSpaces(); };

SPACES: SP+ EOF? 	-> channel(HIDDEN);

DOC : '!!' .*? NEWLINE {emitToken(DOC);};

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\r');

NEW_LINE_INDENT: 'indent';
DEDENT         : 'dedent';

UNKNOWN_TOKEN: . ;

mode QUOTED;
	QUOTE:'"'                           {   setType(QUOTE_END); } -> mode(DEFAULT_MODE);
    Q:'\"'                              {   setType(CHARACTER); };
    SLASH_Q:'\\\"'                      {   setType(CHARACTER); };
    SLASH:'\\'                          {   setType(CHARACTER); };
    CHARACTER:.                         {   setType(CHARACTER); };

mode MULTILINE;
    M_QUOTE: EQUALS EQUALS+             {   setType(QUOTE_END); } -> mode(DEFAULT_MODE);
	M_CHARACTER:.                       {   setType(CHARACTER); };

mode EXPRESSION_MULTILINE_MODE;
	ME_STRING_MULTILINE: DASH DASH+     {   setType(EXPRESSION_END); } -> mode(DEFAULT_MODE);
	ME_CHARACTER:.                      {   setType(CHARACTER); };

mode EXPRESSION_QUOTED;
	E_QUOTE:'\''                        {   setType(EXPRESSION_END); } -> mode(DEFAULT_MODE);
    E_SLASH_Q:'\\\''                    {   setType(CHARACTER); };
    E_SLASH:'\\'                        {   setType(CHARACTER); };
    E_CHARACTER:.                       {   setType(CHARACTER); };

QUOTE_BEGIN        : '%QUOTE_BEGIN%';
QUOTE_END          : '%QUOTE_END%';
EXPRESSION_BEGIN   : '%EXPRESSION_BEGIN%';
EXPRESSION_END    :  '%EXPRESSION_END%';

fragment DOLLAR              : '$';
fragment EURO                : '€';
fragment PERCENTAGE          : '%';
fragment GRADE               : 'º'| '°';
fragment BY                  : '·';
fragment DIVIDED_BY          : '/';
fragment DASH                : '-';

fragment UNDERDASH           : '_';
fragment DIGIT               : [0-9];
fragment LETTER              : 'a'..'z' | 'A'..'Z' | 'ñ' | 'Ñ';