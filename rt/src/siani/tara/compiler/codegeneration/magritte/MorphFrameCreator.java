package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Assumption;

import java.io.File;
import java.util.*;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.composeMorphPackagePath;


public class MorphFrameCreator implements TemplateTags {

	private final FrameBuilder builder = new FrameBuilder();
	private final String generatedLanguage;
	private final Language language;
	private final File nativePath;
	private Node initNode = null;
	Set<String> imports = new HashSet<>();
	private MorphNodeAdapter morphNodeAdapter;

	public MorphFrameCreator(String project, String generatedLanguage, Language language, Locale locale, File nativePath) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		builder.register(NodeImpl.class, morphNodeAdapter = new MorphNodeAdapter(project, generatedLanguage, language, initNode));
		builder.register(FacetTarget.class, new MorphFacetTargetAdapter(project, generatedLanguage));
		this.nativePath = nativePath;
	}

	public MorphFrameCreator(CompilerConfiguration conf) {
		this(conf.getProject(), conf.getGeneratedLanguage(), conf.getLanguage(), conf.getLocale(), conf.getNativePath());
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		morphNodeAdapter.setInitNode(initNode);
		final Frame frame = new Frame().addTypes(MORPH);
		String packagePath = addPackage(frame);
		createMorph(frame, node);
		addImports(frame);
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + node.getName(), frame);
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget) {
		final Frame frame = new Frame().addTypes(MORPH);
		String packagePath = addPackage(facetTarget, frame);
		createFacetTargetMorph(frame, facetTarget);
		addImports(frame);
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + facetTarget.getTargetNode().getName(), frame);
	}

	private void createFacetTargetMorph(Frame frame, FacetTarget node) {
		frame.addFrame("node", builder.build(node));
	}

	private void createMorph(Frame frame, Node node) {
		if (node instanceof NodeReference || node.isTerminalInstance() || isFeatureInstance(node)) return;
		frame.addFrame("node", builder.build(node));
	}

	private String addPackage(Frame frame) {
		String packagePath = composeMorphPackagePath(generatedLanguage);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = composeMorphPackagePath(target, generatedLanguage);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private boolean isFeatureInstance(Node node) {
		Collection<Assumption> assumptions = language.assumptions(node.getType());
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumption instanceof Assumption.Featureinstance) return true;
		return false;
	}

	private void addImports(Frame frame) {
		for (String anImport : imports)
			frame.addFrame(IMPORTS, IMPORT + anImport + SEMICOLON);
		if (nativePath != null && nativePath.exists())
			frame.addFrame(IMPORTS, IMPORT + generatedLanguage.toLowerCase() + DOT + NATIVES + DOT + STAR + SEMICOLON);
	}
}
