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

  psiImplUtilClass='monet.::projectName::.intellij.metamodel.psi.impl.TaraPsiImplUtil'
}

root \:\:= (definition | NEWLINE)*
definition \:\:= ::concepts::

doc \:\:= DOC+
basicConstituents\:\:= attribute | reference | word
modifier \:\:= ABSTRACT | FINAL
annotations \:\:= OPEN_AN (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE | SINGLETON | ROOT)+ CLOSE_AN
identifier  \:\:=  IDENTIFIER_KEY {mixin= 'monet.::projectName::.compiler.intellij.psi.impl.::projectNameFile::IdentifierMixin' methods=[getIdentifier]}
identifierExtend  \:\:=  IDENTIFIER_KEY {mixin= 'monet.::projectName::.compiler.intellij.psi.impl.::projectNameFile::IdentifierMixin' methods=[getIdentifier]}
polymorphic \:\:= POLYMORPHIC_KEY
morph \:\:= MORPH_KEY

::rules::

@concept
::pipe|*::::identifier::@ruleConcept
::identifier::\:\:= doc? ::lexicoIdentifier:: identifierExtend? (polymorphic | modifier? morph?) AS identifier ::assignAttributeHeader|*:: annotations? ::code|*:: ::identifier::Body?
{
mixin= 'monet.::projectName::.compiler.intellij.psi.impl.::projectNameFile::::identifier::Mixin'
implements='monet.::projectName::.compiler.intellij.psi.I::identifier::'
methods=[getIdentifier getIdentifierNode]
}
::attributeRule::

::identifier::Body \:\:=  NEW_LINE_INDENT ((basicConstituents ::identifierConstituent:: ::identifierExplicitAttribute::) NEWLINE+)+ DEDENT
::constituentRule::

