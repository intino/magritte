parser grammar TaraM2Grammar;
options { tokenVocab=TaraM2Lexer; }

@header{
package monet.tara.compiler.parser.antlr;
}

root: NEWLINE* header? NEWLINE* concept NEWLINE* EOF;

header: packet? imports*;

imports: NEWLINE IMPORT headerReference;
packet : PACKAGE headerReference;

concept: doc? signature annotations? body?;

signature: CASE IDENTIFIER
         | CONCEPT modifier? IDENTIFIER
         | CONCEPT COLON identifierReference (modifier? IDENTIFIER)?;

body: NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT;

conceptConstituents: attribute
                   | reference
                   | word
                   | concept;

reference: VAR identifierReference LIST? variableNames;

word: VAR WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER NEWLINE)+ DEDENT;

attribute: ((VAR | PROPERTY)   ALIAS_TYPE  IDENTIFIER (ASSIGN stringValue)?)
         | ((VAR | PROPERTY) NATURAL_TYPE (variableNames | IDENTIFIER ASSIGN naturalValue | LIST IDENTIFIER (ASSIGN naturalList)?))
         | ((VAR | PROPERTY)     INT_TYPE (variableNames | IDENTIFIER ASSIGN integerValue | LIST IDENTIFIER (ASSIGN integerList)?))
         | ((VAR | PROPERTY)  DOUBLE_TYPE (variableNames | IDENTIFIER ASSIGN doubleValue  | LIST IDENTIFIER  (ASSIGN doubleList)?))
         | ((VAR | PROPERTY) BOOLEAN_TYPE (variableNames | IDENTIFIER ASSIGN booleanValue | LIST IDENTIFIER (ASSIGN booleanList)?))
         | ((VAR | PROPERTY)  STRING_TYPE (variableNames | IDENTIFIER ASSIGN stringValue  | LIST IDENTIFIER  (ASSIGN stringList)?));

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

variableNames: IDENTIFIER (COMMA IDENTIFIER)*;

headerReference: hierarchy* IDENTIFIER;
externalReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;

modifier: ABSTRACT
        | FINAL
        | BASE;

doc: DOC+;