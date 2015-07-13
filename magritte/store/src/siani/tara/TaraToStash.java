package siani.tara;


import siani.tara.compiler.core.errorcollection.TaraException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TaraToStash {

	private static final Logger LOG = Logger.getLogger(TaraToStash.class.getName());

	private TaraToStash() {
	}

	public static void main(String[] args) {
		System.out.println("Starting compiling");
		try {
			if (checkArgumentsNumber(args)) throw new TaraException("Error finding agrs file");
			TaraToStashRunner.runTaraCompiler(args);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			System.exit(1);
		}
	}

	private static boolean checkArgumentsNumber(String[] args) {
		if (args.length != 2) {
			System.err.println("%%mThere is no arguments for tara compiler/%m");
			return true;
		}
		return false;
	}
}
