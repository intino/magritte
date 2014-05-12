package monet.tara.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.ReferenceManager;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraReferenceSolver(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = ReferenceManager.resolve((Identifier) myElement);
		if (element != null)
			results.add(new PsiElementResolveResult(element));
		return results.toArray(new ResolveResult[results.size()]);
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		List<PsiElement> concepts = new ArrayList<>();
		if (myElement.getParent() instanceof IdentifierReference) {
			if (isReferenceToConcept()) concepts.addAll(getVariants((TaraIdentifier) myElement));
			else if (isChildrenResolution())
				getChildrenVariants((TaraIdentifier) myElement.getPrevSibling().getPrevSibling(), concepts);
		} else if (myElement.getParent() instanceof HeaderReference)
			getVariantsInHeader(getSubRoute((Identifier) myElement));
		return fillVariants(concepts);
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

	private boolean isReferenceToConcept() {
		return myElement.getPrevSibling() == null;
	}

	private Object[] fillVariants(List<PsiElement> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final PsiElement element : elements) {
			if (element instanceof Concept) {
				Concept concept = (Concept) element;
				if (concept.getName() == null || concept.getName().length() == 0) continue;
				variants.add(LookupElementBuilder.create((PsiNamedElement) concept.getIdentifierNode()).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(element)));
			} else
				variants.add(LookupElementBuilder.create((PsiNamedElement) element).withIcon(TaraIcons.ICON_13).withTypeText(element.getParent().getText()));
		}
		return variants.toArray();
	}

	private List<PsiElement> getVariants(TaraIdentifier parent) {
		List<PsiElement> variants = new ArrayList<>();
		variants.addAll(getPackageVariants(parent));
		variants.addAll(getImportVariants(parent));
		return variants;
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
			TaraFile file = TaraUtil.getTaraFileFromVirtual(parent.getProject(), vFile);
			if (file != null) variants.add(file.getConcept());
		}
		return variants;
	}

	private void getChildrenVariants(TaraIdentifier parent, List<PsiElement> concepts) {
		PsiElement element = ReferenceManager.resolve(parent);
		if (element != null && element instanceof Concept)
			Collections.addAll(concepts, TaraUtil.getChildrenOf((Concept) element));
	}

	private String getFileName(PsiElement concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}