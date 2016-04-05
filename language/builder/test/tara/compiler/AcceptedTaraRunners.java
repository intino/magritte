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
		main(new String[]{home + "sandbox/confFiles/sumus/M3.txt"});
	}

	@Test
	public void TafatM3() {
		main(new String[]{home + "sandbox/confFiles/tafat/M3.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "sandbox/confFiles/monet/M2.txt"});
	}

	@Test
	public void MD_M3() {
		main(new String[]{home + "sandbox/confFiles/md/m3.txt"});
	}

	@Test
	public void MD_M2() {
		main(new String[]{home + "sandbox/confFiles/md/m2.txt"});
	}

	@Test
	public void AmidasM3() {
		main(new String[]{home + "sandbox/confFiles/amidas/M3.txt"});
	}

	@Test
	public void MobilityM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/MobilityM2.txt"});
	}

	@Test
	public void MonopolyM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/MonopolyM2.txt"});
	}

	@Test
	public void PredatorPreyM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/PredatorPreyM1.txt"});
	}

	@Test
	public void SumusRintheimM1() {
		main(new String[]{home + "sandbox/confFiles/sumus/Rintheim.M3.txt"});
	}

	@Test
	public void happySenseM2() {
		main(new String[]{home + "sandbox/confFiles/happysense/M2.txt"});
	}

	@Test
	public void happySenseM1() {
		main(new String[]{home + "sandbox/confFiles/happysense/M1.txt"});
	}

	@Test
	public void MonetM1() {
		main(new String[]{home + "sandbox/confFiles/monet/M1.txt"});
	}

	@Test
	public void SumusApplicationTest() {
		main(new String[]{home + "sandbox/confFiles/sumus/M1Test.txt"});
	}

	@Test
	public void TafatTestM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/testM2.txt"});
	}

	@Test
	public void TafatTestM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/testM1.txt"});
	}

	@Test
	public void SampleM3() {
		main(new String[]{home + "sandbox/confFiles/sample/Sample2.txt"});
	}

	@Test
	public void AmidasM1() {
		main(new String[]{home + "sandbox/confFiles/amidas/M1-Community.txt"});
	}

	@Test
	public void SumusM1Dwellings() {
		main(new String[]{home + "sandbox/confFiles/sumus/M1-Community.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "sandbox/confFiles/monet/M0.txt"});
	}

	@Test
	public void MonetAmidasM0() {
		main(new String[]{home + "sandbox/confFiles/amidas/M0-Community.txt"});
	}

	@Test
	public void MonopolyM0() {
		main(new String[]{home + "sandbox/confFiles/tafat/MonopolyM1.txt"});
	}

	@Test
	public void SumusM2Test() {
		main(new String[]{home + "sandbox/confFiles/sumus/M2.txt"});
	}

	@Test
	public void SimulationM0() {
		main(new String[]{home + "sandbox/confFiles/tafat/TafatM1Simulation.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "sandbox/confFiles/tafat/MobilityM1Paris.txt"});
	}

	@Test
	public void ChpLogicM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/chpLogicM2.txt"});
	}

}