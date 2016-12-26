package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class StringLoader {

    public static List<String> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(e -> (String) e).collect(toList());
    }

}
