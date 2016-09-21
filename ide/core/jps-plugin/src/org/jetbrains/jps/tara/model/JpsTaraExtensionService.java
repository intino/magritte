package org.jetbrains.jps.tara.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.service.JpsServiceManager;
import org.jetbrains.jps.tara.model.impl.JpsModuleConfiguration;
import org.jetbrains.jps.tara.model.impl.TaraJpsCompilerSettings;

public abstract class JpsTaraExtensionService {

	public static JpsTaraExtensionService instance() {
		return JpsServiceManager.getInstance().getService(JpsTaraExtensionService.class);
	}


	@NotNull
	public abstract JpsModuleConfiguration getOrCreateExtension(@NotNull JpsModule module);
	@Nullable
	public abstract JpsModuleConfiguration getConfiguration(@NotNull JpsModule module, CompileContext context);

	public abstract TaraJpsCompilerSettings getSettings(@NotNull JpsProject project);
}
