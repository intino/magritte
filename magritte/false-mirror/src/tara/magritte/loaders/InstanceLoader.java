package tara.magritte.loaders;

import tara.magritte.Instance;
import tara.magritte.Layer;
import tara.magritte.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class InstanceLoader {

    public static <T extends Layer> List<T> load(List<?> list, Model model, Class<T> aClass, Layer layer) {
        return list.stream().map(item -> loadInstance((String) item, model, layer))
                .map(i -> i != null ? i.as(aClass) : null).collect(toList());
    }

    private static Instance loadInstance(String item, Model model, Layer layer) {
        Object layerObject = process(item, layer);
        return layerObject instanceof Layer ? ((Layer) layerObject)._instance() : model.loadInstance(item);
    }

}
