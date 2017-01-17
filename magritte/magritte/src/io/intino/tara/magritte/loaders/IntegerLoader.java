package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class IntegerLoader {

    public static List<Integer> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(e -> (Integer) e).collect(toList());
    }

}
