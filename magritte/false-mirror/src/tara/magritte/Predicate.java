package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Predicate {

    protected final String name;
    protected final Set<String> typeNames = new LinkedHashSet<>();

    public Predicate(String name) {
        this.name = name;
    }

    public static List<String> concepts(Class<? extends Layer> layerClass) {
        return LayerFactory.names(layerClass);
    }

    public String name() {
        return name;
    }

    public String simpleName() {
        String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
        shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : name;
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

    protected Layer cloneMorph(Layer layer) {
        try {
            return layer.getClass().getDeclaredConstructor(Instance.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract List<Instance> components();

    public abstract Map<String, Object> variables();

    public boolean is(String type) {
        return typeNames.contains(type);
    }

    public boolean is(Class<? extends Layer> layer) {
        return isAnyOf(concepts(layer));
    }

    boolean isAnyOf(List<String> concepts) {
        return concepts.stream().filter(this::is).findFirst().isPresent();
    }

    public abstract <T extends Layer> List<T> findComponents(Class<T> aClass);

    public abstract void variables(Map<String, Object> variables);

}
