parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* (concept NEWLINE*)* EOF;

header :  box? imports?;
box : BOX headerReference;

imports :  anImport+;
anImport: NEWLINE+ USE headerReference (AS IDENTIFIER)?;

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
	        | coordinateValue+
            | dateValue+
	        | metaWord
	        | linkValue
	        | EMPTY;

metaWord : metaidentifier metaWordNames*;
metaWordNames : DOT IDENTIFIER;

body: NEW_LINE_INDENT ((attribute | concept | varInit | facetApply | facetTarget | conceptReference) NEWLINE+)+ DEDENT;

attribute : doc? VAR (naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
	| dateAttribute |coordinateAttribute | resource | reference | word) annotations?;

facetApply : IS metaidentifier parameters? (WITH metaidentifier)? body?;
facetTarget : ON identifierReference ALWAYS? body?;
conceptReference : doc? HAS identifierReference IDENTIFIER? (IS AGGREGATED)?;

resource         : RESOURCE attributeType IDENTIFIER;
word             : WORD LIST? IDENTIFIER NEW_LINE_INDENT (wordNames NEWLINE)+ DEDENT;
wordNames        : IDENTIFIER STAR?;

reference        : identifierReference LIST? IDENTIFIER  (EQUALS EMPTY)?;
booleanAttribute : BOOLEAN_TYPE LIST? IDENTIFIER (EQUALS booleanValue+ | EMPTY)?;
stringAttribute  : STRING_TYPE  LIST? IDENTIFIER (EQUALS stringValue+  | EMPTY)?;
naturalAttribute : NATURAL_TYPE attributeType? LIST? IDENTIFIER (EQUALS naturalValue+ measure? | EMPTY)?;
integerAttribute : INT_TYPE     attributeType? LIST? IDENTIFIER (EQUALS integerValue+ measure? | EMPTY)?;
doubleAttribute  : DOUBLE_TYPE  attributeType? LIST? IDENTIFIER (EQUALS doubleValue+  measure? | EMPTY)?;
dateAttribute    : DATE_TYPE    LIST? IDENTIFIER (EQUALS dateValue+ | EMPTY)?;
coordinateAttribute  : COORDINATE_TYPE  LIST? IDENTIFIER (EQUALS (coordinateValue+ | EMPTY))?;

attributeType   : COLON measure;
naturalValue    : NATURAL_VALUE;
integerValue    : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue     : NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE;
booleanValue    : BOOLEAN_VALUE;
stringValue     : STRING_VALUE | STRING_MULTILINE_VALUE_KEY;
dateValue       : DATE_VALUE;
coordinateValue : COORDINATE_VALUE;
linkValue       : address | identifierReference;

measure : IDENTIFIER | DOLLAR | EURO | PERCENTAGE | GRADE;

annotations: IS (ABSTRACT | TERMINAL | SINGLE | REQUIRED | NAMED | FACET | INTENTION | COMPONENT | PROPERTY | UNIVERSAL | ADDRESSED | AGGREGATED)+ ;

varInit : IDENTIFIER COLON initValue;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;