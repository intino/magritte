package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("customLayers")
	public boolean customLayers = false;

	@Tag("level")
	public int level = 2;

	@Tag("dynamicLoad")
	public boolean dynamicLoad;

	@Tag("testModule")
	public boolean testModule;

	@Tag("ontology")
	public boolean ontology;

	@Tag("engineRefactorId")
	public int engineRefactorId = -1;

	@Tag("domainRefactorId")
	public int domainRefactorId = -1;
}