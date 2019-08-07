package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Tag;
import io.intino.tara.lang.model.Variable;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static io.intino.tara.compiler.codegeneration.Format.firstUpperCase;
import static io.intino.tara.compiler.codegeneration.Format.javaValidName;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.facetLayerPackage;
import static io.intino.tara.compiler.codegeneration.magritte.layer.LayerFacetTargetAdapter.name;


public class LayerFrameCreator implements TemplateTags {
	private final Map<Class, Adapter> adapters;
	private final String outDsl;
	private final String workingPackage;
	private Node initNode = null;
	private LayerNodeAdapter layerNodeAdapter;
	private LayerVariableAdapter variableAdapter;
	private LayerFacetTargetAdapter layerFacetTargetAdapter;

	private LayerFrameCreator(String outDsl, Language language, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		this.layerFacetTargetAdapter = new LayerFacetTargetAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage);
		this.adapters = new HashMap<>();
		this.adapters.put(Node.class, layerNodeAdapter = new LayerNodeAdapter(outDsl, modelLevel, language, null, workingPackage, languageWorkingPackage));
		this.adapters.put(FacetTarget.class, layerFacetTargetAdapter);
		this.adapters.put(Variable.class, variableAdapter = new LayerVariableAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage));
	}

	public LayerFrameCreator(CompilerConfiguration conf, String language) {
		this(conf.outLanguage(), ((CompilerConfiguration.DSL) conf.language(d -> d.name().equals(language))).get(), conf.level(), conf.workingPackage(), conf.language(d -> d.name().equals(language)).generationPackage());
	}

	public Map.Entry<String, Frame> create(Node node) {
		initNode = node;
		layerNodeAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		layerNodeAdapter.setInitNode(initNode);
		final FrameBuilder builder = new FrameBuilder(LAYER).add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		addSlot(builder, node);
		addNodeImports(builder);
		return new SimpleEntry<>(calculateLayerPath(node, packageOf(builder)), builder.toFrame());
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget, Node owner) {
		final FrameBuilder frameBuilder = new FrameBuilder(LAYER).add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		layerFacetTargetAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		addSlot(frameBuilder, facetTarget);
		addFacetImports(frameBuilder);
		return new AbstractMap.SimpleEntry<>(calculateFacetLayerPath(facetTarget, owner, frameBuilder), frameBuilder.toFrame());
	}

	public Map.Entry<String, Frame> createDecorable(Node node) {
		final FrameBuilder builder = new FrameBuilder(LAYER, DECORABLE);
		final String aPackage = node.facetTarget() != null ? packageOf(node.facetTarget(), builder) : packageOf(builder);
		builder.add(NAME, node.facetTarget() != null ? name(node.facetTarget()) : node.name());
		if (node.isAbstract()) builder.add(ABSTRACT, true);
		return new SimpleEntry<>(calculateDecorablePath(node, aPackage), builder.toFrame());
	}

	private String calculateFacetLayerPath(FacetTarget facetTarget, Node node, FrameBuilder frameBuilder) {
		return packageOf(facetTarget, frameBuilder) + DOT +
				(node.is(Tag.Decorable) ? "Abstract" : "") + firstUpperCase().format(javaValidName().format(node.name() + facetTarget.targetNode().name()).toString()).toString();
	}

	private String calculateLayerPath(Node node, String aPackage) {
		return aPackage + DOT + (node.is(Tag.Decorable) ? "Abstract" : "") + firstUpperCase().format(javaValidName().format(node.name()).toString()) + facetName(node.facetTarget());
	}

	private String calculateDecorablePath(Node node, String aPackage) {
		return aPackage + DOT + firstUpperCase().format(javaValidName().format(node.name()).toString()) + facetName(node.facetTarget());
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target() : "";
	}

	private void addNodeImports(FrameBuilder builder) {
		Set<String> set = new HashSet<>(layerNodeAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		if (!set.isEmpty()) builder.add(IMPORTS, set.toArray(new Object[0]));
	}

	private void addFacetImports(FrameBuilder builder) {
		Set<String> set = new HashSet<>(layerFacetTargetAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		if (!set.isEmpty()) builder.add(IMPORTS, set.toArray(new Object[0]));
	}

	private void addSlot(FrameBuilder builder, Node node) {
		if (node instanceof NodeReference || node.is(Tag.Instance)) return;
		builder.add(NODE, build(node));
	}

	private void addSlot(FrameBuilder builder, FacetTarget facet) {
		builder.add(NODE, build(facet));
	}

	private String packageOf(FrameBuilder builder) {
		String packagePath = workingPackage.toLowerCase();
		if (!packagePath.isEmpty()) builder.add(PACKAGE, packagePath);
		return packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;
	}

	private String packageOf(FacetTarget target, FrameBuilder builder) {
		String packagePath = facetLayerPackage(target, workingPackage);
		if (!packagePath.isEmpty()) builder.add(PACKAGE, packagePath.substring(0, packagePath.length() - 1));
		return packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;
	}

	private Frame build(Object object) {
		return new FrameBuilder().put(adapters).append(object).toFrame();
	}
}