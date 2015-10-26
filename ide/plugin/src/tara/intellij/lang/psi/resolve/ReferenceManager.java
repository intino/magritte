package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.JavaHelper;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = internalResolve(identifier);
		return reference instanceof Node && !(reference instanceof TaraModel) ? ((TaraNode) reference).getSignature().getIdentifier() : reference;
	}

	@Nullable
	public static Node resolveToNode(IdentifierReference identifierReference) {
		if (identifierReference == null) return null;
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		return (Node) resolveNode(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
	}

	@Nullable
	public static PsiElement resolve(IdentifierReference identifierReference) {
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		PsiElement reference = resolveNode(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
		if (reference instanceof Node) reference = ((TaraNode) reference).getSignature().getIdentifier();
		return reference;
	}

	@Nullable
	public static PsiElement resolveJavaClassReference(Project project, String path) {
		if (project == null || path == null || path.isEmpty()) return null;
		return JavaHelper.getJavaHelper(project).findClass(path);
	}

	private static PsiElement internalResolve(Identifier identifier) {
		if (identifier.getParent() instanceof IdentifierReference)
			return resolveNode(identifier, getIdentifiersOfReference(identifier));
		if (identifier.getParent() instanceof HeaderReference)
			return identifier.getParent().getParent() instanceof TaraDslDeclaration ?
				identifier : resolveHeaderReference(identifier);
		if (identifier.getParent() instanceof Signature) return identifier;
		return null;
	}

	private static PsiElement resolveHeaderReference(Identifier identifier) {
		return resolveBoxPath(identifier);
	}

	private static List<Identifier> getIdentifiersOfReference(Identifier identifier) {
		List<Identifier> path = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		path = path.subList(0, path.indexOf(identifier) + 1);
		return path;
	}

	private static PsiElement resolveNode(PsiElement identifier, List<Identifier> path) {
		List<Identifier> subPath = path.subList(0, path.indexOf(identifier) + 1);
		PsiElement element = (PsiElement) tryToResolveInBox((TaraModel) identifier.getContainingFile(), subPath);
		if (element != null) return element;
		element = tryToResolveOnImportedBoxes(subPath);
		if (element != null) return element;
		return tryToResolveAsQN(subPath);
	}

	private static Node tryToResolveInBox(TaraModel file, List<Identifier> path) {
		Node[] roots = getPossibleRoots(file, path.get(0));
		if (roots.length == 0) return null;
		if (roots.length == 1 && path.size() == 1) return roots[0];
		for (Node possibleRoot : roots) {
			if (possibleRoot.isEnclosed()) continue;
			Node node = resolvePathInNode(path, possibleRoot);
			if (node != null) return node;
		}
		return null;
	}

	protected static Node[] getPossibleRoots(TaraModel file, Identifier identifier) {
		Set<Node> set = new LinkedHashSet<>();
		if (file.equals(identifier.getContainingFile())) addNodesInContext(identifier, set);
		if (isVariableReference(identifier)) addNodeSiblings(identifier, set);
		addRootNodes(file, identifier, set);
		return set.toArray(new Node[set.size()]);
	}

	private static boolean isVariableReference(Identifier identifier) {
		return TaraPsiImplUtil.getContainerByType(identifier, Variable.class) != null;
	}

	private static void addNodeSiblings(Identifier identifier, Set<Node> set) {
		final NodeContainer container = TaraPsiImplUtil.getContainerOf(identifier);
		if (container == null) return;
		set.addAll(container.components().stream().filter(node -> areNamesake(identifier, node)).collect(Collectors.toList()));
	}

	private static PsiElement tryToResolveAsQN(List<Identifier> path) {
		TaraModel model = resolveBoxPath(path.get(0));
		if (model == null || path.isEmpty()) return null;
		List<Identifier> qn = path.subList(1, path.size());
		if (qn.isEmpty()) return null;
		return (PsiElement) tryToResolveInBox(model, qn);
	}

	private static void addRootNodes(TaraModel model, Identifier identifier, Set<Node> set) {
		List<Node> nodes = model.components();
		set.addAll(nodes.stream().filter(node -> areNamesake(identifier, node)).collect(Collectors.toList()));
	}

	private static void addNodesInContext(Identifier identifier, Set<Node> set) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(identifier);
		if (isInFacetTarget(identifier)) addFacetTargetNodes(set, identifier);
		if (container != null && !isExtendsOrParameterReference(identifier) && areNamesake(identifier, container))
			set.add(container);
		if (container != null) {
			collectContextNodes(identifier, set, container);
			if (isExtendsOrParameterReference(identifier) && container.container() instanceof Node) {
				final Node parent = ((Node) container.container()).parent();
				if (parent != null) collectParentComponents(identifier, set, parent);
			}
		}
	}

	private static void collectParentComponents(Identifier identifier, Set<Node> set, Node parent) {
		final Node containerNode = TaraPsiImplUtil.getContainerNodeOf(identifier);
		set.addAll(parent.components().stream().
			filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(containerNode)).
			collect(Collectors.toList()));
	}

	private static boolean isInFacetTarget(Identifier identifier) {
		final PsiElement contextOf = (PsiElement) TaraPsiImplUtil.getContainerOf(identifier);
		return contextOf instanceof FacetTarget && ((TaraFacetTarget) contextOf).getIdentifierReference() != null &&
			!((TaraFacetTarget) contextOf).getIdentifierReference().getIdentifierList().contains(identifier);
	}

	private static void addFacetTargetNodes(Set<Node> set, Identifier identifier) {
		FacetTarget facetTarget = (FacetTarget) TaraPsiImplUtil.getContainerOf(identifier);
		if (facetTarget == null) return;
		for (Node node : facetTarget.components()) {
			if (node.name() == null) continue;
			set.add(node);
			if (node.isAbstract()) set.addAll(node.subs());
		}
	}

	private static void collectContextNodes(Identifier identifier, Set<Node> set, NodeContainer node) {
		NodeContainer container = node;
		final Node containerNode = TaraPsiImplUtil.getContainerNodeOf(identifier);
		while (container != null) {
			set.addAll(collectCandidates(container).stream().
				filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(containerNode)).
				collect(Collectors.toList()));
			container = container.container();
		}
	}

	private static List<Node> collectCandidates(NodeContainer container) {
		List<Node> nodes = new ArrayList<>();
		List<? extends Node> siblings = container.siblings();
		nodes.addAll(siblings);
		for (Node node : siblings) nodes.addAll(node.subs());
		return nodes;
	}

	private static boolean isExtendsOrParameterReference(Identifier reference) {
		PsiElement parent = reference.getParent();
		while (parent != null && !(parent instanceof Signature) && !(parent instanceof Node))
			parent = parent.getParent();
		return parent instanceof Signature;
	}

	private static boolean areNamesake(Identifier identifier, Node node) {
		return identifier.getText().equals(node.name());
	}

	private static Node resolvePathInNode(List<Identifier> path, Node node) {
		Node reference = null;
		for (Identifier identifier : path) {
			reference = reference == null ? areNamesake(identifier, node) ? node : null :
				findComponent(reference, identifier);
			if (reference == null || (reference.isEnclosed() && !isLast(identifier, path))) return null;
		}
		return reference;
	}

	private static Node findComponent(Node node, Identifier identifier) {
		final Node component = TaraUtil.findInner(node, identifier.getText());
		if (component != null) return component;
		for (Facet facet : node.facets()) {
			final Node inner = TaraUtil.findInner(facet, identifier.getText());
			if (inner != null) return inner;
		}
		return null;
	}

	private static boolean isLast(Identifier identifier, List<Identifier> path) {
		return path.indexOf(identifier) == path.size() - 1;
	}

	private static TaraModel resolveBoxPath(Identifier identifier) {
		TaraModel containingFile = (TaraModel) identifier.getContainingFile().getOriginalFile();
		if (containingFile.getVirtualFile() == null) return null;
		Module moduleOfDocument = ModuleProvider.getModuleOf(containingFile);
		for (TaraModel taraBoxFile : TaraUtil.getTaraFilesOfModule(moduleOfDocument))
			if (taraBoxFile.getPresentableName().equals(identifier.getText())) return taraBoxFile;
		return null;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> path) {
		TaraModel context = (TaraModel) path.get(0).getContainingFile();
		Collection<Import> imports = context.getImports();
		return (PsiElement) searchInImport(path, imports);
	}

	private static Node searchInImport(List<Identifier> path, Collection<Import> imports) {
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve.getContainingFile())) continue;
			Node node = tryToResolveInBox((TaraModel) resolve.getContainingFile(), path);
			if (node != null) return node;
		}
		return null;
	}

	private static Node resolvePathInBox(TaraModel containingFile, List<Identifier> path) {
		Set<Node> nodes = new HashSet<>();
		nodes.addAll(containingFile.components());
//		addRoots(containingFile, path.get(0), nodes, nodes);
		for (Node node : nodes) {
			Node solution = resolvePathInNode(path, node);
			if (solution != null) return solution;
		}
		return null;
	}

	private static PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}

	public static PsiElement resolveRule(Rule rule) {
		if (rule == null) return null;
		return isNative(rule) ? resolveNativeClass(rule, rule.getProject()) : resolveRuleToClass(rule);
	}

	private static boolean isNative(Rule rule) {
		Variable variable = TaraPsiImplUtil.getContainerByType(rule, Variable.class);
		return variable != null && Primitive.NATIVE.equals(variable.type());
	}

	private static PsiElement resolveRuleToClass(Rule rule) {
		final Module moduleOf = ModuleProvider.getModuleOf(rule);
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(moduleOf);
		if (taraFacetByModule == null) return null;
		final String generatedDslName = taraFacetByModule.getConfiguration().getGeneratedDslName();
		return resolveJavaClassReference(rule.getProject(), generatedDslName.toLowerCase() + ".rules." + rule.getText());
	}

	private static PsiElement resolveNativeClass(Rule rule, Project project) {
		if (rule == null) return null;
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(rule));
		if (taraFacetByModule == null) return null;
		String aPackage = taraFacetByModule.getConfiguration().getGeneratedDslName().toLowerCase() + '.' + "natives";
		return resolveJavaClassReference(project, aPackage.toLowerCase() + '.' + capitalize(rule.getText()));
	}

	private static final String DOC_SEPARATOR = "#";

	public static PsiElement resolveJavaNativeImplementation(PsiClass psiClass) {
		String data = findData(psiClass.getDocComment().getChildren());
		if (data.isEmpty()) return null;
		String[] nativeInfo = data.split(DOC_SEPARATOR);
		File destinyFile = new File(nativeInfo[1]);
		final List<TaraModel> filesOfModule = TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(psiClass));
		for (TaraModel taraModel : filesOfModule)
			if (FileUtil.compareFiles(destinyFile, new File(taraModel.getVirtualFile().getPath())) == 0)
				return searchNodeIn(taraModel, nativeInfo);
		return null;
	}

	public static PsiElement resolveTaraNativeImplementationToJava(Valued valued) {
		String generatedDSL = TaraUtil.getGeneratedDSL(valued);
		PsiElement psiElement = resolveJavaClassReference(valued.getProject(), generatedDSL.toLowerCase() + ".natives." + firstUpperCase(generatedDSL) + "Natives");
		if (psiElement == null) return null;
		PsiClass psiClass = (PsiClass) psiElement;
		for (PsiClass aClass : psiClass.getInnerClasses())
			if (valued.equals(TaraPsiImplUtil.getContainerByType(resolveJavaNativeImplementation(aClass), Valued.class)))
				return aClass;
		return null;
	}

	private static String firstUpperCase(String value) {
		return value.isEmpty() ? "" : value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	private static String findData(PsiElement[] elements) {
		for (PsiElement element : elements)
			if ("DOC_COMMENT_DATA".equals(element.getNode().getElementType().toString())) return element.getText();
		return "";
	}

	private static PsiElement searchNodeIn(TaraModel taraModel, String[] nativeInfo) {
		final Document document = PsiDocumentManager.getInstance(taraModel.getProject()).getDocument(taraModel);
		if (document == null) return null;
		final int start = Integer.parseInt(nativeInfo[2]) - 1;
		if (document.getTextLength() < start) return null;
		return taraModel.findElementAt(document.getLineStartOffset(start) + Integer.parseInt(nativeInfo[3]));
	}

	private static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}