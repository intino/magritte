package io.intino.magritte.framework;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Predicate {

	protected final String id;
	final Set<String> typeNames = new LinkedHashSet<>();

	Predicate(String id) {
		this.id = id;
	}

	@SuppressWarnings("WeakerAccess")
	public static String nameOf(String id) {
		String shortName = id.contains(".") ? id.substring(id.lastIndexOf(".") + 1) : id;
		shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
		shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
		return shortName;
	}

	public static String stashOf(String id) {
		return id.substring(0, id.indexOf("#"));
	}

	public static String fullNameOf(String id) {
		return id.substring(id.indexOf("#") + 1);
	}

	public String rootNodeId() {
		int pos = id.indexOf("$");
		return pos != -1 ? id.substring(0, pos) : id;
	}

	public String fullName() {
		return fullNameOf(id);
	}

	public String id() {
		return id;
	}

	public String name() {
		return nameOf(this.id);
	}

	public abstract List<Concept> conceptList();

	void putType(Concept concept) {
		typeNames.add(concept.id);
	}

	public abstract List<Node> componentList();

	public abstract Map<String, List<?>> variables();

	public abstract <T extends Layer> List<T> findNode(Class<T> aClass);
}
