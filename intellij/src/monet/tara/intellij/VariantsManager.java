package monet.tara.intellij;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import monet.tara.intellij.codeinsight.JavaHelper;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.ReferenceManager;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VariantsManager {

	private final List<PsiElement> variants;
	private final PsiElement myElement;
	private final boolean external;
	private final Project project;

	public VariantsManager(List<PsiElement> variants, PsiElement myElement, boolean external) {
		this.variants = variants;
		this.myElement = myElement;
		this.external = external;
		this.project = myElement.getProject();
	}

	public void getVariantsInHeader() {
		List<Identifier> subRoute = getSubRoute((Identifier) myElement);
		//TODO
	}

	public void getChildrenVariants(TaraIdentifier parent) {
		PsiElement element = ReferenceManager.resolve(parent, external);
		if (element != null && element instanceof Concept)
			Collections.addAll(variants, TaraUtil.getChildrenOf((Concept) element));
	}

	public void getPackageVariants() {
		VirtualFile virtualFile = myElement.getContainingFile().getOriginalFile().getVirtualFile();
		VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(project).getSourceRootForFile(virtualFile);
		if (contentRoot == null) return;
		if (isChildrenResolution()) resolveRelativePackageReference(contentRoot);
		else resolveAbsolutePackageReference( contentRoot);
	}

	public List<PsiElement> getVariants(TaraIdentifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		variants.addAll(getPackageVariants(parent));
		variants.addAll(getImportVariants(parent));
		return variants;
	}

	private void resolveAbsolutePackageReference(VirtualFile contentRoot) {
		for (VirtualFile file : contentRoot.getChildren()) {
			NavigationItem psi = JavaHelper.getJavaHelper(project).findPackage(file.getName());
			if (file.isDirectory() && psi != null) variants.add((PsiElement) psi);
		}
	}

	private void resolveRelativePackageReference(VirtualFile contentRoot) {
		List<Identifier> objects = getIdentifiers();
		VirtualFile parent = contentRoot.findFileByRelativePath(ReferenceManager.join(objects.subList(0, objects.size() - 1), '/'));
		if (parent == null) return;
		for (VirtualFile file : parent.getChildren()) {
			String relativePath = getRelativePath(contentRoot, file);
			NavigationItem aPackage = JavaHelper.getJavaHelper(project).findPackage(relativePath);
			if (aPackage != null) variants.add((PsiElement) aPackage);
			else addPossibleConceptOfPackage(file);
		}
	}

	private void addPossibleConceptOfPackage(VirtualFile file) {
		if (TaraFileType.INSTANCE.getDefaultExtension().equals(file.getExtension())) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(file);
			if (taraFile != null) variants.add(taraFile.getConcept());
		}
	}

	private String getRelativePath(VirtualFile contentRoot, VirtualFile file) {
		return file.getPath().replace(contentRoot.getPath(), "").replaceAll("/", ".").substring(1);
	}

	private List<Identifier> getIdentifiers() {
		List<Identifier> objects = new ArrayList<>();
		for (PsiElement element : myElement.getParent().getChildren())
			if (element instanceof Identifier) objects.add((Identifier) element);
		return objects;
	}

	public boolean isChildrenResolution() {
		return (myElement.getPrevSibling() != null) && myElement.getPrevSibling().getPrevSibling() instanceof Identifier;
	}

	private List<Identifier> getSubRoute(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}



	private List<PsiElement> getImportVariants(TaraIdentifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		Import[] imports = ((TaraFile) parent.getContainingFile()).getImports();
		if (imports == null) return variants;
		for (Import anImport : imports) {
			List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			variants.add(importIdentifiers.get(importIdentifiers.size() - 1));
		}
		return variants;
	}

	private List<PsiElement> getPackageVariants(TaraIdentifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		VirtualFile packageFile = ReferenceManager.resolveRoute(((TaraFile) parent.getContainingFile()).getPackageRoute());
		if (packageFile == null) return Collections.EMPTY_LIST;
		for (VirtualFile vFile : packageFile.getChildren()) {
			TaraFile file = TaraUtil.getTaraFileFromVirtual(project, vFile);
			if (file != null) variants.add(file.getConcept());
		}
		return variants;
	}


}
