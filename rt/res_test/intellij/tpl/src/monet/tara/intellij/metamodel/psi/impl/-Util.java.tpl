package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.Attribute;
import monet.::projectName::.intellij.metamodel.psi.Body;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
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
			if (identifier.equals(::projectName::File.getDefinition().getName())) result.add(::projectName::File.getDefinition());
		return result;
	}

	public static Definition getRootDefinitionOfFile(::projectProperName::FileImpl ::projectName::File) {
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		return (definitions != null) ? definitions[0] \: null;
	}

	\@NotNull
	public static Definition getBaseDefinitionOf(Definition definition) {
		Definition contextOf = ::projectProperName::PsiImplUtil.getContextOf(definition);
		if (contextOf.isBase()) return contextOf;
		else return ::projectProperName::PsiImplUtil.getContextOf(contextOf);
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

	public static VirtualFile findChildFileOf(VirtualFile file, String name) {
		if (file.getChildren() == null) return null;
		for (VirtualFile virtualFile \: file.getChildren())
			if (virtualFile.getName().split("\\\\.")[0].equals(name))
				return virtualFile;
		return null;
	}

	public static ::projectProperName::File get::projectProperName::FileFromVirtual(Project project, VirtualFile vFile) {
		PsiFile file = PsiManager.getInstance(project).findFile(vFile);
		if (file instanceof ::projectProperName::File) return (::projectProperName::File) file;
		return null;
	}

	public static List<Definition> getSiblings(Definition context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof ::projectProperName::File) && !(element instanceof Body))
			element = element.getParent();
		if (element != null && !(element instanceof ::projectProperName::File))
			return ::projectProperName::PsiImplUtil.getChildrenInBody((Body) element);
		return Collections.EMPTY_LIST;
	}

	\@NotNull
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
