package siani.tara.lang;

public class Resource extends Variable {

	public final String node;

	public Resource(String node, String name) {
		this.node = node;
		this.name = name;
	}

	@Override
	public String getType() {
		return node;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override

	public String toString() {
		return "Resource:" + node + " " + name;
	}

	@Override
	public Variable clone() {
		Resource resource = new Resource(node, name);
		for (Annotation annotation : annotations) resource.add(annotation);
		resource.setDefaultValues(getDefaultValues());
		if (values != null)
			for (Object value : values) resource.addValue(value);
		return resource;
	}
}
