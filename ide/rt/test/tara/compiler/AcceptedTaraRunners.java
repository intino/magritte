package tara.compiler;

import org.junit.Before;
import org.junit.Test;
import tara.TaracRunner;

import java.io.File;

public class AcceptedTaraRunners {


	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("res_test").getAbsolutePath()+ File.separator;
	}

	@Test
	public void SumusM2() {
		TaracRunner.main(new String[]{home + "SumusM2.txt"});
	}

	@Test
	public void SumusM1() {
		TaracRunner.main(new String[]{home + "SumusM1.txt"});
	}

	@Test
	public void SumusM0() {
		TaracRunner.main(new String[]{home + "SumusM0.txt"});
	}

	@Test
	public void TafatM1() {
		TaracRunner.main(new String[]{home + "TafatM1.txt"});
	}

	@Test
	public void TafatTestM2() {
		TaracRunner.main(new String[]{home + "TafatTestM2.txt"});
	}

	@Test
	public void TafatTestM1() {
		TaracRunner.main(new String[]{home + "TafatTestM1.txt"});
	}

	@Test
	public void TafatM0() {
		TaracRunner.main(new String[]{home + "TafatM0.txt"});
	}

	@Test
	public void MonetAnimalesM0() {
		TaracRunner.main(new String[]{home + "monetTestM0.txt"});
	}

	@Test
	public void MonetAnimalesM1() {
		TaracRunner.main(new String[]{home + "monetTestM1.txt"});
	}

	@Test
	public void MonetAnimalesM2() {
		TaracRunner.main(new String[]{home + "monetTestM2.txt"});
	}

	@Test
	public void MonetShopM2() {
		TaracRunner.main(new String[]{home + "monetShopM2.txt"});
	}

	@Test
	public void MonetM2() {
		TaracRunner.main(new String[]{home + "monetM2.txt"});
	}

	@Test
	public void MonetM1() {
		TaracRunner.main(new String[]{home + "monetM1.txt"});
	}

	@Test
	public void MonetM0() {
		TaracRunner.main(new String[]{home + "monetM0.txt"});
	}

	@Test
	public void TeselaM2() {
		TaracRunner.main(new String[]{home + "teselaM2.txt"});
	}

	@Test
	public void TeselaM1() {
		TaracRunner.main(new String[]{home + "teselaM1.txt"});
	}

	@Test
	public void TeselaM0() {
		TaracRunner.main(new String[]{home + "teselaM0.txt"});
	}

	@Test
	public void MonopolyM2() {
		TaracRunner.main(new String[]{home + "MonopolyM2.txt"});
	}

	@Test
	public void MonopolyM1() {
		TaracRunner.main(new String[]{home + "MonopolyM1.txt"});
	}

	@Test
	public void MonopolyM0() {
		TaracRunner.main(new String[]{home + "MonopolyM0.txt"});
	}

	@Test
	public void MobilityM2() {
		TaracRunner.main(new String[]{home + "CarsMobilityM2.txt"});
	}

	@Test
	public void MobilityM1() {
		TaracRunner.main(new String[]{home + "CarsMobilityM1.txt"});
	}

	@Test
	public void MobilityM0() {
		TaracRunner.main(new String[]{home + "CarsMobilityM0.txt"});
	}
}
