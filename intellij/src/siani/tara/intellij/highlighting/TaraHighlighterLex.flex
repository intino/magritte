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
		String[] uses = zzBuffer.toString().split("use");
        String destiny = null;
        for (String use : uses)
            if (use.contains("as metamodel")) {
                destiny = use.split("as metamodel")[0].trim();
                break;
            }
        Model heritage = TaraLanguage.getMetaModel(destiny, project);
        if (heritage != null)
            identifiers = heritage.getIdentifiers();
}
%}

CONCEPT             = "Concept"
METAMODEL           = "metamodel"
SUB                 = "sub"
HAS                 = "has"
EXTENDS             = "extends"
USE_KEY             = "use"
BOX                 = "box"
AS                  = "as"
ON                  = "on"
IS                  = "is"
ALWAYS              = "always"
WITH                = "with"
//annotations
PRIVATE             = "private"
SINGLE              = "single"
REQUIRED            = "required"
NAMED               = "named"
TERMINAL            = "terminal"
PROPERTY            = "property"
INTENTION           = "intention"
FACET               = "facet"
COMPONENT           = "component"
VAR                 = "var"
WORD_KEY            = "word"
RESOURCE_KEY        = "resource"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LIST                = "..."
OPEN_BRACKET        = "{"
CLOSE_BRACKET       = "}"
DOLLAR              = "$"
EURO                = "€"
PERCENTAGE          = "%"
DOT                 = "."
STAR                = "*"
COMMA               = ","
COLON               = ":"
EQUALS              = "="
SEMICOLON           = ";"
APOSTROPHE          = "'"
DASH                = "-"
UNDERDASH           = "_"
DASHES              = {DASH} {DASH}+
POSITIVE            = "+"
AMPERSAND           = "&"

PORT_TYPE           = "port"
INT_TYPE            = "integer"
NATURAL_TYPE        = "natural"
COORDINATE_TYPE     = "coordinate"
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
DATE_VALUE_KEY      = (({NATURAL_VALUE_KEY} {DASH})+ {NATURAL_VALUE_KEY}) |{NATURAL_VALUE_KEY}
COORDINATE_VALUE_KEY= (({DOUBLE_VALUE_KEY} {DASH})+ {DOUBLE_VALUE_KEY}) |{DOUBLE_VALUE_KEY}
CODE_VALUE_KEY      = {AMPERSAND} [:jletterdigit:]+

DOC_LINE            = "#" ~[\n]

DIGIT=[:digit:]
IDENTIFIER_KEY = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

SP = ([ ]+ | [\t]+) | ">"
SPACES= {SP}+
NEWLINE= [\n]+

%%
<YYINITIAL> {

	{CONCEPT}                       {   return TaraTypes.METAIDENTIFIER_KEY; }

	{BOX}                           {  	loadHeritage();
										return TaraTypes.BOX_KEY; }
	{USE_KEY}                       {   return TaraTypes.USE_KEY; }
	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{HAS}                           {   return TaraTypes.HAS; }
	{EXTENDS}                       {   return TaraTypes.EXTENDS; }
	{AS}                            {   return TaraTypes.AS; }
	{ON}                            {   return TaraTypes.ON; }
	{IS}                            {   return TaraTypes.IS; }
	{WITH}                          {   return TaraTypes.WITH; }
	{COLON}                         {   return TaraTypes.COLON; }
	{EQUALS}                        {   return TaraTypes.EQUALS; }
	{ALWAYS}                        {   return TaraTypes.ALWAYS; }
	{VAR}                           {   return TaraTypes.VAR; }
	{SUB}                           {   return TaraTypes.SUB; }

	{REQUIRED}                      {   return TaraTypes.REQUIRED; }
	{SINGLE}                        {   return TaraTypes.SINGLE; }
	{PRIVATE}                       {   return TaraTypes.PRIVATE; }
	{NAMED}                         {   return TaraTypes.NAMED; }
	{INTENTION}                     {   return TaraTypes.INTENTION; }
	{FACET}                         {   return TaraTypes.FACET; }
	{COMPONENT}                     {   return TaraTypes.COMPONENT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{PROPERTY}                      {   return TaraTypes.PROPERTY; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{DATE_VALUE_KEY}                {   return TaraTypes.DATE_VALUE_KEY; }
	{EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }
	{CODE_VALUE_KEY}                {   return TaraTypes.CODE_VALUE_KEY; }
	{COORDINATE_VALUE_KEY}          {   return TaraTypes.COORDINATE_VALUE_KEY; }

	{LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

	{DOLLAR}                        {   return TaraTypes.DOLLAR;}
    {EURO}                          {   return TaraTypes.EURO;}
    {PERCENTAGE}                    {   return TaraTypes.PERCENTAGE;}
	{DOT}                           {   return TaraTypes.DOT; }
	{COMMA}                         {   return TaraTypes.COMMA; }
	{STAR}                          {   return TaraTypes.STAR;     }
	{LIST}                          {   return TaraTypes.LIST;  }

	{WORD_KEY}                      {   return TaraTypes.WORD_KEY; }
	{RESOURCE_KEY}                  {   return TaraTypes.RESOURCE_KEY; }

	{INT_TYPE}                      {   return TaraTypes.INT_TYPE; }
	{BOOLEAN_TYPE}                  {   return TaraTypes.BOOLEAN_TYPE; }
	{NATURAL_TYPE}                  {   return TaraTypes.NATURAL_TYPE; }
    {STRING_TYPE}                   {   return TaraTypes.STRING_TYPE; }
    {DOUBLE_TYPE}                   {   return TaraTypes.DOUBLE_TYPE; }
    {DATE_TYPE}                     {   return TaraTypes.DATE_TYPE; }
    {COORDINATE_TYPE}               {   return TaraTypes.COORDINATE_TYPE; }
	{PORT_TYPE}                     {   return TaraTypes.PORT_TYPE; }
	{SEMICOLON}                     {   return TaraTypes.VAR;  }

	{OPEN_BRACKET}                  {   return TaraTypes.DOT; }

	{CLOSE_BRACKET}                 {   return TaraTypes.DOT; }

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

    {SP}                            {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}                {   return evaluateIdentifier();  }

    {NEWLINE}                       {   return TokenType.WHITE_SPACE; }

    .                               {   return TokenType.BAD_CHARACTER; }
}

.                                   {  return TokenType.BAD_CHARACTER; }
