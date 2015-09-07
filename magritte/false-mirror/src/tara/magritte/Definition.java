package tara.magritte;

import java.util.*;
import java.util.logging.Logger;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Definition extends Predicate {

    private static final Logger LOG = Logger.getLogger(Definition.class.getName());

    private boolean isAbstract;
    private boolean isTerminal;
    private boolean isMain;
    private Class<? extends Morph> morphClass;
    private Definition parent;
    private Set<Definition> children = new LinkedHashSet<>();
    private Set<Definition> types = new LinkedHashSet<>();
    private Set<Definition> instances = new LinkedHashSet<>();
    private Set<Definition> allowsMultiple = new LinkedHashSet<>();
    private Set<Definition> allowsSingle = new LinkedHashSet<>();
    private Set<Definition> requiresMultiple = new LinkedHashSet<>();
    private Set<Definition> requiresSingle = new LinkedHashSet<>();
    private Map<String, Object> variables = new LinkedHashMap<>();
    private List<Declaration> components = new ArrayList<>();
    private List<Declaration> prototypes = new ArrayList<>();

    public Definition(String name) {
        super(name);
    }

    private static void addDefinition(Declaration declaration, Definition definition) {
        declaration.as(definition);
        definition.variables.forEach(declaration::set);
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

    public Class<? extends Morph> morphClass() {
        return morphClass;
    }

    void morphClass(Class<? extends Morph> morphClass) {
        this.morphClass = morphClass;
    }

    public List<Definition> types() {
        return unmodifiableList(new ArrayList<>(types));
    }

    public Definition parent() {
        return parent;
    }

    public void parent(Definition parent) {
        this.parent = parent;
        parent.children.add(this);
    }

    @SuppressWarnings("unused")
    public List<Definition> children() {
        return unmodifiableList(new ArrayList<>(children));
    }

    public void types(List<Definition> types){
        types.forEach(this::as);
    }

    @Override
    public void as(Definition definition) {
        if (is(definition.name())) return;
        super.as(definition);
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
    public List<Definition> requires(Class<? extends Morph> morphClass) {
        List<String> morphDefinitions = MorphFactory.names(morphClass);
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
    public <T extends Morph> List<T> findComponent(Class<T> aClass) {//TODO
        return null;
    }

    @Override
    public void add(Declaration component) {
        if (allowedDefinitionsInComponents().stream().filter(t -> component.is(t.name())).findFirst().isPresent())
            components.add(component);
    }

    public List<Declaration> prototypes() {
        return unmodifiableList(prototypes);
    }

    public void prototypes(List<Declaration> prototypes) {
        this.prototypes.addAll(prototypes);
    }

    public Declaration create(String name) {
        if (!isTerminal) {
            LOG.severe("Declaration cannot be created. Definition " + name + " is not terminal");
            return null;
        }
        return createDeclaration(name);
    }

    private Declaration createDeclaration(String name) {
        Declaration declaration = new Declaration(name);
        types().forEach(t -> addDefinition(declaration, t));
        addDefinition(declaration, this);
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
