package tara.compiler.core;

public class Phases {

	public static final int INITIALIZATION = 1;
	public static final int PARSING = 2;
	public static final int CONVERSION = 3;
	public static final int SEMANTIC_PRE_ANALYSIS = 4;
	public static final int DEPENDENCY_RESOLUTION = 5;
	public static final int SEMANTIC_ANALYSIS = 6;
	public static final int STASH_GENERATION = 7;
	public static final int LANGUAGE_GENERATION = 8;
	public static final int OUTPUT = 9;
	public static final int ALL = 10;
	public static final int FIRST = INITIALIZATION;
	public static final int LAST = OUTPUT;
	private static String[] descriptions = {
		"",
		"initialization",
		"parsing",
		"conversion",
		"semantic pre-analysis",
		"dependency resolution",
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
