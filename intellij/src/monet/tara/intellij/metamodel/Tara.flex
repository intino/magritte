package monet.tara.intellij.metamodel;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.psi.TaraTypes;
import com.intellij.psi.TokenType;

%%

%class TaraLexer
%implements FlexLexer
%unicode
%column
%function advance
%type IElementType

%{

	private Stack<Integer> stack = new Stack<>();

	private int transformToSpaces(CharSequence chain){
		int value = 0;
		for(int i = 0; i < chain.length(); i++){
			if (chain.charAt(i) == ('\n')) continue;
			if (chain.charAt(i) == ('\t')) value += 4;
			else value += 1;
		}
		return value;
	}

	private IElementType eof(){
    		if (!stack.empty()) {
                stack.pop();
                if (!stack.empty())
                    yypushback(yylength());
                return TaraTypes.DEDENT;
            }
                return TokenType.WHITE_SPACE;
        }

	private IElementType cleanstack(){
		if (!stack.empty()) {
            stack.pop();
            if (!stack.empty() && isTextDedented(transformToSpaces(yytext())))
                yypushback(yylength());
            return TaraTypes.DEDENT;
        }
            return TokenType.WHITE_SPACE;
    }

	private boolean isTextIndented(int textLength){
		if (!stack.empty())
			return textLength > (int)stack.peek();
		return false;
	}

	private boolean isTextDedented(int textLength){
		if (!stack.empty())
            return textLength < (int)stack.peek();
		return false;
    }

	private IElementType calculateIndentationToken() {
		int textLength = transformToSpaces(yytext());
        if (stack.empty() || isTextIndented(textLength)){
            stack.push(textLength);
            return TaraTypes.INDENT;
        } else if (isTextDedented(textLength)) {
            stack.pop();
            if (isTextDedented(textLength))
                yypushback(yylength());
            return TaraTypes.DEDENT;
		} else
            return TokenType.WHITE_SPACE;
	}
%}
WS = ([ ]+ | [\t]+)
EOL=([\r\n] | [\n])
INDENT=[\n]+ ([ ]+ | [\t]+)


//=====================
//Reserved words

CONCEPT   = "Concept"
FROM_KEY  = "from"
AS        = "as"
FINAL     = "final"
ABSTRACT  = "abstract"
MULTIPLE  = "multiple"
OPTIONAL  = "optional"
HAS_CODE  = "has-code"
EXTENSIBLE = "extensible"
WORD      = "Word"
VAR       = "var"
DEDENT    = "}"


LIST = {LEFT_BRACKET}{RIGHT_BRACKET}
LEFT_BRACKET  = "["
RIGHT_BRACKET = "]"
DOT           = "."
ASSIGN        = ":"
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

BOOLEAN_VALUE  = "True" | "False"

NATURAL_VALUE  = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE = {NEGATIVE} {DIGIT}+
DOUBLE_VALUE   = ({POSITIVE} | {NEGATIVE})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE   = {DOUBLE_COMMAS} {ALPHANUMERIC} {DOUBLE_COMMAS}

DOC_CONTENT    = [^\?\/]*
OPEN_DOC_BLOCK = "\/\?"
CLOSE_DOC_BLOCK = "\?\/"
DOC_BLOCK = {OPEN_DOC_BLOCK} ~ {CLOSE_DOC_BLOCK}
DOC_LINE = "??" ~[\n]

STRING_VALUE= {DOUBLE_COMMAS} ([ ] | {ALPHANUMERIC})* {DOUBLE_COMMAS}

//=====================
//Tabs

//=====================
//Digits

DIGIT=[:digit:]

//=====================
//Identifier
IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

//=====================
//Alphanumeric

ALPHANUMERIC= [:jletterdigit:]*

%%
<YYINITIAL> {

	{CONCEPT}                   {  return TaraTypes.CONCEPT_KEY; }

	{ABSTRACT}                  {  return TaraTypes.ABSTRACT; }

	{FINAL}                     {  return TaraTypes.FINAL; }

	{AS}                        {  return TaraTypes.AS; }

	{ASSIGN}                    {  return TaraTypes.ASSIGN; }

	{VAR}                       {  return TaraTypes.VAR; }

	{LIST}                      {  return TaraTypes.LIST; }

	{FROM_KEY}                  {  return TaraTypes.FROM_KEY; }

	{OPEN_AN}                   {  return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                  {  return TaraTypes.CLOSE_AN; }

	{OPTIONAL}                  {  return TaraTypes.OPTIONAL; }
	{MULTIPLE}                  {  return TaraTypes.MULTIPLE; }

	{HAS_CODE}                  {  return TaraTypes.HAS_CODE; }
	{EXTENSIBLE}                {  return TaraTypes.EXTENSIBLE; }

	{DOC_LINE}                  {  return TaraTypes.DOC_LINE; }

	{DOC_BLOCK}                 {  return TaraTypes.DOC_BLOCK; }

	{STRING_VALUE}              {  return TaraTypes.STRING_VALUE; }
	{BOOLEAN_VALUE}             {  return TaraTypes.BOOLEAN_VALUE; }
	{DOUBLE_VALUE}              {  return TaraTypes.DOUBLE_VALUE; }
	{NEGATIVE_VALUE}            {  return TaraTypes.NEGATIVE_VALUE; }
	{NATURAL_VALUE}             {  return TaraTypes.NATURAL_VALUE; }

	{LEFT_BRACKET}              {  return TaraTypes.LEFT_BRACKET; }
	{RIGHT_BRACKET}             {  return TaraTypes.RIGHT_BRACKET; }

	{WORD}                      {  return TaraTypes.WORD; }

	{DOT}                       {  return TaraTypes.DOT; }

	{UID_TYPE}                  {  return TaraTypes.UID_TYPE; }
	{INT_TYPE}                  {  return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}              {  return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}              {  return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}               {  return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}               {  return TaraTypes.DOUBLE_TYPE; }

	{IDENTIFIER_KEY}            {  return TaraTypes.IDENTIFIER_KEY;}

	{INDENT}                    {  return calculateIndentationToken();}
	{DEDENT}                    {  return TaraTypes.DEDENT; }

	{EOL}                       {  return cleanstack();}

	{WS}                        {  return TokenType.WHITE_SPACE;}

	<<EOF>>                     {  return eof();}

.                               {  return TokenType.BAD_CHARACTER;}
}