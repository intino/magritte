parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: imports? NEWLINE* (concept NEWLINE*)* EOF;

imports : anImport+;
anImport: NEWLINE* USE headerReference (AS IDENTIFIER)? NEWLINE;

doc: DOC+;
concept: doc? signature annotations? body?;

signature: ((SUB IDENTIFIER)
			| (metaidentifier parameters? IDENTIFIER (EXTENDS identifierReference)?)
			| metaidentifier parameters?) address?;

address: ADDRESS_VALUE;


parameters : LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS;
parameterList : (explicit initValue (COMMA explicit initValue)*) | (initValue (COMMA initValue)*);
explicit: IDENTIFIER EQUALS;

initValue : identifierReference+
			| stringValue+
	        | booleanValue+
	        | naturalValue+ measure?
	        | integerValue+ measure?
	        | doubleValue+ measure?
            | dateValue+
	        | metaWord
	        | linkValue
	        | EMPTY;

metaWord : metaidentifier metaWordNames*;
metaWordNames : DOT IDENTIFIER;

body: NEW_LINE_INDENT ((variable | concept | varInit | facetApply | facetTarget | conceptReference) NEWLINE+)+ DEDENT;

variable : doc? VAR (naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
	| dateAttribute | resource | reference | word) annotations?;

facetApply : IS metaidentifier parameters? (WITH metaidentifier)? body?;
facetTarget : ON identifierReference ALWAYS? body?;
conceptReference : doc? HAS identifierReference annotations?;

resource         : RESOURCE attributeType IDENTIFIER;
word             : WORD LIST? IDENTIFIER NEW_LINE_INDENT (wordNames NEWLINE)+ DEDENT;
wordNames        : IDENTIFIER STAR?;

reference        : identifierReference LIST? IDENTIFIER  (EQUALS EMPTY)?;
booleanAttribute : BOOLEAN_TYPE LIST? IDENTIFIER (EQUALS booleanValue+ | EMPTY)?;
stringAttribute  : STRING_TYPE  LIST? IDENTIFIER (EQUALS stringValue+  | EMPTY)?;
naturalAttribute : NATURAL_TYPE attributeType? LIST? IDENTIFIER (EQUALS naturalValue+ measure? | EMPTY)?;
integerAttribute : INT_TYPE     attributeType? LIST? IDENTIFIER (EQUALS integerValue+ measure? | EMPTY)?;
doubleAttribute  : DOUBLE_TYPE  attributeType? (LIST | count)? IDENTIFIER (EQUALS doubleValue+  measure? | EMPTY)?;
dateAttribute    : DATE_TYPE    LIST? IDENTIFIER (EQUALS dateValue+ | EMPTY)?;

attributeType   : COLON measure;
naturalValue    : NATURAL_VALUE;
integerValue    : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue     : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;
booleanValue    : BOOLEAN_VALUE;
stringValue     : STRING_VALUE | (NEWLINE? STRING_MULTILINE_VALUE_KEY);
dateValue       : DATE_VALUE;
linkValue       : address | identifierReference;
count : LEFT_SQUARE naturalValue RIGHT_SQUARE;
measure : IDENTIFIER | MEASURE_VALUE;

annotations: IS (PLUS? (ABSTRACT | TERMINAL | SINGLE | REQUIRED | READONLY |
 NAMED | FACET | INTENTION | ROOT | COMPONENT | PROPERTY | LOCAL | ADDRESSED | AGGREGATED))+ ;

varInit : IDENTIFIER EQUALS initValue;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;