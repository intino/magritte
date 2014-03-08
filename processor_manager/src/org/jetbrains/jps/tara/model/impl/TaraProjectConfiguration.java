package org.jetbrains.jps.tara.model.impl;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.execution.ParametersListUtil;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Transient;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaraProjectConfiguration {
	public static final String CONFIGURATION_FILE_RELATIVE_PATH = "tara/configuration.xml";
	public static final Set<String> DEFAULT_FILTERING_EXCLUDED_EXTENSIONS;

	static {
		final THashSet<String> set = new THashSet<>(FileUtil.PATH_HASHING_STRATEGY);
		set.addAll(Arrays.asList("jpg", "jpeg", "gif", "bmp", "png"));
		DEFAULT_FILTERING_EXCLUDED_EXTENSIONS = Collections.unmodifiableSet(set);
	}

	private static final Pattern PROPERTY_PATTERN = Pattern.compile("-D(\\S+?)=(.+)");
	private static volatile Map<String, String> ourPropertiesFromMvnOpts;
	private static volatile Properties ourSystemProperties;
	@Tag("resource-processing")
	@MapAnnotation(surroundWithTag = false, surroundKeyWithTag = false, surroundValueWithTag = false, entryTagName = "maven-module", keyAttributeName = "name")
	public Map<String, TaraModuleConfiguration> moduleConfigurations = new THashMap<>();
	@Transient
	private volatile Map<TaraIdBean, TaraModuleConfiguration> myIdToModuleMap;

	@NotNull
	private static Map<String, String> getTaraOptsProperties() {
		Map<String, String> res = ourPropertiesFromMvnOpts;
		if (res == null) {
			String mavenOpts = System.getenv("MAVEN_OPTS");
			if (mavenOpts != null) {
				res = new HashMap<>();
				final String[] split = ParametersListUtil.parseToArray(mavenOpts);
				for (String parameter : split) {
					final Matcher matcher = PROPERTY_PATTERN.matcher(parameter);
					if (matcher.matches()) {
						res.put(matcher.group(1), matcher.group(2));
					}
				}
			} else {
				res = Collections.emptyMap();
			}

			ourPropertiesFromMvnOpts = res;
		}

		return res;
	}

	public static Properties getSystemProperties() {
		Properties res = ourSystemProperties;
		if (res == null) {
			res = new Properties();
			res.putAll(System.getProperties());

			for (Iterator<Object> itr = res.keySet().iterator(); itr.hasNext(); ) {
				final String propertyName = itr.next().toString();
				if (propertyName.startsWith("idea.")) {
					itr.remove();
				}
			}

			for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
				String key = entry.getKey();
				if (key.startsWith("=")) {
					continue;
				}
				if (SystemInfo.isWindows) {
					key = key.toUpperCase();
				}
				res.setProperty("env." + key, entry.getValue());
			}

			ourSystemProperties = res;
		}
		return res;
	}

	@Nullable
	public TaraModuleConfiguration findProject(TaraIdBean id) {
		return getModuleConfigurationMap().get(id);
	}

	@NotNull
	private Map<TaraIdBean, TaraModuleConfiguration> getModuleConfigurationMap() {
		Map<TaraIdBean, TaraModuleConfiguration> map = myIdToModuleMap;
		if (map == null) {
			map = new THashMap<>();
			for (TaraModuleConfiguration configuration : moduleConfigurations.values()) {
				if (configuration != null) {
					map.put(configuration.id, configuration);
				}
			}
			myIdToModuleMap = map;
		}
		return map;
	}

	@Nullable
	public String resolveProperty(final String propName, final TaraModuleConfiguration moduleConfig, Map<String, String> additionalProperties) {
		boolean hasPrefix = false;
		String unprefixed = propName;

		if (propName.startsWith("pom.")) {
			unprefixed = propName.substring("pom.".length());
			hasPrefix = true;
		} else if (propName.startsWith("project.")) {
			unprefixed = propName.substring("project.".length());
			hasPrefix = true;
		}

		TaraModuleConfiguration selectedConfig = moduleConfig;

		while (unprefixed.startsWith("parent.")) {
			TaraIdBean parentId = selectedConfig.parentId;
			if (parentId == null) {
				return null;
			}

			unprefixed = unprefixed.substring("parent.".length());

			if (unprefixed.equals("groupId")) {
				return parentId.groupId;
			}
			if (unprefixed.equals("artifactId")) {
				return parentId.artifactId;
			}

			selectedConfig = findProject(parentId);
			if (selectedConfig == null) {
				return null;
			}
		}

		if (unprefixed.equals("basedir") || (hasPrefix && moduleConfig == selectedConfig && unprefixed.equals("baseUri"))) {
			return selectedConfig.directory;
		}

		String result;

		result = getTaraOptsProperties().get(propName);
		if (result != null) {
			return result;
		}

		result = getSystemProperties().getProperty(propName);
		if (result != null) {
			return result;
		}

		result = selectedConfig.modelMap.get(unprefixed);
		if (result != null) {
			return result;
		}

		result = additionalProperties.get(propName);
		if (result != null) {
			return result;
		}

		return moduleConfig.properties.get(propName);
	}
}
