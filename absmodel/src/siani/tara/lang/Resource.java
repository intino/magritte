package siani.tara.lang;

public class Resource extends Variable {

	public static final String ANY = "any";
	private final String fileType;

	public Resource(String fileType, String name) {
		this(fileType, name, false);
	}

	public Resource(String fileType, String name, boolean inherited) {
		super(inherited);
		this.fileType = fileType;
		this.name = name;
	}

	@Override
	public String getType() {
		return fileType;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override

	public String toString() {
		return "Resource:" + fileType + " " + name;
	}

	@Override
	public Variable clone() {
		Resource resource = new Resource(fileType, name);
		for (Annotation annotation : annotations) resource.add(annotation);
		resource.setDefaultValues(getDefaultValues());
		if (values != null)
			for (Object value : values) resource.addValue(value);
		return resource;
	}
}
