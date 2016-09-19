package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class TaraModuleExtensionProperties {

	@Tag("type")
	public String type = "Platform";

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

	@Tag("lazyLoad")
	public boolean lazyLoad = false;

	@Tag("persistent")
	public boolean persistent;

	@Tag("platformRefactorId")
	public int platformRefactorId = -1;

	@Tag("applicationRefactorId")
	public int applicationRefactorId = -1;

	@Tag("workingDirectory")
	public String workingDirectory = "";

	@Tag("supportedLanguages")
	public List<String> supportedLanguages = new ArrayList<>(singletonList("tara"));
}