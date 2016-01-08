package tara.magritte;

import org.junit.Before;
import org.junit.Test;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.layers.MockLayer;
import tara.magritte.stores.AdvancedFileSystemStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static tara.io.Helper.*;

public class ModelTest {

	private Model model;
	private AdvancedFileSystemStore store;

	@Before
	public void setUp() throws Exception {
		File tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
		Files.write(Paths.get("test_res/Empty.stash"), StashSerializer.serialize(emptyStash()));
		store = new AdvancedFileSystemStore(tempDirectory);
		model = Model.load("Empty", store);
	}

	@Test
	public void new_main_should_be_saved() throws Exception {
		model.newMain("Mock", "Empty").as(MockLayer.class);
		assertThat(model.components().size(), is(1));
		assertThat(model.components().get(0).layers.size(), is(1));
		assertTrue(model.components().get(0).is(MockLayer.class));
		assertTrue(model.components().get(0).is("Mock"));
		Model reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().size(), is(1));
		assertThat(reloaded.components().get(0).layers.size(), is(1));
		assertTrue(reloaded.components().get(0).is(MockLayer.class));
		assertTrue(reloaded.components().get(0).is("Mock"));
	}

	@Test
	public void new_main_should_be_removed() throws Exception {
		MockLayer instance = model.newMain("Mock", "Empty").as(MockLayer.class);
		assertThat(model.components().size(), is(1));
		instance._remove();
		assertThat(model.components().size(), is(0));
		Model reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().size(), is(0));
	}

	@Test
	public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() throws Exception {
		MockLayer instance = model.newMain("Mock", "Empty").as(MockLayer.class);
		MockLayer toBeRemoved = model.newMain("Mock", "Empty").as(MockLayer.class);
		instance.mockLayer(toBeRemoved);
		instance.save();
		assertThat(model.components().size(), is(2));
		toBeRemoved._remove();
		assertThat(model.components().size(), is(1));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		Logger.getLogger(ModelHandler.class.getName()).addHandler(handler);

		Model reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().size(), is(1));
		assertNull(reloaded.components().get(0).as(MockLayer.class).mockLayer());
		handler.flush();
		assertThat(outputStream.toString(), containsString("has not been found"));
	}

	@Test
	public void instance_should_be_removed_from_parent_when_instance_is_removed() throws Exception {
		MockLayer instance = model.newMain("Mock", "Empty").as(MockLayer.class);
		MockLayer child = instance.newMock();
		assertThat(instance._instances().size(), is(1));
		child._remove();
		assertThat(instance._instances().size(), is(0));
	}

	@Test
	public void instance_should_be_saved_with_its_parent_and_removed(){
		MockLayer instance = model.newMain("Mock", "Empty").as(MockLayer.class);
		MockLayer child = instance.newMock();
		child.save();

		Model reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().size(), is(1));
		assertThat(reloaded.components().get(0).instances().size(), is(1));
		reloaded.components().get(0).instances().get(0).remove();
		assertThat(reloaded.components().get(0).instances().size(), is(0));

		reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().get(0).instances().size(), is(0));
		reloaded.components().get(0).remove();
		assertThat(reloaded.components().size(), is(0));

		reloaded = Model.load("Empty", store);
		assertThat(reloaded.components().size(), is(0));
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}
