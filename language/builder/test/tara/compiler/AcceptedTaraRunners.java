package tara.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static tara.TaracRunner.main;

@Ignore
public class AcceptedTaraRunners {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res").getAbsolutePath() + File.separator;
	}

	@Test
	public void SumusM3() {
		main(new String[]{home + "sumus/M3.txt"});
	}

	@Test
	public void TafatM3() {
		main(new String[]{home + "tafat/M3.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "monet/M2.txt"});
	}

	@Test
	public void AmidasM3() {
		main(new String[]{home + "amidas/M2.txt"});
	}

	@Test
	public void MobilityM2() {
		main(new String[]{home + "tafat/MobilityM2.txt"});
	}

	@Test
	public void MonopolyM2() {
		main(new String[]{home + "tafat/MonopolyM2.txt"});
	}

	@Test
	public void PredatorPreyM1() {
		main(new String[]{home + "tafat/PredatorPreyM1.txt"});
	}

	@Test
	public void SumusRintheimM1() {
		main(new String[]{home + "sumus/Rintheim.M1.txt"});
	}

	@Test
	public void happySenseM2() {
		main(new String[]{home + "happysense/M1.txt"});
	}

	@Test
	public void happySenseM1() {
		main(new String[]{home + "happysense/M0.txt"});
	}

	@Test
	public void MonetM1() {
		main(new String[]{home + "monet/M1.txt"});
	}

	@Test
	public void SumusApplicationTest() {
		main(new String[]{home + "sumus/M1Test.txt"});
	}

	@Test
	public void TestLanguageM1() {
		main(new String[]{home + "tafat/testM2.txt"});
	}

	@Test
	public void TestLanguageM0() {
		main(new String[]{home + "tafat/testM1.txt"});
	}

	@Test
	public void SampleM2() {
		main(new String[]{home + "sample/M2.txt"});
	}

	@Test
	public void PeriodicoM1() {
		main(new String[]{home + "sample/M1.txt"});
	}

	@Test
	public void AmidasM1() {
		main(new String[]{home + "amidas/M1-Community.txt"});
	}

	@Test
	public void SumusM1Dwellings() {
		main(new String[]{home + "sumus/M1-Community.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "monet/M0.txt"});
	}

	@Test
	public void MonetAmidasM0() {
		main(new String[]{home + "amidas/M0-Community.txt"});
	}

	@Test
	public void MonopolyM0() {
		main(new String[]{home + "tafat/MonopolyM1.txt"});
	}

	@Test
	public void SumusM2Test() {
		main(new String[]{home + "sumus/M2.txt"});
	}

	@Test
	public void SimulationM0() {
		main(new String[]{home + "tafat/TafatM1Simulation.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "tafat/MobilityM1Paris.txt"});
	}

	@Test
	public void ChpLogicM1() {
		main(new String[]{home + "tafat/chpLogicM2.txt"});
	}

}