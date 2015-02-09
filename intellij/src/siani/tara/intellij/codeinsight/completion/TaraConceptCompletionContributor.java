package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;

public class TaraConceptCompletionContributor extends CompletionContributor {


	public TaraConceptCompletionContributor() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody,
			new CompletionProvider<CompletionParameters>() {

				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					Concept concept = getConceptContainerOf(parameters.getPosition());
					if (parameters.getPosition().getContext() instanceof MetaIdentifier && concept != null)
						addMetaIdentifiers(concept, resultSet);
					resultSet.addElement(create("has "));
					resultSet.addElement(create("sub "));
					resultSet.addElement(create("var "));
					Concept container = getConceptContainerOf(concept);
					if (container == null) return;
					Node node = TaraUtil.getMetaConcept(container);
					if (container.isFacet() || node.is(Annotation.META_FACET)) resultSet.addElement(create("on "));
					if (node != null && !node.getObject().getAllowedFacets().isEmpty() && !areAlreadyApplied(node.getObject().getAllowedFacets(), container))
						resultSet.addElement(create("as "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.inFacetBody,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("on "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.AfterNewLineNoMetamodel,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("Concept "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.AfterNewLineWithMetamodel,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					List<LookupElementBuilder> elementBuilders = new ArrayList<>();
					if (parameters.getPosition().getContext() instanceof MetaIdentifier) {
						Concept concept = getConceptContainerOf(parameters.getPosition());
						Model model = TaraUtil.getMetamodel(concept);
						for (Node node : model.getTreeModel()) {
							if (node.getName() == null) continue;
							LookupElementBuilder element = create(node.getName()).withIcon(TaraIcons.getIcon(TaraIcons.CONCEPT)).withCaseSensitivity(false);
							String parentName = node.getObject().getParentName();
							if (parentName != null) element = element.appendTailText(":" + parentName, true);
							elementBuilders.add(element.withTypeText(model.getName()));
						}
					}
					resultSet.addAllElements(elementBuilders);
					JavaCompletionSorting.addJavaSorting(parameters, resultSet);
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterIdentifier,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("extends "));
				}
			}
		);
	}

	private boolean areAlreadyApplied(Map<String, List<FacetTarget>> allowedFacets, Concept container) {
		boolean found;
		for (String facet : allowedFacets.keySet()) {
			found = false;
			for (FacetApply facetApply : container.getFacetApplies())
				if (facet.equals(facetApply.getFacetName())) {
					found = true;
					break;
				}
			if (!found) return false;
		}
		return true;
	}

	private void addMetaIdentifiers(Concept concept, CompletionResultSet resultSet) {
		Concept container = getConceptContainerOf(concept);
		if (container == null) return;
		Node metaConcept = TaraUtil.getMetaConcept(container);
		Map<Node, LookupElementBuilder> candidates = new LinkedHashMap<>();
		if (metaConcept == null) return;
		for (Node node : metaConcept.getInnerNodes()) {
			if (node.is(DeclaredNode.class) && node.isSub()) continue;
			if (node.isAnonymous() || node.is(Annotation.PROPERTY) || node.getName() == null) continue;
			if (node.is(Annotation.REQUIRED) && container.contains(node.getName())) continue;
			if (node.isAbstract())
				for (DeclaredNode declaredNode : node.getSubConcepts())
					candidates.put(declaredNode, createElement(declaredNode));
			else candidates.put(node, createElement(node));
		}
		Model model = TaraUtil.getMetamodel(container);
		for (Map.Entry<Node, LookupElementBuilder> entry : candidates.entrySet()) {
			LookupElementBuilder element = entry.getValue().withIcon(TaraIcons.getIcon(TaraIcons.CONCEPT)).withCaseSensitivity(false);
			String parentName = entry.getKey().getObject().getParentName();
			if (parentName != null) element = element.appendTailText(":" + parentName, true);
			resultSet.addElement(element.withTypeText(model.getName()));
		}
	}

	private LookupElementBuilder createElement(Node node) {
		if (node.getObject().getVariables().isEmpty())
			return create(node.getName());
		return create(node.getName() + "()");
	}
}