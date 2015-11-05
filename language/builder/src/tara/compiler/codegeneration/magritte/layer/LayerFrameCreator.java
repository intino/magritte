package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Variable;

import java.util.AbstractMap;
import java.util.Map;


public class LayerFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String generatedLanguage;
	private Node initNode = null;
	private LayerNodeAdapter layerNodeAdapter;

	public LayerFrameCreator(String generatedLanguage, Language language, int modelLevel) {
		this.generatedLanguage = generatedLanguage;
		builder.register(Node.class, layerNodeAdapter = new LayerNodeAdapter(generatedLanguage, language, initNode));
		builder.register(FacetTarget.class, new LayerFacetTargetAdapter(generatedLanguage));
		builder.register(Variable.class, new LayerVariableAdapter(generatedLanguage, language, modelLevel));
	}

	public LayerFrameCreator(CompilerConfiguration conf) {
		this(conf.getGeneratedLanguage(), conf.getLanguage(), conf.getLevel());
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		layerNodeAdapter.setInitNode(initNode);
		final Frame frame = new Frame().addTypes(LAYER);
		String packagePath = addPackage(frame);
		createMorph(frame, node);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + Format.javaValidName().format(node.name()).toString(), frame);
	}

	private void createMorph(Frame frame, Node node) {
		if (node instanceof NodeReference || node.isTerminalInstance() || node.isFeatureInstance()) return;
		frame.addFrame(NODE, builder.build(node));
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget) {
		final Frame frame = new Frame().addTypes(LAYER);
		String packagePath = addPackage(facetTarget, frame);
		createFacetTargetMorph(frame, facetTarget);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		return new AbstractMap.SimpleEntry<>(packagePath + DOT +
			Format.javaValidName().format(((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name()).toString(), frame);
	}

	private void createFacetTargetMorph(Frame frame, FacetTarget node) {
		frame.addFrame(NODE, builder.build(node));
	}

	private String addPackage(Frame frame) {
		String packagePath = generatedLanguage.toLowerCase();
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = NameFormatter.composeMorphPackagePath(target, generatedLanguage);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

}
