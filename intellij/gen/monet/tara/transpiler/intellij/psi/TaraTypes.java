// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.transpiler.intellij.psi.impl.*;

public interface TaraTypes {

  IElementType ANNOTATION = new TaraElementType("ANNOTATION");
  IElementType ATTRIBUTE = new TaraElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_LIST = new TaraElementType("ATTRIBUTE_LIST");
  IElementType BOOLEAN_ASSIGN = new TaraElementType("BOOLEAN_ASSIGN");
  IElementType BOOLEAN_LIST_ASSIGN = new TaraElementType("BOOLEAN_LIST_ASSIGN");
  IElementType CHILD = new TaraElementType("CHILD");
  IElementType CHILD_ANNOTATION = new TaraElementType("CHILD_ANNOTATION");
  IElementType CONCEPT = new TaraElementType("CONCEPT");
  IElementType CONCEPT_ANNOTATION = new TaraElementType("CONCEPT_ANNOTATION");
  IElementType CONCEPT_BODY = new TaraElementType("CONCEPT_BODY");
  IElementType CONCEPT_CONSTITUENTS = new TaraElementType("CONCEPT_CONSTITUENTS");
  IElementType CONCEPT_SIGNATURE = new TaraElementType("CONCEPT_SIGNATURE");
  IElementType DOC = new TaraElementType("DOC");
  IElementType DOUBLE_ASSIGN = new TaraElementType("DOUBLE_ASSIGN");
  IElementType DOUBLE_LIST_ASSIGN = new TaraElementType("DOUBLE_LIST_ASSIGN");
  IElementType FROM = new TaraElementType("FROM");
  IElementType FROM_BODY = new TaraElementType("FROM_BODY");
  IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
  IElementType INTEGER_ASSIGN = new TaraElementType("INTEGER_ASSIGN");
  IElementType INTEGER_LIST_ASSIGN = new TaraElementType("INTEGER_LIST_ASSIGN");
  IElementType INTEGER_VALUE = new TaraElementType("INTEGER_VALUE");
  IElementType MODIFIER = new TaraElementType("MODIFIER");
  IElementType NATURAL_ASSIGN = new TaraElementType("NATURAL_ASSIGN");
  IElementType NATURAL_LIST_ASSIGN = new TaraElementType("NATURAL_LIST_ASSIGN");
  IElementType RANGE = new TaraElementType("RANGE");
  IElementType RANGE_ANNOTATION = new TaraElementType("RANGE_ANNOTATION");
  IElementType REFERENCE_STATEMENT = new TaraElementType("REFERENCE_STATEMENT");
  IElementType REFERENCE_STATEMENT_LIST = new TaraElementType("REFERENCE_STATEMENT_LIST");
  IElementType STRING_ASSIGN = new TaraElementType("STRING_ASSIGN");
  IElementType STRING_LIST_ASSIGN = new TaraElementType("STRING_LIST_ASSIGN");
  IElementType WORD_BODY = new TaraElementType("WORD_BODY");

