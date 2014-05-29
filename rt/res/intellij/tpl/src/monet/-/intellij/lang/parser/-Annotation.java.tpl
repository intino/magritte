package monet.::projectName::.intellij.lang.parser;

public class ::projectProperName::Annotation {

	public static final String ROOT = "root";
	public static final String SINGLETON = "singleton";
	public static final String HASNAME = "has-name";
	public static final String REQUIRED = "required";
	public static final String MULTIPLE = "multiple";
	public static final String GENERIC = "generic";
	public static final String INTENTION = "intention";
	public static final String[] CASE_ANNOTATIONS = new String[]{SINGLETON, INTENTION};
	public static final String[] ROOT_ANNOTATIONS = new String[]{GENERIC, ROOT, SINGLETON, INTENTION};
	public static final String[] CHILD_ANNOTATIONS = new String[]{GENERIC, SINGLETON, REQUIRED, MULTIPLE, INTENTION};

	private ::projectProperName::Annotation() {
	}

	public static String[] getAnnotations() {
		return new String[]{MULTIPLE, INTENTION, REQUIRED, ROOT, SINGLETON, GENERIC, HASNAME};
	}
}
