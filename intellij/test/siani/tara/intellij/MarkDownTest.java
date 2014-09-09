package siani.tara.intellij;

import org.junit.Test;
import org.markdown4j.Markdown4jProcessor;

public class MarkDownTest {

	@Test
	public void testMarkDown() throws Exception {
		String html = new Markdown4jProcessor().process("This is a *bold* text");
		html = new Markdown4jProcessor().process("**Entity ResidentialBuilding**\n" +
			"\t*reference* *channels*\n" +
			"\t *Entity* \\*BusinessPremises\\*\n" +
			"\t\\*Entity\\* \\*CommonInstallation\\*\n" +
			"*Definition* *Volume*\n" +
			"*Definition* *Location*\n" +
			"*Definition* *Geolocation*\n" +
			"*Definition* *RelativeToTerrain*\n" +
			"*Definition* *RelativeToWater*\n" +
			"*Entity* *Household*\n" +
			"*Entity* *JunkRoom*\n" +
			"**Allowed facets:**\n" +
			"*tafat.siani.behaviors.thermal.ThermalStock*\n" +
			"*tafat.siani.layers.mobility.ParkingLot*");
		System.out.println(html);
	}
}
