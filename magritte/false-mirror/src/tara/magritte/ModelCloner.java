package tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ModelCloner {

	public static Model doClone(Model model, Model clone) {
		clone.loaders = new ArrayList<>(model.loaders);
		clone.languages = new LinkedHashSet<>(model.languages);
		clone.concepts = new HashMap<>(model.concepts);
		clone.modes = new HashMap<>(model.modes);
		clone.layerFactory = new LayerFactory(model.layerFactory);
		clone.openedStashes = new HashSet<>(model.openedStashes);
		model.graph.componentList().forEach(clone.graph::add);
		if (model.platform != null) clone.modelLoad().wrap(model.application.getClass(), model.platform.getClass());
		else clone.modelLoad().wrap(model.application.getClass());
		return clone;
	}

}
