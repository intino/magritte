package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.List;

import static io.intino.tara.magritte.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class LongLoader {

    public static List<Long> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(e -> (Long) e).collect(toList());
    }

}
