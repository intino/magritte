package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;

import java.util.*;

public class TaraNodeReferenceSolver extends TaraReferenceSolver {

	public TaraNodeReferenceSolver(@NotNull PsiElement element, TextRange range, Node node) {
		super(element, range);
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
		final Set<Node> variants = new LinkedHashSet();
		if (isNodeReference()) new VariantsManager(variants, myElement).resolveVariants();
		return fillVariants(variants);
	}

	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolve((Identifier) myElement);
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

	private boolean isNodeReference() {
		return myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference;
	}
}