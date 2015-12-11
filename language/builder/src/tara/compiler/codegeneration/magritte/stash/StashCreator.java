package tara.compiler.codegeneration.magritte.stash;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.NodeReference;
import tara.io.*;
import tara.io.Variable;
import tara.lang.model.*;
import tara.lang.model.Facet;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.compiler.codegeneration.magritte.stash.StashHelper.*;
import static tara.lang.model.Primitive.*;

public class StashCreator {
	private final List<Node> nodes;
	private final Language language;
	private final File rootFolder;
	private final boolean test;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, Language language, String generatedLanguage, File rootFolder, boolean test) {
		this.nodes = nodes;
		this.language = language;
		this.rootFolder = rootFolder;
		this.test = test;
		stash.language = language.languageName();
		this.generatedLanguage = generatedLanguage;
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		return stash;
	}

	private void create(NodeContainer containerNode, Concept container) {
		if (containerNode instanceof NodeReference) return;
		if (containerNode instanceof Node) asNode((Node) containerNode, container);
		else if (containerNode instanceof FacetTarget)
			stash.concepts.addAll(createConcepts((FacetTarget) containerNode));
	}

	private void asNode(Node node, Concept container) {
		if (isInstance(node))
			if (container == null) stash.instances.add(createInstance(node));
			else container.instances.add(createInstance(node));
		else if (node.isPrototype())
			createPrototype(node, container);
		else createConcept(node);
	}

	private void createPrototype(Node node, Concept container) {
		if (node.isAbstract()) createConcept(node);
		else if (node.isAnonymous()) newPrototype(node, container);
		else {
			createConcept(node);
			newPrototype(node, container);
		}
	}

	private void newPrototype(Node node, Concept container) {
		final Prototype prototype = createPrototype(node);
		if (container == null) stash.prototypes.add(prototype);
		else container.prototypes.add(prototype);
	}

	private List<Prototype> createPrototypes(List<Node> nodes) {
		return nodes.stream().map(this::createPrototype).collect(toList());
	}

	private Prototype createPrototype(Node node) {
		Prototype prototype = new Prototype();
		prototype.name = buildReferenceName(node);
		prototype.className = couldHaveLayer(node) ? getMorphClass(node, generatedLanguage) : null;
		prototype.facets = createPrototypeFacets(node);
		return prototype;
	}

	private List<tara.io.Facet> createPrototypeFacets(Node node) {
		List<tara.io.Facet> facets = new ArrayList<>();
		for (String type : StashHelper.collectPrototypeTypes(node)) {
			tara.io.Facet facet = new tara.io.Facet();
			facet.name = type;
			facet.variables.addAll(variablesOf(node));
			facet.instances.addAll(createPrototypes(node.components()));
			facets.add(facet);
		}
		return facets;
	}

	private Concept createConcept(Node node) {
		final Concept concept = new Concept();
		stash.concepts.add(concept);
		concept.name = node.qualifiedNameCleaned();
		if (node.parentName() != null)
			concept.parent = node.parent().qualifiedNameCleaned();
		concept.isAbstract = node.isAbstract() || node.isFacet();
		concept.isMain = node.isComponent();
		if (node.name() != null && !node.name().isEmpty())
			concept.className = NameFormatter.getJavaQN(generatedLanguage, node);
		concept.types = collectTypes(node);
		addConstrains(node, concept);
		for (Node component : node.components())
			create(component, concept);
		for (FacetTarget facetTarget : node.facetTargets())
			create(facetTarget, concept);
		concept.variables.addAll(variablesOf(node));
		return concept;
	}

	private List<Concept> createConcepts(FacetTarget facetTarget) {
		List<Concept> concepts = new ArrayList<>();
		final Concept container = new Concept();
		container.name = facetTarget.qualifiedNameCleaned();
		container.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		container.types = collectTypes(facetTarget, language.constraints(facetTarget.container().type()));
		container.parent = ((Node) facetTarget.container()).name();
		List<Node> components = collectTypeComponents(facetTarget.components());
//		container.allowsMultiple = collectAllowsMultiple(components);
//		container.requiresMultiple = collectRequiresMultiple(components);
//		container.allowsSingle = collectAllowsSingle(components);
//		container.requiresSingle = collectRequiresSingle(components);
		container.variables = facetTarget.parameters().stream().map(this::createVariableFromParameter).collect(toList());
		for (Node component : facetTarget.components())
			create(component, container);
		concepts.add(container);
		concepts.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, container)).
			collect(toList()));
		return concepts;
	}

	private Concept createChildFacetType(FacetTarget facetTarget, Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = ((Node) facetTarget.container()).name() + node.name();
		child.parent = ((Node) facetTarget.container()).name() + node.parent().name();
		child.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.allowsMultiple = parent.allowsMultiple;
		child.requiresMultiple = parent.requiresMultiple;
		child.allowsSingle = parent.allowsSingle;
		child.requiresSingle = parent.requiresSingle;
		return child;
	}

	private void addConstrains(Node node, Concept concept) {
		List<Node> nodeList = collectTypeComponents(node.components());
//		type.allowsMultiple = collectAllowsMultiple(nodeList);
//		type.requiresMultiple = collectRequiresMultiple(nodeList);
//		type.allowsSingle = collectAllowsSingle(nodeList);
//		type.requiresSingle = collectRequiresSingle(nodeList);
	}

	private List<Node> collectTypeComponents(List<Node> nodes) {
		return nodes.stream().filter(component -> !isInstance(component) && !component.isPrototype()).collect(toList());
	}

	//	private List<String> collectAllowsMultiple(List<Node> nodes) {
