package siani.tara.magritte;

import org.junit.Test;
import siani.tara.magritte.editors.GraphEditor;
import siani.tara.magritte.schema.GateReference;
import siani.tara.magritte.schema.MemoryGraph;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static siani.tara.magritte.Tag.Case;
import static siani.tara.magritte.Tag.Root;

public class AcceptedStashUID {


	@Test
	public void testEqualsAndHashCode() throws Exception {
		assertThat(GateReference.of("//TheSpot/thekey:poi"), is(GateReference.of("//TheSpot/thekey")));
		assertThat(GateReference.of("//TheSpot/thekey:poi"), is(GateReference.of("//TheSpot/thekey")));
		assertThat(GateReference.of("//TheSpot/thekey:poi"), is(GateReference.of("//TheSpot/thekey:poi")));
		assertThat(GateReference.of("//TheSpot/thekey:poi"), not(GateReference.of("//TheSpot/zxc:poi")));
		assertThat(GateReference.of("//TheSpot/thekey#123"), not(GateReference.of("//TheSpot/thekey:poi")));
		assertThat(GateReference.of("//TheSpot/thekey:poi"), is(GateReference.of("//TheSpot/thekey:iop")));
		assertThat(GateReference.of("//TheSpot/thekey:poi").hashCode(), is(-632498775));
		assertThat(GateReference.of("//TheSpot/thekey:123").hashCode(), is(-632498775));
		assertThat(GateReference.of("//TheSpot/thekey#123").hashCode(), is(-1757904808));
	}

	@Test
	public void testUID() throws Exception {
		assertThat(GateReference.of("//Customer/vibur").spot(), is("Customer"));
		assertThat(GateReference.of("//Customer/vibur").value(), is("vibur"));
		assertThat(GateReference.of("//Customer/vibur").toString(), is("//Customer/vibur"));
		assertThat(GateReference.of("//Sale/2015_10_01_20_10").spot(), is("Sale"));
		assertThat(GateReference.of("//Sale/2015_10_01_20_10").value(), is("2015_10_01_20_10"));
		assertThat(GateReference.of("//Sale/2015_10_01_20_10#1.png").value(), is("2015_10_01_20_10#1.png"));
	}

	@Test
	public void testUIDOfNode() throws Exception {
		Graph graph = Example.start().m0().graph();
		assertThat(GateReference.of(graph.get("mary")).toString(), is("//Person/mary"));
		assertThat(GateReference.of(graph.get("mary.name")).toString(), is("//Person/mary.name"));
		assertThat(GateReference.of(graph.get("pongo")).value(), is("pongo"));
		assertThat(GateReference.of(graph.get("pongo")).toString(), is("//zosco/pongo:Animal"));
		assertThat(GateReference.of(graph.get("pongo")), is(GateReference.of("//zosco/pongo")));
		assertThat(GateReference.of(graph.get("pongo")), is(GateReference.of("//zosco/pongo:Animal")));
		assertThat(GateReference.of(graph.get("pongo")).hashCode(), is(GateReference.of("//zosco/pongo:Animal").hashCode()));
		assertThat(GateReference.of(graph.get("pongo.name")).toString(), is("//zosco/pongo.name:Animal.Name"));
	}


	public static class Example extends GraphEditor {

		private Example(Graph graph) {
			super(graph);
		}

		public static Example start() {
			return new Example(new MemoryGraph());
		}

		public Example m1() {
			def("Person").type("Concept");
			def("Person.FullName").type("Concept");
			def("Animal|zosco").type("Concept");
			def("Animal.Name|criopa").type("Concept");
			return this;
		}

		public Example m0() {
			m1();
			def("mary").type("Person").set(Case, Root).has("mary.name");
			def("mary.name").type("Person.FullName").set(Case);

			def("pongo").type("Animal").set(Case, Root).has("pongo.name");
			def("pongo.name").type("Animal.Name");
			return this;
		}

		@Override
		public void write() {

		}

	}
}
