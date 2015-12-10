package tara.magritte.loaders;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class DoubleLoader {

    public static List<Double> load(List<?> list) {
        return list.stream().map(e -> (Double) e).collect(toList());
    }

}
