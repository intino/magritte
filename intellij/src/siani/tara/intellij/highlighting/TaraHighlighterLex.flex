package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.lang.Model;

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
		if (dsl == null) {
			String source = zzBuffer.toString().trim();
			int nl = source.indexOf('\n');
            String dslLine = nl > 0 ? source.substring(0, nl).trim() : source;
			if (!dslLine.startsWith(DSL)) return;
			dsl = dslLine.split(DSL)[1].trim();
		}
		Model heritage = TaraLanguage.getMetaModel(dsl, project);
		if (heritage != null) identifiers = heritage.getIdentifiers();
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
VAR                 = "var"
USE                 = "use"

ALWAYS              = "always"
WITH                = "with"
//annotations
ABSTRACT            = "abstract"
SINGLE              = "single"
REQUIRED            = "required"
NAMED               = "named"
TERMINAL            = "terminal"
PROPERTY            = "property"
INTENTION           = "intention"
ENCLOSED            = "enclosed"
FACET               = "facet"
ADDRESSED           = "addressed"
COMPONENT           = "component"
AGGREGATED          = "aggregated"
ASSOCIATED          = "associated"
READONLY            = "readonly"
CASE                = "case"
ROOT                = "root"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LIST                = "..."
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
DOLLAR              = "$"
EURO                = "€"
GRADE               = "º" | "°"
PERCENTAGE          = "%"
DOT                 = "."
STAR                = "*"
BY                  = "·"
DIVIDED_BY          = "/"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
SEMICOLON           = ";"
QUOTE          = "\""
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
PLUS                = "+"
AMPERSAND           = "&"

WORD_KEY            = "word"
RESOURCE_KEY        = "file"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
DOUBLE_TYPE         = "double"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
RATIO_TYPE          = "ratio"
MEASURE_TYPE_KEY    = "measure"
DATE_TYPE           = "date"
BOOLEAN_VALUE_KEY   = "true" | "false"
EMPTY_REF           = "empty"
NATURAL_VALUE_KEY   = {PLUS}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
NOT_CIENTIFICA      = "E" ({PLUS} | {DASH})? {DIGIT}+
DOUBLE_VALUE_KEY    = ({PLUS} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+ {NOT_CIENTIFICA}?
STRING_VALUE_KEY    = {QUOTE} ~ {QUOTE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}
ADDRESS_VALUE       = {AMPERSAND} {DIGIT} {DIGIT} {DIGIT} ({DOT} {DIGIT} {DIGIT} {DIGIT})+
MEASURE_VALUE_KEY   = ([:jletter:] | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE}) ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY})*
DOC_LINE            = "def" ~[\n]

DIGIT               = [:digit:]
IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

SP                  = ([ ]+ | [\t]+) | ">"
SPACES              = {SP}+
NEWLINE             = [\n]+

ANY=.|\n|\"

%xstate QUOTED

%%
<YYINITIAL> {

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
	{WITH}                          {   return TaraTypes.WITH; }
	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }
	{ALWAYS}                        {   return TaraTypes.ALWAYS; }
	{SUB}                           {   return TaraTypes.SUB; }

	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{ABSTRACT}                      {   return TaraTypes.ABSTRACT; }
	{NAMED}                         {   return TaraTypes.NAMED; }
	{INTENTION}                     {   return TaraTypes.INTENTION; }
	{ENCLOSED}                      {   return TaraTypes.ENCLOSED; }
	{FACET}                         {   return TaraTypes.FACET; }
	{COMPONENT}                     {   return TaraTypes.COMPONENT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{PROPERTY}                      {   return TaraTypes.PROPERTY; }
	{ADDRESSED}                     {   return TaraTypes.ADDRESSED; }
	{AGGREGATED}                    {   return TaraTypes.AGGREGATED; }
	{ASSOCIATED}                    {   return TaraTypes.ASSOCIATED; }
	{READONLY}                      {   return TaraTypes.READONLY; }
	{CASE}                          {   return TaraTypes.CASE; }
    {ROOT}                          {   return TaraTypes.ROOT; }

	{DOC_LINE}                      {   yypushback(1); return TaraTypes.DOC_LINE; }

	{ADDRESS_VALUE}                 {   return TaraTypes.ADDRESS_VALUE; }
	{QUOTE}                         {   yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN; }
//	{STRING_VALUE_KEY}              {   return TaraTypes.QUOTE_BEGIN; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }

	{LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{STAR}                          {   return TaraTypes.STAR;     }
	{LIST}                          {   return TaraTypes.LIST;  }
	{PLUS}                          {   return TaraTypes.PLUS;  }

	{WORD_KEY}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}                  {   return TaraTypes.RESOURCE_KEY; }

	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {RATIO_TYPE}                    {   return TaraTypes.RATIO_TYPE; }
    {MEASURE_TYPE_KEY}              {   return TaraTypes.MEASURE_TYPE_KEY; }
	{SEMICOLON}                     {   return TaraTypes.DSL;  }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

    {SP}                            {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}                {   return evaluateIdentifier();  }

    {MEASURE_VALUE_KEY}             {   return TaraTypes.MEASURE_VALUE; }
    {NEWLINE}                       {   return TokenType.WHITE_SPACE; }
}

<QUOTED> {
  {QUOTE}                           { yybegin(YYINITIAL); return TaraTypes.QUOTE_END; }
  [^\n\r\"\\]                       { return TaraTypes.CHARACTER; }
  \\t                               { return TaraTypes.CHARACTER; }
  \\n                               { return TaraTypes.CHARACTER; }
  \\r                               { return TaraTypes.CHARACTER; }
  \\\"                              { return TaraTypes.CHARACTER; }
  \\                                { return TaraTypes.CHARACTER; }
}

[^]                                  {  return TokenType.BAD_CHARACTER; }
