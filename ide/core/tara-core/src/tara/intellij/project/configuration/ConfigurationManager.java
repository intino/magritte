package tara.intellij.project.configuration;

import com.intellij.openapi.module.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationManager {

	private static Map<Module, Configuration> registeredModules = new HashMap<>();
	private static List<Class<? extends Configuration>> providers = new ArrayList<>();


	public static Configuration register(Module module, Configuration configuration) {
		return registeredModules.put(module, configuration);
	}

	public static Configuration configurationOf(Module module) {
		return registeredModules.get(module);
	}

	public static void unregister(Module module) {
		registeredModules.remove(module);
	}

	public static void registerProvider(Class<? extends Configuration> configuration) {
		providers.add(configuration);
	}

	public static boolean hasExternalProviders() {
		return providers.size() > 0;
	}
}
