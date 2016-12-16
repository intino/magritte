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
	public void cesarM2() {
		main(new String[]{home + "sandbox/confFiles/cesar/M3.txt"});
	}

	@Test
	public void legioM2() {
		main(new String[]{home + "sandbox/confFiles/legio/M2.txt"});
	}


	@Test
	public void teseoM2() {
		main(new String[]{home + "sandbox/confFiles/pandora/teseo.txt"});
	}

	@Test
	public void legioM1() {
		main(new String[]{home + "sandbox/confFiles/legio/legioExample.txt"});
	}

	@Test
	public void mastersimM2() {
		main(new String[]{home + "sandbox/confFiles/mastersim/M2.txt"});
	}

	@Test
	public void mastersimM1Test() {
		main(new String[]{home + "sandbox/confFiles/mastersim/M1Test.txt"});
	}

	@Test
	public void consulM2() {
		main(new String[]{home + "sandbox/confFiles/cesar/consul.txt"});
	}

	@Test
	public void cesarSystem() {
		main(new String[]{home + "sandbox/confFiles/cesar/system.txt"});
	}

	@Test
	public void SumusM3() {
		main(new String[]{home + "sandbox/confFiles/sumus/M3.txt"});
	}

	@Test
	public void tafatM3() {
		main(new String[]{home + "sandbox/confFiles/tafat/M3.txt"});
	}

	@Test
	public void tafatTestM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafatTestApplication.txt"});
	}

	@Test
	public void tafatTestM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafatTestSystem.txt"});
	}

	@Test
	public void dhsM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/DHS-M2.txt"});
	}

	@Test
	public void MonetM3() {
		main(new String[]{home + "sandbox/confFiles/monet/M3.txt"});
	}

	@Test
	public void MonetSampleM2() {
		main(new String[]{home + "sandbox/confFiles/monet/M2Sample.txt"});
	}

	@Test
	public void MonetRegistroSolicitantes() {
		main(new String[]{home + "sandbox/confFiles/registroSolicitantes/m2.txt"});
	}

	@Test
	public void SampleM3() {
		main(new String[]{home + "sandbox/confFiles/sample/M3.txt"});
	}

	@Test
	public void Sherlock_M3() {
		main(new String[]{home + "sandbox/confFiles/sherlock/m2.txt"});
	}

	@Test
	public void Sherlock_M2() {
		main(new String[]{home + "sandbox/confFiles/sherlock/m2.txt"});
	}

	@Test
	public void Sherlock_M1() {
		main(new String[]{home + "sandbox/confFiles/sherlock/m1.txt"});
	}

	@Test
	public void pandora_M2() {
		main(new String[]{home + "sandbox/confFiles/pandora/m2.txt"});
	}

	@Test
	public void pandora_cesar() {
		main(new String[]{home + "sandbox/confFiles/pandora/pandora-cesar.txt"});
	}

	@Test
	public void pandora_channels() {
		main(new String[]{home + "sandbox/confFiles/pandora/pandora-channels.txt"});
	}

	@Test
	public void AmidasM3() {
		main(new String[]{home + "sandbox/confFiles/amidas/M3.txt"});
	}

	@Test
	public void amidasUltimate() {
		main(new String[]{home + "sandbox/confFiles/amidas/AmidasUltimate.txt"});
	}


	@Test
	public void amidasUltimateTest() {
		main(new String[]{home + "sandbox/confFiles/amidas/AmidasUltimateTest.txt"});
	}

	@Test
	public void AmidasTest() {
		main(new String[]{home + "sandbox/confFiles/amidas/M3Test.txt"});
	}


	@Test
	public void AmidasM1() {
		main(new String[]{home + "sandbox/confFiles/amidas/M1-Community.txt"});
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
	public void TafatDomainM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/domain.txt"});
	}

	@Test
	public void PredatorPreyM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/PredatorPreyM1.txt"});
	}

	@Test
	public void happySenseM2() {
		main(new String[]{home + "sandbox/confFiles/happysense/M2.txt"});
	}

	@Test
	public void happySenseSatellite() {
		main(new String[]{home + "sandbox/confFiles/happysense/satellite.txt"});
	}

	@Test
	public void happySenseM1() {
		main(new String[]{home + "sandbox/confFiles/happysense/M1.txt"});
	}

	@Test
	public void happySenseM1Exp() {
		main(new String[]{home + "sandbox/confFiles/happysense/M1exp.txt"});
	}

	@Test
	public void MonetM2() {
		main(new String[]{home + "sandbox/confFiles/monet/M2.txt"});
	}

	@Test
	public void MonetM2_Parques() {
		main(new String[]{home + "sandbox/confFiles/monet/M2_Parques.txt"});
	}

	@Test
	public void MonetM2_Payments() {
		main(new String[]{home + "sandbox/confFiles/monet/M2_Payments.txt"});
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
	public void SampleTest() {
		main(new String[]{home + "sandbox/confFiles/sample/Test.txt"});
	}

	@Test
	public void SamplePlatformM3() {
		main(new String[]{home + "sandbox/confFiles/sample/SamplePlatform.txt"});
	}

	@Test
	public void SumusM1Dwellings() {
		main(new String[]{home + "sandbox/confFiles/sumus/M1-Community.txt"});
	}

	@Test
	public void MonetM0() {
		main(new String[]{home + "sandbox/confFiles/monet/M1.txt"});
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
	public void sumusM3Test() {
		main(new String[]{home + "sandbox/confFiles/sumus/M3Test.txt"});
	}

	@Test
	public void sumusEbar() {
		main(new String[]{home + "sandbox/confFiles/sumus/ebar.txt"});
	}

	@Test
	public void sumusEbarPre() {
		main(new String[]{home + "sandbox/confFiles/sumus/ebar-pre.txt"});
	}

	@Test
	public void GreenPowerM2Test() {
		main(new String[]{home + "sandbox/confFiles/sumus/greenPowerM2.txt"});
	}

	@Test
	public void SimulationM0() {
		main(new String[]{home + "sandbox/confFiles/tafat/TafatM1Simulation.txt"});
	}

	@Test
	public void MobilityM0Paris() {
		main(new String[]{home + "sandbox/confFiles/tafat/MobilityM1Paris.txt"});
	}

}