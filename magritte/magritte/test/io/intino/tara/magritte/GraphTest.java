package io.intino.tara.magritte;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.modelwrappers.MockApplication;
import io.intino.tara.magritte.modelwrappers.MockPlatform;
import io.intino.tara.magritte.stores.FileSystemStore;
import io.intino.tara.magritte.stores.InMemoryFileStore;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static io.intino.tara.magritte.Graph.load;
import static io.intino.tara.magritte.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void new_main_should_be_saved() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        graph.createRoot(MockLayer.class, emptyStash).save();
        assertThat(graph.rootList().size(), is(1));
        assertThat(graph.rootList().get(0).layers.size(), is(1));
        assertTrue(graph.rootList().get(0).is(MockLayer.class));
        assertTrue(graph.rootList().get(0).is("Mock"));
        Graph reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().size(), is(1));
        assertThat(reloaded.rootList().get(0).layers.size(), is(1));
        assertTrue(reloaded.rootList().get(0).is(MockLayer.class));
        assertTrue(reloaded.rootList().get(0).is("Mock"));
    }

    @Test
    public void new_main_should_be_removed() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        assertThat(graph.rootList().size(), is(1));
        mockLayer.delete();
        assertThat(graph.rootList().size(), is(0));
        Graph reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().size(), is(0));
    }

    @Test
    public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
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

        Graph reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().size(), is(1));
        assertNull(reloaded.rootList().get(0).as(MockLayer.class).mockLayer());
        handler.flush();
        assertThat(outputStream.toString(), containsString("has not been found"));
    }

    @Test
    public void node_should_be_removed_from_parent_when_node_is_removed() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        MockLayer child = mockLayer.newMock();
        assertThat(mockLayer.componentList().size(), is(1));
        child.delete();
        assertThat(mockLayer.componentList().size(), is(0));
    }

    @Test
    public void node_should_be_saved_with_its_parent_and_removed() {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        MockLayer child = mockLayer.newMock();
        child.save();

        Graph reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().size(), is(1));
        assertThat(reloaded.rootList().get(0).componentList().size(), is(1));
        reloaded.rootList().get(0).componentList().get(0).delete();
        assertThat(reloaded.rootList().get(0).componentList().size(), is(0));

        reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().get(0).componentList().size(), is(0));
        reloaded.rootList().get(0).delete();
        assertThat(reloaded.rootList().size(), is(0));

        reloaded = load(emptyStash, graph.store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(reloaded.rootList().size(), is(0));
    }

    @Test
    public void should_clear_all_model_platform_and_application() {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
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
        Graph graph = load(oneMockStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
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
        Graph graph = load(oneMockStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
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
        Graph graph = load(oneMockStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        List<Node> components = new ArrayList<>(graph.model.componentList());
        Set<String> openedStashes = new HashSet<>(graph.openedStashes);
        Set<String> languages = new HashSet<>(graph.languages);
        Map<String, Concept> concepts = new HashMap<>(graph.concepts);
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

    @Test
    public void stash_must_be_loaded_automatically_when_node_is_created_for_a_given_namespace() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        graph.createRoot(MockLayer.class, oneMockStash, "z");
        assertThat(graph.find(MockLayer.class).size(), is(3));
    }

    @Test
    public void node_cannot_be_created_if_already_exists() throws Exception {
        Graph graph = load(emptyStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        MockLayer y = graph.createRoot(MockLayer.class, oneMockStash, "y");
        assertNull(y);
        assertThat(graph.find(MockLayer.class).size(), is(2));
    }

    @Test
    public void should_remove_whole_namespace() throws Exception {
        Store store = TestHelper.mockStore();
        Graph graph = load(oneMockStash, store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(graph.find(MockLayer.class).size(), is(2));
        graph.remove(oneMockStash);
        assertThat(graph.find(MockLayer.class).size(), is(0));
        assertThat(load(oneMockStash, store).wrap(MockApplication.class, MockPlatform.class).find(MockLayer.class).size(), is(0));
    }

    @Test
    public void should_load_dependant_stashes() throws Exception {
        Graph graph = load(firstStash, TestHelper.mockStore()).wrap(MockApplication.class, MockPlatform.class);
        List<MockLayer> mockLayers = graph.find(MockLayer.class);
        assertThat(mockLayers.size(), is(3));
        assertThat(mockLayers.get(0).mockLayer(), is(mockLayers.get(1)));
        assertThat(mockLayers.get(1).mockLayer(), is(mockLayers.get(2)));
        assertThat(mockLayers.get(2).mockLayer(), is(mockLayers.get(0)));
    }

    @Test
    public void should_load_dependant_stashes_by_uses() throws Exception {
        Store store = TestHelper.mockStore();
        Graph graph = load(dependantStashByUse, store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(graph.find(MockLayer.class).size(), is(3));
    }

    @Test
    public void should_have_just_one_concept() throws Exception {
        Store store = TestHelper.mockStore();
        Graph graph = load(dependantStashByUse, store).wrap(MockApplication.class, MockPlatform.class);
        assertThat(graph.conceptList().size(), is(1));
    }

    @Test
    public void should_load_all_stashes_when_using_in_memory_file_store() throws Exception {
        File temp = new File("temp");
        FileUtils.deleteDirectory(temp);
        FileSystemStore store = new FileSystemStore(temp);
        store.writeStash(oneMockStash(), oneMockStash + Extension);
        store.writeStash(firstStash(), firstStash + Extension);
        store.writeStash(secondStash(), secondStash + Extension);
        store.writeStash(thirdStash(), thirdStash + Extension);
        store.writeStash(dependantStashByUse(), dependantStashByUse + Extension);
        store.writeStash(independentStashInSubNamespace(), independentStash + Extension);
        Graph graph = load(oneMockStash, new InMemoryFileStore(temp)).wrap(MockApplication.class, MockPlatform.class);
        assertThat(graph.find(MockLayer.class).size(), is(7));
    }

}
