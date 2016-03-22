package tara.magritte.loaders;

import tara.magritte.Layer;
import tara.magritte.Model;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ResourceLoader {

    public static List<URL> load(List<?> list, Model model, Layer layer) {
        return list.stream().map((path) -> loadResource((String) path, model, layer)).collect(toList());
    }

    private static URL loadResource(String path, Model model, Layer layer) {
        Object resourceObject = process(path, layer);
        return resourceObject instanceof URL ? (URL) resourceObject : model.loadResource(path);
    }

}
