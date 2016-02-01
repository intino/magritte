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
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Parameter;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Documentation;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static com.intellij.openapi.util.io.FileUtil.getNameWithoutExtension;

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
		List<Constraint> constraints = language.constraints(node == null ? "" : node.resolve().type());
		if (inFacet != null) constraints = collectFacetAllows(constraints, inFacet.type());
		if (constraints == null) return;
		List<LookupElementBuilder> elementBuilders = createLookUps(fileName(language, node), constraints, inFacet != null ? inFacet : node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	public String fileName(Language language, Node node) {
		final Documentation doc = language.doc(node == null ? null : node.type());
		final String file = doc == null ? null : doc.file();
		return file == null ? "" : getNameWithoutExtension(new File(file));
	}

	public void collectAllowedFacets() {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		List<Constraint> constraints = language.constraints(node == null ? "" : node.resolve().type());
		if (constraints == null) return;
		final String fileName = getNameWithoutExtension(new File(language.doc(node == null ? null : node.type()).file()));
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForFacets(fileName, constraints, node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	public void collectParameters() {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		if (language == null) return;
		List<Constraint> allows = language.constraints(node == null ? "" : node.resolve().type());
		if (allows == null) return;
		List<LookupElementBuilder> elementBuilders = buildLookupElementBuildersForParameters(allows, node == null ? Collections.emptyList() : node.parameters());
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private List<Constraint> collectFacetAllows(List<Constraint> constraints, String type) {
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Facet && ((Constraint.Facet) constraint).type().equals(type))
				return ((Constraint.Facet) constraint).constraints();
		return Collections.emptyList();
	}

	private Facet inFacet(PsiElement position) {
		final NodeContainer containerOf = TaraPsiImplUtil.getContainerOf((PsiElement) TaraPsiImplUtil.getContainerOf(position));
		return containerOf instanceof Facet ? (Facet) containerOf : null;
	}

	private List<LookupElementBuilder> createLookUps(String fileName, List<Constraint> constraints, NodeContainer container) {
		return constraints.stream().
			filter(c -> c instanceof Constraint.Component && !((Constraint.Component) c).type().contains(":")).
			map(allow -> createElement(fileName, (Constraint.Component) allow, container)).
			collect(Collectors.toList());
	}


	private List<LookupElementBuilder> buildLookupElementBuildersForFacets(String fileName, List<Constraint> allows, Node node) {
		return allows.stream().
			filter(allow -> allow instanceof Constraint.Facet).
			map(allow -> createElement(fileName, (Constraint.Facet) allow, node)). //TODO pasar el container
			collect(Collectors.toList());
	}

	private LookupElementBuilder createElement(String fileName, Constraint.Component constraint, NodeContainer container) {
		return create(new FakeElement(constraint.type(), (PsiElement) container), lastTypeOf(constraint.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(fileName);
	}


	private String lastTypeOf(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf('.') + 1, type.length()) : type;
	}

	private LookupElementBuilder createElement(String language, Constraint.Facet allow, NodeContainer container) {
		return create(new FakeElement(allow.type(), (PsiElement) container), lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_16).withCaseSensitivity(true).withTypeText(language);
	}

	private List<LookupElementBuilder> buildLookupElementBuildersForParameters(List<Constraint> allows, List<Parameter> parameterList) {
		return allows.stream().
			filter(allow -> allow instanceof Constraint.Parameter && !contains(parameterList, ((Constraint.Parameter) allow).name())).
			map(allow -> createElement((Constraint.Parameter) allow)).
			collect(Collectors.toList());
	}

	private boolean contains(List<Parameter> parameters, String name) {
		for (Parameter parameter : parameters) if (name.equals(parameter.name())) return true;
		return false;
	}

	private LookupElementBuilder createElement(Constraint.Parameter allow) {
		return create(allow.name() + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(allow.type().getName());
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
