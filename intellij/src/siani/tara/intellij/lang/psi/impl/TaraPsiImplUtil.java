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

	public static List<Concept> getInnerConceptsInBody(Body body) {
		if (body == null) return Collections.EMPTY_LIST;
		List<Concept> conceptList = (List<Concept>) body.getConceptList();
		return conceptList == null ? Collections.EMPTY_LIST : conceptList;
	}

	public static List<Attribute> getAttributesInBody(Body body) {
		return (List<Attribute>) body.getAttributeList();
	}


	public static List<Concept> getInnerConceptsOf(Concept concept) {
		if (concept != null) {
			Body body = concept.getBody();
			if (body != null) {
				List<Concept> children = getInnerConceptsInBody(body);
				removeSubs(children);
				List<Concept> subConcepts = new ArrayList<>();
				for (Concept child : children)
					subConcepts.addAll(collectInnerSubs(child));
				children.addAll(subConcepts);
				return children;
			}
		}
		return Collections.EMPTY_LIST;
	}

	private static void removeSubs(List<Concept> children) {
		List<Concept> list = new ArrayList();
		for (Concept concept : children) if (concept.isSub()) list.add(concept);
		children.removeAll(list);
	}

	private static List<Concept> collectInnerSubs(Concept concept) {
		List<Concept> subs = new ArrayList();
		for (Concept subConcept : concept.getSubConcepts()) {
			subs.add(subConcept);
			subs.addAll(collectInnerSubs((subConcept)));
		}
		return subs;
	}

	@Nullable
	public static Concept getContextOf(PsiElement element) {
		try {
			PsiElement aElement = element;
			while ((aElement.getParent() != null) && !(aElement.getParent() instanceof TaraBoxFile) && !(aElement.getParent() instanceof Concept))
				aElement = aElement.getParent();
			return (aElement.getParent() instanceof Concept) ? (Concept) aElement.getParent() : null;
		} catch (NullPointerException e) {
//			LOG.error(e.getMessage());
			return null;
		}
	}

	public static Concept getParentOf(Concept concept) {
		if (concept.isSub()) {
			Concept parent = concept;
			while (parent != null && parent.isSub()) parent = getContextOf(parent);
			return parent;
		} else {
			if (concept.getParentConcept() != null) {
				TaraIdentifierReference identifierReference = concept.getSignature().getIdentifierReference();
				PsiElement resolve = ReferenceManager.resolve(identifierReference);
				return getContextOf(resolve);
			}
		}
		return null;
	}

	public static PsiElement[] getAnnotations(Annotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child != null) {
			if (!" ".equals(child.getText())) annotationList.add(child);
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
