package siani.tara.intellij.lang.parser;

public class TaraAnnotation {

	public static final String ROOT = "root";
	public static final String HASNAME = "has-name";
	public static final String REQUIRED = "required";
	public static final String MULTIPLE = "multiple";
	public static final String TERMINAL = "terminal";
	public static final String PROPERTY = "property";
	public static final String INTENTION = "intention";
	public static final String[] CASE_ANNOTATIONS = new String[]{INTENTION};
	public static final String PRIVATE = "private";
	public static final String[] ROOT_ANNOTATIONS = new String[]{PRIVATE, ROOT, TERMINAL, INTENTION};
	public static final String[] CHILD_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, REQUIRED, MULTIPLE, INTENTION};

	private TaraAnnotation() {
	}

	public static String[] getAnnotations() {
		return new String[]{MULTIPLE, INTENTION, REQUIRED, ROOT, TERMINAL, PRIVATE, HASNAME};
	}
}
