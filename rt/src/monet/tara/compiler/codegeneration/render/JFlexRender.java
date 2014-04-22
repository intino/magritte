package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.ast.ASTNode;
import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.HashMap;
import java.util.Map;

public class JFlexRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(JFlexRender.class.getName());

	private String tplName;
	private String projectName;
	private Map<String, String> identifierMap;
	private StringBuilder keywords = new StringBuilder();
	private StringBuilder keywords2 = new StringBuilder();
	private ASTWrapper ast;
	private boolean hasCode = false;

	public JFlexRender(String projectName, String tplName, ASTWrapper ast) {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.tplName = tplName;
		this.projectName = projectName;
		this.identifierMap = ast.getIdentifiers();
		this.ast = ast;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("projectName", projectName.toLowerCase());
		addMark("projectNameFile", RenderUtils.toProperCase(projectName));
		addMark("fileName", tplName);
		for (String identifier : identifierMap.keySet()) {
			addConceptToStringBuilder(keywords, identifier, identifierMap.get(identifier));
			addConceptForBNFToStringBuilder(keywords2, identifier);
		}
		addMark("concepts", keywords.toString());
		addMark("conceptsToBNF", keywords2.toString());
		for (ASTNode node : ast.getAST()) goOver(node);
		addCodeMark();
	}

	private void addCodeMark() {
		if (hasCode) {
			addMark("codeStatement", "CODE           = \"\\#\" {DIGIT}+");
			addMark("codeStatementFunction", "{CODE}" +
				"                      {   return " + RenderUtils.toProperCase(projectName) + "Types.CODE;}");
		} else {
			addMark("codeStatement", "");
			addMark("codeStatementFunction", "");
		}
	}

	private void goOver(ASTNode node) {
		if (node != null) {
			if (node.hasCode()) hasCode = true;
			for (ASTNode child : node.getChildren())
				if (!"".equals(child.getIdentifier())) goOver(child);
		}
	}

	private void addConceptForBNFToStringBuilder(StringBuilder keywords2, String identifierKey) {
		Map<String, Object> map = new HashMap<>();
		map.put("conceptKey", identifierKey);
		map.put("projectNameFile", RenderUtils.toProperCase(projectName));
		keywords2.append(block("conceptsToBNF", map));
	}

	private void addConceptToStringBuilder(StringBuilder keywords, String identifierKey, String identifierValue) {
		Map<String, Object> map = new HashMap<>();
		map.put("conceptKey", identifierKey);
		map.put("conceptValue", identifierValue);
		keywords.append(block("concepts", map));
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.severe(String.format(message, args));
		}
	}
}
