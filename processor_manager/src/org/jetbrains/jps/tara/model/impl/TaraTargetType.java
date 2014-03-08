package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaraTargetType extends ModuleBasedBuildTargetType<TaraTarget> {
	public static final TaraTargetType PRODUCTION = new TaraTargetType("tara-plugin-production");

	private TaraTargetType(final String typeId) {
		super(typeId);
	}


	@NotNull
	@Override
	public List<TaraTarget> computeAllTargets(@NotNull JpsModel model) {
		final List<TaraTarget> targets = new ArrayList<>();
		for (JpsModule module : model.getProject().getModules())
			targets.add(new TaraTarget(this, module));
		return targets;
	}

	@NotNull
	@Override
	public BuildTargetLoader<TaraTarget> createLoader(@NotNull JpsModel model) {
		final Map<String, JpsModule> modules = new HashMap<>();
		for (JpsModule module : model.getProject().getModules()) {
			modules.put(module.getName(), module);
		}
		return new BuildTargetLoader<TaraTarget>() {
			@Nullable
			@Override
			public TaraTarget createTarget(@NotNull String targetId) {
				final JpsModule module = modules.get(targetId);
				return module != null ? new TaraTarget(TaraTargetType.this, module) : null;
			}
		};
	}
}
