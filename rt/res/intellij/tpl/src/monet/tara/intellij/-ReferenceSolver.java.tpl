package monet.::projectName::.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.*;
import monet.::projectName::.intellij.metamodel.psi.impl.ReferenceManager;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::ReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public ::projectProperName::ReferenceSolver(\@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	\@NotNull
	\@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = ReferenceManager.resolve((Identifier) myElement);
		if (element != null)
			results.add(new PsiElementResolveResult(element));
		return results.toArray(new ResolveResult[results.size()]);
	}

	\@Nullable
	\@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() \: null;
	}

	\@NotNull
	\@Override
	public Object[] getVariants() {
		List<PsiElement> definitions = new ArrayList<>();
		if (myElement.getParent() instanceof IdentifierReference) {
			if (isReferenceToDefinition()) definitions.addAll(getVariants((::projectProperName::Identifier) myElement));
			else if (isChildrenResolution())
				getChildrenVariants((::projectProperName::Identifier) myElement.getPrevSibling().getPrevSibling(), definitions);
		} else if (myElement.getParent() instanceof HeaderReference)
			getVariantsInHeader(getSubRoute((Identifier) myElement));
		return fillVariants(definitions);
	}

	private boolean isChildrenResolution() {
		return myElement.getPrevSibling().getPrevSibling() instanceof Identifier;
	}

	private void getVariantsInHeader(List<Identifier> route) {
		//TODO
	}

	private List<Identifier> getSubRoute(Identifier identifier) {
		List<Identifier> route = (List<Identifier>) ((HeaderReference) (identifier.getParent())).getIdentifierList();
		return route.subList(0, route.indexOf(identifier) + 1);
	}

	private boolean isReferenceToDefinition() {
		return myElement.getPrevSibling() == null;
	}

	private Object[] fillVariants(List<PsiElement> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final PsiElement element \: elements) {
			if (element instanceof Definition) {
				Definition definition = (Definition) element;
				if (definition.getName() == null || definition.getName().length() == 0) continue;
				variants.add(LookupElementBuilder.create((PsiNamedElement) definition.getIdentifierNode()).withIcon(::projectProperName::Icons.ICON_13).withTypeText(getFileName(element)));
			} else
				variants.add(LookupElementBuilder.create((PsiNamedElement) element).withIcon(::projectProperName::Icons.ICON_13).withTypeText(element.getParent().getText()));
		}
		return variants.toArray();
	}

	private List<PsiElement> getVariants(::projectProperName::Identifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		variants.addAll(getPackageVariants(parent));
		variants.addAll(getImportVariants(parent));
		return variants;
	}

	private List<PsiElement> getImportVariants(::projectProperName::Identifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		Import[] imports = ((::projectProperName::File) parent.getContainingFile()).getImports();
		if (imports == null) return variants;
		for (Import anImport \: imports) {
			List<::projectProperName::Identifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			variants.add(importIdentifiers.get(importIdentifiers.size() - 1));
		}
		return variants;
	}

	private List<PsiElement> getPackageVariants(::projectProperName::Identifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		VirtualFile packageFile = ReferenceManager.resolveRoute(((::projectProperName::File) parent.getContainingFile()).getPackageRoute());
		if (packageFile == null) return Collections.EMPTY_LIST;
		for (VirtualFile vFile \: packageFile.getChildren()) {
			::projectProperName::File file = ::projectProperName::Util.get::projectProperName::FileFromVirtual(parent.getProject(), vFile);
			if (file != null) variants.add(file.getDefinition());
		}
		return variants;
	}

	private void getChildrenVariants(::projectProperName::Identifier parent, List<PsiElement> definitions) {
		PsiElement element = ReferenceManager.resolve(parent);
		if (element != null && element instanceof Definition)
			Collections.addAll(definitions, ::projectProperName::Util.getChildrenOf((Definition) element));
	}

	private String getFileName(PsiElement definition) {
		return definition.getContainingFile().getName().split("\\\\.")[0];
	}
}