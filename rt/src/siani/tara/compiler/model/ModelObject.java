package siani.tara.compiler.model;

public abstract class ModelObject {

	protected String doc;
	protected String parentName;
	protected transient NodeObject parentObject;
	protected String type = "Concept";

	protected String fulltype = "Concept";

	protected String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public NodeObject getParent() {
		return parentObject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFulltype() {
		return fulltype;
	}

	public void setFulltype(String fulltype) {
		this.fulltype = fulltype;
	}

	public void setParentObject(NodeObject parentObject) {
		this.parentObject = parentObject;
		if (parentObject != null) parentName = parentObject.getDeclaredNodeQN();
	}

	public boolean is(Class type) {
		return type.isInstance(this);
	}

}
