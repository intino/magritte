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
root \:\:= NEWLINE* header NEWLINE+ definition NEWLINE* {
	pin = 4
}
definitionKey \:\:= ::conceptKeys::
synthesizeStatement \:\:= NEW_LINE SYNTHESIZE definitionKey ((OPEN_AN synthesizeTag+ CLOSE_AN) | synthesizeAttribute )
synthesizeTag \:\:= GENERIC | intention | FINAL | ABSTRACT |  MULTIPLE |  OPTIONAL | HAS_CODE | SINGLETON | ROOT
synthesizeAttribute \:\:= NEW_LINE_INDENT( attribute  NEWLINE+)+ DEDENT

header \:\:=  packet importStatement* synthesizeStatement*

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

annotations \:\:= OPEN_AN (code | intention | MULTIPLE | OPTIONAL)+ CLOSE_AN {
	pin = 1
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.AnnotationsMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.Annotations'
}

code \:\:= CODE_KEY COLON IDENTIFIER_KEY

intention \:\:= INTENTION_KEY {
	pin = 1
	mixin = 'monet.::projectName::.intellij.lang.psi.impl.ExternalReferenceMixin'
    implements = 'monet.::projectName::.intellij.lang.psi.ExternalReference'
}

private heritageSignature\:\:=metaIdentifier COLON identifierReference modifier? identifier?{
	pin = 2
}

parameters\:\:= LEFT_PARENTHESIS ( explicitParameters | implicitParameters) RIGHT_PARENTHESIS

implicitParameters\:\:= parameter (COMMA parameter)?
{ pin=1}
explicitParameters\:\:= parameterAssign (COMMA parameterAssign)?
{ pin=1}
private parameterAssign \:\:= identifier COLON parameter
{ pin=2}
private parameter \:\:=   identifierReference
						| stringValue
			            | booleanValue
			            | integerValue
			            | doubleValue
			            | naturalValue
			            | stringList
			            | booleanList
			            | integerList
			            | doubleList
			            | naturalList
			            | identifierList

private definitionConstituents \:\:=  attribute | referenceStatement | word | definition

attribute \:\:= doc? ( uuidAttribute
		           | integerAttribute
		           | doubleAttribute
		           | naturalAttribute
		           | booleanAttribute
		           | StringAttribute)
{pin=2 mixin = 'monet.::projectName::.intellij.lang.psi.impl.AttributeMixin'
implements = 'monet.::projectName::.intellij.lang.psi.Attribute'}

private uuidAttribute \:\:= VAR     UID_TYPE IDENTIFIER_KEY (COLON stringValue)?
{pin=3}
private integerAttribute \:\:= VAR INT_TYPE ((IDENTIFIER_KEY (COLON integerValue)?) | (LIST IDENTIFIER_KEY (COLON integerList)?))
{pin=2}
private StringAttribute \:\:= VAR  STRING_TYPE ((IDENTIFIER_KEY (COLON stringValue)?) | (LIST IDENTIFIER_KEY (COLON stringList)?))
{pin=2}
private naturalAttribute \:\:= VAR NATURAL_TYPE ((IDENTIFIER_KEY (COLON naturalValue)?) | (LIST IDENTIFIER_KEY (COLON naturalList)?))
{pin=2}
private booleanAttribute \:\:=VAR BOOLEAN_TYPE ((IDENTIFIER_KEY (COLON booleanValue)?) | (LIST IDENTIFIER_KEY (COLON booleanList)?))
{pin=2}
private doubleAttribute \:\:= VAR  DOUBLE_TYPE ((IDENTIFIER_KEY (COLON doubleValue)?)  | (LIST IDENTIFIER_KEY (COLON doubleList)?))
{pin=2}
word\:\:= VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.WordMixin'
	implements='monet.::projectName::.intellij.lang.psi.Word'
}
referenceStatement\:\:= VAR identifierReference LIST? variableNames {
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.ReferenceStatementMixin'
	implements='monet.::projectName::.intellij.lang.psi.ReferenceStatement'
}

stringValue  \:\:= STRING_VALUE_KEY
booleanValue \:\:= BOOLEAN_VALUE_KEY
integerValue \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
doubleValue  \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
naturalValue \:\:= NATURAL_VALUE_KEY

stringList   \:\:= LEFT_SQUARE STRING_VALUE_KEY+ RIGHT_SQUARE;
booleanList  \:\:= LEFT_SQUARE BOOLEAN_VALUE_KEY+ RIGHT_SQUARE;
integerList  \:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+ RIGHT_SQUARE;
doubleList   \:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+ RIGHT_SQUARE;
naturalList  \:\:= LEFT_SQUARE NATURAL_VALUE_KEY+ RIGHT_SQUARE;
identifierList  \:\:= LEFT_SQUARE identifier+ RIGHT_SQUARE;

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

externalReference \:\:= hierarchy* identifier {
	pin=2
	mixin= 'monet.::projectName::.intellij.lang.psi.impl.ExternalReferenceMixin'
	implements='monet.::projectName::.intellij.lang.psi.ExternalReference'
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

