{
  parserClass='monet.::projectName::.intellij.metamodel.parser.::projectProperName::Parser'
  parserUtilClass="monet.::projectName::.intellij.metamodel.parser.::projectProperName::ParserUtil"

  extends='com.intellij.extapi.psi.ASTWrapperPsiElement'

  psiClassPrefix='::projectProperName::'
  psiImplClassSuffix='Impl'
  psiPackage='monet.::projectName::.intellij.metamodel.psi'
  psiImplPackage='monet.::projectName::.intellij.metamodel.psi.impl'

  elementTypeHolderClass='monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types'
  elementTypeClass='monet.::projectName::.intellij.metamodel.psi.::projectProperName::ElementType'
  tokenTypeClass='monet.::projectName::.intellij.metamodel.psi.::projectProperName::TokenType'

  psiImplUtilClass='monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil'
}

root ::= (definition | NEWLINE)*

definition ::= doc? signature annotations? body?
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.DefinitionMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Definition'
}

signature ::= CONCEPT_KEY extendedDefinition?  (polymorphic | modifier? morph?)  AS identifier
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.SignatureMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Signature'
}

body ::= NEW_LINE_INDENT (definitionConstituents NEWLINE+)+ DEDENT
{ mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.BodyMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Body'
}

private definitionConstituents ::= attribute
		                      | referenceStatement
		                      | word
		                      | definition
		                      | definitionInjection

referenceStatement::= VAR extendedDefinition LIST? variableNames
definitionInjection::= NEW extendedDefinition annotations?
attribute::= VAR     UID_TYPE IDENTIFIER_KEY (ASSIGN stringValue)?
           | VAR     INT_TYPE (variableNames | IDENTIFIER_KEY ASSIGN integerValue | LIST IDENTIFIER_KEY (ASSIGN integerList)?)
           | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY ASSIGN doubleValue | LIST IDENTIFIER_KEY (ASSIGN doubleList)?)
           | VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY ASSIGN naturalValue | LIST IDENTIFIER_KEY (ASSIGN naturalList)?)
           | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY ASSIGN booleanValue | LIST IDENTIFIER_KEY (ASSIGN booleanList)?)
           | VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY ASSIGN stringValue | LIST IDENTIFIER_KEY (ASSIGN stringList)?)
{mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.AttributeMixin'
implements='monet.::projectName::.intellij.metamodel.psi.Attribute'}

word::= VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT

stringValue  ::= STRING_VALUE_KEY
booleanValue ::= BOOLEAN_VALUE_KEY
integerValue ::= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
doubleValue  ::= NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
naturalValue ::= NATURAL_VALUE_KEY

stringList ::= LEFT_SQUARE STRING_VALUE_KEY+ RIGHT_SQUARE;
booleanList::= LEFT_SQUARE BOOLEAN_VALUE_KEY+ RIGHT_SQUARE;
integerList::= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+ RIGHT_SQUARE;
doubleList ::= LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+ RIGHT_SQUARE;
naturalList::= LEFT_SQUARE NATURAL_VALUE_KEY+ RIGHT_SQUARE;

annotations ::= OPEN_AN (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON | ROOT)+ CLOSE_AN {methods=[getAnnotations]}

variableNames::= IDENTIFIER_KEY (COMMA IDENTIFIER_KEY)*;

modifier::= ABSTRACT
          | FINAL

polymorphic::= POLYMORPHIC_KEY
morph::= MORPH_KEY

doc::= DOC_LINE+

extendedDefinition::= identifier (DOT identifier)*

identifier::=  IDENTIFIER_KEY
{mixin= 'monet.::projectName::.intellij.metamodel.psi.impl.IdentifierMixin' methods=[getIdentifier]}