package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

public class BoxFacetTargetAdapter implements Adapter<FacetTarget>, TemplateTags {

	@Override
	public void execute(Frame frame, FacetTarget facetTarget, FrameContext<FacetTarget> context) {
		structure(facetTarget, frame);
		facetTargetVariables(facetTarget, frame, context);
	}

	private void structure(FacetTarget facetTarget, Frame newFrame) {
		final NodeContainer container = facetTarget.getContainer();
		newFrame.addFrame(NAME, clean(container.getQualifiedName()) + "+" + clean(facetTarget.getTargetNode().getQualifiedName()));
		addTypes(facetTarget, newFrame);
		newFrame.addFrame(PARENT, clean(container.getQualifiedName()));
	}

	private void facetTargetVariables(FacetTarget facetTarget, final Frame frame, FrameContext<FacetTarget> FrameContext) {
		for (final Variable variable : facetTarget.getVariables())
			frame.addFrame(VARIABLE, FrameContext.build(variable));
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}

	private void addTypes(FacetTarget facetTarget, Frame newFrame) {
		newFrame.addFrame(NODE_TYPE, ((Node) facetTarget.getContainer()).getType());
	}
}
