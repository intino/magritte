// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import monet.tara.metamodel.psi.impl.*;

public interface TaraTypes {

  IElementType ANNOTATION = new TaraElementType("ANNOTATION");
  IElementType CHILDREN_BODY = new TaraElementType("CHILDREN_BODY");
  IElementType CONCEPT_BODY = new TaraElementType("CONCEPT_BODY");
  IElementType CONCEPT_DEFINITION = new TaraElementType("CONCEPT_DEFINITION");
  IElementType CONCEPT_SIGNATURE = new TaraElementType("CONCEPT_SIGNATURE");
  IElementType PRIMITIVE = new TaraElementType("PRIMITIVE");
  IElementType PRIMITIVE_TYPE = new TaraElementType("PRIMITIVE_TYPE");
  IElementType PROPERTIES_BODY = new TaraElementType("PROPERTIES_BODY");
  IElementType REFERENCES_BODY = new TaraElementType("REFERENCES_BODY");
  IElementType STATEMENT = new TaraElementType("STATEMENT");

  IElementType ACTION = new TaraTokenType("ACTION");
  IElementType ASSIGN = new TaraTokenType("ASSIGN");
  IElementType AT_ROOT = new TaraTokenType("AT_ROOT");
  IElementType COMMENT = new TaraTokenType("COMMENT");
  IElementType CONCEPT = new TaraTokenType("CONCEPT");
  IElementType DEDENT = new TaraTokenType("DEDENT");
  IElementType DOUBLE = new TaraTokenType("DOUBLE");
  IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
  IElementType END_CONCEPT = new TaraTokenType("END_CONCEPT");
  IElementType EXTENSIBLE = new TaraTokenType("EXTENSIBLE");
  IElementType HAS = new TaraTokenType("HAS");
  IElementType IDENTIFIER = new TaraTokenType("IDENTIFIER");
  IElementType ID_TYPE = new TaraTokenType("ID_TYPE");
  IElementType INDENT = new TaraTokenType("INDENT");
  IElementType INT = new TaraTokenType("INT");
  IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
  IElementType IS = new TaraTokenType("IS");
  IElementType MODIFIERS = new TaraTokenType("MODIFIERS");
  IElementType NAMEABLE = new TaraTokenType("NAMEABLE");
  IElementType REF = new TaraTokenType("REF");
  IElementType STRING = new TaraTokenType("STRING");
  IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ANNOTATION) {
        return new TaraAnnotationImpl(node);
      }
      else if (type == CHILDREN_BODY) {
        return new TaraChildrenBodyImpl(node);
      }
      else if (type == CONCEPT_BODY) {
        return new TaraConceptBodyImpl(node);
      }
      else if (type == CONCEPT_DEFINITION) {
        return new TaraConceptDefinitionImpl(node);
      }
      else if (type == CONCEPT_SIGNATURE) {
        return new TaraConceptSignatureImpl(node);
      }
      else if (type == PRIMITIVE) {
        return new TaraPrimitiveImpl(node);
      }
      else if (type == PRIMITIVE_TYPE) {
        return new TaraPrimitiveTypeImpl(node);
      }
      else if (type == PROPERTIES_BODY) {
        return new TaraPropertiesBodyImpl(node);
      }
      else if (type == REFERENCES_BODY) {
        return new TaraReferencesBodyImpl(node);
      }
      else if (type == STATEMENT) {
        return new TaraStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
