package monet.tara.compiler.rt;

import java.io.File;

/**
 * Created by oroncal on 27/12/13.
 */
public class TaracRunner {

	private TaracRunner() {
	}

	public static void main(String[] args) {

		if (args.length != 2) {
			if (args.length != 3 || !"--indy".equals(args[2])) {
				System.err.println("There is no arguments for tara compiler");
				System.exit(1);
			}
		}

		final boolean forStubs = "stubs".equals(args[0]);
		final File argsFile = new File(args[1]);

		if (!argsFile.exists()) {
			System.err.println("Arguments file for Tara compiler not found");
			System.exit(1);
		}

		try {
			DependentTaracRunner.runTarac(forStubs, argsFile);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
