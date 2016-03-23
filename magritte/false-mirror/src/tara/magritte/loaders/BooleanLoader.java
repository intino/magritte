package tara.magritte.loaders;

import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class BooleanLoader {

	public static List<Boolean> load(List<?> list, Layer layer) {
		return process(list, layer).stream().map(e -> Boolean.valueOf(e.toString())).collect(toList());
	}

}
