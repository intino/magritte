package monet.tara.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.HeaderReference;
import monet.tara.intellij.metamodel.psi.Identifier;
import monet.tara.intellij.metamodel.psi.IdentifierReference;
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
		if (element != null) results.add(new PsiElementResolveResult(element));
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
		if (myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference)
			new VariantsManager(variants, myElement).resolveVariants();
		return fillVariants(variants);
	}

	public Object[] fillVariants(List<PsiElement> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final PsiElement variant : variants) {
			if (variant == null) continue;
			if (variant instanceof Concept) {
				Concept concept = (Concept) variant;
				if (concept.getName() == null || concept.getName().length() == 0) continue;
				lookupElements.add(LookupElementBuilder.create((PsiNamedElement) concept.getIdentifierNode()).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(variant)));
			} else {
				Icon icon = (variant instanceof PsiPackage) ? AllIcons.Nodes.Package : TaraIcons.ICON_13;
				lookupElements.add(LookupElementBuilder.create((PsiNamedElement) variant).withIcon(icon).withTypeText(variant.getParent().getText()));
			}
		}
		return lookupElements.toArray();
	}

	private String getFileName(PsiElement concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}