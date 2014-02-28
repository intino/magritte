// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.intellij.metamodel.psi.impl.*;

public interface TaraTypes {

  IElementType ANNOTATIONS = new TaraElementType("ANNOTATIONS");
  IElementType ATTRIBUTE = new TaraElementType("ATTRIBUTE");
  IElementType BODY = new TaraElementType("BODY");
  IElementType BOOLEAN_ASSIGN = new TaraElementType("BOOLEAN_ASSIGN");
  IElementType BOOLEAN_LIST_ASSIGN = new TaraElementType("BOOLEAN_LIST_ASSIGN");
  IElementType CONCEPT = new TaraElementType("CONCEPT");
  IElementType CONCEPT_INJECTION = new TaraElementType("CONCEPT_INJECTION");
  IElementType DOC = new TaraElementType("DOC");
  IElementType DOUBLE_ASSIGN = new TaraElementType("DOUBLE_ASSIGN");
  IElementType DOUBLE_LIST_ASSIGN = new TaraElementType("DOUBLE_LIST_ASSIGN");
  IElementType EXTENDED_CONCEPT = new TaraElementType("EXTENDED_CONCEPT");
  IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
  IElementType INTEGER_ASSIGN = new TaraElementType("INTEGER_ASSIGN");
  IElementType INTEGER_LIST_ASSIGN = new TaraElementType("INTEGER_LIST_ASSIGN");
  IElementType MODIFIER = new TaraElementType("MODIFIER");
  IElementType MORPH = new TaraElementType("MORPH");
  IElementType NATURAL_ASSIGN = new TaraElementType("NATURAL_ASSIGN");
  IElementType NATURAL_LIST_ASSIGN = new TaraElementType("NATURAL_LIST_ASSIGN");
  IElementType POLYMORPHIC = new TaraElementType("POLYMORPHIC");
  IElementType REFERENCE_STATEMENT = new TaraElementType("REFERENCE_STATEMENT");
  IElementType SIGNATURE = new TaraElementType("SIGNATURE");
  IElementType STRING_ASSIGN = new TaraElementType("STRING_ASSIGN");
  IElementType STRING_LIST_ASSIGN = new TaraElementType("STRING_LIST_ASSIGN");
  IElementType WORD = new TaraElementType("WORD");

  IElementType ABSTRACT = new TaraTokenType("ABSTRACT");
  IElementType AS = new TaraTokenType("AS");
  IElementType ASSIGN = new TaraTokenType("ASSIGN");
  IElementType BOOLEAN_TYPE = new TaraTokenType("BOOLEAN_TYPE");
  IElementType BOOLEAN_VALUE = new TaraTokenType("BOOLEAN_VALUE");
  IElementType CLOSE_AN = new TaraTokenType("CLOSE_AN");
  IElementType CONCEPT_KEY = new TaraTokenType("CONCEPT_KEY");
  IElementType DEDENT = new TaraTokenType("DEDENT");
  IElementType DOC_LINE = new TaraTokenType("DOC_LINE");
  IElementType DOT = new TaraTokenType("DOT");
  IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
  IElementType DOUBLE_VALUE = new TaraTokenType("DOUBLE_VALUE");
  IElementType EXTENSIBLE = new TaraTokenType("EXTENSIBLE");
  IElementType FINAL = new TaraTokenType("FINAL");
  IElementType HAS_CODE = new TaraTokenType("HAS_CODE");
  IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
  IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
  IElementType LEFT_SQUARE = new TaraTokenType("LEFT_SQUARE");
  IElementType LIST = new TaraTokenType("LIST");
  IElementType MORPH_KEY = new TaraTokenType("MORPH_KEY");
  IElementType MULTIPLE = new TaraTokenType("MULTIPLE");
  IElementType NATURAL_TYPE = new TaraTokenType("NATURAL_TYPE");
  IElementType NATURAL_VALUE = new TaraTokenType("NATURAL_VALUE");
  IElementType NEGATIVE_VALUE = new TaraTokenType("NEGATIVE_VALUE");
  IElementType NEW = new TaraTokenType("NEW");
  IElementType NEWLINE = new TaraTokenType("NEWLINE");
  IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
  IElementType OPEN_AN = new TaraTokenType("OPEN_AN");
  IElementType OPTIONAL = new TaraTokenType("OPTIONAL");
  IElementType POLYMORPHIC_KEY = new TaraTokenType("POLYMORPHIC_KEY");
  IElementType RIGHT_SQUARE = new TaraTokenType("RIGHT_SQUARE");
  IElementType ROOT = new TaraTokenType("ROOT");
  IElementType SINGLETON = new TaraTokenType("SINGLETON");
  IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
  IElementType STRING_VALUE = new TaraTokenType("STRING_VALUE");
  IElementType UID_TYPE = new TaraTokenType("UID_TYPE");
  IElementType VAR = new TaraTokenType("VAR");
  IElementType WORD_KEY = new TaraTokenType("WORD_KEY");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ANNOTATIONS) {
        return new TaraAnnotationsImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new TaraAttributeImpl(node);
      }
      else if (type == BODY) {
        return new TaraBodyImpl(node);
      }
      else if (type == BOOLEAN_ASSIGN) {
        return new TaraBooleanAssignImpl(node);
      }
      else if (type == BOOLEAN_LIST_ASSIGN) {
        return new TaraBooleanListAssignImpl(node);
      }
      else if (type == CONCEPT) {
        return new TaraConceptImpl(node);
      }
      else if (type == CONCEPT_INJECTION) {
        return new TaraConceptInjectionImpl(node);
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
      else if (type == EXTENDED_CONCEPT) {
        return new TaraExtendedConceptImpl(node);
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
      else if (type == MODIFIER) {
        return new TaraModifierImpl(node);
      }
      else if (type == MORPH) {
        return new TaraMorphImpl(node);
      }
      else if (type == NATURAL_ASSIGN) {
        return new TaraNaturalAssignImpl(node);
      }
      else if (type == NATURAL_LIST_ASSIGN) {
        return new TaraNaturalListAssignImpl(node);
      }
      else if (type == POLYMORPHIC) {
        return new TaraPolymorphicImpl(node);
      }
      else if (type == REFERENCE_STATEMENT) {
        return new TaraReferenceStatementImpl(node);
      }
      else if (type == SIGNATURE) {
        return new TaraSignatureImpl(node);
      }
      else if (type == STRING_ASSIGN) {
        return new TaraStringAssignImpl(node);
      }
      else if (type == STRING_LIST_ASSIGN) {
        return new TaraStringListAssignImpl(node);
      }
      else if (type == WORD) {
        return new TaraWordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
