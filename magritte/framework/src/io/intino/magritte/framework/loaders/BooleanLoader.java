package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.util.List;

import static io.intino.magritte.framework.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class BooleanLoader {

	public static List<Boolean> load(List<?> list, Layer layer) {
		return process(list, layer).stream().map(e -> (Boolean) e).collect(toList());
	}

}
