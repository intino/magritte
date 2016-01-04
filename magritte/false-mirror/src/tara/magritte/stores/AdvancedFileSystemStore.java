package tara.magritte.stores;

import tara.io.Instance;
import tara.io.Stash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class AdvancedFileSystemStore extends FileSystemStore {

	private static final Logger LOG = Logger.getLogger(AdvancedFileSystemStore.class.getName());
	private static final String SEP = ";";

	Map<String, List<ResourceModification>> resources = new HashMap<>();

	public AdvancedFileSystemStore(File file) {
		super(file);
		if (commitFile().exists()) loadCommit();
		processCommit();
	}

	private void processCommit() {
		List<ResourceModification> list = resources.values().stream().flatMap(Collection::stream).collect(toList());
		list.forEach(r -> remove(r.newUrl));
		if (!list.isEmpty())
			LOG.warning(list.size() + " resources have been removed since owners were not saved before");
		resources.clear();
		commitFile().delete();
	}

	@Override
	public void writeStash(Stash stash, String path) {
		super.writeStash(stash, path);
		processModification(stash.instances);
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, tara.magritte.Instance instance) {
		URL newUrl = super.writeResource(inputStream, buildNewPath(newPath), oldUrl, instance);
		registerModification(instance.name(), newUrl, oldUrl);
		writeCommit();
		return newUrl;
	}

	private String buildNewPath(String newPath) {
		return newPath + "_" + randomUUID();
	}

	private void registerModification(String instanceId, URL newUrl, URL oldUrl) {
		if (!resources.containsKey(instanceId))
			resources.put(instanceId, new ArrayList<>());
		resources.get(instanceId).add(new ResourceModification(newUrl, oldUrl));
	}

	private void processModification(List<Instance> instances) {
		instances.forEach(this::processModification);
	}

	private void processModification(Instance instance) {
		if (resources.containsKey(instance.name)) removeOldPathIn(instance.name);
		processModification(instance.facets.stream().map(f -> f.instances).flatMap(Collection::stream).collect(toList()));
	}

	private void removeOldPathIn(String name) {
		resources.get(name).forEach(r -> remove(r.oldUrl));
		resources.remove(name);
		writeCommit();
	}

	private void remove(URL oldUrl) {
		try {
			if(oldUrl == null) return;
			File oldFile = new File(oldUrl.toURI());
			if(!oldFile.getAbsolutePath().startsWith(file.getAbsolutePath())) return;
			if (!oldFile.delete()) LOG.severe("Url " + oldUrl.toString() + " could not be deleted");
		} catch (URISyntaxException e) {
			LOG.severe(e.getCause().getMessage());
		}
	}

	private void writeCommit() {
		StringBuilder content = new StringBuilder();
		resources.entrySet().forEach(e -> e.getValue()
				.forEach(r -> content.append(e.getKey()).append(SEP).append(r.newUrl).append(SEP).append(r.oldUrl).append("\n")));
		try {
			Files.write(commitFile().toPath(), content.toString().getBytes());
		} catch (IOException e) {
			LOG.severe("Commit file could not be written. Reason: " + e.getCause().getMessage());
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
			LOG.severe("Commit file could not be loaded. Reason: " + e.getCause().getMessage());
		}
	}

	private URL urlOf(String url) {
		try {
			return !url.equals("null") ? new URL(url) : null;
		} catch (MalformedURLException e) {
			LOG.severe("Url is malformed " + url + ". Cause: " + e.getCause().getMessage());
			return null;
		}
	}

	private File commitFile() {
		return fileOf(".commit");
	}

	private class ResourceModification {

		URL newUrl;
		URL oldUrl;

		public ResourceModification(URL newUrl, URL oldUrl) {
			this.newUrl = newUrl;
			this.oldUrl = oldUrl;
		}

	}
}
