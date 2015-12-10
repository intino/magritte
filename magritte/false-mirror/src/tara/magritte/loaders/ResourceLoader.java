package tara.magritte.loaders;

import tara.magritte.Model;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class ResourceLoader {

    public static List<URL> load(List<?> list, Model model) {
        return StringLoader.load(list).stream().map(model::loadResource).collect(toList());
    }

}
