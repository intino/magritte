package io.intino.tara.plugin.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.intino.tara.plugin.lang.psi.TaraTypes;
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

	private IElementType eof() {
		if (queue.isEmpty() && !end) {
            blockManager.eof();
            storeTokens();
            end = true;
            queue.add(TaraTypes.NEWLINE);
        }
        return sendToken();
    }

	private String getTextSpaces(String text) {
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

	private IElementType semicolon() {
        blockManager.semicolon(yytext().length());
        storeTokens();
        return sendToken();
    }

    private void storeTokens() {
        blockManager.actions();
        for (IElementType token : blockManager.actions())
            queue.offer(token);
    }
%}

NEWLINE             = [\r|\n|\r\n]+ ([ ] | [\t])*
SP                  = ([ ])
INLINE              = ">"

SUB                 = "sub"
HAS                 = "has"
VAR                 = "var"

USE                 = "use"
DSL                 = "dsl"
WITH                = "with"
ANY                 = "any"
AS                  = "as"
ON                  = "on"
IS                  = "is"
INTO                = "into"
EXTENDS             = "extends"

CONCEPT             = "concept"
ABSTRACT            = "abstract"
COMPONENT           = "component"
TERMINAL            = "terminal"
PRIVATE             = "private"
FEATURE             = "feature"
FINAL               = "final"
ENCLOSED            = "enclosed"
REACTIVE            = "reactive"
VOLATILE            = "volatile"
REQUIRED            = "required"
DECORABLE           = "decorable"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LEFT_CURLY          = "{"
RIGHT_CURLY         = "}"
QUOTE               = "\""
SINGLE_QUOTE        = "'"
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
DOT                 = "."
BY                  = "·"
DIVIDED_BY          = "/"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
STAR                = "*"
DOLLAR              = "$"
EURO                = "€"
PERCENTAGE          = "%"
MINOR				 = "<"
AT					 = "@"
GRADE				 = "º" | "°"
SEMICOLON           = ";"+
HASHTAG             = "#"
WORD_TYPE           = "word"
OBJECT_TYPE         = "object"
RESOURCE_TYPE       = "resource"
INT_TYPE            = "integer"
DOUBLE_TYPE         = "double"
FUNCTION_TYPE       = "function"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
LONG_TYPE        	= "long"
DATE_TYPE           = "datex"
INSTANT_TYPE        = "instant"
TIME_TYPE           = "time"
EMPTY_REF           = "empty"
SCIENCE_NOT         = "E" ({PLUS} | {DASH})? {DIGIT}+
BOOLEAN_VALUE_KEY   = "true" | "false"
NATURAL_VALUE_KEY   = {PLUS}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({PLUS} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+ {SCIENCE_NOT}?
CLASS_TYPE   		= {MINOR} (({IDENTIFIER_KEY} {DOT})* {IDENTIFIER_KEY}) ({COMMA} {SP}* {IDENTIFIER_KEY})? {INLINE}
METRIC_VALUE_KEY    = ([:jletter:] | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE}) ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY})*
COMMENT = {TraditionalComment} | {LINE_COMMENT} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
LINE_COMMENT         = {NEWLINE}? "//" [^\n]*
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

DOC_LINE            = "!!" ~[\n]
PLUS                = "+"
DIGIT               = [:digit:]

STRING_MULTILINE    	= {EQUALS} {EQUALS}+
NATIVE_MULTILINE_VALUE  = {DASHES}

IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {DASH})*

%xstate QUOTED, MULTILINE, EXPRESSION, EXPRESSION_MULTILINE

