package org.jetbrains.jps.tara.model.impl;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

public class JpsModuleConfiguration extends JpsElementBase<JpsModuleConfiguration> implements JpsElement {

	public static final JpsElementChildRole<JpsModuleConfiguration> ROLE = JpsElementChildRoleBase.create("tara-conf");

	public String level = "";

	public String dsl = "";

	public String outDSL = "";

	public String workingPackage = "";

	public String groupID = "";
	public String artifactID = "";
	public String version = "1.0.0";
	public String dslVersion = "";

	@NotNull
	@Override
	public JpsModuleConfiguration createCopy() {
		return new JpsModuleConfiguration();
	}

	@Override
	public void applyChanges(@NotNull JpsModuleConfiguration modified) {

	}

}