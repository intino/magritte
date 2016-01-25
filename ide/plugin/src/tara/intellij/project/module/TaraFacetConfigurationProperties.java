package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraFacetConfigurationProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("dslKey")
	public String dslKey = "";

	@Tag("genDslKey")
	public String generatedDslKey = "";

	@Tag("dslVersion")
	public String dslVersion = "0";

	@Tag("genDslName")
	public String generatedDslName = "";

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("testModule")
	public boolean testModule = false;

	@Tag("engineRefactorId")
	public int engineRefactorId = -1;

	@Tag("domainRefactorId")
	public int domainRefactorId = -1;

	@Tag("level")
	public int level = 2;
}