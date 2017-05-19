package io.intino.tara.magritte;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.modelviews.MockApplication;
import io.intino.tara.magritte.modelviews.MockPlatform;
import io.intino.tara.magritte.stores.FileSystemStore;
import io.intino.tara.magritte.stores.InMemoryFileStore;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static io.intino.tara.magritte.TestHelper.*;
import static java.util.logging.Logger.getGlobal;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void new_main_should_be_saved() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        graph.createRoot(MockLayer.class, emptyStash).save();
        assertThat(graph.rootList().size(), is(1));
        assertThat(graph.rootList().get(0).layers.size(), is(1));
        assertTrue(graph.rootList().get(0).is(MockLayer.class));
        assertTrue(graph.rootList().get(0).is("Mock"));
        Graph reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().size(), is(1));
        assertThat(reloaded.rootList().get(0).layers.size(), is(1));
        assertTrue(reloaded.rootList().get(0).is(MockLayer.class));
        assertTrue(reloaded.rootList().get(0).is("Mock"));
    }

    @Test
    public void new_main_should_be_removed() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        assertThat(graph.rootList().size(), is(1));
        mockLayer.delete();
        assertThat(graph.rootList().size(), is(0));
        Graph reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().size(), is(0));
    }

    @Test
    public void should_store_with_canonical_name() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        graph.createRoot(MockLayer.class, "tara\\magritte").save();
        assertThat(graph.rootList().get(0).path(), is("tara/magritte"));
        Graph reloaded = new Graph(graph.store).loadPaths("tara/magritte");
        assertThat(reloaded.rootList().size(), is(1));
        assertThat(reloaded.rootList().get(0).path(), is("tara/magritte"));
    }

    @Test
    public void a_reference_to_a_removed_element_should_be_warned_without_exiting_application_after_reboot() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        MockLayer toBeRemoved = graph.createRoot(MockLayer.class, emptyStash);
        mockLayer.mockLayer(toBeRemoved);
        mockLayer.save();
        assertThat(graph.rootList().size(), is(2));
        toBeRemoved.delete();
        assertThat(graph.rootList().size(), is(1));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
        getGlobal().addHandler(handler);

        Graph reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().size(), is(1));
        assertNull(reloaded.rootList().get(0).as(MockLayer.class).mockLayer());
        handler.flush();
        assertThat(outputStream.toString(), containsString("has not been found"));
    }

    @Test
    public void node_should_be_removed_from_parent_when_node_is_removed() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        MockLayer child = mockLayer.newMock();
        assertThat(mockLayer.componentList().size(), is(1));
        child.delete();
        assertThat(mockLayer.componentList().size(), is(0));
    }

    @Test
    public void node_should_be_saved_with_its_parent_and_removed() {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash);
        MockLayer child = mockLayer.newMock();
        child.save();

        Graph reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().size(), is(1));
        assertThat(reloaded.rootList().get(0).componentList().size(), is(1));
        reloaded.rootList().get(0).componentList().get(0).delete();
        assertThat(reloaded.rootList().get(0).componentList().size(), is(0));

        reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().get(0).componentList().size(), is(0));
        reloaded.rootList().get(0).delete();
        assertThat(reloaded.rootList().size(), is(0));

        reloaded = new Graph(graph.store).loadPaths(emptyStash);
        assertThat(reloaded.rootList().size(), is(0));
    }

    @Test
    public void should_clear_all_model_platform_and_application() {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        assertThat(graph.rootList().size(), is(0));
        graph.createRoot(MockLayer.class, emptyStash);
        assertThat(graph.rootList().size(), is(1));
        assertThat(graph.view(MockApplication.class).mockLayerList().size(), is(1));
        assertThat(graph.view(MockPlatform.class).mockLayerList().size(), is(1));
        graph.clear();
        assertThat(graph.rootList().size(), is(0));
        assertThat(graph.view(MockApplication.class).mockLayerList().size(), is(0));
        assertThat(graph.view(MockPlatform.class).mockLayerList().size(), is(0));
    }

    @Test
    public void should_reload_all_model_platform_and_application_when_there_is_one_element() {
        Graph graph = new Graph(mockStore()).loadPaths(oneMockStash);
        assertThat(graph.rootList().size(), is(2));
        assertThat(graph.view(MockApplication.class).mockLayerList().size(), is(2));
        assertThat(graph.view(MockPlatform.class).mockLayerList().size(), is(2));
        graph.reload();
        assertThat(graph.rootList().size(), is(2));
        assertThat(graph.view(MockApplication.class).mockLayerList().size(), is(2));
        assertThat(graph.view(MockPlatform.class).mockLayerList().size(), is(2));
    }

    @Test
    public void should_reload_nodes_as_they_are_in_the_stash() {
        Graph graph = new Graph(mockStore()).loadPaths(oneMockStash);
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
        Graph graph = new Graph(mockStore()).loadPaths(oneMockStash);
        List<Node> components = new ArrayList<>(graph.model.componentList());
        Set<String> openedStashes = new HashSet<>(graph.openedStashes);
        Set<String> languages = new HashSet<>(graph.languages);
        Map<String, Concept> concepts = new HashMap<>(graph.concepts);
        Map<String, Node> nodes = new HashMap<>(graph.nodes);
        List<NodeLoader> loaders = new ArrayList<>(graph.loaders);
        List<MockLayer> mockLayersInPlatform = new ArrayList<>(graph.view(MockPlatform.class).mockLayerList());
        List<MockLayer> mockLayersInApplication = new ArrayList<>(graph.view(MockApplication.class).mockLayerList());
        graph.reload();
        assertThat(components.size(), is(graph.model.componentList().size()));
        assertThat(openedStashes.size(), is(graph.openedStashes.size()));
        assertThat(languages.size(), is(graph.languages.size()));
        assertThat(concepts.size(), is(graph.concepts.size()));
        assertThat(nodes.size(), is(graph.nodes.size()));
        assertThat(loaders.size(), is(graph.loaders.size()));
        assertThat(mockLayersInPlatform.size(), is(graph.view(MockPlatform.class).mockLayerList().size()));
        assertThat(mockLayersInApplication.size(), is(graph.view(MockApplication.class).mockLayerList().size()));
    }

    @Test
    public void stash_must_be_loaded_automatically_when_node_is_created_for_a_given_path() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        graph.createRoot(MockLayer.class, oneMockStash, "z");
        assertThat(graph.find(MockLayer.class).size(), is(3));
    }

    @Test
    public void node_cannot_be_created_if_already_exists() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        MockLayer y = graph.createRoot(MockLayer.class, oneMockStash, "y");
        assertNull(y);
        assertThat(graph.find(MockLayer.class).size(), is(2));
    }

    @Test
    public void should_remove_whole_path() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(oneMockStash);
        assertThat(graph.find(MockLayer.class).size(), is(2));
        graph.remove(oneMockStash);
        assertThat(graph.find(MockLayer.class).size(), is(0));
        assertThat(new Graph(graph.store).loadPaths(oneMockStash).find(MockLayer.class).size(), is(0));
    }

    @Test
    public void should_load_dependant_stashes() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(firstStash);
        List<MockLayer> mockLayers = graph.find(MockLayer.class);
        assertThat(mockLayers.size(), is(3));
        assertThat(mockLayers.get(0).mockLayer(), is(mockLayers.get(1)));
        assertThat(mockLayers.get(1).mockLayer(), is(mockLayers.get(2)));
        assertThat(mockLayers.get(2).mockLayer(), is(mockLayers.get(0)));
    }

    @Test
    public void should_load_dependant_stashes_by_uses() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(dependantStashByUse);
        assertThat(graph.find(MockLayer.class).size(), is(4));
    }

    @Test
    public void should_have_just_one_concept() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(dependantStashByUse);
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
        store.writeStash(independentStashInSubPath(), independentStash + Extension);
        store.writeStash(m3(), m3 + Extension);
        assertThat(new Graph(new InMemoryFileStore(temp)).loadPaths(oneMockStash).find(MockLayer.class).size(), is(7));
    }

    @Test
    public void should_load_all_stashes_with_cyclic_dependency() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(dependantStashByUse);
        assertNotNull(graph.load(cyclicDependantStash + "#x").as(MockLayer.class).mockLayer());
    }

    @Test
    public void should_choose_correct_language_when_writing_stash() throws Exception {
        Store store = mockStore();
        Graph graph = new Graph(store).loadPaths("m1");
        graph.load(m1 + "#x").save();
        assertThat(store.stashFrom(m1 + ".stash").language, is("m2"));
    }

    @Test
    public void should_not_log_an_error_if_language_was_previously_loaded() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
        getGlobal().addHandler(handler);
        Graph graph = new Graph(mockStore()).loadPaths("m2");
        graph.loadPaths("m1");
        handler.flush();
        assertThat(outputStream.toString(), not(containsString("m2")));
    }

    @Test
    public void should_not_log_an_error_create_root_uses_a_non_existing_stash() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
        getGlobal().addHandler(handler);
        Graph graph = new Graph(mockStore()).loadPaths("m2");
        graph.createRoot(MockLayer.class, "non_existing_stash");
        handler.flush();
        assertThat(outputStream.toString(), not(containsString("non_existing_stash.stash does not exist")));
    }
}
