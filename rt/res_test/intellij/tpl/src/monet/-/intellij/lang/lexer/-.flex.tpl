package monet.::projectName::.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Types;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;

\%\%

\%class ::projectProperName::Lexer
\%implements FlexLexer
\%unicode
\%function advance
\%type IElementType

\%{
	private BlockManager blockManager = new BlockManager();
	private Queue<IElementType> queue = new LinkedList<>();
	private boolean end = false;

	private IElementType sendToken() {
		IElementType token = (end)? null\:TokenType.WHITE_SPACE;
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
        int index = (text.indexOf(' ') == -1)? text.indexOf('\\t') \: text.indexOf(' ');
        return (index == -1)? "" \: text.substring(index);
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
\%}

SP = ([ ]+ | [\\t]+)
SPACES= {SP}+
NEWLINE= [\\n]+ ([ ] | [\\t])*

//=====================
//Reserved words

IMPORT_KEY = "import"
PACKAGE    = "package"
CASE_KEY  = "case"
BASE_KEY  = "base"
FINAL     = "final"
ABSTRACT  = "abstract"
WORD      = "Word"
VAR       = "var"
RESOURCE_KEY = "Resource"
LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

LEFT_PARENTHESIS = "("
RIGHT_PARENTHESIS = ")"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

DOT           = "."
COMMA         = ","
COLON         = "\:"
SEMICOLON     = ";"
DOUBLE_COMMAS = "\\""
OPEN_AN  = "<"
CLOSE_AN = ">"
POSITIVE = "+"
NEGATIVE = "-"

ALIAS_TYPE   = "Alias"
INT_TYPE     = "Int"
NATURAL_TYPE = "Natural"
DOUBLE_TYPE  = "Double"
STRING_TYPE  = "String"
BOOLEAN_TYPE = "Boolean"

BOOLEAN_VALUE  = "true" | "false"


POSITIVE_VALUE = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE   = {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}
DOC_LINE = "'" ~[\\n]

DIGIT=[\:digit\:]

IDENTIFIER_KEY = [\:jletter:] [\:jletterdigit\:]*



\%\%
<YYINITIAL> {

    {IMPORT_KEY}                {   return ::projectProperName::Types.IMPORT_KEY; }

    {PACKAGE}                   {   return ::projectProperName::Types.PACKAGE; }

    {CASE_KEY}                  {   return ::projectProperName::Types.CASE_KEY; }

	{BASE_KEY}                  {   return ::projectProperName::Types.BASE_KEY; }

	{ABSTRACT}                  {   return ::projectProperName::Types.ABSTRACT; }

	{FINAL}                     {   return ::projectProperName::Types.FINAL; }

	{COLON}                     {   return ::projectProperName::Types.COLON; }

	{VAR}                       {   return ::projectProperName::Types.VAR; }

	{LIST}                      {   return ::projectProperName::Types.LIST; }

	{OPEN_AN}                   {   return ::projectProperName::Types.OPEN_AN; }
	{CLOSE_AN}                  {   return ::projectProperName::Types.CLOSE_AN; }

	{DOC_LINE}                  {   return ::projectProperName::Types.DOC_LINE; }

	{STRING_VALUE}              {   return ::projectProperName::Types.STRING_VALUE_KEY; }
	{BOOLEAN_VALUE}             {   return ::projectProperName::Types.BOOLEAN_VALUE_KEY; }
	{POSITIVE_VALUE}            {   return ::projectProperName::Types.NATURAL_VALUE_KEY; }
	{NEGATIVE_VALUE}            {   return ::projectProperName::Types.NEGATIVE_VALUE_KEY; }
	{DOUBLE_VALUE}              {   return ::projectProperName::Types.DOUBLE_VALUE_KEY; }

	{LEFT_SQUARE}               {   return ::projectProperName::Types.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return ::projectProperName::Types.RIGHT_SQUARE; }

    {LEFT_PARENTHESIS}          {   return ::projectProperName::Types.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}         {   return ::projectProperName::Types.RIGHT_PARENTHESIS; }

	{WORD}                      {   return ::projectProperName::Types.WORD_KEY; }
	{RESOURCE_KEY}            {   return ::projectProperName::Types.RESOURCE_KEY; }
	{DOT}                       {   return ::projectProperName::Types.DOT; }


	{ALIAS_TYPE}                {   return ::projectProperName::Types.ALIAS_TYPE;   }
    {STRING_TYPE}               {   return ::projectProperName::Types.STRING_TYPE;  }
	{BOOLEAN_TYPE}              {   return ::projectProperName::Types.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return ::projectProperName::Types.NATURAL_TYPE; }
	{INT_TYPE}                  {   return ::projectProperName::Types.INT_TYPE;     }
    {DOUBLE_TYPE}               {   return ::projectProperName::Types.DOUBLE_TYPE;  }

	{SEMICOLON}                 {   return semicolon(); }

	{COMMA}                     {   return ::projectProperName::Types.COMMA; }

	{OPEN_BRACKET}              {   return openBracket(); }

	{CLOSE_BRACKET}             {   return closeBracket(); }

	{NEWLINE}                   {   return newlineIndent();}

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

	{SP}                        {   return TokenType.WHITE_SPACE; }

	<<EOF>>                     {   return eof();}

	{IDENTIFIER_KEY}            {   return ::projectProperName::Types.IDENTIFIER_KEY; }
}

.                               {  return TokenType.BAD_CHARACTER;}