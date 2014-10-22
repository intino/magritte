package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Variable implements Cloneable {
	public static final String EMPTY = "EMPTY_VALUE";
	public String name;
	public String doc;
	public Object[] defaultValues;
	public List<Object> values;
	public boolean isList = false;
	public boolean isTerminal = false;
	public boolean isProperty = false;
	public boolean isUniversal = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public boolean isProperty() {
		return isProperty;
	}

	public void setProperty(boolean isProperty) {
		this.isProperty = isProperty;
	}

	public boolean isUniversal() {
		return isUniversal;
	}

	public void setUniversal(boolean isUniversal) {
		this.isUniversal = isUniversal;
	}

	public abstract String getType();

	public Object[] getValues() {
		return values != null ? values.toArray(new Object[values.size()]) : new Object[0];
	}

	public void setValues(Object... values) {
		if (this.values == null) this.values = new ArrayList<>();
		Collections.addAll(this.values, values);
	}

	public void addValue(Object value) {
		if(values== null) this.values = new ArrayList<>();
		this.values.add(value);
	}

	public Object[] getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(Object... defaultValue) {
		this.defaultValues = defaultValue;
	}

	public abstract Variable clone();

	public abstract String toString();
}
