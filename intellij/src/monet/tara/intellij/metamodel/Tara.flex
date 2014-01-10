package monet.tara.intellij.metamodel;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.psi.TaraTypes;
import com.intellij.psi.TokenType;

%%

%class TaraLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
	if(stack.size() > 0) {
		stack.pop();
	}

%eof}


%{
	static Stack<Integer> stack = new Stack<>();

	private int transformToSpaces(CharSequence chain){
		int value = 0;
		for(int i = 0; i < chain.length(); i++)
			if (chain.charAt(i) == ('\t')) value += 4;
			else value += 1;
		return value;
	}

	private IElementType cleanStack(){
		if (!stack.empty()) {
            stack.pop();
            if (!stack.empty() && isTextIndented(transformToSpaces(yytext())))
                yypushback(yylength());
            return TaraTypes.DEDENT;
        }
            return TokenType.WHITE_SPACE;
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

	private boolean isTextSibling(int textLength){
		if (!stack.empty())
			return textLength == stack.peek();
		return false;
    }

	private IElementType calculateIndentationToken() {
		int textLength = transformToSpaces(yytext());
        if (stack.empty() || isTextIndented(textLength)){
            stack.push(textLength);
            return TaraTypes.INDENT;
        } else if (isTextDedented(textLength)) {
            stack.pop();
            if (isTextDedented(textLength))
                yypushback(yylength());
            return TaraTypes.DEDENT;
		} else
            return TokenType.WHITE_SPACE;
	}
%}

EOL=[\n] | ([^][ ]+[\n])
INDENT=[^][ ]+
WS = [ ]+ | [\t]+
END_OF_LINE_COMMENT = ("#"|"!")[^\r\n]*

//=====================
//Reserved words

IS       = "is"
HAS      = "has"
REF      = "ref"
CONCEPT  = "concept"
NAMESPACE= "namespace"
INCLUDE  = "include"
USE      = "use"

//=====================
//Concept modifiers

FINAL    = "final"
ABSTRACT = "abstract"

MODIFIERS = {FINAL} | {ABSTRACT}

EXTENDS = {IS} {WS} {IDENTIFIER_KEY}

//=====================
//Range

RANGE = {LEFT_BRACKET} {INT} {DOTS} ({INT} | "n") {RIGHT_BRACKET}

PARAMETER = "(" {IDENTIFIER_KEY}* ")"

//=====================
//Brackets

LEFT_BRACKET = "["
RIGHT_BRACKET= "]"

LEFT_PARENTH = "("
RIGHT_PARENTH= ")"

//=====================
// Types

ID_TYPE      ="id"
INT_TYPE     ="int"
STRING_TYPE  ="string"
DOUBLE_TYPE  ="double"

//=====================
// Punctuation

COMMA       = ","
DOT         = "."
DOTS        = ".."
COLON       = ":" | ": "
DOUBLE_COMMA = "\""

//=====================
// Relations

ASSIGN = "=" | "= "

//=====================
// Annotations

NAMEABLE ="@nameable"
ROOT   = "@root"
EXTENSIBLE  = "@extensible"
ACTION = "@action:java"
ANONYMOUS = "@anonymous"

ANNOTATION = {NAMEABLE} | {ROOT} | {EXTENSIBLE} | {ACTION}


STRING= {DOUBLE_COMMA} ([ ] | {ALPHANUMERIC})* {DOUBLE_COMMA}

//=====================
//Tabs

TAB= "\t"| "\r"

//=====================
//Digits

INT= {DIGIT}+
DOUBLE = {INT} {DOT} {INT}

DIGIT=[:digit:]

//=====================
//Identifier
IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

//=====================
//Letters

LETTER= [:jletter:]


//=====================
//Alphanumeric

ALPHANUMERIC= [:jletterdigit:]*

%%
<YYINITIAL> {

	{CONCEPT}                   {  return TaraTypes.CONCEPT; }

	{HAS}                       {  return TaraTypes.HAS;}

	{INT_TYPE}                  {  return TaraTypes.INT_TYPE; }

    {STRING_TYPE}               {  return TaraTypes.STRING_TYPE; }

    {DOUBLE_TYPE}               {  return TaraTypes.DOUBLE_TYPE; }

    {ID_TYPE}                   {  return TaraTypes.ID_TYPE; }

    {REF}                       {  return TaraTypes.REF;}

    {USE}                       {  return TaraTypes.USE;}

	{MODIFIERS}                 {  return TaraTypes.MODIFIERS;}

	{IS}                        {  return TaraTypes.IS;}

	{ANNOTATION}                {  return TaraTypes.ANNOTATION;}

	{ANONYMOUS}                 {  return TaraTypes.ANONYMOUS;}

	{ASSIGN}                    {  return TaraTypes.ASSIGN; }

	{STRING}                    {  return TaraTypes.STRING; }

	{INT}                       {  return TaraTypes.INT; }

	{DOUBLE}                    {  return TaraTypes.DOUBLE; }

	{IDENTIFIER_KEY}            {  return TaraTypes.IDENTIFIER_KEY;}

	{RANGE}                     {  return TaraTypes.RANGE;}

	{LEFT_PARENTH}              {  return TaraTypes.LEFT_P;}

	{RIGHT_PARENTH}             {  return TaraTypes.RIGHT_P;}

	{COLON}                     {  return TaraTypes.COLON;}

	{END_OF_LINE_COMMENT}       {  return TaraTypes.COMMENT; }

	{INDENT}                    {  return calculateIndentationToken();}

	{EOL}                       {  return cleanStack();}

	{WS}                        {  return TokenType.WHITE_SPACE;}
}

	.                           {  return TokenType.BAD_CHARACTER;}