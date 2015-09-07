package tara.magritte;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Declaration extends Predicate {

    protected final List<Morph> morphs = new ArrayList<>();
    Declaration owner;

    public Declaration() {
        this("");
    }

    public Declaration(String name) {
        super(name);
    }

    @Override
    public List<Definition> types() {
        List<String> types = new ArrayList<>(this.typeNames);
        Collections.reverse(types);
        return types.stream().map(PersistenceManager::type).collect(toList());
    }

    @Override
    public List<Declaration> components() {
        Set<Declaration> declarations = new LinkedHashSet<>();
        for (int i = morphs.size() - 1; i >= 0; i--) declarations.addAll(morphs.get(i)._components());
        return new ArrayList<>(declarations);
    }

    @Override
    public void add(Declaration component) {
        for (Morph morph : morphs) morph._addComponent(component);
    }

    @Override
    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        morphs.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    @Override
    public <T extends Morph> List<T> findComponent(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass.getSimpleName()))
            tList.add(as(aClass));
        components().forEach(c -> tList.addAll(c.findComponent(aClass)));
        return tList;
    }

    public Declaration(String name, Declaration declaration, Declaration owner) {
        super(name);
        this.owner = owner;
        this.typeNames.addAll(declaration.typeNames);
        this.morphs.addAll(declaration.morphs.stream().map((morph) -> cloneMorph(morph, declaration)).collect(toList()));
        declaration.components().forEach(c -> morphs.forEach(m -> m._addComponent(new Declaration(name + "." + c.shortName(), c, this))));
        PersistenceManager.registerClone(declaration.name, declaration, this);
    }

    @Override
    public void as(Definition definition) {
        if (is(definition.name())) return;
        super.as(definition);
        createMorph(definition);
        removeParentMorph(definition);
    }

    @SuppressWarnings("unchecked")
    public <T extends Morph> T as(Class<T> morphClass) {
        for (Morph morph : morphs)
            if (morphClass.isAssignableFrom(morph.getClass()))
                return (T) morph;
        return null;
    }

    @SuppressWarnings("unused")
    public <T extends Morph> List<T> components(Class<T> morphClass) {
        List<String> types = MorphFactory.names(morphClass);
        return components().stream()
                .filter(c -> c.isAnyOf(types))
                .map(c -> c.as(morphClass))
                .collect(toList());
    }

    @Override
    public void variables(Map<String, Object> variables) {
        variables.forEach((k, v) -> morphs.forEach(m -> m._load(k, v)));
    }

    public void set(String name, Object value) {
        for (Morph morph : morphs) morph._set(name, value);
    }

    private void createMorph(Definition definition) {
        Morph morph = MorphFactory.create(definition.name, this);
        if (morph != null) this.morphs.add(0, morph);
    }

    private void removeParentMorph(Definition definition) {
        if (definition.parent() == null || definition.parent().isAbstract()) return;
        morphs.removeIf(m -> m.getClass() == definition.parent().morphClass());
    }

    public void owner(Declaration owner) {
        this.owner = owner;
    }

    public Declaration owner() {
        return owner;
    }

    public <T extends Morph> T owner(Class<T> $Class) {
        if (owner == null) return null;
        if (owner.is($Class)) return owner.as($Class);
        return owner.owner($Class);
    }


}
