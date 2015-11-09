parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* dslDeclaration? imports? (node NEWLINE*)* EOF;

dslDeclaration : DSL headerReference NEWLINE*;

imports : anImport+;
anImport: USE headerReference NEWLINE+;

doc: DOC+;
node: doc? signature body?;

signature: ((SUB ruleContainer? parameters? IDENTIFIER) | (metaidentifier ruleContainer? parameters? IDENTIFIER? parent?)) tags plate?;

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
nodeReference : HAS ruleContainer? identifierReference tags;
with: WITH identifierReference (COMMA identifierReference)*;
variable : doc? VAR variableType size? ruleContainer? IDENTIFIER (EQUALS value metric?)? flags?;

variableType: INT_TYPE
            | DOUBLE_TYPE
            | BOOLEAN_TYPE
            | STRING_TYPE
            | FUNCTION_TYPE
            | WORD
            | TUPLE_TYPE
            | DATE_TYPE
            | TIME_TYPE
            | RESOURCE
            | identifierReference;

ruleContainer : COLON ruleValue;

ruleValue    : (LEFT_CURLY (IDENTIFIER+ | ((range | stringValue) metric?) | metric) RIGHT_CURLY) | IDENTIFIER;

range        : (doubleValue | integerValue | STAR) DOT DOT (doubleValue | integerValue | STAR);

size: LEFT_SQUARE sizeRange? RIGHT_SQUARE;
sizeRange : NATURAL_VALUE | listRange;
listRange    : (NATURAL_VALUE | STAR) DOT DOT (NATURAL_VALUE | STAR);

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
annotation: TERMINAL | MAIN | FACET | FEATURE | PROTOTYPE | ENCLOSED;

flags: IS flag+;
flag: ABSTRACT | TERMINAL | MAIN | PRIVATE
      	| FACET | FEATURE | PROTOTYPE | ENCLOSED | FINAL | NAMED | DEFINITION | NATIVE;

varInit : IDENTIFIER EQUALS value;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;