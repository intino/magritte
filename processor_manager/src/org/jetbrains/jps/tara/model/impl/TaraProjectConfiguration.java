package org.jetbrains.jps.tara.model.impl;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.Tag;
import gnu.trove.THashMap;
import gnu.trove.THashSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TaraProjectConfiguration {
	public static final String CONFIGURATION_FILE_RELATIVE_PATH = "tara/tarac.xml";
	public static final Set<String> DEFAULT_FILTERING_EXCLUDED_EXTENSIONS;

	static {
		final THashSet<String> set = new THashSet<>(FileUtil.PATH_HASHING_STRATEGY);
		set.addAll(Arrays.asList("jpg", "jpeg", "gif", "bmp", "png"));
		DEFAULT_FILTERING_EXCLUDED_EXTENSIONS = Collections.unmodifiableSet(set);
	}

	@Tag("resource-processing")
	@MapAnnotation(surroundWithTag = false, surroundKeyWithTag = false, surroundValueWithTag = false, entryTagName = "tara-module", keyAttributeName = "name")
	public Map<String, TaraModuleConfiguration> moduleConfigurations = new THashMap<>();

	public TaraProjectConfiguration() {
	}

}
