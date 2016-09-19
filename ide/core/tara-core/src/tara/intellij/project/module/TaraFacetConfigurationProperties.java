package tara.intellij.project.module;

import com.intellij.util.xmlb.annotations.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraFacetConfigurationProperties {

	@Tag("type")
	public String type = "Platform";

	@Tag("workingPackage")
	public String workingPackage;

	@Tag("supportedLanguages")
	public List<String> supportedLanguages = new ArrayList<>(Collections.singletonList("tara"));

	@Tag("platformDsl")
	public String platformDsl = "Proteo";

	@Tag("applicationDsl")
	public String applicationDsl = "";

	@Tag("systemDsl")
	public String systemDsl = "";

	@Tag("platformOutDsl")
	public String platformOutDsl = "";

	@Tag("applicationOutDsl")
	public String applicationOutDsl = "";

	@Tag("applicationImportedDsl")
	public boolean applicationImportedDsl = false;

	@Tag("systemImportedDsl")
	public boolean systemImportedDsl = false;

	@Tag("lazyLoad")
	public boolean lazyLoad = false;

	@Tag("persistent")
	public boolean persistent;

	@Tag("testModule")
	public boolean testModule = false;

	@Tag("platformRefactorId")
	public int platformRefactorId = -1;

	@Tag("applicationRefactorId")
	public int applicationRefactorId = -1;
}