  IElementType ABSTRACT = new TaraTokenType("ABSTRACT");
  IElementType AS = new TaraTokenType("AS");
  IElementType ASSIGN = new TaraTokenType("ASSIGN");
  IElementType BOOLEAN_TYPE = new TaraTokenType("BOOLEAN_TYPE");
  IElementType BOOLEAN_VALUE = new TaraTokenType("BOOLEAN_VALUE");
  IElementType CLOSE_AN = new TaraTokenType("CLOSE_AN");
  IElementType CONCEPT_KEY = new TaraTokenType("CONCEPT_KEY");
  IElementType DEDENT = new TaraTokenType("DEDENT");
  IElementType DOC_BLOCK = new TaraTokenType("DOC_BLOCK");
  IElementType DOC_LINE = new TaraTokenType("DOC_LINE");
  IElementType DOT = new TaraTokenType("DOT");
  IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
  IElementType DOUBLE_VALUE = new TaraTokenType("DOUBLE_VALUE");
  IElementType EXTENSIBLE = new TaraTokenType("EXTENSIBLE");
  IElementType FINAL = new TaraTokenType("FINAL");
  IElementType FROM_KEY = new TaraTokenType("FROM_KEY");
  IElementType HAS_CODE = new TaraTokenType("HAS_CODE");
  IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
  IElementType INDENT = new TaraTokenType("INDENT");
  IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
  IElementType LEFT_BRACKET = new TaraTokenType("LEFT_BRACKET");
  IElementType LIST = new TaraTokenType("LIST");
  IElementType MULTIPLE = new TaraTokenType("MULTIPLE");
  IElementType NATURAL_TYPE = new TaraTokenType("NATURAL_TYPE");
  IElementType NATURAL_VALUE = new TaraTokenType("NATURAL_VALUE");
  IElementType NEGATIVE_VALUE = new TaraTokenType("NEGATIVE_VALUE");
  IElementType OPEN_AN = new TaraTokenType("OPEN_AN");
  IElementType OPTIONAL = new TaraTokenType("OPTIONAL");
  IElementType RIGHT_BRACKET = new TaraTokenType("RIGHT_BRACKET");
  IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
  IElementType STRING_VALUE = new TaraTokenType("STRING_VALUE");
  IElementType UID_TYPE = new TaraTokenType("UID_TYPE");
  IElementType VAR = new TaraTokenType("VAR");
  IElementType WORD = new TaraTokenType("WORD");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ANNOTATION) {
        return new TaraAnnotationImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new TaraAttributeImpl(node);
      }
      else if (type == ATTRIBUTE_LIST) {
        return new TaraAttributeListImpl(node);
      }
      else if (type == BOOLEAN_ASSIGN) {
        return new TaraBooleanAssignImpl(node);
      }
      else if (type == BOOLEAN_LIST_ASSIGN) {
        return new TaraBooleanListAssignImpl(node);
      }
      else if (type == CHILD) {
        return new TaraChildImpl(node);
      }
      else if (type == CHILD_ANNOTATION) {
        return new TaraChildAnnotationImpl(node);
      }
      else if (type == CONCEPT) {
        return new TaraConceptImpl(node);
      }
      else if (type == CONCEPT_ANNOTATION) {
        return new TaraConceptAnnotationImpl(node);
      }
      else if (type == CONCEPT_BODY) {
        return new TaraConceptBodyImpl(node);
      }
      else if (type == CONCEPT_CONSTITUENTS) {
        return new TaraConceptConstituentsImpl(node);
      }
      else if (type == CONCEPT_SIGNATURE) {
        return new TaraConceptSignatureImpl(node);
      }
      else if (type == DOC) {
        return new TaraDocImpl(node);
      }
      else if (type == DOUBLE_ASSIGN) {
        return new TaraDoubleAssignImpl(node);
      }
      else if (type == DOUBLE_LIST_ASSIGN) {
        return new TaraDoubleListAssignImpl(node);
      }
      else if (type == FROM) {
        return new TaraFromImpl(node);
      }
      else if (type == FROM_BODY) {
        return new TaraFromBodyImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new TaraIdentifierImpl(node);
      }
      else if (type == INTEGER_ASSIGN) {
        return new TaraIntegerAssignImpl(node);
      }
      else if (type == INTEGER_LIST_ASSIGN) {
        return new TaraIntegerListAssignImpl(node);
      }
      else if (type == INTEGER_VALUE) {
        return new TaraIntegerValueImpl(node);
      }
      else if (type == MODIFIER) {
        return new TaraModifierImpl(node);
      }
      else if (type == NATURAL_ASSIGN) {
        return new TaraNaturalAssignImpl(node);
      }
      else if (type == NATURAL_LIST_ASSIGN) {
        return new TaraNaturalListAssignImpl(node);
      }
      else if (type == RANGE) {
        return new TaraRangeImpl(node);
      }
      else if (type == RANGE_ANNOTATION) {
        return new TaraRangeAnnotationImpl(node);
      }
      else if (type == REFERENCE_STATEMENT) {
        return new TaraReferenceStatementImpl(node);
      }
      else if (type == REFERENCE_STATEMENT_LIST) {
        return new TaraReferenceStatementListImpl(node);
      }
      else if (type == STRING_ASSIGN) {
        return new TaraStringAssignImpl(node);
      }
      else if (type == STRING_LIST_ASSIGN) {
        return new TaraStringListAssignImpl(node);
      }
      else if (type == WORD_BODY) {
        return new TaraWordBodyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
