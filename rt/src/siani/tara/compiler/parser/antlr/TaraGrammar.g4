parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* dslDeclaration? imports? (node NEWLINE*)* EOF;

dslDeclaration : DSL headerReference NEWLINE+;

imports : anImport+;
anImport: USE headerReference NEWLINE+;

doc: DOC+;
node: signature body?;

signature: ((SUB parameters? IDENTIFIER) | (metaidentifier parameters? IDENTIFIER? parent?)) tags? address?;

parent : EXTENDS identifierReference;

parameters : LEFT_PARENTHESIS ((explicitParameter (COMMA explicitParameter)*) | (implicitParameter (COMMA implicitParameter)*)) RIGHT_PARENTHESIS;
explicitParameter: IDENTIFIER EQUALS value;
implicitParameter: value;

value : identifierReference+
		| stringValue+
        | booleanValue+
        | linkValue+
        | naturalValue+ measureValue?
        | integerValue+ measureValue?
        | doubleValue+ measureValue?
        | EMPTY;

body: NEW_LINE_INDENT ((variable | node | varInit | facetApply | facetTarget | nodeReference | doc) NEWLINE+)+ DEDENT;

facetApply : AS metaidentifier parameters? (WITH metaidentifier)? body?;
facetTarget : ON identifierReference body?;
nodeReference : HAS identifierReference tags?;

variable : VAR variableType nativeName? (LIST | count)? IDENTIFIER (word | (EQUALS value))? flags?;

variableType: NATURAL_TYPE
            | INT_TYPE
            | BOOLEAN_TYPE
            | STRING_TYPE
            | DATE_TYPE
            | NATIVE_TYPE
            | RATIO_TYPE
            | DOUBLE_TYPE
            | MEASURE_TYPE
            | WORD
            | RESOURCE
            | identifierReference;

nativeName : COLON (IDENTIFIER | MEASURE_VALUE);
count  : LEFT_SQUARE NATURAL_VALUE RIGHT_SQUARE;

word            : NEW_LINE_INDENT (wordValue NEWLINE)+ DEDENT;
wordValue       : IDENTIFIER STAR?;

stringValue     : STRING_VALUE | (NEWLINE? STRING_MULTILINE_VALUE_KEY);
booleanValue    : BOOLEAN_VALUE;
naturalValue    : NATURAL_VALUE;
integerValue    : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue     : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;
linkValue       : address | identifierReference;
address         : ADDRESS_VALUE;
measureValue    : IDENTIFIER | MEASURE_VALUE;

tags: flags? annotations?;

annotations: INTO annotation+;
annotation: TERMINAL
            | SINGLE | MULTIPLE | REQUIRED | OPTIONAL
            | FACET
            | FEATURE | PROPERTY | ENCLOSED | ADDRESSED
            | COMPONENT | AGGREGATED | ASSOCIATED;

flags: IS flag+;
flag: ABSTRACT | TERMINAL
      	| SINGLE | MULTIPLE | REQUIRED | OPTIONAL
      	| FACET
      	| FEATURE | PROPERTY | ENCLOSED | ADDRESSED | READONLY
      	| COMPONENT | AGGREGATED | ASSOCIATED;

varInit : IDENTIFIER EQUALS value;

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER DOT;
metaidentifier: METAIDENTIFIER | IDENTIFIER;