package siani.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
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

METAIDENTIFIER      = "Concept"
INTENTION_KEY       = "Intention"
CASE_KEY            = "case"

USE_KEY             = "use"
BOX_KEY             = "box"
METAMODEL           = "metamodel"

WITH                = "with"
AS                  = "as"
ON                  = "on"
IS                  = "is"
//annotations
PRIVATE             = "private"
SINGLE              = "single"
REQUIRED            = "required"
NAMEABLE            = "nameable"
TERMINAL            = "terminal"
PROPERTY            = "property"
ROOT                = "root"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LIST                = {LEFT_SQUARE} {RIGHT_SQUARE}
OPEN_BRACKET        = "{"
CLOSE_BRACKET       = "}"
APOSTROPHE          = "'"
DASH                = "-"
DASHES              = {DASH} {DASH}+
DOT                 = "."
STAR                = "*"
COMMA               = ","
COLON               = ":"
SEMICOLON           = ";"+
DOUBLE_COMMAS       = "\""
POSITIVE            = "+"

VAR                 = "var"

WORD_TYPE           = "word"
RESOURCE_TYPE       = "resource"
ALIAS_TYPE          = "alias"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
DOUBLE_TYPE         = "double"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
DATE_TYPE           = "date"
EMPTY_REF           = "empty"

BOOLEAN_VALUE_KEY   = "true" | "false"
POSITIVE_VALUE_KEY  = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({POSITIVE} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY    = {APOSTROPHE} ~ {APOSTROPHE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}

DOC_LINE            = "#" ~[\n]
DIGIT               = [:digit:]

IDENTIFIER_KEY      = [:jletter:] [:jletterdigit:]*

%%
<YYINITIAL> {

	{METAIDENTIFIER}                {   return TaraTypes.METAIDENTIFIER_KEY; }
	{INTENTION_KEY}                 {   return TaraTypes.INTENTION_KEY; }

	{USE_KEY}                       {   return TaraTypes.USE_KEY; }
	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{BOX_KEY}                       {   return TaraTypes.BOX_KEY; }

	{AS}                            {   return TaraTypes.AS; }

	{ON}                            {   return TaraTypes.ON; }
	{WITH}                          {   return TaraTypes.WITH; }

	{COLON}                         {   return TaraTypes.COLON; }

	{VAR}                           {   return TaraTypes.VAR; }

	{CASE_KEY}                      {   return TaraTypes.CASE_KEY; }

	{IS}                            {   return TaraTypes.IS; }

	{PROPERTY}                      {   return TaraTypes.PROPERTY; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }
	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{NAMEABLE}                      {   return TaraTypes.NAMEABLE; }
	{ROOT}                          {   return TaraTypes.ROOT; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }

	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{POSITIVE_VALUE_KEY}            {   return TaraTypes.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }
    {LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD_TYPE}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_TYPE}                  {   return TaraTypes.RESOURCE_KEY; }

	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{STAR}                          {   return TaraTypes.STAR;  }
	{LIST}                          {   return TaraTypes.LIST;  }

	{ALIAS_TYPE}                    {   return TaraTypes.ALIAS_TYPE; }
	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }

	{IDENTIFIER_KEY}                {   return TaraTypes.IDENTIFIER_KEY;}

	{SEMICOLON}                     {   return semicolon(); }

	{OPEN_BRACKET}                  {   return openBracket(); }

	{CLOSE_BRACKET}                 {   return closeBracket(); }

	{NEWLINE}                       {   return newlineIndent();}

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

	{SP}                            {   return TokenType.WHITE_SPACE; }

	<<EOF>>                         {
                                        return eof();
                                    }
}

.                                   {  return TokenType.BAD_CHARACTER;}
