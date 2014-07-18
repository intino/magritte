package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import siani.tara.lang.LinkNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.util.ModelLoader;

public class ModelTest {

	Model model;

	@Before
	public void setUp() throws Exception {
		model = ModelLoader.load("/Users/oroncal/workspace/tara/rt/res_test/", "siani");
	}

	@Test
	public void testSearchNode() throws Exception {
		testCorrectSearch(model.searchNode("ResidentialBuilding"), "ResidentialBuilding");
		testCorrectSearch(model.searchNode("BusLine.Transfer"), "Transfer");
		testCorrectSearch(model.searchNode("ResidentialBuilding.Household.Appliance"), "Appliance");
		testCorrectSearch(model.searchNode("Street.Section.Section"), "Section");
		testCorrectSearch(model.searchNode("Street.Section.Section.Section"), "Section");
		testCorrectSearch(model.searchNode("Street.Section.Section.Lane"), "Lane");
		testCorrectSearch(model.searchNode("Street.Segment.UrbanFurniture.Shelter"),"Shelter");
		testCorrectSearch(model.searchNode("Region.City.District"),"District");
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
		if (node instanceof LinkNode) Assert.assertEquals(((LinkNode) node).getDestinyName(), name);
		else Assert.assertEquals(node.getName(), name);
	}


}
