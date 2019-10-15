parser grammar TaraGrammar;
options { tokenVocab=TaraLexer; }


root: NEWLINE* dslDeclaration? imports? (node NEWLINE*)* EOF;

dslDeclaration : DSL headerReference NEWLINE*;

imports : anImport+;
anImport: USE headerReference NEWLINE+;

doc: DOC+;
node: doc? signature body?;

signature: ((SUB ruleContainer* parameters? IDENTIFIER aspects*) |
			(metaidentifier ruleContainer* parameters? IDENTIFIER? aspects* parent?)) with? tags;

parent : EXTENDS identifierReference;


parameters : LEFT_PARENTHESIS (parameter (COMMA parameter)*)? RIGHT_PARENTHESIS;
parameter: (IDENTIFIER EQUALS)? value;

aspects : AS aspect+;

aspect: metaidentifier parameters?;

value : identifierReference+
		| stringValue+
		| tupleValue+
		| booleanValue+
		| identifierReference+
		| integerValue+ metric?
		| doubleValue+ metric?
		| expression+
		| methodReference+
		| EMPTY;
body: NEW_LINE_INDENT ((variable | node | varInit | nodeReference) NEWLINE+)+ DEDENT;

nodeReference : HAS ruleContainer* identifierReference tags;
with: WITH identifierReference (COMMA identifierReference)*;
variable : doc? VAR variableType size? ruleContainer? IDENTIFIER (EQUALS value metric?)? flags? bodyValue?;

bodyValue : NEW_LINE_INDENT (stringValue | expression) NEWLINE? DEDENT;

variableType: INT_TYPE
			| LONG_TYPE
            | DOUBLE_TYPE
            | BOOLEAN_TYPE
            | STRING_TYPE
            | FUNCTION_TYPE
            | OBJECT_TYPE
            | WORD
            | DATE_TYPE
            | INSTANT_TYPE
            | TIME_TYPE
            | RESOURCE
            | identifierReference;

ruleContainer : COLON ruleValue;

ruleValue    : (LEFT_CURLY (IDENTIFIER+ | ((range | stringValue) metric?) | metric) RIGHT_CURLY) | (identifierReference);

range        : (doubleValue | integerValue | STAR) (DOT DOT (doubleValue | integerValue | STAR))?;

size		 : LEFT_SQUARE sizeRange? RIGHT_SQUARE;
sizeRange 	 : NATURAL_VALUE | listRange;
listRange    : (NATURAL_VALUE | STAR) DOT DOT (NATURAL_VALUE | STAR);


methodReference : AT identifierReference;

stringValue  : NEWLINE? (STRING);
booleanValue : BOOLEAN_VALUE;
tupleValue   : stringValue COLON doubleValue;
integerValue : NATURAL_VALUE | NEGATIVE_VALUE;
doubleValue  : (NATURAL_VALUE | NEGATIVE_VALUE | DOUBLE_VALUE) SCIENCE_NOT?;

metric       : IDENTIFIER | MEASURE_VALUE;

expression   : NEWLINE? (EXPRESSION_BEGIN CHARACTER* EXPRESSION_END);

tags: flags? annotations?;

annotations: INTO annotation+;
annotation: COMPONENT | FEATURE | ENCLOSED;

flags: IS flag+;
flag: ABSTRACT | TERMINAL | COMPONENT | PRIVATE | FEATURE | ENCLOSED | FINAL | CONCEPT | REACTIVE | VOLATILE | DECORABLE | REQUIRED | DIVINE;

varInit : IDENTIFIER ((EQUALS value) | bodyValue);

headerReference: hierarchy* IDENTIFIER;

identifierReference: hierarchy* IDENTIFIER;
hierarchy: IDENTIFIER (DOT | PLUS);
metaidentifier: IDENTIFIER;