parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* imports? (concept NEWLINE*)* EOF;

imports : anImport+;
anImport: USE headerReference (AS IDENTIFIER)? NEWLINE;

doc: DOC+;
concept: doc? signature annotationsAndFacets? body?;

signature: ((SUB parameters? IDENTIFIER) | (metaidentifier parameters? IDENTIFIER? parent?)) address?;

parent : EXTENDS identifierReference;
address: ADDRESS_VALUE;


parameters : LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS;
parameterList : (explicit initValue (COMMA explicit initValue)*) | (initValue (COMMA initValue)*);
explicit: IDENTIFIER EQUALS;

initValue : identifierReference+
			| stringValue+
	        | booleanValue+
	        | naturalValue+ measureValue?
	        | integerValue+ measureValue?
	        | doubleValue+ measureValue?
	        | metaWord
	        | linkValue
	        | EMPTY;

metaWord : metaidentifier metaWordNames*;
metaWordNames : DOT IDENTIFIER;

body: NEW_LINE_INDENT ((variable | concept | varInit | annotationsAndFacets | facetTarget | conceptReference | doc) NEWLINE+)+ DEDENT;

variable : VAR (naturalAttribute | integerAttribute | doubleAttribute |ratioAttribute | measureAttribute|
    booleanAttribute | stringAttribute | dateAttribute | resource | reference | word) (IS annotations)?;

facetApply : metaidentifier parameters? (WITH metaidentifier)? body?;
facetTarget : ON identifierReference ALWAYS? body?;
conceptReference : HAS identifierReference (IS annotations)?;

word             : WORD LIST? IDENTIFIER NEW_LINE_INDENT (wordNames NEWLINE)+ DEDENT;
wordNames        : IDENTIFIER STAR?;

resource             : RESOURCE attributeType IDENTIFIER;
booleanAttribute     : BOOLEAN_TYPE LIST? IDENTIFIER (EQUALS booleanValue+)?;
stringAttribute      : STRING_TYPE  LIST? IDENTIFIER (EQUALS stringValue+)?;
dateAttribute        : DATE_TYPE    LIST? IDENTIFIER (EQUALS stringValue+)?;
ratioAttribute       : RATIO_TYPE   LIST? IDENTIFIER (EQUALS doubleValue+)?;
naturalAttribute     : NATURAL_TYPE doubleMeasure? LIST? IDENTIFIER (EQUALS naturalValue+ measureValue?)?;
integerAttribute     : INT_TYPE     doubleMeasure? LIST? IDENTIFIER (EQUALS integerValue+ measureValue?)?;

doubleAttribute      : DOUBLE_TYPE  (LIST | count)? doubleMeasure? IDENTIFIER (EQUALS doubleValue+ measureValue?)?;
measureAttribute     : MEASURE_TYPE attributeType (LIST | count)? IDENTIFIER (EQUALS doubleValue+ measureValue?)?;
reference            : identifierReference LIST? IDENTIFIER (EQUALS EMPTY)?;

attributeType   : COLON measureType;
measureType     : IDENTIFIER;
doubleMeasure   : COLON (MEASURE_VALUE | IDENTIFIER);
stringValue     : STRING_VALUE | (NEWLINE? STRING_MULTILINE_VALUE_KEY);
booleanValue    : BOOLEAN_VALUE;
naturalValue    : NATURAL_VALUE;
integerValue    : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue     : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;
linkValue       : address | identifierReference;
count : LEFT_SQUARE naturalValue RIGHT_SQUARE;
measureValue : IDENTIFIER | MEASURE_VALUE;



annotationsAndFacets: IS (annotations | facetApply);
annotations: annotation+;

annotation: PLUS? (ABSTRACT | TERMINAL | SINGLE | REQUIRED | READONLY |
              NAMED | FACET | INTENTION | ROOT | COMPONENT | PROPERTY | LOCAL | ADDRESSED | AGGREGATED | CASE);

varInit : IDENTIFIER EQUALS initValue;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;