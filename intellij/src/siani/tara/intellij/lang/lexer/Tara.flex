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

METAIDENTIFIER  = "Concept"
IMPORT_KEY      = "import"
PACKAGE         = "package"
NAMESPACE       = "namespace"
CASE_KEY        = "case"
BASE_KEY        = "base"
ABSTRACT        = "abstract"
MULTIPLE        = "multiple"
REQUIRED        = "required"
HAS_NAME        = "has-name"
INTENTION_KEY   = "intention"
ROOT            = "root"
TERMINAL         = "terminal"
WORD_KEY        = "Word"
RESOURCE_KEY    = "Resource"
VAR             = "var"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
OPEN_BRACKET        = "{"
CLOSE_BRACKET       = "}"
APOSTROPHE          = "'"
DASH                = "-"
DASHES              = {DASH} {DASH}+
DOT                 = "."
COMMA               = ","
COLON               = ":"
SEMICOLON           = ";"+
DOUBLE_COMMAS       = "\""
OPEN_AN             = "<"
CLOSE_AN            = ">"
POSITIVE            = "+"

ALIAS_TYPE          = "Alias"
INT_TYPE            = "Integer"
NATURAL_TYPE        = "Natural"
DOUBLE_TYPE         = "Double"
STRING_TYPE         = "String"
BOOLEAN_TYPE        = "Boolean"

BOOLEAN_VALUE_KEY   = "true" | "false"
POSITIVE_VALUE_KEY  = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({POSITIVE} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY    = {APOSTROPHE} ~ {APOSTROPHE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}

DOC_LINE            = "#" ~[\n]
DIGIT               =[:digit:]

IDENTIFIER_KEY      = [:jletter:] [:jletterdigit:]*

%%
<YYINITIAL> {

	{METAIDENTIFIER}                {   return TaraTypes.METAIDENTIFIER_KEY; }

	{IMPORT_KEY}                    {   return TaraTypes.IMPORT_KEY; }

	{PACKAGE}                       {   return TaraTypes.PACKAGE; }

	{NAMESPACE}                     {   return TaraTypes.NAMESPACE_KEY; }

	{ABSTRACT}                      {   return TaraTypes.ABSTRACT; }

	{COLON}                         {   return TaraTypes.COLON; }

	{VAR}                           {   return TaraTypes.VAR; }

	{BASE_KEY}                      {   return TaraTypes.BASE_KEY; }
	{CASE_KEY}                      {   return TaraTypes.CASE_KEY; }

	{OPEN_AN}                       {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                      {   return TaraTypes.CLOSE_AN; }

	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{MULTIPLE}                      {   return TaraTypes.MULTIPLE; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }

	{HAS_NAME}                      {   return TaraTypes.HAS_NAME; }
	{INTENTION_KEY}                 {   return TaraTypes.INTENTION_KEY; }
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

	{WORD_KEY}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}                  {   return TaraTypes.RESOURCE_KEY; }

	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA;     }

	{ALIAS_TYPE}                    {   return TaraTypes.ALIAS_TYPE; }
	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }

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
