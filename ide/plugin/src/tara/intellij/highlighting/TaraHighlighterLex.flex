package tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.project.Project;
import tara.Language;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.TaraTypes;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

%%

%class TaraHighlighterLex
%implements FlexLexer
%unicode
%function advance
%type IElementType

%{
	private Set<String> identifiers;
	private Project project;
	private static final String DSL = "dsl";
	private String dsl = null;

	public TaraHighlighterLex(java.io.Reader reader, Project project) {
		this.zzReader = reader;
		this.project = project;
	}

	private IElementType evaluateIdentifier() {
		String identifier = yytext().toString();
		if (identifiers == null) return TaraTypes.IDENTIFIER_KEY;
		return identifiers.contains(identifier) ? TaraTypes.METAIDENTIFIER_KEY : TaraTypes.IDENTIFIER_KEY;
	}

	private void loadHeritage() {
		if (identifiers != null) return;
		if (dsl == null) {
			String source = zzBuffer.toString().trim();
			int nl = source.indexOf('\n');
            String dslLine = nl > 0 ? source.substring(0, nl).trim() : source;
			if (!dslLine.startsWith(DSL) || dslLine.split(DSL).length < 2) return;
			dsl = dslLine.split(DSL)[1].trim();
		}
		identifiers = new HashSet();
		Language heritage = TaraLanguage.getLanguage(dsl, project);
        if (heritage != null) Collections.addAll(identifiers, heritage.lexicon());
	}
%}

CONCEPT             = "Concept"
SUB                 = "sub"
HAS                 = "has"
EXTENDS             = "extends"
DSL                 = "dsl"
PROTEO              = "Proteo"
AS                  = "as"
ON                  = "on"
IS                  = "is"
INTO                = "into"
VAR                 = "var"
USE                 = "use"

WITH                = "with"
ANY                 = "any"

//Tags
ABSTRACT            = "abstract"
TERMINAL            = "terminal"
MAIN                = "main"
PROTOTYPE           = "prototype"
FEATURE             = "feature"
NAMED               = "named"
DEFINITION          = "definition"
FINAL               = "final"
ENCLOSED            = "enclosed"
PRIVATE             = "private"
FACET               = "facet"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LEFT_CURLY			= "{"
RIGHT_CURLY        	= "}"
DOLLAR              = "$"
EURO                = "€"
GRADE               = "º" | "°"
PERCENTAGE          = "%"
DOT                 = "."
BY                  = "·"
DIVIDED_BY          = "/"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
STAR                = "*"
SEMICOLON           = ";"
QUOTE               = "\""
SINGLE_QUOTE        = "'"
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
PLUS                = "+"
HASHTAG             = "#"

WORD_KEY            = "word"
RESOURCE_KEY        = "file"
INT_TYPE            = "integer"
NATIVE_TYPE         = "native"
DOUBLE_TYPE         = "double"
TUPLE_TYPE          = "tuple"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
DATE_TYPE           = "date"
TIME_TYPE           = "time"
BOOLEAN_VALUE_KEY   = "true" | "false"
EMPTY_REF           = "empty"
NATURAL_VALUE_KEY   = {PLUS}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
SCIENCE_NOTATION    = "E" ({PLUS} | {DASH})? {DIGIT}+
DOUBLE_VALUE_KEY    = ({PLUS} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+ {SCIENCE_NOTATION}?

STRING_MULTILINE    	= {EQUALS} {EQUALS}+
NATIVE_MULTILINE_VALUE  = {DASHES}

ADDRESS_VALUE       = {HASHTAG} [:jletter:]+
METRIC_VALUE_KEY   = ([:jletter:] | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE}) ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY})*
DOC_LINE            = "!!" ~[\n]

COMMENT = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
LineTerminator      = \r|\n|\r\n
EndOfLineComment     = "//" [^\n]* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

DIGIT               = [:digit:]
IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {DASH})*

