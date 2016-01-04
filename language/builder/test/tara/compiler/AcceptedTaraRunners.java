package tara.compiler;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static tara.TaracRunner.main;

public class AcceptedTaraRunners {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res").getAbsolutePath() + File.separator;
	}

	@Test
	public void SumusM2() {
		main(new String[]{home + "SumusM2.txt"});
	}

	@Test
	public void TafatM2() {
		main(new String[]{home + "TafatM2.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "monetM2.txt"});
	}

	@Test
	public void MonetAmidasM2() {
		main(new String[]{home + "Monet.Amidas.M2.txt"});
	}

	@Test
	public void MobilityM1() {
		main(new String[]{home + "MobilityM1.txt"});
	}

	@Test
	public void MonopolyM1() {
		main(new String[]{home + "MonopolyM1.txt"});
	}

	@Test
	public void MonetM1() {
		main(new String[]{home + "monetM1.txt"});
	}

	@Test
	public void MonetAmidasM1() {
		main(new String[]{home + "Monet.Amidas.M1.txt"});
	}

	@Test
	public void SumusM1Analytics() {
		main(new String[]{home + "SumusM1Analytics.txt"});
	}

	@Test
	public void SumusM1Dwellings() {
		main(new String[]{home + "SumusM1Dwellings.txt"});
	}

	@Test
	public void SumusM1Test() {
		main(new String[]{home + "SumusM1Test.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "monetM0.txt"});
	}

	@Test
	public void MonetAmidasM0() {
		main(new String[]{home + "Monet.Amidas.M0.txt"});
	}

	@Test
	public void MonopolyM0() {
		main(new String[]{home + "MonopolyM0.txt"});
	}

	@Test
	public void SimulationM0() {
		main(new String[]{home + "TafatM0Simulation.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "MobilityM0Paris.txt"});
	}

	@Test
	public void TestM2() {
		main(new String[]{home + "TestM2.txt"});
	}

	@Test
	public void TestM1() {
		main(new String[]{home + "TestM1.txt"});
	}

	@Test
	public void SumusTestM1() {
		main(new String[]{home + "SumusTest.text"});
	}

	@Test
	public void MonetTestM2() {
		main(new String[]{home + "MonetM2Index.txt"});
	}

	@Test
	public void MonetTestM1() {
		main(new String[]{home + "MonetM1Index.txt"});
	}

	@Test
	public void MonetTestM0() {
		main(new String[]{home + "MonetM0Index.txt"});
	}

	@Test
	public void TestM0() {
		main(new String[]{home + "TestM0.txt"});
	}
}