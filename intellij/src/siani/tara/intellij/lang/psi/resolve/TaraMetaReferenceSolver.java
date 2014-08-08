package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOf;

public class TaraMetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraMetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		Concept contextOf = getContextOf(myElement);
		if (contextOf != null)
			results.add(new PsiElementResolveResult(contextOf));
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
		if (!MetaIdentifier.class.isInstance(myElement)) return PsiElement.EMPTY_ARRAY;
		String parentModel = ((TaraFile) myElement.getContainingFile()).getParentModel();
		if (parentModel == null) return PsiElement.EMPTY_ARRAY;
		List<Node> nodes = new ArrayList<>();
		Model metaModel = TaraLanguage.getMetaModel(parentModel);
		if (metaModel == null) return new Object[0];
		if (myElement.getParent() instanceof TaraFacetApply) {
			Node node = metaModel.searchNode(TaraUtil.getMetaQualifiedName(getContextOf(myElement)));
			if (node == null) return PsiElement.EMPTY_ARRAY;
			return fillFacetVariants(node.getObject().getAllowedFacets().keySet());
		} else {
			Concept context = getContextOf(getContextOf(myElement));
			if (context != null) {
				Node node = metaModel.searchNode(TaraUtil.getMetaQualifiedName(context));
				addChildren(nodes, node);
			} else addRootNodes(nodes, metaModel.getTreeModel());
			return fillVariants(nodes);
		}
	}

	private void addRootNodes(List<Node> nodeList, NodeTree tree) {
		for (Node node : tree)
			if (node instanceof IntentionNode || node.getObject().is(ModelObject.AnnotationType.ROOT))
				nodeList.add(node);
	}


	private Object[] fillFacetVariants(Set<String> allowedFacets) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String facet : allowedFacets) {
			String name = facet.substring(facet.lastIndexOf(".") + 1);
			variants.add(LookupElementBuilder.create(name).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13)).withTypeText("Facet"));
		}
		return variants.toArray();
	}

	private void addChildren(List<Node> nodeList, Node node) {
		if (node == null) return;
		for (Node child : node.getInnerNodes())
			if (!child.getObject().is(NodeObject.AnnotationType.PRIVATE))
				nodeList.add(child);
	}

	private Object[] fillVariants(List<Node> nodes) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Node node : nodes) {
			if (!LinkNode.class.isInstance(node) && node.getName().isEmpty()) continue;
			String name = (node instanceof LinkNode) ? ((LinkNode) node).getDestinyName() : node.getName();
			LookupElementBuilder lookupElementBuilder = LookupElementBuilder.create(name).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13));
			variants.add(node instanceof IntentionNode ? lookupElementBuilder.withTypeText("Intention") : lookupElementBuilder.withTypeText(node.getObject().getType()));
		}
		return variants.toArray();
	}
}
