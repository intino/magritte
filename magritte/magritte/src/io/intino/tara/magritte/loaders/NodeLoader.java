package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;

import static io.intino.tara.magritte.loaders.ListProcessor.process;
import static java.util.logging.Logger.getGlobal;

@SuppressWarnings("unused")
public class NodeLoader {

    public static <T extends Layer> List<T> load(List<?> list, Class<T> aClass, Layer layer) {
        List<T> result = new ArrayList<>();
        for (Object value : list) {
            Node node = loadNode((String) value, layer);
            result.add(node != null ? node.as(aClass) : null);
            if(node == null)
                getGlobal().warning("A reference to the node " + value + " has not been found. Dependant node is " + layer.core$().id());
        }
        return result;
    }

    private static Node loadNode(String item, Layer layer) {
        if(item.startsWith("$@")) {
            Object object = process(item.substring(2), layer);
            return object != null ? ((Layer)object).core$() : null;
        }
        return layer.core$().graph().load(item, false);
    }

}
