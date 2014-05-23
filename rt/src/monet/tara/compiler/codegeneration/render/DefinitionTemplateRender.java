package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTNode;
import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefinitionTemplateRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(DefaultRender.class.getName());
	private final String tplName;
	ASTNode astNode;

	public DefinitionTemplateRender(String tplName, ASTNode node) throws TaraException {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.tplName = tplName;
		this.astNode = node;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("ConceptKey", RenderUtils.toProperCase(astNode.getIdentifier()));
		if (!astNode.getVariables().isEmpty())
			addMark("parameters", constructParameters(astNode.getVariables()));
		StringBuilder children = constructChildren(astNode.getInnerConcepts());
		addMark("children", children.toString());

	}

	private StringBuilder constructChildren(List<ASTNode> innerConcepts) {
		StringBuilder builder = new StringBuilder();
		if (innerConcepts==null) return builder;
		for (ASTNode node : innerConcepts)
			if (node.is(ASTNode.AnnotationType.REQUIRED) && !node.getIdentifier().equals("")) {
				Map<String, Object> map = new HashMap<>();
				map.put("ConceptKey", node.getIdentifier());
				map.put("name", node.getIdentifier() + "Definition");
				builder.append(block("innerConcepts", map)).append("\n\n");
			}
		return builder;
	}

	private String constructParameters(List<ASTNode.Variable> variables) {
		String result = "";
		for (ASTNode.Variable variable : variables) result += ", " + variable.getName();
		return result.substring(2);
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.info("From Templation:\n" + String.format(message, args));
		}
	}
}
