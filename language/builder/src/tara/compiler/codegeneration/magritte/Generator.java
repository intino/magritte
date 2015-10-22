package tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.language.model.*;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;

public abstract class Generator implements TemplateTags {


	protected void addComponents(Frame frame, NodeContainer nodeContainer, Adapter.FrameContext<FacetTarget> context) {
		if (nodeContainer instanceof NodeReference) return;
		nodeContainer.components().stream().
			filter(inner -> !inner.isAnonymous() && (!inner.isReference() || (((NodeReference) inner).isHas()))).
			forEach(inner -> {
				final Frame nodeFrame = (Frame) context.build(inner);
				nodeFrame.addTypes(OWNER);
				frame.addFrame(NODE, nodeFrame);
			});
	}

	protected String getType(Variable variable, String generatedLanguage) {
		if (variable instanceof VariableReference)
			return getQn(((VariableReference) variable).getDestiny(), generatedLanguage.toLowerCase());
		else if (variable.type().equals(Primitive.WORD))
			return variable.contract() != null && !variable.contract().isEmpty() ?
				generatedLanguage.toLowerCase() + ".words." + NameFormatter.firstUpperCase(variable.contract()) :
				NameFormatter.firstUpperCase(variable.name()).toString();
		else return variable.type().javaName();
	}

	protected FacetTarget isInFacetTarget(Node node) {
		NodeContainer container = node.container();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.container();
		return null;
	}
}
