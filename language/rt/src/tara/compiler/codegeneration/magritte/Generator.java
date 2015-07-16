package tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.model.NodeReference;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

public class Generator implements TemplateTags {

	protected boolean isDefinition(Node node, int level) {
		if (level == 1)
			return !node.isTerminal() && (node.isFeatureInstance() || isInFeatureInstance(node) || node.isTerminalInstance());
		else if (level == 2)
			return !node.isTerminal() && (node.isFeature() || isInFeature(node.container()));
		return false;
	}

	private static boolean isInFeatureInstance(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeatureInstance() || ((Node) nodeContainer).isTerminal())
				return true;
			else nodeContainer = nodeContainer.container();
		return false;
	}

	protected void addComponents(NodeContainer target, Frame frame, Adapter.FrameContext<FacetTarget> context) {
		if (target instanceof NodeReference) return;
		target.components().stream().
			filter(inner -> !inner.isAnonymous()).
			forEach(inner -> frame.addFrame(NODE, context.build(inner)));
	}

	private static boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature() || ((Node) nodeContainer).isTerminal())
				return true;
			else nodeContainer = nodeContainer.container();
		return false;
	}

	protected FacetTarget isInFacetTarget(Node node) {
		NodeContainer container = node.container();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.container();
		return null;
	}
}
