package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.model.Variable;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static tara.compiler.codegeneration.magritte.NameFormatter.facetLayerPackage;


public class LayerFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String outDsl;
	private Node initNode = null;
	private LayerNodeAdapter layerNodeAdapter;
	private LayerVariableAdapter variableAdapter;
	private LayerFacetTargetAdapter layerFacetTargetAdapter;

	private LayerFrameCreator(String outDsl, Language language, ModuleType modelLevel) {
		this.outDsl = outDsl;
		builder.register(Node.class, layerNodeAdapter = new LayerNodeAdapter(outDsl, modelLevel, language, initNode));
		layerFacetTargetAdapter = new LayerFacetTargetAdapter(outDsl, language, modelLevel);
		builder.register(FacetTarget.class, layerFacetTargetAdapter);
		builder.register(Variable.class, variableAdapter = new LayerVariableAdapter(language, outDsl, modelLevel));
	}

	public LayerFrameCreator(CompilerConfiguration conf) {
		this(conf.outDsl(), conf.language(), conf.moduleType());
	}

	public Map.Entry<String, Frame> create(Node node) {
		initNode = node;
		layerNodeAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		layerNodeAdapter.setInitNode(initNode);
		final Frame frame = new Frame().addTypes(LAYER).addFrame(GENERATED_LANGUAGE, outDsl);
		createFrame(frame, node);
		addNodeImports(frame);
		final String aPackage = node.facetTarget() != null ? addPackage(node.facetTarget(), frame) : addPackage(frame);
		return new AbstractMap.SimpleEntry<>(aPackage + DOT + Format.javaValidName().format(node.name()).toString() + facetName(node.facetTarget()), frame);
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target() : "";
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget, Node owner) {
		final Frame frame = new Frame().addTypes(LAYER).addFrame(GENERATED_LANGUAGE, outDsl);
		layerFacetTargetAdapter.getImports().clear();
		variableAdapter.getImports().clear();
		createFrame(frame, facetTarget);
		addFacetImports(frame);
		return new AbstractMap.SimpleEntry<>(addPackage(facetTarget, frame) + DOT +
			Format.javaValidName().format(owner.name() + facetTarget.targetNode().name()).toString(), frame);
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

	private void createFrame(Frame frame, FacetTarget facet) {
		frame.addFrame(NODE, builder.build(facet));
	}

	private String addPackage(Frame frame) {
		String packagePath = outDsl.toLowerCase();
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = facetLayerPackage(target, outDsl);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath.substring(0, packagePath.length() - 1));
		return packagePath;
	}
}
