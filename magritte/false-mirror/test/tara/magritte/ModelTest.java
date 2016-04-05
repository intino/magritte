package tara.magritte;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.layers.MockLayer;
import tara.magritte.modelwrappers.MockApplication;
import tara.magritte.modelwrappers.MockPlatform;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static tara.io.Helper.*;

public class ModelTest {

	private static final String emptyStash = "Empty";
	private static final String oneMockStash = "OneMock";
	private static final String Extension = ".stash";

	@Test
	public void new_main_should_be_saved() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		model.newMain(MockLayer.class, emptyStash).save();
		assertThat(model.components().size(), is(1));
		assertThat(model.components().get(0).layers.size(), is(1));
		assertTrue(model.components().get(0).is(MockLayer.class));
		assertTrue(model.components().get(0).is("Mock"));
		Model reloaded = Model.load(emptyStash, model.store);
		assertThat(reloaded.components().size(), is(1));
		assertThat(reloaded.components().get(0).layers.size(), is(1));
		assertTrue(reloaded.components().get(0).is(MockLayer.class));
		assertTrue(reloaded.components().get(0).is("Mock"));
	}

	@Test
	public void new_main_should_be_removed() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer instance = model.newMain(MockLayer.class, emptyStash);
		assertThat(model.components().size(), is(1));
		instance.delete();
		assertThat(model.components().size(), is(0));
		Model reloaded = Model.load(emptyStash, model.store);
		assertThat(reloaded.components().size(), is(0));
	}

	@Test
	public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer instance = model.newMain(MockLayer.class, emptyStash);
		MockLayer toBeRemoved = model.newMain(MockLayer.class, emptyStash);
		instance.mockLayer(toBeRemoved);
		instance.save();
		assertThat(model.components().size(), is(2));
		toBeRemoved.delete();
		assertThat(model.components().size(), is(1));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		Logger.getLogger(ModelHandler.class.getName()).addHandler(handler);

		Model reloaded = Model.load(emptyStash, model.store);
		assertThat(reloaded.components().size(), is(1));
		assertNull(reloaded.components().get(0).as(MockLayer.class).mockLayer());
		handler.flush();
		assertThat(outputStream.toString(), containsString("has not been found"));
	}

	@Test
	public void instance_should_be_removed_from_parent_when_instance_is_removed() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer instance = model.newMain(MockLayer.class, emptyStash);
		MockLayer child = instance.newMock();
		assertThat(instance.instances().size(), is(1));
		child.delete();
		assertThat(instance.instances().size(), is(0));
	}

	@Test
	public void instance_should_be_saved_with_its_parent_and_removed() {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer instance = model.newMain(MockLayer.class, emptyStash);
		MockLayer child = instance.newMock();
		child.save();

		Model reloaded = Model.load(emptyStash, model.store).init(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.components().size(), is(1));
		assertThat(reloaded.components().get(0).instances().size(), is(1));
		reloaded.components().get(0).instances().get(0).delete();
		assertThat(reloaded.components().get(0).instances().size(), is(0));

		reloaded = Model.load(emptyStash, model.store).init(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.components().get(0).instances().size(), is(0));
		reloaded.components().get(0).delete();
		assertThat(reloaded.components().size(), is(0));

		reloaded = Model.load(emptyStash, model.store).init(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.components().size(), is(0));
	}

	@Test
	public void should_clear_all_model_platform_and_application() {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		assertThat(model.components().size(), is(0));
		model.newMain(MockLayer.class, emptyStash);
		assertThat(model.components().size(), is(1));
		assertThat(model.<MockApplication>application().mockLayerList().size(), is(1));
		assertThat(model.<MockPlatform>platform().mockLayerList().size(), is(1));
		model.clear();
		assertThat(model.components().size(), is(0));
		assertThat(model.<MockApplication>application().mockLayerList().size(), is(0));
		assertThat(model.<MockPlatform>platform().mockLayerList().size(), is(0));
	}

	@Test
	public void should_reload_all_model_platform_and_application_when_there_is_one_element() {
		Model model = Model.load(oneMockStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		assertThat(model.components().size(), is(2));
		assertThat(model.<MockApplication>application().mockLayerList().size(), is(2));
		assertThat(model.<MockPlatform>platform().mockLayerList().size(), is(2));
		model.reload();
		assertThat(model.components().size(), is(2));
		assertThat(model.<MockApplication>application().mockLayerList().size(), is(2));
		assertThat(model.<MockPlatform>platform().mockLayerList().size(), is(2));
	}

	@Test
	public void should_reload_instances_as_they_are_in_the_stash() {
		Model model = Model.load(oneMockStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		assertThat(model.components().size(), is(2));
		assertNull(model.components(MockLayer.class).get(0).mockLayer());
		model.components(MockLayer.class).get(0).mockLayer(model.components(MockLayer.class).get(1));
		assertNotNull(model.components(MockLayer.class).get(0).mockLayer());
		model.reload();
		assertThat(model.components().size(), is(2));
		assertNull(model.components(MockLayer.class).get(0).mockLayer());
	}

	@Test
	public void fields_should_be_kept_the_same() {
		Model model = Model.load(oneMockStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		List<Instance> components = new ArrayList<>(model.soil.components());
		Set<String> openedStashes = new HashSet<>(model.openedStashes);
		Set<String> languages = new HashSet<>(model.languages);
		Map<String, tara.magritte.Concept> concepts = new HashMap<>(model.concepts);
		Map<String, Instance> instances = new HashMap<>(model.instances);
		List<InstanceLoader> loaders = new ArrayList<>(model.loaders);
		List<MockLayer> mockLayersInPlatform = new ArrayList<>(model.<MockPlatform>platform().mockLayerList());
		List<MockLayer> mockLayersInApplication = new ArrayList<>(model.<MockApplication>application().mockLayerList());
		model.reload();
		assertThat(components.size(), is(model.soil.components().size()));
		assertThat(openedStashes.size(), is(model.openedStashes.size()));
		assertThat(languages.size(), is(model.languages.size()));
		assertThat(concepts.size(), is(model.concepts.size()));
		assertThat(instances.size(), is(model.instances.size()));
		assertThat(loaders.size(), is(model.loaders.size()));
		assertThat(mockLayersInPlatform.size(), is(model.<MockPlatform>platform().mockLayerList().size()));
		assertThat(mockLayersInApplication.size(), is(model.<MockApplication>application().mockLayerList().size()));
	}

	private Store mockStore() {
		return new Store() {

			Map<String, Stash> store = new HashMap<String, Stash>(){{
				put(emptyStash + Extension, emptyStash());
				put(oneMockStash + Extension, oneMockStash());
			}};

			@Override
			public Stash stashFrom(String path) {
				return store.get(path);
			}

			@Override
			public void writeStash(Stash stash, String path) {
				store.put(path, composeStash(path, stash));
			}

			@Override
			public URL resourceFrom(String path) {
				return null;
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

	private Stash oneMockStash() {
		Stash stash = emptyStash();
		stash.instances.add(newInstance(oneMockStash + "#x", list(newFacet("Mock", list(), list()))));
		stash.instances.add(newInstance(oneMockStash + "#y", list(newFacet("Mock", list(), list()))));
		return stash;
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}
