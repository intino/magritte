package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
import com.intellij.psi.TokenType;

%%

%class ::projectProperName::HighlighterLex
%implements FlexLexer
%unicode
%function advance
%type IElementType

SP = ([ ]+ | [\\t]+)
SPACES= {SP}+
NEWLINE= [\\n]+ ([ ] | [\\t])*

//=====================
//Reserved words

IMPORT_KEY = "import"
PACKAGE    = "package"
CASE_KEY  = "case"
BASE_KEY  = "base"
FINAL     = "final"
ABSTRACT  = "abstract"
WORD      = "Word"
VAR       = "var"
RESOURCE_KEY = "Resource"
LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

LEFT_PARENTHESIS  = "("
RIGHT_PARENTHESIS = ")"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

DOT           = "."
COMMA         = ","
COLON         = "\:"
SEMICOLON     = ";"
DOUBLE_COMMAS = "\\""
OPEN_AN  = "<"
CLOSE_AN = ">"
POSITIVE = "+"
NEGATIVE = "-"

ALIAS_TYPE   = "Alias"
INT_TYPE     = "Int"
NATURAL_TYPE = "Natural"
DOUBLE_TYPE  = "Double"
STRING_TYPE  = "String"
BOOLEAN_TYPE = "Boolean"

BOOLEAN_VALUE  = "true" | "false"

//=====================
//Concepts
METAIDENTIFIER = ::concepts::

POSITIVE_VALUE = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE   = {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}
DOC_LINE = "'" ~[\\n]

DIGIT=[\:digit\:]

IDENTIFIER_KEY = [\:jletter:] [\:jletterdigit\:]*


%%
<YYINITIAL> {

	{METAIDENTIFIER}            {   return TaraTypes.METAIDENTIFIER_KEY; }

	{IMPORT_KEY}                {   return TaraTypes.IMPORT_KEY; }

	{PACKAGE}                   {   return TaraTypes.PACKAGE; }

    {BASE_KEY}                  {   return TaraTypes.BASE_KEY; }

	{CASE_KEY}                  {   return TaraTypes.CASE_KEY; }

	{ABSTRACT}                  {   return TaraTypes.ABSTRACT; }

	{FINAL}                     {   return TaraTypes.FINAL; }

	{COLON}                     {   return TaraTypes.COLON; }

	{VAR}                       {   return TaraTypes.VAR; }

	{LIST}                      {   return TaraTypes.LIST; }

	{OPEN_AN}                   {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                  {   return TaraTypes.CLOSE_AN; }

	{DOC_LINE}                  {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE}              {   return TaraTypes.STRING_VALUE_KEY; }
	{BOOLEAN_VALUE}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{POSITIVE_VALUE}            {   return TaraTypes.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}               {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return TaraTypes.RIGHT_SQUARE; }

    {LEFT_PARENTHESIS}          {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}         {   return TaraTypes.RIGHT_PARENTHESIS; }

	{WORD}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}              {   return TaraTypes.RESOURCE_KEY; }

	{DOT}                       {   return TaraTypes.DOT; }

	{ALIAS_TYPE}                {   return TaraTypes.ALIAS_TYPE; }
	{INT_TYPE}                  {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}               {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return TaraTypes.DOUBLE_TYPE; }

	{SEMICOLON}                 {   return TaraTypes.LEFT_SQUARE; }

	{COMMA}                     {   return TaraTypes.COMMA;     }

	{OPEN_BRACKET}              {   return TaraTypes.LEFT_SQUARE; }

	{CLOSE_BRACKET}             {   return TaraTypes.LEFT_SQUARE; }

	{NEWLINE}                   {   return TokenType.WHITE_SPACE; }

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

	{SP}                        {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}            {   return TaraTypes.IDENTIFIER_KEY;}
}

    .                           {  return TokenType.BAD_CHARACTER;}

