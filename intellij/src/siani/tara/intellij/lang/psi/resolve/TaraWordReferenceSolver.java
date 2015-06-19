package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.VariantsManager;
import siani.tara.semantic.Allow;

import java.util.*;

public class TaraWordReferenceSolver extends TaraReferenceSolver {

	private final Allow.Parameter parameterAllow;

	public TaraWordReferenceSolver(PsiElement element, TextRange range, Allow.Parameter parameterAllow) {
		super(element, range);
		this.parameterAllow = parameterAllow;
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		return myElement;
	}

	@Override
	protected PsiElement doMultiResolve() {
		return myElement;
	}

	@NotNull
	@Override
	public Object[] getVariants() { //TODO
		Set<Node> variants = new LinkedHashSet();
		if (myElement instanceof Identifier)
			new VariantsManager(variants, myElement).resolveVariants();
		else if (myElement instanceof IdentifierReference)
			new VariantsManager(variants, myElement.getLastChild()).resolveVariants();
		return fillVariants(variants);
	}


	public Object[] fillVariants(Collection<Node> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final Node node : variants) {
			if (node == null || node.getName() == null || node.getName().length() == 0) continue;
			lookupElements.add(LookupElementBuilder.create(node.getIdentifierNode()).
				withIcon(TaraIcons.NODE).withTypeText(node.getType()));
		}
		return lookupElements.toArray();
	}
}
