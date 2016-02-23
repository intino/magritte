package tara.magritte.utils;

import tara.magritte.Layer;
import tara.magritte.NativeCode;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class NativeClassExtractor {

	private static final Logger LOG = Logger.getLogger(NativeClassExtractor.class.getName());


	public static Class<? extends NativeCode> extract(String fieldName, Layer layer) {
		try {
			Field declaredField = layer.getClass().getDeclaredField(fieldName);
			boolean previousAccessibility = declaredField.isAccessible();
			declaredField.setAccessible(true);
			Class<? extends NativeCode> result = (Class<? extends NativeCode>) declaredField.get(layer).getClass();
			declaredField.setAccessible(previousAccessibility);
			return result;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LOG.severe("Failed to extract the class from a native: " + e.getCause().getMessage());
			return null;
		}
	}
}
