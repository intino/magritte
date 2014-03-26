package monet.::projectName::.intellij.metamodel.parser;

public class Annotation {

	public static final String EXTENSIBLE = "extensible";
	public static final String ROOT = "root";
	public static final String SINGLETON = "singleton";
	public static final String HAS_CODE = "has-code";
	public static final String[] MORPH_ANNOTATIONS = new String[]{EXTENSIBLE, SINGLETON, HAS_CODE};
	public static final String[] ROOT_ANNOTATIONS = new String[]{ROOT, EXTENSIBLE, SINGLETON, HAS_CODE};
	public static final String OPTIONAL = "optional";
	public static final String MULTIPLE = "multiple";
	public static final String[] CHILD_ANNOTATIONS = new String[]{EXTENSIBLE, SINGLETON, HAS_CODE, OPTIONAL, MULTIPLE};

	private Annotation() {
	}
}
