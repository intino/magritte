package monet.tara.compiler.codegeneration.render;

import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.HashMap;
import java.util.Map;

public class JFlexRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(DefaultRender.class.getName());

	private String tplName;
	private String projectName;
	private Map<String, String> identifierMap;
	private StringBuilder keywords = new StringBuilder();
	private StringBuilder keywords2 = new StringBuilder();

	public JFlexRender(String projectName, String tplName, String path, Map<String, String> identifierMap) {
		super(new Logger(), path);
		this.tplName = tplName;
		this.projectName = projectName;
		this.identifierMap = identifierMap;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("projectName", projectName.substring(0, 1).toLowerCase() + projectName.substring(1));
		addMark("projectNameFile", RenderUtils.toProperCase(projectName));
		addMark("fileName", tplName);
		for (String identifier : identifierMap.keySet()) {
			addConceptToStringBuilder(keywords, identifier, identifierMap.get(identifier));
			addConceptForBNFToStringBuilder(keywords2, identifier);
		}
		addMark("concepts", keywords.toString());
		addMark("conceptsToBNF", keywords2.toString());

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
