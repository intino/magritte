package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import monet.::projectName::.intellij.codeinsight.JavaHelper;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil.getContextOf;
import static monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil.getContextOfRef;

public class ReferenceManager {

	private ReferenceManager() {
	}

	\@Nullable
	public static PsiElement resolve(Identifier identifier, boolean external) {
		PsiElement reference = null;
		if (external) reference = resolveExternalReference(identifier);
		else if (identifier.getParent() instanceof IdentifierReference)
			reference = resolveDefinitionReference(identifier, getIdentifiersOfReference(identifier));
		else if (identifier.getParent() instanceof HeaderReference)
			reference = resolveHeaderReference(identifier, getRouteFromHeader(identifier));
		else if (identifier.getParent() instanceof Signature) return identifier;
		if (reference instanceof Definition) reference = ((Definition) reference).getIdentifierNode();
		return reference;
	}

	private static PsiElement resolveDefinitionReference(Identifier identifier, List<Identifier> route) {
		return resolveDefinition(identifier, route);
	}

	private static PsiElement resolveHeaderReference(Identifier identifier, List<Identifier> route) {
		VirtualFile file = resolveRoute(route);
		if (file != null) {
			if (file.isDirectory())
				return resolvePackageReference(identifier.getProject(), join(route, '.'));
		} else return resolveInnerDefinition(route);
		::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(identifier.getProject()).findFile(file);
		return ::projectName::File != null ? ::projectName::File.getDefinition() \: null;
	}

	private static PsiElement resolveExternalReference(Identifier identifier) {
		::projectProperName::Packet packet = ((::projectProperName::File) identifier.getContainingFile()).getPackage();
		String path = packet.getHeaderReference().getText() + "." + composeDefinitionRoute(identifier);
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

	private static String composeDefinitionRoute(Identifier identifier) {
		Definition definition = getContextOf(identifier);
		String route = definition.getName();
		while (definition != null) {
			definition = getContextOf(definition);
			if (definition != null) route = definition.getName() + "." + route;
		}
		return route;
	}

	private static PsiElement resolveDefinition(PsiElement identifier, List<Identifier> route) {
		PsiElement reference = resolveRelativeReference(route, (::projectProperName::Identifier) identifier);
		if (!isPrimeDefinition(getContextOfRef((IdentifierReference) identifier.getParent())) && reference != null)
			return reference;
		else return resolveAbsoluteReference(route);
	}

	private static PsiElement resolveInnerDefinition(List<Identifier> route) {
		int i;
		VirtualFile file = null;
		for (i = route.size() - 1; i >= 0; i--) {
			file = resolveRoute(route.subList(0, i));
			if (file != null) break;
		}
		if (file != null) {
			::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(route.get(0).getProject()).findFile(file);
			if (::projectName::File == null) return null;
			Definition definition = ::projectName::File.getDefinition();
			for (int j = i; j < route.size(); j++)
				if (definition != null) definition = ::projectProperName::Util.findChildOf(definition, route.get(j).getText());
			return definition;
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
		VirtualFile file = ::projectProperName::Util.getSourcePath(route.get(0).getProject());
		for (Identifier identifier \: route)
			file = ::projectProperName::Util.findChildFileOf(file, identifier.getText());
		return file;
	}

	private static boolean isPrimeDefinition(Definition definition) {
		return getContextOf(definition) == null;
	}

	private static PsiElement resolveRelativeReference(List<Identifier> route, Identifier element) {
		Definition context = getContextOfRef((IdentifierReference) element.getParent());
		Definition definition = getContextOf(context);
		for (Identifier identifier \: route) {
			definition = ::projectProperName::Util.findChildOf(definition, ((::projectProperName::IdentifierImpl) identifier).getIdentifier());
			if (definition == null) break;
		}
		return definition;
	}

	private static PsiElement resolveAbsoluteReference(List<Identifier> route) {
		::projectProperName::File context = (::projectProperName::File) getContextOf(route.get(0)).getContainingFile();
		Definition reference = searchInImport(route, context);
		if (reference != null) return reference;
		PsiElement definition = searchInPackage(route, context.getPackageRoute());
		return definition != null ? definition \: searchInProject(route);
	}

	private static PsiElement searchInProject(List<Identifier> route) {
		PsiFile file = route.get(0).getContainingFile();
		final VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(file.getProject()).getSourceRootForFile(file.getOriginalFile().getVirtualFile());
		if (contentRoot != null) {
			String path = join(route, '/');
			if (Character.isUpperCase(route.get(route.size() - 1).getText().charAt(0)))
				path += "." + ::projectProperName::FileType.INSTANCE.getDefaultExtension();
			final VirtualFile proposedFile = contentRoot.findFileByRelativePath(path);
			if (proposedFile != null) {
				::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(file.getProject()).findFile(proposedFile);
				if (::projectName::File != null) return ::projectName::File.getDefinition();
				else return resolvePackageReference(file.getProject(), join(route, '.'));
			}
		}
		return null;
	}

	private static PsiElement searchInPackage(List<Identifier> route, List<? extends Identifier> aPackage) {
		VirtualFile packageFile = resolveRoute(aPackage);
		PsiElement reference;
		if (packageFile == null) return null;
		for (VirtualFile vFile \: packageFile.getChildren()) {
			PsiFile file = PsiManager.getInstance(aPackage.get(0).getProject()).findFile(vFile);
			if (file instanceof ::projectProperName::File) {
				reference = checkPathInDefinition(route, ((::projectProperName::File) file).getDefinition());
				if (reference != null) return reference;
			}
		}
		return null;
	}

	private static Definition checkPathInDefinition(List<Identifier> route, Definition definition) {
		Definition reference = null;
		for (Identifier identifier \: route) {
			reference = (reference == null) ? identifier.getText().equals(definition.getName()) ? definition \: null \:
				::projectProperName::Util.findChildOf(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static Definition searchInImport(List<Identifier> route, ::projectProperName::File context) {
		Import[] imports = context.getImports();
		if (imports != null)
			for (Import anImport \: imports) {
				List<::projectProperName::Identifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
				::projectProperName::Identifier identifier = importIdentifiers.get(importIdentifiers.size() - 1);
				if (identifier.getText().equals(route.get(0).getText()))
					return resolveDefinitionInImport(route, resolveHeaderReference(identifier, getRouteFromHeader(identifier)));
			}
		return null;
	}

	private static Definition resolveDefinitionInImport(List<Identifier> definitionRoute, PsiElement reference) {
		Definition definition;
		if (reference instanceof ::projectProperName::File) {
			::projectProperName::File psiFile = (::projectProperName::File) reference;
			definition = psiFile.getDefinition();
		} else definition = (Definition) reference;
		for (int i = 1; i < definitionRoute.size(); i++)
			if ((definition = ::projectProperName::Util.findChildOf(definition, definitionRoute.get(i).getText())) == null) return null;
		return definition;
	}

	public static String join(List<? extends Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier \: subRoute) result += c + identifier.getText();
		return result.substring(1);
	}
}
