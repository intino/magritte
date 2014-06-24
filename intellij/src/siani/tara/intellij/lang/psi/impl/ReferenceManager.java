package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.codeinsight.JavaHelper;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;

import java.util.List;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOf;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOfRef;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier, boolean external) {
		PsiElement reference = null;
		if (external) reference = resolveExternalReference(identifier);
		else if (identifier.getParent() instanceof IdentifierReference)
			reference = resolveConceptReference(identifier, getIdentifiersOfReference(identifier));
		else if (identifier.getParent() instanceof HeaderReference)
			reference = resolveHeaderReference(identifier, getRouteFromHeader(identifier));
		else if (identifier.getParent() instanceof Signature) return identifier;
		if (reference instanceof Concept) reference = ((Concept) reference).getIdentifierNode();
		return reference;
	}

	private static PsiElement resolveConceptReference(Identifier identifier, List<Identifier> route) {
		return resolveConcept(identifier, route);
	}

	private static PsiElement resolveHeaderReference(Identifier identifier, List<Identifier> route) {
		VirtualFile file = resolveRoute(route);
		if (file != null) {
			if (file.isDirectory())
				return resolvePackageReference(identifier.getProject(), join(route.subList(2, route.size()), '.'));
			if (file.getExtension().equals("iml") | file.getExtension().equals("xml")) {
				PsiFile module = PsiManager.getInstance(route.get(0).getProject()).findFile(file);
				return module;
			}
		} else return resolveInnerConcept(route);
		TaraFile taraFile = (TaraFile) PsiManager.getInstance(identifier.getProject()).findFile(file);

		return taraFile;
	}

	private static PsiElement resolveExternalReference(Identifier identifier) {
		TaraBox box = ((TaraFile) identifier.getContainingFile()).getBoxReference();
		String path = box.getHeaderReference().getText() + "." + composeConceptRoute(identifier);
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

	private static String composeConceptRoute(Identifier identifier) {
		Concept concept = getContextOf(identifier);
		String route = concept.getName();
		while (concept != null) {
			concept = getContextOf(concept);
			if (concept != null) route = concept.getName() + "." + route;
		}
		return route;
	}

	private static PsiElement resolveConcept(PsiElement identifier, List<Identifier> route) {
		PsiElement reference = resolveRelativeReference(route, (TaraIdentifier) identifier);
		if (!isPrimeConcept(getContextOfRef((IdentifierReference) identifier.getParent())) && reference != null)
			return reference;
		else return resolveAbsoluteReference(route);
	}

	private static PsiElement resolveInnerConcept(List<Identifier> route) {
		int i;
		VirtualFile file = null;
		for (i = route.size() - 1; i >= 0; i--) {
			file = resolveRoute(route.subList(0, i));
			if (file != null) break;
		}
		if (file != null) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(route.get(0).getProject()).findFile(file);
			if (taraFile == null) return null;
			Concept concept = taraFile.getConcept();
			for (int j = i; j < route.size(); j++)
				if (concept != null) concept = TaraUtil.findChildOf(concept, route.get(j).getText());
			return concept;
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
		VirtualFile file = TaraUtil.getSourcePath(project, boxRoute.get(0).getContainingFile());
		for (Identifier identifier : boxRoute.subList(2, boxRoute.size()))
			file = TaraUtil.findChildFileOf(file, identifier.getText());
		return file;
	}

	private static boolean isPrimeConcept(Concept concept) {
		return getContextOf(concept) == null;
	}

	private static PsiElement resolveRelativeReference(List<Identifier> route, Identifier element) {
		Concept context = getContextOfRef((IdentifierReference) element.getParent());
		Concept concept = getContextOf(context);
		for (Identifier identifier : route) {
			concept = TaraUtil.findChildOf(concept, ((TaraIdentifierImpl) identifier).getIdentifier());
			if (concept == null) break;
		}
		return concept;
	}

	private static PsiElement resolveAbsoluteReference(List<Identifier> route) {
		TaraFile context = (TaraFile) getContextOf(route.get(0)).getContainingFile();
		Concept reference = searchInImport(route, context);
		if (reference != null) return reference;
		PsiElement concept = searchInPackage(route, context.getBoxRoute());
		return concept != null ? concept : searchInProject(route);
	}

	private static PsiElement searchInProject(List<Identifier> route) {
		PsiFile file = route.get(0).getContainingFile();
		final VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(file.getProject()).getSourceRootForFile(file.getOriginalFile().getVirtualFile());
		if (contentRoot != null) {
			String path = join(route, '/');
			if (Character.isUpperCase(route.get(route.size() - 1).getText().charAt(0)))
				path += "." + TaraFileType.INSTANCE.getDefaultExtension();
			final VirtualFile proposedFile = contentRoot.findFileByRelativePath(path);
			if (proposedFile != null) {
				TaraFile taraFile = (TaraFile) PsiManager.getInstance(file.getProject()).findFile(proposedFile);
				if (taraFile != null) return taraFile.getConcept();
				else return resolvePackageReference(file.getProject(), join(route, '.'));
			}
		}
		return null;
	}

	private static PsiElement searchInPackage(List<Identifier> route, List<? extends Identifier> aPackage) {
		VirtualFile packageFile = resolveRoute(aPackage);
		PsiElement reference;
		if (packageFile == null) return null;
		for (VirtualFile vFile : packageFile.getChildren()) {
			PsiFile file = PsiManager.getInstance(aPackage.get(0).getProject()).findFile(vFile);
			if (file instanceof TaraFile) {
				reference = checkPathInConcept(route, ((TaraFile) file).getConcept());
				if (reference != null) return reference;
			}
		}
		return null;
	}

	private static Concept checkPathInConcept(List<Identifier> route, Concept concept) {
		Concept reference = null;
		for (Identifier identifier : route) {
			reference = (reference == null) ? identifier.getText().equals(concept.getName()) ? concept : null :
				TaraUtil.findChildOf(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static Concept searchInImport(List<Identifier> route, TaraFile context) {
		Import[] imports = context.getImports();
		if (imports != null)
			for (Import anImport : imports) {
				List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
				TaraIdentifier identifier = importIdentifiers.get(importIdentifiers.size() - 1);
				if (identifier.getText().equals(route.get(0).getText()))
					return resolveConceptInImport(route, resolveHeaderReference(identifier, getRouteFromHeader(identifier)));
			}
		return null;
	}

	private static Concept resolveConceptInImport(List<Identifier> conceptRoute, PsiElement reference) {
		Concept concept;
		if (reference instanceof TaraFile) {
			TaraFile psiFile = (TaraFile) reference;
			concept = psiFile.getConcept();
		} else concept = (Concept) reference;
		for (int i = 1; i < conceptRoute.size(); i++)
			if ((concept = TaraUtil.findChildOf(concept, conceptRoute.get(i).getText())) == null) return null;
		return concept;
	}

	public static String join(List<? extends Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier : subRoute) result += c + identifier.getText();
		return result.substring(1);
	}
}
