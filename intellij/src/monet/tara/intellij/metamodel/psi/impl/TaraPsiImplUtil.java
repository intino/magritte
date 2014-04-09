package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraPsiImplUtil {

	private static final Logger LOG = Logger.getInstance(TaraPsiImplUtil.class.getName());

	private TaraPsiImplUtil() {
	}

	public static String getIdentifier(Identifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;

	}

	public static String getIdentifier(Concept element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			if (valueNode != null) return valueNode.getText();
		}
		return null;
	}

	public static PsiElement getIdentifierNode(Concept element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			if (valueNode != null) return valueNode.getPsi();
		}
		return null;
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

	public static List<Concept> getChildrenInBody(Body body) {
		return (List<Concept>) body.getConceptList();
	}

	public static List<Attribute> getAttributesInBody(Body body) {
		return (List<Attribute>) body.getAttributeList();
	}


	public static List<Concept> getChildrenOf(Concept concept) {
		if (concept != null) {
			Body body = concept.getBody();
			if (body != null) return getChildrenInBody(body);
		}
		return Collections.EMPTY_LIST;
	}

	public static Concept resolveContextOfRef(ReferenceIdentifier identifier) {
		PsiElement element = identifier;
		while (!(element.getParent() instanceof Concept))
			element = element.getParent();
		return (Concept) element.getParent();
	}

	public static Concept getContextOf(PsiElement element1) {
		try {
			PsiElement element = element1;
			while ((element.getParent() != null) && !(element.getParent() instanceof TaraFile) && !(element.getParent() instanceof Concept))
				element = element.getParent();
			return (element.getParent() instanceof Concept) ? (Concept) element.getParent() : null;
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	public static PsiElement[] getAnnotations(Annotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child.getNextSibling().getNextSibling() != null) {
			if (!" ".equals(child.getNextSibling().getText())) annotationList.add(child.getNextSibling());
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
