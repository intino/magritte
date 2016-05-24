package tara.magritte.loaders;

import tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class DoubleLoader {

    public static List<Double> load(List<?> list, Layer layer) {
        List<Double> result = new ArrayList<>();
        for (Object value : process(list, layer)) result.add((Double) value);
        return result;
    }

}
