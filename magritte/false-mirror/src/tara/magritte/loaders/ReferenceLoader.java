package tara.magritte.loaders;

import tara.magritte.DynamicModel;
import tara.magritte.Layer;
import tara.magritte.Reference;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class ReferenceLoader {

    public static List<Reference> load(List<?> list, Layer layer) {
        return list.stream().map(r -> process((String) r, layer)).collect(toList());
    }

    private static Reference process(String reference, Layer layer) {
        Object referenceObject = ListProcessor.process(reference, layer);
        return referenceObject instanceof Layer ? new Reference(((Layer) referenceObject).instance()) : new Reference(reference, (DynamicModel) layer.model());
    }

}
