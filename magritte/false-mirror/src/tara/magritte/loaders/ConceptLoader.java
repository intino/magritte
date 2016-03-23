package tara.magritte.loaders;

import tara.magritte.Concept;
import tara.magritte.Layer;
import tara.magritte.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.*;

@SuppressWarnings("unused")
public class ConceptLoader {

    public static List<Concept> load(List<?> list, Layer layer) {
        return list.stream().map(item -> conceptOf((String) item, layer)).collect(toList());
    }

    private static Concept conceptOf(String item, Layer layer) {
        Object conceptObject = process(item, layer);
        return conceptObject instanceof Concept ? (Concept) conceptObject : layer.model().conceptOf(item);
    }

}
