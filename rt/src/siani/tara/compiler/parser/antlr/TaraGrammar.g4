parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* concept NEWLINE* EOF;

header: packet? imports*;

imports: NEWLINE IMPORT headerReference;
packet : PACKAGE (module COLON)? headerReference;
module : (IDENTIFIER DOT)* IDENTIFIER;

concept: doc? signature annotations? body?;
signature: (CASE IDENTIFIER
         | (METAIDENTIFIER modifier? IDENTIFIER)
         | (METAIDENTIFIER COLON identifierReference modifier? IDENTIFIER?)) parameters? ;
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
conceptConstituents: attribute | concept;

identifierList : LEFT_SQUARE IDENTIFIER+ RIGHT_SQUARE;

attribute : (aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
                        | resource | reference | word);

aliasAttribute   : doc? (VAR | PROPERTY) ALIAS_TYPE IDENTIFIER (COLON stringValue)?;
booleanAttribute : doc? (VAR | PROPERTY) BOOLEAN_TYPE ((IDENTIFIER (COLON booleanValue)?) | (LIST IDENTIFIER (COLON booleanList)?));
stringAttribute  : doc? (VAR | PROPERTY) STRING_TYPE ((IDENTIFIER (COLON stringValue)?)   | (LIST IDENTIFIER (COLON stringList)?)) ;
naturalAttribute : doc? (VAR | PROPERTY) NATURAL_TYPE ((IDENTIFIER (COLON naturalValue)?) | (LIST IDENTIFIER (COLON naturalList)?));
integerAttribute : doc? (VAR | PROPERTY) INT_TYPE ((IDENTIFIER (COLON integerValue)?)     | (LIST IDENTIFIER (COLON integerList)?));
doubleAttribute  : doc? (VAR | PROPERTY) DOUBLE_TYPE ((IDENTIFIER (COLON doubleValue)?)   | (LIST IDENTIFIER (COLON doubleList)?)) ;
resource         : doc? (VAR | PROPERTY) RESOURCE COLON IDENTIFIER IDENTIFIER;
reference        : doc? VAR identifierReference LIST? IDENTIFIER;
word             : doc? VAR WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER NEWLINE)+ DEDENT;

naturalValue: POSITIVE_VALUE;
integerValue: POSITIVE_VALUE | NEGATIVE_VALUE;
doubleValue : POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE;
booleanValue: BOOLEAN_VALUE;
stringValue : STRING_VALUE;

stringList : LEFT_SQUARE STRING_VALUE+ RIGHT_SQUARE;
booleanList: LEFT_SQUARE BOOLEAN_VALUE+ RIGHT_SQUARE;
naturalList: LEFT_SQUARE POSITIVE_VALUE+ RIGHT_SQUARE;
integerList: LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE)+ RIGHT_SQUARE;
doubleList : LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE)+ RIGHT_SQUARE;

annotations: OPEN_AN (GENERIC | HAS_NAME | MULTIPLE | REQUIRED | INTENTION | SINGLETON | ROOT)+ CLOSE_AN;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;

modifier: ABSTRACT
        | FINAL
        | BASE;

doc: DOC+;