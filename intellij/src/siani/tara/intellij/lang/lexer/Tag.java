package siani.tara.intellij.lang.lexer;


public enum Tag {
	ABSTRACT("abstract"), TERMINAL("terminal"),
	SINGLE("single"), MULTIPLE("multiple"), REQUIRED("required"), OPTIONAL("optional"),
	FEATURE("feature"), PROPERTY("property"),
	NATIVE("native"), FACET("facet"),
	ADDRESSED("addressed"), ENCLOSED("enclosed"),
	COMPONENT("component"), AGGREGATED("aggregated"), ASSOCIATED("associated"),
	READONLY("readonly");

	private String name;

	Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
