// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.impl.*;

public interface TaraTypes {

  IElementType ANNOTATIONS = new TaraElementType("ANNOTATIONS");
  IElementType ATTRIBUTE = new TaraElementType("ATTRIBUTE");
  IElementType BODY = new TaraElementType("BODY");
  IElementType BOOLEAN_LIST = new TaraElementType("BOOLEAN_LIST");
  IElementType BOOLEAN_VALUE = new TaraElementType("BOOLEAN_VALUE");
  IElementType BOX = new TaraElementType("BOX");
  IElementType CONCEPT = new TaraElementType("CONCEPT");
  IElementType DOC = new TaraElementType("DOC");
  IElementType DOUBLE_LIST = new TaraElementType("DOUBLE_LIST");
  IElementType DOUBLE_VALUE = new TaraElementType("DOUBLE_VALUE");
  IElementType EMPTY = new TaraElementType("EMPTY");
  IElementType EMPTY_FIELD = new TaraElementType("EMPTY_FIELD");
  IElementType EXPLICIT = new TaraElementType("EXPLICIT");
  IElementType HEADER = new TaraElementType("HEADER");
  IElementType HEADER_REFERENCE = new TaraElementType("HEADER_REFERENCE");
  IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
  IElementType IDENTIFIER_LIST = new TaraElementType("IDENTIFIER_LIST");
  IElementType IDENTIFIER_REFERENCE = new TaraElementType("IDENTIFIER_REFERENCE");
  IElementType IMPORT_STATEMENT = new TaraElementType("IMPORT_STATEMENT");
  IElementType INTEGER_LIST = new TaraElementType("INTEGER_LIST");
  IElementType INTEGER_VALUE = new TaraElementType("INTEGER_VALUE");
  IElementType INTENTION = new TaraElementType("INTENTION");
  IElementType META_IDENTIFIER = new TaraElementType("META_IDENTIFIER");
  IElementType META_WORD = new TaraElementType("META_WORD");
  IElementType NATURAL_LIST = new TaraElementType("NATURAL_LIST");
  IElementType NATURAL_VALUE = new TaraElementType("NATURAL_VALUE");
  IElementType PARAMETER = new TaraElementType("PARAMETER");
  IElementType PARAMETERS = new TaraElementType("PARAMETERS");
  IElementType SIGNATURE = new TaraElementType("SIGNATURE");
  IElementType STRING_LIST = new TaraElementType("STRING_LIST");
  IElementType STRING_VALUE = new TaraElementType("STRING_VALUE");
  IElementType VAR_INIT = new TaraElementType("VAR_INIT");
  IElementType WORD = new TaraElementType("WORD");

