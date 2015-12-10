package tara.magritte.loaders;

import tara.magritte.Concept;
import tara.magritte.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class ConceptLoader {

    public static List<Concept> load(List<?> list, Model model) {
        return StringLoader.load(list).stream().map(model::conceptOf).collect(toList());
    }

}
