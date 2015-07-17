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

	public Morph(Morph morph, Node node) {
		this.node = node;
	}

	public boolean is(String name) {
		return node.is(name);
	}

	public boolean is(Class<? extends Morph> aClass) {
		return is(getClassName(aClass));
	}

	public <T extends Morph> T as(Class<T> tClass) {
		return node.morph(tClass);
	}

	public List<Node> _components() {
		return Collections.emptyList();
	}

	public Map<String, Object> _variables() {
		return Collections.emptyMap();
	}

	protected void add(Node component) {
	}

	protected void set(String name, Object object) {
	}

	@Override
	public boolean equals(Object o) {
		return o != null && (this.node == ((Morph) o).node);
	}

	protected Object link(NativeCode value) {
		if (value == null) return null;
		Node context = node.search(value.$Class());
		if (context instanceof Type) return value;
		value.set(context == null ? this : context.morph(value.$Class()));
		return value;
	}

	public Node node() {
		return node;
	}

	public void add(List<Node> components) {
		components.forEach(this::add);
	}

	static <T extends Morph> String getClassName(Class<T> aClass) {
		return aClass.getName().replace(aClass.getPackage().getName() + ".", "");
	}
}