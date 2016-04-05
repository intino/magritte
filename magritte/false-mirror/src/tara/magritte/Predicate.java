package tara.magritte;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Predicate {

    protected final String id;
    protected final Set<String> typeNames = new LinkedHashSet<>();

    public Predicate(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public String name() {
        String shortName = id.contains(".") ? id.substring(id.lastIndexOf(".") + 1) : id;
        shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
        shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
        return shortName;
    }

    public abstract List<Concept> types();

    protected void putType(Concept concept) {
        typeNames.add(concept.id());
    }

    protected void deleteType(Concept concept) {
        typeNames.remove(concept.id());
    }

    public abstract List<Instance> components();

    public abstract Map<String, List<?>> variables();

	public abstract <T extends Layer> List<T> findInstance(Class<T> aClass);
}
