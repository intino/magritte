package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraPsiImplUtil {

	public static String getIdentifier(TaraIdentifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;

	}

	public static String getIdentifier(Concept element) {
		ASTNode valueNode;
		if (element.getNode().findChildByType(TaraTypes.SIGNATURE) != null) {
			valueNode = element.getNode().findChildByType(TaraTypes.SIGNATURE).findChildByType(TaraTypes.IDENTIFIER);
			if (valueNode != null) return valueNode.getText();
		}
		return null;
	}

	public static PsiElement getIdentifierNode(Concept element) {
		if (element.getNode().findChildByType(TaraTypes.SIGNATURE) != null) {
			ASTNode valueNode = element.getNode().findChildByType(TaraTypes.SIGNATURE).findChildByType(TaraTypes.IDENTIFIER);
			if (valueNode != null) return valueNode.getPsi();
		}
		return null;
	}

	public static String getIdentifier(TaraExtendedConcept element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(TaraReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(Signature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			Concept concept = TaraElementFactoryImpl.getInstance(element.getProject()).createConcept(newName);
			ASTNode newKeyNode = concept.getIdentifierNode().getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(TaraSignature element) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}


	public static List<TaraConcept> getChildrenInBody(TaraBody body) {
		return body.getConceptList();
	}

	public static List<TaraAttribute> getAttributesInBody(TaraBody body) {
		return body.getAttributeList();
	}


	public static List<TaraConcept> getChildrenOf(Concept concept) {
		TaraBody body;
		if ((body = concept.getBody()) != null)
			return getChildrenInBody(body);
		return Collections.EMPTY_LIST;
	}

	public static TaraConcept resolveContextOfRef(TaraExtendedConcept identifier) {
		PsiElement element = identifier;
		while (!(element.getParent() instanceof Concept))
			element = element.getParent();
		return (TaraConcept) element.getParent();
	}

	public static TaraConcept getContextOf(PsiElement element1) {
		try {
			PsiElement element = element1;
			while ((element.getParent() != null) && !(element.getParent() instanceof TaraFile) && !(element.getParent() instanceof Concept))
				element = element.getParent();
			return (element.getParent() instanceof TaraConcept) ? (TaraConcept) element.getParent() : null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PsiElement[] getAnnotations(TaraAnnotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child.getNextSibling().getNextSibling() != null) {
			if (!child.getNextSibling().getText().equals(" ")) annotationList.add(child.getNextSibling());
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
