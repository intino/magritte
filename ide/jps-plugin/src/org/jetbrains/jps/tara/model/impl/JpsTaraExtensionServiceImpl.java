package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;

public class JpsTaraExtensionServiceImpl extends JpsTaraExtensionService {

	@NotNull
	@Override
	public JpsModuleConfiguration getOrCreateExtension(@NotNull JpsModule module) {
		JpsModuleConfiguration extension = module.getContainer().getChild(JpsModuleConfiguration.ROLE);
		if (extension == null) {
			extension = new JpsModuleConfiguration();
			module.getContainer().setChild(JpsModuleConfiguration.ROLE, extension);
		}
		return extension;
	}

	@Nullable
	@Override
	public JpsModuleConfiguration getConfiguration(@NotNull JpsModule module, CompileContext context) {
		return new JpsConfigurationLoader(module, context).load();
	}

	@Nullable
	@Override
	public TaraJpsCompilerSettings getSettings(@NotNull JpsProject project) {
		return project.getContainer().getChild(TaraJpsCompilerSettings.ROLE);
	}
}
