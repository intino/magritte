package tara.magritte.loaders;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class IntegerLoader {

    public static List<Integer> load(List<?> list) {
        return list.stream().map(e -> (Integer) e).collect(toList());
    }

}
