package tara.magritte.loaders;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class WordLoader {

    public static <T extends Enum<T>> List<T> load(List<?> list, Class<T> aClass) {
        return StringLoader.load(list).stream().map(w -> Enum.valueOf(aClass, w)).collect(toList());
    }

}
