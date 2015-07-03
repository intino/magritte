package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.FileVariantsManager;

import java.util.*;

public class TaraFileReferenceSolver extends TaraReferenceSolver {
	public TaraFileReferenceSolver(HeaderReference element, TextRange range) {
		super(element, range);
	}

	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolve((Identifier) myElement.getLastChild());
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		return ReferenceManager.resolve((Identifier) myElement.getLastChild());
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		final Set<TaraModel> variants = new LinkedHashSet();
		new FileVariantsManager(variants, myElement).resolveVariants();
		return fillVariants(variants);
	}

	public Object[] fillVariants(Collection<TaraModel> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final TaraModel model : variants) {
			if (model == null || model.getName().length() == 0) continue;
			lookupElements.add(LookupElementBuilder.create(model.getPresentableName()).withIcon(TaraIcons.MODEL));
		}
		return lookupElements.toArray();
	}
}
