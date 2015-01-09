package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.codeinsight.JavaHelper;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = resolveInternal(identifier);
		return reference instanceof Concept ? ((Concept) reference).getIdentifierNode() : reference;
	}

	private static PsiElement resolveInternal(Identifier identifier) {
		if (identifier.getParent() instanceof IdentifierReference)
			return resolveConcept(identifier, getIdentifiersOfReference(identifier));
		if (identifier.getParent() instanceof HeaderReference)
			return ((Import) identifier.getParent().getParent()).isMetamodelImport() ?
				resolveMetamodelImport(identifier) : resolveHeaderReference(identifier);
		if (identifier.getParent() instanceof Signature)
			return identifier;
		return null;
	}

	private static PsiElement resolveMetamodelImport(Identifier identifier) {
		HeaderReference reference = (HeaderReference) identifier.getParent();
		List<? extends Identifier> identifiers = reference.getIdentifierList();
		if (identifiers.size() > 2) return null;
		Project project = identifier.getProject();
		if (identifier.equals(identifiers.get(0))) return getPsiFile(project.getProjectFile(), project);
		if (identifier.equals(identifiers.get(1))) {
			Module moduleByName = ModuleManager.getInstance(project).findModuleByName(identifier.getName());
			return moduleByName != null ? getPsiFile(moduleByName.getModuleFile(), project) : null;
		}
		return null;
	}

	private static PsiElement getPsiFile(VirtualFile file, Project project) {
		return PsiManager.getInstance(project).findFile(file);
	}

	@Nullable
	public static PsiElement resolve(IdentifierReference identifierReference) {
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		PsiElement reference = resolveConcept(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
		if (reference instanceof Concept) reference = ((Concept) reference).getIdentifierNode();
		return reference;
	}

	private static PsiElement resolveHeaderReference(Identifier identifier) {
		return resolveBoxPath(identifier);
	}

	private static List<Identifier> getIdentifiersOfReference(Identifier identifier) {
		List<Identifier> path = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		path = path.subList(0, path.indexOf(identifier) + 1);
		return path;
	}

	private static PsiElement resolveConcept(PsiElement identifier, List<Identifier> path) {
		List<Identifier> subPath = path.subList(0, path.indexOf(identifier) + 1);
		PsiElement element = tryToResolveInBox((TaraBoxFile) identifier.getContainingFile(), subPath);
		if (element != null) return element;
		element = tryToResolveOnImportedBoxes(subPath);
		if (element != null) return element;
		return tryToResolveAsQN(subPath);
	}

	private static PsiElement tryToResolveInBox(TaraBoxFile file, List<Identifier> path) {
		Concept[] possibleRoots = getPossibleRoots(file, path.get(0));
		if (possibleRoots.length == 0) return null;
		if (possibleRoots.length == 1 && path.size() == 1) return possibleRoots[0];
		for (Concept possibleRoot : possibleRoots) {
			Concept concept = resolvePathInConcept(path, possibleRoot);
			if (concept != null) return concept;
		}
		return null;
	}

	private static Concept[] getPossibleRoots(TaraBoxFile file, Identifier identifier) {
		Set<Concept> set = new HashSet<>();
		addConceptsInContext(identifier, set);
		addRootConcepts(file, identifier, set);
		addAggregated(file, identifier, set, toArrayList(set));
		return set.toArray(new Concept[set.size()]);
	}

	private static PsiElement tryToResolveAsQN(List<Identifier> path) {
		TaraBoxFileImpl resolve;
		resolve = resolveBoxPath(path.get(0));
		if (resolve == null || path.size() == 0) return null;
		List<Identifier> qn = path.subList(1, path.size());
		if (qn.isEmpty()) return resolve;
		return tryToResolveInBox(resolve, qn);
	}

	private static ArrayList<Concept> toArrayList(Set<Concept> set) {
		ArrayList<Concept> visited = new ArrayList<>();
		visited.addAll(set);
		return visited;
	}

	private static void addRootConcepts(TaraBoxFile file, Identifier identifier, Set<Concept> set) {
		Collection<Concept> concepts = file.getConcepts();
		for (Concept concept : concepts)
			if (namesAreEqual(identifier, concept))
				set.add(concept);
	}

	private static void addConceptsInContext(Identifier identifier, Set<Concept> set) {
		Concept container = getConceptContainerOf(identifier);
		if (container != null && !isExtendsReference((IdentifierReference) identifier.getParent()) &&
			namesAreEqual(identifier, container))
			set.add(container);
		while (container != null) {
			for (Concept sibling : container.getConceptSiblings())
				if (namesAreEqual(identifier, sibling) && !sibling.equals(getConceptContainerOf(identifier)))
					set.add(sibling);
			container = getConceptContainerOf(container);
		}
	}

	private static boolean isExtendsReference(IdentifierReference reference) {
		return (reference.getParent() instanceof Signature);
	}

	private static void addAggregated(TaraBoxFile file, Identifier identifier, Set<Concept> set, ArrayList<Concept> visited) {
		List<Concept> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(file);
		for (Concept concept : allConceptsOfFile)
			if (namesAreEqual(identifier, concept) && isAggregated(file, concept, visited))
				set.add(concept);
	}

	private static boolean namesAreEqual(Identifier identifier, Concept concept) {
		return identifier.getText().equals(concept.getName());
	}

	private static boolean isAggregated(TaraBoxFile file, Concept concept, ArrayList<Concept> visited) {
		if (visited.contains(concept)) return false;
		visited.add(concept);
		if (concept.isAnnotatedAsAggregated() || concept.isMetaAggregated()) return true;
		IdentifierReference parentReference = concept.getSignature().getParentReference();
		if (parentReference == null) return false;
		Concept[] roots = getRootConcepts(file, parentReference, visited, new HashSet<Concept>());
		if (roots.length == 0) return false;
		for (Concept possibleRoot : roots) {
			Concept aggregated = resolvePathInConcept((List<Identifier>) parentReference.getIdentifierList(), possibleRoot);
			if (aggregated != null) return aggregated.isAnnotatedAsAggregated();
		}
		return false;
	}

	private static Concept[] getRootConcepts(TaraBoxFile file, IdentifierReference parentReference, ArrayList<Concept> visited, Set<Concept> roots) {
		Identifier identifier = getIdentifier(parentReference);
		addConceptsInContext(identifier, roots);
		addRootConcepts(file, identifier, roots);
		visited.addAll(roots);
		addAggregated((TaraBoxFile) identifier.getContainingFile(), identifier, roots, visited);
		return roots.toArray(new Concept[roots.size()]);
	}

	private static Identifier getIdentifier(IdentifierReference reference) {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		return identifierList.get(identifierList.size() - 1);
	}

	private static Concept resolvePathInConcept(List<Identifier> path, Concept concept) {
		Concept reference = null;
		for (Identifier identifier : path) {
			reference = (reference == null) ? namesAreEqual(identifier, concept) ? concept : null :
				TaraUtil.findChildOf(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static PsiElement resolvePackageReference(Project project, String path) {
		return (PsiElement) JavaHelper.getJavaHelper(project).findPackage(path);
	}

	public static PsiElement resolveJavaClassReference(Project project, String path) {
		return JavaHelper.getJavaHelper(project).findClass(path);
	}

	public static TaraBoxFileImpl resolveBoxPath(Identifier identifier) {
		TaraBoxFile containingFile = (TaraBoxFile) identifier.getContainingFile();
		if (containingFile == null || containingFile.getVirtualFile() == null) return null;
		Module moduleOfDocument = ModuleProvider.getModuleOfFile(containingFile);
		List<TaraBoxFileImpl> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(moduleOfDocument);
		for (TaraBoxFileImpl taraBoxFile : taraFilesOfModule)
			if (taraBoxFile.getPresentableName().equals(identifier.getText())) return taraBoxFile;
		return null;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> path) {
		TaraBoxFile context = (TaraBoxFile) path.get(0).getContainingFile();
		Collection<Import> imports = context.getImports();
		return searchInImport(path, imports);
	}

	private static Concept searchInImport(List<Identifier> path, Collection<Import> imports) {
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraBoxFile.class.isInstance(resolve.getContainingFile())) continue;
			TaraBoxFile containingFile = (TaraBoxFile) resolve.getContainingFile();
			Concept concept = resolvePathInBox(containingFile, path);
			if (concept != null) return concept;
		}
		return null;
	}

	public static Concept resolvePathInBox(TaraBoxFile containingFile, List<Identifier> path) {
		Set<Concept> concepts = new HashSet<>();
		concepts.addAll(containingFile.getConcepts());
		addAggregated(containingFile, path.get(0), concepts, toArrayList(concepts));
		for (Concept concept : concepts) {
			Concept solution = resolvePathInConcept(path, concept);
			if (solution != null) return solution;
		}
		return null;
	}

	private static PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}
}
