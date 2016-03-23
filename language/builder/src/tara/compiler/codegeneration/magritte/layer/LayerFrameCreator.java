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
import tara.lang.model.Tag;
import tara.lang.model.Variable;

import java.io.File;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class LayerFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String generatedLanguage;
	private Node initNode = null;
	private LayerNodeAdapter layerNodeAdapter;
	private LayerVariableAdapter variableAdapter;
	private LayerFacetTargetAdapter layerFacetTargetAdapter;

	private LayerFrameCreator(String generatedLanguage, Language language, int modelLevel, File importsFile) {
		this.generatedLanguage = generatedLanguage;
		builder.register(Node.class, layerNodeAdapter = new LayerNodeAdapter(generatedLanguage, modelLevel, language, initNode));
		layerFacetTargetAdapter = new LayerFacetTargetAdapter(generatedLanguage, language, modelLevel);
		builder.register(FacetTarget.class, layerFacetTargetAdapter);
		builder.register(Variable.class, variableAdapter = new LayerVariableAdapter(language, generatedLanguage, modelLevel, importsFile));
	}

	public LayerFrameCreator(CompilerConfiguration conf) {
		this(conf.generatedLanguage(), conf.getLanguage(), conf.level(), conf.getImportsFile());
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		layerNodeAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		final Frame frame = new Frame().addTypes(LAYER).addFrame(GENERATED_LANGUAGE, generatedLanguage);
		layerNodeAdapter.setInitNode(initNode);
		createFrame(frame, node);
		addNodeImports(frame);
		final String aPackage = node.facetTarget() != null ? addPackage(node.facetTarget(), frame) : addPackage(frame);
		return new AbstractMap.SimpleEntry<>(aPackage + DOT + Format.javaValidName().format(node.name()).toString() + facetName(node.facetTarget()), frame);
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target() : "";
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget) {
		final Frame frame = new Frame().addTypes(LAYER).addFrame(GENERATED_LANGUAGE, generatedLanguage);
		layerFacetTargetAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		createFrame(frame, facetTarget);
		addFacetImports(frame);
		return new AbstractMap.SimpleEntry<>(addPackage(facetTarget, frame) + DOT +
			Format.javaValidName().format(facetTarget.owner().name() + facetTarget.targetNode().name()).toString(), frame);
	}

	private void addNodeImports(Frame frame) {
		Set<String> set = new HashSet<>(layerNodeAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		frame.addFrame(IMPORTS, set.toArray(new String[set.size()]));
	}

	private void addFacetImports(Frame frame) {
		Set<String> set = new HashSet<>(layerFacetTargetAdapter.getImports());
		set.addAll(variableAdapter.getImports());
		frame.addFrame(IMPORTS, set.toArray(new String[set.size()]));
	}

	private void createFrame(Frame frame, Node node) {
		if (node instanceof NodeReference || node.is(Tag.Instance)) return;
		frame.addFrame(NODE, builder.build(node));
	}

	private void createFrame(Frame frame, FacetTarget node) {
		frame.addFrame(NODE, builder.build(node));
	}

	private String addPackage(Frame frame) {
		String packagePath = generatedLanguage.toLowerCase();
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = NameFormatter.composeLayerPackagePath(target, generatedLanguage);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}
}
