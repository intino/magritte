package tara.magritte;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Predicate {

    protected final String name;
    protected final Set<String> typeNames = new LinkedHashSet<>();

    public Predicate(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String simpleName() {
        String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
        shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
        shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
        return shortName;
    }

    public abstract List<Concept> types();

    protected void putType(Concept concept) {
        typeNames.add(concept.name());
    }

    protected void deleteType(Concept concept) {
        typeNames.remove(concept.name());
    }

    public abstract List<Instance> components();

    public abstract Map<String, List<?>> variables();

	public abstract <T extends Layer> List<T> findInstance(Class<T> aClass);
}