//		return nodes.stream().filter(component -> !component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectRequiresMultiple(List<Node> nodes) {
//		return nodes.stream().filter(component -> component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectAllowsSingle(List<Node> nodes) {
//		return nodes.stream().filter(component -> !component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectRequiresSingle(List<Node> nodes) {
//		return nodes.stream().filter(component -> component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//


	private List<Instance> createDeclarations(List<Node> nodes) {
		return nodes.stream().map(this::createInstance).collect(toList());
	}

	private Instance createInstance(Node node) {
		Instance instance = new Instance();
		instance.name = buildReferenceName(node);
		instance.facets = createFacets(node);
		return instance;
	}

	private List<tara.io.Facet> createFacets(Node node) {
		List<tara.io.Facet> facets = new ArrayList<>();
		for (String type : collectTypes(node)) {
			tara.io.Facet facet = new tara.io.Facet();
			facet.name = type;
			facet.variables.addAll(variablesOf(node));
			facet.instances.addAll(createDeclarations(node.components()));
			facets.add(facet);
		}
		return facets;
	}

	private List<Variable> variablesOf(Node node) {
		List<Variable> variables = node.parameters().stream().map(this::createVariableFromParameter).collect(toList());
		for (Facet facet : node.facets())
			variables.addAll(facet.parameters().stream().map(this::createVariableFromParameter).collect(toList()));
		return variables;
	}

	private Variable createVariableFromParameter(Parameter parameter) {
		final Variable variable = VariableFactory.get(parameter.inferredType());
		if (variable == null) return null;
		variable.name = parameter.name();
		if (parameter.hasReferenceValue()) variable.values = buildReferenceValues(parameter.values());
		else if (FUNCTION.equals(parameter.inferredType()) || parameter.flags().contains(Tag.Native.name()))
			variable.values = createNativeReference(parameter);
		else if (parameter.values().get(0).toString().startsWith("$"))
			variable.values = buildResourceValue(parameter.values(), parameter.file());
		else variable.values = getValue(parameter);
		return variable;
	}


	private List<Object> createNativeReference(Parameter parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return new ArrayList<>(Collections.singletonList(generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID()));
	}

	private List<Object> getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.inferredType()) ? convert(parameter) : parameter.values());
	}

	private List<?> convert(Parameter parameter) {
		final Primitive type = parameter.inferredType();
		if (type.equals(WORD)) return type.convert(parameter.values().toArray());
		if (type.equals(FILE)) return (parameter.values()).stream().map(Object::toString).collect(toList());
		else return type.convert(parameter.values().toArray(new String[parameter.values().size()]));
	}

	public List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(values.stream().map(this::buildReferenceName).collect(toList()));
	}

	public String buildReferenceName(Object o) {
		return o instanceof Node ? (isInstance((Node) o) ? getStash((Node) o) + "#" : "") + ((Node) o).qualifiedNameCleaned() : buildInstanceReference(o);
	}

	public String getStash(Node node) {
		return test ? getTestStash(node) : getDefaultStashName();
	}

	public String getTestStash(Node node) {
		final File file = new File(node.file());
		File modelRoot = new File(rootFolder.getParent(), "model");
		final String stashPath = file.getAbsolutePath().substring(modelRoot.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}

	public String getDefaultStashName() {
		return generatedLanguage == null || generatedLanguage.isEmpty() ? "Model" : generatedLanguage;
	}
}