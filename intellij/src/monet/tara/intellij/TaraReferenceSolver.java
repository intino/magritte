package monet.tara.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaraReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	private final boolean external;

	public TaraReferenceSolver(@NotNull PsiElement element, TextRange textRange, boolean external) {
		super(element, textRange);
		this.external = external;
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = ReferenceManager.resolve((Identifier) myElement, external);
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
		List<PsiElement> variants = new ArrayList<>();
		VariantsManager manager = new VariantsManager(variants, myElement, external);
		if (myElement.getParent() instanceof IdentifierReference) {
			if (isReferenceToConcept()) manager.getVariants((TaraIdentifier) myElement);
			else if (manager.isChildrenResolution())
				manager.getChildrenVariants((TaraIdentifier) myElement.getPrevSibling().getPrevSibling());
		} else if (myElement.getParent() instanceof HeaderReference)
			manager.getVariantsInHeader();
		manager.getPackageVariants();
		return fillVariants(variants);
	}


	private boolean isReferenceToConcept() {
		return myElement.getPrevSibling() == null;
	}

	public Object[] fillVariants(List<PsiElement> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final PsiElement element : elements) {
			if (element instanceof Concept) {
				Concept concept = (Concept) element;
				if (concept.getName() == null || concept.getName().length() == 0) continue;
				variants.add(LookupElementBuilder.create((PsiNamedElement) concept.getIdentifierNode()).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(element)));
			} else {
				Icon icon = (element instanceof PsiPackage) ? AllIcons.Nodes.Package : TaraIcons.ICON_13;
				variants.add(LookupElementBuilder.create((PsiNamedElement) element).withIcon(icon).withTypeText(element.getParent().getText()));
			}
		}
		return variants.toArray();
	}

	private String getFileName(PsiElement concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}