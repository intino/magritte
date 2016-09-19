package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraFacet;

public class JpsTaraExtensionServiceImpl extends JpsTaraExtensionService {
	@Nullable
	@Override
	public JpsTaraFacet getExtension(@NotNull JpsModule module) {
		return module.getContainer().getChild(JpsTaraFacetImpl.ROLE);
	}

	@Nullable
	@Override
	public TaraJpsCompilerSettings getSettings(@NotNull JpsProject project) {
		return project.getContainer().getChild(TaraJpsCompilerSettings.ROLE);
	}
}
