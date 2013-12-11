package monet.tara.metamodelplugin;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tara.metamodelplugin.psi.TaraTypes;
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
//          return(TaraTypes.DEDENT);
		return;
	}

%eof}


%{

	static Stack<Integer> stack = new Stack<java.lang.Integer>();

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
        return TaraTypes.END_CONCEPT;
    }

	private boolean isWhiteLine(int i){
		return i == 1;
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
		} else if (isTextSibling(textLength)){
			return null;
        } else
            return TaraTypes.END_CONCEPT;
	}
%}

EOL=[\n]
INDENTED_LINE=[^][ ]+
WS = [\ ]+ | [\t]+
END_OF_LINE_COMMENT =("#"|"!")[^\r\n]*

//=====================
//Reserved words

IS       = "is"
HAS      = "has"
REF      = "ref"
CONCEPT  = "concept"
NAMESPACE= "namespace"
INCLUDE  = "include"

//=====================
//Concept modifiers

FINAL    = "final"
ABSTRACT = "abstract"

MODIFIERS = {FINAL} | {ABSTRACT}

EXTENDS = {IS} {WS} {IDENTIFIER}

//=====================
//Range

RANGE = {LEFT_BRACKET} {INT_NUMBER} {DOTS} {INT_NUMBER} {RIGHT_BRACKET}

//=====================
//Brackets

LEFT_BRACKET = "\["
RIGHT_BRACKET= "]"

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
DOTS        =".."
DOUBLE_COMMA = "\""

//=====================
// Relations

ASSIGN = "="

//=====================
// Annotations

NAMEABLE ="@nameable"
ROOT   = "@root"
EXTENSIBLE  = "@extensible"
ACTION = "@action=java"

ANNOTATIONS = {NAMEABLE} | {ROOT} |{EXTENSIBLE} | {ACTION}

//=====================
//"String"

STRING= {DOUBLE_COMMA} {ALPHANUMERIC}* {DOUBLE_COMMA}

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
IDENTIFIER = [:jletter:] [:jletterdigit:]*

//=====================
//Letters

LETTER= [:jletter:]


//=====================
//Alphanumeric

ALPHANUMERIC= [:jletterdigit:]*


%%
<YYINITIAL> {

	{CONCEPT}                  {  return TaraTypes.CONCEPT; }
	{HAS}                      {  return TaraTypes.HAS;}
	{INT_TYPE}                 {  return TaraTypes.INT_TYPE; }
    {STRING_TYPE}              {  return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}              {  return TaraTypes.DOUBLE_TYPE; }
    {ID_TYPE}                  {  return TaraTypes.ID_TYPE; }
    {REF}                      {  return TaraTypes.REF;}

	{MODIFIERS}                {  return TaraTypes.MODIFIERS;}

	{IS}                       {  return TaraTypes.IS;}

	{ROOT}                     {  return TaraTypes.AT_ROOT;}

	{NAMEABLE}                 {  return TaraTypes.NAMEABLE;}

	{EXTENSIBLE}               {  return TaraTypes.EXTENSIBLE;}

	{ACTION}                   {  return TaraTypes.ACTION;}

	{ASSIGN}                   {  return TaraTypes.ASSIGN; }

	{STRING}                   {  return TaraTypes.STRING; }

	{INT}                      {  return TaraTypes.INT; }

	{DOUBLE}                   {  return TaraTypes.DOUBLE; }

	{IDENTIFIER}               {  return TaraTypes.IDENTIFIER;}

	{END_OF_LINE_COMMENT}      {  return TaraTypes.COMMENT; }

	{INDENTED_LINE}            {IElementType elementType;if((elementType = calculateIndentationToken()) != null) return elementType;}

	{EOL}                      {  return cleanStack();}

	{WS}                       {return TokenType.WHITE_SPACE;}
}

	.                          {  return TokenType.BAD_CHARACTER;}