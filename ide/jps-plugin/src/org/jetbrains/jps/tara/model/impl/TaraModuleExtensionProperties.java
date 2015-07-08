package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dictionary")
	public String dictionary = "English";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("plateRequired")
	public boolean plateRequired = false;

	@Tag("level")
	public int level = 2;

	@Tag("dslsDirectory")
	public String dslsDirectory = "";
}