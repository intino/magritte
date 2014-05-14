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


CONCEPT    = "Concept"
IMPORT_KEY = "import"
PACKAGE    = "package"
CASE_KEY   = "case"
BASE_KEY   = "base"
FINAL     = "final"
ABSTRACT  = "abstract"
MULTIPLE  = "multiple"
OPTIONAL  = "optional"
HAS_CODE  = "has-code"
SINGLETON = "singleton"
INTENTION = "intention"
ROOT      = "root"
EXTENSION_KEY = "extension"
EXTENSIBLE_KEY = "extensible"
GENERIC   = "generic"
WORD_KEY      = "Word"
VAR       = "var"

LIST = {LEFT_SQUARE}{RIGHT_SQUARE}
LEFT_SQUARE  = "["
RIGHT_SQUARE = "]"

OPEN_BRACKET  = "{"
CLOSE_BRACKET = "}"

DOT           = "."
COLON        = ":"
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

	{IMPORT_KEY}                {   return TaraTypes.IMPORT_KEY; }
	{PACKAGE}                   {   return TaraTypes.PACKAGE; }

	{ABSTRACT}                  {   return TaraTypes.ABSTRACT; }

	{FINAL}                     {   return TaraTypes.FINAL; }

	{COLON}                     {   return TaraTypes.COLON; }

	{VAR}                       {   return TaraTypes.VAR; }

	{LIST}                      {   return TaraTypes.LIST; }

	{BASE_KEY}                  {   return TaraTypes.BASE_KEY; }

	{CASE_KEY}                  {   return TaraTypes.CASE_KEY; }

	{OPEN_AN}                   {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                  {   return TaraTypes.CLOSE_AN; }

	{OPTIONAL}                  {   return TaraTypes.OPTIONAL; }
	{MULTIPLE}                  {   return TaraTypes.MULTIPLE; }

	{HAS_CODE}                  {   return TaraTypes.HAS_CODE; }
	{EXTENSIBLE_KEY}            {   return TaraTypes.EXTENSIBLE_KEY; }
	{EXTENSION_KEY}             {   return TaraTypes.EXTENSION_KEY; }
	{ROOT}                      {   return TaraTypes.ROOT; }
	{SINGLETON}                 {   return TaraTypes.SINGLETON; }
	{GENERIC}                   {   return TaraTypes.GENERIC; }
	{INTENTION}                 {   return TaraTypes.INTENTION; }

	{DOC_LINE}                  {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}          {   return TaraTypes.STRING_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}         {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}          {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}        {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}         {   return TaraTypes.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}               {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return TaraTypes.RIGHT_SQUARE; }

	{WORD_KEY}                  {   return TaraTypes.WORD_KEY; }

	{DOT}                       {   return TaraTypes.DOT; }

	{UID_TYPE}                  {   return TaraTypes.UID_TYPE; }
	{INT_TYPE}                  {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}               {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return TaraTypes.DOUBLE_TYPE; }

	{SEMICOLON}                 {   return TaraTypes.LEFT_SQUARE;  }

	{OPEN_BRACKET}              {   return TaraTypes.LEFT_SQUARE; }

	{CLOSE_BRACKET}             {   return TaraTypes.LEFT_SQUARE; }

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

    {SP}                        {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}            {   return TaraTypes.IDENTIFIER_KEY; }

    {NEWLINE}                   {   return TokenType.WHITE_SPACE; }

    .                           {   return TokenType.BAD_CHARACTER; }
}

.                               {  return TokenType.BAD_CHARACTER; }
