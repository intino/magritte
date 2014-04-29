package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.::projectName::.intellij.codeinsight.JavaHelper;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::Util {

	private ::projectProperName::Util() {
	}

	\@NotNull
	public static List<Definition> findRootDefinition(Project project, String identifier) {
		List<Definition> result = new ArrayList<>();
		for (::projectProperName::FileImpl ::projectName::File \: getProjectFiles(project))
			result.addAll(getDefinitionsOfFileByName(::projectName::File, identifier));
		return result;
	}

	\@NotNull
	public static List<Definition> getDefinitionsOfFileByName(::projectProperName::FileImpl ::projectName::File, String identifier) {
		List<Definition> result = new ArrayList<>();
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		if (definitions != null) result.addAll(getDefinitionsByName(definitions, identifier));
		return result;
	}

	public static Definition getRootDefinitionOfFile(::projectProperName::FileImpl ::projectName::File) {
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		return (definitions != null) ? definitions[0] \: null;
	}

	\@NotNull
	private static ::projectProperName::FileImpl[] getProjectFiles(Project project) {
		List<::projectProperName::FileImpl> ::projectName::Files = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, ::projectProperName::FileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile file \: files)
			if (file != null) ::projectName::Files.add((::projectProperName::FileImpl) PsiManager.getInstance(project).findFile(file));
		return ::projectName::Files.toArray(new ::projectProperName::FileImpl[::projectName::Files.size()]);
	}

	\@NotNull
	private static List<Definition> getDefinitionsByName(Definition[] definitions, String identifier) {
		List<Definition> result = new ArrayList<>();
		for (Definition definition \: definitions)
			if (definition.getName() != null && identifier.equals(definition.getName())) result.add(definition);
		return result;
	}

	\@NotNull
	public static List<Definition> getRootDefinitions(Project project) {
		List<Definition> result = new ArrayList<>();
		for (::projectProperName::FileImpl ::projectName::File \: getProjectFiles(project)) {
			Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
			if (definitions != null) Collections.addAll(result, definitions);
		}
		return result;
	}

	public static int findDuplicates(Project project, Definition definition) {
		Definition parent = ::projectProperName::PsiImplUtil.getContextOf(definition);
		if (parent != null)
			return checkChildDuplicates(definition, parent);
		return findRootDefinition(project, definition.getIdentifierNode().getText()).size();
	}

	private static int checkChildDuplicates(Definition definition, Definition parent) {
		int duplicates = 0;
		for (Definition ::projectName::Definition \: ::projectProperName::PsiImplUtil.getChildrenOf(parent))
			if (::projectName::Definition.getName() != null && definition.getName() != null && ::projectName::Definition.getName().equals(definition.getName()))
				duplicates++;
		return duplicates;
	}

	\@NotNull
	public static Attribute[] findAttributeDuplicates(Attribute attribute) {
		List<Attribute> result = new ArrayList<>();
		List<Attribute> attributes = ::projectProperName::PsiImplUtil.getAttributesInBody((Body) attribute.getParent());
		for (Attribute ::projectName::Attribute \: attributes)
			if (::projectName::Attribute.getName() != null && ::projectName::Attribute.getName().equals(attribute.getName()))
				result.add(::projectName::Attribute);
		return result.toArray(new Attribute[result.size()]);
	}

	\@NotNull
	public static List<Definition> findAllDefinitionsOfFile(::projectProperName::FileImpl ::projectName::File) {
		List<Definition> result = new ArrayList<>();
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		if (definitions != null) {
			Collections.addAll(result, definitions);
			for (Definition definition \: definitions)
				result.addAll(::projectProperName::PsiImplUtil.getChildrenOf(definition));
		}
		return result;

	}

	public static PsiElement resolveReference(PsiElement identifier) {
		PsiElement reference = null;
		if (identifier.getParent() instanceof ReferenceIdentifier)
			reference = resolveDefinitionReference(identifier);
		else if (identifier.getParent() instanceof HeaderReference) {
			reference = resolveHeaderReference(identifier);
		}
		return reference;
	}


	public static Definition resolveDefinitionReference(PsiElement identifier) {
		if (identifier.getParent() instanceof ReferenceIdentifier) {
			List<Identifier> route = (List<Identifier>) ((ReferenceIdentifier) (identifier.getParent())).getIdentifierList();
			route = route.subList(0, route.indexOf(identifier) + 1);
			return resolveInDefinitionReference(identifier, route);
		}
		return null;
	}

	public static PsiElement resolveHeaderReference(PsiElement identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		List<Identifier> subRoute = route.subList(0, route.indexOf(identifier) + 1);
		VirtualFile file = resolveRoute(subRoute);
		if (file == null || file.isDirectory())
			return resolvePackageReference(identifier.getProject(), join(subRoute, '.'));
		return PsiManager.getInstance(identifier.getProject()).findFile(file);
	}

	private static String join(List<Identifier> subRoute, char c) {
		String result = "";
		for (Identifier identifier \: subRoute) {
			result += c + identifier.getText();
		}
		return result.substring(1);
	}

	public static PsiElement resolvePackageReference(Project project, String path) {
		return (PsiElement) JavaHelper.getJavaHelper(project).findPackage(path);
	}

	private static VirtualFile resolveRoute(List<Identifier> subRoute) {
		VirtualFile file = getSourcePath(subRoute.get(0).getProject());
		for (Identifier identifier \: subRoute)
			file = findChildFileOf(file, identifier.getText());
		return file;
	}

	private static VirtualFile findChildFileOf(VirtualFile file, String name) {
		for (VirtualFile virtualFile \: file.getChildren())
			if (virtualFile.getName().split("\\\\.")[0].equals(name))
				return virtualFile;
		return null;
	}

	private static Definition resolveInDefinitionReference(PsiElement identifier, List<Identifier> route) {
		Definition reference = resolveRelativeReference(route, (::projectProperName::Identifier) identifier);
		if (!isRootDefinition(::projectProperName::PsiImplUtil.resolveContextOfRef((ReferenceIdentifier) identifier.getParent())) && reference != null)
			return reference;
		else return resolveAbsoluteReference(identifier.getProject(), route);
	}

	private static boolean isRootDefinition(Definition definition) {
		return ::projectProperName::PsiImplUtil.getContextOf(definition) == null;
	}

	private static Definition resolveRelativeReference(List<Identifier> route, Identifier element) {
		Definition context = ::projectProperName::PsiImplUtil.resolveContextOfRef((ReferenceIdentifier) element.getParent());
		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(context);
		for (Identifier identifier \: route) {
			definition = findChildOf(definition, ((::projectProperName::IdentifierImpl) identifier).getIdentifier());
			if (definition == null) break;
		}
		return definition;
	}

	private static Definition resolveAbsoluteReference(Project project, List<Identifier> identifiers) {
		List<Definition> rootDefinitions = findRootDefinition(project, ((::projectProperName::IdentifierImpl) identifiers.get(0)).getIdentifier());
		Definition reference = null;
		for (Definition rootDefinition \: rootDefinitions) {
			Definition internRef = rootDefinition;
			for (int i = 1; i < identifiers.size(); i++) {
				internRef = findChildOf(internRef, ((::projectProperName::IdentifierImpl) identifiers.get(i)).getIdentifier());
				if (internRef == null) break;
			}
			reference = internRef;
		}
		return reference;
	}

	public static List<Definition> getSiblings(Definition context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof ::projectProperName::File) && !(element instanceof Body))
			element = element.getParent();
		if (element != null && !(element instanceof ::projectProperName::File))
			return ::projectProperName::PsiImplUtil.getChildrenInBody((Body) element);
		return Collections.EMPTY_LIST;
	}

	public static Definition[] getChildrenOf(Definition definition) {
		List<Definition> childrenOf = ::projectProperName::PsiImplUtil.getChildrenOf(definition);
		return childrenOf.toArray(new Definition[childrenOf.size()]);
	}

	public static Definition findChildOf(Definition definition, String name) {
		List<Definition> children = ::projectProperName::PsiImplUtil.getChildrenOf(definition);
		for (Definition child \: children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}


	public static VirtualFile getSourcePath(Project project) {
		Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module \: modules) {
			ModuleRootManager mrm = ModuleRootManager.getInstance(module);
			List<VirtualFile> virtualFiles = mrm.getSourceRoots(JavaModuleSourceRootTypes.SOURCES);
			for (VirtualFile file \: virtualFiles)
				if (file.isDirectory() && "src".equals(file.getName()))
					return file;
		}
		return null;
	}
}
