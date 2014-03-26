package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Tag("resource")
public class TaraConfiguration {
	@Tag("directory")
	@NotNull
	public String directory;

	@Tag("targetPath")
	@Nullable
	public String targetPath;

	@Attribute("filtered")
	public boolean isFiltered;

	@Tag("includes")
	@AbstractCollection(surroundWithTag = false, elementTag = "pattern")
	public Set<String> includes = new HashSet<String>();

	@Tag("excludes")
	@AbstractCollection(surroundWithTag = false, elementTag = "pattern")
	public Set<String> excludes = new HashSet<String>();


	public int computeConfigurationHash() {
		int result = directory.hashCode();
		result = 31 * result + (targetPath != null ? targetPath.hashCode() : 0);
		result = 31 * result + (isFiltered ? 1 : 0);
		return result;
	}
}
