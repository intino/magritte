package tara.magritte.loaders;

import tara.magritte.Concept;
import tara.magritte.Layer;
import tara.magritte.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ConceptLoader {

    public static List<Concept> load(List<?> list, Model model, Layer layer) {
        return process(list, layer).stream().map(item -> model.conceptOf((String) item)).collect(toList());
    }

}
