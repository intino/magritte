package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.JavaCompletionSorting;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import tara.Language;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Parameter;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.semantics.Allow;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;

public interface CompletionUtils {

	default void collectAllowedTypes(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForIncludes(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	default List<LookupElementBuilder> buildLookupElementBuildersForIncludes(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Include).
			map(allow -> createElement(language, (Allow.Include) allow)).
			collect(Collectors.toList());
	}

	default LookupElementBuilder createElement(String language, Allow.Include allow) {
		return create(lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(language);
	}

	default String lastTypeOf(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1, type.length()) : type;
	}


	default void collectAllowedFacets(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForFacets(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	default List<LookupElementBuilder> buildLookupElementBuildersForFacets(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Facet).
			map(allow -> createElement(language, (Allow.Facet) allow)).
			collect(Collectors.toList());
	}


	default LookupElementBuilder createElement(String language, Allow.Facet allow) {
		return create(lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_13).withCaseSensitivity(true).withTypeText(language);
	}

	default void collectParameters(CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().getFullType());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForParameters(allows, node == null ? Collections.emptyList() : node.getParameterList());
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	default List<LookupElementBuilder> buildLookupElementBuildersForParameters(Collection<Allow> allows, List<Parameter> parameterList) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Parameter && !contains(parameterList, ((Allow.Parameter) allow).name())).
			map(allow -> createElement((Allow.Parameter) allow)).
			collect(Collectors.toList());
	}

	default boolean contains(List<Parameter> parameters, String name) {
		for (Parameter parameter : parameters) if (name.equals(parameter.getName())) return true;
		return false;
	}

	default LookupElementBuilder createElement(Allow.Parameter allow) {
		return create(allow.name() + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText("var");
	}
}
