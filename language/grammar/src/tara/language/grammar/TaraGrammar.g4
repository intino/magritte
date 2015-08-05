parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* dslDeclaration? imports? (node NEWLINE*)* EOF;

dslDeclaration : DSL headerReference NEWLINE*;

imports : anImport+;
anImport: USE headerReference NEWLINE+;

doc: DOC+;
node: signature body?;

signature: ((SUB parameters? IDENTIFIER) | (metaidentifier parameters? IDENTIFIER? parent?)) tags plate?;

parent : EXTENDS identifierReference;

parameters : LEFT_PARENTHESIS (parameter (COMMA parameter)*)? RIGHT_PARENTHESIS;
parameter: (IDENTIFIER EQUALS)? value;

value : identifierReference+
		| stringValue+
		| tupleValue+
        | booleanValue+
        | linkValue+
        | naturalValue+ measureValue?
        | integerValue+ measureValue?
        | doubleValue+ measureValue?
        | expression+
        | EMPTY;

body: NEW_LINE_INDENT ((variable | node | varInit | facetApply | facetTarget | nodeReference | doc) NEWLINE+)+ DEDENT;

facetApply : AS metaidentifier parameters? with? body?;
facetTarget : ON (identifierReference | ANY) with? body?;
nodeReference : HAS identifierReference tags;
with: WITH identifierReference (COMMA identifierReference)*;
variable : VAR variableType contract? (LIST | count)? IDENTIFIER (EQUALS value MEASURE_VALUE?)? flags?;

variableType: NATURAL_TYPE
            | INT_TYPE
            | BOOLEAN_TYPE
            | TUPLE_TYPE
            | STRING_TYPE
            | DATE_TYPE
            | NATIVE_TYPE
            | RATIO_TYPE
            | DOUBLE_TYPE
            | MEASURE_TYPE
            | WORD
            | RESOURCE
            | identifierReference;

contract : COLON contractValue;

contractValue :(LEFT_SQUARE (MEASURE_VALUE | IDENTIFIER)+ RIGHT_SQUARE) | (MEASURE_VALUE | IDENTIFIER);

count  : LEFT_SQUARE NATURAL_VALUE RIGHT_SQUARE;

stringValue  : NEWLINE? (QUOTE_BEGIN CHARACTER* QUOTE_END);
booleanValue : BOOLEAN_VALUE;
tupleValue   : stringValue COLON doubleValue;
naturalValue : NATURAL_VALUE;
integerValue : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue  : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;
linkValue    : plate | identifierReference;
plate        : PLATE_VALUE;
measureValue : IDENTIFIER | MEASURE_VALUE;

expression  : NEWLINE? (EXPRESSION_BEGIN CHARACTER* EXPRESSION_END);

tags: flags? annotations?;

annotations: INTO annotation+;
annotation: TERMINAL | MAIN | SINGLE | REQUIRED
            | FACET | FEATURE | PROTOTYPE | ENCLOSED;

flags: IS flag+;
flag: ABSTRACT | TERMINAL | MAIN
      	| SINGLE  | REQUIRED | PRIVATE
      	| FACET | FEATURE | PROTOTYPE | ENCLOSED | FINAL;

varInit : IDENTIFIER EQUALS value;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;