package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("referenceModel")
	public String referenceModelPath = "";

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("customMorphs")
	public boolean customMorphs = false;

	@Tag("languageExtension")
	public String languageExtension = "";

	@Tag("plateRequired")
	public boolean plateRequired = false;

	@Tag("level")
	public int level = 2;
}