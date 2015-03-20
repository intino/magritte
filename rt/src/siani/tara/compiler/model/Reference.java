package siani.tara.compiler.model;

public class Reference extends Variable {
	private String type;
	private boolean empty = false;

	public Reference(String type, String name) {
		this(type, name, false, false);
	}

	public Reference(String type, String name, boolean isList) {
		this(type, name, false, isList);
	}

	public Reference(String type, String name, boolean inherited, boolean isList) {
		super(inherited);
		this.type = type;
		this.name = name;
		this.isList = isList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEmpty() {
		return empty || getDefaultValues().length > 0;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return type + (isList ? "..." : "") + " " + name;
	}


	@Override
	public boolean hasValue() {
		return super.hasValue() || empty;
	}

	@Override
	public Reference clone() {
		Reference reference = new Reference(type, name, isList);
		for (Annotation annotation : annotations) reference.add(annotation);
		reference.setDefaultValues(getDefaultValues());
		if (values != null)
			for (Object value : values) reference.addValue(value);
		return reference;
	}
}
