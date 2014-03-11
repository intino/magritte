package monet.::projectName::.intellij.metamodel;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.::projectName::.compiler.intellij.psi.::projectNameFile::Types;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;

%%

%class ::projectNameFile::Lexer
%implements FlexLexer
%unicode
%column
%function advance
%type IElementType

%{
	private BlockManager blockManager = new BlockManager();
	private Queue<IElementType> queue = new LinkedList<>();
	private boolean end = false;

	private IElementType sendToken() {
		IElementType token = (end)? null:TokenType.WHITE_SPACE;
		if (!queue.isEmpty())
			token = queue.poll();
		if (!queue.isEmpty())
			yypushback(yylength());
		return token;
	}

	private IElementType eof(){
		if (queue.isEmpty() && !end) {
            blockManager.eof();
            storeTokens();
            end = true;
        }
        return sendToken();
    }

	private String getTextSpaces(String text){
        int index = (text.indexOf(' ') == -1)? text.indexOf('\\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
    }

	private boolean isWhiteLineOrEOF() {
		return (zzMarkedPos >= zzBuffer.length()) || (zzMarkedPos < zzBuffer.length() && zzBuffer.charAt(zzMarkedPos) == '\\n');
	}

    private IElementType newlineIndent() {
		if (isWhiteLineOrEOF()) return TokenType.WHITE_SPACE;
        if (queue.isEmpty()) {
            String spaces = getTextSpaces(yytext().toString());
            blockManager.spaces(spaces);
            storeTokens();
        }
        return sendToken();
    }

    private IElementType openBracket() {
        blockManager.openBracket(yytext().length());
        storeTokens();
        return sendToken();
    }

    private IElementType closeBracket() {
       if (queue.isEmpty()) {
            blockManager.closeBracket(yytext().length());
            storeTokens();
        }
        return sendToken();
    }

	private IElementType semicolon(){
        blockManager.semicolon(yytext().length());
        storeTokens();
        return sendToken();
    }

    private void storeTokens(){
        blockManager.actions();
        for (IElementType token : blockManager.actions())
            queue.offer(token);
    }
%}

SP = ([ ]+ | [\\t]+)
SPACES= {SP}+
NEWLINE= [\\n]+ ([ ] | [\\t])*

//=====================
//Reserved words
POLYMORPH = "Polymorph"
MORPH     = "Morph"
FROM_KEY  = "from"
AS        = "as"
FINAL     = "final"
ABSTRACT  = "abstract"
MULTIPLE  = "multiple"
OPTIONAL  = "optional"
HAS_CODE  = "has-code"
SINGLETON = "singleton"
ROOT      = "root"
EXTENSIBLE = "extensible"
WORD      = "Word"
VAR       = "var"
NEW       = "new"


LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

EQUAL         = "="
DOT           = "."
COMMA         = ","
ASSIGN        = ":"
SEMICOLON     = ";"+
DOUBLE_COMMAS = "\\""
OPEN_AN  = "<"
CLOSE_AN = ">"
POSITIVE = "+"
NEGATIVE = "-"

UID_TYPE     = "Uid"
INT_TYPE     = "Int"
NATURAL_TYPE = "Natural"
DOUBLE_TYPE  = "Double"
STRING_TYPE  = "String"
BOOLEAN_TYPE = "Boolean"

BOOLEAN_VALUE  = "true" | "false"

//=====================
//Concepts
::concepts::

POSITIVE_VALUE = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE= {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}

DOC = "'" ~[\\n]

DIGIT=[:digit:]

IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*



%%
<YYINITIAL> {

    {POLYMORPH}                 {   return ::projectNameFile::Types.POLYMORPH; }

	{MORPH}                     {   return ::projectNameFile::Types.MORPH; }

	{ABSTRACT}                  {   return ::projectNameFile::Types.ABSTRACT; }

	{FINAL}                     {   return ::projectNameFile::Types.FINAL; }

	{AS}                        {   return ::projectNameFile::Types.AS; }

	{ASSIGN}                    {   return ::projectNameFile::Types.ASSIGN; }

	{VAR}                       {   return ::projectNameFile::Types.VAR; }

	{NEW}                       {   return ::projectNameFile::Types.NEW; }

	{LIST}                      {   return ::projectNameFile::Types.LIST; }

	{FROM_KEY}                  {   return ::projectNameFile::Types.FROM_KEY; }

	{OPEN_AN}                   {   return ::projectNameFile::Types.OPEN_AN; }
	{CLOSE_AN}                  {   return ::projectNameFile::Types.CLOSE_AN; }

	{OPTIONAL}                  {   return ::projectNameFile::Types.OPTIONAL; }
	{MULTIPLE}                  {   return ::projectNameFile::Types.MULTIPLE; }

	{HAS_CODE}                  {   return ::projectNameFile::Types.HAS_CODE; }
	{EXTENSIBLE}                {   return ::projectNameFile::Types.EXTENSIBLE; }
	{ROOT}                      {   return ::projectNameFile::Types.ROOT; }
	{SINGLETON}                 {   return ::projectNameFile::Types.SINGLETON; }

	{DOC}                       {   return ::projectNameFile::Types.DOC; }

	{STRING_VALUE}              {   return ::projectNameFile::Types.STRING_VALUE; }
	{BOOLEAN_VALUE}             {   return ::projectNameFile::Types.BOOLEAN_VALUE; }
	{DOUBLE_VALUE}              {   return ::projectNameFile::Types.DOUBLE_VALUE; }
	{NEGATIVE_VALUE}            {   return ::projectNameFile::Types.NEGATIVE_VALUE; }
	{POSITIVE_VALUE}            {   return ::projectNameFile::Types.NATURAL_VALUE; }

	{LEFT_SQUARE}               {   return ::projectNameFile::Types.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return ::projectNameFile::Types.RIGHT_SQUARE; }

	{WORD}                      {   return ::projectNameFile::Types.WORD_KEY; }

	{DOT}                       {   return ::projectNameFile::Types.DOT; }

    {EQUAL}                     {   return ::projectNameFile::Types.EQUAL; }


	{UID_TYPE}                  {   return ::projectNameFile::Types.UID_TYPE; }
	{INT_TYPE}                  {   return ::projectNameFile::Types.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return ::projectNameFile::Types.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return ::projectNameFile::Types.NATURAL_TYPE; }
    {STRING_TYPE}               {   return ::projectNameFile::Types.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return ::projectNameFile::Types.DOUBLE_TYPE; }

	{IDENTIFIER_KEY}            {   return ::projectNameFile::Types.IDENTIFIER_KEY;}

	{SEMICOLON}                 {   return semicolon(); }

	{COMMA}                     {   return ::projectNameFile::Types.COMMA();     }

	{OPEN_BRACKET}              {   return openBracket(); }

	{CLOSE_BRACKET}             {   return closeBracket(); }

	{NEWLINE}                   {   return newlineIndent();}

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

	{SP}                        {   return TokenType.WHITE_SPACE; }

	<<EOF>>                     {
                                    return eof();
                                }
::conceptsToBNF::
}

.                               {  return TokenType.BAD_CHARACTER;}



@concepts
::conceptKey::= "::conceptValue::"
@conceptsToBNF
    {::conceptKey::}                {  return TokenType.::conceptKey::;  }
