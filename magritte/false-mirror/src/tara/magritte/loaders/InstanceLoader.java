package tara.magritte.loaders;

import tara.magritte.Node;
import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class InstanceLoader {

    public static <T extends Layer> List<T> load(List<?> list, Class<T> aClass, Layer layer) {
        return list.stream().map(item -> loadInstance((String) item, layer))
                .map(i -> i != null ? i.as(aClass) : null).collect(toList());
    }

    private static Node loadInstance(String item, Layer layer) {
        Object layerObject = process(item, layer);
        return layerObject instanceof Layer ? ((Layer) layerObject).node() : layer.model().loadNode(item);
    }

}
