package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.TreeWrapper;
import org.monet.templation.CanvasLogger;

import java.util.HashMap;
import java.util.Map;

public class JFlexRender extends DefaultRender {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(JFlexRender.class.getName());

	private Map<String, String> identifierMap;
	private StringBuilder keywords = new StringBuilder();
	private StringBuilder keywords2 = new StringBuilder();

	public JFlexRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.identifierMap = ((TreeWrapper) ast).getIdentifiers();
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("projectName", projectName.toLowerCase());
		addMark("projectProperName", RenderUtils.toProperCase(projectName));
		for (String identifier : identifierMap.keySet()) {
			if (!identifier.endsWith("KEY")) continue;
			addConceptToStringBuilder(keywords, identifier, identifierMap.get(identifier));
			addConceptForBNFToStringBuilder(keywords2, identifier);
		}
		addMark("concepts", keywords.toString());
		addMark("conceptsToBNF", keywords2.toString());
	}

	private void addConceptForBNFToStringBuilder(StringBuilder keywords2, String identifierKey) {
		Map<String, Object> map = new HashMap<>();
		map.put("conceptKey", identifierKey);
		map.put("projectProperName", RenderUtils.toProperCase(projectName));
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
