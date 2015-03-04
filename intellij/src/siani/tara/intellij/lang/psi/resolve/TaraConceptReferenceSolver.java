package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.psi.impl.VariantsManager;
import siani.tara.lang.LinkNode;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;

import java.util.*;

public class TaraConceptReferenceSolver extends TaraReferenceSolver {


	private final Node node;
	private final Concept scope;

	public TaraConceptReferenceSolver(@NotNull PsiElement element, TextRange range, Concept concept, Node node) {
		super(element, range);
		this.node = node;
		scope = node != null ? TaraUtil.findScope(node, concept) : null;
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
		final Set<Concept> variants = new LinkedHashSet();
		if (isConceptReference()) new VariantsManager(variants, myElement).resolveVariants();
		if (!variants.isEmpty()) filterVariants(variants);
		return fillVariants(variants);
	}

	private void filterVariants(Set<Concept> variants) {
		if (node == null) return;
		List<Concept> toRemove = new ArrayList<>();
		for (Concept variant : variants)
			if (!areCompatibles(variant) || !inScope(variant) || variant.isAnonymous()) toRemove.add(variant);
		variants.removeAll(toRemove);
	}

	private boolean areCompatibles(Concept variant) {
		Node metaConcept = TaraUtil.getMetaConcept(variant);
		return compare(getNodeCandidates(), metaConcept.getObject());
	}

	private boolean compare(Collection<String> types, NodeObject object) {
		if (object == null) return false;
		for (String type : types)
			if (type.equals(object.getDeclaredNodeQN())) return true;
		return compare(types, object.getParent());
	}

	private boolean inScope(Concept variant) {
		return scope == null || variant.getQualifiedName().startsWith(scope.getQualifiedName() + ".");
	}

	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolve((Identifier) myElement);
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

	private boolean isConceptReference() {
		return myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference;
	}

	public Collection<String> getNodeCandidates() {
		List<String> names = new ArrayList<>();
		for (Node inner : node.getInnerNodes())
			names.add(inner.is(LinkNode.class) ? ((LinkNode) inner).getDestiny().getQualifiedName() : inner.getQualifiedName());
		return names;
	}
}