package io.intino.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static io.intino.tara.TaracRunner.main;

@Ignore
public class AcceptedTaraRunners {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res").getAbsolutePath() + File.separator;
	}

	@Test
	public void cesarM2() {
		main(new String[]{home + "sandbox/confFiles/cesar/M2.txt"});
	}

	@Test
	public void legioM2() {
		main(new String[]{home + "sandbox/confFiles/legio/M2.txt"});
	}

	@Test
	public void humketM2() {
		main(new String[]{home + "sandbox/confFiles/humket/Humket.txt"});
	}

	@Test
	public void humketM1() {
		main(new String[]{home + "sandbox/confFiles/humket/Humket-bot.txt"});
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
		final long l = System.currentTimeMillis();
		main(new String[]{home + "sandbox/confFiles/sumus/M3.txt"});
		System.out.println((System.currentTimeMillis() - l) / 1000);
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
	public void exampleM3() {
		main(new String[]{home + "sandbox/confFiles/example/m3.txt"});
	}

	@Test
	public void ebarBrdigeM2() {
		main(new String[]{home + "sandbox/confFiles/ebar/m2.txt"});
	}

	@Test
	public void exampleM1() {
		main(new String[]{home + "sandbox/confFiles/example/m1.txt"});
	}

	@Test
	public void exampleM2() {
		main(new String[]{home + "sandbox/confFiles/example/m2.txt"});
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
	public void konos_M2() {
		main(new String[]{home + "sandbox/confFiles/konos/m2.txt"});
	}

	@Test
	public void pandora_cesar() {
		main(new String[]{home + "sandbox/confFiles/konos/konos-cesar.txt"});
	}

	@Test
	public void pandora_channels() {
		main(new String[]{home + "sandbox/confFiles/konos/konos-channels.txt"});
	}

	@Test
	public void AmidasM3() {
		main(new String[]{home + "sandbox/confFiles/amidas/M3.txt"});
	}

	@Test
	public void AmidasEbar() {
		main(new String[]{home + "sandbox/confFiles/amidas/M2.txt"});
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
		main(new String[]{home + "sandbox/confFiles/amidas/Comnunity-test.txt"});
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
	public void happySensePre() {
		main(new String[]{home + "sandbox/confFiles/happysense/pre.txt"});
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
		main(new String[]{home + "sandbox/confFiles/sumus/Comnunity-test.txt"});
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
	public void MonopolyM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/MonopolyM1.txt"});
	}

	@Test
	public void sumusM3Test() {
		main(new String[]{home + "sandbox/confFiles/sumus/M3Test.txt"});
	}

	@Test
	public void sumusEbar() {
		main(new String[]{home + "sandbox/confFiles/ebar/ebar jose.txt"});
	}

	@Test
	public void sumusCallgenie() {
		main(new String[]{home + "sandbox/confFiles/sumus/callgenie.txt"});
	}

	@Test
	public void sumusEbarTest() {
		main(new String[]{home + "sandbox/confFiles/sumus/ebar_test.txt"});
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