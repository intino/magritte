package monet.tara.compiler.core;

public class Phases {

	public static final int INITIALIZATION = 1;
	public static final int PARSING = 2;
	public static final int CONVERSION = 3;
	public static final int SEMANTIC_ANALYSIS = 4;
	public static final int CLASS_GENERATION = 5;
	public static final int PLUGIN_GENERATION = 6;
	public static final int OUTPUT = 7;
	public static final int FINALIZATION = 8;
	public static final int ALL = 9;
	private static String[] descriptions = {
		"initialization",
		"parsing",
		"semantic analysis",
		"conversion",
		"class generation",
		"output",
		"finalization"};

	private Phases() {
	}

	public static String getDescription(int phase) {
		return descriptions[phase];
	}
}
