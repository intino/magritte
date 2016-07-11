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
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Parameter;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Documentation;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static com.intellij.openapi.util.io.FileUtil.getNameWithoutExtension;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerByType;
import static tara.intellij.lang.psi.impl.TaraUtil.getLanguage;

public class CompletionUtils {

	private final CompletionParameters parameters;
	private final CompletionResultSet resultSet;

	CompletionUtils(CompletionParameters parameters, CompletionResultSet resultSet) {
		this.parameters = parameters;
		this.resultSet = resultSet;
	}

	void collectAllowedTypes() {
		Language language = getLanguage(parameters.getOriginalFile());
		if (language == null) return;
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		final List<Constraint> nodeConstraints = language.constraints(node == null ? "" : node.resolve().type());
		if (nodeConstraints == null) return;
		List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		if (node != null) constraints.addAll(facetConstraints(node));
		final List<Constraint> components = constraints.stream().filter(c -> c instanceof Constraint.Component && !((Constraint.Component) c).type().contains(":")).collect(Collectors.toList());
		List<LookupElementBuilder> elementBuilders = createComponentLookUps(fileName(language, node), components, node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private List<Constraint> facetConstraints(Node node) {
		List<Constraint> constraints = new ArrayList<>();
		final List<Facet> facets = node.facets();
		facets.stream().forEach(facet -> collectFacetAllows(constraints, facet.type()));
		return constraints;
	}

	void collectAllowedFacets() {
		Language language = getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		List<Constraint> constraints = language.constraints(node == null ? "" : node.resolve().type());
		if (constraints == null || node == null || node.type() == null) return;
		final String fileName = language.doc(node.type()) != null ? getNameWithoutExtension(new File(language.doc(node.type()).file())) : "";
		List<LookupElementBuilder> elementBuilders = buildCompletionForFacets(fileName, constraints, node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	void collectParameters() {
		Language language = getLanguage(parameters.getOriginalFile());
		if (language == null) return;
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		final Facet facet = getContainerByType(parameters.getPosition(), Facet.class);
		List<Constraint> constraints = language.constraints(node == null ? "" : node.resolve().type());
		if (facet != null) constraints = collectFacetAllows(constraints, facet.type());
		if (constraints == null) return;
		List<LookupElementBuilder> elementBuilders = buildCompletionForParameters(constraints, node == null ? Collections.emptyList() : node.parameters());
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	private String fileName(Language language, Node node) {
		final Documentation doc = language.doc(node == null ? null : node.type());
		final String file = doc == null ? null : doc.file();
		return file == null ? "" : getNameWithoutExtension(new File(file));
	}

	private List<Constraint> collectFacetAllows(List<Constraint> constraints, String type) {
		if (constraints == null) return Collections.emptyList();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Facet && ((Constraint.Facet) constraint).type().equals(type))
				return ((Constraint.Facet) constraint).constraints();
		return Collections.emptyList();
	}

	private List<LookupElementBuilder> createComponentLookUps(String fileName, List<Constraint> constraints, NodeContainer container) {
		Set<String> added = new HashSet<>();
		List<LookupElementBuilder> builders = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.OneOf)
				builders.addAll(createElement(fileName, (Constraint.OneOf) constraint, container));
			else builders.add(createElement(fileName, (Constraint.Component) constraint, container));
		return builders.stream().filter(c -> added.add(c.getLookupString())).collect(Collectors.toList());
	}

	private List<LookupElementBuilder> buildCompletionForFacets(String fileName, List<Constraint> constraints, Node node) {
		Set<String> added = new HashSet<>();
		return constraints.stream().
			filter(c -> c instanceof Constraint.Facet && !hasFacet(node, (Constraint.Facet) c)).
			map(c -> createElement(fileName, (Constraint.Facet) c, node)).filter(l -> added.add(l.getLookupString())). //TODO pasar el container
			collect(Collectors.toList());
	}

	private boolean hasFacet(Node node, Constraint.Facet c) {
		for (Facet facet : node.facets()) if (facet.type().equals(c.type())) return true;
		return false;
	}

	private LookupElementBuilder createElement(String fileName, Constraint.Component constraint, NodeContainer container) {
		return create(new FakeElement(constraint.type(), (PsiElement) container), lastTypeOf(constraint.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(fileName);
	}

	private List<LookupElementBuilder> createElement(String fileName, Constraint.OneOf constraint, NodeContainer container) {
		return constraint.components().stream().map(component -> createElement(fileName, (Constraint.Component) component, container)).collect(Collectors.toList());
	}

	private String lastTypeOf(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf('.') + 1, type.length()) : type;
	}

	private LookupElementBuilder createElement(String language, Constraint.Facet allow, NodeContainer container) {
		return create(new FakeElement(allow.type(), (PsiElement) container), lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_16).withCaseSensitivity(true).withTypeText(language);
	}

	private List<LookupElementBuilder> buildCompletionForParameters(List<Constraint> allows, List<Parameter> parameterList) {
		Set<String> added = new HashSet<>();
		return allows.stream().
			filter(allow -> allow instanceof Constraint.Parameter && !contains(parameterList, ((Constraint.Parameter) allow).name())).
			map(allow -> createElement((Constraint.Parameter) allow)).filter(l -> added.add(l.getLookupString())).
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
