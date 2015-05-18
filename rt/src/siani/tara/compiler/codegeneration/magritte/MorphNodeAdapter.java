package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getTypes;

public class MorphNodeAdapter implements Adapter<NodeImpl>, TemplateTags {
	private final String project;
	private final String module;
	private final Language language;
	private final Locale locale;
	private Node initNode;

	public MorphNodeAdapter(String project, String module, Language language, Locale locale, Node initNode) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.locale = locale;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, NodeImpl node, FrameContext context) {
		frame.addTypes(getTypes(node, language));
		addNodeInfo(node, frame);
		addInner(node, frame, context);
	}

	private void addNodeInfo(Node node, Frame frame) {
		if (initNode != null && !node.equals(initNode)) frame.addFrame(INNER, "");
		if (node.getDoc() != null) frame.addFrame(DOC, node.getDoc());
		addName(node, frame);
		addParent(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addVariables(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addComponents(node, frame);
	}

	private void addName(Node node, Frame frame) {
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, getQn(node)).addFrame(PROJECT, project);
	}

	private void addInner(Node node, Frame frame, FrameContext context) {
		for (Node inner : node.getIncludedNodes())
			if (!(inner instanceof NodeReference) && !inner.isAnonymous())
				frame.addFrame("node", context.build(inner));
	}

	private void addParent(Node node, Frame newFrame) {
		if (node.getParent() != null)
			newFrame.addFrame(PARENT, getQn(node.getParent()));
		else newFrame.addFrame(PARENT, isDefinition(node) ? DEFINITION : MORPH);
	}

	private boolean isDefinition(Node node) {//TODO si no va a llegar a M0. saber además si la Feature no es de M1
		for (Tag tag : node.getFlags())
			if (tag.equals(Tag.FEATURE)) return true;
		return isInFeature(node.getContainer());

	}

	private boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature()) return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	private String getQn(Node node) {
		return NameFormatter.composeMorphPackagePath(node, locale, module) + DOT + node.getQualifiedName();
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getFacets())
			newFrame.addFrame(FACETS, new Frame(null).addTypes(getTypes(facet)).addFrame(NAME, facet.getType()));
	}

	private void addComponents(Node node, Frame frame) {
		for (Node include : node.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame(null).addTypes(collectReferenceTypes(include));
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			} else if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame("component", includeFrame);
		}
	}

	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.getName());
		frame.addFrame(QN, getQn(reference)).addFrame(PROJECT, project);
	}

	private String[] collectReferenceTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.isRequired()) types.add("required");
		for (Tag tag : node.getFlags()) types.add(tag.name());
		return types.toArray(new String[types.size()]);
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getFacetTargets())
			newFrame.addFrame(TARGETS, new Frame(null).addTypes(getTypes(target)).addFrame(NAME, target.getTarget()));
	}

	protected void addVariables(Node node, final Frame frame) {
		boolean definition = isDefinition(node);
		for (final Variable variable : node.getVariables())
			if (!variable.isInherited()) {
				Frame varFrame = createVarFrame(variable);
				if (definition) varFrame.addTypes("definition");
				frame.addFrame(VARIABLE, varFrame);
			}
	}


	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame(null) {
			{
				addFrame(NAME, variable.getName());
				addFrame(TYPE, variable instanceof VariableReference ? getQn(((VariableReference) variable).getDestiny()) : getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					TODO addFrame(MEASURE, variable.getContract());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.getType();
			}
		};

		return frame.addTypes(MorphCreatorHelper.getTypes(variable));
	}

}