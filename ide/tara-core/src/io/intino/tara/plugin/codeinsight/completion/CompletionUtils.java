package io.intino.tara.plugin.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.JavaCompletionSorting;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.FakePsiElement;
import io.intino.tara.Language;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeContainer;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Documentation;
import io.intino.tara.plugin.lang.TaraIcons;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static com.intellij.openapi.util.io.FileUtil.getNameWithoutExtension;
import static io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static io.intino.tara.plugin.lang.psi.impl.TaraUtil.parameterConstraintsOf;
import static java.util.stream.Collectors.toList;

public class CompletionUtils {

	private final CompletionParameters parameters;
	private final CompletionResultSet resultSet;
	private final Language language;

	CompletionUtils(CompletionParameters parameters, CompletionResultSet resultSet) {
		this.parameters = parameters;
		this.resultSet = resultSet;
		language = TaraUtil.getLanguage(parameters.getOriginalFile());

	}

	void collectAllowedTypes() {
		if (language == null) return;
		Node container = getContainerNodeOf((PsiElement) getContainerNodeOf(parameters.getPosition()));
		final List<Constraint> nodeConstraints = language.constraints(container == null ? "" : container.resolve().type());
		if (nodeConstraints == null) return;
		List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		if (container != null) constraints.addAll(facetConstraints(nodeConstraints, container.facets()));
		List<Constraint.Component> components = constraints.stream().filter(c -> c instanceof Constraint.Component).map(c -> (Constraint.Component) c).collect(toList());
		components = components.stream().filter(c -> isSizeAccepted(c, container)).collect(toList());
		if (components.isEmpty()) return;
		List<LookupElementBuilder> elementBuilders = createComponentLookUps(fileName(language, container), components, container);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	void collectAllowedFacets() {
		Node node = getContainerNodeOf(parameters.getPosition().getContext());
		if (language == null) return;
		List<Constraint> constraints = language.constraints(node == null ? "" : node.resolve().type());
		if (constraints == null || node == null || node.type() == null) return;
		final String fileName = language.doc(node.type()) != null ? getNameWithoutExtension(new File(language.doc(node.type()).file())) : "";
		List<LookupElementBuilder> elementBuilders = buildCompletionForFacets(fileName, constraints, node);
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}

	void collectParameters() {
		if (language == null) return;
		Node node = getContainerNodeOf((PsiElement) getContainerNodeOf(parameters.getPosition()));
		if (node == null) return;
		List<LookupElementBuilder> elementBuilders = buildCompletionForParameters(parameterConstraintsOf(node), node.parameters());
		resultSet.addAllElements(elementBuilders);
		JavaCompletionSorting.addJavaSorting(parameters, resultSet);
	}


	private boolean isSizeAccepted(Constraint.Component component, Node container) {
		return component.rules().stream().filter(r -> r instanceof Size).allMatch(r -> ((Size) r).max() > container.components().stream().filter(c -> component.type().equals(c.type())).collect(toList()).size());
	}

	private String fileName(Language language, Node node) {
		final Documentation doc = language.doc(node == null ? null : node.type());
		final String file = doc == null ? null : doc.file();
		return file == null ? "" : getNameWithoutExtension(new File(file));
	}

	private List<Constraint> facetConstraints(List<Constraint> nodeConstraints, List<Facet> facets) {
		List<String> facetTypes = facets.stream().map(Facet::type).collect(toList());
		List<Constraint> list = new ArrayList<>();
		if (nodeConstraints == null) return list;
		for (Constraint constraint : nodeConstraints)
			if (constraint instanceof Constraint.Facet && facetTypes.contains(((Constraint.Facet) constraint).type())) list.add(constraint);
		return list;
	}

	private List<LookupElementBuilder> createComponentLookUps(String fileName, List<Constraint.Component> constraints, NodeContainer container) {
		Set<String> added = new HashSet<>();
		List<LookupElementBuilder> builders = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.OneOf)
				builders.addAll(createElement(fileName, (Constraint.OneOf) constraint, container));
			else builders.add(createElement(fileName, (Constraint.Component) constraint, container));
		return builders.stream().filter(c -> added.add(c.getLookupString())).collect(toList());
	}

	private List<LookupElementBuilder> buildCompletionForFacets(String fileName, List<Constraint> constraints, Node node) {
		Set<String> added = new HashSet<>();
		return constraints.stream().
				filter(c -> c instanceof Constraint.Facet && !hasFacet(node, (Constraint.Facet) c)).
				map(c -> createElement(fileName, (Constraint.Facet) c, node)).filter(l -> added.add(l.getLookupString())). //TODO pasar el container
				collect(toList());
	}

	private boolean hasFacet(Node node, Constraint.Facet c) {
		for (Facet facet : node.facets()) if (facet.type().equals(c.type())) return true;
		return false;
	}

	private LookupElementBuilder createElement(String fileName, Constraint.Component constraint, NodeContainer container) {
		return create(new FakeElement(constraint.type(), (PsiElement) container), lastTypeOf(constraint.type()) + " ").withIcon(TaraIcons.NODE).withCaseSensitivity(true).withTypeText(fileName);
	}

	private List<LookupElementBuilder> createElement(String fileName, Constraint.OneOf constraint, NodeContainer container) {
		return constraint.components().stream().map(component -> createElement(fileName, component, container)).collect(toList());
	}

	private String lastTypeOf(String fullType) {
		String[] splittedType = fullType.split(":");
		String type = splittedType[splittedType.length - 1];
		return type.contains(".") ? type.substring(type.lastIndexOf('.') + 1, type.length()) : type;
	}

	private LookupElementBuilder createElement(String language, Constraint.Facet allow, NodeContainer container) {
		return create(new FakeElement(allow.type(), (PsiElement) container), lastTypeOf(allow.type()) + " ").withIcon(TaraIcons.ICON_16).withCaseSensitivity(true).withTypeText(language);
	}

	private List<LookupElementBuilder> buildCompletionForParameters(List<Constraint.Parameter> constraints, List<Parameter> parameterList) {
		Set<String> added = new HashSet<>();
		return constraints.stream().
				filter(c -> c != null && !contains(parameterList, c.name())).
				map(c -> createElement(c)).filter(l -> added.add(l.getLookupString())).
				collect(toList());
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

		FakeElement(String type, PsiElement parent) {
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
