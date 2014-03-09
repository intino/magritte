package monet.::projectName::.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;

%%

%class ::projectProperName::HighlighterLex
%implements FlexLexer
%unicode
%function advance
%type IElementType

%{
%}

CONCEPT   = "Definition"
MORPH_KEY  = "morph"
POLYMORPHIC_KEY = "polymorphic"
AS        = "as"
FINAL     = "final"
ABSTRACT  = "abstract"
MULTIPLE  = "multiple"
OPTIONAL  = "optional"
HAS_CODE  = "has-code"
SINGLETON = "singleton"
ROOT      = "root"
EXTENSIBLE = "extensible"
WORD      = "Word"
VAR       = "var"
NEW       = "new"


LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

DOT           = "."
ASSIGN        = ":"
SEMICOLON     = ";"
DOUBLE_COMMAS = "\""
OPEN_AN  = "<"
CLOSE_AN = ">"
POSITIVE = "+"
NEGATIVE = "-"

UID_TYPE     = "Uid"
INT_TYPE     = "Int"
NATURAL_TYPE = "Natural"
DOUBLE_TYPE  = "Double"
STRING_TYPE  = "String"
BOOLEAN_TYPE = "Boolean"

BOOLEAN_VALUE_KEY  = "true" | "false"
NATURAL_VALUE_KEY = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE_KEY   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY= {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}

DOC_LINE = "'" ~[\n]

DIGIT=[:digit:]
IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

SP = ([ ]+ | [\t]+)
SPACES= {SP}+
NEWLINE= [\n]+

%%
<YYINITIAL> {

	{CONCEPT}                   {   return ::projectProperName::Types.CONCEPT_KEY; }

	{ABSTRACT}                  {   return ::projectProperName::Types.ABSTRACT; }

	{FINAL}                     {   return ::projectProperName::Types.FINAL; }

	{AS}                        {   return ::projectProperName::Types.AS; }

	{ASSIGN}                    {   return ::projectProperName::Types.ASSIGN; }

	{VAR}                       {   return ::projectProperName::Types.VAR; }

	{NEW}                       {   return ::projectProperName::Types.NEW; }

	{LIST}                      {   return ::projectProperName::Types.LIST; }

	{POLYMORPHIC_KEY}           {   return ::projectProperName::Types.POLYMORPHIC_KEY; }

	{MORPH_KEY}                 {   return ::projectProperName::Types.MORPH_KEY; }

	{OPEN_AN}                   {   return ::projectProperName::Types.OPEN_AN; }
	{CLOSE_AN}                  {   return ::projectProperName::Types.CLOSE_AN; }

	{OPTIONAL}                  {   return ::projectProperName::Types.OPTIONAL; }
	{MULTIPLE}                  {   return ::projectProperName::Types.MULTIPLE; }

	{HAS_CODE}                  {   return ::projectProperName::Types.HAS_CODE; }
	{EXTENSIBLE}                {   return ::projectProperName::Types.EXTENSIBLE; }
	{ROOT}                      {   return ::projectProperName::Types.ROOT; }
	{SINGLETON}                 {   return ::projectProperName::Types.SINGLETON; }

	{DOC_LINE}                  {   return ::projectProperName::Types.DOC_LINE; }

	{STRING_VALUE_KEY}          {   return ::projectProperName::Types.STRING_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}         {   return ::projectProperName::Types.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}          {   return ::projectProperName::Types.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}        {   return ::projectProperName::Types.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}         {   return ::projectProperName::Types.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}               {   return ::projectProperName::Types.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return ::projectProperName::Types.RIGHT_SQUARE; }

	{WORD}                      {   return ::projectProperName::Types.WORD; }

	{DOT}                       {   return ::projectProperName::Types.DOT; }

	{UID_TYPE}                  {   return ::projectProperName::Types.UID_TYPE; }
	{INT_TYPE}                  {   return ::projectProperName::Types.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return ::projectProperName::Types.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return ::projectProperName::Types.NATURAL_TYPE; }
    {STRING_TYPE}               {   return ::projectProperName::Types.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return ::projectProperName::Types.DOUBLE_TYPE; }

	{IDENTIFIER_KEY}            {   return ::projectProperName::Types.IDENTIFIER_KEY; }

	{SEMICOLON}                 {   return ::projectProperName::Types.LEFT_SQUARE;  }

	{OPEN_BRACKET}              {   return ::projectProperName::Types.LEFT_SQUARE; }

	{CLOSE_BRACKET}             {   return ::projectProperName::Types.LEFT_SQUARE; }

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

    {SP}                        {   return TokenType.WHITE_SPACE; }

    {NEWLINE}                   {   return TokenType.WHITE_SPACE; }

    .                           {   return TokenType.BAD_CHARACTER; }
}

.                               {  return TokenType.BAD_CHARACTER; }
