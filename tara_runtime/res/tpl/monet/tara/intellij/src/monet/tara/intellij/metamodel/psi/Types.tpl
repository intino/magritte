// This is a generated file. Not intended for manual editing.
package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import monet.::projectName::.intellij.metamodel.psi.impl.*;

public interface ::projectProperName::Types {

  IElementType ANNOTATIONS = new ::projectProperName::ElementType("ANNOTATIONS");
  IElementType ATTRIBUTE = new ::projectProperName::ElementType("ATTRIBUTE");
  IElementType BODY = new ::projectProperName::ElementType("BODY");
  IElementType BOOLEAN_LIST = new ::projectProperName::ElementType("BOOLEAN_LIST");
  IElementType BOOLEAN_VALUE = new ::projectProperName::ElementType("BOOLEAN_VALUE");
  IElementType CONCEPT = new ::projectProperName::ElementType("CONCEPT");
  IElementType CONCEPT_INJECTION = new ::projectProperName::ElementType("CONCEPT_INJECTION");
  IElementType DOC = new ::projectProperName::ElementType("DOC");
  IElementType DOUBLE_LIST = new ::projectProperName::ElementType("DOUBLE_LIST");
  IElementType DOUBLE_VALUE = new ::projectProperName::ElementType("DOUBLE_VALUE");
  IElementType EXTENDED_CONCEPT = new ::projectProperName::ElementType("EXTENDED_CONCEPT");
  IElementType IDENTIFIER = new ::projectProperName::ElementType("IDENTIFIER");
  IElementType INTEGER_LIST = new ::projectProperName::ElementType("INTEGER_LIST");
  IElementType INTEGER_VALUE = new ::projectProperName::ElementType("INTEGER_VALUE");
  IElementType MODIFIER = new ::projectProperName::ElementType("MODIFIER");
  IElementType MORPH = new ::projectProperName::ElementType("MORPH");
  IElementType NATURAL_LIST = new ::projectProperName::ElementType("NATURAL_LIST");
  IElementType NATURAL_VALUE = new ::projectProperName::ElementType("NATURAL_VALUE");
  IElementType POLYMORPHIC = new ::projectProperName::ElementType("POLYMORPHIC");
  IElementType REFERENCE_STATEMENT = new ::projectProperName::ElementType("REFERENCE_STATEMENT");
  IElementType SIGNATURE = new ::projectProperName::ElementType("SIGNATURE");
  IElementType STRING_LIST = new ::projectProperName::ElementType("STRING_LIST");
  IElementType STRING_VALUE = new ::projectProperName::ElementType("STRING_VALUE");
  IElementType VARIABLE_NAMES = new ::projectProperName::ElementType("VARIABLE_NAMES");
  IElementType WORD = new ::projectProperName::ElementType("WORD");

