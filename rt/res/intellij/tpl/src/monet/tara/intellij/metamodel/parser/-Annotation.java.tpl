package monet.::projectName::.intellij.metamodel.parser;

public class ::projectProperName::Annotation {

	public static final String EXTENSIBLE = "extensible";
	public static final String EXTENSION = "extension";
	public static final String ROOT = "root";
	public static final String SINGLETON = "singleton";
	public static final String HAS_CODE = "has-code";
	public static final String OPTIONAL = "optional";
	public static final String MULTIPLE = "multiple";
	public static final String GENERIC = "generic";
	public static final String INTENTION = "intention";
	public static final String[] CASE_ANNOTATIONS = new String[]{EXTENSIBLE, SINGLETON, HAS_CODE, INTENTION};
	public static final String[] ROOT_ANNOTATIONS = new String[]{GENERIC, ROOT, EXTENSIBLE, SINGLETON, HAS_CODE, INTENTION};
	public static final String[] CHILD_ANNOTATIONS = new String[]{GENERIC, EXTENSIBLE, EXTENSION, SINGLETON, HAS_CODE, OPTIONAL, MULTIPLE, INTENTION};

	private ::projectProperName::Annotation() {
	}
}
