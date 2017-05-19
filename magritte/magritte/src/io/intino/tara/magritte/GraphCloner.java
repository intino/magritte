package io.intino.tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static io.intino.tara.magritte.GraphHelper.create;

class GraphCloner {

    static Graph doClone(Graph graph, Graph clone) {
        clone.loaders = new ArrayList<>(graph.loaders);
        clone.languages = new LinkedHashSet<>(graph.languages);
        clone.concepts = new HashMap<>(graph.concepts);
        clone.nodes = new HashMap<>(graph.nodes);
        clone.layerFactory = new LayerFactory(graph.layerFactory);
        clone.openedStashes = new HashSet<>(graph.openedStashes);
        graph.model.componentList().forEach(clone.model::add);
        graph.views.values().forEach(w -> clone.views.put(w.getClass(), create(w.getClass(), clone)));
        return clone;
    }

}
