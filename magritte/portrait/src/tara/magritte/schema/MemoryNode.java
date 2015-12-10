package tara.magritte.schema;

import tara.magritte.*;

public class MemoryNode implements Node {

    public static final String Separator = "\\|";

    private final Graph graph;
    private final GroupSet<Tag.Link, Node> links;
    private final VariableSet variables;

    public MemoryNode(Graph graph) {
        this.graph = graph;
        this.links = new GroupSet();
        this.variables = new VariableSet();
        this.flags(0);
    }

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public Reference ref() {
        return this::title;
    }

    @Override
    public String name() {
        return hasTitle() ? title() + (hasKey() ? "|" + key() : "") : null;
    }

    @Override
    public String title() {
        return valid(get(TITLE));
    }

    @Override
    public String key() {
        return hasKey() ? get(KEY) : title();
    }

    private boolean hasTitle() {
        return variables.contains(TITLE);
    }

    private boolean hasKey() {
        return variables.contains(KEY);
    }

    @Override
    public Tag[] tags() {
        return Tag.tags(flags());
    }

    @Override
    public boolean is(Tag tag) {
        return (flags() & tag.flag) != 0;
    }

    @Override
    public <T extends Node> T as(Class<T> class_) {
        return (T) this;
    }

    @Override
    public String[] vars() {
        return variables.names();
    }

    @Override
    public <C> C get(String name) {
        return (C) variables.get(name);
    }

    @Override
    public Node type() {
        return links.group(Tag.Link.Class).get(0);
    }

    @Override
    public Set<Node> types() {
        return links.group(Tag.Link.Class);
    }

    @Override
    public Node parent() {
        return links.group(Tag.Link.Parent).get(0);
    }

    @Override
    public Set<Node> children() {
        return links.group(Tag.Link.Child);
    }

    @Override
    public Node root() {
        Node owner = owner();
        return owner == null ? this : owner.root();
    }

    @Override
    public Node owner() {
        return links.group(Tag.Link.Owner).get(0);
    }

    @Override
    public Set<Node> members(Member member) {
        Tag.Link[] linkOf = {Tag.Link.OwnedMember, Tag.Link.RequiredMember, Tag.Link.OptionalMember, Tag.Link.AggregableMember };
        return links.group(linkOf[member.index]);
    }

    @Override
    public Set<Node> fanOut() {
        return links.group(Tag.Link.FanOut);
    }

    @Override
    public Set<Node> fanIn() {
        return this.links.group(Tag.Link.FanIn);
    }

    @Override
    public LinkAction link(final Node node) {
        return link -> links.add(node, link);
    }

    @Override
    public LinkAction unlink(final Node node) {
        return link -> links.remove(node, link);
    }

    @Override
    public void set(String name) {
        if (name == null) return;
        String[] names = name.split(Separator);
        set(TITLE, names[0]);
        set(KEY, names.length > 1 ? names[1] : null);
        graph.index(this);
    }


    @Override
    public void set(Tag... tags) {
        flags(this.flags() | Tag.mask(tags));
        graph().index(this);
    }

    @Override
    public <T> void set(String name, T value) {
        variables.put(name, value);
    }

    private void set(String name, String value) {
        if (value == null) return;
        variables.put(name, value);
    }

    private void flags(int value) {
        variables.put(FLAGS, value);
    }

    private int flags() {
        return get(FLAGS);
    }

    @Override
    public String toString() {
        return label() + "["  + title() + titleType() + "]";
    }

    private String label() {
        return is(Tag.Case) ? "Case" : (is(Tag.Prototype) ? "Prototype" : "Type");
    }

    private String titleType() {
        Node type = type();
        return type != null ? ":" + type.title() + type.as(MemoryNode.class).titleType() : "";
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object object) {
        return this == object;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    private String valid(String name) {
        return name == null ? "?" : name;
    }


}
