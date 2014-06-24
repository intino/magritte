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
	private Project project;
	private Set<String> identifiers;

	public TaraHighlighterLex(java.io.Reader o, Project project) {
		this.project = project;
	}

	private IElementType evaluateIdentifier() {
		String identifier = yytext().toString();
		if (identifiers == null) return TaraTypes.IDENTIFIER_KEY;
		return identifiers.contains(identifier) ? TaraTypes.METAIDENTIFIER_KEY : TaraTypes.IDENTIFIER_KEY;
	}

	private void loadHeritage() {
		Module module = ModuleProvider.getNamespaceOfDocument(project, zzBuffer.toString());
		TreeWrapper heritage = TaraLanguage.getHeritage(module);
		if (heritage != null)
			identifiers = heritage.getIdentifiers();
	}
%}

CONCEPT             = "Concept"
IMPORT_KEY          = "import"
BOX                 = "package"
CASE_KEY            = "case"
PRIVATE             = "private"
MULTIPLE            = "multiple"
REQUIRED            = "required"
HAS_NAME            = "has-name"
TERMINAL            = "terminal"
INTENTION_KEY       = "intention"
PROPERTY            = "property"
ROOT                = "root"
WORD_KEY            = "Word"
RESOURCE_KEY        = "Resource"
VAR                 = "var"

LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"

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
CLOSE_AN            = ">"
POSITIVE            = "+"

ALIAS_TYPE          = "Alias"
INT_TYPE            = "Integer"
NATURAL_TYPE        = "Natural"
DOUBLE_TYPE         = "Double"
STRING_TYPE         = "String"
BOOLEAN_TYPE        = "Boolean"
BOOLEAN_VALUE_KEY   = "true" | "false"
NATURAL_VALUE_KEY   = {POSITIVE}? {DIGIT}+
NEGATIVE_VALUE_KEY  = {DASH} {DIGIT}+
DOUBLE_VALUE_KEY    = ({POSITIVE} | {DASH})? {DIGIT}+ {DOT} {DIGIT}+
STRING_VALUE_KEY    = {APOSTROPHE} ~ {APOSTROPHE}
STRING_MULTILINE_VALUE_KEY   = {DASHES} ~ {DASHES}
EMPTY_REF               = "empty"

DOC_LINE = "#" ~[\n]

DIGIT=[:digit:]
IDENTIFIER_KEY = [:jletter:] [:jletterdigit:]*

SP = ([ ]+ | [\t]+)
SPACES= {SP}+
NEWLINE= [\n]+

%%
<YYINITIAL> {

	{CONCEPT}                       {   return TaraTypes.METAIDENTIFIER_KEY; }

	{IMPORT_KEY}                    {   return TaraTypes.IMPORT_KEY; }

	{BOX}                           {  	loadHeritage();return TaraTypes.BOX_KEY; }

	{PRIVATE}                       {   return TaraTypes.PRIVATE; }

	{COLON}                         {   return TaraTypes.COLON; }

	{VAR}                           {   return TaraTypes.VAR; }

	{CASE_KEY}                      {   return TaraTypes.CASE_KEY; }

	{OPEN_AN}                       {   return TaraTypes.OPEN_AN; }
	{CLOSE_AN}                      {   return TaraTypes.CLOSE_AN; }

	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{MULTIPLE}                      {   return TaraTypes.MULTIPLE; }

	{HAS_NAME}                      {   return TaraTypes.HAS_NAME; }
	{ROOT}                          {   return TaraTypes.ROOT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{INTENTION_KEY}                 {   return TaraTypes.INTENTION_KEY; }
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
	{ALIAS_TYPE}                    {   return TaraTypes.ALIAS_TYPE; }
	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }

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
