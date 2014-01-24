package monet.tara;

import monet.tara.compiler.rt.TaraRtConstants;

import java.io.File;

/**
 * Created by oroncal on 27/12/13.
 */
public class TaracRunner {

	public static String TEMP_PATH = "";
	private TaracRunner() {

	}

	public static void main(String[] args) {

		if (args.length != 2) {
			if (args.length != 3 || !"--indy".equals(args[2])) {
				System.err.println("There is no arguments for tara compiler");
				System.exit(1);
			}
		}
		final boolean transpile = "stubs".equals(args[0]);
		final String outputPath = args[1];
		final File path = new File(args[2]);
		if (!path.exists()) {
			System.err.println("Arguments file for Tara compiler not found");
			System.exit(1);
		}

		try {
			Class.forName("monet.tara.core.TranspilationUnit");
		} catch (Throwable e) {
			System.err.println(TaraRtConstants.NO_TARA);
			System.exit(1);
		}
		try {
			if (transpile){
				TaraTranspilerRunner.runTaraTranspiler(path, TEMP_PATH);
			} else {
				TaraCompilerRunner.runTaraCompiler(TEMP_PATH, outputPath);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
