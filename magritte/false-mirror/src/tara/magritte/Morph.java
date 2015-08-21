package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Morph {

	protected final Node node;
	protected final String type = getClassName(this.getClass());

	public Morph(Node node) {
		this.node = node;
	}

    public Node _node() {
        return node;
    }

	public boolean is(String name) {
		return node.is(name);
	}

	public boolean is(Class<? extends Morph> aClass) {
		return is(getClassName(aClass));
	}

    public Node _owner() {
        return node.owner();
    }

    public <T extends Morph> T _owner(Class<T> $Class) {
        return node.owner($Class);
    }

	public <T extends Morph> T as(Class<T> tClass) {
		return node.morph(tClass);
	}

    protected void _add(Node component) {
    }

    protected void _add(List<Node> components) {
        components.forEach(this::_add);
    }

    protected void _set(String name, Object object) {
    }

    public List<Node> _components() {
        return Collections.emptyList();
    }

	public Map<String, Object> _variables() {
		return Collections.emptyMap();
	}

	@Override
	public boolean equals(Object o) {
		return o != null && this.node == ((Morph) o).node;
	}

	@Override
	public int hashCode() {
		int result = node != null ? node.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	static <T extends Morph> String getClassName(Class<T> aClass) {
		return aClass.getName().replace(aClass.getPackage().getName() + ".", "");
	}

    protected Object _link(NativeCode value) {
        if (value == null) return null;
        Node context = node.is(value.$Class()) ? node : searchOwner(value);
        if (context instanceof Type) return value;
        Morph morph = context == null ? this : context.morph(value.$Class());
        value.set(morph == null ? this : morph);
        return value;
    }

    private Node searchOwner(NativeCode value) {
        Morph owner = node.owner(value.$Class());
        return owner != null ? owner._node() : null;
    }

	protected Node _loadNode(Object id) {
		return PersistenceManager.loadNode(id);
	}

	protected List<Node> _loadNode(Object[] ids) {
		return PersistenceManager.loadNode(ids);
	}

	protected Object _newInstanceOf(Object aClass) {
		try {
			if (aClass == null) return null;
			return aClass instanceof String ? Class.forName(aClass.toString()).newInstance() : aClass.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

    protected void save(){
        PersistenceManager.save(node);
    }

    @Override
    public String toString() {
        return node.name();
    }
}
