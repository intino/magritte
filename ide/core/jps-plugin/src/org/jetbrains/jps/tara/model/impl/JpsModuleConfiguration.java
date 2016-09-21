package org.jetbrains.jps.tara.model.impl;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class JpsModuleConfiguration extends JpsElementBase<JpsModuleConfiguration> implements JpsElement {

	public static final JpsElementChildRole<JpsModuleConfiguration> ROLE = JpsElementChildRoleBase.create("tara-conf");


	public String workingDirectory = "";

	public String type = "Platform";

	public String applicationDsl = "";

	public String systemDsl = "";

	public String platformOutDsl = "";

	public String applicationOutDsl = "";

	public boolean persistent;

	public int platformRefactorId = -1;

	public int applicationRefactorId = -1;

	public List<String> supportedLanguages = new ArrayList<>(singletonList("tara"));

	@NotNull
	@Override
	public JpsModuleConfiguration createCopy() {
		return new JpsModuleConfiguration();
	}

	@Override
	public void applyChanges(@NotNull JpsModuleConfiguration modified) {

	}

}