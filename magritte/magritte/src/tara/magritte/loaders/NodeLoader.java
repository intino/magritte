package tara.magritte.loaders;

import tara.magritte.Layer;
import tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;

import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class NodeLoader {

    public static <T extends Layer> List<T> load(List<?> list, Class<T> aClass, Layer layer) {
        List<T> result = new ArrayList<>();
        for (Object value : list) {
            Node node = loadNode((String) value, layer);
            result.add(node != null ? node.as(aClass) : null);
        }
        return result;
    }

    private static Node loadNode(String item, Layer layer) {
        if(item.startsWith("$@")) {
            Object object = process(item, layer);
            return object != null ? ((Layer)object).node() : null;
        }
        return layer.graph().loadNode(item);
    }

}
