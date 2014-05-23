package monet.tara.compiler.core;

public class Phases {

	public static final int INITIALIZATION = 1;
	public static final int PARSING = 2;
	public static final int CONVERSION = 3;
	public static final int DEPENDENCY_RESOLUTION = 4;
	public static final int SEMANTIC_ANALYSIS = 5;
	public static final int CLASS_GENERATION = 6;
	public static final int PLUGIN_GENERATION = 7;
	public static final int OUTPUT = 8;
	public static final int FINALIZATION = 9;
	public static final int ALL = 10;
	private static String[] descriptions = {
		"",
		"initialization",
		"parsing",
		"conversion",
		"Dependency resolution",
		"semantic analysis",
		"class generation",
		"plugin generation",
		"output",
		"finalization"};

	private Phases() {
	}

	public static String getDescription(int phase) {
		return descriptions[phase];
	}
}
