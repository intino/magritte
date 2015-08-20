package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("customMorphs")
	public boolean customMorphs = false;

	@Tag("level")
	public int level = 2;

	@Tag("dynamicLoad")
	public boolean dynamicLoad;
}