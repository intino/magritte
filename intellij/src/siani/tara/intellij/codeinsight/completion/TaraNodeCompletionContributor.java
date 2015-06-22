package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
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
		newLine();
		afterAs();
		afterIdentifier();
	}

	private void bodyCompletion() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody, new BodyCompletionProvider());
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

	private void afterAs() {
		extend(CompletionType.BASIC, TaraFilters.afterAs,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
					collectAllowedFacets(parameters, resultSet);
				}
			}
		);
	}

	private void collectAllowedFacets(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForFacets(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private void collectAllowedTypes(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = getContainerNodeOf(getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForIncludes(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private List<LookupElementBuilder> buildLookupElementBuildersForIncludes(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Include).
			map(allow -> createElement(language, (Allow.Include) allow)).
			collect(Collectors.toList());
	}

	private List<LookupElementBuilder> buildLookupElementBuildersForFacets(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Facet).
			map(allow -> createElement(language, (Allow.Facet) allow)).
			collect(Collectors.toList());
	}

	private LookupElementBuilder createElement(String language, Allow.Include allow) {
		return create(lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(language);
	}

	private LookupElementBuilder createElement(String language, Allow.Facet allow) {
		return create(lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_13).withCaseSensitivity(true).withTypeText(language);
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
					resultSet.addElement(create("is "));
					resultSet.addElement(create("into "));
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
			if (!inFacetApply(parameters.getPosition().getContext())) {
				addKeywords(resultSet);
				addFacetAlternatives(parameters, resultSet);
			}
		}

		private boolean inFacetApply(PsiElement context) {
			return TaraPsiImplUtil.getContextOf(context) instanceof FacetApply;
		}

		private void addFacetAlternatives(@NotNull CompletionParameters parameters, CompletionResultSet resultSet) {
			Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
			Node node = getContainerNodeOf(getContainerNodeOf(parameters.getPosition()));
			Collection<Assumption> assumptions = language != null ? language.assumptions(node == null ? "" : node.resolve().getFullType()) : null;
			if (assumptions == null) return;
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