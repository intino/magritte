package siani.tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraModuleExtensionProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dictionary")
	public String dictionary = "English";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("addressRequired")
	public boolean addressRequired = false;
}