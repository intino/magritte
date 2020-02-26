package io.intino.magritte.framework;

import io.intino.magritte.framework.layers.MockLayer;
import io.intino.magritte.framework.layers.SubMockLayer;
import io.intino.magritte.framework.modelviews.MockApplication;
import io.intino.magritte.framework.modelviews.MockPlatform;
import io.intino.magritte.framework.stores.FileSystemStore;
import io.intino.magritte.framework.stores.InMemoryFileStore;
import io.intino.magritte.io.Stash;
import org.apache.commons.io.FileUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static java.util.logging.Logger.getGlobal;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GraphTest {

	@Test
	public void new_main_should_be_saved() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		graph.createRoot(MockLayer.class, TestHelper.emptyStash).save$();
		assertThat(graph.rootList().size(), is(1));
		assertThat(graph.rootList().get(0).layers.size(), is(1));
		assertTrue(graph.rootList().get(0).is(MockLayer.class));
		assertTrue(graph.rootList().get(0).is("Mock"));
		Graph reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().size(), is(1));
		assertThat(reloaded.rootList().get(0).layers.size(), is(1));
		assertTrue(reloaded.rootList().get(0).is(MockLayer.class));
		assertTrue(reloaded.rootList().get(0).is("Mock"));
	}

	@Test
	public void new_main_should_be_removed() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		assertThat(graph.rootList().size(), is(1));
		mockLayer.delete$();
		assertThat(graph.rootList().size(), is(0));
		Graph reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().size(), is(0));
	}

	@Test
	public void should_store_with_canonical_name() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		graph.createRoot(MockLayer.class, "tara\\magritte").save$();
		assertThat(graph.rootList().get(0).stash(), is("tara/magritte"));
		Graph reloaded = new Graph(graph.store).loadStashes("tara/magritte");
		assertThat(reloaded.rootList().size(), is(1));
		assertThat(reloaded.rootList().get(0).stash(), is("tara/magritte"));
	}

	@Test
	public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		MockLayer toBeRemoved = graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		mockLayer.mockLayer(toBeRemoved);
		mockLayer.save$();
		assertThat(graph.rootList().size(), is(2));
		toBeRemoved.delete$();
		assertThat(graph.rootList().size(), is(1));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		getGlobal().addHandler(handler);

		Graph reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().size(), is(1));
		assertNull(reloaded.rootList().get(0).as(MockLayer.class).mockLayer());
		handler.flush();
		assertThat(outputStream.toString(), containsString("has not been found"));
	}

	@Test
	public void node_should_be_removed_from_parent_when_node_is_removed() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		MockLayer child = mockLayer.newMock();
		assertThat(mockLayer.componentList$().size(), is(1));
		child.delete$();
		assertThat(mockLayer.componentList$().size(), is(0));
	}

	@Test
	public void node_should_be_saved_with_its_parent_and_removed() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		MockLayer child = mockLayer.newMock();
		child.save$();

		Graph reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().size(), is(1));
		assertThat(reloaded.rootList().get(0).componentList().size(), is(1));
		reloaded.rootList().get(0).componentList().get(0).delete();
		assertThat(reloaded.rootList().get(0).componentList().size(), is(0));

		reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().get(0).componentList().size(), is(0));
		reloaded.rootList().get(0).delete();
		assertThat(reloaded.rootList().size(), is(0));

		reloaded = new Graph(graph.store).loadStashes(TestHelper.emptyStash);
		assertThat(reloaded.rootList().size(), is(0));
	}

	@Test
	public void should_clear_all_model_platform_and_application() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		assertThat(graph.rootList().size(), is(0));
		graph.createRoot(MockLayer.class, TestHelper.emptyStash);
		assertThat(graph.rootList().size(), is(1));
		MatcherAssert.assertThat(graph.as(MockApplication.class).mockLayerList().size(), is(1));
		MatcherAssert.assertThat(graph.as(MockPlatform.class).mockLayerList().size(), is(1));
		graph.clear();
		assertThat(graph.rootList().size(), is(0));
		assertThat(graph.as(MockApplication.class).mockLayerList().size(), is(0));
		assertThat(graph.as(MockPlatform.class).mockLayerList().size(), is(0));
	}

	@Test
	public void should_reload_all_model_platform_and_application_when_there_is_one_element() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
		assertThat(graph.rootList().size(), is(2));
		assertThat(graph.as(MockApplication.class).mockLayerList().size(), is(2));
		assertThat(graph.as(MockPlatform.class).mockLayerList().size(), is(2));
		graph.reload();
		assertThat(graph.rootList().size(), is(2));
		assertThat(graph.as(MockApplication.class).mockLayerList().size(), is(2));
		assertThat(graph.as(MockPlatform.class).mockLayerList().size(), is(2));
	}

	@Test
	public void should_reload_nodes_as_they_are_in_the_stash() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
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
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
		List<Node> components = new ArrayList<>(graph.model.componentList());
		Set<String> openedStashes = new HashSet<>(graph.openedStashes);
		Set<String> languages = new HashSet<>(graph.languages);
		Map<String, Concept> concepts = new HashMap<>(graph.concepts);
		Map<String, Node> nodes = new HashMap<>(graph.nodes);
		List<NodeLoader> loaders = new ArrayList<>(graph.loaders);
		List<MockLayer> mockLayersInPlatform = new ArrayList<>(graph.as(MockPlatform.class).mockLayerList());
		List<MockLayer> mockLayersInApplication = new ArrayList<>(graph.as(MockApplication.class).mockLayerList());
		graph.reload();
		assertThat(components.size(), is(graph.model.componentList().size()));
		assertThat(openedStashes.size(), is(graph.openedStashes.size()));
		assertThat(languages.size(), is(graph.languages.size()));
		assertThat(concepts.size(), is(graph.concepts.size()));
		assertThat(nodes.size(), is(graph.nodes.size()));
		assertThat(loaders.size(), is(graph.loaders.size()));
		assertThat(mockLayersInPlatform.size(), is(graph.as(MockPlatform.class).mockLayerList().size()));
		assertThat(mockLayersInApplication.size(), is(graph.as(MockApplication.class).mockLayerList().size()));
	}

	@Test
	public void stash_must_be_loaded_automatically_when_node_is_created_for_a_given_path() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		graph.createRoot(MockLayer.class, TestHelper.oneMockStash, "z");
		assertThat(graph.find(MockLayer.class).size(), is(3));
	}

	@Test
	public void node_cannot_be_created_if_already_exists() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer y = graph.createRoot(MockLayer.class, TestHelper.oneMockStash, "y");
		assertNull(y);
		assertThat(graph.find(MockLayer.class).size(), is(2));
	}

	@Test
	public void should_remove_whole_path() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
		assertThat(graph.find(MockLayer.class).size(), is(2));
		graph.remove(TestHelper.oneMockStash);
		assertThat(graph.find(MockLayer.class).size(), is(0));
		assertThat(new Graph(graph.store).loadStashes(TestHelper.oneMockStash).find(MockLayer.class).size(), is(0));
	}

	@Test
	public void should_clone_node_correctly() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
		List<MockLayer> layers = graph.find(MockLayer.class);
		assertThat(layers.size(), is(2));
		assertThat(layers.get(0).componentList$().size(), is(0));
		assertThat(layers.get(1).componentList$().size(), is(0));
		Node clone = layers.get(1).core$().clone(layers.get(0).core$());
		assertThat(graph.find(MockLayer.class).size(), is(3));
		assertThat(graph.node(layers.get(0).core$().id() + "." + layers.get(1).name$()), is(clone));
		assertThat(layers.get(0).componentList$().size(), is(1));
		assertThat(layers.get(1).componentList$().size(), is(0));
	}

	@Test
	public void should_clone_node_correctly_when_is_a_root_node() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.oneMockStash);
		List<MockLayer> layers = graph.find(MockLayer.class);
		assertThat(layers.size(), is(2));
		assertThat(layers.get(0).componentList$().size(), is(0));
		assertThat(layers.get(1).componentList$().size(), is(0));
		Node clone = layers.get(1).core$().clone("newStash");
		assertNotNull(clone);
		assertThat(graph.find(MockLayer.class).size(), is(3));
		assertThat(graph.node("newStash#" + layers.get(1).name$()), is(clone));
		assertThat(layers.get(0).componentList$().size(), is(0));
		assertThat(layers.get(1).componentList$().size(), is(0));
	}

	@Test
	public void should_load_dependant_stashes() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.firstStash);
		List<MockLayer> mockLayers = graph.find(MockLayer.class);
		assertThat(mockLayers.size(), is(3));
		assertThat(mockLayers.get(0).mockLayer(), is(mockLayers.get(1)));
		assertThat(mockLayers.get(1).mockLayer(), is(mockLayers.get(2)));
		assertThat(mockLayers.get(2).mockLayer(), is(mockLayers.get(0)));
	}

	@Test
	public void should_load_dependant_stashes_by_uses() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.dependantStashByUse);
		assertThat(graph.find(MockLayer.class).size(), is(4));
	}

	@Test
	public void should_have_just_one_concept() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.dependantStashByUse);
		assertThat(graph.conceptList().size(), is(1));
	}

	@Test
	public void should_load_all_stashes_when_using_in_memory_file_store() throws Exception {
		File temp = new File("temp");
		FileUtils.deleteDirectory(temp);
		FileSystemStore store = new FileSystemStore(temp);
		store.writeStash(TestHelper.oneMockStash(), TestHelper.oneMockStash + TestHelper.Extension);
		store.writeStash(TestHelper.firstStash(), TestHelper.firstStash + TestHelper.Extension);
		store.writeStash(TestHelper.secondStash(), TestHelper.secondStash + TestHelper.Extension);
		store.writeStash(TestHelper.thirdStash(), TestHelper.thirdStash + TestHelper.Extension);
		store.writeStash(TestHelper.dependantStashByUse(), TestHelper.dependantStashByUse + TestHelper.Extension);
		store.writeStash(TestHelper.independentStashInSubStash(), TestHelper.independentStash + TestHelper.Extension);
		store.writeStash(TestHelper.m3(), TestHelper.m3 + TestHelper.Extension);
		assertThat(new Graph(new InMemoryFileStore(temp)).loadStashes(TestHelper.oneMockStash).find(MockLayer.class).size(), is(7));
	}

	@Test
	public void should_load_all_stashes_with_cyclic_dependency() {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.dependantStashByUse);
		assertNotNull(graph.load(TestHelper.cyclicDependantStash + "#x").as(MockLayer.class).mockLayer());
	}

	@Test
	public void should_choose_correct_language_when_writing_stash() {
		Store store = TestHelper.mockStore();
		Graph graph = new Graph(store).loadStashes("m1");
		graph.load(TestHelper.m1 + "#x").save();
		assertThat(store.stashFrom(TestHelper.m1 + ".stash").language, is("m2"));
	}

	@Test
	public void should_not_log_an_error_if_language_was_previously_loaded() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		getGlobal().addHandler(handler);
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes("m2");
		graph.loadStashes("m1");
		handler.flush();
		assertThat(outputStream.toString(), not(containsString("m2")));
	}

	@Test
	public void should_not_log_an_error_create_root_uses_a_non_existing_stash() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		getGlobal().addHandler(handler);
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes("m2");
		graph.createRoot(MockLayer.class, "non_existing_stash");
		handler.flush();
		assertThat(outputStream.toString(), not(containsString("non_existing_stash.stash does not exist")));
	}

	@Test
	public void should_create_new_graph_and_wrappers_when_cloned() {
		MockApplication application = new Graph(TestHelper.mockStore()).loadStashes("m2").as(MockApplication.class);
		MockApplication clonedApplication = application.core$().clone().as(MockApplication.class);
		assertNotEquals(clonedApplication, application);
		assertNotEquals(clonedApplication.core$(), application.core$());
	}

	@Test
	public void should_not_crash_when_language_is_included_in_the_list_of_stashes_to_open() {
		MockApplication application = new Graph(TestHelper.mockStore()).loadStashes("m1", "m2", "m3").as(MockApplication.class);
		assertEquals(2, application.core$().languages.size());
		assertEquals(3, application.core$().openedStashes.size());
	}

	@Test
	public void node_should_have_complete_hierarchy_of_concepts_when_loading_root() {
		MockApplication application = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.highHierarchy).as(MockApplication.class);
		assertEquals(6, application.core$().rootList(SubMockLayer.class).get(0).core$().typeNames.size());
	}

	@Test
	public void node_should_have_complete_hierarchy_of_concepts_when_creating_root() {
		MockApplication application = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.highHierarchy).as(MockApplication.class);
		Node mock = application.core$().createRoot("Mock", TestHelper.highHierarchy);
		assertEquals(6, mock.typeNames.size());
	}

	@Test
	public void should_show_severe_log_when_creating_two_components_with_the_same_name_returning_null() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		getGlobal().addHandler(handler);
		MockApplication application = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.m1).as(MockApplication.class);
		application.core$().concept("Mock").createNode("a", application.mockLayerList().get(0).core$());
		application.core$().concept("Mock").createNode("a", application.mockLayerList().get(0).core$());
		handler.flush();
		assertThat(outputStream.toString(), containsString("component named a"));
	}

	@Test
	public void should_show_severe_log_when_loading_a_node_that_refers_to_a_non_existent_node() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		getGlobal().addHandler(handler);
		new Graph(TestHelper.mockStore()).loadStashes(TestHelper.missingReference).as(MockApplication.class);
		handler.flush();
		assertThat(outputStream.toString(), containsString("Dependant node is missingReference#x"));
	}

	@Test
	public void should_load_a_big_list_of_dependencies() {
		MockApplication application = new Graph(new TestHelper.MockStore() {
			@Override
			public Stash stashFrom(String stash) {
				Stash result = super.stashFrom(stash);
				if (result == null) result = TestHelper.referencedStash(stash);
				return result;
			}
		}).loadStashes(TestHelper.manyReferences).as(MockApplication.class);
		assertThat(application.mockLayerList().get(0).varMockList().size(), is(2000));
		assertThat(application.mockLayerList().get(0).varMockList().get(0).varMockList().size(), is(1));
		assertThat(application.mockLayerList().get(0).varMockList().get(0).varMockList().get(0).core$().id, is("stash1#x"));
	}

	@Test
	public void should_provide_correct_root_node_id() {
		Node child = new Node("xxx#yyy$zzz");
		assertThat(child.rootNodeId(), is("xxx#yyy"));
	}
}
