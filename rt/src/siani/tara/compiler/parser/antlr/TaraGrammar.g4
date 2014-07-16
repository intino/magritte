parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE+ (concept  NEWLINE*)* EOF;

header :  box? imports?;
box : BOX headerReference;

imports :  anImport+;
anImport: NEWLINE+ USE headerReference (AS METAMODEL)?;

doc: DOC+;
concept: doc? signature annotations? body?;

signature: ((CASE IDENTIFIER)
         | (metaidentifier IDENTIFIER)
         | (metaidentifier COLON identifierReference IDENTIFIER?) | IDENTIFIER) parameters? ;


parameters : LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS;
parameterList : explicit? parameter (COMMA explicit? parameter)*;
explicit: IDENTIFIER EQUALS;
parameter : identifierReference
			| stringValue+
	        | booleanValue+
	        | naturalValue+ measure?
	        | integerValue+ measure?
	        | doubleValue+ measure?
            | dateValue+
            | codeValue+
	        | metaWord;

metaWord : metaidentifier metaWordNames*;
metaWordNames : DOT IDENTIFIER;

body: NEW_LINE_INDENT ((attribute | concept | varInit | facetApply | facetTarget) NEWLINE+)+ DEDENT;

facetApply : IS identifierReference parameters? (WITH identifierReference)?;
facetTarget : ON identifierReference body?;

attribute : doc? VAR (naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
	| dateAttribute |coordinateAttribute | refAttribute | resource | reference | word) annotations?;

resource         : RESOURCE attributeType IDENTIFIER;
word             : WORD IDENTIFIER NEW_LINE_INDENT (wordNames NEWLINE)+ DEDENT;
wordNames        : IDENTIFIER STAR?;

reference        : identifierReference LIST? IDENTIFIER  (EQUALS EMPTY)?       ;
booleanAttribute : BOOLEAN_TYPE LIST? IDENTIFIER (EQUALS booleanValue | EMPTY)?;
stringAttribute  : STRING_TYPE  LIST? IDENTIFIER (EQUALS stringValue  | EMPTY)?;
naturalAttribute : NATURAL_TYPE attributeType? LIST? IDENTIFIER (EQUALS naturalValue | EMPTY)?;
integerAttribute : INT_TYPE     attributeType? LIST? IDENTIFIER (EQUALS integerValue | EMPTY)?;
doubleAttribute  : DOUBLE_TYPE  attributeType? LIST? IDENTIFIER (EQUALS doubleValue  | EMPTY)?;
dateAttribute    : DATE_TYPE    LIST? IDENTIFIER (EQUALS dateValue | EMPTY)?;
coordinateAttribute  : COORDINATE_TYPE  LIST? IDENTIFIER (EQUALS (coordinateValue | EMPTY))?;
refAttribute         : REFERENCE_TYPE   LIST?  IDENTIFIER (EQUALS (codeValue  | EMPTY))?;

attributeType   : COLON IDENTIFIER;
naturalValue    : NATURAL_VALUE;
integerValue    : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue     : NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE;
booleanValue    : BOOLEAN_VALUE;
stringValue     : STRING_VALUE | STRING_MULTILINE_VALUE_KEY;
codeValue       : CODE_VALUE;
dateValue       : DATE_VALUE;
coordinateValue : COORDINATE_VALUE;

measure : IDENTIFIER | DOLLAR | EURO | PERCENTAGE | GRADE;

annotations: IS (PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMED | ROOT | PROPERTY)+ ;

varInit : IDENTIFIER EQUALS ( EMPTY
				        | identifierReference+
				        | stringValue+
                        | booleanValue+
						| dateValue+
						| codeValue+
						| coordinateValue+
                        | naturalValue+ measure?
                        | integerValue+ measure?
                        | doubleValue+  measure?);


headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER | INTENTION;
