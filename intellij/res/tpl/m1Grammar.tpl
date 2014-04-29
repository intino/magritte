{
  parserClass='monet.::projectName::.intellij.metamodel.parser.::projectNameFile::Parser'
  parserUtilClass="monet.::projectName::.intellij.metamodel.parser.::projectNameFile::ParserUtil"

  extends='com.intellij.extapi.psi.ASTWrapperPsiElement'

  psiClassPrefix='::projectNameFile::'
  psiImplClassSuffix='Impl'
  psiPackage='monet.::projectName::.intellij.metamodel.psi'
  psiImplPackage='monet.::projectName::.intellij.metamodel.psi.impl'

  elementTypeHolderClass='monet.::projectName::.intellij.metamodel.psi.::projectNameFile::Types'
  elementTypeClass='monet.::projectName::.intellij.metamodel.psi.::projectNameFile::ElementType'
  tokenTypeClass='monet.::projectName::.intellij.metamodel.psi.::projectNameFile::TokenType'

  psiImplUtilClass='monet.::projectName::.intellij.metamodel.psi.impl.::projectNameFile::PsiImplUtil'
}
root \:\:= NEWLINE* header NEWLINE+ definition NEWLINE*

private synt \:\:= ::conceptKeyList::
private syntElement \:\:= synt (DOT synt)*
synthesizeStatement \:\:= NEW_LINE SYNTHESIZE syntElement ((OPEN_AN synthesizeTag+ CLOSE_AN) | synthesizeAttribute )
synthesizeTag \:\:= GENERIC | INTENTION | FINAL | ABSTRACT |  MULTIPLE |  OPTIONAL | HAS_CODE | SINGLETON | ROOT | extensible
synthesizeAttribute \:\:= NEW_LINE_INDENT( attribute  NEWLINE+)+ DEDENT

header \:\:=  packet importStatement* synthesizeStatement*

packet \:\:= PACKAGE headerReference
importStatement \:\:= NEWLINE IMPORT_KEY headerReference
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ImportMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Import'}

private child \:\:= DOT identifier {pin=1}

annotations \:\:= OPEN_AN (code | extensible | extension)+ CLOSE_AN
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.AnnotationsMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Annotations'
}

extension \:\:= EXTENSION_KEY COLON externalReference
externalReference \:\:= identifier child*
{
pin=1
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ExternalReferenceMixin'
implements='monet.::projectName::.intellij.metamodel.psi.ExternalReference'
}
headerReference\:\:= identifier child* {
                pin=1
                mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ReferenceIdentifierMixin'
                implements='monet.::projectName::.intellij.metamodel.psi.HeaderReference'}
code \:\:= CODE_KEY COLON IDENTIFIER_KEY

extensible \:\:= EXTENSIBLE_KEY COLON lang
lang \:\:= IDENTIFIER_KEY




private definition \:\:= ::concepts::
doc \:\:= DOC_LINE+
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.DocMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Doc'
}
private basicConstituents\:\:= attribute | referenceStatement | word
definitionInjection \:\:= NEW referenceIdentifier
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.DefinitionInjectionMixin'
implements='monet.::projectName::.intellij.metamodel.psi.DefinitionInjection'
}
modifier \:\:= ABSTRACT | FINAL

identifier  \:\:=  IDENTIFIER_KEY
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.IdentifierMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Identifier'
}
referenceIdentifier  \:\:=  identifier child*
{
pin=1
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ReferenceIdentifierMixin'
implements='monet.::projectName::.intellij.metamodel.psi.ReferenceIdentifier'
}
base \:\:= POLYMORPHIC_KEY
morph \:\:= MORPH_KEY
genericList\:\:= OPEN_AN ( UID_TYPE|INT_TYPE|NATURAL_TYPE|DOUBLE_TYPE|STRING_TYPE|BOOLEAN_TYPE
                         | ::conceptKeyList:: ) CLOSE_AN
attribute\:\:= VAR     UID_TYPE IDENTIFIER_KEY (COLON stringValue)?
           | VAR     INT_TYPE (variableNames | IDENTIFIER_KEY COLON integerValue | LIST IDENTIFIER_KEY (COLON integerList)?)
           | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY COLON doubleValue | LIST IDENTIFIER_KEY (COLON doubleList)?)
           | VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY COLON naturalValue | LIST IDENTIFIER_KEY (COLON naturalList)?)
           | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY COLON booleanValue | LIST IDENTIFIER_KEY (COLON booleanList)?)
           | VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY COLON stringValue | LIST IDENTIFIER_KEY (COLON stringList)?)
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.AttributeMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Attribute'
}
word\:\:= VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.WordMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Word'
}
referenceStatement\:\:= VAR referenceIdentifier LIST? variableNames
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ReferenceStatementMixin'
implements='monet.::projectName::.intellij.metamodel.psi.ReferenceStatement'
}
variableNames\:\:= IDENTIFIER_KEY (COMMA IDENTIFIER_KEY)*;

stringValue  \:\:= STRING_VALUE_KEY
booleanValue \:\:= BOOLEAN_VALUE_KEY
integerValue \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
doubleValue  \:\:= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
naturalValue \:\:= NATURAL_VALUE_KEY

stringList \:\:= LEFT_SQUARE STRING_VALUE_KEY+ RIGHT_SQUARE;
booleanList\:\:= LEFT_SQUARE BOOLEAN_VALUE_KEY+ RIGHT_SQUARE;
integerList\:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+ RIGHT_SQUARE;
doubleList \:\:= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+ RIGHT_SQUARE;
naturalList\:\:= LEFT_SQUARE NATURAL_VALUE_KEY+ RIGHT_SQUARE;


::rules::

@concept
::pipe|*::::identifier::@ruleConcept
::identifier::\:\:= doc? ::identifier::Signature annotations? ::identifier::Body?
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.DefinitionMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Definition'
}
::identifier::Signature \:\:= ::lexicoIdentifier:: ((COLON referenceIdentifier) ::genericOption:: )? (base | modifier? morph?) identifier? ::assignAttributeHeader|*::
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.SignatureMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Signature'
}
::attributeRule::

::identifier::Body \:\:=  NEW_LINE_INDENT (::identifier::ConstituentsStatement NEWLINE+)+ DEDENT
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.BodyMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Body'
}
private ::identifier::ConstituentsStatement \:\:= basicConstituents | definitionInjection ::identifierConstituent:: ::identifierExplicitAttribute::

::constituentRule::
