package io.intino.magritte.builder.compiler.codegeneration.magritte.stash;

import io.intino.builder.CompilerConfiguration;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.natives.NativeFormatter;
import io.intino.magritte.io.Helper;
import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;
import io.intino.tara.Language;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.property.FunctionRule;
import io.intino.tara.processors.model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.*;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashHelper.hasToBeConverted;
import static io.intino.tara.builder.utils.Format.noPackage;
import static io.intino.tara.builder.utils.Format.withDollar;
import static io.intino.tara.model.Annotation.*;
import static io.intino.tara.model.Level.M1;
import static io.intino.tara.model.Level.M3;
import static io.intino.tara.model.Primitive.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class StashCreator {
	private static final String STASH = ".stash";
	private final List<Mogram> mograms;
	private final Language language;
	private final File resourceFolder;
	private final boolean test;
	private final Stash stash = new Stash();
	private final String outDSL;
	private final String workingPackage;

	public StashCreator(List<Mogram> mograms, Language language, String outDSL, CompilerConfiguration conf) {
		this.mograms = mograms;
		this.language = language;
		this.outDSL = Format.javaValidName().format(Format.firstUpperCase().format(outDSL)).toString();
		this.workingPackage = conf.generationPackage();
		this.resourceFolder = conf.resDirectory();
		this.test = conf.test();
		this.stash.language = language.languageName();
		this.stash.path = new File(mograms.get(0).source()).getName().split("\\.")[0] + STASH;
	}

	private static String toSystemIndependentName(String fileName) {
		return fileName.replace('\\', '/');
	}

	public Stash create() {
		mograms.forEach(node -> create(node, null));
		stash.contentRules = collectContents(mograms.stream()
				.filter(mogram -> !mogram.is(Component) && mogram.facetPrescription() == null && mogram.level() != M1)
				.collect(Collectors.toList()));
		return stash;
	}

	private void create(Mogram mogram, Concept container) {
		if (mogram.level() == M1)
			if (container == null) stash.nodes.add(createNode(mogram));
			else container.nodes.add(createNode(mogram));
		else createConcept(mogram);
	}

	private void createConcept(Mogram mogram) {
		if (mogram.facetPrescription() != null) stash.concepts.addAll(createFacetConcept(mogram));
		else {
			List<Mogram> nodeList = collectTypeComponents(mogram.components());
			Concept concept = Helper.newConcept(StashHelper.name(mogram, workingPackage),
					mogram.is(Generalization),
					mogram.level() == M3,
					mogram.facetPrescription() != null,
					mogram.container() instanceof Model && !mogram.is(Annotation.Component),
					className(mogram),
					mogram.parent() != null ? Format.qualifiedName().format(layerQualifiedName(mogram.parent().get())).toString() : null,
					StashHelper.collectTypes(mogram, this.language),
					collectContents(nodeList),
					propertiesOf(mogram),
					parametersOf(mogram),
					emptyList());
			stash.concepts.add(concept);
			for (Mogram component : mogram.components()) create(component, concept);
		}
	}

	private List<Concept> createFacetConcept(Mogram facetMogram) {
		List<Concept> concepts = new ArrayList<>();
		final Concept concept = new Concept();
		concepts.add(concept);
		concept.isMetaConcept = facetMogram.level().equals(M3);
		concept.isAbstract = facetMogram.is(Generalization);
		concept.isAspect = true;
		concept.name = StashHelper.name(facetMogram, workingPackage);
		concept.className = facetClassName(facetMogram);
		concept.types = StashHelper.collectTypes(facetMogram, language);
		concept.parent = calculateParent(facetMogram);
		concept.variables = propertiesOf(facetMogram);
		concept.parameters = parametersOf(facetMogram);
		concept.contentRules = collectContents(collectTypeComponents(facetMogram.components()));
		for (Mogram component : facetMogram.components()) create(component, concept);
		return concepts;
	}

	private String className(Mogram mogram) {
		return workingPackage + DOT + withDollar().format(noPackage().format(NameFormatter.getQn(mogram, workingPackage))).toString();
	}

	private String facetClassName(Mogram facetMogram) {
		return workingPackage + DOT + withDollar().format(noPackage().format(NameFormatter.getQn(facetMogram, workingPackage)).toString());
	}

	private String calculateParent(Mogram mogram) {
		return mogram.parent() != null ? layerQn(mogram.parent().get()) : null;
	}

	private Concept createChildAspectType(Mogram facetMogram, Mogram mogram, Concept parent) {
		final Concept child = new Concept();
		child.name = StashHelper.name(facetMogram, workingPackage);//TODO
		child.parent = parent.name;
		child.isAbstract = facetMogram.is(Generalization);
		child.className = facetClassName(facetMogram);
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.contentRules = parent.contentRules;
		return child;
	}

	private List<Mogram> collectTypeComponents(List<Mogram> mograms) {
		return mograms.stream().filter(component -> (component.level() != M1)).collect(toList());
	}

	private List<Concept.Content> collectContents(List<Mogram> mograms) {
		return mograms.stream().
				filter(m -> m.facetPrescription() == null && m.level() != M1).
				map(m -> new Concept.Content(layerQualifiedName(m), m.container().sizeOf(m).min(), m.container().sizeOf(m).max()))
				.collect(Collectors.toList());
	}

	private List<Node> createNodes(List<Mogram> nodes) {
		return nodes.stream().map(this::createNode).collect(toList());
	}

	private Node createNode(Mogram node) {
		Node instanceNode = new Node();
		instanceNode.name = buildReferenceName(node);
		instanceNode.layers.addAll(StashHelper.collectTypes(node, this.language));
		instanceNode.variables.addAll(parametersOf(node));
		instanceNode.nodes.addAll(createNodes(node.components()));
		return instanceNode;
	}

	private boolean isNotEmpty(Valued v) {
		return !v.values().isEmpty() && v.values().get(0) != null && !(v.values().get(0) instanceof EmptyMogram);
	}

	private List<Variable> propertiesOf(Mogram mogram) {
		return mogram.properties().stream().filter(this::isNotEmpty).map(this::transformTaraVariableToStashVariable).collect(Collectors.toList());
	}

	private List<Variable> parametersOf(Mogram node) {
		return node.parameters().stream().filter(this::isNotEmpty).map(this::createVariableFromParameter).collect(toList());
	}

	private Variable transformTaraVariableToStashVariable(Property prop) {
		final Variable variable = new Variable();
		variable.name = prop.name();
		if (prop.isReference() && !(prop.values().get(0) instanceof Expression))
			variable.values = buildReferenceValues(prop.values());
		else if (prop.values().get(0) instanceof Expression)
			variable.values = createNativeReference(prop);
		else if (prop.type().equals(RESOURCE) && prop.values().get(0).toString().startsWith("$"))
			variable.values = StashHelper.buildResourceValue(prop.values(), prop.source().getPath());
		else variable.values = getValue(prop);
		return variable;
	}

	private Variable createVariableFromParameter(PropertyDescription parameter) {
		final Variable variable = new Variable();
		variable.name = parameter.name();
		if (parameter.definition().isReference()) variable.values = buildReferenceValues(parameter.values());
		else if (parameter.values().get(0) instanceof Expression)
			variable.values = createNativeReference(parameter);
		else if (parameter.type().equals(RESOURCE) && parameter.values().get(0).toString().startsWith("$"))
			variable.values = StashHelper.buildResourceValue(parameter.values(), parameter.source().getPath());
		else variable.values = getValue(parameter);
		return variable;
	}

	//TODO change native package
	private List<Object> createNativeReference(Property property) {
		final String aPackage = NativeFormatter.calculatePackage(property.container());
		return new ArrayList<>(singletonList(reactivePrefix(property) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(Format.firstUpperCase().format(property.name())).toString() + "_" + property.getUID()));
	}

	private List<Object> createNativeReference(PropertyDescription parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return new ArrayList<>(singletonList(reactivePrefix(parameter) + workingPackage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(Format.firstUpperCase().format(parameter.name())).toString() + "_" + parameter.getUID()));
	}

	private String reactivePrefix(Property prop) {
		return prop.type().equals(FUNCTION) || prop.annotations().contains(Reactive) ? "" : "$@";
	}

	private String reactivePrefix(PropertyDescription prop) {
		return prop.type().equals(FUNCTION) || prop.definition().annotations().contains(Reactive) ? "" : "$@";
	}

	private List<Object> getValue(Property prop) {
		if (prop.values().getFirst() instanceof Primitive.Reference r && r.isEmpty()) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(prop.values(), prop.type()) ?
				convert(prop) :
				(prop.rule(FunctionRule.class) != null ?
						formatNativeReferenceOfVariable(prop.values()) :
						prop.values()));
	}

	private List<Object> formatNativeReferenceOfVariable(List<Object> values) {
		return values.stream().map(value -> "$@" + value.toString()).collect(Collectors.toList());
	}

	private List<Object> getValue(PropertyDescription parameter) {
		if (parameter.values().getFirst() instanceof Primitive.Reference r && r.isEmpty()) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.type()) ? convert(parameter) : parameter.values());
	}

	@SuppressWarnings("SuspiciousToArrayCall")
	private List<?> convert(Valued valued) {
		final Primitive type = valued.type();
		if (type.equals(WORD)) return WORD.convert(valued.values().toArray());
		if (type.equals(LONG) && areIntegers(valued))
			return valued.values().stream().map(v -> Long.valueOf((Integer) v)).collect(toList());
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
		if (values.getFirst() instanceof Primitive.Reference r && r.isEmpty()) return new ArrayList<>();
		return values.stream().map(this::buildReferenceName).collect(Collectors.toList());
	}

	private String buildReferenceName(Object o) {
		if (o instanceof Primitive.Reference r && !r.isToInstance())
			return mogramStashQualifiedName(r.get().get());
		else if (o instanceof Mogram)
			return mogramStashQualifiedName((Mogram) o);
		return StashHelper.buildInstanceReference(o);
	}

	private String mogramStashQualifiedName(Mogram mogram) {
		return (mogram.level() == M1 ? getStash(mogram) + "#" : "") + layerQn(mogram);
	}

	private String getStash(Mogram mogram) {
		return test || mogram.level() == M1 ? getStashByNode(mogram) : outDSL;
	}

	private String getStashByNode(Mogram mogram) {
		final String file = new File(mogram.source()).getName();
		return file.substring(0, file.lastIndexOf("."));
	}
}