package tara.magritte.loaders;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Model;
import tara.magritte.layers.MockLayer;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static tara.io.Helper.list;
import static tara.io.Helper.newConcept;
import static tara.io.Helper.newStash;

public class ConceptLoaderTest {

	@Test
	public void load() throws Exception {
		Model model = Model.load().loadStashes(emptyStash());
		MockLayer mockLayer = model.newMain(MockLayer.class);
		ConceptLoader.load(asList("Mock", "tara.magritte.natives.CodedConcept"), model, mockLayer);
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}
}