package siani.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.Node;
import monet.tara.lang.NodeObject;
import monet.tara.lang.NodeTree;
import monet.tara.lang.Variable;
import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefinitionTemplateRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(DefaultRender.class.getName());
	private final String tplName;
	Node nodeObject;

	public DefinitionTemplateRender(String tplName, Node node) throws TaraException {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.tplName = tplName;
		this.nodeObject = node;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("ConceptKey", RenderUtils.toProperCase(nodeObject.getName()));
		if (!nodeObject.getObject().getVariables().isEmpty())
			addMark("parameters", constructParameters(nodeObject.getObject().getVariables()));
		StringBuilder children = constructChildren(nodeObject.getInnerNodes());
		addMark("children", children.toString());

	}

	private StringBuilder constructChildren(NodeTree innerConcepts) {
		StringBuilder builder = new StringBuilder();
		if (innerConcepts == null) return builder;
		for (Node node : innerConcepts)
			if (node.getObject().is(NodeObject.AnnotationType.REQUIRED) && !node.getName().equals("")) {
				Map<String, Object> map = new HashMap<>();
				map.put("ConceptKey", node.getName());
				map.put("name", node.getName() + "Definition");
				builder.append(block("innerConcepts", map)).append("\n\n");
			}
		return builder;
	}

	private String constructParameters(List<Variable> variables) {
		String result = "";
		for (Variable variable : variables) result += ", " + variable.getName();
		return result.substring(2);
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.info("From Templation:\n" + String.format(message, args));
		}
	}
}
