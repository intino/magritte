package tara.magritte.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class AnchorHandler {

	private static final Logger LOG = Logger.getLogger(AnchorHandler.class.getName());
	private static final int ANCHOR = 0;
	private static final int CONCEPT_ID = 1;

	Map<String, Map<String, String>> anchors = new LinkedHashMap<>();
	Map<String, Map<String, String>> concepts = new LinkedHashMap<>();

	public AnchorHandler(URL url) {
		process(contentOf(url));
	}

	public String anchorOf(String concept, String build) {
		return anchors.get(build).get(concept);
	}

	public String conceptOf(String anchor, String build) {
		return concepts.get(build).get(anchor);
	}

	public String last(String anchor) {
		return conceptOf(anchor, new ArrayList<>(concepts.keySet()).get(concepts.size() - 1));
	}

	private void process(String content) {
		final String[] build = {""};
		asList(content.split("\n")).forEach(line -> {
			if(line.startsWith("#")) init(build[0] = line.substring(1));
			else put(build[0], line);
		});
	}

	private void init(String buildId) {
		anchors.put(buildId, new HashMap<>());
		concepts.put(buildId, new HashMap<>());
	}

	private void put(String build, String line) {
		String[] entry = line.split(":");
		anchors.get(build).put(entry[CONCEPT_ID], entry[ANCHOR]);
		concepts.get(build).put(entry[ANCHOR], entry[CONCEPT_ID]);
	}

	private String contentOf(URL url) {
		try {
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) builder.append(line).append("\n");
			reader.close();
			return builder.toString();
		} catch (IOException e) {
			LOG.severe("Anchor at " + url + " could not be opened. Cause: " + e.getCause().getMessage());
			return "";
		}
	}
}
