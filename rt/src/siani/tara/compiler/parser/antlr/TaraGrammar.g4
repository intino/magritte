parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* concept NEWLINE* EOF;

header :  box? imports*;

imports: NEWLINE+ IMPORT headerReference;
box : BOX headerReference;

concept: doc? signature annotations? body?;
signature: ((CASE IDENTIFIER)
         | (metaidentifier IDENTIFIER)
         | (metaidentifier COLON identifierReference IDENTIFIER?)) parameters? ;

metaidentifier: METAIDENTIFIER | IDENTIFIER;

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
		        | identifierList;


body: NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT;
conceptConstituents: attribute | concept | varInit;

identifierList : LEFT_SQUARE IDENTIFIER+ RIGHT_SQUARE;
varInit : IDENTIFIER COLON (       EMPTY
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

attribute : (aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
                        | resource | reference | word) annotations?;

aliasAttribute   : doc? VAR ALIAS_TYPE IDENTIFIER (COLON stringValue | EMPTY)?                   ;
booleanAttribute : doc? VAR BOOLEAN_TYPE (IDENTIFIER (COLON booleanValue | EMPTY)?)              ;
stringAttribute  : doc? VAR STRING_TYPE (IDENTIFIER (COLON stringValue | EMPTY)?)                ;
naturalAttribute : doc? VAR NATURAL_TYPE (IDENTIFIER (COLON naturalValue | EMPTY)?)              ;
integerAttribute : doc? VAR INT_TYPE (IDENTIFIER (COLON integerValue | EMPTY)?)                  ;
doubleAttribute  : doc? VAR DOUBLE_TYPE (IDENTIFIER (COLON doubleValue | EMPTY)?)                ;
resource         : doc? VAR RESOURCE COLON IDENTIFIER IDENTIFIER                                 ;
reference        : doc? VAR identifierReference IDENTIFIER  (COLON EMPTY)?                       ;
word             : doc? VAR WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER STAR? NEWLINE)+ DEDENT   ;

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

annotations: OPEN_AN (PRIVATE | HAS_NAME | MULTIPLE | REQUIRED | INTENTION | TERMINAL | ROOT | PROPERTY)+ CLOSE_AN;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;

doc: DOC+;