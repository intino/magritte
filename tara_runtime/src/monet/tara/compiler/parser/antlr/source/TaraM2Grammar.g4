parser grammar TaraM2Grammar;
options { tokenVocab=TaraM2Lexer; }

@header{
    package AntlrM2;
}

root: (concept | NEWLINE)* EOF;

extendedConcept: IDENTIFIER (DOT IDENTIFIER)*;

concept: doc? (CONCEPT| extendedConcept) modifier? AS IDENTIFIER conceptAnnotations? conceptBody?;

conceptBody: NEWLINE INDENT (conceptConstituent NEWLINE+)+ DEDENT;
conceptConstituent: attribute
                  | reference
                  | word
                  | from
                  | component;

component: doc? (CONCEPT| extendedConcept) modifier? AS IDENTIFIER componentAnnotations? conceptBody?
         | extendedConcept componentAnnotations?;

from: FROM fromAnnotations? fromBody;
fromBody: NEWLINE INDENT (fromComponent NEWLINE+)+ DEDENT;

fromComponent: doc? (CONCEPT| extendedConcept) modifier? AS IDENTIFIER fromConceptAnnotations? conceptBody?
             | extendedConcept fromConceptAnnotations?;

attribute: VAR     UID_TYPE IDENTIFIER stringAssign?
         | VAR     INT_TYPE (IDENTIFIER integerAssign? | LIST IDENTIFIER integerListAssign?)
         | VAR  DOUBLE_TYPE (IDENTIFIER doubleAssign?  | LIST IDENTIFIER doubleListAssign?)
         | VAR NATURAL_TYPE (IDENTIFIER naturalAssign? | LIST IDENTIFIER naturalListAssign?)
         | VAR BOOLEAN_TYPE (IDENTIFIER booleanAssign? | LIST IDENTIFIER booleanListAssign?)
         | VAR  STRING_TYPE (IDENTIFIER stringAssign?  | LIST IDENTIFIER stringListAssign?);

reference: VAR IDENTIFIER (DOT IDENTIFIER)* IDENTIFIER
         | VAR IDENTIFIER (DOT IDENTIFIER)* LIST IDENTIFIER;

word: VAR WORD IDENTIFIER NEWLINE INDENT (IDENTIFIER NEWLINE)+ DEDENT;

stringAssign : ASSIGN STRING_VALUE;
booleanAssign: ASSIGN BOOLEAN_VALUE;
integerAssign: ASSIGN (POSITIVE_VALUE | NEGATIVE_VALUE);
doubleAssign : ASSIGN (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE);
naturalAssign: ASSIGN POSITIVE_VALUE;

stringListAssign : ASSIGN LEFT_BRACKET STRING_VALUE+ RIGHT_BRACKET;
booleanListAssign: ASSIGN LEFT_BRACKET BOOLEAN_VALUE+ RIGHT_BRACKET;
integerListAssign: ASSIGN LEFT_BRACKET (POSITIVE_VALUE | NEGATIVE_VALUE)+ RIGHT_BRACKET;
doubleListAssign : ASSIGN LEFT_BRACKET (POSITIVE_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE)+ RIGHT_BRACKET;
naturalListAssign: ASSIGN LEFT_BRACKET POSITIVE_VALUE+ RIGHT_BRACKET;

conceptAnnotations    : OPEN_AN ( ROOT | HAS_CODE | EXTENSIBLE | SINGLETON )+ CLOSE_AN;
componentAnnotations  : OPEN_AN ( MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON )+ CLOSE_AN;
fromAnnotations       : OPEN_AN ( OPTIONAL | MULTIPLE )+ CLOSE_AN;
fromConceptAnnotations: OPEN_AN ( HAS_CODE | EXTENSIBLE | SINGLETON )+ CLOSE_AN;

modifier: ABSTRACT
        | FINAL;

doc: DOC_LINE
   | DOC_BLOCK;