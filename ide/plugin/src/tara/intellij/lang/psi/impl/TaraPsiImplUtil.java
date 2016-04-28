package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.lang.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

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

	public static List<Node> getBodyComponents(Body body) {
		if (body == null) return emptyList();
		List<Node> nodes = new ArrayList<>();
		nodes.addAll(body.getNodeList());
		nodes.addAll(body.getNodeLinks());
		return nodes;
	}

	public static List<Variable> getVariablesInBody(Body body) {
		return body != null ? (List<Variable>) body.getVariableList() : emptyList();
	}

	public static List<Node> getComponentsOf(Node node) {
		List<Node> components = new ArrayList<>();
		if (node != null) {
			bodyComponents((TaraNode) node, components);
			final Node parent = node.parent();
			if (parent != null) components.addAll(parent.components());
			return components;
		}
		return emptyList();
	}

	private static void bodyComponents(TaraNode node, List<Node> components) {
		if (node.getBody() != null) {
			components.addAll(getBodyComponents(node.getBody()));
			removeSubs(components);
			addSubsOfComponent(components);
		}
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

	private static int countTabs(String text) {
		int i = text.length() - text.replace("\t", "").length();
		return i + (text.length() - text.replace(" ", "").length()) / 4;
	}


	public static <T> T getContainerByType(PsiElement element, Class<T> tClass) {
		PsiElement parent = element;
		while (parent != null)
			if (tClass.isInstance(parent)) return (T) parent;
			else parent = parent.getParent();
		return null;
	}

	public static <T> T contextOf(PsiElement element, Class<T> tClass) {
		PsiElement context = element;
		while (context != null)
			if (tClass.isInstance(context)) return (T) context;
			else context = context.getContext();
		return null;
	}

	private static void addSubsOfComponent(List<Node> inner) {
		List<Node> toAdd = new ArrayList<>();
		for (Node node : inner) toAdd.addAll(node.subs());
		inner.addAll(toAdd);
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

	@Nullable
	public static NodeContainer getContainerOf(PsiElement element) {
		PsiElement aElement = element;
		while (aElement != null && aElement.getParent() != null && isNotConceptOrFile(aElement) && !isFacet(aElement))
			aElement = aElement.getParent();
		return (NodeContainer) (aElement != null ? aElement.getParent() : null);
	}

	private static boolean isNotConceptOrFile(PsiElement aElement) {
		return !(aElement.getParent() instanceof TaraModel) && !(aElement.getParent() instanceof Node);
	}

	private static boolean isFacet(PsiElement aElement) {
		return aElement.getParent() instanceof TaraFacetApply;
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
		return ((TaraNode) node).getSignature().parent();
	}


	public static boolean isAnnotatedAsComponent(Node node) {
		for (Tag flag : node.flags())
			if (flag.equals(Tag.Component)) return true;
		return false;
	}

	public static PsiElement setType(Signature signature, String type) {
		TaraMetaIdentifier oldType = signature.getMetaIdentifier();
		if (oldType != null)
			return oldType.replace(TaraElementFactoryImpl.getInstance(signature.getProject()).createMetaIdentifier(type).copy());
		return null;
	}
}
