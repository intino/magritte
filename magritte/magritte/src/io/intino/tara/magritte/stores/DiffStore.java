package io.intino.tara.magritte.stores;

import io.intino.tara.io.Node;
import io.intino.tara.io.Stash;
import io.intino.tara.magritte.Predicate;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.util.stream.Collectors.*;

public class DiffStore extends FileSystemStore {

	private static MessageDigest md;
	private Map<String, String> oldChecksums;
	private Map<String, String> newChecksums = new HashMap<>();
	private Map<String, Action> changeMap;

	public DiffStore(File file) {
		super(file);
		oldChecksums = readOldChecksums();
	}

	private static String calculateChecksum(Object object) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(baos.toByteArray());
			return DatatypeConverter.printHexBinary(thedigest);
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static boolean isUUID(String str) {
		return str.length() == 36 && str.charAt(8) == '-' && str.charAt(13) == '-' && str.charAt(18) == '-' && str.charAt(23) == '-';
	}

	private Map<String, String> readOldChecksums() {
		try {
			return Files.lines(new File(directory(), ".checksum").toPath())
					.collect(toMap(l -> l.split("@")[0], l -> l.split("@")[1]));
		} catch (IOException e) {
			return Collections.emptyMap();
		}
	}

	@Override
	public Stash stashFrom(String path) {
		Stash stash = super.stashFrom(path);
		Stash clone = super.stashFrom(path);
		calculateChecksums(stash, clone);
		changeMap = null;
		return stash;
	}

	private void calculateChecksums(Stash stash, Stash clone) {
		for (int i = 0; i < stash.nodes.size(); i++) put(checksumOf(clone.nodes.get(i)), stash.nodes.get(i).name);
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
		writeNewChecksums();
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

	public boolean isModified(io.intino.tara.magritte.Node node) {
		return isModified(node.id());
	}

	public boolean isModified(String nodeId) {
		return calculateMap().containsKey(nodeId) && calculateMap().get(nodeId) == Action.Modified;
	}

	public boolean isCreated(io.intino.tara.magritte.Node node) {
		return isCreated(node.id());
	}

	public boolean isCreated(String nodeId) {
		return calculateMap().containsKey(nodeId) && calculateMap().get(nodeId) == Action.Created;
	}

	public boolean isRemoved(io.intino.tara.magritte.Node node) {
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
			Files.write(new File(directory(), ".checksum").toPath(),
					newChecksums.entrySet().stream()
							.map(e -> e.getKey() + "@" + e.getValue())
							.collect(joining("\n")).getBytes()
			);
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
