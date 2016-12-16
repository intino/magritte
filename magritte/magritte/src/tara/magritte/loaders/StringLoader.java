package tara.magritte.loaders;

import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class StringLoader {

    public static List<String> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(e -> (String) e).collect(toList());
    }

}
