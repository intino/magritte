package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

public class TaraFacetConfigurationProperties {

	@Tag("dsl")
	public String dsl = "Proteo";

	@Tag("genDslName")
	public String outputDsl = "";

	@Tag("artifactoryDsl")
	public boolean artifactoryDsl = false;

	@Tag("dynamicLoad")
	public boolean dynamicLoad = false;

	@Tag("ontology")
	public boolean ontology = false;

	@Tag("testModule")
	public boolean testModule = false;

	@Tag("engineRefactorId")
	public int engineRefactorId = -1;

	@Tag("domainRefactorId")
	public int domainRefactorId = -1;

	@Tag("level")
	public int level = 2;
}