  IElementType ALIAS_TYPE = new TaraTokenType("ALIAS_TYPE");
  IElementType BOOLEAN_TYPE = new TaraTokenType("BOOLEAN_TYPE");
  IElementType BOOLEAN_VALUE_KEY = new TaraTokenType("BOOLEAN_VALUE_KEY");
  IElementType BOX_KEY = new TaraTokenType("BOX_KEY");
  IElementType CASE_KEY = new TaraTokenType("CASE_KEY");
  IElementType CLOSE_AN = new TaraTokenType("CLOSE_AN");
  IElementType COLON = new TaraTokenType("COLON");
  IElementType COMMA = new TaraTokenType("COMMA");
  IElementType DEDENT = new TaraTokenType("DEDENT");
  IElementType DOC_LINE = new TaraTokenType("DOC_LINE");
  IElementType DOT = new TaraTokenType("DOT");
  IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
  IElementType DOUBLE_VALUE_KEY = new TaraTokenType("DOUBLE_VALUE_KEY");
  IElementType EMPTY_REF = new TaraTokenType("EMPTY_REF");
  IElementType HAS_NAME = new TaraTokenType("HAS_NAME");
  IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
  IElementType IMPORT_KEY = new TaraTokenType("IMPORT_KEY");
  IElementType INTENTION_KEY = new TaraTokenType("INTENTION_KEY");
  IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
  IElementType LEFT_PARENTHESIS = new TaraTokenType("LEFT_PARENTHESIS");
  IElementType LEFT_SQUARE = new TaraTokenType("LEFT_SQUARE");
  IElementType METAIDENTIFIER_KEY = new TaraTokenType("METAIDENTIFIER_KEY");
  IElementType MULTIPLE = new TaraTokenType("MULTIPLE");
  IElementType NATURAL_TYPE = new TaraTokenType("NATURAL_TYPE");
  IElementType NATURAL_VALUE_KEY = new TaraTokenType("NATURAL_VALUE_KEY");
  IElementType NEGATIVE_VALUE_KEY = new TaraTokenType("NEGATIVE_VALUE_KEY");
  IElementType NEWLINE = new TaraTokenType("NEWLINE");
  IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
  IElementType OPEN_AN = new TaraTokenType("OPEN_AN");
  IElementType PRIVATE = new TaraTokenType("PRIVATE");
  IElementType PROPERTY = new TaraTokenType("PROPERTY");
  IElementType REQUIRED = new TaraTokenType("REQUIRED");
  IElementType RESOURCE_KEY = new TaraTokenType("RESOURCE_KEY");
  IElementType RIGHT_PARENTHESIS = new TaraTokenType("RIGHT_PARENTHESIS");
  IElementType RIGHT_SQUARE = new TaraTokenType("RIGHT_SQUARE");
  IElementType ROOT = new TaraTokenType("ROOT");
  IElementType STAR = new TaraTokenType("STAR");
  IElementType STRING_MULTILINE_VALUE_KEY = new TaraTokenType("STRING_MULTILINE_VALUE_KEY");
  IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
  IElementType STRING_VALUE_KEY = new TaraTokenType("STRING_VALUE_KEY");
  IElementType TERMINAL = new TaraTokenType("TERMINAL");
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
      else if (type == BOOLEAN_LIST) {
        return new TaraBooleanListImpl(node);
      }
      else if (type == BOOLEAN_VALUE) {
        return new TaraBooleanValueImpl(node);
      }
      else if (type == BOX) {
        return new TaraBoxImpl(node);
      }
      else if (type == CONCEPT) {
        return new TaraConceptImpl(node);
      }
      else if (type == DOC) {
        return new TaraDocImpl(node);
      }
      else if (type == DOUBLE_LIST) {
        return new TaraDoubleListImpl(node);
      }
      else if (type == DOUBLE_VALUE) {
        return new TaraDoubleValueImpl(node);
      }
      else if (type == EMPTY) {
        return new TaraEmptyImpl(node);
      }
      else if (type == EMPTY_FIELD) {
        return new TaraEmptyFieldImpl(node);
      }
      else if (type == EXPLICIT) {
        return new TaraExplicitImpl(node);
      }
      else if (type == HEADER) {
        return new TaraHeaderImpl(node);
      }
      else if (type == HEADER_REFERENCE) {
        return new TaraHeaderReferenceImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new TaraIdentifierImpl(node);
      }
      else if (type == IDENTIFIER_LIST) {
        return new TaraIdentifierListImpl(node);
      }
      else if (type == IDENTIFIER_REFERENCE) {
        return new TaraIdentifierReferenceImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new TaraImportStatementImpl(node);
      }
      else if (type == INTEGER_LIST) {
        return new TaraIntegerListImpl(node);
      }
      else if (type == INTEGER_VALUE) {
        return new TaraIntegerValueImpl(node);
      }
      else if (type == INTENTION) {
        return new TaraIntentionImpl(node);
      }
      else if (type == META_IDENTIFIER) {
        return new TaraMetaIdentifierImpl(node);
      }
      else if (type == META_WORD) {
        return new TaraMetaWordImpl(node);
      }
      else if (type == NATURAL_LIST) {
        return new TaraNaturalListImpl(node);
      }
      else if (type == NATURAL_VALUE) {
        return new TaraNaturalValueImpl(node);
      }
      else if (type == PARAMETER) {
        return new TaraParameterImpl(node);
      }
      else if (type == PARAMETERS) {
        return new TaraParametersImpl(node);
      }
      else if (type == SIGNATURE) {
        return new TaraSignatureImpl(node);
      }
      else if (type == STRING_LIST) {
        return new TaraStringListImpl(node);
      }
      else if (type == STRING_VALUE) {
        return new TaraStringValueImpl(node);
      }
      else if (type == VAR_INIT) {
        return new TaraVarInitImpl(node);
      }
      else if (type == WORD) {
        return new TaraWordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
