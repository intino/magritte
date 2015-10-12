package tara.magritte;

import tara.util.WordGenerator;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Definition extends Predicate {

    private boolean isAbstract;
    private boolean isTerminal;
    private boolean isMain;
    private Class<? extends Layer> layerClass;
    private Definition parent;
    private Set<Definition> children = new LinkedHashSet<>();
    private Set<Definition> types = new LinkedHashSet<>();
    private Set<Definition> instances = new LinkedHashSet<>();
    private Set<Definition> allowsMultiple = new LinkedHashSet<>();
    private Set<Definition> allowsSingle = new LinkedHashSet<>();
    private Set<Definition> requiresMultiple = new LinkedHashSet<>();
    private Set<Definition> requiresSingle = new LinkedHashSet<>();
    private List<Declaration> components = new ArrayList<>();
    private List<Declaration> prototypes = new ArrayList<>();
    private Map<String, Object> variables = new LinkedHashMap<>();

    public Definition(String name) {
        super(name);
    }

    private static void addDefinition(Declaration declaration, Definition definition) {
        declaration.addLayer(definition);
        definition.variables.forEach(declaration::load);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    void isAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    void isTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    @SuppressWarnings("unused")
    public boolean isMain() {
        return isMain;
    }

    void isMain(boolean isMain) {
        this.isMain = isMain;
    }

    public Class<? extends Layer> layerClass() {
        return layerClass;
    }

    void layerClass(Class<? extends Layer> layerClass) {
        this.layerClass = layerClass;
    }

    public List<Definition> types() {
        return unmodifiableList(new ArrayList<>(types));
    }

    public Definition parent() {
        return parent;
    }

    public void parent(Definition parent) {
        if(parent == null) return;
        this.parent = parent;
        putType(parent);
        parent.children.add(this);
    }

    @SuppressWarnings("unused")
    public List<Definition> children() {
        return unmodifiableList(new ArrayList<>(children));
    }

    public void types(List<Definition> types){
        types.forEach(this::putType);
    }

    @Override
    public void putType(Definition definition) {
        if (is(definition.name())) return;
        super.putType(definition);
        types.add(definition);
        definition.instances.add(this);
    }

    @SuppressWarnings("unused")
    public List<Definition> instances() {
        Set<Definition> instances = new LinkedHashSet<>();
        instances.addAll(this.instances);
        this.instances.forEach(s -> instances.addAll(s.instances()));
        return new ArrayList<>(instances);
    }

    public List<Definition> allowedDefinitionsInComponents() {
        Set definitions = new LinkedHashSet<>();
        definitions.addAll(allowsSingle());
        definitions.addAll(allowsMultiple());
        definitions.addAll(requiresSingle());
        definitions.addAll(requiresMultiple());
        return unmodifiableList(new ArrayList<>(definitions));
    }

    public List<Definition> allowsMultiple() {
        return unmodifiableList(new ArrayList<>(allowsMultiple));
    }

    void allowsMultiple(List<Definition> definitions) {
        allowsMultiple.addAll(definitions);
    }

    public List<Definition> allowsSingle() {
        return unmodifiableList(new ArrayList<>(allowsSingle));
    }

    public void allowsSingle(List<Definition> definitions) {
        allowsSingle.addAll(definitions);
    }

    @SuppressWarnings("unused")
    public List<Definition> requires(Class<? extends Layer> layerClass) {
        List<String> morphDefinitions = LayerFactory.names(layerClass);
        List<Definition> definitions = new ArrayList<>();
        definitions.addAll(requiresMultiple.stream().filter(r -> !r.isTerminal() && r.isAnyOf(morphDefinitions)).collect(toList()));
        definitions.addAll(requiresSingle.stream().filter(r -> !r.isTerminal() && r.isAnyOf(morphDefinitions)).collect(toList()));
        return definitions;
    }

    void requiresMultiple(List<Definition> definitions) {
        requiresMultiple.addAll(definitions);
    }

    public List<Definition> requiresMultiple() {
        return unmodifiableList(new ArrayList<>(requiresMultiple));
    }

    public void requiresSingle(List<Definition> definitions) {
        requiresSingle.addAll(definitions);
    }

    public List<Definition> requiresSingle() {
        return unmodifiableList(new ArrayList<>(requiresSingle));
    }

    @Override
    public Map<String, Object> variables() {
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public void variables(Map<String, Object> variables) {
        this.variables = variables;
    }

    @Override
    public List<Declaration> components() {
        return unmodifiableList(components);
    }

    @Override
    public <T extends Layer> List<T> findComponents(Class<T> layerClass) {//TODO
        return null;
    }

    public void components(List<Declaration> components) {
        this.components = components;
    }

    public List<Declaration> prototypes() {
        return unmodifiableList(prototypes);
    }

    public void prototypes(List<Declaration> prototypes) {
        this.prototypes.addAll(prototypes);
    }

    public Declaration create(Declaration owner) {
        return createDeclaration(WordGenerator.generate(), owner);
    }

    public Declaration create(String name, Declaration owner) {
        if (!isTerminal) {
            Logger.severe("Declaration cannot be created. Definition " + this.name + " is not terminal");
            return null;
        }
        return createDeclaration(name, owner);
    }

    private Declaration createDeclaration(String name, Declaration owner) {
        Declaration declaration = new Declaration(name);
        declaration.owner(owner);
        types().forEach(t -> addDefinition(declaration, t));
        addDefinition(declaration, this);
        owner.add(declaration);
        return declaration;
    }

    @Override
    public String toString() {
        return name + "{" +
                "names=" + types.stream().map(m -> m.name).collect(toList()) +
                ", instances=" + instances.stream().map(m -> m.name).collect(toList()) +
                ", allowsMultiple=" + allowsMultiple.stream().map(m -> m.name).collect(toList()) +
                ", allowsSingle=" + allowsSingle.stream().map(m -> m.name).collect(toList()) +
                ", requiresMultiple=" + requiresMultiple.stream().map(m -> m.name).collect(toList()) +
                ", requiresSingle=" + requiresSingle.stream().map(m -> m.name).collect(toList()) +
                '}';
    }
}
