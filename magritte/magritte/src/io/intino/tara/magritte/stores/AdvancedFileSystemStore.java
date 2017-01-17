package io.intino.tara.magritte.stores;

import io.intino.tara.io.Node;
import io.intino.tara.io.Stash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class AdvancedFileSystemStore extends FileSystemStore {

	private static final String SEP = ";";

	private Map<String, List<ResourceModification>> resources = new HashMap<>();

	public AdvancedFileSystemStore(File file) {
		super(file);
		if (commitFile().exists()) loadCommit();
		processCommit();
	}

	private void processCommit() {
		List<ResourceModification> list = resources.values().stream().flatMap(Collection::stream).collect(toList());
		list.forEach(r -> remove(r.newUrl));
		if (!list.isEmpty())
			getGlobal().warning(list.size() + " resources have been removed since owners were not saved before");
		resources.clear();
		commitFile().delete();
	}

	@Override
	public void writeStash(Stash stash, String path) {
		super.writeStash(stash, path);
		processModification(stash.nodes);
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, io.intino.tara.magritte.Node node) {
		URL newUrl = super.writeResource(inputStream, buildNewPath(newPath), oldUrl, node);
		registerModification(node.id(), newUrl, oldUrl);
		writeCommit();
		return newUrl;
	}

	private String buildNewPath(String newPath) {
		return newPath + "_" + randomUUID();
	}

	private void registerModification(String nodeId, URL newUrl, URL oldUrl) {
		if (!resources.containsKey(nodeId))
			resources.put(nodeId, new ArrayList<>());
		resources.get(nodeId).add(new ResourceModification(newUrl, oldUrl));
	}

	private void processModification(List<Node> nodes) {
		nodes.forEach(this::processModification);
	}

	private void processModification(Node node) {
		if (resources.containsKey(node.name)) removeOldPathIn(node.name);
		processModification(node.nodes);
	}

	private void removeOldPathIn(String name) {
		resources.get(name).forEach(r -> remove(r.oldUrl));
		resources.remove(name);
		writeCommit();
	}

	private void remove(URL oldUrl) {
		try {
			if (oldUrl == null || !oldUrl.getProtocol().contains("file")) return;
			File oldFile = new File(oldUrl.toURI());
			if (!oldFile.getAbsolutePath().startsWith(file.getAbsolutePath())) return;
			if (!oldFile.delete()) getGlobal().severe("Url " + oldUrl.toString() + " could not be deleted");
		} catch (URISyntaxException e) {
			getGlobal().severe(e.getCause().getMessage());
		}
	}

	private void writeCommit() {
		StringBuilder content = new StringBuilder();
		resources.entrySet().forEach(e -> e.getValue()
				.forEach(r -> content.append(e.getKey()).append(SEP).append(r.newUrl).append(SEP).append(r.oldUrl).append("\n")));
		try {
			Files.write(commitFile().toPath(), content.toString().getBytes());
		} catch (IOException e) {
			getGlobal().severe("Commit file could not be written. Reason: " + e.getCause().getMessage());
		}
	}

	private void loadCommit() {
		try {
			List<String> resources = Files.readAllLines(commitFile().toPath());
			resources.stream().filter(s -> !s.isEmpty()).forEach(s -> {
				String[] split = s.split(SEP);
				registerModification(split[0], urlOf(split[1]), urlOf(split[2]));
			});
		} catch (IOException e) {
			getGlobal().log(Level.SEVERE,"Commit file could not be loaded. Reason: " + e.getMessage(), e);
		}
	}

	private URL urlOf(String url) {
		try {
			return !url.equals("null") ? new URL(url) : null;
		} catch (MalformedURLException e) {
			getGlobal().severe("Url is malformed " + url + ". Cause: " + e.getCause().getMessage());
			return null;
		}
	}

	private File commitFile() {
		return fileOf(".commit");
	}

	private class ResourceModification {

		URL newUrl;
		URL oldUrl;

		ResourceModification(URL newUrl, URL oldUrl) {
			this.newUrl = newUrl;
			this.oldUrl = oldUrl;
		}

	}
}
