package magritte.wraps.variables;

import magritte.*;
import magritte.primitives.Date;
import magritte.primitives.Enumerate;
import magritte.primitives.Resource;

import static magritte.handlers.Casting.cast;
import static magritte.handlers.NativeCodeHandler.init;

public class Single {

	private final Object value;
	private final Node node;
	private final Node scope;

	public Single(Object value, Node node) {
		this(value, node, null);
	}

	public Single(Object value, Node node, Node scope) {
		this.value = value;
		this.node = node;
		this.scope = scope;
	}

	public String asString() {
		return value != null ? evaluate() : "";
	}

	public int asInteger() {
		return value != null ? evaluate() : 0;
	}

	public double asDouble() {
		return value != null ? evaluate() : 0.0;
	}

	public boolean asBoolean() {
		return value != null ? evaluate() : false;
	}

	public Date asDate() {
		return value != null ? evaluate() : null;
	}

	public Resource asResource() {
		return value != null ? (Resource) value : null;
	}

	public Reference asReference() {
		return value != null ? (Reference) value : null;
	}

	public <T extends Enum> T asEnumerate(Enum[] enums) {
		return value != null ? cardinal(evaluate(), enums) : null;
	}

	public <T extends NodeWrap> T as(Class<T> wrapClass) {
		return value != null ? cast(node(asReference())).as(wrapClass) : null;
	}

	public <T extends NativeCode> T asNative() {
		return (scope != null) ? init((T) value).on(scope) : (T) value;
	}

	private <T extends Enum> T cardinal(int value, Enum[] enums) {
		return (T) Enumerate.enumerate(value, enums);
	}

	private Node node(Reference reference) {
		return node(reference.value());
	}

	private Node node(String value) {
		return model().get(value);
	}

	private Graph model() {
		return node.graph();
	}

	private <T> T evaluate() {
		return (value instanceof Expression) ? ((Expression<T>) value).value() : (T) value;
	}


}
