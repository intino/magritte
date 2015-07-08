package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

public class JpsTaraExtensionServiceImpl extends JpsTaraExtensionService {
	@Nullable
	@Override
	public JpsTaraModuleExtension getExtension(@NotNull JpsModule module) {
		return module.getContainer().getChild(JpsTaraModuleExtensionImpl.ROLE);
	}
}
