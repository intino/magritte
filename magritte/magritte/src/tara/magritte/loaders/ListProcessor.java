package tara.magritte.loaders;

import tara.magritte.Expression;
import tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static tara.magritte.loaders.FunctionLoader.link;

class ListProcessor {

	private static final Logger LOG = Logger.getLogger(ListProcessor.class.getName());

	static List<?> process(List<?> toProcess, Layer layer) {
		List<Object> result = new ArrayList<>();
		for (Object item : toProcess) result.add(process(item, layer));
		return result;
	}

	static Object process(Object item, Layer layer) {
		return item instanceof String && ((String) item).startsWith("$@") ? process((String) item, layer) : item;
	}

	static Object process(String item, Layer layer) {
		try {
			return link(NativeCodeLoader.nativeCodeOf(ClassFinder.find(item.substring(2))), layer, Expression.class).value();
		} catch (ClassNotFoundException e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}

}
