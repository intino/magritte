package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import siani.tara.lang.LinkNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

public class ModelTest {

	Model model;

	@Before
	public void setUp() throws Exception {
//		model = ModelLoader.load("/Users/octavio/workspace/tara/rt/res_test/", "siani");
	}

	@Test
	public void testSearchNode() throws Exception {
		testCorrectSearch(model.searchNode("ResidentialBuilding"), "ResidentialBuilding");
		testCorrectSearch(model.searchNode("BusLine.Transfer"), "Transfer");
		testCorrectSearch(model.searchNode("ResidentialBuilding.Household.Radiator"), "Radiator");
		testCorrectSearch(model.searchNode("Street.Section.Section"), "Section");
		testCorrectSearch(model.searchNode("Street.Section.Section.Section"), "Section");
		testCorrectSearch(model.searchNode("Street.Section.Section.Lane"), "Lane");
		testCorrectSearch(model.searchNode("Street.Segment.UrbanFurniture.Shelter"), "Shelter");
		testCorrectSearch(model.searchNode("Region.City.District"), "District");
		testCorrectSearch(model.searchNode("PowerPlant.ResidentialBuilding"), "ResidentialBuilding");
		testCorrectSearch(model.searchNode("PowerPlant.Building.CommonInstallation.Lighting"), "Lighting");
		testCorrectSearch(model.searchNode("PowerPlant.Building.CommonInstallation.Fridge"), "Fridge");
		testCorrectSearch(model.searchNode("SubwayStation"), "SubwayStation");
		testCorrectSearch(model.searchNode("FuelStation"), "FuelStation");
		testIncorrectSearch(model.searchNode("Region.City.Island"));
		testIncorrectSearch(model.searchNode("Street.Segment.Segment"));
		testIncorrectSearch(model.searchNode("Street.Segment.Segment.Segment"));
		testIncorrectSearch(model.searchNode("Street.Region"));
	}

	private void testIncorrectSearch(Node node) {
		Assert.assertNull(node);
	}

	private void testCorrectSearch(Node node, String name) {
		Assert.assertNotNull(node);
		if (node instanceof LinkNode) Assert.assertEquals(name, ((LinkNode) node).getDestinyName());
		else Assert.assertEquals(name, node.getName());
	}


	private String rebuild(String metaNode) {
		String[] names = metaNode.split("\\.");
		String qn = "";
		boolean inLink = false;
		for (String name : names) {
			if (name.startsWith("[")) inLink = true;
			if (!inLink) qn += "." + name;
			else if (name.endsWith("@link]")) qn += "." + name.replace("@link]", "").replace("[","");
			if (name.endsWith("]")) inLink = false;
		}
		return qn.substring(1);
	}

	@Test
	public void testName() throws Exception {
		String metanode = "Animales.Perro.[Animales.Animal.FechaNacimiento@link]";
		Assert.assertEquals("Animales.Perro.FechaNacimiento", rebuild(metanode));

		metanode = "Animales.Perro.[FechaNacimiento@link].Milu";
		Assert.assertEquals("Animales.Perro.FechaNacimiento.Milu", rebuild(metanode));

		metanode = "Animales.Perro.[FechaNacimiento@link].Milu.Aq344";
		Assert.assertEquals("Animales.Perro.FechaNacimiento.Milu.Aq344", rebuild(metanode));
		metanode = "";
		Assert.assertEquals("", rebuild(metanode));

		metanode = "Animales";
		Assert.assertEquals("Animales", rebuild(metanode));

		metanode = "Animales.Perro.[FechaNacimiento@link].Milu.Aq344.[Animales.Animal.FechaNacimiento@link]";
		Assert.assertEquals("Animales.Perro.FechaNacimiento.Milu.Aq344.FechaNacimiento", rebuild(metanode));
	}
}
