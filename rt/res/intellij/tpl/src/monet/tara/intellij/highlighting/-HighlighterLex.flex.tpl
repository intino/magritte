package monet.::projectName::.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.metamodel.psi.::projectNameFile::Types;
import com.intellij.psi.TokenType;

\%\%

\%class ::projectNameFile::HighlighterLex
\%implements FlexLexer
\%unicode
\%function advance
\%type IElementType


SP = ([ ]+ | [\\t]+)
SPACES= {SP}+
NEWLINE= [\\n]+ ([ ] | [\\t])*

//=====================
//Reserved words
SYNTHESIZE= "synthesize"
MORPH_KEY = "Morph"
POLYMORPHIC_KEY = "Polymorphic"
MORPH_KEY = "Morph"
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
COMMA         = ","
ASSIGN        = ":"
SEMICOLON     = ";"
DOUBLE_COMMAS = "\\""
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

BOOLEAN_VALUE  = "true" | "false"

//=====================
//Concepts
::concepts::

POSITIVE_VALUE = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE   = {DOUBLE_COMMAS} ~ {DOUBLE_COMMAS}
::codeStatement::
DOC_LINE = "'" ~[\\n]

DIGIT=[:digit:]

IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*



\%\%
<YYINITIAL> {

    {SYNTHESIZE}                {   return ::projectNameFile::Types.SYNTHESIZE; }

    {POLYMORPHIC_KEY}           {   return ::projectNameFile::Types.POLYMORPHIC_KEY; }

	{MORPH_KEY}                 {   return ::projectNameFile::Types.MORPH_KEY; }

	{ABSTRACT}                  {   return ::projectNameFile::Types.ABSTRACT; }

	{FINAL}                     {   return ::projectNameFile::Types.FINAL; }

	{AS}                        {   return ::projectNameFile::Types.AS; }

	{ASSIGN}                    {   return ::projectNameFile::Types.ASSIGN; }

	{VAR}                       {   return ::projectNameFile::Types.VAR; }

	{NEW}                       {   return ::projectNameFile::Types.NEW; }

	{LIST}                      {   return ::projectNameFile::Types.LIST; }

	{OPEN_AN}                   {   return ::projectNameFile::Types.OPEN_AN; }
	{CLOSE_AN}                  {   return ::projectNameFile::Types.CLOSE_AN; }

	{OPTIONAL}                  {   return ::projectNameFile::Types.OPTIONAL; }
	{MULTIPLE}                  {   return ::projectNameFile::Types.MULTIPLE; }

	{HAS_CODE}                  {   return ::projectNameFile::Types.HAS_CODE; }
	{EXTENSIBLE}                {   return ::projectNameFile::Types.EXTENSIBLE; }
	{ROOT}                      {   return ::projectNameFile::Types.ROOT; }
	{SINGLETON}                 {   return ::projectNameFile::Types.SINGLETON; }

	{DOC_LINE}                       {   return ::projectNameFile::Types.DOC_LINE; }

	{STRING_VALUE}              {   return ::projectNameFile::Types.STRING_VALUE_KEY; }
::codeStatementFunction::
	{BOOLEAN_VALUE}             {   return ::projectNameFile::Types.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE}              {   return ::projectNameFile::Types.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE}            {   return ::projectNameFile::Types.NEGATIVE_VALUE_KEY; }
	{POSITIVE_VALUE}            {   return ::projectNameFile::Types.NATURAL_VALUE_KEY; }

	{LEFT_SQUARE}               {   return ::projectNameFile::Types.LEFT_SQUARE; }
	{RIGHT_SQUARE}              {   return ::projectNameFile::Types.RIGHT_SQUARE; }

	{WORD}                      {   return ::projectNameFile::Types.WORD_KEY; }

	{DOT}                       {   return ::projectNameFile::Types.DOT; }


	{UID_TYPE}                  {   return ::projectNameFile::Types.UID_TYPE; }
	{INT_TYPE}                  {   return ::projectNameFile::Types.INT_TYPE; }
	{BOOLEAN_TYPE}              {   return ::projectNameFile::Types.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {   return ::projectNameFile::Types.NATURAL_TYPE; }
    {STRING_TYPE}               {   return ::projectNameFile::Types.STRING_TYPE; }
    {DOUBLE_TYPE}               {   return ::projectNameFile::Types.DOUBLE_TYPE; }

	{SEMICOLON}                 {   return ::projectNameFile::Types.LEFT_SQUARE; }

	{COMMA}                     {   return ::projectNameFile::Types.COMMA;     }

	{OPEN_BRACKET}              {   return ::projectNameFile::Types.LEFT_SQUARE;}

	{CLOSE_BRACKET}             {   return ::projectNameFile::Types.LEFT_SQUARE;}

	{NEWLINE}                   {   return TokenType.WHITE_SPACE;}

	{SPACES}                    {   return TokenType.WHITE_SPACE; }

	{SP}                        {   return TokenType.WHITE_SPACE; }

::conceptsToBNF::

{IDENTIFIER_KEY}                {   return ::projectNameFile::Types.IDENTIFIER_KEY;}
}

.                               {  return TokenType.BAD_CHARACTER;}



@concepts
	::conceptKey::= "::conceptValue::"
@conceptsToBNF
    {::conceptKey::}                {  return ::projectNameFile::Types.::conceptKey::;  }
