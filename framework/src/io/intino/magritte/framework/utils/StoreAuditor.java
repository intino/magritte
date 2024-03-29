package io.intino.magritte.framework.utils;

import io.intino.magritte.framework.PersistenceManager;
import io.intino.magritte.framework.Predicate;
import io.intino.magritte.framework.Store;
import io.intino.magritte.framework.stores.FileSystemStore;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.*;

public class StoreAuditor {

	private static final String CHECKSUM_FILE = ".checksum";
	private final Store store;
	private final Map<String, String> oldChecksums;
	private final PersistenceManager manager;
	private final String checksumName;
	private Map<String, String> newChecksums = new HashMap<>();
	private Map<String, Action> changeMap;

	public StoreAuditor(Store store) {
		this(store, defaultPersistenceManager(store), CHECKSUM_FILE);
	}

	public StoreAuditor(Store store, String checksumName) {
		this(store, defaultPersistenceManager(store), checksumName);
	}

	public StoreAuditor(Store fileSystemStore, PersistenceManager manager, String checksumName) {
		this.manager = manager;
		this.checksumName = checksumName;
		this.store = fileSystemStore;
		this.oldChecksums = readOldChecksums();
	}

	private static PersistenceManager defaultPersistenceManager(Store store) {
		return store instanceof FileSystemStore ?
				new PersistenceManager.FilePersistenceManager(((FileSystemStore) store).directory()) :
				new PersistenceManager.InMemoryPersistenceManager();
	}

	private static String calculateChecksum(Node node) {
		return node.hashCode() + "";
	}

	private static boolean isUUID(String str) {
		return str.length() == 36 && str.charAt(8) == '-' && str.charAt(13) == '-' && str.charAt(18) == '-' && str.charAt(23) == '-';
	}

	public void removeTrack(String nodeId) {
		Map.Entry<String, String> entry = oldChecksums.entrySet().stream().filter(e -> e.getValue().equals(nodeId)).findFirst().orElse(null);
		if (entry == null) return;
		oldChecksums.remove(entry.getKey());
		entry = newChecksums.entrySet().stream().filter(e -> e.getValue().equals(nodeId)).findFirst().orElse(null);
		if (entry == null) return;
		newChecksums.remove(entry.getKey());
		changeMap = null;
	}

	private Map<String, String> readOldChecksums() {
		return new BufferedReader(new InputStreamReader(manager.read(checksumName))).lines()
				.collect(toMap(l -> l.split("@")[0], l -> l.split("@")[1]));
	}

	public void commit() {
		writeNewChecksums();
	}

	public void trace(String path) {
		Stash stash = store.stashFrom(path);
		if (stash == null) return;
		calculateChecksums(stash);
		changeMap = null;
	}

	private void calculateChecksums(Stash stash) {
		for (int i = 0; i < stash.nodes.size(); i++) put(checksumOf(stash.nodes.get(i)), stash.nodes.get(i).name);
	}

	private void put(String checksum, String nodeId) {
		if (!newChecksums.containsKey(checksum)) newChecksums.put(checksum, nodeId);
		int counter = 1;
		while (newChecksums.containsKey(checksum + counter++)) ;
		newChecksums.put(checksum, nodeId);
	}

	private String checksumOf(Node node) {
		processNames(node.nodes);
		return calculateChecksum(node);
	}

	private void processNames(List<Node> nodes) {
		for (Node node : nodes) {
			String name = Predicate.nameOf(node.name);
			node.name = isUUID(name) ? "" : name;
			processNames(node.nodes);
		}
	}

	public List<Change> changeList() {
		return calculateMap().entrySet().stream().map(e -> new Change(e.getKey(), e.getValue())).collect(toList());
	}

	private Map<String, Action> calculateMap() {
		if (changeMap != null) return changeMap;
		changeMap = new HashMap<>();
		Set<String> checksums = new HashSet<>(oldChecksums.keySet());
		checksums.addAll(newChecksums.keySet());
		for (String checksum : checksums) {
			if (oldChecksums.containsKey(checksum) && newChecksums.containsKey(checksum)) continue;
			if (oldChecksums.containsKey(checksum)) put(changeMap, oldChecksums.get(checksum), Action.Removed);
			if (newChecksums.containsKey(checksum)) put(changeMap, newChecksums.get(checksum), Action.Created);
		}
		return changeMap;
	}

	public boolean isModified(io.intino.magritte.framework.Node node) {
		return isModified(node.id());
	}

	public boolean isModified(String nodeId) {
		return calculateMap().containsKey(nodeId) && calculateMap().get(nodeId) == Action.Modified;
	}

	public boolean isCreated(io.intino.magritte.framework.Node node) {
		return isCreated(node.id());
	}

	public boolean isCreated(String nodeId) {
		return calculateMap().containsKey(nodeId) && calculateMap().get(nodeId) == Action.Created;
	}

	public boolean isRemoved(io.intino.magritte.framework.Node node) {
		return isRemoved(node.id());
	}

	public boolean isRemoved(String nodeId) {
		return calculateMap().containsKey(nodeId) && calculateMap().get(nodeId) == Action.Removed;
	}

	private void put(Map<String, Action> result, String nodeId, Action action) {
		if (result.containsKey(nodeId)) result.put(nodeId, Action.Modified);
		else result.put(nodeId, action);
	}

	private void writeNewChecksums() {
		try {
			DataOutputStream stream = new DataOutputStream(manager.write(checksumName));
			stream.write(newChecksums.entrySet().stream()
					.map(e -> e.getKey() + "@" + e.getValue())
					.collect(joining("\n")).getBytes());
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public enum Action {Created, Modified, Removed}

	public static class Change {
		String nodeId;
		Action action;

		public Change(String nodeId, Action action) {
			this.nodeId = nodeId;
			this.action = action;
		}

		public String nodeId() {
			return nodeId;
		}

		public Action action() {
			return action;
		}
	}

}
