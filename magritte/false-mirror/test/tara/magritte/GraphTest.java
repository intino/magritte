package tara.magritte;

import org.junit.Ignore;
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

@Ignore
public class GraphTest {

	private static final String emptyStash = "Empty";
	private static final String oneMockStash = "OneMock";
	private static final String Extension = ".stash";

	@Test
	public void new_main_should_be_saved() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		graph.createRoot(MockLayer.class, emptyStash).save();
		assertThat(graph.rootList().size(), is(1));
		assertThat(graph.rootList().get(0).layers.size(), is(1));
		assertTrue(graph.rootList().get(0).is(MockLayer.class));
		assertTrue(graph.rootList().get(0).is("Mock"));
		Graph reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.rootList().size(), is(1));
		assertThat(reloaded.rootList().get(0).layers.size(), is(1));
		assertTrue(reloaded.rootList().get(0).is(MockLayer.class));
		assertTrue(reloaded.rootList().get(0).is("Mock"));
	}

	@Test
	public void new_main_should_be_removed() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
		assertThat(graph.rootList().size(), is(1));
		mockLayer.delete();
		assertThat(graph.rootList().size(), is(0));
		Graph reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.rootList().size(), is(0));
	}

	@Test
	public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
		MockLayer toBeRemoved = graph.createRoot(MockLayer.class, emptyStash);
		mockLayer.mockLayer(toBeRemoved);
		mockLayer.save();
		assertThat(graph.rootList().size(), is(2));
		toBeRemoved.delete();
		assertThat(graph.rootList().size(), is(1));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		Logger.getLogger(GraphHandler.class.getName()).addHandler(handler);

		Graph reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);;
		assertThat(reloaded.rootList().size(), is(1));
		assertNull(reloaded.rootList().get(0).as(MockLayer.class).mockLayer());
		handler.flush();
		assertThat(outputStream.toString(), containsString("has not been found"));
	}

	@Test
	public void node_should_be_removed_from_parent_when_node_is_removed() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
		MockLayer child = mockLayer.newMock();
		assertThat(mockLayer.componentList().size(), is(1));
		child.delete();
		assertThat(mockLayer.componentList().size(), is(0));
	}

	@Test
	public void node_should_be_saved_with_its_parent_and_removed() {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
		MockLayer child = mockLayer.newMock();
		child.save();

		Graph reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.rootList().size(), is(1));
		assertThat(reloaded.rootList().get(0).componentList().size(), is(1));
		reloaded.rootList().get(0).componentList().get(0).delete();
		assertThat(reloaded.rootList().get(0).componentList().size(), is(0));

		reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.rootList().get(0).componentList().size(), is(0));
		reloaded.rootList().get(0).delete();
		assertThat(reloaded.rootList().size(), is(0));

		reloaded = Graph.load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
		assertThat(reloaded.rootList().size(), is(0));
	}

	@Test
	public void should_clear_all_model_platform_and_application() {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		assertThat(graph.rootList().size(), is(0));
		graph.createRoot(MockLayer.class, emptyStash);
		assertThat(graph.rootList().size(), is(1));
		assertThat(graph.<MockApplication>application().mockLayerList().size(), is(1));
		assertThat(graph.<MockPlatform>platform().mockLayerList().size(), is(1));
		graph.clear();
		assertThat(graph.rootList().size(), is(0));
		assertThat(graph.<MockApplication>application().mockLayerList().size(), is(0));
		assertThat(graph.<MockPlatform>platform().mockLayerList().size(), is(0));
	}

	@Test
	public void should_reload_all_model_platform_and_application_when_there_is_one_element() {
		Graph graph = Graph.load(oneMockStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		assertThat(graph.rootList().size(), is(2));
		assertThat(graph.<MockApplication>application().mockLayerList().size(), is(2));
		assertThat(graph.<MockPlatform>platform().mockLayerList().size(), is(2));
		graph.reload();
		assertThat(graph.rootList().size(), is(2));
		assertThat(graph.<MockApplication>application().mockLayerList().size(), is(2));
		assertThat(graph.<MockPlatform>platform().mockLayerList().size(), is(2));
	}

	@Test
	public void should_reload_nodes_as_they_are_in_the_stash() {
		Graph graph = Graph.load(oneMockStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		assertThat(graph.rootList().size(), is(2));
		assertNull(graph.rootList(MockLayer.class).get(0).mockLayer());
		graph.rootList(MockLayer.class).get(0).mockLayer(graph.rootList(MockLayer.class).get(1));
		assertNotNull(graph.rootList(MockLayer.class).get(0).mockLayer());
		graph.reload();
		assertThat(graph.rootList().size(), is(2));
		assertNull(graph.rootList(MockLayer.class).get(0).mockLayer());
	}

	@Test
	public void fields_should_be_kept_the_same() {
		Graph graph = Graph.load(oneMockStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		List<Node> components = new ArrayList<>(graph.model.componentList());
		Set<String> openedStashes = new HashSet<>(graph.openedStashes);
		Set<String> languages = new HashSet<>(graph.languages);
		Map<String, tara.magritte.Concept> concepts = new HashMap<>(graph.concepts);
		Map<String, Node> nodes = new HashMap<>(graph.nodes);
		List<NodeLoader> loaders = new ArrayList<>(graph.loaders);
		List<MockLayer> mockLayersInPlatform = new ArrayList<>(graph.<MockPlatform>platform().mockLayerList());
		List<MockLayer> mockLayersInApplication = new ArrayList<>(graph.<MockApplication>application().mockLayerList());
		graph.reload();
		assertThat(components.size(), is(graph.model.componentList().size()));
		assertThat(openedStashes.size(), is(graph.openedStashes.size()));
		assertThat(languages.size() - 1, is(graph.languages.size()));
		assertThat(concepts.size(), is(graph.concepts.size()));
		assertThat(nodes.size(), is(graph.nodes.size()));
		assertThat(loaders.size(), is(graph.loaders.size()));
		assertThat(mockLayersInPlatform.size(), is(graph.<MockPlatform>platform().mockLayerList().size()));
		assertThat(mockLayersInApplication.size(), is(graph.<MockApplication>application().mockLayerList().size()));
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
			public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
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
		stash.nodes.add(newNode(oneMockStash + "#x", list("Mock"), emptyList(), emptyList()));
		stash.nodes.add(newNode(oneMockStash + "#y", list("Mock"), emptyList(), emptyList()));
		return stash;
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
			list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}
