// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import monet.tara.metamodel.psi.impl.*;

public interface TaraTypes {

	IElementType CHILD = new TaraElementType("CHILD");
	IElementType CONCEPT_BODY = new TaraElementType("CONCEPT_BODY");
	IElementType CONCEPT_DEFINITION = new TaraElementType("CONCEPT_DEFINITION");
	IElementType CONCEPT_SIGNATURE = new TaraElementType("CONCEPT_SIGNATURE");
	IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
	IElementType INTENTION = new TaraElementType("INTENTION");
	IElementType PRIMITIVE = new TaraElementType("PRIMITIVE");
	IElementType PRIMITIVE_TYPE = new TaraElementType("PRIMITIVE_TYPE");
	IElementType PROPERTY = new TaraElementType("PROPERTY");
	IElementType REFERENCE_STATEMENT = new TaraElementType("REFERENCE_STATEMENT");
	IElementType STATEMENT = new TaraElementType("STATEMENT");

	IElementType ANNOTATION = new TaraTokenType("ANNOTATION");
	IElementType ANONYMOUS = new TaraTokenType("ANONYMOUS");
	IElementType ASSIGN = new TaraTokenType("ASSIGN");
	IElementType COLON = new TaraTokenType("COLON");
	IElementType COMMENT = new TaraTokenType("COMMENT");
	IElementType CONCEPT = new TaraTokenType("CONCEPT");
	IElementType DEDENT = new TaraTokenType("DEDENT");
	IElementType DOUBLE = new TaraTokenType("DOUBLE");
	IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
	IElementType HAS = new TaraTokenType("HAS");
	IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
	IElementType ID_TYPE = new TaraTokenType("ID_TYPE");
	IElementType INDENT = new TaraTokenType("INDENT");
	IElementType INT = new TaraTokenType("INT");
	IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
	IElementType IS = new TaraTokenType("IS");
	IElementType LEFT_P = new TaraTokenType("LEFT_P");
	IElementType MODIFIERS = new TaraTokenType("MODIFIERS");
	IElementType RANGE = new TaraTokenType("RANGE");
	IElementType REF = new TaraTokenType("REF");
	IElementType RIGHT_P = new TaraTokenType("RIGHT_P");
	IElementType STRING = new TaraTokenType("STRING");
	IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
	IElementType USE = new TaraTokenType("USE");

	class Factory {
		public static PsiElement createElement(ASTNode node) {
			IElementType type = node.getElementType();
			if (type == CHILD) {
				return new TaraChildImpl(node);
			} else if (type == CONCEPT_BODY) {
				return new TaraConceptBodyImpl(node);
			} else if (type == CONCEPT_DEFINITION) {
				return new TaraConceptDefinitionImpl(node);
			} else if (type == CONCEPT_SIGNATURE) {
				return new TaraConceptSignatureImpl(node);
			} else if (type == IDENTIFIER) {
				return new TaraIdentifierImpl(node);
			} else if (type == INTENTION) {
				return new TaraIntentionImpl(node);
			} else if (type == PRIMITIVE) {
				return new TaraPrimitiveImpl(node);
			} else if (type == PRIMITIVE_TYPE) {
				return new TaraPrimitiveTypeImpl(node);
			} else if (type == PROPERTY) {
				return new TaraPropertyImpl(node);
			} else if (type == REFERENCE_STATEMENT) {
				return new TaraReferenceStatementImpl(node);
			} else if (type == STATEMENT) {
				return new TaraStatementImpl(node);
			}
			throw new AssertionError("Unknown element type: " + type);
		}
	}
}
