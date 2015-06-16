package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.model.FacetTarget;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class TaraNodeCompletionContributor extends CompletionContributor {

	public TaraNodeCompletionContributor() {
		bodyCompletion();
		facetCompletion();
		newLine();
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
		extend(CompletionType.BASIC, TaraFilters.AfterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
					collectAllowedTypes(parameters, resultSet);
				}
			}
		);
	}

	private void collectAllowedTypes(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = getContainerNodeOf(getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		List<LookupElementBuilder> elementBuilders = getLookupElementBuilders(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private List<LookupElementBuilder> getLookupElementBuilders(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Include).
			map(allow -> createElement(language, (Allow.Include) allow)).
			collect(Collectors.toList());
	}

	private LookupElementBuilder createElement(String language, Allow.Include allow) {
		return create(lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.CONCEPT).withCaseSensitivity(false).withTypeText(language);
	}

	private String lastTypeOf(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1, type.length()) : type;
	}

	private void afterIdentifier() {
		extend(CompletionType.BASIC, TaraFilters.afterNodeIdentifier,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(create("extends "));
				}
			}
		);
	}

	private class BodyCompletionProvider extends CompletionProvider<CompletionParameters> {

		public void addCompletions(@NotNull CompletionParameters parameters,
		                           ProcessingContext context,
		                           @NotNull CompletionResultSet resultSet) {
			if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
			collectAllowedTypes(parameters, resultSet);
			addKeywords(resultSet);
			addFacetAlternatives(parameters, resultSet);
		}

		private void addFacetAlternatives(@NotNull CompletionParameters parameters, CompletionResultSet resultSet) {
			Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
			Node node = getContainerNodeOf(getContainerNodeOf(parameters.getPosition()));
			Collection<Assumption> assumptions = language.assumptions(node == null ? "" : node.resolve().getFullType());
			for (Assumption assumption : assumptions) {
				if (assumption instanceof Assumption.FacetInstance && !areAlreadyApplied(Collections.EMPTY_MAP, node)) //TODO find allowedFacets of node
					resultSet.addElement(create("as "));
				if (assumption instanceof Assumption.Facet)
					resultSet.addElement(create("on "));

			}
		}

		private void addKeywords(CompletionResultSet resultSet) {
			resultSet.addElement(create("has "));
			resultSet.addElement(create("sub "));
			resultSet.addElement(create("var "));
		}

		private boolean areAlreadyApplied(Map<String, List<FacetTarget>> allowedFacets, Node container) {
			boolean found;
			for (String facet : allowedFacets.keySet()) {
				found = false;
				for (FacetApply facetApply : container.getFacetApplies())
					if (facet.equals(facetApply.getType())) {
						found = true;
						break;
					}
				if (!found) return false;
			}
			return true;
		}
	}
}