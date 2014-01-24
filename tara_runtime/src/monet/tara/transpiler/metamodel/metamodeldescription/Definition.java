package monet.tara.transpiler.metamodel.metamodeldescription;

import java.util.ArrayList;

public class Definition {

	public enum ExtensionType {
		EXTENSIBLE, HASCODE
	}

	private boolean abstractModifier;
	private boolean finalModifier;
	private ArrayList<ExtensionType> annotations = new ArrayList<>();
	private ArrayList<Param> params = new ArrayList<>();
	private ArrayList<Definition> children = new ArrayList<>();
	private String name = "";

	public Definition(String name) {
		this.name = name;
		abstractModifier = false;
		finalModifier = false;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ExtensionType> getAnnotations() {
		return annotations;
	}

	public ArrayList<Param> getParams() {
		return params;
	}

	public ArrayList<Definition> getChildren() {
		return children;
	}

	public boolean isFinalModifier() {
		return finalModifier;
	}

	public boolean isAbstractModifier() {
		return abstractModifier;
	}

	public void setFinalModifier() {
		this.finalModifier = true;
	}

	public void setAbstractModifier() {
		this.abstractModifier = true;
	}

	public void add(ExtensionType extension) {
		annotations.add(extension);
	}

	public void add(Param param) {
		params.add(param);
	}

	public void add(Definition child) {
		children.add(child);
	}

}
