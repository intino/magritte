package io.intino.tara.magritte;

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

    public String id() {
        return id;
    }

    public String name() {
		return nameOf(this.id);
    }

	@SuppressWarnings("WeakerAccess")
	public static String nameOf(String id) {
		String shortName = id.contains(".") ? id.substring(id.lastIndexOf(".") + 1) : id;
		shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
		shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
		return shortName;
	}

	public abstract List<Concept> conceptList();

    void putType(Concept concept) {
        typeNames.add(concept.id);
    }

    void deleteType(Concept concept) {
        typeNames.remove(concept.id());
    }

    public abstract List<Node> componentList();

    public abstract Map<String, List<?>> variables();

	public abstract <T extends Layer> List<T> findNode(Class<T> aClass);
}
