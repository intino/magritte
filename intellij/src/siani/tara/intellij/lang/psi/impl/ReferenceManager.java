package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.codeinsight.JavaHelper;
import siani.tara.intellij.lang.psi.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContextOf;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier, boolean external) {
		PsiElement reference = null;
		if (external) reference = resolveExternalReference(identifier);
		else if (identifier.getParent() instanceof IdentifierReference)
			reference = resolveConcept(identifier, getIdentifiersOfReference(identifier));
		else if (identifier.getParent() instanceof HeaderReference)
			reference = resolveHeaderReference(identifier, getPathFromHeader(identifier));
		else if (identifier.getParent() instanceof Signature) return identifier;
		if (reference instanceof Concept) reference = ((Concept) reference).getIdentifierNode();
		return reference;
	}

	@Nullable
	public static PsiElement resolve(IdentifierReference identifierReference) {
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		PsiElement reference = resolveConcept(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
		if (reference instanceof Concept) reference = ((Concept) reference).getIdentifierNode();
		return reference;
	}

	private static PsiElement resolveHeaderReference(Identifier identifier, List<Identifier> path) {
		VirtualFile file = resolvePath(path);
		if (file != null) {
			if (file.isDirectory())
				return resolvePackageReference(identifier.getProject(), join(path.subList(2, path.size()), '.'));
			if ("iml".equals(file.getExtension()) || "xml".equals(file.getExtension()))
				return PsiManager.getInstance(path.get(0).getProject()).findFile(file);
		} else return resolveInnerConcept(path);
		return PsiManager.getInstance(identifier.getProject()).findFile(file);
	}

	private static PsiElement resolveExternalReference(Identifier identifier) {
		TaraBox box = ((TaraBoxFile) identifier.getContainingFile()).getBoxReference();
		String path = box.getHeaderReference().getText() + "." + TaraUtil.composeConceptQN(identifier);
		return resolveJavaClassReference(identifier.getProject(), path);
	}

	private static List<Identifier> getPathFromHeader(Identifier identifier) {
		List<Identifier> path = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return path.subList(0, path.indexOf(identifier) + 1);
	}

	private static List<Identifier> getIdentifiersOfReference(Identifier identifier) {
		List<Identifier> path = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		path = path.subList(0, path.indexOf(identifier) + 1);
		return path;
	}

	private static PsiElement resolveConcept(PsiElement identifier, List<Identifier> path) {
		List<Identifier> subPath = path.subList(0, path.indexOf(identifier) + 1);
		PsiElement element = tryToResolveInBox(subPath);
		if (element != null) return element;
		return tryToResolveOnImportedBoxes(subPath);
	}

	private static PsiElement tryToResolveInBox(List<Identifier> path) {
		Concept[] possibleRoots = getPossibleRoots(path.get(0));
		if (possibleRoots.length == 0) return null;
		if (path.size() == 1) return possibleRoots[0];
		for (Concept possibleRoot : possibleRoots) {
			Concept concept = resolvePathInConcept(path, possibleRoot);
			if (concept != null) return concept;
		}
		return null;
	}

	private static Concept[] getPossibleRoots(Identifier identifier) {
		Set<Concept> list = new HashSet<>();
		Concept parent = getConceptContextOf(identifier);
		while (parent != null) {
			for (Concept sibling : parent.getConceptSiblings())
				if (sibling.getName() != null && sibling.getName().equals(identifier.getText()) && !sibling.equals(parent))
					list.add(sibling);
			parent = getConceptContextOf(parent);
		}
		Collection<Concept> concepts = ((TaraBoxFile) identifier.getContainingFile()).getConcepts();
		for (Concept concept : concepts)
			if (identifier.getText().equals(concept.getName()))
				list.add(concept);
		return list.toArray(new Concept[list.size()]);
	}

	private static Concept resolvePathInConcept(List<Identifier> path, Concept concept) {
		Concept reference = null;
		for (Identifier identifier : path) {
			reference = (reference == null) ? identifier.getText().equals(concept.getName()) ? concept : null :
				TaraUtil.findChildOf(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static PsiElement resolveInnerConcept(List<Identifier> path) {
		int i;
		VirtualFile file = null;
		for (i = path.size() - 1; i >= 0; i--) {
			file = resolvePath(path.subList(0, i));
			if (file != null) break;
		}
		if (file != null) {
			PsiFile file1 = PsiManager.getInstance(path.get(0).getProject()).findFile(file);
			if (!TaraBoxFile.class.isInstance(file1)) return null;
			TaraBoxFile taraBoxFile = (TaraBoxFile) file1;
			for (Concept prime : taraBoxFile.getConcepts()) {
				Concept aConcept = resolvePathInConcept(path, prime);
				if (aConcept != null) return aConcept;
			}
		}
		return null;
	}

	private static PsiElement resolvePackageReference(Project project, String path) {
		return (PsiElement) JavaHelper.getJavaHelper(project).findPackage(path);
	}

	private static PsiElement resolveJavaClassReference(Project project, String path) {
		return JavaHelper.getJavaHelper(project).findClass(path);
	}

	public static VirtualFile resolvePath(List<? extends Identifier> boxPath) {
		if (boxPath.isEmpty()) return null;
		Project project = boxPath.get(0).getProject();
		if (boxPath.size() == 1) return project.getWorkspaceFile();
		if (boxPath.size() == 2) {
			Module moduleByName = ModuleManager.getInstance(project).findModuleByName(boxPath.get(1).getText());
			if (moduleByName == null) return null;
			return moduleByName.getModuleFile();
		}
		return resolveBoxInSourcePath(boxPath.subList(2, boxPath.size()), project);
	}


	public static VirtualFile resolveBoxInSourcePath(List<? extends Identifier> boxPath, Project project) {
		VirtualFile file = TaraUtil.getSourcePath(project, boxPath.get(0).getContainingFile());
		for (Identifier identifier : boxPath)
			file = TaraUtil.findChildFileOf(file, identifier.getText());
		return file;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> path) {
		TaraBoxFile context = (TaraBoxFile) path.get(0).getContainingFile();
		Collection<Import> imports = context.getImports();
		return searchInImport(path, imports);
	}

	private static Concept searchInImport(List<Identifier> path, Collection<Import> imports) {
		for (Import anImport : imports) {
			List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			PsiElement resolve = resolve(importIdentifiers.get(importIdentifiers.size() - 1), false);
			if (resolve == null) continue;
			if (!TaraBoxFile.class.isInstance(resolve.getContainingFile())) continue;
			TaraBoxFile containingFile = (TaraBoxFile) resolve.getContainingFile();
			for (Concept concept : containingFile.getConcepts()) {
				Concept solution = resolvePathInConcept(path, concept);
				if (solution != null) return solution;
			}
		}
		return null;
	}

	public static String join(List<? extends Identifier> subPath, char c) {
		String result = "";
		for (Identifier identifier : subPath) result += c + identifier.getText();
		return result.substring(1);
	}
}
