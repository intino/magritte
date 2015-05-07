package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dictionary")
	public String dictionary = "English";

	@Tag("genDslName")
	public String generatedDslName = "";

}