package siani.tara.compiler;

import siani.tara.TaracRunner;
import org.junit.Test;

public class TaraCRunnerTest {

	@Test
	public void compilerTest() {
		String[] args = new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/argsFile.txt"};
		TaracRunner.main(args);
	}

	@Test
	public void compilerTestRuntime() {
		Runtime rt = Runtime.getRuntime();
	}

}