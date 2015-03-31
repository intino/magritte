package siani.tara.compiler;

import org.junit.Before;
import org.junit.Test;
import siani.tara.TaracRunner;

public class TaraCRunnerTest {


	private String home;

	@Before
	public void setUp() throws Exception {
		home = System.getProperty("user.home");
	}

	@Test
	public void SumusM2() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/SumusM2.txt"});
	}

	@Test
	public void SumusM1() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/SumusM1.txt"});
	}

	@Test
	public void SumusM0() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/SumusM0.txt"});
	}


	@Test
	public void TafatM2() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/TafatM2.txt"});
	}

	@Test
	public void TafatM1() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/TafatM1.txt"});
	}

	@Test
	public void TafatM0() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/TafatM0.txt"});
	}

	@Test
	public void MonetM2() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/monetM2.txt"});
	}

	@Test
	public void MonetM1() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/monetM1.txt"});
	}

	@Test
	public void MonetM0() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/monetM0.txt"});
	}

	@Test
	public void TeselaM2() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/teselaM2.txt"});
	}

	@Test
	public void TeselaM1() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/teselaM1.txt"});
	}

	@Test
	public void TeselaM0() {
		TaracRunner.main(new String[]{"--gen-plugin", home + "/workspace/tara/rt/res_test/teselaM0.txt"});
	}

}
