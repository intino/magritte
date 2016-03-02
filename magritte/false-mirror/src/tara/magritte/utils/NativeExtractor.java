package tara.magritte.utils;

import tara.magritte.Layer;
import tara.magritte.NativeCode;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class NativeExtractor {

	private static final Logger LOG = Logger.getLogger(NativeExtractor.class.getName());


	public static NativeCode extract(String fieldName, Layer layer) {
		try {
			Field declaredField = layer.getClass().getDeclaredField(fieldName);
			boolean previousAccessibility = declaredField.isAccessible();
			declaredField.setAccessible(true);
			NativeCode result = (NativeCode) declaredField.get(layer);
			declaredField.setAccessible(previousAccessibility);
			return result;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LOG.severe("Failed to extract the class from a native: " + e.getCause().getMessage());
			return null;
		}
	}
}
