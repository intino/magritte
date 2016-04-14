package tara.compiler.codegeneration.magritte.stash;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.dsl.ProteoConstants;
import tara.io.*;
import tara.io.Node;
import tara.io.Variable;
import tara.lang.model.*;
import tara.lang.model.Facet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.NameFormatter.getStashQn;
import static tara.compiler.codegeneration.magritte.stash.StashHelper.*;
import static tara.lang.model.Primitive.*;
import static tara.lang.model.Tag.*;

public class StashCreator {

	private static final Logger LOG = Logger.getLogger(StashCreator.class.getName());

	private final List<tara.lang.model.Node> nodes;
	private final Language language;
	private final File resourceFolder;
	private final int level;
	private final boolean test;
	private final Stash stash = new Stash();
	private final String generatedLanguage;

	public StashCreator(List<tara.lang.model.Node> nodes, Language language, String genLanguage, CompilerConfiguration conf) {
		this.nodes = nodes;
		this.language = language;
		this.generatedLanguage = Format.javaValidName().format(genLanguage).toString();
		this.resourceFolder = conf.getResourcesDirectory();
		this.level = conf.level();
		this.test = conf.isTest();
		this.stash.language = language.languageName();
		this.stash.applicationRefactorId = conf.domainRefactorId();
		this.stash.platformRefactorId = conf.engineRefactorId();
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		stash.contentRules = collectContents(nodes.stream().filter(node -> !node.is(Component) && !node.isFacet() && !node.is(Instance)).collect(Collectors.toList()));
		return stash;
	}

	private void create(NodeContainer containerNode, Concept container) {
		if (containerNode instanceof NodeReference) return;
		if (containerNode instanceof tara.lang.model.Node) asNode((tara.lang.model.Node) containerNode, container);
	}

	private void asNode(tara.lang.model.Node node, Concept container) {
		if (isInstance(node))
			if (container == null) stash.nodes.add(createInstance(node));
			else container.nodes.add(createInstance(node));
		else if (node.is(Prototype)) createPrototype(node, container);
		else createConcept(node);
	}

	private void createPrototype(tara.lang.model.Node node, Concept container) {
		if (node.isAbstract()) createConcept(node);
		else if (node.isAnonymous()) newPrototype(node, container);
		else {
			createConcept(node);
			newPrototype(node, container);
		}
	}

	private void newPrototype(tara.lang.model.Node node, Concept container) {
		final Prototype prototype = createPrototype(node);
		if (container == null) stash.prototypes.add(prototype);
		else container.prototypes.add(prototype);
	}

	private Prototype createPrototype(tara.lang.model.Node node) {
		Prototype prototype = new Prototype();
		prototype.name = buildReferenceName(node);
		prototype.className = couldHaveLayer(node) ? getLayerClass(node, generatedLanguage) : null;
		prototype.facets = createPrototypeFacets(node);
		return prototype;
	}

	private List<tara.io.Facet> createPrototypeFacets(tara.lang.model.Node node) {
		List<tara.io.Facet> facets = new ArrayList<>();
		for (String type : collectPrototypeTypes(node)) {
			tara.io.Facet facet = new tara.io.Facet();
			facet.name = type;
			facet.variables.addAll(variablesOf(node));
			facet.variables.addAll(parametersOf(node));
			if (!node.isReference()) facet.nodes.addAll(createPrototypes(node.components()));
			facets.add(facet);
		}
		return facets;
	}

	private List<Prototype> createPrototypes(List<tara.lang.model.Node> nodes) {
		return nodes.stream().map(this::createPrototype).collect(toList());
	}

