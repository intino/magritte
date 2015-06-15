package siani.tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.codegeneration.magritte.MorphVariableAdapter;
import siani.tara.compiler.codegeneration.magritte.TemplateTags;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Allow;

import java.util.AbstractMap;
import java.util.Map;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.composeMorphPackagePath;


public class MorphFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String generatedLanguage;
	private Node initNode = null;
	private MorphNodeAdapter morphNodeAdapter;

	public MorphFrameCreator(String project, String generatedLanguage, Language language, int modelLevel) {
		this.generatedLanguage = generatedLanguage;
		builder.register(NodeImpl.class, morphNodeAdapter = new MorphNodeAdapter(project, generatedLanguage, language, initNode, modelLevel));
		builder.register(FacetTarget.class, new MorphFacetTargetAdapter(project, generatedLanguage, modelLevel));
		builder.register(Variable.class, new MorphVariableAdapter(generatedLanguage, modelLevel));
		builder.register(Allow.Parameter.class, new MorphParameterAdapter(generatedLanguage));
	}

	public MorphFrameCreator(CompilerConfiguration conf) {
		this(conf.getProject(), conf.getGeneratedLanguage(), conf.getLanguage(), conf.getLevel());
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		morphNodeAdapter.setInitNode(initNode);
		final Frame frame = new Frame().addTypes(MORPH);
		String packagePath = addPackage(frame, node);
		createMorph(frame, node);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + node.getName(), frame);
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget) {
		final Frame frame = new Frame().addTypes(MORPH);
		String packagePath = addPackage(facetTarget, frame);
		createFacetTargetMorph(frame, facetTarget);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + ((Node) facetTarget.getContainer()).getName() + "_" + facetTarget.getTargetNode().getName(), frame);
	}

	private void createFacetTargetMorph(Frame frame, FacetTarget node) {
		frame.addFrame("node", builder.build(node));
	}

	private void createMorph(Frame frame, Node node) {
		if (node instanceof NodeReference || node.isTerminalInstance() || node.isFeatureInstance()) return;
		frame.addFrame("node", builder.build(node));
	}

	private String addPackage(Frame frame, Node node) {
		String packagePath = composeMorphPackagePath(generatedLanguage) + (node.isFacet() ? DOT + node.getName().toLowerCase() : "");
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = composeMorphPackagePath(target, generatedLanguage);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

}
