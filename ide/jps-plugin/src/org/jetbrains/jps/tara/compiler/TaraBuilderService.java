package org.jetbrains.jps.tara.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;

import java.util.Arrays;
import java.util.List;


public class TaraBuilderService extends BuilderService {

	@NotNull
	public List<? extends ModuleLevelBuilder> createModuleLevelBuilders() {
		final TaraBuilder taraBuilder = new TaraBuilder();
		final JavaRecompiler javaRecompiler = new JavaRecompiler(TaraBuilder.REMEMBERED_SOURCES);
		return Arrays.asList(taraBuilder, javaRecompiler);
	}
}