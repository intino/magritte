package siani.tara.compiler;

import org.junit.Test;
import siani.tara.TaracRunner;

public class TaraCRunnerTest {

	@Test
	public void TestMagritteM2() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/magritteM2.txt"});
	}

	@Test
	public void TestM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/testM1.txt"});
	}

	@Test
	public void TestTafat2M2() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/tafat2M2.txt"});
	}

	@Test
	public void TestMagritteTafat2M2() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/magritteTafatM2.txt"});
	}

	@Test
	public void TestMagritteTafat2M1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/magritteTafatM1.txt"});
	}

	@Test
	public void TestTafat2M1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/tafat2M1.txt"});
	}


	@Test
	public void TestSmartCitiesM2() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/smartM2.txt"});
	}

	@Test
	public void TestSmartCitiesM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/smartM1.txt"});
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
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/octavio/workspace/tara/rt/res_test/monetM2.txt"});
	}

	@Test
	public void MonetM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/octavio/workspace/tara/rt/res_test/monetM1.txt"});
	}

	@Test
	public void MonetM0() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/octavio/workspace/tara/rt/res_test/monetM0.txt"});
	}

	@Test
	public void SampleM0() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/sampleM0.txt"});
	}

	@Test
	public void SampleM1() {
		TaracRunner.main(new String[]{"--gen-plugin", "/Users/oroncal/workspace/tara/rt/res_test/sampleM1.txt"});
	}
}
