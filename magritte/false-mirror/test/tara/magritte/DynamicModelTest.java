package tara.magritte;

import org.junit.Ignore;
import org.junit.Test;
import tara.io.Stash;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.modelwrappers.DynamicMockDomain;
import tara.magritte.modelwrappers.DynamicMockEngine;
import tara.magritte.stores.AdvancedFileSystemStore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tara.io.Helper.*;

public class DynamicModelTest {

	private static final String stash = "DynamicEmpty";

	@Test
	public void stashes_should_be_opened_on_demand() throws Exception {
		Store store = createStore();
		DynamicModel model = DynamicModel.load(stash, store).init(DynamicMockDomain.class, DynamicMockEngine.class);
		DynamicMockLayer instance = model.newMain("Mock", stash).as(DynamicMockLayer.class);
		DynamicMockLayer out = model.newMain("Mock", "Out").as(DynamicMockLayer.class);
		instance.mockLayer(out);
		instance.save();

		Model reloaded = DynamicModel.load(stash, store);
		assertThat(reloaded.openedStashes.size(), is(1));
		reloaded.components().get(0).as(DynamicMockLayer.class).mockLayer();
		assertThat(reloaded.openedStashes.size(), is(2));
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void instances_should_be_saved_with_high_memory() throws Exception {
		DynamicModel model = DynamicModel.load(stash, mockStore()).init(DynamicMockDomain.class, DynamicMockEngine.class);
		long count = 0;
		long amount = 0;
		while(true) {
			model.newMain("Mock", "Out" + count++).as(DynamicMockLayer.class);
			if(model.references.size() > amount) amount = model.references.size();
			if(model.references.size() < amount) break;
		}
		assertThat(model.engine(DynamicMockEngine.class).mockLayerList().size(), is((int)Math.round(amount * 0.8) + 1));
	}

	@Ignore("This test can take long, ignored to be only executed on demand: -Xmx5m") @Test
	public void creating_one_hundred_thousand_instances_should_be_possible_in_5_MB() throws Exception {
		DynamicModel model = DynamicModel.load(stash, mockStore()).init(DynamicMockDomain.class, DynamicMockEngine.class);
		long block = 0;
		long counter = 0;
		while(block < 100) {
			model.newMain("Mock", block + File.separator + counter++).as(DynamicMockLayer.class);
			if(counter == 1000){
				counter = 0;
				block++;
			}
		}
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