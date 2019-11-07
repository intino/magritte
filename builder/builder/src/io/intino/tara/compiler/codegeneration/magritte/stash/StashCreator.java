package io.intino.tara.compiler.codegeneration.magritte.stash;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.shared.Configuration.Model.Level;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.io.Concept;
import io.intino.tara.io.Node;
import io.intino.tara.io.Variable;
import io.intino.tara.io.*;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.Format.noPackage;
import static io.intino.tara.compiler.codegeneration.Format.withDollar;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.DOT;
import static io.intino.tara.compiler.codegeneration.magritte.stash.StashHelper.hasToBeConverted;
import static io.intino.tara.lang.model.Primitive.*;
import static io.intino.tara.lang.model.Tag.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class StashCreator {
	private final List<io.intino.tara.lang.model.Node> nodes;
	private final Language language;
	private final File resourceFolder;
	private final Level level;
	private final boolean test;
	private final Stash stash = new Stash();
	private final String outDSL;
	private final String workingPackage;
	private final boolean stashGeneration;

	public StashCreator(List<io.intino.tara.lang.model.Node> nodes, Language language, String outDSL, CompilerConfiguration conf) {
		this.nodes = nodes;
		this.language = language;
		this.outDSL = Format.javaValidName().format(Format.firstUpperCase().format(outDSL)).toString();
		this.workingPackage = conf.workingPackage();
		this.resourceFolder = conf.resourcesDirectory();
		this.level = conf.model().level();
		this.test = conf.isTest();
		this.stashGeneration = conf.isStashGeneration();
		this.stash.language = language.languageName();
	}

	private static String toSystemIndependentName(String fileName) {
		return fileName.replace('\\', '/');
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		stash.contentRules = collectContents(nodes.stream().filter(node -> !node.is(Component) && !node.isAspect() && !node.is(Instance)).collect(Collectors.toList()));
		return stash;
	}

	private void create(io.intino.tara.lang.model.Node node, Concept container) {
		if (node.isReference()) return;
		if (node.is(Instance))
			if (container == null) stash.nodes.add(createNode(node));
			else container.nodes.add(createNode(node));
		else createConcept(node);
	}

	private void createConcept(io.intino.tara.lang.model.Node node) {
		if (node.isAspect()) stash.concepts.addAll(createAspectConcept(node));
		else {
			List<io.intino.tara.lang.model.Node> nodeList = collectTypeComponents(node.components());
			Concept concept = Helper.newConcept(StashHelper.name(node, workingPackage),
					node.isAbstract(),
					node.type().equals(ProteoConstants.META_CONCEPT),
					node.isAspect() || node.isMetaAspect(),
					node.container() instanceof Model && !node.is(Tag.Component),
					className(node),
					node.parent() != null ? Format.qualifiedName().format(((NodeImpl) node.parent()).layerQualifiedName()).toString() : null,
					StashHelper.collectTypes(node, this.language),
					collectContents(nodeList),
					variablesOf(node),
					parametersOf(node),
					emptyList());
			stash.concepts.add(concept);
			for (io.intino.tara.lang.model.Node component : node.components()) create(component, concept);
		}
	}

	private List<Concept> createAspectConcept(io.intino.tara.lang.model.Node aspectNode) {
		List<Concept> concepts = new ArrayList<>();
		final Concept concept = new Concept();
		concepts.add(concept);
		concept.isMetaConcept = aspectNode.type().equals(ProteoConstants.META_CONCEPT);
		concept.isAbstract = aspectNode.isAbstract();
		concept.isAspect = true;
		concept.name = StashHelper.name(aspectNode, workingPackage);
		concept.className = aspectClassName(aspectNode);
		concept.types = StashHelper.collectTypes(aspectNode, language);
		concept.parent = calculateParent(aspectNode);
		concept.variables = variablesOf(aspectNode);
		concept.parameters = parametersOf(aspectNode);
		concept.contentRules = collectContents(collectTypeComponents(aspectNode.components()));
		for (io.intino.tara.lang.model.Node component : aspectNode.components()) create(component, concept);
		return concepts;
	}

	private String className(io.intino.tara.lang.model.Node node) {
		return workingPackage + DOT + withDollar().format(noPackage().format(isInDecorable(node) ?
				NameFormatter.decorableInnerClassQn(node, workingPackage) :
				NameFormatter.getQn(node, workingPackage))).toString();
	}

	private String aspectClassName(io.intino.tara.lang.model.Node aspectNode) {
		return workingPackage + DOT + withDollar().format(noPackage().format(
				aspectNode.container().is(Decorable) ?
						NameFormatter.decorableInnerClassQn(aspectNode, workingPackage) :
						NameFormatter.getQn(aspectNode, workingPackage)).toString());
	}

	private boolean isInDecorable(io.intino.tara.lang.model.Node node) {
		io.intino.tara.lang.model.Node aNode = node.container();
		while (!(aNode instanceof Model)) {
			if (aNode.is(Decorable)) return true;
			aNode = aNode.container();
		}
		return false;
	}


	private List<io.intino.tara.lang.model.Node> collectChildren(io.intino.tara.lang.model.Node parent) {
		Set<io.intino.tara.lang.model.Node> set = new HashSet<>();
		for (io.intino.tara.lang.model.Node child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private String calculateParent(io.intino.tara.lang.model.Node node) {
		return node.parent() != null ? ((NodeImpl) node.parent()).layerQn() : null;
	}

	private Concept createChildAspectType(io.intino.tara.lang.model.Node aspectNode, io.intino.tara.lang.model.Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = StashHelper.name(aspectNode, workingPackage);//TODO
		child.parent = parent.name;
		child.isAbstract = aspectNode.isAbstract();
		child.className = aspectClassName(aspectNode);
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
				filter(node -> !node.isAspect() && !node.is(Instance)).
				map(n -> new Concept.Content(n.isReference() ? ((NodeImpl) n.destinyOfReference()).layerQn() : ((NodeImpl) n).layerQn(), n.container().sizeOf(n).min(), n.container().sizeOf(n).max())).collect(Collectors.toList());
	}

	private List<Node> createNodes(List<io.intino.tara.lang.model.Node> nodes) {
		return nodes.stream().map(this::createNode).collect(toList());
	}

	private Node createNode(io.intino.tara.lang.model.Node node) {
		Node instanceNode = new Node();
		instanceNode.name = buildReferenceName(node);
		instanceNode.layers = StashHelper.collectTypes(node, this.language);
		instanceNode.variables.addAll(parametersOf(node));
		instanceNode.nodes.addAll(createNodes(node.components()));
		return instanceNode;
	}

	private boolean isNotEmpty(Valued v) {
		return !v.values().isEmpty() && v.values().get(0) != null && !(v.values().get(0) instanceof EmptyNode);
	}

	private List<Variable> variablesOf(io.intino.tara.lang.model.Node node) {
		return node.variables().stream().filter(v -> isNotEmpty(v) && !v.isInherited()).map(this::transformTaraVariableToStashVariable).collect(Collectors.toList());
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
		else if (modelVariable.type().equals(RESOURCE) && modelVariable.values().get(0).toString().startsWith("$"))
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
		else if (parameter.type().equals(RESOURCE) && parameter.values().get(0).toString().startsWith("$"))
			variable.values = StashHelper.buildResourceValue(parameter.values(), parameter.file());
		else variable.values = getValue(parameter);
		return variable;
	}

	//TODO change native package
	private List<Object> createNativeReference(io.intino.tara.lang.model.Variable variable) {
		final String aPackage = NativeFormatter.calculatePackage(variable.container());
		return new ArrayList<>(singletonList(reactivePrefix(variable) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(Format.firstUpperCase().format(variable.name())).toString() + "_" + variable.getUID()));
	}

	private List<Object> createNativeReference(Parameter parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return new ArrayList<>(singletonList(reactivePrefix(parameter) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(Format.firstUpperCase().format(parameter.name())).toString() + "_" + parameter.getUID()));
	}

	private String reactivePrefix(Valued variable) {
		return variable.type().equals(FUNCTION) || variable.flags().contains(Reactive) ? "" : "$@";
	}

	private List<Object> getValue(io.intino.tara.lang.model.Variable variable) {
		if (variable.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(variable.values(), variable.type()) ?
				convert(variable) :
				variable.rule() instanceof NativeRule ?
						formatNativeReferenceOfVariable(variable.values()) :
						variable.values());
	}

	private List<Object> formatNativeReferenceOfVariable(List<Object> values) {
		return values.stream().map(value -> "$@" + value.toString()).collect(Collectors.toList());
	}

	private List<Object> getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.type()) ? convert(parameter) : parameter.values());
	}

	@SuppressWarnings("SuspiciousToArrayCall")
	private List<?> convert(Valued valued) {
		final Primitive type = valued.type();
		if (type.equals(WORD)) return WORD.convert(valued.values().toArray());
		if (type.equals(LONG) && areIntegers(valued)) return valued.values().stream().map(v -> Long.valueOf((Integer) v)).collect(toList());
		else if (type.equals(INSTANT)) return INSTANT.convert(valued.values().toArray(new String[0]));
		if (type.equals(RESOURCE)) {
			return (valued.values()).stream()
					.map(o -> relative((File) o))
					.collect(toList());
		} else return type.convert(valued.values().toArray(new String[0]));
	}

	private boolean areIntegers(Valued valued) {
		return valued.values().stream().allMatch(v -> v instanceof Integer);
	}

	private String relative(File file) {
		final String path = toSystemIndependentName(file.getAbsolutePath());
		final String resources = toSystemIndependentName(resourceFolder.getAbsolutePath());
		return path.equals(resources) ? path : path.substring(resources.length() + 1);
	}

	private List<Object> buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return values.stream().map(this::buildReferenceName).collect(Collectors.toList());
	}

	private String buildReferenceName(Object o) {
		if (o instanceof Primitive.Reference && !((Reference) o).isToInstance())
			return nodeStashQualifiedName(((Reference) o).reference());
		else if (o instanceof io.intino.tara.lang.model.Node) return nodeStashQualifiedName((io.intino.tara.lang.model.Node) o);
		return StashHelper.buildInstanceReference(o);
	}

	private String nodeStashQualifiedName(io.intino.tara.lang.model.Node node) {
		return (((node).is(Instance)) ? getStash(node) + "#" : "") + ((NodeImpl) node).layerQn();
	}

	private String getStash(io.intino.tara.lang.model.Node node) {
		return (test || level.compareLevelWith(Level.Solution) == 0) && !stashGeneration ? getStashByNode(node) : outDSL;
	}

	private String getStashByNode(io.intino.tara.lang.model.Node node) {
		final String file = new File(node.file()).getName();
		return file.substring(0, file.lastIndexOf("."));
	}
}