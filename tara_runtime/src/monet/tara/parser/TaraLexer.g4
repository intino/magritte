lexer grammar TaraLexer;

@header{
    package monet.tara.intellij.parser;
}

@lexer::members{
	public static java.util.Queue<Token>   queue = new java.util.LinkedList<Token>();
	public static java.util.Stack<Integer> stack = new java.util.Stack<java.lang.Integer>();

	@Override
	public void recover(final LexerNoViableAltException error) {
		throw new RuntimeException(error);
	}

    @Override
    public void reset() {
        super.reset();
        queue.clear();
        stack.clear();
    }

	@Override
	public void emit(Token token) {
        if (token.getType() == EOF)
            endReached();

        queue.offer(token);
        setToken(token);
	}

	@Override
	public Token nextToken() {
		super.nextToken();
		return queue.isEmpty() ? (new CommonToken(EOF)) : queue.poll();
	}

	private boolean firstTokenInLine(){
		return (_tokenStartCharPositionInLine==0);
	}
	private boolean finalToken(){
		return (_input.LA(1) == EOF);
	}

    private void emitTokenText(int ttype, String text) {
    	emit(new CommonToken(ttype, text));
    }

	private int transformToSpaces(String text){
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

	private void calculateIndentationToken() {
		int textLength = transformToSpaces(getText());
		if (stack.empty() || isTextIndented(textLength)){
		    stack.push(textLength);
            emitTokenText(INDENT, "{");
		} else if (isTextDedented(textLength)) {
		    stack.pop();
		    /*if (isTextDedented(textLength))
		        emitTokenText(SPACES,getText());*/
            emitTokenText(DEDENT, "}");
		}
	}

    private void endReached() {
        while (!stack.empty()) {
            stack.pop();
            emitTokenText(DEDENT, "}");
        }
    }

	private boolean needToDedent (int asciiChar){
	    char nextCharacter = (char) asciiChar;
	    return (nextCharacter != ' ' && nextCharacter!='\n' && nextCharacter !='\t');
	}
}

//=====================
//Reserved words

IS       : 'is'       ;
USE      : 'use'      ;
HAS      : 'has'      ;
REF      : 'ref'      ;
CONCEPT  : 'concept'  ;
NAMESPACE: 'namespace';
INCLUDE  : 'include'  ;

//=====================
//Modifiers

FINAL   : 'final'   ;
ABSTRACT: 'abstract';

//=====================
//Range

// Need to control [0..0] and [4..2] (second lower than first)
RANGE: LEFT_BRACKET DIGIT+ DOTS (DIGIT+ |'n') RIGHT_BRACKET;

//=====================
//Brackets

SLASH        : '/';
LEFT_BRACKET : '[';
RIGHT_BRACKET: ']';

//=====================
// Types

INT_TYPE    : 'int'    ;
DOUBLE_TYPE : 'double' ;
NATURAL_TYPE: 'natural';
STRING_TYPE : 'string' ;
BOOLEAN_TYPE: 'boolean';
DATE_TYPE   : 'date'   ;

TRUE: 'true'
	| 'True';

FALSE: 'false'
	 | 'False';

DATE: DIGIT DIGIT? SLASH DIGIT DIGIT? SLASH DIGIT DIGIT (DIGIT DIGIT)?;

//=====================
// Punctuation

COMMA       : ',' ;
DOT         : '.' ;
DOTS        : '..';
COL         : ':' ;
DOUBLE_COMMA: '"' ;

//=====================
// Assignment

ASSIGN: '=';

//=====================
// Annotations

HAS_ID_AN        : '@has-id'       ;
ROOT_AN          : '@root'         ;
EXTEN_AN         : '@extensible'   ;
DEFAULT_AN       : '@default'      ;
ACTION_JAVA_AN   : '@action:java'  ;
ACTION_PHYTON_AN : '@action:phyton';

//=====================
//"String"

STRING: DOUBLE_COMMA ALPHANUMERIC+ DOUBLE_COMMA;

//=====================
//Digits

NUMBER       : DIGIT+;
DOUBLE_NUMBER: NUMBER DOT NUMBER;

DIGIT: [0-9];
SIGN : POSITIVE
	 | NEGATIVE
	 ;

POSITIVE: '+';
NEGATIVE: '-';

//=====================
//Identifier

IDENTIFIER: LETTER ALPHANUMERIC*;

ALPHANUMERIC: (LETTER | DIGIT)+;

//=====================
//Letters

LETTER: 'a'..'z'
      | 'A'..'Z'
      ;

//=====================
//Comments

COMMENT     : '/*' .*? '*/'  -> channel(HIDDEN);
LINE_COMMENT: '//' .*? '\n'? -> channel(HIDDEN);

//=======================
//INDENT & DEDENT control

INDENT: '{';
DEDENT: '}';

WHITE_LINE: SP+ NL -> channel(HIDDEN);

EOL: NL { if (needToDedent(_input.LA(1))) {endReached();}  setChannel(HIDDEN); };

SPACES: SP+ {
			  if (firstTokenInLine() && !finalToken()) {calculateIndentationToken();}
		      setChannel(HIDDEN);
        	};

SP: (' ' | '\t');
NL: ('\r'? '\n' | '\n');