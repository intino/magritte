parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* concept NEWLINE* EOF;

header :  box? imports?;
box : BOX headerReference;

imports :  anImport+;
anImport: NEWLINE+ USE headerReference (AS METAMODEL)?;

doc: DOC+;
concept: doc? signature annotations? body?;

signature: ((CASE IDENTIFIER)
         | (metaidentifier IDENTIFIER)
         | (metaidentifier COLON identifierReference IDENTIFIER?)) parameters? ;


parameters : LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS;
parameterList : explicit? parameter (COMMA explicit? parameter)*;
explicit: IDENTIFIER COLON;
parameter :     identifierReference
				| stringValue
		        | booleanValue
		        | naturalValue
		        | integerValue
		        | doubleValue
		        | stringList
		        | booleanList
		        | naturalList
		        | integerList
		        | doubleList
		        | identifierList
		        | metaWord;

metaWord : metaidentifier metaWordNames*;
metaWordNames : DOT IDENTIFIER;

body: NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT;
conceptConstituents: attribute | concept | varInit | facetApply | facetTarget;

facetApply : AS IDENTIFIER parameters? WITH identifierReference;
facetTarget : ON IDENTIFIER body?;

attribute : doc? VAR (aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
| dateAttribute | resource | reference | word) annotations?;

resource         : RESOURCE COLON    IDENTIFIER IDENTIFIER;
word             : WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER STAR? NEWLINE)+ DEDENT;
aliasAttribute   : ALIAS_TYPE   LIST IDENTIFIER (COLON stringValue  | EMPTY)?;
booleanAttribute : BOOLEAN_TYPE LIST IDENTIFIER (COLON booleanValue | EMPTY)?;
stringAttribute  : STRING_TYPE  LIST IDENTIFIER (COLON stringValue  | EMPTY)?;
naturalAttribute : NATURAL_TYPE LIST IDENTIFIER (COLON naturalValue | EMPTY)?;
integerAttribute : INT_TYPE     LIST IDENTIFIER (COLON integerValue | EMPTY)?;
doubleAttribute  : DOUBLE_TYPE  LIST IDENTIFIER (COLON doubleValue  | EMPTY)?;
dateAttribute    : DATE_TYPE    LIST IDENTIFIER (COLON naturalValue | EMPTY)?;
reference        : identifierReference LIST IDENTIFIER  (COLON EMPTY)?       ;

naturalValue: POSITIVE_VALUE;
integerValue: POSITIVE_VALUE | NEGATIVE_VALUE;
doubleValue : POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE;
booleanValue: BOOLEAN_VALUE;
stringValue : STRING_VALUE | STRING_MULTILINE_VALUE_KEY;

stringList : LEFT_SQUARE STRING_VALUE+ RIGHT_SQUARE;
booleanList: LEFT_SQUARE BOOLEAN_VALUE+ RIGHT_SQUARE;
naturalList: LEFT_SQUARE POSITIVE_VALUE+ RIGHT_SQUARE;
integerList: LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE)+ RIGHT_SQUARE;
doubleList : LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE)+ RIGHT_SQUARE;
identifierList : LEFT_SQUARE IDENTIFIER+ RIGHT_SQUARE;

annotations: IS (PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMEABLE | ROOT | PROPERTY)+ ;

varInit : IDENTIFIER COLON (EMPTY
							| identifierReference
							| stringValue
                            | booleanValue
                            | naturalValue
                            | integerValue
                            | doubleValue
                            | identifierList
                            | stringList
                            | booleanList
                            | naturalList
                            | integerList
                            | doubleList);

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER | INTENTION;
