package tara.magritte.loaders;

import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class DoubleLoader {

    public static List<Double> load(List<?> list, Layer layer) {
        return process(list, layer).stream().map(e -> (Double) e).collect(toList());
    }

}
