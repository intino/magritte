parser grammar TaraM2Grammar;
options { tokenVocab=TaraM2Lexer; }

@header{
    package AntlrM2;
}

root: (concept | NEWLINE)* EOF;

concept: doc? signature annotations? body?;

signature: CONCEPT referenceIdentifier? (POLYMORPHIC | modifier? MORPH?) AS IDENTIFIER;

body: NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT;

conceptConstituents: attribute
                   | reference
                   | word
                   | concept
                   | conceptInjection;

reference: VAR referenceIdentifier LIST? variableNames;

word: VAR WORD IDENTIFIER NEW_LINE_INDENT (IDENTIFIER NEWLINE)+ DEDENT;

conceptInjection: NEW referenceIdentifier annotations?;

attribute: VAR     UID_TYPE  IDENTIFIER (ASSIGN stringValue)?
         | VAR     INT_TYPE (variableNames | IDENTIFIER ASSIGN integerValue | LIST IDENTIFIER (ASSIGN integerList)?)
         | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER ASSIGN doubleValue | LIST IDENTIFIER  (ASSIGN doubleList)?)
         | VAR NATURAL_TYPE (variableNames | IDENTIFIER ASSIGN naturalValue | LIST IDENTIFIER (ASSIGN naturalList)?)
         | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER ASSIGN booleanValue | LIST IDENTIFIER (ASSIGN booleanList)?)
         | VAR  STRING_TYPE (variableNames | IDENTIFIER ASSIGN stringValue | LIST IDENTIFIER  (ASSIGN stringList)?);

stringValue : STRING_VALUE;
booleanValue: BOOLEAN_VALUE;
integerValue: POSITIVE_VALUE | NEGATIVE_VALUE;
doubleValue : POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE;
naturalValue: POSITIVE_VALUE;

stringList : LEFT_SQUARE STRING_VALUE+ RIGHT_SQUARE;
booleanList: LEFT_SQUARE BOOLEAN_VALUE+ RIGHT_SQUARE;
integerList: LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE)+ RIGHT_SQUARE;
doubleList : LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE)+ RIGHT_SQUARE;
naturalList: LEFT_SQUARE POSITIVE_VALUE+ RIGHT_SQUARE;

annotations: OPEN_AN (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON | ROOT)+ CLOSE_AN;

variableNames: IDENTIFIER (COMMA IDENTIFIER)*;

referenceIdentifier: IDENTIFIER (DOT IDENTIFIER)*;

modifier: ABSTRACT
        | FINAL;

doc: DOC+;