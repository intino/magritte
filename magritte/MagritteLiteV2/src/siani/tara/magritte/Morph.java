package siani.tara.magritte;

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
        value.set(node.search(value.$Class()));
        return value;
    }
}
