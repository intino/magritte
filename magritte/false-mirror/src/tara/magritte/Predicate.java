package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Logger;

public abstract class Predicate {
    protected static final Logger LOG = Logger.getLogger(Predicate.class.getName());

    protected final String name;
    protected final Set<String> typeNames = new LinkedHashSet<>();

    public Predicate(String name) {
        this.name = name;
    }

    public static List<String> definitions(Class<? extends Morph> morphClass) {
        return MorphFactory.names(morphClass);
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

    protected Morph cloneMorph(Morph morph, Declaration aDeclaration) {
        try {
            return morph.getClass().getDeclaredConstructor(Declaration.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void as(Definition definition) {
        typeNames.add(definition.name());
    }

    public abstract List<Declaration> components();

    public abstract void add(Declaration component);

    public abstract Map<String, Object> variables();

    public boolean is(String type) {
        return typeNames.contains(type);
    }

    public boolean is(Class<? extends Morph> morph) {
        return isAnyOf(definitions(morph));
    }

    boolean isAnyOf(List<String> definitions) {
        return definitions.stream().filter(this::is).findFirst().isPresent();
    }

    public abstract <T extends Morph> List<T> findComponent(Class<T> aClass);

    public abstract void variables(Map<String, Object> variables);
}
