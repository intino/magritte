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
%eof{  return;
%eof}


%{
	static Stack<Integer> stack = new Stack<java.lang.Integer>();
	{
		stack.push(0);
	}

	private void indent(){
    	stack.push(stack.peek()+1);
    }

	private void deindent(){
        stack.pop();
    }

	private int getLevel(CharSequence yytext){
		int level = 0;
		int ws = 0;
		for (int i = 0; i < yytext.length(); i++){
			if (yytext.charAt(i) == '\t') ws += 4;
			if (yytext.charAt(i) == ' ') ws++;
		}
		return level + ws/4;
	}

	private IElementType indentDeindent(int currentLevel){
		if (currentLevel > stack.peek()){
			indent();
			return TaraTypes.INDENT;
		} else if (currentLevel < stack.peek()){
			deindent();
            return TaraTypes.DEINDENT;
		} else
			return TaraTypes.INLINE;
	}
%}


CRLF = \r|\n|[\r\n]
NEW_LINE_INDENT = "\n\t"
WS =[ \t\f]
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

%state WAITING

%%
<YYINITIAL> {// System.out.println("white space. Level " +getLevel(yytext())); I)
	{WS}+                      {return indentDeindent(getLevel(yytext()));}
	{CONCEPT}                  { yybegin(WAITING); indentDeindent(getLevel(yytext())); return TaraTypes.CONCEPT; }
	{HAS}                      { yybegin(WAITING); return TaraTypes.HAS;}
	{INT_TYPE}                 { yybegin(WAITING); return TaraTypes.INT_TYPE; }
    {STRING_TYPE}              { yybegin(WAITING); return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}              { yybegin(WAITING); return TaraTypes.DOUBLE_TYPE; }
    {ID_TYPE}                  { yybegin(WAITING); return TaraTypes.ID_TYPE; }
    {REF}                      { yybegin(WAITING); return TaraTypes.REF;}
}

<WAITING> {
	{MODIFIERS}                {  return TaraTypes.MODIFIERS;}

	{IS}                       {  return TaraTypes.IS;}

	{ROOT}                     {  return TaraTypes.AT_ROOT;}

	{NAMEABLE}                 {  return TaraTypes.NAMEABLE ;}

	{EXTENSIBLE}               {  return TaraTypes.EXTENSIBLE;}

	{ACTION}                   {  return TaraTypes.ACTION;}

	{ASSIGN}                   {  return TaraTypes.ASSIGN; }

	{STRING}                   {  return TaraTypes.STRING; }

	{INT}                      {  return TaraTypes.INT; }

	{DOUBLE}                   {  return TaraTypes.DOUBLE; }

	{TAB}                      {  return TaraTypes.INDENT; }

	{IDENTIFIER}               {  return TaraTypes.IDENTIFIER;}

	{END_OF_LINE_COMMENT}      {  return TaraTypes.COMMENT; }

	{NEW_LINE_INDENT}              { yybegin(YYINITIAL); return TokenType.NEW_LINE_INDENT; }

}

{CRLF}                         { yybegin(YYINITIAL); return TaraTypes.CRLF; }


{WS}+                          { return TokenType.WHITE_SPACE; }

.                              { return TokenType.BAD_CHARACTER;}