package monet.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.metamodel.psi.TaraTypes;

%%

%class TaraHighlighterLex
%implements FlexLexer
%unicode
%function advance
%type IElementType

%{
%}

CONCEPT   = "Concept"
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

	{CONCEPT}                   {   return TaraTypes.CONCEPT_KEY; }

	{ABSTRACT}                  {   return TaraTypes.ABSTRACT; }

	{FINAL}                     {   return TaraTypes.FINAL; }

	{AS}                        {   return TaraTypes.AS; }

	{ASSIGN}                    {   return TaraTypes.ASSIGN; }

	{VAR}                       {   return TaraTypes.VAR; }

	{NEW}                       {   return TaraTypes.NEW; }

	{LIST}                      {   return TaraTypes.LIST; }

	{POLYMORPHIC_KEY}           {   return TaraTypes.POLYMORPHIC_KEY; }

	{MORPH_KEY}                 {   return TaraTypes.MORPH_KEY; }

	{OPEN_AN}                   {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                  {   return TaraTypes.CLOSE_AN; }

	{OPTIONAL}                  {   return TaraTypes.OPTIONAL; }
	{MULTIPLE}                  {   return TaraTypes.MULTIPLE; }

	{HAS_CODE}                  {   return TaraTypes.HAS_CODE; }
	{EXTENSIBLE}                {   return TaraTypes.EXTENSIBLE; }
	{ROOT}                      {   return TaraTypes.ROOT; }
	{SINGLETON}                 {   return TaraTypes.SINGLETON; }

	{DOC_LINE}                  {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE; }
	{NATURAL_VALUE_KEY}            {   return TaraTypes.NATURAL_VALUE; }

	{LEFT_SQUARE}               {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return TaraTypes.RIGHT_SQUARE; }

	{WORD}                      {   return TaraTypes.WORD; }

	{DOT}                       {   return TaraTypes.DOT; }

	{UID_TYPE}                  {   return TaraTypes.UID_TYPE; }
	{INT_TYPE}                  {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}               {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return TaraTypes.DOUBLE_TYPE; }

	{IDENTIFIER_KEY}            {   return TaraTypes.IDENTIFIER_KEY; }

	{SEMICOLON}                 {   return TaraTypes.LEFT_SQUARE;  }

	{OPEN_BRACKET}              {   return TaraTypes.LEFT_SQUARE; }

	{CLOSE_BRACKET}             {   return TaraTypes.LEFT_SQUARE; }

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

    {SP}                        {   return TokenType.WHITE_SPACE; }

    {NEWLINE}                   {   return TokenType.WHITE_SPACE; }

    .                           {   return TokenType.BAD_CHARACTER; }
}

.                               {  return TokenType.BAD_CHARACTER; }
