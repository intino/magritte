package tara.magritte;

import tara.magritte.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ModelCloner {

    public static void doClone(Model model, Model clone) {
        clone.loaders = new ArrayList<>(model.loaders);
        clone.languages = new HashSet<>(model.languages);
        clone.engine = model.engine;
        clone.domain = model.domain;
        clone.concepts = new HashMap<>(model.concepts);
        clone.instances = new HashMap<>(model.instances);
        clone.instanceIndex = model.instanceIndex;
        model.soil.components().forEach(clone.soil::add);
    }

}
