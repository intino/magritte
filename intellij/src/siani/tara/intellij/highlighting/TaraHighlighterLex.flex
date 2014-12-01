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
SUB                 = "sub"
HAS                 = "has"
EXTENDS             = "extends"
USE_KEY             = "use"
BOX                 = "box"
AS                  = "as"
ON                  = "on"
IS                  = "is"
VAR                 = "var"
METAMODEL           = "metamodel"

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
UNIVERSAL           = "universal"
FACET               = "facet"
ADDRESSED           = "addressed"
COMPONENT           = "component"
AGGREGATED          = "aggregated"
COMPOSED            = "composed"
MULTIPLE            = "multiple"
ROOT                = "root"

LEFT_PARENTHESIS    = "("
RIGHT_PARENTHESIS   = ")"
LIST                = "..."
LEFT_SQUARE         = "["
RIGHT_SQUARE        = "]"
DOLLAR              = "$"
EURO                = "€"
GRADE               = "º"
PERCENTAGE          = "%"
DOT                 = "."
STAR                = "*"
BY                  = "·"
DIVIDED_BY          = "/"
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

WORD_KEY            = "word"
RESOURCE_KEY        = "resource"
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
DATE_VALUE_KEY      = (({NATURAL_VALUE_KEY} {DASH})+ {NATURAL_VALUE_KEY}) |{NATURAL_VALUE_KEY}
ADDRESS_VALUE       = {AMPERSAND} {DIGIT} {DIGIT} {DIGIT} ({DOT} {DIGIT} {DIGIT} {DIGIT})+
MEASURE_VALUE       = ([:jletterdigit:] | {UNDERDASH} | {DASH}| {BY} | {DIVIDED_BY} | {PERCENTAGE} | {DOLLAR}| {EURO} | {GRADE})*
DOC_LINE            = "#" ~[\n]

DIGIT               = [:digit:]
IDENTIFIER_KEY      = [:jletter:] ([:jletterdigit:] | {UNDERDASH} | {DASH})*

SP                  = ([ ]+ | [\t]+) | ">"
SPACES              = {SP}+
NEWLINE             = [\n]+

%%
<YYINITIAL> {

	{CONCEPT}                       {   return TaraTypes.METAIDENTIFIER_KEY; }

	{METAMODEL}                     {   return TaraTypes.METAMODEL; }

	{BOX}                           {  	loadHeritage();
										return TaraTypes.BOX_KEY; }
	{USE_KEY}                       {   return TaraTypes.USE_KEY; }
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
	{ABSTRACT}                       {   return TaraTypes.ABSTRACT; }
	{NAMED}                         {   return TaraTypes.NAMED; }
	{INTENTION}                     {   return TaraTypes.INTENTION; }
	{UNIVERSAL}                     {   return TaraTypes.UNIVERSAL; }
	{FACET}                         {   return TaraTypes.FACET; }
	{COMPONENT}                     {   return TaraTypes.COMPONENT; }
	{TERMINAL}                      {   return TaraTypes.TERMINAL; }
	{PROPERTY}                      {   return TaraTypes.PROPERTY; }
	{ADDRESSED}                     {   return TaraTypes.ADDRESSED; }
	{AGGREGATED}                    {   return TaraTypes.AGGREGATED; }
	{COMPOSED}                      {   return TaraTypes.COMPOSED; }
    {ROOT}                          {   return TaraTypes.ROOT; }
    {MULTIPLE}                      {   return TaraTypes.MULTIPLE; }

	{DOC_LINE}                      {   return TaraTypes.DOC_LINE; }

	{ADDRESS_VALUE}                 {   return TaraTypes.ADDRESS_VALUE; }
	{STRING_VALUE_KEY}              {   return TaraTypes.STRING_VALUE_KEY; }
	{STRING_MULTILINE_VALUE_KEY}    {   return TaraTypes.STRING_MULTILINE_VALUE_KEY; }
	{BOOLEAN_VALUE_KEY}             {   return TaraTypes.BOOLEAN_VALUE_KEY; }
	{DOUBLE_VALUE_KEY}              {   return TaraTypes.DOUBLE_VALUE_KEY; }
	{NEGATIVE_VALUE_KEY}            {   return TaraTypes.NEGATIVE_VALUE_KEY; }
	{NATURAL_VALUE_KEY}             {   return TaraTypes.NATURAL_VALUE_KEY; }
	{DATE_VALUE_KEY}                {   return TaraTypes.DATE_VALUE_KEY; }
	{EMPTY_REF}                     {   return TaraTypes.EMPTY_REF; }

	{LEFT_PARENTHESIS}              {   return TaraTypes.LEFT_PARENTHESIS; }
    {RIGHT_PARENTHESIS}             {   return TaraTypes.RIGHT_PARENTHESIS; }

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
	{SEMICOLON}                     {   return TaraTypes.BOX_KEY;  }

	{LEFT_SQUARE}                   {   return TaraTypes.LEFT_SQUARE; }
	{RIGHT_SQUARE}                  {   return TaraTypes.RIGHT_SQUARE; }

	{SPACES}                        {   return TokenType.WHITE_SPACE; }

    {SP}                            {   return TokenType.WHITE_SPACE; }

	{IDENTIFIER_KEY}                {   return evaluateIdentifier();  }

	{MEASURE_VALUE}                 {   return TaraTypes.MEASURE_VALUE; }

    {NEWLINE}                       {   return TokenType.WHITE_SPACE; }

    .                               {   return TokenType.BAD_CHARACTER; }
}

.                                   {  return TokenType.BAD_CHARACTER; }
