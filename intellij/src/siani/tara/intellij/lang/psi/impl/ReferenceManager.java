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

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOf;

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
			reference = resolveHeaderReference(identifier, getRouteFromHeader(identifier));
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

	private static PsiElement resolveHeaderReference(Identifier identifier, List<Identifier> route) {
		VirtualFile file = resolveRoute(route);
		if (file != null) {
			if (file.isDirectory())
				return resolvePackageReference(identifier.getProject(), join(route.subList(2, route.size()), '.'));
			if ("iml".equals(file.getExtension()) || "xml".equals(file.getExtension()))
				return PsiManager.getInstance(route.get(0).getProject()).findFile(file);
		} else return resolveInnerConcept(route);
		return PsiManager.getInstance(identifier.getProject()).findFile(file);
	}

	private static PsiElement resolveExternalReference(Identifier identifier) {
		TaraBox box = ((TaraFile) identifier.getContainingFile()).getBoxReference();
		String path = box.getHeaderReference().getText() + "." + TaraUtil.composeConceptQN(identifier);
		return resolveJavaClassReference(identifier.getProject(), path);
	}

	private static List<Identifier> getRouteFromHeader(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private static List<Identifier> getIdentifiersOfReference(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		route = route.subList(0, route.indexOf(identifier) + 1);
		return route;
	}

	private static PsiElement resolveConcept(PsiElement identifier, List<Identifier> route) {
		List<Identifier> subRoute = route.subList(0, route.indexOf(identifier) + 1);
		PsiElement element = tryToResolveInBox(subRoute);
		if (element != null) return element;
		return tryToResolveOnImportedBoxes(subRoute);
	}

	private static PsiElement tryToResolveInBox(List<Identifier> route) {
		Concept[] possibleRoots = getPossibleRoots(route.get(0));
		if (possibleRoots.length == 0) return null;
		if (route.size() == 1) return possibleRoots[0];
		for (Concept possibleRoot : possibleRoots) {
			Concept concept = resolvePathInConcept(route, possibleRoot);
			if (concept != null) return concept;
		}
		return null;
	}

	private static Concept[] getPossibleRoots(Identifier identifier) {
		List<Concept> list = new ArrayList();
		Concept parent = getContextOf(identifier);
		while (parent != null) {
			for (Concept sibling : parent.getConceptSiblings()) {
				if (sibling.getName() != null && sibling.getName().equals(identifier.getName()))
					list.add(parent);
			}
			parent = getContextOf(parent);
		}
		return list.toArray(new Concept[list.size()]);
	}

	private static Concept resolvePathInConcept(List<Identifier> route, Concept concept) {
		Concept reference = null;
		for (Identifier identifier : route) {
			reference = (reference == null) ? identifier.getText().equals(concept.getName()) ? concept : null :
				TaraUtil.findChildOf(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static PsiElement resolveInnerConcept(List<Identifier> route) {
		int i;
		VirtualFile file = null;
		for (i = route.size() - 1; i >= 0; i--) {
			file = resolveRoute(route.subList(0, i));
			if (file != null) break;
		}
		if (file != null) {
			PsiFile file1 = PsiManager.getInstance(route.get(0).getProject()).findFile(file);
			if (!TaraFile.class.isInstance(file1)) return null;
			TaraFile taraFile = (TaraFile) file1;
			Concept[] concept = taraFile.getConcepts();
			for (Concept prime : concept) {
				Concept aConcept = resolvePathInConcept(route, prime);
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

	public static VirtualFile resolveRoute(List<? extends Identifier> boxRoute) {
		if (boxRoute.isEmpty()) return null;
		Project project = boxRoute.get(0).getProject();
		if (boxRoute.size() == 1) return project.getWorkspaceFile();
		if (boxRoute.size() == 2) {
			Module moduleByName = ModuleManager.getInstance(project).findModuleByName(boxRoute.get(1).getText());
			if (moduleByName == null) return null;
			return moduleByName.getModuleFile();
		}
		return resolveBoxInSourcePath(boxRoute.subList(2, boxRoute.size()), project);
	}


	public static VirtualFile resolveBoxInSourcePath(List<? extends Identifier> boxRoute, Project project) {
		VirtualFile file = TaraUtil.getSourcePath(project, boxRoute.get(0).getContainingFile());
		for (Identifier identifier : boxRoute)
			file = TaraUtil.findChildFileOf(file, identifier.getText());
		return file;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> route) {
		TaraFile context = (TaraFile) route.get(0).getContainingFile();
		Import[] imports = context.getImports();
		if (imports == null) return null;
		return searchInImport(route, imports);
	}

	private static Concept searchInImport(List<Identifier> route, Import[] imports) {
		for (Import anImport : imports) {
			List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			PsiElement resolve = resolve(importIdentifiers.get(importIdentifiers.size() - 1), false);
			if (resolve == null) continue;
			if (!TaraFile.class.isInstance(resolve.getContainingFile())) continue;
			TaraFile containingFile = (TaraFile) resolve.getContainingFile();
			for (Concept concept : containingFile.getConcepts()) {
				Concept solution = resolvePathInConcept(route, concept);
				if (solution != null) return solution;
			}
		}
		return null;
	}

	public static String join(List<? extends Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier : subRoute) result += c + identifier.getText();
		return result.substring(1);
	}
}
