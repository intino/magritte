package org.jetbrains.jps.tara.model.impl;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.Tag;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TaraModuleConfiguration {
	@NotNull
	@Tag("id")
	public TaraIdBean id;

	@Nullable
	@Tag("parentId")
	public TaraIdBean parentId;

	@NotNull
	@Tag("directory")
	public String directory;

	@NotNull
	@Tag("delimiters-pattern")
	public String delimitersPattern;

	@Tag("model-map")
	@MapAnnotation(surroundWithTag = false, surroundKeyWithTag = false, surroundValueWithTag = false)
	public Map<String, String> modelMap = new HashMap<String, String>();

	@Tag("properties")
	@MapAnnotation(surroundWithTag = false, surroundKeyWithTag = false, surroundValueWithTag = false)
	public Map<String, String> properties = new HashMap<String, String>();

	@Tag("filtering-excluded-extensions")
	@AbstractCollection(surroundWithTag = false, elementTag = "extension")
	public Set<String> filteringExclusions = new THashSet<String>(FileUtil.PATH_HASHING_STRATEGY);

	@OptionTag
	public String escapeString = null;

	@OptionTag
	public boolean escapeWindowsPaths = true;

	@Tag("resources")
	@AbstractCollection(surroundWithTag = false, elementTag = "resource")
	public List<TaraConfiguration> resources = new ArrayList<>();

	@Tag("test-resources")
	@AbstractCollection(surroundWithTag = false, elementTag = "resource")
	public List<TaraConfiguration> testResources = new ArrayList<>();

	public int computeConfigurationHash(boolean forTestResources) {
		int result = computeModuleConfigurationHash();

		final List<TaraConfiguration> taraResources = forTestResources ? testResources : resources;
		result = 31 * result;
		for (TaraConfiguration resource : taraResources) {
			result += resource.computeConfigurationHash();
		}
		return result;
	}

	public int computeModuleConfigurationHash() {
		int result = id.hashCode();
		result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
		result = 31 * result + directory.hashCode();
		result = 31 * result + delimitersPattern.hashCode();
		result = 31 * result + modelMap.hashCode();
		result = 31 * result + properties.hashCode();
		result = 31 * result + filteringExclusions.hashCode();
		result = 31 * result + (escapeString != null ? escapeString.hashCode() : 0);
		result = 31 * result + (escapeWindowsPaths ? 1 : 0);
		return result;
	}
}