SP                  = ([ ]+ | [\t]+) | ">"
SPACES              = {SP}+
NEWLINE             = [\n]+


%xstate QUOTED, MULTILINE, EXPRESSION, EXPRESSION_MULTILINE

%%
<YYINITIAL> {
	{COMMENT}                       {   return TaraTypes.COMMENT;}
	{CONCEPT}                       {   return TaraTypes.METAIDENTIFIER_KEY; }

	{DSL}                           {   loadHeritage();  return TaraTypes.DSL; }
	{PROTEO}                        {   return TaraTypes.PROTEO; }
	{USE}                           {   return TaraTypes.USE; }
	{VAR}                           {   return TaraTypes.VAR; }
	{HAS}                           {   return TaraTypes.HAS; }
	{EXTENDS}                       {   return TaraTypes.EXTENDS; }
	{AS}                            {   return TaraTypes.AS; }
	{ON}                            {   return TaraTypes.ON; }
	{IS}                            {   return TaraTypes.IS; }
	{INTO}                          {   return TaraTypes.INTO; }
	{WITH}                          {   return TaraTypes.WITH; }
	{ANY}                           {   return TaraTypes.ANY; }

	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }
	{STAR}                          {   return TaraTypes.STAR; }

	{SUB}                           {   return TaraTypes.SUB; }

	{ABSTRACT}                      {   return TaraTypes.ABSTRACT; }

    {MAIN}                          {   return TaraTypes.MAIN; }

    {PROTOTYPE}                     {   return TaraTypes.PROTOTYPE; }
    {FEATURE}                       {   return TaraTypes.FEATURE; }
    {NAMED}                         {   return TaraTypes.NAMED; }
    {DEFINITION}                    {   return TaraTypes.DEFINITION; }

    {FACET}                         {   return TaraTypes.FACET; }

    {TERMINAL}                      {   return TaraTypes.TERMINAL; }
    {ENCLOSED}                      {   return TaraTypes.ENCLOSED; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }

    {FINAL}                         {   return TaraTypes.FINAL; }

	{DOC_LINE}                      {   yypushback(1); return TaraTypes.DOC_LINE; }

	{ADDRESS_VALUE}                 {   return TaraTypes.ADDRESS_VALUE; }
	{QUOTE}                         {   yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN; }
	{STRING_MULTILINE}              {   yybegin(MULTILINE); return TaraTypes.QUOTE_BEGIN; }

	{SINGLE_QUOTE}					{   yybegin(EXPRESSION); return TaraTypes.EXPRESSION_BEGIN; }
	{NATIVE_MULTILINE_VALUE}		{   yybegin(EXPRESSION_MULTILINE); return TaraTypes.EXPRESSION_BEGIN; }

	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }

	{LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }

	{LEFT_CURLY}                   {   return TaraTypes.LEFT_CURLY; }
	{RIGHT_CURLY}                  {   return TaraTypes.RIGHT_CURLY; }

	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }

	{WORD_KEY}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}                  {   return TaraTypes.RESOURCE_KEY; }

	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
    {NATIVE_TYPE}                   {   return TaraTypes.NATIVE_TYPE; }
    {TUPLE_TYPE}                    {   return TaraTypes.TUPLE_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {TIME_TYPE}                     {   return TaraTypes.TIME_TYPE; }
	{SEMICOLON}                     {   return TaraTypes.DSL;  }

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

    {SP}                            {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}                {   return evaluateIdentifier();  }

    {METRIC_VALUE_KEY}              {   return TaraTypes.METRIC_VALUE_KEY; }
    {NEWLINE}                       {   return TokenType.WHITE_SPACE; }
    .                               {   return TokenType.BAD_CHARACTER; }
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
	[^]                             {   return TokenType.BAD_CHARACTER; }
	.                               {   return TokenType.BAD_CHARACTER; }
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

[^]                                  {  return TokenType.BAD_CHARACTER; }
