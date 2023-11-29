package io.intino.magritte.framework;

import io.intino.magritte.io.Stash;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static io.intino.magritte.io.Helper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AspectTest {

	@Test
	public void should_have_all_parents_of_aspect() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		assertThat(graph.rootList().get(0).typeNames.size(), is(5));
		assertThat(graph.rootList().get(0).layers.size(), is(4));
	}

	@Test
	public void should_remove_parent_aspect_when_children_is_removed() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeAspect("Car$Oiled");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_children_aspect_when_removing_parent_aspect() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeAspect("Car$Motorized");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_remove_instances_aspect_when_removing_meta_aspect() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeAspect("Entity$Facet");
		assertThat(graph.rootList().get(0).typeNames.size(), is(2));
		assertThat(graph.rootList().get(0).layers.size(), is(2));
	}

	@Test
	public void should_not_remove_aspects_of_core_objects() throws Exception {
		Graph graph = new Graph(store()).loadStashes("foo");
		graph.rootList().get(0).removeAspect("Entity");
		assertThat(graph.rootList().get(0).typeNames.size(), is(5));
		assertThat(graph.rootList().get(0).layers.size(), is(4));
	}

	private Store store() {
		return new Store() {
			@Override
			public Stash stashFrom(String path) {
				return newStash("Meta", list(), list(),
						list(
								newConcept("Entity", false, true, false, true, "io.intino.magritte.framework.AspectTest$Entity", null, list("Concept"), list(), list(), list(), list()),
								newConcept("Car", false, false, false, true, "io.intino.magritte.framework.AspectTest$Car", null, list("Entity"), list(), list(), list(), list()),
								newConcept("Entity$Facet", false, true, true, true, "io.intino.magritte.framework.AspectTest$AspectEntity", null, list("Aspect"), list(), list(), list(), list()),
								newConcept("Car$Motorized", true, false, true, true, "io.intino.magritte.framework.AspectTest$MotorizedCar", null, list("Entity$Facet"), list(), list(), list(), list()),
								newConcept("Car$Oiled", false, false, true, true, "io.intino.magritte.framework.AspectTest$OiledCar", "Car$Motorized", list(), list(), list(), list(), list())),
						list(
								newNode("Car1", list("Car", "Car$Oiled"), list(), list())
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