%%
<YYINITIAL> {
	{LINE_COMMENT}                  {   return TokenType.WHITE_SPACE;}
	{DOC_LINE}                      {   yypushback(1); return TaraTypes.DOC_LINE; }
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
	{ANY}                           {   return TaraTypes.ANY; }

	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }
	{STAR}                          {   return TaraTypes.STAR; }
	{PLUS}                          {   return TaraTypes.PLUS; }

	{AT}   		                    {   return TaraTypes.AT; }
	{CLASS_TYPE}                    {   return TaraTypes.CLASS_TYPE; }

	{SUB}                           {   return TaraTypes.SUB; }

	{ABSTRACT}                      {   return TaraTypes.ABSTRACT; }
	{COMPONENT}                     {   return TaraTypes.COMPONENT; }
    {FEATURE}                       {   return TaraTypes.FEATURE; }
    {REACTIVE}                      {   return TaraTypes.REACTIVE; }
    {CONCEPT}	                    {   return TaraTypes.CONCEPT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{ENCLOSED}                      {   return TaraTypes.ENCLOSED; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }
	{FINAL}                         {   return TaraTypes.FINAL; }
	{VOLATILE}                      {   return TaraTypes.VOLATILE; }
	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
    {DECORABLE}                     {   return TaraTypes.DECORABLE; }

	{QUOTE}                         {   yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN; }
	{STRING_MULTILINE}              {   yybegin(MULTILINE); return TaraTypes.QUOTE_BEGIN; }

	{SINGLE_QUOTE}					{   yybegin(EXPRESSION); return TaraTypes.EXPRESSION_BEGIN; }
	{NATIVE_MULTILINE_VALUE}		{   yybegin(EXPRESSION_MULTILINE); return TaraTypes.EXPRESSION_BEGIN; }

	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }

    {LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }
	{LEFT_CURLY}                    {   return TaraTypes.LEFT_CURLY; }
    {RIGHT_CURLY}                   {   return TaraTypes.RIGHT_CURLY; }
	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }

	{WORD_TYPE}                     {   return TaraTypes.WORD_TYPE; }
	{OBJECT_TYPE}                   {   return TaraTypes.OBJECT_TYPE; }
	{RESOURCE_TYPE}                 {   return TaraTypes.RESOURCE_TYPE; }
	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {FUNCTION_TYPE}                 {   return TaraTypes.FUNCTION_TYPE; }
    {INSTANT_TYPE}                  {   return TaraTypes.INSTANT_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {TIME_TYPE}                     {   return TaraTypes.TIME_TYPE; }
    {LONG_TYPE}                     {   return TaraTypes.LONG_TYPE; }
    {EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }
	{IDENTIFIER_KEY}                {   return TaraTypes.IDENTIFIER_KEY;}
	{METRIC_VALUE_KEY}              {   return TaraTypes.METRIC_VALUE_KEY; }
	{SEMICOLON}                     {   return semicolon(); }
	{NEWLINE}                       {   return newlineIndent();}
	{INLINE}                        {   return inline();}
	{SP}                            {   return TokenType.WHITE_SPACE; }

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

<EXPRESSION> {
    {SINGLE_QUOTE}                  {   yybegin(YYINITIAL); return TaraTypes.EXPRESSION_END; }
    [^\n\r\'\\]                     {   return TaraTypes.CHARACTER; }
    \n | \r                         {   return TaraTypes.CHARACTER; }
    \t                              {   return TaraTypes.CHARACTER; }
    \\t                             {   return TaraTypes.CHARACTER; }
    \\n                             {   return TaraTypes.CHARACTER; }
    \\r                             {   return TaraTypes.CHARACTER; }
    \\\'                            {   return TaraTypes.CHARACTER; }
    \\                              {   return TaraTypes.CHARACTER; }
    [^]                             {   return TokenType.BAD_CHARACTER;}
    .                               {   return TokenType.BAD_CHARACTER;}
}


<EXPRESSION_MULTILINE> {
    {NATIVE_MULTILINE_VALUE}        {   yybegin(YYINITIAL); return TaraTypes.EXPRESSION_END; }
    [^\n\r\\]                       {   return TaraTypes.CHARACTER; }
    \n | \r                         {   return TaraTypes.CHARACTER; }
    \t                              {   return TaraTypes.CHARACTER; }
    \\t                             {   return TaraTypes.CHARACTER; }
    \\n                             {   return TaraTypes.CHARACTER; }
    \\r                             {   return TaraTypes.CHARACTER; }
    \\                              {   return TaraTypes.CHARACTER; }
    [^]                             {   return TokenType.BAD_CHARACTER; }
    .                               {   return TokenType.BAD_CHARACTER; }
}

[^]                                 {   return TokenType.BAD_CHARACTER;}
.                                   {   return TokenType.BAD_CHARACTER;}
