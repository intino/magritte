package tara.magritte.loaders;

import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ObjectLoader {

	public static <T> List<T> load(List<?> list, Class<T> tClass, Layer layer) {
		return process(list, layer).stream().map(e -> (T) e).collect(toList());
	}

}
