package siani.tara.lang;

public class Attribute extends Variable {
	public final String primitiveType;
	public String measureValue;
	public String measureType;
	public Integer count = 1;

	public Attribute(String type, String name, boolean isList) {
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
	}

	public Attribute(String type, String name) {
		this.primitiveType = type;
		this.name = name;
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
