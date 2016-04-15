package tara.magritte.loaders;

import tara.magritte.Layer;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ResourceLoader {

    public static List<URL> load(List<?> list, Layer layer) {
        return list.stream().map((path) -> loadResource((String) path, layer)).collect(toList());
    }

    private static URL loadResource(String path, Layer layer) {
        Object resourceObject = process(path, layer);
        return resourceObject instanceof URL ? (URL) resourceObject : layer.graph().loadResource(path);
    }

}
