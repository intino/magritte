package tara.magritte.loaders;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class StringLoader {

    public static List<String> load(List<Object> list) {
        return list.stream().map(e -> (String) e).collect(toList());
    }

}
