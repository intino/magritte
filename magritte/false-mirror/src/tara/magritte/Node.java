package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public abstract class Node {
    protected final String name;
    protected final Set<String> types = new LinkedHashSet<>();
    protected final List<Facet> facets = new ArrayList<>();
    Case owner;

    public Node(String name) {
        this.name = name;
    }

    private static List<String> morphType(Class<? extends Facet> aClass) {
        return FacetFactory.type(aClass);
    }

    public String shortName() {
        String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
        shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
        return shortName;
    }

    public String name() {
        return name;
    }

    public List<Type> types() {
        List<String> types = new ArrayList<>(this.types);
        Collections.reverse(types);
        return types.stream().map(PersistenceManager::type).collect(Collectors.toList());
    }

    protected Facet cloneMorph(Facet facet, Case aCase) {
        try {
            return facet.getClass().getDeclaredConstructor(Case.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Case> components() {
        Set<Case> aCases = new LinkedHashSet<>();
        for (int i = facets.size() - 1; i >= 0; i--) aCases.addAll(facets.get(i)._components());
        return new ArrayList<>(aCases);
    }

    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        facets.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    public void add(Type type) {
        if (is(type)) return;
        removeSuperClassesMorph(type);
        Facet facet = FacetFactory.newInstance(type.name, this);
        if (facet != null) this.facets.add(0, facet);
        types.add(type.name());
    }

    private void removeSuperClassesMorph(Type type) {
        if(type.isAbstract()) return;
        facets.removeAll(facets.stream().filter(m -> m.getClass().isAssignableFrom(type.morphClass())).collect(toList()));
    }

    public void remove(Facet facet) {
        facets.remove(facet);
    }

    public void init(String parameter, Object value) {
        for (Facet facet : facets) facet._init(parameter, value);
    }

    public void set(String parameter, Object value) {
        for (Facet facet : facets) facet._set(parameter, value);
    }

    public void owner(Case owner) {
        this.owner = owner;
    }

    public Case owner() {
        return owner;
    }

    public <T extends Facet> T owner(Class<T> $Class) {
        if (owner == null) return null;
        if (owner.is($Class)) return owner.morph($Class);
        return owner.owner($Class);
    }

    public void add(Case component) {
        for (Facet facet : facets) facet._add(component);
    }

    public boolean is(Type type) {
        return types().contains(type);
    }

    public boolean is(String type) {
        return types.contains(type);
    }

    public <T extends Facet> List<T> components(Class<T> aClass) {
        List<String> types = morphType(aClass);
        return components().stream()
                .filter(c -> c.isAnyOf(types))
                .map(c -> c.morph(aClass))
                .collect(toList());
    }

    public boolean is(Class<? extends Facet> morph) {
        return isAnyOf(morphType(morph));
    }

    boolean isAnyOf(List<String> types) {
        return types.stream().filter(this::is).findFirst().isPresent();
    }

    public List<Facet> _morphs() {
        return Collections.unmodifiableList(facets);
    }

    @SuppressWarnings("unchecked")
    public <T extends Facet> T morph(Class<T> morph) {
        for (Facet aFacet : facets)
            if (morph.isAssignableFrom(aFacet.getClass()))
                return (T) aFacet;
        return null;
    }

    public <T extends Facet> List<T> find(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass.getSimpleName()))
            tList.add(morph(aClass));
        components().forEach(c -> tList.addAll(c.find(aClass)));
        return tList;
    }
}