	private void createConcept(tara.lang.model.Node node) {
		if (node.facetTarget() != null) stash.concepts.addAll(create(node.facetTarget(), node));
		else {
			List<tara.lang.model.Node> nodeList = collectTypeComponents(node.components());
			Concept concept = Helper.newConcept(node.qualifiedNameCleaned(),
				node.isAbstract() || node.isFacet(), node.type().equals(ProteoConstants.METACONCEPT),
				node.container() instanceof Model && !node.is(Tag.Component),
				node.name() != null && !node.name().isEmpty() ? getStashQn(node, generatedLanguage) : null,
				node.parentName() != null ? Format.qualifiedName().format(node.parent().qualifiedNameCleaned()).toString() : null,
				collectTypes(node),
				collectContents(nodeList),
				emptyList(),
				variablesOf(node),
				parametersOf(node),
				emptyList());
			stash.concepts.add(concept);
			for (tara.lang.model.Node component : node.components()) create(component, concept);
		}
	}

	private List<Concept> create(FacetTarget facetTarget, tara.lang.model.Node owner) {
		List<tara.lang.model.Node> components = collectTypeComponents(owner.components());
		List<Concept> concepts = new ArrayList<>();
		final Concept concept = new Concept();
		concepts.add(concept);
		concept.isMetaConcept = owner.type().equals(ProteoConstants.METACONCEPT);
		concept.isAbstract = owner.isAbstract();
		concept.name = owner.qualifiedNameCleaned();
		concept.className = getQn(facetTarget, owner, generatedLanguage);
		concept.types = collectTypes(facetTarget, language.constraints(owner.type()));
		concept.parent = facetTarget.parent() != null ? facetTarget.parent().qualifiedNameCleaned() : null;
		concept.contentRules = collectContents(components);
		concept.variables = variablesOf(owner);
		concept.parameters = parametersOf(owner);
		for (tara.lang.model.Node component : owner.components()) create(component, concept);
		concepts.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, concept)).
			collect(toList()));
		return concepts;
	}

	private Concept createChildFacetType(FacetTarget facetTarget, tara.lang.model.Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = facetTarget.owner().name() + "#" + node.qualifiedNameCleaned();
		child.parent = parent.name;
		child.isAbstract = facetTarget.owner().isAbstract();
		child.className = getQn(facetTarget, facetTarget.owner(), generatedLanguage);
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.contentRules = parent.contentRules;
		return child;
	}


	private List<tara.lang.model.Node> collectTypeComponents(List<tara.lang.model.Node> nodes) {
		return nodes.stream().filter(component -> !isInstance(component) && !component.is(Prototype)).collect(toList());
	}

	private List<Concept.Content> collectContents(List<tara.lang.model.Node> nodes) {
		return nodes.stream().
			filter(node -> !node.isFacet() && !node.is(Instance)).
			map(n -> new Concept.Content(n.isReference() ? n.destinyOfReference().qualifiedNameCleaned() : n.qualifiedNameCleaned(), n.container().ruleOf(n).min(), n.container().ruleOf(n).max())).collect(Collectors.toList());
	}

	private List<Node> createInstances(List<tara.lang.model.Node> nodes) {
		return nodes.stream().map(this::createInstance).collect(toList());
	}

	private Node createInstance(tara.lang.model.Node node) {
		Node instance = new Node();
		instance.name = buildReferenceName(node);
		instance.facets = createFacets(node);
		return instance;
	}

	private List<tara.io.Facet> createFacets(tara.lang.model.Node node) {
		List<tara.io.Facet> facets = new ArrayList<>();
		for (String type : collectTypes(node)) {
			tara.io.Facet facet = new tara.io.Facet();
			facet.name = type;
			facet.variables.addAll(parametersOf(node, type));
			facet.nodes.addAll(createInstances(componentsByType(node, type)));
			facets.add(facet);
		}
		return facets;
	}

	private List<Variable> parametersOf(tara.lang.model.Node node, String type) {
		for (tara.lang.model.Facet facet : node.facets())
			if ((facet.type() + "#" + node.type().replace(".", "$")).equals(type))
				return facet.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
		return node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
	}

	private List<tara.lang.model.Node> componentsByType(tara.lang.model.Node node, String type) {
		for (tara.lang.model.Facet facet : node.facets())
			if ((facet.type() + "#" + node.type().replace(".", "$")).equals(type))
				return facet.components();
		return node.components();
	}

	private boolean isNotEmpty(tara.lang.model.Valued v) {
		return !v.values().isEmpty() && v.values().get(0) != null && !(v.values().get(0) instanceof EmptyNode);
	}

	private List<Variable> variablesOf(tara.lang.model.Node node) {
		List<Variable> variables = new ArrayList<>();
		variables.addAll(node.variables().stream().filter(v -> isNotEmpty(v) && !v.isInherited()).map(this::createVariableFromVariable).collect(toList()));
		return variables;
	}

	private List<Variable> parametersOf(tara.lang.model.Node node) {
		List<Variable> parameters = node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
		for (Facet facet : node.facets())
			parameters.addAll(facet.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList()));
		return parameters;
	}

	private Variable createVariableFromVariable(tara.lang.model.Variable modelVariable) {
		final Variable variable = VariableFactory.get(modelVariable.type());
		if (variable == null) return null;
		variable.name = modelVariable.name();
		if (modelVariable.isReference() && !(modelVariable.values().get(0) instanceof Expression))
			variable.values = buildReferenceValues(modelVariable.values());
		else if (modelVariable.values().get(0) instanceof Expression)
			variable.values = createNativeReference(modelVariable);
		else if (modelVariable.values().get(0).toString().startsWith("$"))
			variable.values = buildResourceValue(modelVariable.values(), modelVariable.file());
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
			variable.values = buildResourceValue(parameter.values(), parameter.file());
		else variable.values = getValue(parameter);
		return variable;
	}

	private List<Object> createNativeReference(tara.lang.model.Variable variable) {
		final String aPackage = NativeFormatter.calculatePackage(variable.container());
		return new ArrayList<>(singletonList(generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(variable.name()).toString() + "_" + variable.getUID()));
	}

	private List<Object> createNativeReference(Parameter parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return new ArrayList<>(singletonList(generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID()));
	}

	private List<Object> getValue(tara.lang.model.Variable variable) {
		if (variable.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(variable.values(), variable.type()) ? convert(variable) : variable.values());
	}

	private List<Object> getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.type()) ? convert(parameter) : parameter.values());
	}

	private List<?> convert(tara.lang.model.Valued valued) {
		final Primitive type = valued.type();
		if (type.equals(WORD)) return type.convert(valued.values().toArray());
		if (type.equals(BOOLEAN)) return type.convert(valued.values().toArray());
		if (type.equals(RESOURCE))
			return (valued.values()).stream().map(o -> toSystemIndependentName(((File) o).getAbsolutePath()).substring(toSystemIndependentName(resourceFolder.getAbsolutePath()).length() + 1)).collect(toList());
		else return type.convert(valued.values().toArray(new String[valued.values().size()]));
	}

	private List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(values.stream().map(this::buildReferenceName).collect(toList()));
	}

	private String buildReferenceName(Object o) {
		return o instanceof tara.lang.model.Node ? (isInstance((tara.lang.model.Node) o) ? getStash((tara.lang.model.Node) o) + "#" : "") + ((tara.lang.model.Node) o).qualifiedNameCleaned() :
			buildInstanceReference(o);
	}

	private static String toSystemIndependentName(String fileName) {
		return fileName.replace('\\', '/');
	}

	private String getStash(tara.lang.model.Node node) {
		return test ? getTestStash(node) : getDefaultStashName();
	}

	private String getTestStash(tara.lang.model.Node node) {
		final File file = new File(node.file());
		File root = findRoot(file);
		final String stashPath = file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}

	private File findRoot(File nodeFile) {
		for (String sourceDirectory : CompilerConfiguration.SOURCE_DIRECTORIES)
			if (isIn(new File(resourceFolder.getParent(), sourceDirectory), nodeFile))
				return new File(resourceFolder.getParent(), sourceDirectory);
		return nodeFile;
	}

	private boolean isIn(File root, File nodeFile) {
		return nodeFile.getAbsolutePath().startsWith(root.getAbsolutePath());
	}

	private String getDefaultStashName() {
		return level == 0 ? "Model" : generatedLanguage;
	}
}