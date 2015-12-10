package tara.magritte.loaders;

import tara.magritte.Model;
import tara.magritte.Reference;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class ReferenceLoader {

    public static List<Reference> load(List<?> list, Model model) {
        return StringLoader.load(list).stream().map(r -> new Reference(r, model)).collect(toList());
    }

}
