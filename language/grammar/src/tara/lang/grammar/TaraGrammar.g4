parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* dslDeclaration? imports? (node NEWLINE*)* EOF;

dslDeclaration : DSL headerReference NEWLINE*;

imports : anImport+;
anImport: USE headerReference NEWLINE+;

doc: DOC+;
node: doc? signature body?;

signature: ((SUB parameters? IDENTIFIER) | (metaidentifier parameters? IDENTIFIER? parent?)) tags plate?;

parent : EXTENDS identifierReference;

parameters : LEFT_PARENTHESIS (parameter (COMMA parameter)*)? RIGHT_PARENTHESIS;
parameter: (IDENTIFIER EQUALS)? value;

value : identifierReference+
		| stringValue+
		| tupleValue+
        | booleanValue+
        | linkValue+
        | integerValue+ metric?
        | doubleValue+ metric?
        | expression+
        | EMPTY;

body: NEW_LINE_INDENT ((variable | node | varInit | facetApply | facetTarget | nodeReference) NEWLINE+)+ DEDENT;

facetApply : AS metaidentifier parameters? with? body?;
facetTarget : doc? ON (identifierReference | ANY) with? body?;
nodeReference : HAS identifierReference tags;
with: WITH identifierReference (COMMA identifierReference)*;
variable : doc? VAR variableType ruleContainer? (LIST | count)? IDENTIFIER (EQUALS value MEASURE_VALUE?)? flags?;

variableType: INT_TYPE
            | DOUBLE_TYPE
            | BOOLEAN_TYPE
            | STRING_TYPE
            | NATIVE_TYPE
            | WORD
            | TUPLE_TYPE
            | DATE_TYPE
            | TIME_TYPE
            | RESOURCE
            | identifierReference;

ruleContainer : COLON ruleValue;

ruleValue   : (LEFT_SQUARE (IDENTIFIER+ | ((range | stringValue) metric?) | metric) RIGHT_SQUARE) | IDENTIFIER;

range       : (doubleValue | integerValue | STAR) DOT DOT (doubleValue | integerValue | STAR);

count       : LEFT_SQUARE NATURAL_VALUE RIGHT_SQUARE;

stringValue  : NEWLINE? (QUOTE_BEGIN CHARACTER* QUOTE_END);
booleanValue : BOOLEAN_VALUE;
tupleValue   : stringValue COLON doubleValue;
integerValue : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue  : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;
linkValue    : plate | identifierReference;
plate        : PLATE_VALUE;

metric       : IDENTIFIER | MEASURE_VALUE;

expression   : NEWLINE? (EXPRESSION_BEGIN CHARACTER* EXPRESSION_END);

tags: flags? annotations?;

annotations: INTO annotation+;
annotation: TERMINAL | MAIN | SINGLE | REQUIRED
            | FACET | FEATURE | PROTOTYPE | ENCLOSED;

flags: IS flag+;
flag: ABSTRACT | TERMINAL | MAIN
      	| SINGLE  | REQUIRED | PRIVATE
      	| FACET | FEATURE | PROTOTYPE | ENCLOSED | FINAL | NAMED | DEFINITION;

varInit : IDENTIFIER EQUALS value;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;