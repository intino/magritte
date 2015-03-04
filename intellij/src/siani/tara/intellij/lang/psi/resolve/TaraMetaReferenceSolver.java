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
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;
import static siani.tara.lang.Annotation.COMPONENT;
import static siani.tara.lang.Annotation.ABSTRACT;

public class TaraMetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraMetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		Concept contextOf = getConceptContainerOf(myElement);
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
		String parentModel = ((TaraModel) myElement.getContainingFile()).getDSL();
		if (parentModel == null) return PsiElement.EMPTY_ARRAY;
		List<Variable> variables = null;
		Model metaModel = TaraLanguage.getMetaModel(myElement.getContainingFile());
		if (metaModel == null) return new Object[0];
		if (myElement.getParent() instanceof TaraFacetApply) {
			Node node = TaraUtil.findNode(getConceptContainerOf(myElement), metaModel);
			if (node == null || node.is(ABSTRACT) || node.getSubNodes().length > 0) return PsiElement.EMPTY_ARRAY;
			return fillFacetVariants(node.getObject().getAllowedFacets().keySet());
		} else {
			List<Node> nodes = new ArrayList<>();
			Concept context = getConceptContainerOf(getConceptContainerOf(myElement));
			if (context != null) {
				Node node = TaraUtil.findNode(context, metaModel);
				if (node == null || node.is(ABSTRACT) || node.getSubNodes().length > 0)
					return PsiElement.EMPTY_ARRAY;
				addChildren(nodes, node);
				variables = node.getObject().getVariables();
			} else addRootNodes(nodes, metaModel.getTreeModel());
			Object[] nodeObjects = fillNodeVariants(nodes);
			if (variables == null) return nodeObjects;
			else {
				List<Object> nodesAndVariables = new ArrayList<>(Arrays.asList(nodeObjects));
				Collections.addAll(nodesAndVariables, fillVariables(variables));
				return nodesAndVariables.toArray();
			}
		}
	}

	private void addRootNodes(List<Node> nodeList, NodeTree tree) {
		for (Node node : tree)
			if ((node instanceof DeclaredNode) && !node.getObject().is(COMPONENT)) {
				nodeList.add(node);
				addSubNodes(nodeList, node);
			}
	}

	private void addSubNodes(List<Node> nodeList, Node node) {
		for (Node caseNode : node.getObject().getSubConcepts()) {
			nodeList.add(caseNode);
			addSubNodes(nodeList, caseNode);
		}
	}

	private Object[] fillFacetVariants(Set<String> allowedFacets) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String facet : allowedFacets) {
			String name = facet.substring(facet.lastIndexOf('.') + 1);
			variants.add(LookupElementBuilder.create(name).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13)).withTypeText("Facet"));
		}
		return variants.toArray();
	}

	private void addChildren(List<Node> nodeList, Node node) {
		if (node == null) return;
		for (Node child : node.getInnerNodes())
			if (!child.getObject().is(ABSTRACT)) {
				nodeList.add(child);
				addSubs(nodeList, child);
			}
	}

	private void addSubs(List<Node> nodeList, Node child) {
		for (DeclaredNode sub : child.getSubNodes()) {
			nodeList.add(sub);
			addSubNodes(nodeList, sub);
		}
	}

	private Object[] fillNodeVariants(List<Node> nodes) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Node node : nodes) {
			if (!LinkNode.class.isInstance(node) && node.getName().isEmpty()) continue;
			String name = (node instanceof LinkNode) ? ((LinkNode) node).getDestinyName() : node.getName();
			LookupElementBuilder lookupElementBuilder = LookupElementBuilder.create(name).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13));
			String parent = node.getObject().getParent() != null ? " > " + node.getObject().getParentName() : "";
			variants.add(lookupElementBuilder.withTypeText(node.getObject().getType() + parent));
		}
		return variants.toArray();
	}

	private Object[] fillVariables(List<Variable> variables) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Variable variable : variables) {
			LookupElementBuilder builder = LookupElementBuilder.create(variable.getName()).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13));
			variants.add(builder.withTypeText(variable.getType()));
		}
		return variants.toArray();

	}

}
