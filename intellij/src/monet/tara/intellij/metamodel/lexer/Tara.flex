package monet.tara.intellij.metamodel.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;

%%

%class TaraLexer
%implements FlexLexer
%unicode
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
        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
    }

	private boolean isWhiteLineOrEOF() {
		return (zzMarkedPos >= zzBuffer.length()) || (zzMarkedPos < zzBuffer.length() && zzBuffer.charAt(zzMarkedPos) == '\n');
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

SP = ([ ]+ | [\t]+)
SPACES= {SP}+
NEWLINE= [\n]+ ([ ] | [\t])*

//=====================
//Reserved words

CONCEPT   = "Concept"
IMPORT_KEY= "import"
PACKAGE   = "package"
CASE_KEY  = "case"
BASE_KEY  = "base"
FINAL     = "final"
ABSTRACT  = "abstract"
MULTIPLE  = "multiple"
OPTIONAL  = "optional"
HAS_CODE  = "has-code"
SINGLETON = "singleton"
INTENTION_KEY = "intention"
ROOT      = "root"
GENERIC   = "generic"
WORD      = "Word"
VAR       = "var"

LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

DOT           = "."
COLON         = ":"
SEMICOLON     = ";"+
DOUBLE_COMMAS = "\""
OPEN_AN  = "<"
CLOSE_AN = ">"
POSITIVE = "+"
NEGATIVE = "-"

UID_TYPE     = "Uid"
INT_TYPE     = "Integer"
NATURAL_TYPE = "Natural"
DOUBLE_TYPE  = "Double"
STRING_TYPE  = "String"
BOOLEAN_TYPE = "Boolean"

BOOLEAN_VALUE_KEY  = "true" | "false"
POSITIVE_VALUE_KEY = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE_KEY   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY   = {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}

DOC_LINE = "'" ~[\n]
DIGIT=[:digit:]

IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

%%
<YYINITIAL> {

	{CONCEPT}                   {   return TaraTypes.CONCEPT_KEY; }

	{IMPORT_KEY}                {   return TaraTypes.IMPORT_KEY; }

	{PACKAGE}                   {   return TaraTypes.PACKAGE; }

	{ABSTRACT}                  {   return TaraTypes.ABSTRACT; }

	{FINAL}                     {   return TaraTypes.FINAL; }

	{COLON}                     {   return TaraTypes.COLON; }

	{VAR}                       {   return TaraTypes.VAR; }

	{LIST}                      {   return TaraTypes.LIST; }

	{BASE_KEY}                  {   return TaraTypes.BASE_KEY; }
	{CASE_KEY}                  {   return TaraTypes.CASE_KEY; }

	{OPEN_AN}                   {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                  {   return TaraTypes.CLOSE_AN; }

	{OPTIONAL}                  {   return TaraTypes.OPTIONAL; }
	{MULTIPLE}                  {   return TaraTypes.MULTIPLE; }
	{GENERIC}                   {   return TaraTypes.GENERIC; }

	{HAS_CODE}                  {   return TaraTypes.HAS_CODE; }
	{INTENTION_KEY}             {   return TaraTypes.INTENTION_KEY; }
	{ROOT}                      {   return TaraTypes.ROOT; }
	{SINGLETON}                 {   return TaraTypes.SINGLETON; }

	{DOC_LINE}                  {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}          {   return TaraTypes.STRING_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}         {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}          {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}        {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{POSITIVE_VALUE_KEY}        {   return TaraTypes.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}               {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return TaraTypes.RIGHT_SQUARE; }

	{WORD}                      {   return TaraTypes.WORD_KEY; }

	{DOT}                       {   return TaraTypes.DOT; }

	{UID_TYPE}                  {   return TaraTypes.UID_TYPE; }
	{INT_TYPE}                  {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}               {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return TaraTypes.DOUBLE_TYPE; }

	{IDENTIFIER_KEY}            {   return TaraTypes.IDENTIFIER_KEY;}

	{SEMICOLON}                 {   return semicolon(); }

	{OPEN_BRACKET}              {   return openBracket(); }

	{CLOSE_BRACKET}             {   return closeBracket(); }

	{NEWLINE}                   {   return newlineIndent();}

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

	{SP}                        {   return TokenType.WHITE_SPACE; }

	<<EOF>>                     {
                                    return eof();
                                }
}

.                               {  return TokenType.BAD_CHARACTER;}
