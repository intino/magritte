/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.jps.tara.model.impl;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.xmlb.annotations.*;
import com.intellij.util.xmlb.annotations.AbstractCollection;
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

		final List<TaraConfiguration> _resources = forTestResources ? testResources : resources;
		result = 31 * result;
		for (TaraConfiguration resource : _resources) {
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


