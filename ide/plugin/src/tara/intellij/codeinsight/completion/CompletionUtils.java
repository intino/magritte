package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.JavaCompletionSorting;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.FakePsiElement;
import tara.Language;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Parameter;
import tara.language.semantics.Allow;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;

public class CompletionUtils {

	private final CompletionParameters parameters;
	private final CompletionResultSet resultSet;

	public CompletionUtils(CompletionParameters parameters, CompletionResultSet resultSet) {
		this.parameters = parameters;
		this.resultSet = resultSet;
	}

	public void collectAllowedTypes() {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		if (language == null) return;
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		final Facet inFacet = inFacet(parameters.getPosition());
		List<Allow> allows = language.allows(node == null ? "" : node.resolve().type());
		if (inFacet != null) allows = collectFacetAllows(allows, inFacet.type());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = createLookUps(language.languageName(), allows, inFacet != null ? inFacet : node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	public void collectAllowedFacets() {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().type());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForFacets(language.languageName(), allows);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	public void collectParameters() {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		Collection<Allow> allows = language.allows(node == null ? "" : node.resolve().type());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForParameters(allows, node == null ? Collections.emptyList() : node.parameters());
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private List<Allow> collectFacetAllows(List<Allow> allows, String type) {
		for (Allow allow : allows)
			if (allow instanceof Allow.Facet && ((Allow.Facet) allow).type().equals(type))
				return ((Allow.Facet) allow).allows();
		return Collections.emptyList();
	}

	private Facet inFacet(PsiElement position) {
		final NodeContainer containerOf = TaraPsiImplUtil.getContainerOf((PsiElement) TaraPsiImplUtil.getContainerOf(position));
		return containerOf instanceof Facet ? (Facet) containerOf : null;
	}

	private List<LookupElementBuilder> createLookUps(String language, Collection<Allow> allows, NodeContainer container) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Include).
			map(allow -> createElement(language, (Allow.Include) allow, container)).
			collect(Collectors.toList());
	}


	private List<LookupElementBuilder> buildLookupElementBuildersForFacets(String language, Collection<Allow> allows) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Facet).
			map(allow -> createElement(language, (Allow.Facet) allow, null)). //TODO pasar el container
			collect(Collectors.toList());
	}

	private LookupElementBuilder createElement(String language, Allow.Include allow, NodeContainer container) {
		return create(new FakeElement(allow.type(), (PsiElement) container), lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(language);
	}


	private String lastTypeOf(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf('.') + 1, type.length()) : type;
	}

	private LookupElementBuilder createElement(String language, Allow.Facet allow, NodeContainer container) {
		return create(new FakeElement(allow.type(), (PsiElement) container), lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_13).withCaseSensitivity(true).withTypeText(language);
	}

	private List<LookupElementBuilder> buildLookupElementBuildersForParameters(Collection<Allow> allows, List<Parameter> parameterList) {
		return allows.stream().
			filter(allow -> allow instanceof Allow.Parameter && !contains(parameterList, ((Allow.Parameter) allow).name())).
			map(allow -> createElement((Allow.Parameter) allow)).
			collect(Collectors.toList());
	}

	private boolean contains(List<Parameter> parameters, String name) {
		for (Parameter parameter : parameters) if (name.equals(parameter.name())) return true;
		return false;
	}

	private LookupElementBuilder createElement(Allow.Parameter allow) {
		return create(allow.name() + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(allow.type());
	}

	public static class FakeElement extends FakePsiElement implements NavigatablePsiElement {

		private final String type;
		private PsiElement parent;

		public FakeElement(String type, PsiElement parent) {
			this.type = type;
			this.parent = parent;
		}

		public String getType() {
			return type;
		}

		public void setParent(PsiElement parent) {
			this.parent = parent;
		}

		@Override
		public PsiElement getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return type;
		}

		@Override
		public String getPresentableText() {
			return toString();
		}

		@Override
		public String getName() {
			return toString();
		}
	}
}
