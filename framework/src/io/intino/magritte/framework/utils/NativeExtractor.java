package io.intino.magritte.framework.utils;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;

import java.lang.reflect.Field;

import static java.util.logging.Logger.getGlobal;

@SuppressWarnings("WeakerAccess")
public class NativeExtractor {

	public static NativeCode extract(String fieldName, Layer layer) {
		try {
			Class<? extends Layer> aClass = layer.getClass();
			Field declaredField = declaredField(fieldName, aClass);
			while (declaredField == null) {
				aClass = (Class<? extends Layer>) aClass.getSuperclass();
				if (aClass == null) break;
				declaredField = declaredField(fieldName, aClass);
			}
			boolean previousAccessibility = declaredField.isAccessible();
			declaredField.setAccessible(true);
			NativeCode result = (NativeCode) declaredField.get(layer);
			declaredField.setAccessible(previousAccessibility);
			return result;
		} catch (Exception e) {
			getGlobal().severe("Failed to extract the class from a native: " + e.getCause().getMessage());
			return null;
		}
	}

	private static Field declaredField(String fieldName, Class<? extends Layer> aClass) {
		for (Field field : aClass.getDeclaredFields())
			if (field.getName().equals(fieldName)) return field;
		return null;
	}
}
