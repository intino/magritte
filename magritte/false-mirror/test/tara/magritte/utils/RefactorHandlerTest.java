package tara.magritte.utils;

import org.junit.Ignore;
import org.junit.Test;
import tara.io.Stash;
import tara.io.refactor.Refactors;
import tara.magritte.DynamicModel;
import tara.magritte.Instance;
import tara.magritte.Store;
import tara.magritte.stores.ResourcesStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tara.io.Helper.*;
import static tara.io.refactor.RefactorsDeserializer.refactorFrom;
import static tara.io.refactor.RefactorsSerializer.serialize;

public class RefactorHandlerTest {

	RefactorHandler handler;

	public void setUp() throws Exception {
		Refactors platformRefactors = refactorFrom(this.getClass().getResourceAsStream("/Platform"));
		Refactors applicationRefactors = refactorFrom(this.getClass().getResourceAsStream("/Application"));
		handler = new RefactorHandler(platformRefactors, applicationRefactors);
	}

	@Test
	public void being_in_build_0_should_provide_thing_when_asked_by_entity() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Entity", 0), is("Thing"));
		assertThat(handler.lastApplicationRefactor("Entity", 0), is("Entity"));
	}

	@Test
	public void should_provide_aspect() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Behavior", 0), is("Aspect"));
		assertThat(handler.lastApplicationRefactor("Behavior", 0), is("Behavior"));
	}

	@Test
	public void should_provide_bike() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Vehicle", 0), is("Vehicle"));
		assertThat(handler.lastApplicationRefactor("Vehicle", 0), is("Bike"));
	}

	@Test
	public void should_provide_electric() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Electrical", 0), is("Electrical"));
		assertThat(handler.lastApplicationRefactor("Electrical", 0), is("Electric"));
	}

	@Test
	public void should_provide_bike_starting_in_a_different_level() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Car", 1), is("Car"));
		assertThat(handler.lastApplicationRefactor("Car", 1), is("Bike"));
	}

	@Test
	public void should_provide_vehicle_starting_in_a_different_level() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Vehicle", 1), is("Vehicle"));
		assertThat(handler.lastApplicationRefactor("Vehicle", 1), is("Vehicle"));
	}

	@Test
	public void should_provide_vehicle_starting_in_a_non_existing_level() throws Exception {
		setUp();
		assertThat(handler.lastPlatformRefactor("Vehicle", 20), is("Vehicle"));
		assertThat(handler.lastApplicationRefactor("Vehicle", 20), is("Vehicle"));
	}

	@Ignore
	@Test
	public void should_refactor_instance() throws Exception {
		setUp();
		Store store = mockStore();
		DynamicModel.load("Empty", store);
		Stash stash = store.stashFrom("");
		assertThat(stash.applicationRefactorId, is(3));
		assertThat(stash.platformRefactorId, is(2));
		assertThat(stash.instances.get(0).name, is("anonymous"));
		assertThat(stash.instances.get(0).facets.get(0).name, is("NewMock"));
	}

	private Store mockStore() {
		return new Store() {

			Stash stash = null;

			@Override
			public Stash stashFrom(String path) {
				return stash == null ? emptyStash() : stash;
			}

			@Override
			public void writeStash(Stash stash, String path) {
				this.stash = stash;
			}

			@Override
			public URL resourceFrom(String path) {
				return new ResourcesStore().resourceFrom(path);
			}

			@Override
			public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Instance instance) {
				return null;
			}

			@Override
			public String relativePathOf(URL url) {
				return null;
			}
		};
	}

	@Ignore("Only execute this if Platform file is not in res folder")@Test
	public void platformRefactors() throws IOException {
		Refactors refactors = new Refactors();
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Entity", "Member"));
		refactors.add(new Refactors.Refactor("asdasdsadaaz", "Behavior", "Aspect"));
		refactors.add(new Refactors.Refactor("asdasdsadaax", "Mock", "NewMock"));
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Member", "Thing"));
		Files.write(Paths.get("test_res/platform"), serialize(refactors));
	}

	@Ignore("Only execute this if Application file is not in res folder")@Test
	public void applicationRefactors() throws IOException {
		Refactors refactors = new Refactors();
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Vehicle", "Car"));
		refactors.add(new Refactors.Refactor("asdasdsadaaz", "Electrical", "Electric"));
		refactors.add(new Refactors.Refactor("asdasdfadfsd", "Car", "Bike"));
		Files.write(Paths.get("test_res/application"), serialize(refactors));
	}

	private Stash emptyStash() {
		Stash stash = newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("NewMock", false, false, true, "tara.magritte.layers.DynamicMockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				list(newInstance("anonymous", list(newFacet("Mock", emptyList(), emptyList())))));
		stash.applicationRefactorId = 0;
		stash.platformRefactorId = 0;
		return stash;
	}

}