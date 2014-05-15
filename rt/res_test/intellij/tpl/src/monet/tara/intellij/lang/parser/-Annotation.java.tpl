package monet.::projectName::.intellij.lang.parser;

public class ::projectProperName::Annotation {

	public static final String EXTENSIBLE = "extensible";
	public static final String EXTENSION = "extension";
	public static final String ROOT = "root";
	public static final String SINGLETON = "singleton";
	::empty|public static final String CODE = "code";::
	public static final String OPTIONAL = "optional";
	public static final String MULTIPLE = "multiple";
	public static final String GENERIC = "generic";
	public static final String INTENTION = "intention";
	public static final String[] CASE_ANNOTATIONS = new String[]{EXTENSIBLE, SINGLETON, CODE, INTENTION};
	public static final String[] ROOT_ANNOTATIONS = new String[]{GENERIC, ROOT, EXTENSIBLE, SINGLETON, CODE, INTENTION};
	public static final String[] CHILD_ANNOTATIONS = new String[]{GENERIC, EXTENSIBLE, EXTENSION, SINGLETON, CODE, OPTIONAL, MULTIPLE, INTENTION};

	private ::projectProperName::Annotation() {
	}
}
