package tara.compiler.refactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tara.io.refactor.Refactors;
import tara.io.refactor.RefactorsSerializer;
import tara.lang.model.Refactorizable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefactorsManager {
	private static final Logger LOG = Logger.getLogger(RefactorsManager.class.getName());


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

	public void commitRefactors(List<Refactorizable> refactorizables) {
		if (refactors == null) refactors = new Refactors();
		for (Refactorizable node : refactorizables) {
			final String oldQn = anchors.get(node.anchor());
			if (oldQn != null && !oldQn.equals(node.cleanQn()))
				refactors.add(new Refactors.Refactor(node.anchor(), oldQn.replace("$", "."), node.cleanQn().replace("$", ".")));
		}
		save(refactors);
	}

	public void updateAnchors(List<Refactorizable> nodes) {
		anchors = new LinkedHashMap<>();
		for (Refactorizable node : nodes) anchors.put(node.anchor(), node.cleanQn());
		save(anchors);
	}

	private void save(Map<String, String> anchors) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (!anchorsFile.exists()) anchorsFile.getParentFile().mkdirs();
		try {
			Files.write(anchorsFile.toPath(), gson.toJson(anchors).getBytes());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void save(Refactors refactors) {
		final byte[] bytes = RefactorsSerializer.serialize(refactors);
		if (!refactorsFile.exists()) refactorsFile.getParentFile().mkdirs();
		try {
			Files.write(refactorsFile.toPath(), bytes);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
