package tara.builder;


import tara.language.model.Node;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetadataEnricherProvider {


	public static final String METADATA_FILE = ".metadata";

	public static MetadataEnricher get(File root) {
		File metadataFile = new File(root, METADATA_FILE);
		if (!metadataFile.exists()) return null;
		final List<String> lines = readFile(metadataFile);
		return createMetadataEnricher(toMap(lines));

	}

	private static MetadataEnricher createMetadataEnricher(Map<String, String> map) {
		return qualifiedName -> {
			final String data = map.get(clean(qualifiedName));
			if (data == null) return null;
			final String[] value = data.split(":");
			return new MetadataEnricher.Metadata(value[0], MetadataEnricher.Type.valueOf(firstUpperCase(value[1])));
		};
	}

	private static String firstUpperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}

	private static String clean(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

	private static Map<String, String> toMap(List<String> lines) {
		Map<String, String> map = new HashMap();
		for (String line : lines) {
			final String[] keyAndValue = line.split("=");
			map.put(keyAndValue[0].trim(), keyAndValue[1].trim());
		}
		return map;
	}

	private static List<String> readFile(File metadataFile) {
		try {
			return Files.readAllLines(metadataFile.toPath(), Charset.defaultCharset());
		} catch (IOException ignored) {
		}
		return Collections.emptyList();
	}
}
