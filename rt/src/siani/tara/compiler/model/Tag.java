package siani.tara.compiler.model;


public enum Tag {
	ABSTRACT("abstract"), TERMINAL("terminal"), NAMED("named"), ROOT("root"),
	SINGLE("single"), MULTIPLE("multiple"), REQUIRED("required"), OPTIONAL("optional"),
	FEATURE("feature"), PROPERTY("property"),
	FACET("facet"),
	ADDRESSED("addressed"), ENCLOSED("enclosed"),
	COMPONENT("component"), AGGREGATED("aggregated"), ASSOCIATED("associated"),
	READONLY("readonly"),
	TERMINAL_INSTANCE("case"),
	FACET_INSTANCE("facetInstance"),
	FEATURE_INSTANCE("featureInstance"),
	PROPERTY_INSTANCE("propertyInstance");

	private String name;

	Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
