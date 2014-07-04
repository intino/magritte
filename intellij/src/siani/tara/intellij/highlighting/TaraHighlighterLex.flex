package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.lang.TreeWrapper;

import java.util.Set;

%%

%class TaraHighlighterLex
%implements FlexLexer
%unicode
%function advance
%type IElementType

%{
	private Set<String> identifiers;


	private IElementType evaluateIdentifier() {
		String identifier = yytext().toString();
		if (identifiers == null) return TaraTypes.IDENTIFIER_KEY;
		return identifiers.contains(identifier) ? TaraTypes.METAIDENTIFIER_KEY : TaraTypes.IDENTIFIER_KEY;
	}

	private void loadHeritage() {
		String[] uses = zzBuffer.toString().split("use");
        String destiny = null;
        for (String use : uses)
            if (use.contains("as metamodel")) {
                destiny = use.split("as metamodel")[0].trim();
                break;
            }
        TreeWrapper heritage = TaraLanguage.getHeritage(destiny);
        if (heritage != null)
            identifiers = heritage.getIdentifiers();
}
%}

CONCEPT             = "Concept"
INTENTION_KEY       = "Intention"
METAMODEL           = "metamodel"
CASE_KEY            = "case"
USE_KEY             = "use"
BOX                 = "box"
AS                  = "as"
ON                  = "on"
IS                  = "is"
WITH                = "with"
//annotations
PRIVATE             = "private"
SINGLE              = "single"
REQUIRED            = "required"
NAMEABLE            = "nameable"
TERMINAL            = "terminal"
PROPERTY            = "property"
ROOT                = "root"
VAR                 = "var"
WORD_KEY            = "word"
RESOURCE_KEY        = "resource"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LIST                = {LEFT_SQUARE} {RIGHT_SQUARE}
OPEN_BRACKET        = "{"
CLOSE_BRACKET       = "}"

DOT                 = "."
STAR                = "*"
COMMA               = ","
COLON               = ":"
SEMICOLON           = ";"
APOSTROPHE          = "'"
DASH                = "-"
DASHES              = {DASH} {DASH}+
OPEN_AN             = "<"
POSITIVE            = "+"

ALIAS_TYPE          = "alias"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
DOUBLE_TYPE         = "double"
STRING_TYPE         = "string"
BOOLEAN_TYPE        = "boolean"
DATE_TYPE           = "date"
BOOLEAN_VALUE_KEY   = "true" | "false"
EMPTY_REF           = "empty"
NATURAL_VALUE_KEY   = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({POSITIVE} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY    = {APOSTROPHE} ~ {APOSTROPHE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}

DOC_LINE            = "#" ~[\n]

DIGIT=[:digit:]
IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

SP = ([ ]+ | [\t]+)
SPACES= {SP}+
NEWLINE= [\n]+

%%
<YYINITIAL> {

	{CONCEPT}                       {   return TaraTypes.METAIDENTIFIER_KEY; }
	{INTENTION_KEY}                 {   return TaraTypes.INTENTION_KEY; }

	{BOX}                           {  	loadHeritage();
										return TaraTypes.BOX_KEY; }
	{USE_KEY}                       {   return TaraTypes.USE_KEY; }
	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{AS}                            {   return TaraTypes.AS; }
	{ON}                            {   return TaraTypes.ON; }
	{IS}                            {   return TaraTypes.IS; }
	{WITH}                          {   return TaraTypes.WITH; }
	{COLON}                         {   return TaraTypes.COLON; }

	{VAR}                           {   return TaraTypes.VAR; }

	{CASE_KEY}                      {   return TaraTypes.CASE_KEY; }

	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }
	{NAMEABLE}                      {   return TaraTypes.NAMEABLE; }
	{ROOT}                          {   return TaraTypes.ROOT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{PROPERTY}                      {   return TaraTypes.PROPERTY; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }
	{LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD_KEY}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}                  {   return TaraTypes.RESOURCE_KEY; }

	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{STAR}                          {   return TaraTypes.STAR;     }
	{LIST}                          {   return TaraTypes.LIST;  }

	{ALIAS_TYPE}                    {   return TaraTypes.ALIAS_TYPE; }
	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }

	{SEMICOLON}                     {   return TaraTypes.LEFT_SQUARE;  }

	{OPEN_BRACKET}                  {   return TaraTypes.LEFT_SQUARE; }

	{CLOSE_BRACKET}                 {   return TaraTypes.LEFT_SQUARE; }

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

    {SP}                            {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}                {   return evaluateIdentifier();  }

    {NEWLINE}                       {   return TokenType.WHITE_SPACE; }

    .                               {   return TokenType.BAD_CHARACTER; }
}

.                                   {  return TokenType.BAD_CHARACTER; }
