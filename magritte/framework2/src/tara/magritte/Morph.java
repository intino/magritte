package tara.magritte;

import java.util.List;

public abstract class Morph {

    protected final Node node;
    protected final String type = getClassName(this.getClass());

    public Morph(Node node) {
        this.node = node;
    }

    public Morph(Morph morph, Node node) {
        this.node = node;
    }

    static <T extends Morph> String getClassName(Class<T> aClass) {
        return aClass.getName().replace(aClass.getPackage().getName() + ".", "");
    }

    public boolean is(String name) {
        return node.is(name);
    }

    public boolean is(Class<? extends Morph> aClass) {
        return is(aClass.getName());
    }

    public <T extends Morph> T as(Class<T> tClass) {
        return node.morph(tClass);
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (this.node == ((Morph) o).node);
    }

    protected abstract void add(Node component);

    protected abstract void set(String name, Object object);

    protected Object link(NativeCode value) {
        if(value == null) return null;
        Node context = node.search(value.$Class());
        if(context instanceof Type) return value;
        value.set(context == null ? this : context.morph(value.$Class()));
        return value;
    }

    public abstract List<Node> components();

    public Node node() {
        return node;
    }
}
