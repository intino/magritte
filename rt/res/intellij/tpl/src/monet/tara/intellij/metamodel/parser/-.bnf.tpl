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
root \:\:= NEWLINE* header NEWLINE+ definition NEWLINE*
{pin=4}
private syntElement \:\:= definitionKey (DOT definitionKey)*
synthesizeStatement \:\:= NEW_LINE SYNTHESIZE syntElement ((OPEN_AN synthesizeTag+ CLOSE_AN) | synthesizeAttribute )
synthesizeTag \:\:= GENERIC | INTENTION | FINAL | ABSTRACT |  MULTIPLE |  OPTIONAL | HAS_CODE | SINGLETON | ROOT | extensible
synthesizeAttribute \:\:= NEW_LINE_INDENT( attribute  NEWLINE+)+ DEDENT

header \:\:=  packet importStatement* synthesizeStatement*

packet \:\:= PACKAGE headerReference
importStatement \:\:= NEWLINE IMPORT_KEY headerReference
{ mixin= 'monet.goros.intellij.metamodel.psi.impl.ImportMixin'
implements='monet.goros.intellij.metamodel.psi.Import'}

annotations \:\:= OPEN_AN (code | extensible | extension | MULTIPLE | OPTIONAL)+ CLOSE_AN
{
mixin= 'monet.goros.intellij.metamodel.psi.impl.AnnotationsMixin'
implements='monet.goros.intellij.metamodel.psi.Annotations'
}
code \:\:= CODE_KEY COLON IDENTIFIER_KEY

extension \:\:= EXTENSION_KEY COLON externalReference
extensible \:\:= EXTENSIBLE_KEY COLON IDENTIFIER_KEY
{pin=1}

doc \:\:= DOC_LINE+
{
mixin= 'monet.goros.intellij.metamodel.psi.impl.DocMixin'
implements='monet.goros.intellij.metamodel.psi.Doc'
}

headerReference \:\:= hierarchy* identifier
{ pin=2
mixin= 'monet.goros.intellij.metamodel.psi.impl.IdentifierReferenceMixin'
implements='monet.goros.intellij.metamodel.psi.HeaderReference'}

identifierReference\:\:= hierarchy* identifier
{pin=2
mixin= 'monet.goros.intellij.metamodel.psi.impl.IdentifierReferenceMixin'
implements='monet.goros.intellij.metamodel.psi.IdentifierReference'}

externalReference \:\:= hierarchy* identifier
{pin=2
mixin= 'monet.goros.intellij.metamodel.psi.impl.ExternalReferenceMixin'
implements='monet.goros.intellij.metamodel.psi.ExternalReference'}

private hierarchy \:\:= identifier DOT

identifier  \:\:=  IDENTIFIER_KEY {
mixin= 'monet.goros.intellij.metamodel.psi.impl.IdentifierMixin'
implements='monet.goros.intellij.metamodel.psi.Identifier'
}
metaIdentifier \:\:= IDENTIFIER_KEY {
mixin= 'monet.goros.intellij.metamodel.psi.impl.MetaIdentifierMixin'
implements='monet.goros.intellij.metamodel.psi.MetaIdentifier'
}

modifier\:\:= ABSTRACT
          | FINAL
          | BASE_KEY

definition \:\:= doc? signature annotations? body?
{ mixin= 'monet.goros.intellij.metamodel.psi.impl.DefinitionMixin'
implements='monet.goros.intellij.metamodel.psi.Definition'}

definitionKey \:\:= EVENT_KEY|FORM_KEY|ONCHANGE_KEY|DESCRIPTION_KEY|REQUIRED_KEY|READONLY_KEY|DISPLAY_KEY|BOOLEANFIELD_KEY|TEXTFIELD_KEY|EDITION_KEY|ALLOWHISTORY_KEY|MODE_KEY|MAXLENGTH_KEY|MINLENGTH_KEY|PATTERN_KEY|META_KEY|DATEFIELD_KEY|TODAYISTHELATESTDATE_KEY|TODAYISTHEEARLIESTDATE_KEY|SELECTFIELD_KEY|ALLOWSEARCH_KEY|ALLOWOTHERS_KEY|ROOT_KEY|INTERNAL_KEY|LEAF_KEY|FROMFIELD_KEY|FROMTERM_KEY|DEPTH_KEY|FILTER_KEY|VIEW_KEY|SHOWKEY_KEY|EMBEDDED_KEY|COMPOSITEFIELD_KEY|TABLE_KEY|SECTION_KEY|GEOREFERENCE_KEY|TAB_KEY|SUMMARY_KEY|HELP_KEY|OPERATION_KEY|THESAURUS_KEY|TERM_KEY|ONTOLOGY_KEY
signature \:\:= ((CASE_KEY identifier)
		 | heritageSignature
         | (metaIdentifier modifier? identifier?)) parameters?
{ pin=1 mixin= 'monet.goros.intellij.metamodel.psi.impl.SignatureMixin'
implements='monet.goros.intellij.metamodel.psi.Signature'}

private heritageSignature\:\:=metaIdentifier COLON identifierReference modifier? identifier?
{ pin=2}

body \:\:= NEW_LINE_INDENT (definitionConstituents NEWLINE+)+ DEDENT
{ mixin= 'monet.goros.intellij.metamodel.psi.impl.BodyMixin'
implements='monet.goros.intellij.metamodel.psi.Body'}

parameters\:\:= LEFT_PARENTHESIS ( explicitParameters | implicitParameters) RIGHT_PARENTHESIS

implicitParameters\:\:= parameter (COMMA parameter)?
{ pin=1}
explicitParameters\:\:= parameterAssign (COMMA parameterAssign)?
{ pin=1}
private parameterAssign \:\:= identifier COLON parameter
{ pin=2}
private parameter \:\:= identifierReference
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

private definitionConstituents \:\:=  attribute
			                      | referenceStatement
			                      | word
			                      | definition

attribute\:\:= VAR     UID_TYPE IDENTIFIER_KEY (COLON stringValue)?
           | VAR     INT_TYPE (variableNames | IDENTIFIER_KEY COLON integerValue | LIST IDENTIFIER_KEY (COLON integerList)?)
           | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY COLON doubleValue | LIST IDENTIFIER_KEY (COLON doubleList)?)
           | VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY COLON naturalValue | LIST IDENTIFIER_KEY (COLON naturalList)?)
           | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY COLON booleanValue | LIST IDENTIFIER_KEY (COLON booleanList)?)
           | VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY COLON stringValue | LIST IDENTIFIER_KEY (COLON stringList)?)
{
mixin= 'monet.goros.intellij.metamodel.psi.impl.AttributeMixin'
implements='monet.goros.intellij.metamodel.psi.Attribute'
}
word\:\:= VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT
{
mixin= 'monet.goros.intellij.metamodel.psi.impl.WordMixin'
implements='monet.goros.intellij.metamodel.psi.Word'
}
referenceStatement\:\:= VAR identifierReference LIST? variableNames
{
mixin= 'monet.goros.intellij.metamodel.psi.impl.ReferenceStatementMixin'
implements='monet.goros.intellij.metamodel.psi.ReferenceStatement'
}
variableNames\:\:= IDENTIFIER_KEY (COMMA IDENTIFIER_KEY)*;

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

