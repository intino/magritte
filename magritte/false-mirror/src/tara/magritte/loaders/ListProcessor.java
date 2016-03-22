package tara.magritte.loaders;

import tara.magritte.Expression;
import tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.FunctionLoader.link;

class ListProcessor {

	static List<?> process(List<?> toProcess, Layer layer){
		return toProcess.stream().map(item -> process(item, layer)).collect(toList());
	}

	static Object process(Object item, Layer layer) {
		return item instanceof String ? process((String) item, layer) : item;
	}

	private static Object process(String item, Layer layer) {
		try {
			return link(NativeCodeLoader.nativeCodeOf(Class.forName(item)), layer, Expression.class).value();
		} catch (ClassNotFoundException e) {
			return item;
		}
	}

}
