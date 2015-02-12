package siani.tara.intellij.lang.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.resolve.TaraReferenceSolver;
import siani.tara.lang.Node;
import siani.tara.lang.Word;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaraMetaWordReferenceSolver extends TaraReferenceSolver {

	private final Node node;
	private final Word word;

	public TaraMetaWordReferenceSolver(PsiElement element, TextRange range, Node node, Word word) {
		super(element, range);
		this.node = node;
		this.word = word;
	}


	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolve((Identifier) myElement);
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return fillVariants(word.getWordTypes());
	}

	public Object[] fillVariants(List<String> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		Icon icon = TaraIcons.getIcon(TaraIcons.CONCEPT);
		for (final String variant : variants)
			lookupElements.add(LookupElementBuilder.create(variant).withIcon(icon));
		return lookupElements.toArray();
	}
}
