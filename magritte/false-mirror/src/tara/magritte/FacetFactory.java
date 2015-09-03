package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Logger;

public class FacetFactory {
	private static final Logger LOG = Logger.getLogger(FacetFactory.class.getName());

	private static Map<String, Class<? extends Facet>> morphMap = new HashMap<>();
	private static TypeMap typeMap = new TypeMap();
	private static Set<String> abstractTypes = new LinkedHashSet<>();

	static {
		morphMap.put("Root", Root.class);
	}

	public static Facet newInstance(String type, Instance instance) {
        if(isAbstract(type)) return null;
		if (morphMap.containsKey(type))
			try {
				return morphMap.get(type).getDeclaredConstructor(Instance.class).newInstance(instance);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				LOG.severe("Cannot instantiate: " + type);
			}
        else LOG.severe("Type not found: " + type);
        return null;
	}

	public static Facet newInstance(Class<? extends Facet> morph, Case aCase) {
		try {
			return morph.getDeclaredConstructor(Case.class).newInstance(aCase);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			LOG.severe(e.getMessage());
		}
		return null;
	}

    public static List<String> type(Class<? extends Facet> morph){
        return Collections.unmodifiableList(typeMap.get(morph));
    }

	public static boolean isAbstract(String type) {
		return abstractTypes.contains(type);
	}

	public static void register(String type, String aClass) {
		try {
			morphMap.put(type, (Class<? extends Facet>) Class.forName(aClass));
            typeMap.put(morphMap.get(type), type);
		} catch (ClassNotFoundException e) {
			LOG.severe(e.getMessage());
		}
	}

	public static void registerAbstract(String type, String aClass) {
		register(type, aClass);
        abstractTypes.add(type);
	}

    public static Class<? extends Facet> getClass(String type){
        return morphMap.get(type);
    }

    static class TypeMap{

        private Map<Class<? extends Facet>, List<String>> typeMap = new HashMap<>();

        public void put(Class<? extends Facet> aClass, String type) {
            if(!typeMap.containsKey(aClass))
                typeMap.put(aClass, new ArrayList<>());
            typeMap.get(aClass).add(type);
        }

        public List<String> get(Class<? extends Facet> aClass) {
            return typeMap.get(aClass);
        }
    }
}