package tara.magritte;

import org.junit.Ignore;
import org.junit.Test;
import tara.io.Stash;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.modelwrappers.DynamicMockApplication;
import tara.magritte.modelwrappers.DynamicMockPlatform;
import tara.magritte.stores.AdvancedFileSystemStore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static tara.io.Helper.*;

public class DynamicModelTest {

	private static final String stash = "DynamicEmpty";

	@Test
	public void stashes_should_be_opened_on_demand() throws Exception {
		DynamicModel model = DynamicModel.load(stash, createStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer instance = model.newMain(DynamicMockLayer.class, stash);
		DynamicMockLayer out = model.newMain(DynamicMockLayer.class, "Out");
		instance.mockLayer(out);
		instance.save();

		Model reloaded = DynamicModel.load(stash, model.store);
		assertThat(reloaded.openedStashes.size(), is(1));
		reloaded.components().get(0).as(DynamicMockLayer.class).mockLayer();
		assertThat(reloaded.openedStashes.size(), is(2));
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void instances_should_be_saved_with_high_memory() throws Exception {
		DynamicModel model = DynamicModel.load(stash, mockStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		long count = 0;
		long amount = 0;
		while(true) {
			model.newMain(DynamicMockLayer.class, "Out" + count++);
			if(model.references.size() > amount) amount = model.references.size();
			if(model.references.size() < amount) break;
		}
		assertThat(model.<DynamicMockPlatform>platform().mockLayerList().size(), is((int) Math.round(amount * 0.8) + 1));
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void creating_one_hundred_thousand_instances_should_be_possible_in_5_MB() throws Exception {
		DynamicModel model = DynamicModel.load(stash, mockStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		long block = 0;
		long counter = 0;
		while(block < 100) {
			model.newMain(DynamicMockLayer.class, block + File.separator + counter++);
			if(counter == 1000){
				counter = 0;
				block++;
			}
		}
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void explicitly_opened_stashes_should_not_be_free() throws Exception {
		DynamicModel model = DynamicModel.load(stash, createStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer main = model.newMain(DynamicMockLayer.class, stash);

		long amount = 0;
		while(true) {
			model.newMain(DynamicMockLayer.class, "Out" + amount++);
			if(model.references.size() > amount) amount = model.references.size();
			if(model.references.size() < amount) break;
		}

		assertThat(model.<DynamicMockPlatform>platform().mockLayerList(m -> m._name().equals(main._name())).size(), is(1));
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void referred_instance_should_be_free_when_necessary_and_recovered_on_demand() throws Exception {
		DynamicModel model = DynamicModel.load(stash, createStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer main = model.newMain(DynamicMockLayer.class, stash);
		DynamicMockLayer referred = model.newMain(DynamicMockLayer.class, "Referred");
		main.mockLayer(referred);
		referred.mockLayer(main);

		long amount = 0;
		while(true) {
			model.newMain(DynamicMockLayer.class, "Out" + amount++);
			if(model.references.size() > amount) amount = model.references.size();
			if(model.references.size() < amount) break;
		}

		assertNull(main.mockLayer.instance);
		main.mockLayer();
		assertNotNull(main.mockLayer.instance);
		assertNull(main.mockLayer().mockLayer.instance);
		main.mockLayer().mockLayer();
		assertNotNull(main.mockLayer().mockLayer.instance);
		assertEquals(referred.mockLayer(), main);
		assertEquals(main.mockLayer().mockLayer(), main);
	}

	@Test
	public void modifications_in_references_list_should_be_applied_back_to_real_list() throws IOException {
		DynamicModel model = DynamicModel.load(stash, mockStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer main = model.newMain(DynamicMockLayer.class, stash);
		DynamicMockLayer referred1 = model.newMain(DynamicMockLayer.class, "Referred");
		DynamicMockLayer referred2 = model.newMain(DynamicMockLayer.class, "Referred2");

		main.mockLayers().add(referred1);
		assertThat(main.mockLayers().size(), is(1));

		main.mockLayers().add(referred1);
		assertThat(main.mockLayers().size(), is(2));

		main.mockLayers().add(referred2);
		assertThat(main.mockLayers().size(), is(3));

		main.mockLayers().remove(referred1);
		assertThat(main.mockLayers().size(), is(2));

		main.mockLayers().clear();
		assertThat(main.mockLayers().size(), is(0));
	}

	private Store mockStore() {
		return new Store() {

			@Override
			public Stash stashFrom(String path) {
				return path.contains("DynamicEmpty") ? emptyStash() : null;
			}

			@Override
			public void writeStash(Stash stash, String path) {
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

	private Store createStore() throws IOException {
		File tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
		Store store = new AdvancedFileSystemStore(tempDirectory);
		store.writeStash(emptyStash(), stash + ".stash");
		return store;
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.DynamicMockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}