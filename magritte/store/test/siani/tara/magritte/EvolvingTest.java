package siani.tara.magritte;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static siani.tara.magritte.Node.Member.Component;

public class EvolvingTest {

	@Test
	public void sameVersion() throws Exception {
		Graph priorGraph = EvolvingProject.start().v1().createDocuments("A.X", "B", "C.Y").graph();
		Graph novelGraph = EvolvingProject.start().v1().load(priorGraph).graph();

		assertNotNull(novelGraph.get("Concept"));
		assertNotNull(novelGraph.get("Folder"));
		assertNotNull(novelGraph.get("Document"));
		assertNotNull(novelGraph.get("Students"));
		assertEquals(0, novelGraph.get("Students").members(Component).size());
		assertEquals(3, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));
	}

	@Test
	public void v1v2() throws Exception {
		Graph priorGraph = EvolvingProject.start().v1().createDocuments("A.X", "B", "C.Y").graph();
		Graph novelGraph = EvolvingProject.start().v2().load(priorGraph).graph();

		assertNotNull(novelGraph.get("Concept"));
		assertNotNull(novelGraph.get("Folder"));
		assertNotNull(novelGraph.get("Document"));
		assertNotNull(novelGraph.get("Students"));
		assertEquals(0, novelGraph.get("Students").members(Component).size());
		assertEquals(3, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));

		assertEquals(3, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("$", novelGraph.get("Students").members(Component).get(0).members(Component).get(0).get("linkSet"));

		assertEquals(3, novelGraph.get("Students").members(Component).size());
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("$", novelGraph.get("Students").members(Component).get(1).members(Component).get(0).get("linkSet"));

		assertEquals(3, novelGraph.get("Students").members(Component).size());
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));
		assertEquals("$", novelGraph.get("Students").members(Component).get(2).members(Component).get(0).get("linkSet"));
	}


	@Test
	public void v2v3() throws Exception {
		Graph priorGraph = EvolvingProject.start().v2().createDocuments("A.X", "B", "C.Y").graph();
		Graph novelGraph = EvolvingProject.start().v3().load(priorGraph).createDocuments("D.Z", "E").graph();

		assertNotNull(novelGraph.get("Concept"));
		assertNotNull(novelGraph.get("Folder"));
		assertNotNull(novelGraph.get("Document"));
		assertNotNull(novelGraph.get("Students"));
		assertEquals(0, novelGraph.get("Students").members(Component).size());
		assertEquals(5, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("X", novelGraph.get("Students").members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("$", novelGraph.get("Students").members(Component).get(1).members(Component).get(0).get("linkSet"));
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));
		assertEquals("Y", novelGraph.get("Students").members(Component).get(2).members(Component).get(0).get("linkSet"));
		assertEquals("D", novelGraph.get("Students").members(Component).get(3).get("label"));
		assertEquals("Z", novelGraph.get("Students").members(Component).get(3).members(Component).get(0).get("linkSet"));
		assertEquals("E", novelGraph.get("Students").members(Component).get(4).get("label"));
		assertEquals("#", novelGraph.get("Students").members(Component).get(4).members(Component).get(0).get("linkSet"));
	}

	@Test
	public void v3v4() throws Exception {
		Graph priorGraph = EvolvingProject.start().v3().createDocuments("A.X", "B", "C.Y").graph();
		Graph novelGraph = EvolvingProject.start().v4().load(priorGraph).createDocuments("D.Z", "E").graph();

		assertNotNull(novelGraph.get("Concept"));
		assertNotNull(novelGraph.get("Folder"));
		assertNotNull(novelGraph.get("Document"));
		assertNotNull(novelGraph.get("Students"));
		assertEquals(0, novelGraph.get("Students").members(Component).size());
		assertEquals(5, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("X", novelGraph.get("Students").members(Component).get(0).members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("#", novelGraph.get("Students").members(Component).get(1).members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));
		assertEquals("Y", novelGraph.get("Students").members(Component).get(2).members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("D", novelGraph.get("Students").members(Component).get(3).get("label"));
		assertEquals("Z", novelGraph.get("Students").members(Component).get(3).members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("E", novelGraph.get("Students").members(Component).get(4).get("label"));
		assertEquals("&", novelGraph.get("Students").members(Component).get(4).members(Component).get(0).members(Component).get(0).get("linkSet"));
	}

	@Test
	public void v4v5() throws Exception {
		Graph priorGraph = EvolvingProject.start().v4().createDocuments("A.X", "B", "C.Y").graph();
		Graph novelGraph = EvolvingProject.start().v5().load(priorGraph).createDocuments("D.Z", "E").graph();

		assertNotNull(novelGraph.get("Concept"));
		assertNotNull(novelGraph.get("Folder"));
		assertNotNull(novelGraph.get("Document"));
		assertNotNull(novelGraph.get("Students"));
		assertEquals(0, novelGraph.get("Students").members(Component).size());
		assertEquals(5, novelGraph.get("Students").members(Component).size());
		assertEquals("A", novelGraph.get("Students").members(Component).get(0).get("label"));
		assertEquals("X", novelGraph.get("Students").members(Component).get(0).members(Component).get(0).get("linkSet"));
		assertEquals("B", novelGraph.get("Students").members(Component).get(1).get("label"));
		assertEquals("&", novelGraph.get("Students").members(Component).get(1).members(Component).get(0).get("linkSet"));
		assertEquals("C", novelGraph.get("Students").members(Component).get(2).get("label"));
		assertEquals("Y", novelGraph.get("Students").members(Component).get(2).members(Component).get(0).get("linkSet"));
		assertEquals("D", novelGraph.get("Students").members(Component).get(3).get("label"));
		assertEquals("Z", novelGraph.get("Students").members(Component).get(3).members(Component).get(0).get("linkSet"));
		assertEquals("E", novelGraph.get("Students").members(Component).get(4).get("label"));
		assertEquals("%", novelGraph.get("Students").members(Component).get(4).members(Component).get(0).get("linkSet"));
	}


}
