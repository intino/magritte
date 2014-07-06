package siani.tara.intellij.lang.parser;

public class TaraAnnotation {

	public static final String ROOT = "root";
	public static final String NAMEABLE = "nameable";
	public static final String REQUIRED = "required";
	public static final String SINGLE = "single";
	public static final String TERMINAL = "terminal";
	public static final String PROPERTY = "property";
	public static final String PRIVATE = "private";
	public static final String[] CASE_ANNOTATIONS = new String[]{PROPERTY, NAMEABLE, TERMINAL};
	public static final String[] ROOT_ANNOTATIONS = new String[]{PRIVATE, ROOT, TERMINAL, SINGLE, NAMEABLE, TERMINAL};
	public static final String[] CHILD_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, REQUIRED, SINGLE, PROPERTY, NAMEABLE};
	public static final String[] VARIABLE_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, PROPERTY, SINGLE, PROPERTY};

	private TaraAnnotation() {
	}

	public static String[] getAnnotations() {
		return new String[]{SINGLE, REQUIRED, ROOT, TERMINAL, PRIVATE, NAMEABLE};
	}
}
