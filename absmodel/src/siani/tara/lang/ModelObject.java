package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelObject {

	protected String doc;
	protected String parentName;
	protected transient NodeObject parentObject;
	protected String type = "Concept";
	protected List<String> inheritedTypes;
	protected String name = "";

	public ModelObject() {
		inheritedTypes = new ArrayList<>();
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
		if (parentObject != null)
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

	public boolean addInheritedTypes(String inheritedType) {
		return inheritedTypes.add(inheritedType);
	}

	public boolean is(Class type) {
		return type.isInstance(this);
	}

}
