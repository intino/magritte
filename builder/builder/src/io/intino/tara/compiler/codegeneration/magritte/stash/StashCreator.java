package io.intino.tara.compiler.codegeneration.magritte.stash;

import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.Language;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.io.Concept;
import io.intino.tara.io.*;
import io.intino.tara.io.Node;
import io.intino.tara.io.Variable;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Primitive.*;
import static io.intino.tara.lang.model.Tag.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.compiler.shared.Configuration.Level.System;

public class StashCreator {

	private final List<io.intino.tara.lang.model.Node> nodes;
	private final Language language;
	private final File resourceFolder;
	private final CompilerConfiguration.Level level;
	private final boolean test;
	private final Stash stash = new Stash();
	private final String generatedLanguage;
	private final String workingPackage;

	public StashCreator(List<io.intino.tara.lang.model.Node> nodes, Language language, String genLanguage, CompilerConfiguration conf) {
		this.nodes = nodes;
		this.language = language;
		this.generatedLanguage = Format.javaValidName().format(genLanguage).toString();
		this.workingPackage = conf.workingPackage();
		this.resourceFolder = conf.resourcesDirectory();
		this.level = conf.level();
		this.test = conf.isTest();
		this.stash.language = language.languageName();
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		stash.contentRules = collectContents(nodes.stream().filter(node -> !node.is(Component) && !node.isFacet() && !node.is(Instance)).collect(Collectors.toList()));
		return stash;
	}

	private void create(io.intino.tara.lang.model.Node node, Concept container) {
		if (!node.isReference()) asNode(node, container);
	}

	private void asNode(io.intino.tara.lang.model.Node node, Concept container) {
		if (node.is(Instance))
			if (container == null) stash.nodes.add(createInstance(node));
			else container.nodes.add(createInstance(node));
		else createConcept(node);
	}

	private void createConcept(io.intino.tara.lang.model.Node node) {
		if (node.facetTarget() != null) stash.concepts.addAll(create(node.facetTarget(), node));
		else {
			List<io.intino.tara.lang.model.Node> nodeList = collectTypeComponents(node.components());
			Concept concept = Helper.newConcept(name(node),
					node.isAbstract() || node.isFacet(), node.type().equals(ProteoConstants.META_CONCEPT),
					node.container() instanceof Model && !node.is(Tag.Component),
					node.name() != null && !node.name().isEmpty() ? NameFormatter.getStashQn(node, workingPackage) : null,
					node.parentName() != null ? Format.qualifiedName().format(node.parent().cleanQn()).toString() : null,
					StashHelper.collectTypes(node),
					collectContents(nodeList),
					variablesOf(node),
					parametersOf(node),
					emptyList());
			stash.concepts.add(concept);
			for (io.intino.tara.lang.model.Node component : node.components()) create(component, concept);
		}
	}

	private List<Concept> create(FacetTarget facetTarget, io.intino.tara.lang.model.Node owner) {
		List<io.intino.tara.lang.model.Node> components = collectTypeComponents(owner.components());
		List<Concept> concepts = new ArrayList<>();
		final Concept concept = new Concept();
		concepts.add(concept);
		concept.isMetaConcept = owner.type().equals(ProteoConstants.META_CONCEPT);
		concept.isAbstract = owner.isAbstract();
		concept.name = name(owner);
		concept.className = NameFormatter.getQn(facetTarget, owner, workingPackage);
		concept.types = StashHelper.collectTypes(facetTarget, language.constraints(owner.type()));
		concept.parent = calculateParent(facetTarget);
		concept.contentRules = collectContents(components);
		concept.variables = variablesOf(owner);
		concept.parameters = parametersOf(owner);
		for (io.intino.tara.lang.model.Node component : owner.components()) create(component, concept);
		concepts.addAll(collectChildren(facetTarget.targetNode()).stream().
				map(node -> createChildFacetType(facetTarget, node, concept)).
				collect(toList()));
		return concepts;
	}

	private String name(io.intino.tara.lang.model.Node owner) {
		return Format.withDollar().format(Format.noPackage().format(NameFormatter.getStashQn(owner, workingPackage))).toString();
	}

