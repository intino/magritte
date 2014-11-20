package siani.tara.lang;

public class Attribute extends Variable {
	public final String primitiveType;
	public String measure;

	public Attribute(String type, String name, boolean isList) {
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
	}

	public Attribute(String type, String name) {
		this.primitiveType = type;
		this.name = name;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@Override
	public String getType() {
		return primitiveType;
	}

	public String toString() {
		return primitiveType + (isList ? "..." : "") + " " + name;
	}

	@Override
	public Attribute clone() {
		Attribute attribute = new Attribute(primitiveType, name, isList);
		attribute.setDefaultValues(defaultValues);
		for (Annotations.Annotation annotation : annotations) attribute.add(annotation);
		attribute.measure = measure;
		attribute.setDefaultValues(defaultValues);
		if (values != null)
			for (Object value : values) attribute.addValue(value);
		return attribute;
	}
}
