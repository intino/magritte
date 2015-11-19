package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraFacetConfigurationProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("importedLanguagePath")
	public String importedLanguagePath = "";

	@Tag("referenceModel")
	public String referenceModelPath = "";

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("customLayers")
	public boolean customLayers = false;

	@Tag("languageExtension")
	public String languageExtension = "";

	@Tag("refactorId")
	public long refactorId = 0;

	@Tag("level")
	public int level = 2;
}