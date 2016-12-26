package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class ListProcessor {

	private static final Logger LOG = Logger.getLogger(ListProcessor.class.getName());

	static List<?> process(List<?> toProcess, Layer layer) {
		List<Object> result = new ArrayList<>();
		for (Object item : toProcess) result.add(process(item, layer));
		return result;
	}

	static Object process(Object item, Layer layer) {
		return item instanceof String && ((String) item).startsWith("$@") ? process(item.toString().substring(2), layer) : item;
	}

	static Object process(String item, Layer layer) {
		try {
			return FunctionLoader.link(NativeCodeLoader.nativeCodeOf(ClassFinder.find(item)), layer, Expression.class).value();
		} catch (ClassNotFoundException e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}

}
