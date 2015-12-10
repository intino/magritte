package tara.magritte.loaders;

import tara.magritte.Layer;
import tara.magritte.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class InstanceLoader {

    public static <T extends Layer> List<T> load(List<?> list, Model model, Class<T> aClass) {
        return StringLoader.load(list).stream().map(model::loadInstance)
                .filter(i -> i != null)
                .map(i -> i.as(aClass)).collect(toList());
    }

}
