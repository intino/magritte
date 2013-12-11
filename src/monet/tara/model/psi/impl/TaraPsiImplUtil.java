package monet.tara.model.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;
import monet.tara.metamodelplugin.psi.TaraTypes;
import monet.tara.metamodelplugin.psi.impl.TaraReferencesBodyImpl;
import monet.tara.model.psi.TaraElementFactory;

public class TaraPsiImplUtil {

	public static String getIdentifier(TaraConceptDefinition element) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getText();
		else return null;
	}

	public static String getIdentifier(TaraReferencesBodyImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getName(TaraConceptDefinition element) {
		return getIdentifier(element);
	}

	public static PsiElement setName(TaraConceptDefinition element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			TaraConceptDefinition concept = TaraElementFactory.createCpncept(element.getProject(), newName);
			ASTNode newKeyNode = concept.getFirstChild().getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getNameIdentifier(TaraConceptDefinition element) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}

}
