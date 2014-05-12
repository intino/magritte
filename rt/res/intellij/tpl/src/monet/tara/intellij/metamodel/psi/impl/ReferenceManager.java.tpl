package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import monet.::projectName::.intellij.codeinsight.JavaHelper;
import monet.::projectName::.intellij.metamodel.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReferenceManager {

	private ReferenceManager() {
	}

	\@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = null;
		if (identifier.getParent() instanceof IdentifierReference)
			reference = resolveDefinitionReference(identifier, getIdentifiersFromDefinition(identifier));
		else if (identifier.getParent() instanceof HeaderReference)
			reference = resolveHeaderReference(identifier, getRouteFromHeader(identifier));
		else if (identifier.getParent() instanceof ExternalReference)
			reference = resolveExternalReference(identifier, getIdentifiersFromExternal(identifier));
		else if (identifier.getParent() instanceof Signature) return identifier;
		if (reference instanceof Definition) reference = ((Definition) reference).getIdentifierNode();
		return reference;
	}

	private static Definition resolveDefinitionReference(Identifier identifier, List<Identifier> route) {
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

	private static PsiElement resolveExternalReference(Identifier identifier, List<Identifier> route) {
		::projectProperName::Packet packet = ((::projectProperName::File) identifier.getContainingFile()).getPackage();
		String path = packet.getHeaderReference().getText() + "." +
			::projectProperName::PsiImplUtil.getExtensibleOfExtension(::projectProperName::PsiImplUtil.getContextOf(identifier)).getName() + "." + join(route, '.');
		return resolveJavaClassReference(identifier.getProject(), path);
	}


	private static List<Identifier> getRouteFromHeader(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private static List<Identifier> getIdentifiersFromDefinition(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		route = route.subList(0, route.indexOf(identifier) + 1);
		return route;
	}

	private static List<Identifier> getIdentifiersFromExternal(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((ExternalReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private static Definition resolveDefinition(PsiElement identifier, List<Identifier> route) {
		Definition reference = resolveRelativeReference(route, (::projectProperName::Identifier) identifier);
		if (!isPrimeDefinition(::projectProperName::PsiImplUtil.getContextOfRef((IdentifierReference) identifier.getParent())) && reference != null)
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
		return ::projectProperName::PsiImplUtil.getContextOf(definition) == null;
	}

	private static Definition resolveRelativeReference(List<Identifier> route, Identifier element) {
		Definition context = ::projectProperName::PsiImplUtil.getContextOfRef((IdentifierReference) element.getParent());
		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(context);
		for (Identifier identifier \: route) {
			definition = ::projectProperName::Util.findChildOf(definition, ((::projectProperName::IdentifierImpl) identifier).getIdentifier());
			if (definition == null) break;
		}
		return definition;
	}

	private static Definition resolveAbsoluteReference(List<Identifier> route) {
		::projectProperName::File context = (::projectProperName::File) ::projectProperName::PsiImplUtil.getContextOf(route.get(0)).getContainingFile();
		Definition reference = searchInImport(route, context);
		if (reference != null) return reference;
		return searchInPackage(route, context.getPackageRoute());
	}

	private static Definition searchInPackage(List<Identifier> route, List<? extends Identifier> aPackage) {
		VirtualFile packageFile = resolveRoute(aPackage);
		Definition reference;
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

	private static String join(List<? extends Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier \: subRoute) result += c + identifier.getText();
		return result.substring(1);
	}
}
