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
NEWLINE             = [\r|\n|\r\n]+ ([ ] | [\t])*
INLINE              = ">"


METAIDENTIFIER      = "Concept"
SUB                 = "sub"
HAS                 = "has"
VAR                 = "var"

USE                 = "use"
DSL                 = "dsl"
WITH                = "with"
AS                  = "as"
ON                  = "on"
IS                  = "is"
INTO                = "into"
EXTENDS             = "extends"


ABSTRACT            = "abstract"

SINGLE              = "single"
MULTIPLE            = "multiple"

ROOT                = "root"
REQUIRED            = "required"

TERMINAL            = "terminal"

PROPERTY            = "property"
FEATURE             = "feature"

READONLY            = "readonly"
ENCLOSED            = "enclosed"

FACET               = "facet"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LIST                = "..."
QUOTE               = "\""
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
DOT                 = "."
BY                  = "·"
DIVIDED_BY          = "/"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
DOLLAR              = "$"
EURO                = "€"
PERCENTAGE          = "%"
GRADE               = "º" | "°"
SEMICOLON           = ";"+
HASHTAG             = "#"

WORD_TYPE           = "word"
RESOURCE_TYPE       = "file"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
DOUBLE_TYPE         = "double"
NATIVE_TYPE         = "native"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
RATIO_TYPE          = "ratio"
MEASURE_TYPE_KEY    = "measure"
DATE_TYPE           = "date"
EMPTY_REF           = "empty"
SCIENCE_NOT         = "E" ({PLUS} | {DASH})? {DIGIT}+
BOOLEAN_VALUE_KEY   = "true" | "false"
NATURAL_VALUE_KEY   = {PLUS}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({PLUS} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+ {SCIENCE_NOT}?
ADDRESS_VALUE       = {HASHTAG} [:jletter:]+
MEASURE_VALUE_KEY   = ([:jletter:] | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE}) ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY})*

COMMENT = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
LineTerminator      = \r|\n|\r\n
EndOfLineComment     = "//" [^\n]*
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

DOC_LINE            = "doc" ~[\n]
PLUS                = "+"
DIGIT               = [:digit:]
STRING_MULTILINE    = {DASHES}
IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

%xstate QUOTED, MULTILINE

%%
<YYINITIAL> {
	{COMMENT}                       {   }
	{DOC_LINE}                      {   yypushback(1); return TaraTypes.DOC_LINE; }
	{METAIDENTIFIER}                {   return TaraTypes.METAIDENTIFIER_KEY; }
	{USE}                           {   return TaraTypes.USE; }
	{DSL}                           {   return TaraTypes.DSL; }

	{EXTENDS}                       {   return TaraTypes.EXTENDS; }
	{HAS}                           {   return TaraTypes.HAS; }
	{VAR}                           {   return TaraTypes.VAR; }
	{AS}                            {   return TaraTypes.AS; }
	{IS}                            {   return TaraTypes.IS; }
	{INTO}                          {   return TaraTypes.INTO; }
	{ON}                            {   return TaraTypes.ON; }
	{WITH}                          {   return TaraTypes.WITH; }

	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }

	{SUB}                           {   return TaraTypes.SUB; }

	{ABSTRACT}                      {   return TaraTypes.ABSTRACT; }

	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{MULTIPLE}                      {   return TaraTypes.MULTIPLE; }
	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{ROOT}                          {   return TaraTypes.ROOT; }

	{PROPERTY}                      {   return TaraTypes.PROPERTY; }
	{FEATURE}                       {   return TaraTypes.FEATURE; }

	{FACET}                         {   return TaraTypes.FACET; }

	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{ENCLOSED}                      {   return TaraTypes.ENCLOSED; }

	{READONLY}                      {   return TaraTypes.READONLY; }

	{QUOTE}                         {   yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN; }
	{STRING_MULTILINE}              {   yybegin(MULTILINE); return TaraTypes.QUOTE_BEGIN; }

	{ADDRESS_VALUE}                 {   return TaraTypes.ADDRESS_VALUE; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }

    {LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD_TYPE}                     {   return TaraTypes.WORD_KEY; }
	{RESOURCE_TYPE}                 {   return TaraTypes.RESOURCE_KEY; }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }
	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{LIST}                          {   return TaraTypes.LIST;  }

	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {NATIVE_TYPE}                   {   return TaraTypes.NATIVE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {RATIO_TYPE}                    {   return TaraTypes.RATIO_TYPE; }
    {MEASURE_TYPE_KEY}              {   return TaraTypes.MEASURE_TYPE_KEY; }
    {EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }
	{IDENTIFIER_KEY}                {   return TaraTypes.IDENTIFIER_KEY;}
	{MEASURE_VALUE_KEY}             {   return TaraTypes.MEASURE_VALUE_KEY; }
	{SEMICOLON}                     {   return semicolon(); }
	{NEWLINE}                       {   return newlineIndent();}
	{INLINE}                        {   return inline();}
	{SPACES}                        {   return TokenType.WHITE_SPACE; }

	{SP}                            {   return TokenType.WHITE_SPACE; }
	<<EOF>>                         {   return eof(); }
	[^]                             {   return TokenType.BAD_CHARACTER;}
    .                               {   return TokenType.BAD_CHARACTER;}
}

<QUOTED> {
    {QUOTE}                         {   yybegin(YYINITIAL); return TaraTypes.QUOTE_END; }
    [^\n\r\"\\]                     {   return TaraTypes.CHARACTER; }
    \n | \r                         {   return TaraTypes.CHARACTER; }
    \t                              {   return TaraTypes.CHARACTER; }
    \\t                             {   return TaraTypes.CHARACTER; }
    \\n                             {   return TaraTypes.CHARACTER; }
    \\r                             {   return TaraTypes.CHARACTER; }
    \\\"                            {   return TaraTypes.CHARACTER; }
    \\                              {   return TaraTypes.CHARACTER; }
    [^]                             {   return TokenType.BAD_CHARACTER;}
    .                               {   return TokenType.BAD_CHARACTER;}
}

<MULTILINE> {
    {STRING_MULTILINE}              {   yybegin(YYINITIAL); return TaraTypes.QUOTE_END; }
    [^\n\r\\]                       {   return TaraTypes.CHARACTER; }
    \n | \r                         {   return TaraTypes.CHARACTER; }
    \t                              {   return TaraTypes.CHARACTER; }
    \\t                             {   return TaraTypes.CHARACTER; }
    \\n                             {   return TaraTypes.CHARACTER; }
    \\r                             {   return TaraTypes.CHARACTER; }
    \\\"                            {   return TaraTypes.CHARACTER; }
    \\                              {   return TaraTypes.CHARACTER; }
    [^]                             {   return TokenType.BAD_CHARACTER; }
    .                               {   return TokenType.BAD_CHARACTER; }
}

[^]                                 {   return TokenType.BAD_CHARACTER;}
.                                   {   return TokenType.BAD_CHARACTER;}
