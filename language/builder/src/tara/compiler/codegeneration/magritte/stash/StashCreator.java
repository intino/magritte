package tara.compiler.codegeneration.magritte.stash;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.dsl.Proteo;
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
import static tara.compiler.codegeneration.magritte.stash.StashHelper.*;
import static tara.lang.model.Primitive.*;
import static tara.lang.model.Tag.*;

public class StashCreator {
	private final List<Node> nodes;
	private final Language language;
	private final File rootFolder;
	private final int level;
	private final boolean test;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, Language language, String generatedLanguage, File rootFolder, int level, boolean test) {
		this.nodes = nodes;
		this.language = language;
		this.rootFolder = rootFolder;
		this.level = level;
		this.test = test;
		stash.language = language.languageName();
		this.generatedLanguage = generatedLanguage;
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
		else if (node.is(Prototype))
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
		prototype.className = couldHaveLayer(node) ? getLayerClass(node, generatedLanguage) : null;
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

	private void createConcept(Node node) {
		if (node.facetTarget() != null) {
			stash.concepts.addAll(create(node.facetTarget(), node));
		} else {
			List<Node> nodeList = collectTypeComponents(node.components());
			Concept concept = Helper.newConcept(Format.qualifiedName().format(node.qualifiedNameCleaned()).toString(),
				node.isAbstract() || node.isFacet(), node.type().equals(Proteo.METACONCEPT),
				node.container() instanceof Model && !node.is(Tag.Component),
				node.name() != null && !node.name().isEmpty() ? NameFormatter.getJavaQN(generatedLanguage, node) : null,
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
		concept.isMetaConcept = owner.type().equals(Proteo.METACONCEPT);
		concept.name = owner.qualifiedNameCleaned();
		concept.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget, owner);
		concept.types = collectTypes(facetTarget, language.constraints(owner.type()));
		concept.parent = facetTarget.parent() != null ? facetTarget.parent().name() : null;
		concept.contentRules = collectContents(components);
		concept.variables = owner.parameters().stream().map(this::createVariableFromParameter).collect(toList());
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
		child.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget, facetTarget.owner());
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
		return new ArrayList<>(singletonList(generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID()));
	}

	private List<Object> getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.inferredType()) ? convert(parameter) : parameter.values());
	}

	private List<?> convert(Parameter parameter) {
		final Primitive type = parameter.inferredType();
		if (type.equals(WORD)) return type.convert(parameter.values().toArray());
		if (type.equals(RESOURCE)) return (parameter.values()).stream().map(Object::toString).collect(toList());
		else return type.convert(parameter.values().toArray(new String[parameter.values().size()]));
	}

	public List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(values.stream().map(this::buildReferenceName).collect(toList()));
	}

	public String buildReferenceName(Object o) {
		return o instanceof Node ? (isInstance((Node) o) ? getStash((Node) o) + "#" : "") + ((Node) o).qualifiedNameCleaned() :
			buildInstanceReference(o);
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
		return level == 0 ? "Model" : generatedLanguage;
	}
}