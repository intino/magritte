package tara.compiler.codegeneration.magritte.stash;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.dsl.ProteoConstants;
import tara.io.*;
import tara.io.Variable;
import tara.lang.model.*;
import tara.lang.model.Facet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static tara.compiler.codegeneration.magritte.NameFormatter.getJavaQN;
import static tara.compiler.codegeneration.magritte.stash.StashHelper.*;
import static tara.lang.model.Primitive.*;
import static tara.lang.model.Tag.*;

public class StashCreator {
	private final List<Node> nodes;
	private final Language language;
	private final File resourceFolder;
	private final int level;
	private final boolean test;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, Language language, String genLanguage, CompilerConfiguration conf) {
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
		if (containerNode instanceof Node) asNode((Node) containerNode, container);
	}

	private void asNode(Node node, Concept container) {
		if (isInstance(node))
			if (container == null) stash.instances.add(createInstance(node));
			else container.instances.add(createInstance(node));
		else if (node.is(Prototype)) createPrototype(node, container);
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
		prototype.className = couldHaveLayer(node) ? getLayerClass(node, generatedLanguage) : null;
		prototype.facets = createPrototypeFacets(node);
		return prototype;
	}

	private List<tara.io.Facet> createPrototypeFacets(Node node) {
		List<tara.io.Facet> facets = new ArrayList<>();
		for (String type : collectPrototypeTypes(node)) {
			tara.io.Facet facet = new tara.io.Facet();
			facet.name = type;
			facet.variables.addAll(variablesOf(node));
			facet.instances.addAll(createPrototypes(node.components()));
			facets.add(facet);
		}
		return facets;
	}

	private void createConcept(Node node) {
		if (node.facetTarget() != null)
			stash.concepts.addAll(create(node.facetTarget(), node));
		else {
			List<Node> nodeList = collectTypeComponents(node.components());
			Concept concept = Helper.newConcept(node.qualifiedNameCleaned(),
				node.isAbstract() || node.isFacet(), node.type().equals(ProteoConstants.METACONCEPT),
				node.container() instanceof Model && !node.is(Tag.Component),
				node.name() != null && !node.name().isEmpty() ? getJavaQN(generatedLanguage, node) : null,
				node.parentName() != null ? Format.qualifiedName().format(node.parent().qualifiedNameCleaned()).toString() : null,
				collectTypes(node),
				collectContents(nodeList),
				emptyList(),
				variablesOf(node),
				emptyList());
			stash.concepts.add(concept);
			for (Node component : node.components()) create(component, concept);
		}
	}

	private List<Concept> create(FacetTarget facetTarget, Node owner) {
		List<Node> components = collectTypeComponents(owner.components());
		List<Concept> concepts = new ArrayList<>();
		final Concept concept = new Concept();
		concepts.add(concept);
		concept.isMetaConcept = owner.type().equals(ProteoConstants.METACONCEPT);
		concept.name = owner.qualifiedNameCleaned();
		concept.className = getJavaQN(generatedLanguage, facetTarget, owner);
		concept.types = collectTypes(facetTarget, language.constraints(owner.type()));
		concept.parent = facetTarget.parent() != null ? facetTarget.parent().name() : null;
		concept.contentRules = collectContents(components);
		concept.variables = variablesOf(owner);
		for (Node component : owner.components()) create(component, concept);
		concepts.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, concept)).
			collect(toList()));
		return concepts;
	}

	private Concept createChildFacetType(FacetTarget facetTarget, Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = facetTarget.owner().name() + node.name();
		child.parent = facetTarget.owner().name() + node.parent().name();
		child.className = getJavaQN(generatedLanguage, facetTarget, facetTarget.owner());
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.contentRules = parent.contentRules;
		return child;
	}


	private List<Node> collectTypeComponents(List<Node> nodes) {
		return nodes.stream().filter(component -> !isInstance(component) && !component.is(Prototype)).collect(toList());
	}

	private List<Concept.Content> collectContents(List<Node> nodes) {
		return nodes.stream().
			filter(node -> !node.isFacet() && !node.is(Instance)).
			map(n -> new Concept.Content(n.isReference() ? n.destinyOfReference().qualifiedNameCleaned() : n.qualifiedNameCleaned(), n.container().ruleOf(n).min(), n.container().ruleOf(n).max())).collect(Collectors.toList());
	}

	private List<Instance> createInstances(List<Node> nodes) {
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
			facet.variables.addAll(variablesOf(node, type));
			facet.instances.addAll(createInstances(node.components()));
			facets.add(facet);
		}
		return facets;
	}

	private List<Variable> variablesOf(Node node, String type) {
		for (Facet facet : node.facets())
			if ((facet.type() + node.type()).equals(type)) {
				return facet.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
			}
		return node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
	}

	private boolean isNotEmpty(tara.lang.model.Valued v) {
		return !v.values().isEmpty() && v.values().get(0) != null && !(v.values().get(0) instanceof EmptyNode);
	}

	private List<Variable> variablesOf(Node node) {
		List<Variable> variables = node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
		variables.addAll(node.variables().stream().filter(v -> isNotEmpty(v) && !v.isInherited()).map(this::createVariableFromVariable).collect(toList()));
		for (Facet facet : node.facets())
			variables.addAll(facet.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList()));
		return variables;
	}

	private Variable createVariableFromVariable(tara.lang.model.Variable modelVariable) {
		final Variable variable = VariableFactory.get(modelVariable.type());
		if (variable == null) return null;
		variable.name = modelVariable.name();
		if (modelVariable.isReference()) variable.values = buildReferenceValues(modelVariable.values());
		else if (FUNCTION.equals(modelVariable.type()) || modelVariable.flags().contains(Tag.Native))
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
		else if (FUNCTION.equals(parameter.type()) || parameter.flags().contains(Tag.Native.name()))
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
			return (valued.values()).stream().map((o) -> o.toString().substring(resourceFolder.getAbsolutePath().length() + 1)).collect(toList());
		else return type.convert(valued.values().toArray(new String[valued.values().size()]));
	}

	private List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(values.stream().map(this::buildReferenceName).collect(toList()));
	}

	private String buildReferenceName(Object o) {
		return o instanceof Node ? (isInstance((Node) o) ? getStash((Node) o) + "#" : "") + ((Node) o).qualifiedNameCleaned() :
			buildInstanceReference(o);
	}

	private String getStash(Node node) {
		return test ? getTestStash(node) : getDefaultStashName();
	}

	private String getTestStash(Node node) {
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

	public boolean isIn(File root, File nodeFile) {
		return nodeFile.getAbsolutePath().startsWith(root.getAbsolutePath());
	}

	private String getDefaultStashName() {
		return level == 0 ? "Model" : generatedLanguage;
	}
}