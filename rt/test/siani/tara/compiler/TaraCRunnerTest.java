package siani.tara.compiler;

import siani.tara.TaracRunner;
import org.junit.Test;

public class TaraCRunnerTest {

	@Test
	public void compilerTest() {
		String[] args = new String[]{"--gen-plugin", "/Users/octavio/workspace/tara/rt/res_test/argsFile2.txt"};
		TaracRunner.main(args);
	}

	@Test
	public void compilerTestRuntime() {
		Runtime rt = Runtime.getRuntime();
	}

}
