package monet.::projectName::.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.HeaderReference;
import monet.::projectName::.intellij.lang.psi.Identifier;
import monet.::projectName::.intellij.lang.psi.IdentifierReference;
import monet.::projectName::.intellij.lang.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ::projectProperName::ReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	private final boolean external;

	public ::projectProperName::ReferenceSolver(\@NotNull PsiElement element, TextRange textRange, boolean external) {
		super(element, textRange);
		this.external = external;
	}

	\@NotNull
	\@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = ReferenceManager.resolve((Identifier) myElement, external);
		if (element != null) results.add(new PsiElementResolveResult(element));
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
		List<PsiElement> variants = new ArrayList<>();
		if (myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference)
			new VariantsManager(variants, myElement).resolveVariants();
		return fillVariants(variants);
	}

	public Object[] fillVariants(List<PsiElement> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final PsiElement variant \: variants) {
			if (variant == null) continue;
			if (variant instanceof Definition) {
				Definition definition = (Definition) variant;
				if (definition.getName() == null || definition.getName().length() == 0) continue;
				lookupElements.add(LookupElementBuilder.create((PsiNamedElement) definition.getIdentifierNode()).withIcon(::projectProperName::Icons.getIcon(::projectProperName::Icons.ICON_13)).withTypeText(getFileName(variant)));
			} else {
				Icon icon = (variant instanceof PsiPackage) ? AllIcons.Nodes.Package \: ::projectProperName::Icons.getIcon(::projectProperName::Icons.ICON_13);
				lookupElements.add(LookupElementBuilder.create((PsiNamedElement) variant).withIcon(icon).withTypeText(variant.getParent().getText()));
			}
		}
		return lookupElements.toArray();
	}

	private String getFileName(PsiElement definition) {
		return definition.getContainingFile().getName().split("\\\\.")[0];
	}
}