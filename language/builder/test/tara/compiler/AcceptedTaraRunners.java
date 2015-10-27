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
	public void SumusM1() {
		main(new String[]{home + "SumusM1.txt"});
	}

	@Test
	public void SumusM0() {
		main(new String[]{home + "SumusM0.txt"});
	}

	@Test
	public void TafatM2() {
		main(new String[]{home + "TafatM2.txt"});
	}

	@Test
	public void TafatM1() {
		main(new String[]{home + "TafatM1.txt"});
	}

	@Test
	public void TafatM0() {
		main(new String[]{home + "TafatM0.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "monetM2.txt"});
	}

	@Test
	public void MonetM1() {
		main(new String[]{home + "monetM1.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "monetM0.txt"});
	}

	@Test
	public void MonopolyM2() {
		main(new String[]{home + "MonopolyM2.txt"});
	}

	@Test
	public void MonopolyM1() {
		main(new String[]{home + "MonopolyM1.txt"});
	}

	@Test
	public void MonopolyM0() {
		main(new String[]{home + "MonopolyM0.txt"});
	}

	@Test
	public void MobilityM2() {
		main(new String[]{home + "MobilityM2.txt"});
	}

	@Test
	public void MobilityM1() {
		main(new String[]{home + "MobilityM1.txt"});
	}

	@Test
	public void PowerGridM1() {
		main(new String[]{home + "PowerGridM1.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "MobilityM0Paris.txt"});
	}

	@Test
	public void SumusM1Reunion() {
		main(new String[]{home + "LaReunion.txt"});
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
	public void TestM0() {
		main(new String[]{home + "TestM0.txt"});
	}
}