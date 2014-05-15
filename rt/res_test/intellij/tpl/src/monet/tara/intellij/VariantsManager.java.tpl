package monet.::projectName::.intellij;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import monet.::projectName::.intellij.codeinsight.JavaHelper;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.*;
import monet.::projectName::.intellij.lang.psi.impl.ReferenceManager;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VariantsManager {

	private final List<PsiElement> variants;
	private final PsiElement myElement;
	private final Project project;
	private final List<Identifier> context;

	public VariantsManager(List<PsiElement> variants, PsiElement myElement) {
		this.variants = variants;
		this.myElement = myElement;
		this.project = myElement.getProject();
		this.context = solveIdentifierContext();
	}

	public void resolveVariants() {
		addContextVariants();
		addInPackageVariants();
		addImportVariants();
		addPackageVariants();
	}

	private void addContextVariants() {
		Definition contextOf = ::projectProperName::PsiImplUtil.getContextOf(::projectProperName::PsiImplUtil.getContextOf(myElement));
		if (contextOf == null) return;
		for (Definition definition \: ::projectProperName::PsiImplUtil.getChildrenOf(contextOf))
			resolveRouteFor(definition, context);
	}

	private List<PsiElement> addInPackageVariants() {
		List<PsiElement> variants = new ArrayList<>();
		VirtualFile packageFile = ReferenceManager.resolveRoute(((::projectProperName::File) myElement.getContainingFile()).getPackageRoute());
		if (packageFile == null) return Collections.EMPTY_LIST;
		for (VirtualFile vFile \: packageFile.getChildren()) {
			::projectProperName::File file = ::projectProperName::Util.get::projectProperName::FileFromVirtual(project, vFile);
			if (file == null) continue;
			resolveRouteFor(file.getDefinition(), context);
		}
		return variants;
	}

	private List<PsiElement> addImportVariants() {
		List<PsiElement> variants = new ArrayList<>();
		Import[] imports = ((::projectProperName::File) myElement.getContainingFile()).getImports();
		if (imports == null) return variants;
		for (Import anImport \: imports) {
			List<::projectProperName::Identifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			PsiElement resolve = ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1), false);
			if (resolve == null) continue;
			Definition definition = (resolve instanceof Identifier) ? ::projectProperName::PsiImplUtil.getContextOf(resolve) \: (Definition) resolve;
			resolveRouteFor(definition, context);
		}
		return variants;
	}

	private void addPackageVariants() {
		VirtualFile virtualFile = myElement.getContainingFile().getOriginalFile().getVirtualFile();
		VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(project).getSourceRootForFile(virtualFile);
		if (contentRoot == null) return;
		if (isChildrenResolution()) resolveRelativePackageReference(contentRoot);
		else resolveAbsolutePackageReference(contentRoot);
	}

	private void resolveRouteFor(Definition definition, List<Identifier> route) {
		List<Definition> childrenOf = ::projectProperName::PsiImplUtil.getChildrenOf(definition);
		if (route.isEmpty()) {
			if (!definition.isCase())
				variants.add(definition);
		} else if (route.get(0).getText().equals(definition.getName()))
			for (Definition child \: childrenOf)
				resolveRouteFor(child, route.subList(1, route.size()));
	}

	private void resolveAbsolutePackageReference(VirtualFile contentRoot) {
		for (VirtualFile file \: contentRoot.getChildren()) {
			NavigationItem psi = JavaHelper.getJavaHelper(project).findPackage(file.getName());
			if (file.isDirectory() && psi != null) variants.add((PsiElement) psi);
		}
	}

	private void resolveRelativePackageReference(VirtualFile contentRoot) {
		List<Identifier> objects = getIdentifiers();
		VirtualFile parent = contentRoot.findFileByRelativePath(ReferenceManager.join(objects.subList(0, objects.size() - 1), '/'));
		if (parent == null) return;
		for (VirtualFile file \: parent.getChildren()) {
			String relativePath = getRelativePath(contentRoot, file);
			NavigationItem aPackage = JavaHelper.getJavaHelper(project).findPackage(relativePath);
			if (aPackage != null) variants.add((PsiElement) aPackage);
			else addPossibleDefinitionOfPackage(file);
		}
	}

	private void addPossibleDefinitionOfPackage(VirtualFile file) {
		if (::projectProperName::FileType.INSTANCE.getDefaultExtension().equals(file.getExtension())) {
			::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(project).findFile(file);
			if (::projectName::File != null) variants.add(::projectName::File.getDefinition());
		}
	}

	private String getRelativePath(VirtualFile contentRoot, VirtualFile file) {
		return file.getPath().replace(contentRoot.getPath(), "").replaceAll("/", ".").substring(1);
	}

	private List<Identifier> getIdentifiers() {
		List<Identifier> objects = new ArrayList<>();
		for (PsiElement element \: myElement.getParent().getChildren())
			if (element instanceof Identifier) objects.add((Identifier) element);
		return objects;
	}

	public boolean isChildrenResolution() {
		return (myElement.getPrevSibling() != null) && myElement.getPrevSibling().getPrevSibling() instanceof Identifier;
	}

	public List<Identifier> solveIdentifierContext() {
		if (myElement.getParent() instanceof IdentifierReference) {
			IdentifierReference element = (IdentifierReference) myElement.getParent();
			List<? extends Identifier> list = element.getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		} else {
			HeaderReference element = (HeaderReference) myElement.getParent();
			List<? extends Identifier> list = element.getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		}
	}
}
