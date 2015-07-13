package wata._magritte.lite;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorphFactory {

	private static Map<String, Class<? extends Morph>> morphMap = new HashMap<>();
	private static List<String> abstractTypes = new ArrayList<>();


	public static Morph newInstance(String type, Node node) {
		if (morphMap.containsKey(type.toLowerCase()))
			try {
				return morphMap.get(type.toLowerCase()).getDeclaredConstructor(Node.class).newInstance(node);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				e.printStackTrace();
			}
		throw new RuntimeException("type not found: " + type);
	}

	public static boolean isAbstract(String type) {
		return abstractTypes.contains(type.toLowerCase());
	}

	public static void register(String type, Class<? extends Morph> morph) {
		morphMap.put(type, morph);
	}

	public static void registerAbstract(String type) {
		abstractTypes.add(type);
	}
}
