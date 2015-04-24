package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Assumption;

import java.util.*;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.composeMorphPackagePath;


public class MorphFrameCreator implements TemplateTags {

	private final String project;
	private final String module;
	private final Language language;
	private final Locale locale;
	private Node initNode = null;
	Set<String> imports = new HashSet<>();

	public MorphFrameCreator(String project, String module, Language language, Locale locale) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.locale = locale;
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		final Frame frame = new Frame(MORPH);
		String packagePath = addPackage(node, frame);
		createMorph(frame, node);
		addImports(frame);
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + node.getName(), frame);
	}

	public Map.Entry<String, Frame> create(FacetTarget facetTarget) {
		final Frame frame = new Frame(MORPH);
		String packagePath = addPackage(facetTarget, frame);
		createFacetTargetMorph(frame, facetTarget);
		addImports(frame);
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + facetTarget.getTargetNode().getName(), frame);
	}

	private void createFacetTargetMorph(Frame frame, FacetTarget node) {
		FrameBuilder builder = new FrameBuilder();
		builder.register(FacetTarget.class, new MorphFacetTargetAdapter(project, module, imports, locale));
		frame.addFrame("node", builder.build(node));
	}

	private void createMorph(Frame frame, Node node) {
		FrameBuilder builder = new FrameBuilder();
		builder.register(NodeImpl.class, new MorphNodeAdapter(project, module, language, imports, locale, initNode));
		if (node instanceof NodeReference || node.isTerminalInstance() || isFeatureInstance(node)) return;
		frame.addFrame("node", builder.build(node));
	}

	private String addPackage(Node node, Frame frame) {
		String packagePath = composeMorphPackagePath(node, locale, module);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private String addPackage(FacetTarget target, Frame frame) {
		String packagePath = composeMorphPackagePath(target, locale, module);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		return packagePath;
	}

	private boolean isFeatureInstance(Node node) {
		Collection<Assumption> assumptions = language.assumptions(node.getType());
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumption instanceof Assumption.FeatureInstance)
				return true;
		return false;
	}

	private void addImports(Frame frame) {
		for (String anImport : imports)
			frame.addFrame(IMPORTS, IMPORT + anImport + SEMICOLON);
		frame.addFrame(IMPORTS, IMPORT + module.toLowerCase() + DOT + NATIVES + DOT + STAR + SEMICOLON);
	}
}