	private List<io.intino.tara.lang.model.Node> collectChildren(io.intino.tara.lang.model.Node parent) {
		Set<io.intino.tara.lang.model.Node> set = new HashSet<>();
		for (io.intino.tara.lang.model.Node child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private String calculateParent(FacetTarget facetTarget) {
		if (facetTarget.parent() != null) return facetTarget.parent().cleanQn();
		if (facetTarget.owner().parent() != null) return facetTarget.owner().parent().cleanQn();
		else return null;
	}

	private Concept createChildFacetType(FacetTarget facetTarget, io.intino.tara.lang.model.Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = facetTarget.owner().name() + "#" + name(node);
		child.parent = parent.name;
		child.isAbstract = facetTarget.owner().isAbstract();
		child.className = NameFormatter.getQn(facetTarget, facetTarget.owner(), workingPackage);
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.contentRules = parent.contentRules;
		return child;
	}

	private List<io.intino.tara.lang.model.Node> collectTypeComponents(List<io.intino.tara.lang.model.Node> nodes) {
		return nodes.stream().filter(component -> !(component.is(Instance))).collect(toList());
	}

	private List<Concept.Content> collectContents(List<io.intino.tara.lang.model.Node> nodes) {
		return nodes.stream().
				filter(node -> !node.isFacet() && !node.is(Instance)).
				map(n -> new Concept.Content(n.isReference() ? n.destinyOfReference().cleanQn() : n.cleanQn(), n.container().sizeOf(n).min(), n.container().sizeOf(n).max())).collect(Collectors.toList());
	}

	private List<Node> createInstances(List<io.intino.tara.lang.model.Node> nodes) {
		return nodes.stream().map(this::createInstance).collect(toList());
	}

	private Node createInstance(io.intino.tara.lang.model.Node node) {
		Node instanceNode = new Node();
		instanceNode.name = buildReferenceName(node);
		instanceNode.facets = StashHelper.collectTypes(node);
		instanceNode.variables.addAll(parametersOf(node));
		instanceNode.nodes.addAll(createInstances(node.components()));
		return instanceNode;
	}

	private boolean isNotEmpty(Valued v) {
		return !v.values().isEmpty() && v.values().get(0) != null && !(v.values().get(0) instanceof EmptyNode);
	}

	private List<Variable> variablesOf(io.intino.tara.lang.model.Node node) {
		List<Variable> variables = new ArrayList<>();
		variables.addAll(node.variables().stream().filter(v -> isNotEmpty(v) && !v.isInherited()).map(this::transformTaraVariableToStashVariable).collect(toList()));
		return variables;
	}

	private List<Variable> parametersOf(io.intino.tara.lang.model.Node node) {
		return node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
	}

	private Variable transformTaraVariableToStashVariable(io.intino.tara.lang.model.Variable modelVariable) {
		final Variable variable = VariableFactory.get(modelVariable.type());
		if (variable == null) return null;
		variable.name = modelVariable.name();
		if (modelVariable.isReference() && !(modelVariable.values().get(0) instanceof Expression))
			variable.values = buildReferenceValues(modelVariable.values());
		else if (modelVariable.values().get(0) instanceof Expression)
			variable.values = createNativeReference(modelVariable);
		else if (modelVariable.values().get(0).toString().startsWith("$"))
			variable.values = StashHelper.buildResourceValue(modelVariable.values(), modelVariable.file());
		else variable.values = getValue(modelVariable);
		return variable;
	}

	private Variable createVariableFromParameter(Parameter parameter) {
		final Variable variable = VariableFactory.get(parameter.type());
		if (variable == null) return null;
		variable.name = parameter.name();
		if (parameter.hasReferenceValue()) variable.values = buildReferenceValues(parameter.values());
		else if (parameter.values().get(0) instanceof Expression)
			variable.values = createNativeReference(parameter);
		else if (parameter.values().get(0).toString().startsWith("$"))
			variable.values = StashHelper.buildResourceValue(parameter.values(), parameter.file());
		else variable.values = getValue(parameter);
		return variable;
	}

	//TODO change native package
	private List<Object> createNativeReference(io.intino.tara.lang.model.Variable variable) {
		final String aPackage = NativeFormatter.calculatePackage(variable.container());
		return new ArrayList<>(singletonList(reactivePrefix(variable) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(variable.name()).toString() + "_" + variable.getUID()));
	}

	private List<Object> createNativeReference(Parameter parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return new ArrayList<>(singletonList(reactivePrefix(parameter) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID()));
	}

	private String reactivePrefix(Valued variable) {
		return variable.type().equals(FUNCTION) || variable.flags().contains(Reactive) ? "" : "$@";
	}

	private List<Object> getValue(io.intino.tara.lang.model.Variable variable) {
		if (variable.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(StashHelper.hasToBeConverted(variable.values(), variable.type()) ?
				convert(variable) : variable.rule() instanceof NativeRule ? formatNativeReferenceOfVariable(variable.values()) : variable.values());
	}

	private List<Object> formatNativeReferenceOfVariable(List<Object> values) {
		return values.stream().map(value -> "$@" + value.toString()).collect(Collectors.toList());
	}

	private List<Object> getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(StashHelper.hasToBeConverted(parameter.values(), parameter.type()) ? convert(parameter) : parameter.values());
	}

	private List<?> convert(Valued valued) {
		final Primitive type = valued.type();
		if (type.equals(WORD)) return type.convert(valued.values().toArray());
		if (type.equals(BOOLEAN)) return type.convert(valued.values().toArray());
		if (type.equals(RESOURCE))
			//TODO CHECK VALUE EQUALS RESOURCE FOLDER.IT WILL TRHOW INDEXOUTOFRANGE
			return (valued.values()).stream().map(o -> toSystemIndependentName(((File) o).getAbsolutePath()).substring(toSystemIndependentName(resourceFolder.getAbsolutePath()).length() + 1)).collect(toList());
		else return type.convert(valued.values().toArray(new String[valued.values().size()]));
	}

	private List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(values.stream().map(this::buildReferenceName).collect(toList()));
	}

	private String buildReferenceName(Object o) {
		return o instanceof io.intino.tara.lang.model.Node ? ((((io.intino.tara.lang.model.Node) o).is(Instance)) ? getStash((io.intino.tara.lang.model.Node) o) + "#" : "") + ((io.intino.tara.lang.model.Node) o).cleanQn() :
				StashHelper.buildInstanceReference(o);
	}

	private static String toSystemIndependentName(String fileName) {
		return fileName.replace('\\', '/');
	}

	private String getStash(io.intino.tara.lang.model.Node node) {
		return test ? getTestStash(node) : getDefaultStashName();
	}

	private String getTestStash(io.intino.tara.lang.model.Node node) {
		final String file = new File(node.file()).getName();
		return file.substring(0, file.lastIndexOf("."));
	}

	private String getDefaultStashName() {
		return level.compareLevelWith(System) == 0 ? "Model" : generatedLanguage;
	}
}