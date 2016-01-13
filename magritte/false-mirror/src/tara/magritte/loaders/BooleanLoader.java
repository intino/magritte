package tara.magritte.loaders;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class BooleanLoader {

    public static List<Boolean> load(List<?> list) {
        return list.stream().map(e -> Boolean.valueOf((String) e)).collect(toList());
    }

}
