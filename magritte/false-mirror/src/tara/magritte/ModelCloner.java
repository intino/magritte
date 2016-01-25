package tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ModelCloner {

    public static Model doClone(Model model, Model clone) {
        clone.loaders = new ArrayList<>(model.loaders);
        clone.languages = new HashSet<>(model.languages);
        clone.concepts = new HashMap<>(model.concepts);
		clone.instances = new HashMap<>(model.instances);
		clone.layerFactory = new LayerFactory(model.layerFactory);
		clone.openedStashes = new HashSet<>(model.openedStashes);
		model.soil.components().forEach(clone.soil::add);
		clone.init(model.domain.getClass(), model.engine.getClass());
		return clone;
    }

}
