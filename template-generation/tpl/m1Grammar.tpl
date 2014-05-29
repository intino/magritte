{
	parserClass='monet.::projectName::.intellij.lang.parser.::projectProperName::Parser'
	parserUtilClass="monet.::projectName::.intellij.lang.parser.::projectProperName::ParserUtil"

	extends='com.intellij.extapi.psi.ASTWrapperPsiElement'

	psiClassPrefix='::projectProperName::'
	psiImplClassSuffix='Impl'
	psiPackage='monet.::projectName::.intellij.lang.psi'
	psiImplPackage='monet.::projectName::.intellij.lang.psi.impl'

	elementTypeHolderClass='monet.::projectName::.intellij.lang.psi.::projectProperName::Types'
	elementTypeClass='monet.::projectName::.intellij.lang.psi.::projectProperName::ElementType'
	tokenTypeClass='monet.::projectName::.intellij.lang.psi.::projectProperName::TokenType'

	psiImplUtilClass='monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil'
}
root \:\:= NEWLINE* header? NEWLINE+ definition? NEWLINE* {
	pin = 4
}
definitionKey \:\:= ::conceptKeys::

header \:\:=  packet? importStatement*

packet \:\:= PACKAGE headerReference

importStatement \:\:= NEWLINE IMPORT_KEY headerReference {
    mixin = 'monet.::projectName::.intellij.lang.psi.impl.ImportMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.Import'
}

doc \:\:= DOC_LINE+ {
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.DocMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.Doc'
}

definition \:\:= doc? signature annotations? body? {
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.DefinitionMixin'
	implements = 'monet.::projectName::.intellij.lang.psi.Definition'
}

signature \:\:= ((CASE_KEY identifier) | heritageSignature | (metaIdentifier modifier? identifier?)) parameters? {
    pin = 1
    mixin= 'monet.::projectName::.intellij.lang.psi.impl.SignatureMixin'
	implements='monet.::projectName::.intellij.lang.psi.Signature'
}

body \:\:= NEW_LINE_INDENT (definitionConstituents NEWLINE+)+ DEDENT {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.BodyMixin'
	implements='monet.::projectName::.intellij.lang.psi.Body'
}

modifier\:\:= ABSTRACT | FINAL | BASE_KEY

annotations \:\:= OPEN_AN (intention | MULTIPLE | REQUIRED)+ CLOSE_AN {
	pin = 1
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.AnnotationsMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.Annotations'
}

intention \:\:= INTENTION_KEY {
	pin = 1
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.ExternalReferenceMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.ExternalReference'
}

private heritageSignature\:\:=metaIdentifier COLON identifierReference modifier? identifier?{
	pin = 2
}

parameters\:\:= LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS {
	pin=1
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.ParametersMixin'
	implements = 'monet.::projectName::.intellij.lang.psi.Parameters'
}

private parameterList\:\:=  explicit? parameter (COMMA explicit? parameter)*
{ pin=2}

parameter \:\:=     identifierReference
				| stringValue
		        | booleanValue
		        | naturalValue
		        | integerValue
		        | doubleValue
		        | stringList
		        | booleanList
		        | integerList
		        | doubleList
		        | naturalList
		        | identifierList
{mixin = 'monet.::projectName::.intellij.lang.psi.impl.ParameterMixin'
implements = 'monet.::projectName::.intellij.lang.psi.Parameter'}

explicit\:\:= identifier COLON
{ pin=2}

private definitionConstituents \:\:=  attribute | referenceStatement | word | definition

attribute \:\:= doc? ( aliasAttribute
		           | stringAttribute)
		           | booleanAttribute
		           | naturalAttribute
		           | integerAttribute
		           | doubleAttribute
{pin=2 mixin = 'monet.::projectName::.intellij.lang.psi.impl.AttributeMixin'
implements = 'monet.::projectName::.intellij.lang.psi.Attribute'}

private aliasAttribute \:\:= VAR ALIAS_TYPE IDENTIFIER_KEY (COLON stringValue)?
{pin=3}
private naturalAttribute \:\:= VAR NATURAL_TYPE ((IDENTIFIER_KEY (COLON naturalValue)?) | (LIST IDENTIFIER_KEY (COLON naturalList)?))
{pin=2}
private integerAttribute \:\:= VAR INT_TYPE ((IDENTIFIER_KEY (COLON integerValue)?) | (LIST IDENTIFIER_KEY (COLON integerList)?))
{pin=2}
private doubleAttribute \:\:= VAR  DOUBLE_TYPE ((IDENTIFIER_KEY (COLON doubleValue)?)  | (LIST IDENTIFIER_KEY (COLON doubleList)?))
{pin=2}
private stringAttribute \:\:= VAR  STRING_TYPE ((IDENTIFIER_KEY (COLON stringValue)?) | (LIST IDENTIFIER_KEY (COLON stringList)?))
{pin=2}
private booleanAttribute \:\:=VAR BOOLEAN_TYPE ((IDENTIFIER_KEY (COLON booleanValue)?) | (LIST IDENTIFIER_KEY (COLON booleanList)?))
{pin=2}
word\:\:= VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.WordMixin'
	implements='monet.::projectName::.intellij.lang.psi.Word'
}
referenceStatement\:\:= VAR identifierReference LIST? IDENTIFIER_KEY {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.ReferenceStatementMixin'
	implements='monet.::projectName::.intellij.lang.psi.ReferenceStatement'
}

stringValue  \:\:= STRING_VALUE_KEY
booleanValue \:\:= BOOLEAN_VALUE_KEY
naturalValue \:\:= NATURAL_VALUE_KEY
integerValue \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
doubleValue  \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY

stringList   \:\:= LEFT_SQUARE STRING_VALUE_KEY+ RIGHT_SQUARE;
booleanList  \:\:= LEFT_SQUARE BOOLEAN_VALUE_KEY+ RIGHT_SQUARE;
integerList  \:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+ RIGHT_SQUARE;
doubleList   \:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+ RIGHT_SQUARE;
naturalList  \:\:= LEFT_SQUARE NATURAL_VALUE_KEY+ RIGHT_SQUARE;
identifierList \:\:= LEFT_SQUARE identifier+ RIGHT_SQUARE;

headerReference \:\:= hierarchy* identifier {
	pin=2
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.IdentifierReferenceMixin'
	implements='monet.::projectName::.intellij.lang.psi.HeaderReference'
}

identifierReference\:\:= hierarchy* identifier {
	pin=2
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.IdentifierReferenceMixin'
	implements='monet.::projectName::.intellij.lang.psi.IdentifierReference'
}

private hierarchy \:\:= identifier DOT

identifier  \:\:=  IDENTIFIER_KEY {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.IdentifierMixin'
	implements='monet.::projectName::.intellij.lang.psi.Identifier'
}

metaIdentifier \:\:= IDENTIFIER_KEY {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.MetaIdentifierMixin'
	implements='monet.::projectName::.intellij.lang.psi.MetaIdentifier'
}

