package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.tara.magritte.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class DoubleLoader {

    public static List<Double> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(d -> (Double) d).collect(toList());
    }

}
