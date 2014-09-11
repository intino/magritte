package siani.tara.lang;

public class TaraAnnotations {

	public static final String COMPONENT = "component";
	public static final String NAMED = "named";
	public static final String REQUIRED = "required";
	public static final String SINGLE = "single";
	public static final String TERMINAL = "terminal";
	public static final String PROPERTY = "property";
	public static final String PRIVATE = "private";
	public static final String INTENTION = "intention";
	public static final String FACET = "facet";
	public static final String[] SUB_ANNOTATIONS = new String[]{PROPERTY, NAMED, TERMINAL};
	public static final String[] PRIME_ANNOTATIONS = new String[]{PRIVATE, COMPONENT, SINGLE, NAMED, TERMINAL, PROPERTY, REQUIRED, INTENTION, FACET};
	public static final String[] COMPONENT_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, REQUIRED, SINGLE, PROPERTY, NAMED};
	public static final String[] VARIABLE_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, PROPERTY, SINGLE, PROPERTY};

	private TaraAnnotations() {
	}

	public static String[] getAnnotations() {
		return new String[]{SINGLE, REQUIRED, COMPONENT, TERMINAL, PROPERTY, NAMED, PRIVATE, INTENTION, FACET};
	}
}
