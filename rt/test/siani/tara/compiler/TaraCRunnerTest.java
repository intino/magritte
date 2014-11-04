package siani.tara.compiler;

import org.junit.Test;
import siani.tara.TaracRunner;

public class TaraCRunnerTest {

	@Test
	public void TestMagritteM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/magritteM2.txt"});
	}

	@Test
	public void TestM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/testM1.txt"});
	}


	@Test
	public void TestM0() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/testM1.txt"});

		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/testM0.txt"});
	}

	@Test
	public void TafatM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/tafatEngine.txt"});
	}


	@Test
	public void TafatM0() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/tafatEngine.txt"});

		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/tafatM1.txt"});
	}

	@Test
	public void MonetM2() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/monet.txt"});
	}


	@Test
	public void MonetM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/monet.txt"});

		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/externa.txt"});
	}
}
