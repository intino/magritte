package tara.magritte;

import org.junit.Before;
import org.junit.Test;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.modelwrappers.DynamicMockDomain;
import tara.magritte.modelwrappers.DynamicMockEngine;
import tara.magritte.stores.AdvancedFileSystemStore;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tara.io.Helper.*;

public class DynamicModelTest {

	private DynamicModel model;
	private AdvancedFileSystemStore store;

	@Before
	public void setUp() throws Exception {
		File tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
		Files.write(Paths.get("test_res/Empty.stash"), StashSerializer.serialize(emptyStash()));
		store = new AdvancedFileSystemStore(tempDirectory);
		model = DynamicModel.load("Empty", store).init(DynamicMockDomain.class, DynamicMockEngine.class);
	}

	@Test
	public void stashes_should_be_opened_on_demand() throws Exception {
		DynamicMockLayer instance = model.newMain("Mock", "Empty").as(DynamicMockLayer.class);
		DynamicMockLayer out = model.newMain("Mock", "Out").as(DynamicMockLayer.class);
		instance.mockLayer(out);
		instance.save();

		Model reloaded = DynamicModel.load("Empty", store);
		assertThat(reloaded.openedStashes.size(), is(1));
		reloaded.components().get(0).as(DynamicMockLayer.class).mockLayer();
		assertThat(reloaded.openedStashes.size(), is(2));
	}

	@Test
	public void instances_should_be_saved_with_high_memory() throws Exception {
		long count = 0;
		long amount = 0;
		while(true) {
			model.newMain("Mock", "Out" + count++).as(DynamicMockLayer.class);
			if(model.references.size() > amount) amount = model.references.size();
			if(model.references.size() < amount) break;
		}
		assertThat(model.engine(DynamicMockEngine.class).mockLayerList().size(), is((int)Math.round(amount * 0.8) + 1));
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.DynamicMockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}