  IElementType ABSTRACT = new ::projectProperName::TokenType("ABSTRACT");
  IElementType AS = new ::projectProperName::TokenType("AS");
  IElementType ASSIGN = new ::projectProperName::TokenType("ASSIGN");
  IElementType BOOLEAN_TYPE = new ::projectProperName::TokenType("BOOLEAN_TYPE");
  IElementType BOOLEAN_VALUE_KEY = new ::projectProperName::TokenType("BOOLEAN_VALUE_KEY");
  IElementType CLOSE_AN = new ::projectProperName::TokenType("CLOSE_AN");
  IElementType COMMA = new ::projectProperName::TokenType("COMMA");
  IElementType CONCEPT_KEY = new ::projectProperName::TokenType("CONCEPT_KEY");
  IElementType DEDENT = new ::projectProperName::TokenType("DEDENT");
  IElementType DOC_LINE = new ::projectProperName::TokenType("DOC_LINE");
  IElementType DOT = new ::projectProperName::TokenType("DOT");
  IElementType DOUBLE_TYPE = new ::projectProperName::TokenType("DOUBLE_TYPE");
  IElementType DOUBLE_VALUE_KEY = new ::projectProperName::TokenType("DOUBLE_VALUE_KEY");
  IElementType EXTENSIBLE = new ::projectProperName::TokenType("EXTENSIBLE");
  IElementType FINAL = new ::projectProperName::TokenType("FINAL");
  IElementType HAS_CODE = new ::projectProperName::TokenType("HAS_CODE");
  IElementType IDENTIFIER_KEY = new ::projectProperName::TokenType("IDENTIFIER_KEY");
  IElementType INT_TYPE = new ::projectProperName::TokenType("INT_TYPE");
  IElementType LEFT_SQUARE = new ::projectProperName::TokenType("LEFT_SQUARE");
  IElementType LIST = new ::projectProperName::TokenType("LIST");
  IElementType MORPH_KEY = new ::projectProperName::TokenType("MORPH_KEY");
  IElementType MULTIPLE = new ::projectProperName::TokenType("MULTIPLE");
  IElementType NATURAL_TYPE = new ::projectProperName::TokenType("NATURAL_TYPE");
  IElementType NATURAL_VALUE_KEY = new ::projectProperName::TokenType("NATURAL_VALUE_KEY");
  IElementType NEGATIVE_VALUE_KEY = new ::projectProperName::TokenType("NEGATIVE_VALUE_KEY");
  IElementType NEW = new ::projectProperName::TokenType("NEW");
  IElementType NEWLINE = new ::projectProperName::TokenType("NEWLINE");
  IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
  IElementType OPEN_AN = new ::projectProperName::TokenType("OPEN_AN");
  IElementType OPTIONAL = new ::projectProperName::TokenType("OPTIONAL");
  IElementType POLYMORPHIC_KEY = new ::projectProperName::TokenType("POLYMORPHIC_KEY");
  IElementType RIGHT_SQUARE = new ::projectProperName::TokenType("RIGHT_SQUARE");
  IElementType ROOT = new ::projectProperName::TokenType("ROOT");
  IElementType SINGLETON = new ::projectProperName::TokenType("SINGLETON");
  IElementType STRING_TYPE = new ::projectProperName::TokenType("STRING_TYPE");
  IElementType STRING_VALUE_KEY = new ::projectProperName::TokenType("STRING_VALUE_KEY");
  IElementType UID_TYPE = new ::projectProperName::TokenType("UID_TYPE");
  IElementType VAR = new ::projectProperName::TokenType("VAR");
  IElementType WORD_KEY = new ::projectProperName::TokenType("WORD_KEY");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ANNOTATIONS) {
        return new ::projectProperName::AnnotationsImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new ::projectProperName::AttributeImpl(node);
      }
      else if (type == BODY) {
        return new ::projectProperName::BodyImpl(node);
      }
      else if (type == BOOLEAN_LIST) {
        return new ::projectProperName::BooleanListImpl(node);
      }
      else if (type == BOOLEAN_VALUE) {
        return new ::projectProperName::BooleanValueImpl(node);
      }
      else if (type == CONCEPT) {
        return new ::projectProperName::DefinitionImpl(node);
      }
      else if (type == CONCEPT_INJECTION) {
        return new ::projectProperName::DefinitionInjectionImpl(node);
      }
      else if (type == DOC) {
        return new ::projectProperName::DocImpl(node);
      }
      else if (type == DOUBLE_LIST) {
        return new ::projectProperName::DoubleListImpl(node);
      }
      else if (type == DOUBLE_VALUE) {
        return new ::projectProperName::DoubleValueImpl(node);
      }
      else if (type == EXTENDED_CONCEPT) {
        return new ::projectProperName::ExtendedDefinitionImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new ::projectProperName::IdentifierImpl(node);
      }
      else if (type == INTEGER_LIST) {
        return new ::projectProperName::IntegerListImpl(node);
      }
      else if (type == INTEGER_VALUE) {
        return new ::projectProperName::IntegerValueImpl(node);
      }
      else if (type == MODIFIER) {
        return new ::projectProperName::ModifierImpl(node);
      }
      else if (type == MORPH) {
        return new ::projectProperName::MorphImpl(node);
      }
      else if (type == NATURAL_LIST) {
        return new ::projectProperName::NaturalListImpl(node);
      }
      else if (type == NATURAL_VALUE) {
        return new ::projectProperName::NaturalValueImpl(node);
      }
      else if (type == POLYMORPHIC) {
        return new ::projectProperName::PolymorphicImpl(node);
      }
      else if (type == REFERENCE_STATEMENT) {
        return new ::projectProperName::ReferenceStatementImpl(node);
      }
      else if (type == SIGNATURE) {
        return new ::projectProperName::SignatureImpl(node);
      }
      else if (type == STRING_LIST) {
        return new ::projectProperName::StringListImpl(node);
      }
      else if (type == STRING_VALUE) {
        return new ::projectProperName::StringValueImpl(node);
      }
      else if (type == VARIABLE_NAMES) {
        return new ::projectProperName::VariableNamesImpl(node);
      }
      else if (type == WORD) {
        return new ::projectProperName::WordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
