package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
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
		bodyCompletion();
		facetCompletion();
		newLine();
		newLineWithMetamodel();
		afterIdentifier();
	}

	private void bodyCompletion() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody, new BodyCompletionProvider());
	}

	private void facetCompletion() {
		extend(CompletionType.BASIC, TaraFilters.inFacetBody,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("on "));
				}
			}
		);
	}

	private void newLine() {
		extend(CompletionType.BASIC, TaraFilters.AfterNewLineNoMetamodel,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("Concept "));
				}
			}
		);
	}

	private void newLineWithMetamodel() {
		extend(CompletionType.BASIC, TaraFilters.AfterNewLineWithMetamodel,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					List<LookupElementBuilder> elementBuilders = new ArrayList<>();
					if (parameters.getPosition().getContext() instanceof MetaIdentifier) {
						Model model = TaraUtil.getMetamodel(parameters.getOriginalFile());
						if (model == null) return;
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
	}

	private void afterIdentifier() {
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

	private void addMetaIdentifiers(PsiFile originalFile, Concept concept, CompletionResultSet resultSet) {
		Concept container = getConceptContainerOf(concept);
		if (container == null) return;
		Model metamodel = TaraLanguage.getMetaModel(originalFile);
		if (metamodel == null) {
			resultSet.addElement(create("Concept "));
			return;
		}
		Node metaConcept = TaraUtil.findNode(container, metamodel);
		Map<Node, LookupElementBuilder> candidates = new LinkedHashMap<>();
		if (metaConcept == null) return;
		for (Node node : metaConcept.getInnerNodes()) {
			if (isCandidate(container, node)) continue;
			if (node.isAbstract())
				addSubNodes(candidates, node);
			else candidates.put(node, createElement(node));
		}
		buildEntries(resultSet, metamodel, candidates);
	}

	private void addSubNodes(Map<Node, LookupElementBuilder> candidates, Node node) {
		for (DeclaredNode declaredNode : node.getDeepSubNodes())
			if (!declaredNode.is(Annotation.ABSTRACT))
				candidates.put(declaredNode, createElement(declaredNode));
	}

	private void buildEntries(CompletionResultSet resultSet, Model metaModel, Map<Node, LookupElementBuilder> candidates) {
		for (Map.Entry<Node, LookupElementBuilder> entry : candidates.entrySet()) {
			LookupElementBuilder element = entry.getValue().withIcon(TaraIcons.getIcon(TaraIcons.CONCEPT)).withCaseSensitivity(false);
			String parentName = entry.getKey().getObject().getParentName();
			if (parentName != null) element = element.appendTailText(":" + parentName, true);
			resultSet.addElement(element.withTypeText(metaModel.getName()));
		}
	}

	private boolean isCandidate(Concept container, Node node) {
		if (node.is(DeclaredNode.class) && node.isSub()) return true;
		if (node.isAnonymous() || node.getName() == null) return true;
		return node.is(Annotation.REQUIRED) && container.contains(node.getName());
	}

	private LookupElementBuilder createElement(Node node) {
		if (node.getObject().getVariables().isEmpty())
			return create(node.getName());
		return create(node.getName() + "()");
	}

	private class BodyCompletionProvider extends CompletionProvider<CompletionParameters> {

		public void addCompletions(@NotNull CompletionParameters parameters,
		                           ProcessingContext context,
		                           @NotNull CompletionResultSet resultSet) {
			Concept concept = getConceptContainerOf(parameters.getPosition());
			if (parameters.getPosition().getContext() instanceof MetaIdentifier && concept != null)
				addMetaIdentifiers(parameters.getOriginalFile(), (Concept) concept.getOriginalElement(), resultSet);
			addKeywords(resultSet);
			Concept container = getConceptContainerOf(concept);
			Node node = findContainerNode(container);
			if (node == null) return;
			addFacetAlternatives(resultSet, container, node);
		}

		private void addFacetAlternatives(CompletionResultSet resultSet, Concept container, Node node) {
			if (container.isFacet() || node.is(Annotation.META_FACET)) resultSet.addElement(create("on "));
			if (!node.getObject().getAllowedFacets().isEmpty() && !areAlreadyApplied(node.getObject().getAllowedFacets(), container))
				resultSet.addElement(create("as "));
		}

		private void addKeywords(CompletionResultSet resultSet) {
			resultSet.addElement(create("has "));
			resultSet.addElement(create("sub "));
			resultSet.addElement(create("var "));
		}

		private Node findContainerNode(Concept container) {
			if (container == null) return null;
			return TaraUtil.getMetaConcept(container);
		}
	}
}