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

root \:\:= (synthesizeStatement | NEWLINE)* (definition | NEWLINE)*

synthesizeStatement \:\:= SYNTHESIZE referenceIdentifier ((OPEN_AN synthesizeTag+ CLOSE_AN) | synthesizeAttribute )
synthesizeTag \:\:= FINAL | ABSTRACT |  MULTIPLE |  OPTIONAL | HAS_CODE | SINGLETON | ROOT | EXTENSIBLE
synthesizeAttribute \:\:= NEW_LINE_INDENT( attribute  NEWLINE+)+ DEDENT

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
referenceIdentifier  \:\:=  identifier (DOT identifier)*
{
mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.ReferenceIdentifierMixin'
implements='monet.::projectName::.intellij.metamodel.psi.ReferenceIdentifier'
}
polymorphic \:\:= POLYMORPHIC_KEY
morph \:\:= MORPH_KEY

attribute\:\:= VAR     UID_TYPE IDENTIFIER_KEY (ASSIGN stringValue)?
           | VAR     INT_TYPE (variableNames | IDENTIFIER_KEY ASSIGN integerValue | LIST IDENTIFIER_KEY (ASSIGN integerList)?)
           | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY ASSIGN doubleValue | LIST IDENTIFIER_KEY (ASSIGN doubleList)?)
           | VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY ASSIGN naturalValue | LIST IDENTIFIER_KEY (ASSIGN naturalList)?)
           | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY ASSIGN booleanValue | LIST IDENTIFIER_KEY (ASSIGN booleanList)?)
           | VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY ASSIGN stringValue | LIST IDENTIFIER_KEY (ASSIGN stringList)?)
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
::identifier::\:\:= doc? ::identifier::Signature ::code|*:: ::identifier::Body?
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.DefinitionMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Definition'
}
::identifier::Signature \:\:= ::lexicoIdentifier:: referenceIdentifier? (polymorphic | modifier? morph?) (AS identifier)? ::assignAttributeHeader|*::
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
