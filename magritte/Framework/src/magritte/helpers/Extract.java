package magritte.helpers;

import java.util.HashMap;
import java.util.Map;

public class Extract {

	private static Map<Class, String> names = new HashMap<>();

	public static String classNameOf(Object object) {
		return nameOf(object.getClass());
	}

	public static String nameOf(Class<?> wrapClass) {
		if (!names.containsKey(wrapClass)) names.put(wrapClass, withoutPackage(wrapClass.getCanonicalName()));
		return names.get(wrapClass);
	}

	private static String withoutPackage(String name) {
		int index = 0;
		for (String token : name.split("\\.")) {
			if (token.charAt(0) < 97) break;
			index += token.length() + 1;
		}
		return name.substring(index).replaceAll("_", "+");
	}
}
