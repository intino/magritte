package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraFacetConfigurationProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dslKey")
	public String dslKey = "000.000.000";

	@Tag("genDslKey")
	public String generatedDslKey = "";

	@Tag("dslVersion")
	public String dslVersion = "0";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("customLayers")
	public boolean customLayers = false;

	@Tag("refactorId")
	public long refactorId = 0;

	@Tag("level")
	public int level = 2;
}