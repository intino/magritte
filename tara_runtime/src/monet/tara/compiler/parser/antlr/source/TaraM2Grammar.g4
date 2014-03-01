parser grammar TaraM2Grammar;
options { tokenVocab=TaraM2Lexer; }

@header{
    package AntlrM2;
}

root: (concept | NEWLINE)* EOF;

extendedConcept : IDENTIFIER (DOT IDENTIFIER)*;
conceptSignature: CONCEPT extendedConcept? modifier? AS IDENTIFIER;

concept: doc? conceptSignature conceptAnnotations? conceptBody?;

conceptBody: NEWLINE_INDENT (conceptConstituent NEWLINE+)+ DEDENT;
conceptConstituent: attribute
                  | reference
                  | word
                  | from
                  | component;

component: doc? conceptSignature componentAnnotations? conceptBody?
         | NEW extendedConcept componentAnnotations?;

from: FROM fromAnnotations? fromBody;
fromBody: NEWLINE_INDENT (fromComponent NEWLINE+)+ DEDENT;

fromComponent: doc? conceptSignature fromComponentAnnotations? conceptBody?
             | NEW extendedConcept fromComponentAnnotations?;

attribute: VAR     UID_TYPE IDENTIFIER stringAssign?
         | VAR     INT_TYPE (IDENTIFIER integerAssign? | LIST IDENTIFIER integerListAssign?)
         | VAR  DOUBLE_TYPE (IDENTIFIER doubleAssign?  | LIST IDENTIFIER doubleListAssign?)
         | VAR NATURAL_TYPE (IDENTIFIER naturalAssign? | LIST IDENTIFIER naturalListAssign?)
         | VAR BOOLEAN_TYPE (IDENTIFIER booleanAssign? | LIST IDENTIFIER booleanListAssign?)
         | VAR  STRING_TYPE (IDENTIFIER stringAssign?  | LIST IDENTIFIER stringListAssign?);

reference: VAR IDENTIFIER (DOT IDENTIFIER)* IDENTIFIER
         | VAR IDENTIFIER (DOT IDENTIFIER)* LIST IDENTIFIER;

word: VAR WORD IDENTIFIER NEWLINE_INDENT (IDENTIFIER NEWLINE+)+ DEDENT;

stringAssign : ASSIGN STRING_VALUE;
booleanAssign: ASSIGN BOOLEAN_VALUE;
integerAssign: ASSIGN (POSITIVE_VALUE | NEGATIVE_VALUE);
doubleAssign : ASSIGN (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE);
naturalAssign: ASSIGN POSITIVE_VALUE;

stringListAssign : ASSIGN LEFT_SQUARE STRING_VALUE+ RIGHT_SQUARE;
booleanListAssign: ASSIGN LEFT_SQUARE BOOLEAN_VALUE+ RIGHT_SQUARE;
integerListAssign: ASSIGN LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE)+ RIGHT_SQUARE;
doubleListAssign : ASSIGN LEFT_SQUARE (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE)+ RIGHT_SQUARE;
naturalListAssign: ASSIGN LEFT_SQUARE POSITIVE_VALUE+ RIGHT_SQUARE;

conceptAnnotations      : OPEN_AN ( ROOT | HAS_CODE | EXTENSIBLE | SINGLETON )+ CLOSE_AN;
componentAnnotations    : OPEN_AN ( MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON )+ CLOSE_AN;
fromAnnotations         : OPEN_AN ( OPTIONAL | MULTIPLE )+ CLOSE_AN;
fromComponentAnnotations: OPEN_AN ( HAS_CODE | EXTENSIBLE | SINGLETON )+ CLOSE_AN;

modifier: ABSTRACT
        | FINAL;

doc: DOC+;