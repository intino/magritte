{
  parserClass='monet.::projectName::.intellij.parser.::projectProperName::Parser'
  parserUtilClass="monet.::projectName::.intellij.parser.::projectProperName::ParserUtil"

  extends='com.intellij.extapi.psi.ASTWrapperPsiElement'

  psiClassPrefix='::projectProperName::'
  psiImplClassSuffix='Impl'
  psiPackage='monet.::projectName::.intellij.psi'
  psiImplPackage='monet.::projectName::.intellij.psi.impl'

  elementTypeHolderClass='monet.::projectName::.intellij.psi.::projectProperName::Types'
  elementTypeClass='monet.::projectName::.intellij.psi.::projectProperName::ElementType'
  tokenTypeClass='monet.::projectName::.intellij.psi.::projectProperName::TokenType'

  psiImplUtilClass='monet.::projectName::.intellij.psi.impl.::projectProperName::PsiImplUtil'
}

root \::= (definition)*

definition \::=  doc? definitionSignature definitionBody? {mixin='monet.::projectName::.intellij.psi.impl.::projectProperName::NamedElementImpl'
implements='monet.::projectName::.intellij.psi.::projectProperName::NamedElement' methods=[getName setName getNameIdentifier]}

definitionSignature \::=  (DEFINITION_KEY | identifier) modifier? AS identifier definitionAnnotation?

child  \::=  doc? (DEFINITION_KEY | identifier(DOT identifier)?) modifier? (AS identifier)? childAnnotation? definitionBody?

identifier  \::=  IDENTIFIER_KEY {methods=[getIdentifier]}

definitionBody \::= INDENT definitionConstituents+ DEDENT

definitionConstituents \::= attribute
                      | attributeList
                      | referenceStatement
                      | referenceStatementList
                      | from
                      | child

referenceStatement     \::= VAR identifier IDENTIFIER_KEY
referenceStatementList \::= VAR identifier LIST IDENTIFIER_KEY

attribute\::= VAR     UID_TYPE IDENTIFIER_KEY stringAssign?
           | VAR     INT_TYPE IDENTIFIER_KEY integerAssign?
           | VAR  DOUBLE_TYPE IDENTIFIER_KEY doubleAssign?
           | VAR NATURAL_TYPE IDENTIFIER_KEY naturalAssign?
           | VAR BOOLEAN_TYPE IDENTIFIER_KEY booleanAssign?
           | VAR  STRING_TYPE IDENTIFIER_KEY stringAssign?
           | VAR WORD IDENTIFIER_KEY wordBody
wordBody\::= INDENT IDENTIFIER_KEY+ DEDENT

attributeList\::= VAR     INT_TYPE LIST IDENTIFIER_KEY integerListAssign?
               | VAR  DOUBLE_TYPE LIST IDENTIFIER_KEY doubleListAssign?
               | VAR NATURAL_TYPE LIST IDENTIFIER_KEY naturalListAssign?
               | VAR BOOLEAN_TYPE LIST IDENTIFIER_KEY booleanListAssign?
               | VAR  STRING_TYPE LIST IDENTIFIER_KEY stringListAssign?

stringAssign   \::= ASSIGN STRING_VALUE
booleanAssign  \::= ASSIGN BOOLEAN_VALUE
integerAssign  \::= ASSIGN integerValue
doubleAssign   \::= ASSIGN (integerValue | DOUBLE_VALUE)
naturalAssign  \::= ASSIGN NATURAL_VALUE

stringListAssign   \::= ASSIGN LEFT_BRACKET STRING_VALUE+ RIGHT_BRACKET
booleanListAssign  \::= ASSIGN LEFT_BRACKET BOOLEAN_VALUE+ RIGHT_BRACKET
integerListAssign  \::= ASSIGN LEFT_BRACKET integerValue+ RIGHT_BRACKET
doubleListAssign   \::= ASSIGN LEFT_BRACKET (integerValue | DOUBLE_VALUE)+ RIGHT_BRACKET
naturalListAssign  \::= ASSIGN LEFT_BRACKET NATURAL_VALUE+ RIGHT_BRACKET

from\::= FROM_KEY rangeAnnotation?
    | rangeAnnotation? fromBody

fromBody\::= INDENT child+ DEDENT

definitionAnnotation\::= OPEN_AN annotation+ CLOSE_AN
childAnnotation  \::= OPEN_AN (range | annotation)+ CLOSE_AN
rangeAnnotation  \::= OPEN_AN range+ CLOSE_AN

range\::= OPTIONAL
       | MULTIPLE

annotation\::= HAS_CODE
            | EXTENSIBLE

integerValue\::= NATURAL_VALUE
              | NEGATIVE_VALUE

modifier\::= ABSTRACT
          | FINAL

doc\::= DOC_LINE
     | DOC_BLOCK