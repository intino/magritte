package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static io.intino.tara.io.Helper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AspectTest {

	@Test
	public void should_have_all_parents_of_facet() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		assertThat(graph.rootList().get(0).typeNames.size(), is(5));
		assertThat(graph.rootList().get(0).layers.size(), is(4));
	}

	@Test
	public void should_remove_parent_facet_when_children_is_removed() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeFacet("Oiled#Car");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_children_facet_when_removing_parent_facet() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeFacet("Motorized#Car");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_remove_instances_facet_when_removing_meta_facet() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeFacet("Facet#Entity");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_not_remove_facets_of_core_objects() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeFacet("Entity");
		assertThat(graph.rootList().get(0).typeNames.size(), is(5));
		assertThat(graph.rootList().get(0).layers.size(), is(4));
	}

	private Store store() {
		return new Store() {
			@Override
			public Stash stashFrom(String path) {
				return newStash("Meta", list(), list(),
						list(
								newConcept("Entity", false, true, true, "io.intino.tara.magritte.AspectTest$Entity", null, list("Concept"), list(), list(), list(), list()),
								newConcept("Car", false, false, true, "io.intino.tara.magritte.AspectTest$Car", null, list("Entity"), list(), list(), list(), list()),
								newConcept("Facet#Entity", false, true, true, "io.intino.tara.magritte.AspectTest$AspectEntity", null, list("Aspect"), list(), list(), list(), list()),
								newConcept("Motorized#Car", true, false, true, "io.intino.tara.magritte.AspectTest$MotorizedCar", null, list("Facet#Entity"), list(), list(), list(), list()),
								newConcept("Oiled#Car", false, false, true, "io.intino.tara.magritte.AspectTest$OiledCar", "Motorized#Car", list(), list(), list(), list(), list())),
						list(
								newNode("Car1", list("Car", "Oiled#Car"), list(), list())
						));
			}

			@Override
			public void writeStash(Stash stash, String path) {

			}

			@Override
			public URL resourceFrom(String path) {
				return null;
			}

			@Override
			public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
				return null;
			}

			@Override
			public String relativePathOf(URL url) {
				return null;
			}
		};
	}

	public static class Entity extends Layer {

		public Entity(Node node) {
			super(node);
		}
	}


	public static class Car extends Layer {

		public Car(Node node) {
			super(node);
		}
	}

	public static class AspectEntity extends Layer {

		public AspectEntity(Node node) {
			super(node);
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static abstract class MotorizedCar extends Layer {

		public MotorizedCar(Node node) {
			super(node);
		}
	}

	public static class OiledCar extends MotorizedCar {

		public OiledCar(Node node) {
			super(node);
		}
	}

}
