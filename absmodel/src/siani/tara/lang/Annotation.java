package siani.tara.lang;


public enum Annotation {
	NAMED("named"), COMPONENT("component"), TERMINAL("terminal"), SINGLE("single"), REQUIRED("required"),
	ABSTRACT("abstract"), PROPERTY("property"), FACET("facet"), INTENTION("intention"), LOCAL("local"),
	ADDRESSED("addressed"), AGGREGATED("aggregated"), COMPOSED("composed"), MULTIPLE("composed"), ROOT("root"),
	CASE("case"), READONLY("readonly"),

	META_NAMED("+named"), META_COMPONENT("+component"), META_SINGLE("+single"), META_REQUIRED("+required"),
	META_PROPERTY("+property"), META_FACET("+facet"), META_INTENTION("+intention"), META_LOCAL("+local"),
	META_ADDRESSED("+addressed"), META_AGGREGATED("+aggregated"), META_COMPOSED("+composed"), META_MULTIPLE("+composed"),
	META_ROOT("+root"), META_READONLY("readonly"), META_TERMINAL("+terminal");

	private String name;

	private Annotation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isMeta() {
		return name.startsWith("+");
	}

	public static Annotation getNormalAnnotationOfMeta(Annotation annotation) {
		return annotation.isMeta() ? Annotation.valueOf(annotation.getName().substring(1).toUpperCase()) : annotation;
	}
}
