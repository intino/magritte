package monet.tara.compiler.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.compiler.intellij.psi.TaraConcept;
import monet.tara.compiler.intellij.psi.TaraConceptSignature;
import monet.tara.compiler.intellij.psi.TaraIdentifier;
import monet.tara.compiler.intellij.psi.TaraTypes;

public class TaraPsiImplUtil {

	public static String getIdentifier(TaraIdentifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;
	}

	public static String getIdentifier(TaraConcept element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.CONCEPT_SIGNATURE).findChildByType(TaraTypes.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(TaraReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(TaraConceptSignature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			TaraConcept concept = TaraElementFactoryImpl.getInstance(element.getProject()).createConcept(newName);
			ASTNode newKeyNode = concept.getFirstChild().getChildren()[0].getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(TaraConceptSignature element) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}

}