package monet.tara.compiler.rt;

/**
 * Created by oroncal on 26/12/13.
 */
public class TaraRtConstants {
	public static final String PATCHERS = "patchers";
	public static final String ENCODING = "encoding";
	public static final String OUTPUTPATH = "outputpath";
	public static final String FINAL_OUTPUTPATH = "final_outputpath";
	public static final String END = "end";
	public static final String SRC_FILE = "src_file";
	public static final String COMPILED_START = "%%c";
	public static final String COMPILED_END = "/%c";
	public static final String TO_RECOMPILE_START = "%%rc";
	public static final String TO_RECOMPILE_END = "/%rc";
	public static final String MESSAGES_START = "%%m";
	public static final String MESSAGES_END = "/%m";
	public static final String SEPARATOR = "#%%#%%%#%%%%%%%%%#";
	public static final String PRESENTABLE_MESSAGE = "@#$%@# Presentable:";
	public static final String CLEAR_PRESENTABLE = "$@#$%^ CLEAR_PRESENTABLE";
	public static final String NO_TARA = "Cannot compile Tara files: no Tara generator is defined";
}
