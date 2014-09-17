package siani.tara.lang;

public abstract class ModelObject {

	protected String doc;
	protected String parentName;
	protected transient NodeObject parentObject;
	protected String type = "Concept";
	protected String name = "";

	public ModelObject() {
	}

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

	public void setParentObject(NodeObject parentObject) {
		this.parentObject = parentObject;
		parentName = parentObject.getName();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMetaQN() {
		String qn = "." + type;
		NodeObject parent = this.getParent();
		while (parent != null) {
			qn += "." + parent.getType();
			parent = parent.getParent();
		}
		return qn.substring(1);
	}

	public boolean is(Class type) {
		return type.isInstance(this);
	}

}
