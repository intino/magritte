package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.tara.model.impl.TaraProjectConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.model.module.JpsDependencyElement;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.service.JpsServiceManager;

/**
 * @author nik
 */
public abstract class JpsTaraExtensionService {
	public static JpsTaraExtensionService getInstance() {
		return JpsServiceManager.getInstance().getService(JpsTaraExtensionService.class);
	}

	@Nullable
	public abstract JpsTaraModuleExtension getExtension(@NotNull JpsModule module);

	@NotNull
	public abstract JpsTaraModuleExtension getOrCreateExtension(@NotNull JpsModule module);

	public abstract void setProductionOnTestDependency(@NotNull JpsDependencyElement dependency, boolean value);

	public abstract boolean isProductionOnTestDependency(@NotNull JpsDependencyElement dependency);

	public abstract boolean hasTaraProjectConfiguration(@NotNull BuildDataPaths paths);

	@NotNull
	public abstract TaraProjectConfiguration getTaraProjectConfiguration(BuildDataPaths paths);
}
