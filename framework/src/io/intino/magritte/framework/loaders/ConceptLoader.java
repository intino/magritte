package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Concept;
import io.intino.magritte.framework.Layer;

import java.util.List;

import static io.intino.magritte.framework.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

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
