package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collection;
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
		return body == null ? Collections.EMPTY_LIST : (List<Concept>) body.getConceptList();
	}

	public static List<Variable> getVariablesInBody(Body body) {
		return body != null ? (List<Variable>) body.getAttributeList() : Collections.EMPTY_LIST;
	}


	public static List<Concept> getInnerConceptsOf(Concept concept) {
		if (concept != null && concept.getBody() != null) {
			List<Concept> children = getInnerConceptsInBody(concept.getBody());
			removeSubs(children);
			List<Concept> subConcepts = new ArrayList<>();
			for (Concept child : children)
				subConcepts.addAll(collectInnerSubs(child));
			children.addAll(subConcepts);
			return children;
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
			subs.addAll(collectInnerSubs(subConcept));
		}
		return subs;
	}

	@Nullable
	public static Concept getConceptContainerOf(PsiElement element) {
		try {
			PsiElement aElement = element;
			while ((aElement.getParent() != null)
				&& !(aElement.getParent() instanceof TaraModel)
				&& !(aElement.getParent() instanceof Concept))
				aElement = aElement.getParent();
			return (aElement.getParent() instanceof Concept) ? (Concept) aElement.getParent() : null;
		} catch (NullPointerException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@NotNull
	public static Collection<TaraFacetTarget> getFacetTargets(Concept concept) {
		if (concept.getBody() == null) return Collections.EMPTY_LIST;
		List<TaraFacetTarget> targets = new ArrayList<>();
		getFacetTargets(concept.getBody(), targets);
		return targets;
	}

	private static void getFacetTargets(Body body, List<TaraFacetTarget> targets) {
		for (TaraFacetTarget target : body.getFacetTargetList()) {
			targets.add(target);
			if (target.getBody() != null) getFacetTargets(target.getBody(), targets);
		}
	}

	@Nullable
	public static PsiElement getContextOf(PsiElement element) {
		PsiElement aElement = element;
		while (aElement.getParent() != null && isNotConceptOrFile(aElement) && isNotFacet(aElement))
			aElement = aElement.getParent();
		return aElement.getParent();
	}

	private static boolean isNotConceptOrFile(PsiElement aElement) {
		return !(aElement.getParent() instanceof TaraModel)
			&& !(aElement.getParent() instanceof Concept);
	}

	private static boolean isNotFacet(PsiElement aElement) {
		return !(aElement.getParent() instanceof TaraFacetTarget)
			&& !(aElement.getParent() instanceof TaraFacetApply);
	}

	public static Body getBodyContextOf(PsiElement element) {
		PsiElement aElement = element;
		while ((aElement.getParent() != null)
			&& !(aElement.getParent() instanceof Body))
			aElement = aElement.getParent();
		return (Body) aElement.getParent();
	}

	public static Concept getParentOf(Concept concept) {
		if (concept.isSub()) {
			Concept parent = concept;
			while (parent != null && parent.isSub())
				parent = getConceptContainerOf(parent);
			return parent;
		}
		return concept.getSignature().getParentConcept();
	}

}
