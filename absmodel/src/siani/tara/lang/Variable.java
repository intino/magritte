package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public static final String EMPTY = "EMPTY_VALUE";
	public String name;
	public String doc;
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

	public abstract String getValue();

	public abstract Variable clone();

	public abstract String toString();
}
