package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public String name;
	public String doc;
	public boolean isTerminal;
	public boolean isMultiple;
	private boolean multiple;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();

	public boolean isMultiple() {
		return isMultiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public boolean isTerminal(){
		return isTerminal;
	}

	public abstract String toString();

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public abstract Variable clone();
}
