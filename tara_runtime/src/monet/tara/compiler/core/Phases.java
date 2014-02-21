package monet.tara.compiler.core;

/**
 * Created by oroncal on 24/01/14.
 */
public class Phases {

	public static final int INITIALIZATION = 1;
	public static final int PARSING = 2;
	public static final int SEMANTIC_ANALYSIS = 3;
	public static final int CONVERSION = 4;
	public static final int CLASS_GENERATION = 5;
	public static final int PLUGIN_GENERATION = 6;
	public static final int OUTPUT = 7;
	public static final int FINALIZATION = 8;
	public static final int ALL = 9;
	public static String[] descriptions = {
		"startup",
		"initialization",
		"parsing",
		"semantic analysis",
		"conversion",
		"canonization",
		"class generation",
		"output",
		"cleanup"};

	public static String getDescription(int phase) {
		return descriptions[phase];
	}
}
