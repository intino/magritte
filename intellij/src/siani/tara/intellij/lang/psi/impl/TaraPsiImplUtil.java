package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraPsiImplUtil {

	private static final Logger LOG = Logger.getInstance(TaraPsiImplUtil.class.getName());

	private TaraPsiImplUtil() {
	}

	public static String getIdentifier(Identifier keyNode) {
		return keyNode != null ? keyNode.getText() : null;
	}

	public static String getIdentifier(Concept element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			return valueNode.getText();
		}
		return null;
	}

	public static Identifier getIdentifierNode(Concept element) {
		return element.getSignature().getIdentifier() != null ? element.getSignature().getIdentifier() : null;
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
		List<Concept> conceptList = (List<Concept>) body.getConceptList();
		return conceptList == null ? Collections.EMPTY_LIST : conceptList;
	}

	public static List<Attribute> getAttributesInBody(Body body) {
		return (List<Attribute>) body.getAttributeList();
	}


	public static List<Concept> getChildrenOf(Concept concept) {
		if (concept != null) {
			Body body = concept.getBody();
			if (body != null) {
				List<Concept> children = getChildrenInBody(body);
				List<Concept> cases = new ArrayList<>();
				for (Concept child : children) {
					cases.addAll(collectInnerCases(child));
				}
				children.addAll(cases);
				return children;
			}
		}
		return Collections.EMPTY_LIST;
	}

	private static List<Concept> collectInnerCases(Concept concept) {
		List<Concept> cases = new ArrayList();
		for (Concept caseConcept : concept.getCases()) {
			cases.add(caseConcept);
			cases.addAll(collectInnerCases((caseConcept)));
		}
		return cases;
	}

	@Nullable
	public static Concept getContextOf(PsiElement element) {
		try {
			PsiElement aElement = element;
			while ((aElement.getParent() != null) && !(aElement.getParent() instanceof TaraFile) && !(aElement.getParent() instanceof Concept))
				aElement = aElement.getParent();
			return (aElement.getParent() instanceof Concept) ? (Concept) aElement.getParent() : null;
		} catch (NullPointerException e) {
//			LOG.error(e.getMessage());
			return null;
		}
	}

	public static PsiElement[] getAnnotations(Annotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child.getNextSibling() != null && child.getNextSibling().getNextSibling() != null) {
			if (!" ".equals(child.getNextSibling().getText())) annotationList.add(child.getNextSibling());
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
