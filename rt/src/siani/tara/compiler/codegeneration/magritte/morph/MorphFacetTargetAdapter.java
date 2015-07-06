package siani.tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.magritte.Generator;
import siani.tara.compiler.codegeneration.magritte.TemplateTags;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQnOfFacet;

public class MorphFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private final int modelLevel;
	private FrameContext<FacetTarget> context;

	public MorphFacetTargetAdapter(String project, String generatedLanguage, int modelLevel) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		this.context = context;
		frame.addTypes("nodeimpl");
		addFacetTargetInfo(target, frame);
		addComponents(target, frame, context, modelLevel);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addParent(target, frame);
		addVariables(target, frame);
		addComponents(target, frame);
		addComponents(target.getTargetNode().getIncludedNodes(), frame);
	}

	private void addName(FacetTarget node, Frame frame) {
		frame.addFrame(NAME, ((Node) node.getContainer()).getName() + "_" + node.getTargetNode().getName());
		frame.addFrame(QN, node.getTargetNode().getQualifiedName()).addFrame(PROJECT, project);
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		NodeContainer nodeContainer = target.getContainer();
		newFrame.addFrame(PARENT, generatedLanguage.toLowerCase() + "." + getQnOfFacet((Node) nodeContainer));
	}

	protected void addVariables(FacetTarget target, final Frame frame) {
		target.getVariables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> frame.addFrame(VARIABLE, context.build(variable)));
		target.getTargetNode().getVariables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> frame.addFrame(VARIABLE, context.build(variable)));
	}

	private void addComponents(FacetTarget target, Frame frame) {
		for (Node include : target.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(TypesProvider.getTypesOfReference(include));
			if (isDefinition(include, modelLevel) && !isDefinition(target.getTargetNode(), modelLevel))
				includeFrame.addFrame(DEFINITION, "");
			if (!isDefinition(target.getTargetNode(), modelLevel)) includeFrame.addFrame(DEFINITION_AGGREGABLE, "");
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			} else if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame(COMPONENT, includeFrame);
		}
	}

	private void addComponents(List<Node> targetIncludes, Frame frame) {
		for (Node include : targetIncludes) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(TypesProvider.getTypesOfReference(include));
			if (isDefinition(include, modelLevel) && !isDefinition(include, modelLevel))
				includeFrame.addFrame(DEFINITION, "");
			if (!isDefinition(include, modelLevel)) includeFrame.addFrame(DEFINITION_AGGREGABLE, "");
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			} else if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame(COMPONENT, includeFrame);
		}
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


}