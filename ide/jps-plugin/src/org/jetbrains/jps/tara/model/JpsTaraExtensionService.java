package org.jetbrains.jps.tara.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.service.JpsServiceManager;
import org.jetbrains.jps.tara.model.impl.TaraJpsCompilerSettings;

public abstract class JpsTaraExtensionService {

	public static JpsTaraExtensionService getInstance() {
		return JpsServiceManager.getInstance().getService(JpsTaraExtensionService.class);
	}

	@Nullable
	public abstract JpsTaraFacet getExtension(@NotNull JpsModule module);

	public abstract TaraJpsCompilerSettings getSettings(@NotNull JpsProject project);
}
