package tara.compiler.core;

public class Phases {

	static final int INITIALIZATION = 1;
	static final int PARSING = 2;
	static final int CONVERSION = 3;
	static final int DEPENDENCY_RESOLUTION = 4;
	static final int MODEL_DEPENDENCY_RESOLUTION = 5;
	static final int SEMANTIC_ANALYSIS = 6;
	static final int POST_ANALYSIS_RESOLUTION = 7;
	static final int CODE_GENERATION = 8;
	static final int STASH_GENERATION = 9;
	public static final int LANGUAGE_GENERATION = 10;
	static final int ALL = 11;
	static final int FIRST = INITIALIZATION;
	static final int LAST = LANGUAGE_GENERATION;
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
		"stash generation",
		"language generation",
		"output",
		"finalization"};

	private Phases() {
	}

	public static String getDescription(int phase) {
		return descriptions[phase];
	}
}
