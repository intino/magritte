package org.jetbrains.jps.incremental.tara.model.impl;

import org.jetbrains.jps.incremental.tara.model.JpsTaraModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

public class JpsTaraModuleExtensionImpl extends JpsElementBase<JpsTaraModuleExtensionImpl> implements JpsTaraModuleExtension {
	public static final JpsElementChildRole<JpsTaraModuleExtension> ROLE = JpsElementChildRoleBase.create("tara");

	public JpsTaraModuleExtensionImpl() {
	}

	@NotNull
	@Override
	public JpsTaraModuleExtensionImpl createCopy() {
		return new JpsTaraModuleExtensionImpl();
	}

	@Override
	public void applyChanges(@NotNull JpsTaraModuleExtensionImpl modified) {
		//TODO
	}
}

