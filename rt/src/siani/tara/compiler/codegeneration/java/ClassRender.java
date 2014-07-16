package siani.tara.compiler.codegeneration.java;

import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.HashMap;
import java.util.Map;

public class ClassRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(BoxRender.class.getName());
	private final Node node;


	public ClassRender(Node node) {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.node = node;
	}

	@Override
	protected void init() {
		loadCanvas("/templates/conceptClass", true);
		addMark("identifier", node.getName());
		String parentName = node.getObject().getParentName();
		addMark("parent", (parentName != null) ? parentName : node.getObject().getType());
		addMark("vars", getVarsAsMarks());
	}

	public String getVarsAsMarks() {
		StringBuilder variableBuilder = new StringBuilder();
		for (Variable variable : node.getObject().getVariables()) {
			if (variable.getValue() == null) continue;
			Map<String, Object> map = new HashMap<>();
			map.put("type", variable.getType());
			map.put("name", variable.getName());
			variableBuilder.append(block("vars", map));
		}
		return variableBuilder.toString();
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.severe(String.format(message, args));
		}
	}
}
