package tara.compiler.core;

public class Phases {

	public static final int INITIALIZATION = 1;
	public static final int PARSING = 2;
	public static final int CONVERSION = 3;
	public static final int DEPENDENCY_RESOLUTION = 4;
	public static final int MODEL_DEPENDENCY_RESOLUTION = 5;
	public static final int SEMANTIC_ANALYSIS = 6;
	public static final int POST_ANALYSIS_RESOLUTION = 7;
	public static final int CODE_GENERATION = 8;
	public static final int REFACTOR_HISTORY = 9;
	public static final int LANGUAGE_GENERATION = 10;
	public static final int ALL = 11;
	public static final int FIRST = INITIALIZATION;
	public static final int LAST = LANGUAGE_GENERATION;
	private static String[] descriptions = {
		"",
		"initialization",
		"parsing",
		"conversion",
		"dependency resolution",
		"system dependency resolution",
		"semantic analysis",
		"post-analysis resolution",
		"class generation",
		"refactors history",
		"language generation",
		"output",
		"finalization"};

	private Phases() {
	}

	public static String getDescription(int phase) {
		return descriptions[phase];
	}
}