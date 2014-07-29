package siani.tara.intellij;

import org.junit.Test;
import org.markdown4j.Markdown4jProcessor;

public class MarkDownTest {

	@Test
	public void testMarkDown() throws Exception {
		String html = new Markdown4jProcessor().process("This is a _bold_ text");
		System.out.println(html);
		html = new Markdown4jProcessor().process("**Entity ResidentialBuilding**\n" +
			"_reference_ _channels_\n" +
			"_Entity_ _BusinessPremises_\n" +
			"_Entity_ _CommonInstallation_\n" +
			"_Definition_ _Volume_\n" +
			"_Definition_ _Location_\n" +
			"_Definition_ _Geolocation_\n" +
			"_Definition_ _RelativeToTerrain_\n" +
			"_Definition_ _RelativeToWater_\n" +
			"_Entity_ _Household_\n" +
			"_Entity_ _JunkRoom_\n" +
			"_*Allowed facets:*_\n" +
			"*tafat.siani.behaviors.thermal.ThermalStock*\n" +
			"*tafat.siani.layers.mobility.ParkingLot*");
		System.out.println(html);
	}
}
