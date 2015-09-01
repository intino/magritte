package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class MorphFactory {
	private static final Logger LOG = Logger.getLogger(MorphFactory.class.getName());

	private static Map<String, Class<? extends Morph>> morphMap = new HashMap<>();
	private static Map<Class<? extends Morph>, String> typeMap = new HashMap<>();
	private static Set<String> abstractTypes = new LinkedHashSet<>();

	static {
		morphMap.put("Root", Root.class);
	}

	public static Morph newInstance(String type, Node node) {
        if(isAbstract(type)) return null;
		if (morphMap.containsKey(type))
			try {
				return morphMap.get(type).getDeclaredConstructor(Node.class).newInstance(node);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				LOG.severe("type not found: " + type);
			}
        return null;
	}

	public static Morph newInstance(Class<? extends Morph> morph, Node node) {
		try {
			return morph.getDeclaredConstructor(Node.class).newInstance(node);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			LOG.severe(e.getMessage());
		}
		return null;
	}

    public static String type(Class<? extends Morph> morph){
        return typeMap.get(morph);
    }

	public static boolean isAbstract(String type) {
		return abstractTypes.contains(type);
	}

	public static void register(String type, String aClass) {
		try {
			morphMap.put(type, (Class<? extends Morph>) Class.forName(aClass));
            typeMap.put(morphMap.get(type), type);
		} catch (ClassNotFoundException e) {
			LOG.severe(e.getMessage());
		}
	}

	public static void registerAbstract(String type, String aClass) {
		register(type, aClass);
        abstractTypes.add(type);
	}

    public static Class<? extends Morph> getClass(String type){
        return morphMap.get(type);
    }

}