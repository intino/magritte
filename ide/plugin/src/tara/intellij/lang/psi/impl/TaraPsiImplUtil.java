package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.lang.model.*;

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

	public static PsiElement setName(Signature signature, String newName) {
		TaraIdentifier oldId = signature.getIdentifier();
		if (oldId != null)
			signature.getNode().replaceChild(oldId.getNode(), TaraElementFactoryImpl.getInstance(signature.getProject()).createNameIdentifier(newName).getNode());
		return signature;
	}

	public static List<Node> getInnerNodesInBody(Body body) {
		if (body == null) return Collections.EMPTY_LIST;
		List<Node> nodes = new ArrayList<>();
		nodes.addAll(body.getNodeList());
		nodes.addAll(body.getNodeLinks());
		return nodes;
	}

	public static List<Variable> getVariablesInBody(Body body) {
		return body != null ? (List<Variable>) body.getVariableList() : Collections.EMPTY_LIST;
	}

	public static List<Node> getComponentsOf(Node node) {
		if (node != null && ((TaraNode) node).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraNode) node).getBody());
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	public static List<Node> getComponentsOf(Facet facetApply) {
		if (facetApply != null && ((TaraFacetApply) facetApply).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraFacetApply) facetApply).getBody());
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	public static int getIndentation(PsiElement element) {
		PsiElement container = (PsiElement) TaraPsiImplUtil.getContainerOf(element);
		if (container == null) return 0;
		if (is(container.getPrevSibling(), TaraTypes.NEWLINE) || is(container.getPrevSibling(), TaraTypes.NEW_LINE_INDENT))
			return 1 + countTabs(container.getPrevSibling().getText());
		return 0;
	}

	private static boolean is(PsiElement element, IElementType type) {
		return element != null && element.getNode().getElementType().equals(type);
	}

	public static int countTabs(String text) {
		int i = text.length() - text.replace("\t", "").length();
		return i + (text.length() - text.replace(" ", "").length()) / 4;
	}


	public static List<Node> getComponentsOf(FacetTarget facetTarget) {
		if (facetTarget != null && ((TaraFacetTarget) facetTarget).getBody() != null) {
			List<Node> inner = getInnerNodesInBody(((TaraFacetTarget) facetTarget).getBody());
			removeSubs(inner);
			addSubsOfInner(inner);
			return inner;
		}
		return Collections.EMPTY_LIST;
	}

	public static <T> T getContainerByType(PsiElement element, Class<T> tClass) {
		PsiElement parent = element;
		while (parent != null)
			if (tClass.isInstance(parent)) return (T) parent;
			else parent = parent.getParent();
		return null;
	}


	private static void addSubsOfInner(List<Node> inner) {
		List<Node> toAdd = new ArrayList<>();
		for (Node node : inner) toAdd.addAll(node.subs());
		inner.addAll(toAdd);
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
			return (aElement.getParent() != null) ? (Node) aElement.getParent() : null;
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
		if (node.isSub()) return getContainerNodeOf((PsiElement) node);
		return ((TaraNode) node).getSignature().getParentNode();
	}


	public static boolean isAnnotatedAsComponent(Node node) {
		for (Tag flag : node.flags())
			if (flag.equals(Tag.Component)) return true;
		return false;
	}

	public static PsiElement setType(Signature signature, String type) {
		TaraMetaIdentifier oldType = signature.getMetaIdentifier();
		if (oldType != null)
			signature.getNode().replaceChild(oldType.getNode(), TaraElementFactoryImpl.getInstance(signature.getProject()).createMetaIdentifier(type).getNode());
		return signature;
	}
}
