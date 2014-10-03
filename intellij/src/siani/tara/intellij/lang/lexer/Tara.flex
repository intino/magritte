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
		IElementType token = (end)? null : TaraTypes.NEWLINE;
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
            queue.add(TaraTypes.NEWLINE);
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

    private IElementType inline() {
            if (queue.isEmpty()) {
                String spaces = "    ";
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

SP                  = ([ ]+ | [\t]+)
SPACES              = {SP}+
NEWLINE             = [\n]+ ([ ] | [\t])*
INLINE              = ">"

//Reserved words

METAIDENTIFIER      = "Concept"
SUB                 = "sub"
HAS                 = "has"

USE_KEY             = "use"
BOX_KEY             = "box"
METAMODEL           = "metamodel"

WITH                = "with"
AS                  = "as"
ON                  = "on"
ALWAYS              = "always"
IS                  = "is"
EXTENDS             = "extends"
//annotations
PRIVATE             = "private"
SINGLE              = "single"
REQUIRED            = "required"
NAMED               = "named"
TERMINAL            = "terminal"
PROPERTY            = "property"
COMPONENT           = "component"
INTENTION           = "intention"
FACET               = "facet"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LIST                = "..."
OPEN_BRACKET        = "{"
CLOSE_BRACKET       = "}"
APOSTROPHE          = "'"
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
DOT                 = "."
STAR                = "*"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
DOLLAR              = "$"
EURO                = "€"
PERCENTAGE          = "%"
GRADE               = "º"
SEMICOLON           = ";"+
POSITIVE            = "+"
AMPERSAND           = "&"
VAR                 = "var"

WORD_TYPE           = "word"
PORT_TYPE           = "port"
RESOURCE_TYPE       = "resource"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
DOUBLE_TYPE         = "double"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
DATE_TYPE           = "date"
COORDINATE_TYPE     = "coordinate"
EMPTY_REF           = "empty"

BOOLEAN_VALUE_KEY   = "true" | "false"
NATURAL_VALUE_KEY   = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({POSITIVE} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY    = {APOSTROPHE} ~ {APOSTROPHE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}
DATE_VALUE_KEY      = (({NATURAL_VALUE_KEY} {DASH})+ {NATURAL_VALUE_KEY}) |{NATURAL_VALUE_KEY}
COORDINATE_VALUE_KEY= (({DOUBLE_VALUE_KEY} {DASH})+ {DOUBLE_VALUE_KEY}) |{DOUBLE_VALUE_KEY}
CODE_VALUE_KEY      = {AMPERSAND} [:jletterdigit:]+

DOC_LINE            = "#" ~[\n]
DIGIT               = [:digit:]

IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

%%
<YYINITIAL> {

	{METAIDENTIFIER}                {   return TaraTypes.METAIDENTIFIER_KEY; }

	{USE_KEY}                       {   return TaraTypes.USE_KEY; }
	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{BOX_KEY}                       {   return TaraTypes.BOX_KEY; }

	{EXTENDS}                       {   return TaraTypes.EXTENDS; }
	{HAS}                           {   return TaraTypes.HAS; }
	{AS}                            {   return TaraTypes.AS; }
	{IS}                            {   return TaraTypes.IS; }
	{ON}                            {   return TaraTypes.ON; }
	{WITH}                          {   return TaraTypes.WITH; }
	{ALWAYS}                        {   return TaraTypes.ALWAYS; }

	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }

	{VAR}                           {   return TaraTypes.VAR; }

	{SUB}                           {   return TaraTypes.SUB; }


	{PROPERTY}                      {   return TaraTypes.PROPERTY; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }
	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{NAMED}                         {   return TaraTypes.NAMED; }
	{COMPONENT}                     {   return TaraTypes.COMPONENT; }
	{FACET}                         {   return TaraTypes.FACET; }
	{INTENTION}                     {   return TaraTypes.INTENTION; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }

	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{DATE_VALUE_KEY}                {   return TaraTypes.DATE_VALUE_KEY; }
	{CODE_VALUE_KEY}                {   return TaraTypes.CODE_VALUE_KEY; }
	{COORDINATE_VALUE_KEY}          {   return TaraTypes.COORDINATE_VALUE_KEY; }

    {LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD_TYPE}                     {   return TaraTypes.WORD_KEY; }
	{RESOURCE_TYPE}                 {   return TaraTypes.RESOURCE_KEY; }

	{DOLLAR}                        {   return TaraTypes.DOLLAR;}
    {EURO}                          {   return TaraTypes.EURO;}
    {PERCENTAGE}                    {   return TaraTypes.PERCENTAGE;}
    {GRADE}                         {   return TaraTypes.GRADE;}
	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{STAR}                          {   return TaraTypes.STAR;  }
	{LIST}                          {   return TaraTypes.LIST;  }

	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {COORDINATE_TYPE}               {   return TaraTypes.COORDINATE_TYPE; }
    {EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }
	{PORT_TYPE}                     {   return TaraTypes.PORT_TYPE; }
	{IDENTIFIER_KEY}                {   return TaraTypes.IDENTIFIER_KEY;}
	{SEMICOLON}                     {   return semicolon(); }

	{OPEN_BRACKET}                  {   return openBracket(); }

	{CLOSE_BRACKET}                 {   return closeBracket(); }

	{NEWLINE}                       {   return newlineIndent();}
	{INLINE}                        {   return inline();}

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

	{SP}                            {   return TokenType.WHITE_SPACE; }

	<<EOF>>                         {
                                        return eof();
                                    }
}

.                                   {  return TokenType.BAD_CHARACTER;}
