package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::Util {

	@NotNull
	public static List<Definition> findRootDefinition(Project project, String identifier) {
		List<Definition> result = new ArrayList<>();
		for (::projectProperName::FileImpl ::projectName::File : getProjectFiles(project))
			result.addAll(getDefinitionsOfFileByName(::projectName::File, identifier));
		return result;
	}

	@NotNull
	public static List<Definition> getDefinitionsOfFileByName(::projectProperName::FileImpl ::projectName::File, String identifier) {
		List<Definition> result = new ArrayList<>();
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		if (definitions != null) result.addAll(getDefinitionsByName(definitions, identifier));
		return result;
	}

	public static List<Definition> getDefinitionsOfFile(::projectProperName::FileImpl ::projectName::File) {
		List<Definition> result = new ArrayList<>();
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		if (definitions != null) Collections.addAll(result, definitions);
		return result;
	}

	@NotNull
	private static ::projectProperName::FileImpl[] getProjectFiles(Project project) {
		List<::projectProperName::FileImpl> ::projectName::Files = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, ::projectProperName::FileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile file : files)
			if (file != null) ::projectName::Files.add((::projectProperName::FileImpl) PsiManager.getInstance(project).findFile(file));
		return ::projectName::Files.toArray(new ::projectProperName::FileImpl[::projectName::Files.size()]);
	}

	@NotNull
	private static List<Definition> getDefinitionsByName(Definition[] definitions, String identifier) {
		List<Definition> result = new ArrayList<>();
		for (Definition definition : definitions)
			if (definition.getName() != null && identifier.equals(definition.getName())) result.add(definition);
		return result;
	}

	@NotNull
	public static List<Definition> getRootDefinitions(Project project) {
		List<Definition> result = new ArrayList<>();
		for (::projectProperName::FileImpl ::projectName::File : getProjectFiles(project)) {
			Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
			if (definitions != null) Collections.addAll(result, definitions);
		}
		return result;
	}

	public static int findDuplicates(Project project, Definition definition) {
		::projectProperName::Definition parent = ::projectProperName::PsiImplUtil.getContextOf(definition);
		if (parent != null)
			return checkChildDuplicates(definition, parent);
		return findRootDefinition(project, definition.getIdentifierNode().getText()).size();
	}

	private static int checkChildDuplicates(Definition definition, ::projectProperName::Definition parent) {
		int duplicates = 0;
		for (::projectProperName::Definition ::projectName::Definition : ::projectProperName::PsiImplUtil.getChildrenOf(parent))
			if (::projectName::Definition.getName() != null && definition.getName() != null && ::projectName::Definition.getName().equals(definition.getName()))
				duplicates++;
		return duplicates;
	}

	@NotNull
	public static ::projectProperName::Attribute[] findAttributeDuplicates(::projectProperName::Attribute attribute) {
		List<::projectProperName::Attribute> result = new ArrayList<>();
		List<::projectProperName::Attribute> attributes = ::projectProperName::PsiImplUtil.getAttributesInBody((::projectProperName::Body) attribute.getParent());
		for (::projectProperName::Attribute ::projectName::Attribute : attributes)
			if (::projectName::Attribute.getName() != null && ::projectName::Attribute.getName().equals(attribute.getName()))
				result.add(::projectName::Attribute);
		return result.toArray(new ::projectProperName::Attribute[result.size()]);
	}

	@NotNull
	public static List<Definition> findAllDefinitions(Project project) {
		List<Definition> result = new ArrayList<>();
		for (::projectProperName::FileImpl ::projectName::File : getProjectFiles(project))
			result.addAll(findAllDefinitionsOfFile(::projectName::File));
		return result;
	}

	@NotNull
	public static List<Definition> findAllDefinitionsOfFile(::projectProperName::FileImpl ::projectName::File) {
		List<Definition> result = new ArrayList<>();
		Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, Definition.class);
		if (definitions != null) {
			Collections.addAll(result, definitions);
			for (Definition definition : definitions)
				result.addAll(::projectProperName::PsiImplUtil.getChildrenOf(definition));
		}
		return result;

	}

	public static Definition resolveReferences(Project project, PsiElement identifier) {
		Definition reference;
		if (identifier.getParent() instanceof ::projectProperName::ExtendedDefinition) {
			List<::projectProperName::Identifier> route = ((::projectProperName::ExtendedDefinition) (identifier.getParent())).getIdentifierList();
			route = route.subList(0, route.indexOf(identifier) + 1);
			if (!isRootDefinition(::projectProperName::PsiImplUtil.resolveContextOfRef((::projectProperName::ExtendedDefinition) identifier.getParent())) &&
				(reference = resolveRelativeReference(route, (::projectProperName::Identifier) identifier)) != null) return reference;
			else return resolveAbsoluteReference(project, route);
		}
		return null;
	}

	private static boolean isRootDefinition(Definition definition) {
		return ::projectProperName::PsiImplUtil.getContextOf(definition) == null;
	}

	private static Definition resolveRelativeReference(List<::projectProperName::Identifier> route, ::projectProperName::Identifier element) {
		Definition context = ::projectProperName::PsiImplUtil.resolveContextOfRef((::projectProperName::ExtendedDefinition) element.getParent());
		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(context);
		for (::projectProperName::Identifier identifier : route)
			if ((definition = findChildOf(definition, identifier.getIdentifier())) == null) break;
		return definition;
	}

	private static Definition resolveAbsoluteReference(Project project, List<::projectProperName::Identifier> identifiers) {
		List<Definition> rootDefinitions = findRootDefinition(project, identifiers.get(0).getIdentifier());
		Definition reference = null;
		for (Definition rootDefinition : rootDefinitions) {
			Definition internRef = rootDefinition;
			for (int i = 1; i < identifiers.size(); i++) {
				internRef = findChildOf(internRef, identifiers.get(i).getIdentifier());
				if (internRef == null) break;
			}
			reference = internRef;
		}
		return reference;
	}

	public static ::projectProperName::Definition findSibling(Definition context, String identifier) {
		PsiElement element = context;
		List<::projectProperName::Definition> childrenInBody;
		while (element != null && !(element instanceof ::projectProperName::Body))
			element = element.getParent();
		if (element != null) {
			childrenInBody = ::projectProperName::PsiImplUtil.getChildrenInBody((::projectProperName::Body) element);
			return findChildIn(childrenInBody, identifier);
		}
		return null;
	}

	public static List<::projectProperName::Definition> getSiblings(Definition context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof ::projectProperName::File) && !(element instanceof ::projectProperName::Body))
			element = element.getParent();
		if (element != null && !(element instanceof ::projectProperName::File))
			return ::projectProperName::PsiImplUtil.getChildrenInBody((::projectProperName::Body) element);
		return Collections.EMPTY_LIST;
	}

	private static ::projectProperName::Definition findChildIn(List<::projectProperName::Definition> list, String identifier) {
		for (::projectProperName::Definition definition : list)
			if (definition.getName() != null && definition.getName().equals(identifier)) return definition;
		return null;
	}

	public static ::projectProperName::Definition[] getChildrenOf(Definition definition) {
		List<::projectProperName::Definition> childrenOf = ::projectProperName::PsiImplUtil.getChildrenOf(definition);
		return childrenOf.toArray(new ::projectProperName::Definition[childrenOf.size()]);
	}

	public static Definition findChildOf(Definition definition, String name) {
		List<::projectProperName::Definition> children = ::projectProperName::PsiImplUtil.getChildrenOf(definition);
		for (Definition child : children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}

	public static ::projectProperName::Identifier[] getAbsoluteReference(::projectProperName::Identifier reference) {
		::projectProperName::ExtendedDefinition extendedDefinition = (::projectProperName::ExtendedDefinition) reference.getParent();
		List<::projectProperName::Identifier> identifiers = extendedDefinition.getIdentifierList();
		identifiers = identifiers.subList(0, identifiers.indexOf(reference) + 1);
		return identifiers.toArray(new ::projectProperName::Identifier[identifiers.size()]);
	}
}
