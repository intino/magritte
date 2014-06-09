parser grammar TaraM2Grammar;
options { tokenVocab=TaraM2Lexer; }

@header{
package siani.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* concept NEWLINE* EOF;

header: packet? imports*;

imports: NEWLINE IMPORT headerReference;
packet : PACKAGE headerReference;

concept: doc? signature annotations? body?;
withHeritage : metaIdentifier COLON identifierReference modifier? identifier?;
signature: (CASE IDENTIFIER
         | METAIDENTIFIER modifier? IDENTIFIER
         | withHeritage) parameters? ;
parameters : LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS;
parameterList : explicit? parameter (COMMA explicit? parameter)*;
explicit: identifier COLON;
body: NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT;
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
		        
conceptConstituents: attribute
                   | reference
                   | word
                   | concept;

reference: doc? VAR identifierReference LIST? IDENTIFIER;

word: VAR WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER NEWLINE)+ DEDENT;

attribute : doc? (aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute
                        | resource | reference | word);

aliasAttribute   : (VAR | PROPERTY) ALIAS_TYPE IDENTIFIER (COLON stringValue)?;
booleanAttribute : (VAR | PROPERTY) BOOLEAN_TYPE ((IDENTIFIER (COLON booleanValue)?) | (LIST IDENTIFIER (COLON booleanList)?));
stringAttribute  : (VAR | PROPERTY) STRING_TYPE ((IDENTIFIER (COLON stringValue)?)   | (LIST IDENTIFIER (COLON stringList)?)) ;
naturalAttribute : (VAR | PROPERTY) NATURAL_TYPE ((IDENTIFIER (COLON naturalValue)?) | (LIST IDENTIFIER (COLON naturalList)?));
integerAttribute : (VAR | PROPERTY) INT_TYPE ((IDENTIFIER (COLON integerValue)?)     | (LIST IDENTIFIER (COLON integerList)?));
doubleAttribute  : (VAR | PROPERTY) DOUBLE_TYPE ((IDENTIFIER (COLON doubleValue)?)   | (LIST IDENTIFIER (COLON doubleList)?)) ;
resource         : (VAR | PROPERTY) RESOURCE_KEY COLON IDENTIFIER IDENTIFIER;

resource: (VAR | PROPERTY) RESOURCE COLON IDENTIFIER IDENTIFIER;

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