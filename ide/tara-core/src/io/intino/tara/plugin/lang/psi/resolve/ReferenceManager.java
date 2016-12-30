package io.intino.tara.plugin.lang.psi.resolve;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import io.intino.tara.plugin.codeinsight.JavaHelper;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.Format;
import io.intino.tara.plugin.lang.psi.*;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.project.module.ModuleProvider;
import io.intino.tara.lang.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.plugin.project.TaraModuleType;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@NotNull
	public static List<PsiElement> resolve(Identifier identifier) {
		PsiElement reference = internalResolve(identifier);
		return Collections.singletonList(reference instanceof Node && !(reference instanceof TaraModel) ? ((TaraNode) reference).getSignature().getIdentifier() : reference);
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
		return JavaHelper.getJavaHelper(project).findClass(path.trim());
	}

	public static PsiElement resolveTable(PsiElement tableName) {
		final VirtualFile dataRoot = TaraUtil.getResourcesRoot(tableName);
		if (dataRoot == null) return null;
		final VirtualFile tableFile = dataRoot.findChild(tableName.getText() + ".table");
		if (tableFile == null) return null;
		return PsiManager.getInstance(tableName.getProject()).findFile(tableFile);
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

	private static PsiElement resolveNode(Identifier identifier, List<Identifier> path) {
		List<Identifier> subPath = path.subList(0, path.indexOf(identifier) + 1);
		PsiElement element = (PsiElement) tryToResolveInBox((TaraModel) identifier.getContainingFile(), subPath);
		if (element != null) return element;
		element = tryToResolveOnImportedBoxes(subPath);
		if (element != null) return element;
		return tryToResolveAsQN(subPath);
	}

	private static NodeContainer tryToResolveInBox(TaraModel file, List<Identifier> path) {
		Node[] roots = getPossibleRoots(file, path.get(0));
		if (roots.length == 0) return null;
		if (roots.length == 1 && path.size() == 1) return roots[0];
		for (Node possibleRoot : roots) {
			if (possibleRoot.is(Tag.Enclosed)) continue;
			NodeContainer node = resolvePathInNode(path, possibleRoot);
			if (node != null) return node;
		}
		return null;
	}

	private static Node[] getPossibleRoots(TaraModel file, Identifier identifier) {
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
		if (container != null && !isExtendsOrParameterReference(identifier) && areNamesake(identifier, container))
			set.add(container);
		if (container != null) {
			collectContextNodes(identifier, set, container);
			if (isExtendsOrParameterReference(identifier) && container.container() != null) {
				final Node parent = container.container().parent();
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

	private static void collectContextNodes(Identifier identifier, Set<Node> set, Node node) {
		Node container = node;
		final Node containerNode = TaraPsiImplUtil.getContainerNodeOf(identifier);
		while (container != null) {
			set.addAll(collectCandidates(container).stream().
				filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(containerNode)).
				collect(Collectors.toList()));
			container = container.container();
		}
	}

	private static List<Node> collectCandidates(Node container) {
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

	private static NodeContainer resolvePathInNode(List<Identifier> path, Node node) {
		Node reference = null;
		for (Identifier identifier : path) {
			reference = reference == null ? areNamesake(identifier, node) ? node : null :
				findIn(reference, identifier);
			if (reference == null || reference.is(Tag.Enclosed) && !isLast(identifier, path))
				return null;
		}
		return reference;
	}

	private static Node findIn(Node node, Identifier identifier) {
		return identifier.isReferringTarget() ? findTarget(node, identifier) : TaraUtil.findComponent(node, identifier.getText());
	}

	private static Node findTarget(Node container, Identifier identifier) {
		return container != null && container.facetTarget() != null && container.facetTarget().target().equals(identifier.getName()) ? container : null;
	}

	private static boolean isLast(Identifier identifier, List<Identifier> path) {
		return path.indexOf(identifier) == path.size() - 1;
	}

	private static TaraModel resolveBoxPath(Identifier identifier) {
		TaraModel containingFile = (TaraModel) identifier.getContainingFile().getOriginalFile();
		if (containingFile.getVirtualFile() == null) return null;
		Module moduleOfDocument = ModuleProvider.moduleOf(containingFile);
		for (TaraModel taraBoxFile : TaraUtil.getTaraFilesOfModule(moduleOfDocument))
			if (taraBoxFile.getPresentableName().equals(identifier.getText())) return taraBoxFile;
		return null;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> path) {
		TaraModel context = (TaraModel) path.get(0).getContainingFile();
		Collection<Import> imports = context.getImports();
		return (PsiElement) searchInImport(path, imports);
	}

	private static NodeContainer searchInImport(List<Identifier> path, Collection<Import> imports) {
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve.getContainingFile())) continue;
			NodeContainer node = tryToResolveInBox((TaraModel) resolve.getContainingFile(), path);
			if (node != null) return node;
		}
		return null;
	}

	private static PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return resolve(importIdentifiers.get(importIdentifiers.size() - 1)).get(0);
	}

	public static PsiElement resolveRule(io.intino.tara.plugin.lang.psi.Rule rule) {
		if (rule == null) return null;
		return isNative(rule) ? resolveNativeClass(rule, rule.getProject()) : resolveRuleToClass(rule);
	}

	private static boolean isNative(io.intino.tara.plugin.lang.psi.Rule rule) {
		Variable variable = TaraPsiImplUtil.getContainerByType(rule, Variable.class);
		return variable != null && Primitive.FUNCTION.equals(variable.type());
	}

	private static PsiElement resolveRuleToClass(io.intino.tara.plugin.lang.psi.Rule rule) {
		if (!TaraModuleType.isTara(ModuleProvider.moduleOf(rule))) return null;
		final String workingPackage = TaraUtil.workingPackage(rule);
		if (workingPackage== null) return null;
		return resolveJavaClassReference(rule.getProject(), workingPackage.toLowerCase() + ".rules." + rule.getText());
	}

	private static PsiElement resolveNativeClass(io.intino.tara.plugin.lang.psi.Rule rule, Project project) {
		if (rule == null) return null;
		String aPackage = TaraUtil.workingPackage(rule) + '.' + "functions";
		return resolveJavaClassReference(project, aPackage.toLowerCase() + '.' + capitalize(rule.getText()));
	}

	private static final String DOC_SEPARATOR = "#";

	public static PsiElement resolveJavaNativeImplementation(PsiClass psiClass) {
		if (psiClass.isInterface() || psiClass.getDocComment() == null) return null;
		String data = findData(psiClass.getDocComment().getChildren());
		if (data.isEmpty()) return null;
		String[] nativeInfo = data.split(DOC_SEPARATOR);
		if (nativeInfo.length < 2) return null;
		File destinyFile = new File(nativeInfo[1]);
		final List<TaraModel> filesOfModule = TaraUtil.getTaraFilesOfModule(ModuleProvider.moduleOf(psiClass));
		for (TaraModel taraModel : filesOfModule)
			if (FileUtil.compareFiles(destinyFile, new File(taraModel.getVirtualFile().getPath())) == 0)
				return searchNodeIn(taraModel, nativeInfo);
		return null;
	}

	public static PsiElement resolveTaraNativeImplementationToJava(io.intino.tara.plugin.lang.psi.Valued valued) {
		String workingPackage = TaraUtil.workingPackage(valued);
		if (ModuleProvider.moduleOf(valued) == null) return null;
		if (workingPackage == null || workingPackage.isEmpty())workingPackage = ModuleProvider.moduleOf(valued).getName();
		for (PsiClass aClass : getCandidates(valued, workingPackage.toLowerCase()))
			if (valued.equals(TaraPsiImplUtil.getContainerByType(resolveJavaNativeImplementation(aClass), io.intino.tara.plugin.lang.psi.Valued.class)))
				return aClass;
		return null;
	}

	@NotNull
	private static List<PsiClass> getCandidates(io.intino.tara.plugin.lang.psi.Valued valued, String generatedDSL) {
		final PsiPackage aPackage = (PsiPackage) JavaHelper.getJavaHelper(valued.getProject()).findPackage(generatedDSL.toLowerCase() + ".natives");
		if (aPackage == null || valued.name() == null) return Collections.emptyList();
		return getAllClasses(aPackage).stream().filter(c -> c.getName() != null && c.getName().startsWith(Format.firstUpperCase().format(valued.name()) + "_")).collect(Collectors.toList());
	}

	private static List<PsiClass> getAllClasses(PsiPackage aPackage) {
		List<PsiClass> psiClasses = new ArrayList<>(Arrays.asList(aPackage.getClasses()));
		Arrays.asList(aPackage.getSubPackages()).forEach(p -> psiClasses.addAll(ReferenceManager.getAllClasses(p)));
		return psiClasses;
	}

	private static String findData(PsiElement[] elements) {
		for (PsiElement element : elements) {
			final String comment = element.getNode().getElementType().toString();
			if ("DOC_COMMENT_DATA".equals(comment) || "GDOC_COMMENT_DATA".equals(comment)) return element.getText();
		}
		return "";
	}

	private static PsiElement searchNodeIn(TaraModel taraModel, String[] nativeInfo) {
		final Document document = PsiDocumentManager.getInstance(taraModel.getProject()).getDocument(taraModel);
		if (document == null) return null;
		final int start = Integer.parseInt(nativeInfo[2]) - 1;
		if (document.getLineCount() <= start) return null;
		final PsiElement elementAt = taraModel.findElementAt(document.getLineStartOffset(start) + Integer.parseInt(nativeInfo[3]));
		return elementAt != null && (elementAt.getNode().getElementType().equals(TaraTypes.NEWLINE) ||
			elementAt.getNode().getElementType().equals(TaraTypes.NEW_LINE_INDENT)) ? elementAt.getNextSibling() : elementAt;
	}

	private static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}