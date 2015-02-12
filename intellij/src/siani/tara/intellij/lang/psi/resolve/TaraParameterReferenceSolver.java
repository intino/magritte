package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.psi.impl.VariantsManager;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;

import java.util.*;

public class TaraParameterReferenceSolver extends TaraReferenceSolver {

	private final Variable variable;
	private final Concept scope;

	public TaraParameterReferenceSolver(PsiElement element, TextRange range, Concept concept, Node node, Variable variable) {
		super(element, range);
		this.variable = variable;
		scope = TaraUtil.findScope(node, concept);
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
		Set<Concept> variants = new LinkedHashSet();
		if (myElement instanceof Identifier)
			new VariantsManager(variants, myElement).resolveVariants();
		else if (myElement instanceof IdentifierReference)
			new VariantsManager(variants, myElement.getLastChild()).resolveVariants();
		if (!variants.isEmpty()) filterVariants(variants);
		return fillVariants(variants);
	}

	private void filterVariants(Set<Concept> variants) {
		List<Concept> toRemove = new ArrayList<>();
		for (Concept variant : variants)
			if (!areCompatibles(variant) || !inScope(variant) || variant.isAnonymous()) toRemove.add(variant);
		variants.removeAll(toRemove);
	}

	private boolean areCompatibles(Concept variant) {
		String metaQualifiedName = variant.getMetaQualifiedName();
		Node metaConcept = TaraUtil.getMetaConcept(variant);
		String type = variable.getType();
		if (metaQualifiedName.equals(type)) return true;
		NodeObject parent = metaConcept.getObject().getParent();
		while (parent != null) {
			if (type.equals(parent.getDeclaredNodeQN())) return true;
			parent = parent.getParent();
		}
		return false;
	}

	private boolean inScope(Concept variant) {
		return scope == null || variant.getQualifiedName().startsWith(scope.getQualifiedName() + ".");
	}

	public Object[] fillVariants(Collection<Concept> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final Concept concept : variants) {
			if (concept == null || concept.getName() == null || concept.getName().length() == 0) continue;
			lookupElements.add(LookupElementBuilder.create(concept.getIdentifierNode()).
				withIcon(TaraIcons.getIcon(TaraIcons.CONCEPT)).withTypeText(concept.getType()));
		}
		return lookupElements.toArray();
	}
}
