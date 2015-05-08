package siani.tara.semantic.model;


public enum Tag {
	ABSTRACT("abstract"), TERMINAL("terminal"),
	SINGLE("single"), MULTIPLE("multiple"), REQUIRED("required"), OPTIONAL("optional"),
	FEATURE("feature"), PROPERTY("property"),
	FACET("facet"),
	ADDRESSED("addressed"), ENCLOSED("enclosed"),
	COMPONENT("component"), AGGREGATED("aggregated"), ASSOCIATED("associated"),
	READONLY("readonly"),
	NAMED("named"),
	TERMINAL_INSTANCE("terminal_instance"),
	FACET_INSTANCE("facet_instance"),
	FEATURE_INSTANCE("feature_instance"),
	PROPERTY_INSTANCE("property_instance");

	private String name;

	Tag(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}
}
