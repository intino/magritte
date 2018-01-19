package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Tag;
import io.intino.tara.lang.model.Variable;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.intino.tara.compiler.codegeneration.Format.firstUpperCase;
import static io.intino.tara.compiler.codegeneration.Format.javaValidName;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.facetLayerPackage;
import static io.intino.tara.compiler.codegeneration.magritte.layer.LayerFacetTargetAdapter.name;


public class LayerFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String outDsl;
	private final String workingPackage;
	private Node initNode = null;
	private LayerNodeAdapter layerNodeAdapter;
	private LayerVariableAdapter variableAdapter;
	private LayerFacetTargetAdapter layerFacetTargetAdapter;

	private LayerFrameCreator(String outDsl, Language language, Level modelLevel, String workingPackage, String languageWorkingPackage) {
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		builder.register(Node.class, layerNodeAdapter = new LayerNodeAdapter(outDsl, modelLevel, language, initNode, workingPackage, languageWorkingPackage));
		layerFacetTargetAdapter = new LayerFacetTargetAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage);
		builder.register(FacetTarget.class, layerFacetTargetAdapter);
		builder.register(Variable.class, variableAdapter = new LayerVariableAdapter(language, outDsl, modelLevel, workingPackage, languageWorkingPackage));
	}

	public LayerFrameCreator(CompilerConfiguration conf, String language) {
		this(conf.outDSL(), ((CompilerConfiguration.DSL) conf.language(d -> d.name().equals(language))).get(), conf.level(), conf.workingPackage(), conf.language(d -> d.name().equals(language)).generationPackage());
	}

	public Map.Entry<String, Frame> create(Node node) {
		initNode = node;
		layerNodeAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		layerNodeAdapter.setInitNode(initNode);
		final Frame frame = new Frame().addTypes(LAYER).addSlot(OUT_LANGUAGE, outDsl).addSlot(WORKING_PACKAGE, workingPackage);
		createFrame(frame, node);
		addNodeImports(frame);
		return new SimpleEntry<>(calculateLayerPath(node, packageOf(frame)), frame);
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget, Node owner) {
		final Frame frame = new Frame().addTypes(LAYER).addSlot(OUT_LANGUAGE, outDsl).addSlot(WORKING_PACKAGE, workingPackage);
		layerFacetTargetAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		createFrame(frame, facetTarget);
		addFacetImports(frame);
		return new SimpleEntry<>(calculateLayerPath(owner, packageOf(facetTarget, frame)), frame);
	}

	public Map.Entry<String, Frame> createDecorable(Node node) {
		final Frame frame = new Frame().addTypes(LAYER, DECORABLE);
		final String aPackage = node.facetTarget() != null ? packageOf(node.facetTarget(), frame) : packageOf(frame);
		frame.addSlot(NAME, node.facetTarget() != null ? name(node.facetTarget()) : node.name());
		if (node.isAbstract()) frame.addSlot(ABSTRACT, true);
		return new SimpleEntry<>(calculateDecorablePath(node, aPackage), frame);
	}

	private String calculateDecorablePath(Node node, String aPackage) {
		return aPackage + DOT + firstUpperCase().format(javaValidName().format(node.name()).toString()) + facetName(node.facetTarget());
	}

	private String calculateLayerPath(Node node, String aPackage) {
		return aPackage + DOT + (node.is(Tag.Decorable) ? "Abstract" : "") + firstUpperCase().format(javaValidName().format(node.name()).toString()) + facetName(node.facetTarget());
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target() : "";
	}

	private void addNodeImports(Frame frame) {
		Set<String> set = new HashSet<>(layerNodeAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		frame.addSlot(IMPORTS, set.toArray(new String[set.size()]));
	}

	private void addFacetImports(Frame frame) {
		Set<String> set = new HashSet<>(layerFacetTargetAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		frame.addSlot(IMPORTS, set.toArray(new String[set.size()]));
	}

	private void createFrame(Frame frame, Node node) {
		if (node instanceof NodeReference || node.is(Tag.Instance)) return;
		frame.addSlot(NODE, builder.build(node));
	}

	private void createFrame(Frame frame, FacetTarget facet) {
		frame.addSlot(NODE, builder.build(facet));
	}

	private String packageOf(Frame frame) {
		String packagePath = workingPackage.toLowerCase();
		if (!packagePath.isEmpty()) frame.addSlot(PACKAGE, packagePath);
		return packagePath;
	}

	private String packageOf(FacetTarget target, Frame frame) {
		String packagePath = facetLayerPackage(target, workingPackage);
		if (!packagePath.isEmpty()) frame.addSlot(PACKAGE, packagePath.substring(0, packagePath.length() - 1));
		return packagePath.endsWith(".") ? packagePath.substring(0, packagePath.length() - 1) : packagePath;
	}
}
