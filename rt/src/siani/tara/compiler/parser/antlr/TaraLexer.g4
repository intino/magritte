lexer grammar TaraLexer;

@header{
package siani.tara.compiler.parser.antlr;
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

METAIDENTIFIER      : 'Concept';
INTENTION           : 'Intention';
CASE                : 'case';

USE                 : 'use';
BOX                 : 'box';
METAMODEL           : 'metamodel';

AS                  :'as';
ON                  :'on';
IS                  :'is';
WITH                : 'WITH';
//annotations
PRIVATE             : 'private';
SINGLE              : 'single';
REQUIRED            : 'required';
ROOT                : 'root';
TERMINAL            : 'terminal';
NAMEABLE            : 'nameable';
PROPERTY            : 'property';

LEFT_PARENTHESIS    : '(';
RIGHT_PARENTHESIS   : ')';
LEFT_SQUARE         : '[';
RIGHT_SQUARE        : ']';
LIST                :LEFT_SQUARE RIGHT_SQUARE;
OPEN_BRACKET        : '{' {  openBracket(); };
CLOSE_BRACKET       : '}' { closeBracket(); };

OPEN_AN             : '<';
CLOSE_AN            : '>';
COLON               : ':';
COMMA               : ',';
DOT                 : '.';
ASSIGN              : '=';
APHOSTROPHE         : '\'';
SEMICOLON           : ';'+ { semicolon(); };
STAR                : '*';
POSITIVE            : '+';
DASH                : '-';
DASHES              : DASH DASH+;

VAR                 : 'var';

WORD                : 'word';
RESOURCE            : 'resource';
ALIAS_TYPE          : 'alias';
INT_TYPE            : 'integer';
NATURAL_TYPE        : 'natural';
DOUBLE_TYPE         : 'double';
STRING_TYPE         : 'string';
BOOLEAN_TYPE        : 'boolean';
DATE_TYPE           : 'date';
EMPTY               : 'empty';

BOOLEAN_VALUE : 'true' | 'false';
POSITIVE_VALUE: POSITIVE? DIGIT+;
NEGATIVE_VALUE: DASH DIGIT+ ;
DOUBLE_VALUE  : (POSITIVE | DASH)? DIGIT+ DOT DIGIT+;
STRING_VALUE  : APHOSTROPHE (~'\'')* APHOSTROPHE;
STRING_MULTILINE_VALUE_KEY   : DASHES  (~'-')* DASHES;

IDENTIFIER: LETTER (DIGIT | LETTER)*;

DIGIT : [0-9];

LETTER: 'a'..'z'
      | 'A'..'Z';

NEWLINE: NL+ SP* { newlinesAndSpaces(); };

SPACES: SP+ EOF? -> channel(HIDDEN);

DOC : '#' .*? NL;

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\n');

NEW_LINE_INDENT: 'indent';
DEDENT         : 'dedent';

UNKNOWN_TOKEN: . ;