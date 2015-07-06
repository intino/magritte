package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.impl.NodeReference;

public class Generator implements TemplateTags {

	protected boolean isDefinition(Node node, int level) {
		if (level == 1)
			return !node.isTerminal() && (node.isFeatureInstance() || isInFeatureInstance(node) || node.isTerminalInstance());
		else if (level == 2)
			return !node.isTerminal() && (node.isFeature() || isInFeature(node.getContainer()));
		return false;
	}

	private static boolean isInFeatureInstance(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeatureInstance() || ((Node) nodeContainer).isTerminal())
				return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	private static boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature() || ((Node) nodeContainer).isTerminal())
				return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	protected void addComponents(NodeContainer target, Frame frame, Adapter.FrameContext<FacetTarget> context, int level) {
		target.getIncludedNodes().stream().
			filter(inner -> !(inner instanceof NodeReference) && !inner.isAnonymous()).
			forEach(inner -> frame.addFrame(NODE, context.build(inner)));
	}

	protected FacetTarget isInFacetTarget(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.getContainer();
		return null;
	}
}