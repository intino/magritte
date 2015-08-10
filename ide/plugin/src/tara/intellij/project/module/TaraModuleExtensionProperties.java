package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dictionary")
	public String dictionary = "English";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("referenceModel")
	public String referenceModelPath = "";

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("plateRequired")
	public boolean plateRequired = false;

	@Tag("level")
	public int level = 2;
}