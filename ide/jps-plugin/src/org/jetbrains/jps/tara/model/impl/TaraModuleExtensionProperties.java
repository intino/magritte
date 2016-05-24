package org.jetbrains.jps.tara.model.impl;

import com.intellij.util.xmlb.annotations.Tag;

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
}