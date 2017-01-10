package io.intino.tara.magritte.utils;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

import java.lang.reflect.Field;

import static java.util.logging.Logger.getGlobal;

@SuppressWarnings("WeakerAccess")
public class NativeExtractor {

    public static NativeCode extract(String fieldName, Layer layer) {
        try {
            Field declaredField = layer.getClass().getDeclaredField(fieldName);
            boolean previousAccessibility = declaredField.isAccessible();
            declaredField.setAccessible(true);
            NativeCode result = (NativeCode) declaredField.get(layer);
            declaredField.setAccessible(previousAccessibility);
            return result;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getGlobal().severe("Failed to extract the class from a native: " + e.getCause().getMessage());
            return null;
        }
    }
}
