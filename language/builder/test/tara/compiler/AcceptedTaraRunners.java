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
	public void SumusM2() {
		main(new String[]{home + "sumus/M2.txt"});
	}

	@Test
	public void TafatM2() {
		main(new String[]{home + "tafat/M2.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "monet/M2.txt"});
	}

	@Test
	public void AmidasM2() {
		main(new String[]{home + "amidas/M2.txt"});
	}

	@Test
	public void MobilityM1() {
		main(new String[]{home + "tafat/MobilityM1.txt"});
	}

	@Test
	public void MonopolyM1() {
		main(new String[]{home + "tafat/MonopolyM1.txt"});
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
	public void MonetM1() {
		main(new String[]{home + "monet/M1.txt"});
	}

	@Test
	public void SumusApplicationTest() {
		main(new String[]{home + "sumus/M1Test.txt"});
	}

	@Test
	public void TestLanguageM1() {
		main(new String[]{home + "tafat/testM1.txt"});
	}

	@Test
	public void TestLanguageM0() {
		main(new String[]{home + "tafat/testM0.txt"});
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
		main(new String[]{home + "amidas/M1.txt"});
	}

	@Test
	public void SumusM1Dwellings() {
		main(new String[]{home + "sumus/M1.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "monet/M0.txt"});
	}

	@Test
	public void MonetAmidasM0() {
		main(new String[]{home + "amidas/M0.txt"});
	}

	@Test
	public void MonopolyM0() {
		main(new String[]{home + "tafat/MonopolyM0.txt"});
	}

	@Test
	public void M0Test() {
		main(new String[]{home + "sumus/M0Test.txt"});
	}

	@Test
	public void SimulationM0() {
		main(new String[]{home + "tafat/TafatM0Simulation.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "tafat/MobilityM0Paris.txt"});
	}

	@Test
	public void ChpLogicM1() {
		main(new String[]{home + "tafat/chpLogicM1.txt"});
	}

}