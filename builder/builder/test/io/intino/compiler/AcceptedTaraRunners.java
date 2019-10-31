package io.intino.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.time.Instant;

import static io.intino.tara.TaracRunner.main;

@Ignore
public class AcceptedTaraRunners {

	private String home;

	@Before
	public void setUp() {
		home = new File("test-res").getAbsolutePath() + File.separator;
	}

	@Test
	public void adquiverM2() {
		io.intino.tara.TaracRunner.main(new String[]{home + "sandbox/confFiles/adquiver/M2.txt"});
	}

	@Test
	public void sezzetM2() {
		main(new String[]{home + "sandbox/confFiles/sezzet/M2.txt"});
	}

	@Test
	public void octanaM1() {
		main(new String[]{home + "sandbox/confFiles/octana/M1.txt"});
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
	public void legioM1() {
		main(new String[]{home + "sandbox/confFiles/legio/M1.txt"});
	}


	@Test
	public void consulM2() {
		main(new String[]{home + "sandbox/confFiles/cesar/consul.txt"});
	}

	@Test
	public void SumusM3() {
		final long l = System.currentTimeMillis();
		main(new String[]{home + "sandbox/confFiles/sumus/M3.txt"});
		System.out.println((System.currentTimeMillis() - l) / 1000);
	}

	@Test
	public void tafatM3() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafat.txt"});
	}

	@Test
	public void tafatM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafat-test-m2.txt"});
	}

	@Test
	public void tafatM1() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafat-test-m1.txt"});
	}

	@Test
	public void ebarM2() {
		main(new String[]{home + "sandbox/confFiles/ebar/ebar.txt"});
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
	public void exampleM1() {
		main(new String[]{home + "sandbox/confFiles/example/m1.txt"});
	}

	@Test
	public void exampleM2() {
		main(new String[]{home + "sandbox/confFiles/example/m2.txt"});
	}

	@Test
	public void ness_m2() {
		main(new String[]{home + "sandbox/confFiles/ness/m2.txt"});
	}

	@Test
	public void ness_m1() {
		main(new String[]{home + "sandbox/confFiles/ness/m1.txt"});
	}

	@Test
	public void konos_M1() {
		System.out.println(Instant.now().toString());
		main(new String[]{home + "sandbox/confFiles/konos/m1-test.txt"});
		System.out.println(Instant.now().toString());
	}

	@Test
	public void konos_M2() {
		System.out.println(Instant.now().toString());
		main(new String[]{home + "sandbox/confFiles/konos/m2.txt"});
		System.out.println(Instant.now().toString());
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
	public void happySenseM1Test() {
		main(new String[]{home + "sandbox/confFiles/happysense/M1test.txt"});
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
	public void MonopolyM2() {
		main(new String[]{home + "sandbox/confFiles/tafat/tafat-monopoly-m2.txt"});
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

}