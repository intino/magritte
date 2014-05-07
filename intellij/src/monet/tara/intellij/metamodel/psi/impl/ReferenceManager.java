package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import monet.tara.intellij.codeinsight.JavaHelper;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = null;
		if (identifier.getParent() instanceof IdentifierReference)
			reference = resolveConceptReference(identifier, getIdentifiersFromConcept(identifier));
		else if (identifier.getParent() instanceof HeaderReference)
			reference = resolveHeaderReference(identifier, getRouteFromHeader(identifier));
		else if (identifier.getParent() instanceof ExternalReference)
			reference = resolveExternalReference(identifier, getIdentifiersFromExternal(identifier));
		else if (identifier.getParent() instanceof Signature) return identifier;
		if (reference instanceof Concept) reference = ((Concept) reference).getIdentifierNode();
		return reference;
	}

	private static Concept resolveConceptReference(Identifier identifier, List<Identifier> route) {
		return resolveConcept(identifier, route);
	}

	private static PsiElement resolveHeaderReference(Identifier identifier, List<Identifier> route) {
		VirtualFile file = resolveRoute(route);
		if (file != null) {
			if (file.isDirectory())
				return resolvePackageReference(identifier.getProject(), join(route, '.'));
		} else return resolveInnerConcept(route);
		TaraFile taraFile = (TaraFile) PsiManager.getInstance(identifier.getProject()).findFile(file);
		return taraFile != null ? taraFile.getConcept() : null;
	}

	private static PsiElement resolveExternalReference(Identifier identifier, List<Identifier> route) {
		TaraPacket packet = ((TaraFile) identifier.getContainingFile()).getPackage();
		String path = packet.getHeaderReference().getText() + "." +
			TaraPsiImplUtil.getExtensibleOfExtension(TaraPsiImplUtil.getContextOf(identifier)).getName() + "." + join(route, '.');
		return resolveJavaClassReference(identifier.getProject(), path);
	}


	private static List<Identifier> getRouteFromHeader(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private static List<Identifier> getIdentifiersFromConcept(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		route = route.subList(0, route.indexOf(identifier) + 1);
		return route;
	}

	private static List<Identifier> getIdentifiersFromExternal(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((ExternalReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private static Concept resolveConcept(PsiElement identifier, List<Identifier> route) {
		Concept reference = resolveRelativeReference(route, (TaraIdentifier) identifier);
		if (!isPrimeConcept(TaraPsiImplUtil.getContextOfRef((IdentifierReference) identifier.getParent())) && reference != null)
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

	public static VirtualFile resolveRoute(List<? extends Identifier> route) {
		VirtualFile file = TaraUtil.getSourcePath(route.get(0).getProject());
		for (Identifier identifier : route)
			file = TaraUtil.findChildFileOf(file, identifier.getText());
		return file;
	}

	private static boolean isPrimeConcept(Concept concept) {
		return TaraPsiImplUtil.getContextOf(concept) == null;
	}

	private static Concept resolveRelativeReference(List<Identifier> route, Identifier element) {
		Concept context = TaraPsiImplUtil.getContextOfRef((IdentifierReference) element.getParent());
		Concept concept = TaraPsiImplUtil.getContextOf(context);
		for (Identifier identifier : route) {
			concept = TaraUtil.findChildOf(concept, ((TaraIdentifierImpl) identifier).getIdentifier());
			if (concept == null) break;
		}
		return concept;
	}

	private static Concept resolveAbsoluteReference(List<Identifier> route) {
		TaraFile context = (TaraFile) TaraPsiImplUtil.getContextOf(route.get(0)).getContainingFile();
		Concept reference = searchInImport(route, context);
		if (reference != null) return reference;
		return searchInPackage(route, context.getPackageRoute());
	}

	private static Concept searchInPackage(List<Identifier> route, List<? extends Identifier> aPackage) {
		VirtualFile packageFile = resolveRoute(aPackage);
		Concept reference;
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

	private static String join(List<? extends Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier : subRoute) result += c + identifier.getText();
		return result.substring(1);
	}
}
