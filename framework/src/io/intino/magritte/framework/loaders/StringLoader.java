package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class StringLoader {

	public static List<String> load(List<?> list, Layer layer) {
		return ListProcessor.process(list, layer).stream().map(e -> (String) e).collect(toList());
	}

}
