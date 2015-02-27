package siani.tara.lang;

public class Attribute extends Variable {
	private final String primitiveType;
	private String measureValue;
	private String measureType;
	private Integer count = 1;

	public Attribute(String type, String name) {
		this(type, name, false, false);
	}

	public Attribute(String type, String name, boolean isList) {
		this(type, name, false, isList);
	}

	public Attribute(String type, String name, boolean inherited, boolean isList) {
		super(inherited);
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
	}

	public String getMeasureValue() {
		return measureValue;
	}

	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean isList() {
		return count > 1 || isList;
	}

	@Override
	public String getType() {
		return primitiveType;
	}

	public String getPrimitiveType() {
		return primitiveType;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String toString() {
		return primitiveType + (isList ? "..." : "") + (count != null ? "[" + count + "]" : "") + " " + name;
	}

	@Override
	public Attribute clone() {
		Attribute attribute = new Attribute(primitiveType, name, isList);
		attribute.setDefaultValues(defaultValues);
		for (Annotation annotation : annotations) attribute.add(annotation);
		attribute.measureValue = measureValue;
		attribute.setDefaultValues(defaultValues);
		attribute.setMeasureType(measureType);
		attribute.setMeasureValue(measureValue);
		if (values != null)
			for (Object value : values) attribute.addValue(value);
		return attribute;
	}
}
