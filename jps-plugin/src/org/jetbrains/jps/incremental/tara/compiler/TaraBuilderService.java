package org.jetbrains.jps.incremental.tara.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;

import java.util.Arrays;
import java.util.List;


public class TaraBuilderService extends BuilderService {

	@NotNull
	public List<? extends ModuleLevelBuilder> createModuleLevelBuilders() {
		return Arrays.asList(new TaraBuilder());
	}
}