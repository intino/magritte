package siani.tara.intellij.lang.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaraMetaWordReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	private final Node node;
	private final Variable variable;

	public TaraMetaWordReferenceSolver(PsiElement element, TextRange range, Node node, Variable variable) {
		super(element, range);
		this.node = node;
		this.variable = variable;
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		return new ResolveResult[0];
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		return null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		List<String> variants = new ArrayList<>();
		int children = myElement.getText().length() - myElement.getText().replace(".", "").length();
//		if (children == 0)
//			variants.add(node.getName());
//		else if (children == 1 && myElement.getChildren()[0].getChildren()[0].getText().equals(node.getName()))
//			variants.add(variable.getName());
//		else if (children == 2)
//			variants = ((NodeWord) variable).getWordTypes();
		return fillVariants(variants);
	}

	public Object[] fillVariants(List<String> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		Icon icon = TaraIcons.getIcon(TaraIcons.ICON_13);
		for (final String variant : variants)
			lookupElements.add(LookupElementBuilder.create(variant).withIcon(icon));
		return lookupElements.toArray();
	}
}
