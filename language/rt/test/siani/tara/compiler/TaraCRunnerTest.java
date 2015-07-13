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
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/SumusM2.txt"});
	}

	@Test
	public void SumusM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/SumusM1.txt"});
	}

	@Test
	public void SumusM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/SumusM0.txt"});
	}

	@Test
	public void TafatM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/TafatM1.txt"});
	}

	@Test
	public void TafatTestM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/TafatTestM2.txt"});
	}

	@Test
	public void TafatTestM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/TafatTestM1.txt"});
	}

	@Test
	public void TafatM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/TafatM0.txt"});
	}

	@Test
	public void MonetAnimalesM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetTestM0.txt"});
	}

	@Test
	public void MonetAnimalesM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetTestM1.txt"});
	}

	@Test
	public void MonetAnimalesM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetTestM2.txt"});
	}

	@Test
	public void MonetShopM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetShopM2.txt"});
	}

	@Test
	public void MonetM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetM2.txt"});
	}

	@Test
	public void MonetM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetM1.txt"});
	}

	@Test
	public void MonetM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/monetM0.txt"});
	}

	@Test
	public void TeselaM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/teselaM2.txt"});
	}

	@Test
	public void TeselaM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/teselaM1.txt"});
	}

	@Test
	public void TeselaM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/teselaM0.txt"});
	}

	@Test
	public void MonopolyM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/MonopolyM2.txt"});
	}

	@Test
	public void MonopolyM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/MonopolyM1.txt"});
	}

	@Test
	public void MonopolyM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/MonopolyM0.txt"});
	}

	@Test
	public void MobilityM2() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/CarsMobilityM2.txt"});
	}

	@Test
	public void MobilityM1() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/CarsMobilityM1.txt"});
	}

	@Test
	public void MobilityM0() {
		TaracRunner.main(new String[]{home + "/workspace/tara/compiler/rt/res_test/CarsMobilityM0.txt"});
	}
}
