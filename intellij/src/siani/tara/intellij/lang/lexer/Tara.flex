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
        blockManager.openBracket(yytext().length());
        storeTokens();
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
VAR                 = "var"

USE_KEY             = "use"
BOX_KEY             = "box"
WITH                = "with"
AS                  = "as"
ON                  = "on"
ALWAYS              = "always"
IS                  = "is"
EXTENDS             = "extends"
METAMODEL           = "metamodel"
//annotations
PRIVATE             = "private"
SINGLE              = "single"
REQUIRED            = "required"
NAMED               = "named"
TERMINAL            = "terminal"
PROPERTY            = "property"
COMPONENT           = "component"
UNIVERSAL           = "universal"
INTENTION           = "intention"
FACET               = "facet"
ADDRESSED           = "addressed"
AGGREGATED          = "aggregated"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LIST                = "..."
APOSTROPHE          = "'"
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
DOT                 = "."
STAR                = "*"
BY                  = "·"
DIVIDED_BY          = "/"
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

WORD_TYPE           = "word"
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
STRING_MULTILINE_VALUE_KEY = {DASHES} ~ {DASHES}
DATE_VALUE_KEY      = (({NATURAL_VALUE_KEY} {DASH})+ {NATURAL_VALUE_KEY}) |{NATURAL_VALUE_KEY}
ADDRESS_VALUE       = {AMPERSAND} {DIGIT} {DIGIT} {DIGIT} ({DOT} {DIGIT} {DIGIT} {DIGIT})+
COORDINATE_VALUE_KEY= "["({DOUBLE_VALUE_KEY} | {NATURAL_VALUE_KEY} | {NEGATIVE_VALUE_KEY}) ({SPACES}? {COMMA} {SPACES}? {DOUBLE_VALUE_KEY} | {NATURAL_VALUE_KEY} | {NEGATIVE_VALUE_KEY})+ "]"
MEASURE_VALUE       = ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY} | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE})*

DOC_LINE            = "#" ~[\n]
DIGIT               = [:digit:]

IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

%%
<YYINITIAL> {

	{METAIDENTIFIER}                {   return TaraTypes.METAIDENTIFIER_KEY; }

	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{USE_KEY}                       {   return TaraTypes.USE_KEY; }

	{BOX_KEY}                       {   return TaraTypes.BOX_KEY; }

	{EXTENDS}                       {   return TaraTypes.EXTENDS; }
	{HAS}                           {   return TaraTypes.HAS; }
	{VAR}                           {   return TaraTypes.VAR; }
	{AS}                            {   return TaraTypes.AS; }
	{IS}                            {   return TaraTypes.IS; }
	{ON}                            {   return TaraTypes.ON; }
	{WITH}                          {   return TaraTypes.WITH; }
	{ALWAYS}                        {   return TaraTypes.ALWAYS; }

	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }

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
	{UNIVERSAL}                     {   return TaraTypes.UNIVERSAL; }
	{ADDRESSED}                     {   return TaraTypes.ADDRESSED; }
    {AGGREGATED}                    {   return TaraTypes.AGGREGATED; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }

	{ADDRESS_VALUE}                 {   return TaraTypes.ADDRESS_VALUE; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{DATE_VALUE_KEY}                {   return TaraTypes.DATE_VALUE_KEY; }
	{COORDINATE_VALUE_KEY}          {   return TaraTypes.COORDINATE_VALUE_KEY; }

    {LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD_TYPE}                     {   return TaraTypes.WORD_KEY; }
	{RESOURCE_TYPE}                 {   return TaraTypes.RESOURCE_KEY; }

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
	{IDENTIFIER_KEY}                {   return TaraTypes.IDENTIFIER_KEY;}
	{SEMICOLON}                     {   return semicolon(); }
	{MEASURE_VALUE}                 {   return TaraTypes.MEASURE_VALUE; }
	{NEWLINE}                       {   return newlineIndent();}
	{INLINE}                        {   return inline();}

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

	{SP}                            {   return TokenType.WHITE_SPACE; }

	<<EOF>>                         {
                                        return eof();
                                    }
}

.                                   {  return TokenType.BAD_CHARACTER;}
