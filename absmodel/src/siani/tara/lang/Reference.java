package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class Reference extends Variable {
	public String type;
	public List<String> inheritedTypes = new ArrayList<>();
	public boolean empty = false;

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

	public List<String> getInheritedTypes() {
		return inheritedTypes;
	}

	public boolean addInheritedType(String c) {
		return inheritedTypes.add(c);
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
