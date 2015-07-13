package org.jetbrains.jps.tara.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.service.JpsServiceManager;

public abstract class JpsTaraExtensionService {

	public static JpsTaraExtensionService getInstance() {
		return JpsServiceManager.getInstance().getService(JpsTaraExtensionService.class);
	}

	@Nullable
	public abstract JpsTaraModuleExtension getExtension(@NotNull JpsModule module);
}
