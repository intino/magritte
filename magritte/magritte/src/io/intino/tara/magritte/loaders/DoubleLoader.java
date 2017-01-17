package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;

import static io.intino.tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class DoubleLoader {

    public static List<Double> load(List<?> list, Layer layer) {
        List<Double> result = new ArrayList<>();
        for (Object value : process(list, layer)) result.add((Double) value);
        return result;
    }

}
