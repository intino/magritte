package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Primitives;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQn;

public class MorphFacetTargetAdapter implements org.siani.itrules.Adapter<FacetTarget>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private int modelLevel;

	public MorphFacetTargetAdapter(String project, String generatedLanguage, int modelLevel) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		frame.addTypes("nodeimpl");
		addFacetTargetInfo(target, frame);
		addInner(target, frame, context);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addParent(target, frame);
		addVariables(target, frame);
		addComponents(target, frame);
	}

	private void addInner(FacetTarget target, Frame frame, FrameContext<FacetTarget> context) {
		for (Node inner : target.getIncludedNodes()) {
			if (inner instanceof NodeReference || inner.isAnonymous()) continue;
			frame.addFrame("node", context.build(inner));
		}
	}

	private void addName(FacetTarget node, Frame frame) {
		frame.addFrame(NAME, node.getTargetNode().getName());
		frame.addFrame(QN, node.getTargetNode().getQualifiedName()).addFrame(PROJECT, project);
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		Node parent = findParentTargetInContext(target);
		newFrame.addFrame(PARENT, parent == null ? getQn(target, generatedLanguage) : parent.getName());
	}

	private Node findParentTargetInContext(FacetTarget target) {
		Node container = (Node) target.getContainer();
		Node targetNode = target.getTargetNode();
		FacetTarget parentTarget = null;
		Node parent = targetNode.getParent();
		while (parent != null) {
			parentTarget = findInTargets(container, parent);
			if (parentTarget != null) break;
			parent = parent.getParent();
		}
		return parentTarget == null ? null : parentTarget.getTargetNode();
	}

	private FacetTarget findInTargets(Node container, Node parent) {
		for (FacetTarget facetTarget : container.getFacetTargets())
			if (facetTarget.getTargetNode().equals(parent)) return facetTarget;
		return null;
	}

	protected void addVariables(FacetTarget target, final Frame frame) {
		for (final Variable variable : target.getVariables()) frame.addFrame(VARIABLE, createVarFrame(variable));
	}

	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.getName());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				addFrame(CONTRACT, format(variable.getContract()));
				addFrame(TYPE, getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					addFrame(MEASURE_TYPE, ((Attribute) variable).getMeasureType());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String format(String contract) {
				if (contract == null) return "";
				final int i = contract.indexOf(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
				return (i >= 0) ? contract.substring(0, i) : contract;
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				if (variable instanceof VariableReference)
					return getQn(((VariableReference) variable).getDestiny(), generatedLanguage);
				else return variable.getType();
			}
		};
		return frame.addTypes(MorphCreatorHelper.getTypes(variable, modelLevel));
	}

	private void addComponents(FacetTarget target, Frame frame) {
		for (Node include : target.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(collectReferenceTypes(include));
			if (!isInFeature(include)) includeFrame.addFrame(DEFINITION, "");
			if (!isInFeature(include)) includeFrame.addFrame(DEFINITION_AGGREGABLE, "");
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			}
			if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame(COMPONENT, includeFrame);
		}
	}

	private boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature()) return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	private void addName(Node node, Frame frame) {
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, node.getQualifiedName()).addFrame(PROJECT, project);
	}


	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.getName());
		frame.addFrame(QN, getQn(reference, generatedLanguage));
		frame.addFrame(PROJECT, project);
	}

	private String[] collectReferenceTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.isRequired()) types.add("required");
		if (node.isFeature()) types.add("feature");
		return types.toArray(new String[types.size()]);
	}


}
