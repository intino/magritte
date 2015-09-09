package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Predicate {

    protected final String name;
    protected final Set<String> typeNames = new LinkedHashSet<>();

    public Predicate(String name) {
        this.name = name;
    }

    public static List<String> definitions(Class<? extends Layer> layerClass) {
        return LayerFactory.names(layerClass);
    }

    public String name() {
        return name;
    }

    public String shortName() {
        String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
        shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
        return shortName;
    }

    public abstract List<Definition> types();

    protected void putType(Definition definition) {
        typeNames.add(definition.name());
    }

    protected Layer cloneMorph(Layer layer) {
        try {
            return layer.getClass().getDeclaredConstructor(Declaration.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract List<Declaration> components();

    public abstract Map<String, Object> variables();

    public boolean is(String type) {
        return typeNames.contains(type);
    }

    public boolean is(Class<? extends Layer> layer) {
        return isAnyOf(definitions(layer));
    }

    boolean isAnyOf(List<String> definitions) {
        return definitions.stream().filter(this::is).findFirst().isPresent();
    }

    public abstract <T extends Layer> List<T> findComponent(Class<T> aClass);

    public abstract void variables(Map<String, Object> variables);

}
