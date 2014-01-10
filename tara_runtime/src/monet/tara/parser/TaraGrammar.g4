parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }

@header{
    package monet.tara.parser;
}

//grammar TaraGrammar;
//import TaraLexer;

root: (namespace | definition)* EOF;

namespace: NAMESPACE IDENTIFIER namespaceBody?;

namespaceBody: INDENT definition+ DEDENT;

definition: annotations* CONCEPT IDENTIFIER extend? modifiers? annotations* body?;

extend: IS IDENTIFIER (DOT IDENTIFIER)*;

modifiers: COL (FINAL | ABSTRACT);

annotations: HAS_ID_AN
		   | ROOT_AN
		   | EXTEN_AN
		   | ACTION_JAVA_AN
		   | ACTION_PHYTON_AN
		   ;

body: INDENT statement+ DEDENT;

statement: property
		 | reference
		 | child
		 | intention
		 ;

property: (intProperty
		| doubleProperty
		| naturalProperty
		| booleanProperty
		| stringProperty
		| dateProperty)  DEFAULT_AN?
		;

intProperty    :     INT_TYPE IDENTIFIER assignIntValue?    ;
doubleProperty :  DOUBLE_TYPE IDENTIFIER assignDoubleValue? ;
naturalProperty: NATURAL_TYPE IDENTIFIER assignNaturalValue?;
stringProperty :  STRING_TYPE IDENTIFIER assignStringValue? ;
booleanProperty: BOOLEAN_TYPE IDENTIFIER assignBooleanValue?;
dateProperty   :    DATE_TYPE IDENTIFIER assignDateValue?   ;

reference      : REF IDENTIFIER RANGE? IDENTIFIER;
child          : HAS IDENTIFIER RANGE? extend? annotations* include? body?;

assignStringValue : ASSIGN STRING;
assignBooleanValue: ASSIGN (TRUE | FALSE);
assignIntValue    : ASSIGN SIGN? NUMBER;
assignDoubleValue : ASSIGN SIGN? (NUMBER | DOUBLE_NUMBER);
assignNaturalValue: ASSIGN NUMBER;
assignDateValue   : ASSIGN DATE  ;

include  : INCLUDE IDENTIFIER;
intention: USE IDENTIFIER intentionBody?;

intentionBody: INDENT IDENTIFIER DEDENT;