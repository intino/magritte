package monet.tara.compiler.codegeneration;

import java.io.File;

public class PathManager {

	public static final String IDE = "intellij";
	public static final String SRC = "src";
	public static final String BUILD = "build";
	public static final String RES = "res";
	public static final String SEP = File.separator;

	private PathManager() {
	}

	public static String getBuildIdeResDir(File tempDir) {
		return tempDir + SEP + SRC + SEP + IDE + SEP + RES + SEP;
	}

	public static String getBuildIdeDir(File tempDir) {
		return tempDir + SEP + BUILD + SEP + IDE + SEP;
	}

	public static String getSrcDir(File dir) {
		return dir + SEP + SRC + SEP;
	}

	public static String getBuildDir(File dir) {
		return dir + SEP + BUILD + SEP;
	}

	public static String getSrcIdeDir(File tempDir) {
		return tempDir + SEP + SRC + SEP + IDE + SEP + SRC + SEP;
	}

	public static String getSrcProjectDir(File tempDir, String project) {
		return tempDir + SEP + SRC + SEP + project + SEP + SRC + SEP;
	}
}
