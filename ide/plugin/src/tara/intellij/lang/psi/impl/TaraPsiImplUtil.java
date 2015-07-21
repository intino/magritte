package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.language.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaraPsiImplUtil {

	private static final Logger LOG = Logger.getInstance(TaraPsiImplUtil.class.getName());

	private TaraPsiImplUtil() {
	}

	public static String getIdentifier(Identifier keyNode) {
		return keyNode != null ? keyNode.getText() : null;
	}

	public static String getIdentifier(Node element) {
		if (((TaraNode) element).getSignature().getIdentifier() != null) {
			ASTNode valueNode = ((TaraNode) element).getSignature().getIdentifier().getNode();
			return valueNode.getText();
		}
		return null;
	}

	public static Identifier getIdentifierNode(Node element) {
		return ((TaraNode) element).getSignature().getIdentifier() != null ? ((TaraNode) element).getSignature().getIdentifier() : null;
	}

	public static PsiElement setName(Signature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			Node node = TaraElementFactoryImpl.getInstance(element.getProject()).createNode(newName);
			ASTNode newKeyNode = ((TaraNode) element).getSignature().getIdentifier().getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static List<Node> getInnerNodesInBody(Body body) {
		return body == null ? Collections.EMPTY_LIST : (List<Node>) body.getNodeList();
	}

	public static List<Variable> getVariablesInBody(Body body) {
		return body != null ? (List<Variable>) body.getVariableList() : Collections.EMPTY_LIST;
	}

	public static List<Node> getComponentsOf(Node node) {
		if (node != null && ((TaraNode) node).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraNode) node).getBody());
			removeRoots(inner);
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	public static List<Node> getComponentsOf(Facet facetApply) {
		if (facetApply != null && ((TaraFacetApply) facetApply).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraFacetApply) facetApply).getBody());
			removeRoots(inner);
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	public static List<Node> getComponentsOf(FacetTarget facetTarget) {
		if (facetTarget != null && ((TaraFacetTarget) facetTarget).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraFacetTarget) facetTarget).getBody());
			removeRoots(inner);
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	private static void removeRoots(List<Node> inner) {
		List<Node> list = inner.stream().filter(Node::isMain).collect(Collectors.toList());
		inner.removeAll(list);

	}

	private static void addSubsOfInner(List<Node> inner) {
		List<Node> toAdd = new ArrayList<>();
		for (Node node : inner) toAdd.addAll(node.subs());
		inner.addAll(toAdd);
	}

	public static PsiElement getParentByType(PsiElement psiElement, Class<? extends PsiElement> aClass) {
		PsiElement element = psiElement.getParent();
		while (element != null && !PsiFile.class.isInstance(element) && !aClass.isInstance(element))
			element = element.getParent();
		return aClass.isInstance(element) ? element : null;
	}

	public static List<Node> getAllInnerNodesOf(Node node) {
		if (node != null && ((TaraNode) node).getBody() != null)
			return getInnerNodesInBody(((TaraNode) node).getBody());
		return Collections.EMPTY_LIST;
	}


	public static List<Node> getNodeReferencesOf(Node node) {
		return ((TaraNode) node).getBody() == null ? Collections.EMPTY_LIST : ((TaraNode) node).getBody().getNodeLinks();
	}


	private static void removeSubs(List<Node> children) {
		List<Node> list = children.stream().filter(Node::isSub).collect(Collectors.toList());
		children.removeAll(list);
	}

	@Nullable
	public static Node getContainerNodeOf(PsiElement element) {
		try {
			if (element == null) return null;
			PsiElement aElement = element.getOriginalElement();
			while ((aElement.getParent() != null)
				&& !(aElement.getParent() instanceof TaraModel)
				&& !(aElement.getParent() instanceof Node))
				aElement = aElement.getParent();
			return (aElement.getParent() instanceof Node) ? (Node) aElement.getParent() : null;
		} catch (NullPointerException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@NotNull
	public static List<FacetTarget> getFacetTargets(Node node) {
		if (((TaraNode) node).getBody() == null) return Collections.EMPTY_LIST;
		List<FacetTarget> targets = new ArrayList<>();
		getFacetTargets(((TaraNode) node).getBody(), targets);
		return targets;
	}

	private static void getFacetTargets(Body body, List<FacetTarget> targets) {
		for (TaraFacetTarget target : body.getFacetTargetList()) {
			targets.add(target);
			if (target.getBody() != null) getFacetTargets(target.getBody(), targets);
		}
	}

	@Nullable
	public static NodeContainer getContainerOf(PsiElement element) {
		PsiElement aElement = element;
		while (aElement.getParent() != null && isNotConceptOrFile(aElement) && isNotFacet(aElement))
			aElement = aElement.getParent();
		return (NodeContainer) aElement.getParent();
	}

	private static boolean isNotConceptOrFile(PsiElement aElement) {
		return !(aElement.getParent() instanceof TaraModel)
			&& !(aElement.getParent() instanceof Node);
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

	public static Node getParentOf(Node node) {
		if (node.isSub()) {
			Node parent = node;
			while (parent != null && parent.isSub())
				parent = getContainerNodeOf((PsiElement) parent);
			return parent;
		}
		return ((TaraNode) node).getSignature().getParentNode();
	}

}
