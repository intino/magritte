package monet.tafat.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tafat.intellij.psi.TafatDefinition;
import monet.tafat.intellij.psi.TafatDefinitionSignature;
import monet.tafat.intellij.psi.TafatIdentifier;
import monet.tafat.intellij.psi.TafatTypes;

public class TafatPsiImplUtil {

	public static String getIdentifier(TafatIdentifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;
	}

	public static String getIdentifier(TafatDefinition element) {
		ASTNode valueNode = element.getNode().findChildByType(TafatTypes.DEFINITION_SIGNATURE).findChildByType(TafatTypes.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(TafatReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(TafatTypes.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(TafatDefinitionSignature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TafatTypes.IDENTIFIER);
		if (keyNode != null) {
			TafatDefinition concept = TafatElementFactoryImpl.getInstance(element.getProject()).createDefinition(newName);
			ASTNode newKeyNode = concept.getFirstChild().getChildren()[0].getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(TafatDefinitionSignature element) {
		ASTNode keyNode = element.getNode().findChildByType(TafatTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}

}
