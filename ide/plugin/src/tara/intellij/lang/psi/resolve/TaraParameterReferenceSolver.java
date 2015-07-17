package tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.IdentifierReference;

import java.util.*;

public class TaraParameterReferenceSolver extends TaraReferenceSolver {

	public TaraParameterReferenceSolver(PsiElement element, TextRange range, Node node) {
		super(element, range);
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
			if (node == null || node.name() == null || node.name().length() == 0) continue;
			lookupElements.add(LookupElementBuilder.create(node.getIdentifierNode()).
				withIcon(TaraIcons.NODE).withTypeText(node.type()));
		}
		return lookupElements.toArray();
	}
}