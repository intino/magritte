package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Concept;
import io.intino.tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ConceptLoader {

	public static List<Concept> load(List<?> list, Layer layer) {
		return list.stream().map(item -> conceptOf((String) item, layer)).collect(toList());
	}

	private static Concept conceptOf(String item, Layer layer) {
		Object conceptObject = process((Object) item, layer);
		return conceptObject instanceof Concept ? (Concept) conceptObject : layer.core$().graph().concept(item);
	}

}
