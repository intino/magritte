package tara.compiler.refactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tara.lang.model.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RefactorsManager {

	private final File anchorsFile;
	private final File refactorsFile;
	private Map<String, String> anchors;
	private Refactors refactors;

	public RefactorsManager(File anchorsFile, File refactorsFile, Map<String, String> anchors, Refactors refactors) {
		this.anchorsFile = anchorsFile;
		this.refactorsFile = refactorsFile;
		this.anchors = anchors;
		this.refactors = refactors;
	}

	public void commitRefactors(List<Node> nodes) {
		if (refactors == null) refactors = new Refactors();
		for (Node node : nodes) {
			final String oldQn = anchors.get(node.anchor());
			if (oldQn != null && !oldQn.equals(node.qualifiedNameCleaned()))
				refactors.add(new Refactors.Refactor(refactors.size(), node.anchor(), oldQn, node.qualifiedNameCleaned()));
		}
		save(refactors);
	}

	public void updateAnchors(List<Node> nodes) {
		anchors = new LinkedHashMap<>();
		for (Node node : nodes) anchors.put(node.anchor(), node.qualifiedNameCleaned());
		save(anchors);
	}

	private void save(Map<String, String> anchors) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String txt = gson.toJson(anchors);
		if (!anchorsFile.exists()) anchorsFile.getParentFile().mkdirs();
		try {
			Files.write(anchorsFile.toPath(), txt.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void save(Refactors refactors) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String txt = gson.toJson(refactors);
		if (!refactorsFile.exists()) refactorsFile.getParentFile().mkdirs();
		try {
			Files.write(refactorsFile.toPath(), txt.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
