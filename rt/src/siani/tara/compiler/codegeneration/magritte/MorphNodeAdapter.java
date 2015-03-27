package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.VariableReference;

import java.util.Locale;
import java.util.Set;

import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getNodeContainer;
import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getTypes;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.composeMorphPackagePath;

public class MorphNodeAdapter implements Adapter<NodeImpl>, TemplateTags {
	private final String project;
	private final Language language;
	private final Set<String> imports;
	private final Locale locale;
	private Node initNode;

	public MorphNodeAdapter(String project, Language language, Set<String> imports, Locale locale, Node initNode) {
		this.project = project;
		this.language = language;
		this.imports = imports;
		this.locale = locale;
		this.initNode = initNode;
	}

	@Override
	public void adapt(Frame frame, NodeImpl node, BuilderContext builderContext) {
		frame.add(getTypes(node, language));
		addNodeInfo(node, frame);
		addImports(getNodeContainer(node), locale);
	}

	private void addImports(Node container, Locale locale) {
		if (container != null) {
			String containerPackage = composeMorphPackagePath(container, locale);
			if (!containerPackage.equals(MAGRITTE_MORPHS))
				imports.add(containerPackage + DOT + container.getName());
		}
	}

	private void addNodeInfo(Node node, Frame frame) {
		if (initNode != null && !node.equals(initNode)) {
			frame.addFrame(INNER, "");
			if (node.isAggregated()) frame.addFrame(RELATION, AGGREGATION);
			else frame.addFrame(RELATION, COMPOSITION);
		}
		if (node.getDoc() != null) frame.addFrame(DOC, node.getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, node.getQualifiedName()).addFrame(PROJECT, project);
		addParent(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addVariables(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addHolding(node, frame);
		addComponents(node, frame);
	}

	private void addParent(Node node, Frame newFrame) {
		if (node.getParent() != null) {
			newFrame.addFrame(PARENT, node.getParent().getName().equals(node.getName()) ?
				MAGRITTE_MORPHS + DOT + node.getParent().getQualifiedName() :
				node.getParent().getQualifiedName());
		} else newFrame.addFrame(PARENT, MORPH);
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getFacets())
			newFrame.addFrame(FACETS, new Frame(getTypes(facet)).addFrame(NAME, facet.type()));
	}

	private void addComponents(Node node, Frame frame) {

	}

	private void addHolding(Node node, Frame frame) {

	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getFacetTargets())
			newFrame.addFrame(TARGETS, new Frame(getTypes(target)).addFrame(NAME, target.getTarget()));
	}


	protected void addVariables(Node node, final Frame frame) {
		for (final Variable variable : node.getVariables())
			if (variable instanceof VariableReference && !variable.getDefaultExtension().isEmpty()) {
//				addImports(, locale);
			}
	}

}