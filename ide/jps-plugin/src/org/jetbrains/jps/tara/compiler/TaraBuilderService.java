package org.jetbrains.jps.tara.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;

import java.util.Collections;
import java.util.List;


public class TaraBuilderService extends BuilderService {

	@NotNull
	public List<? extends ModuleLevelBuilder> createModuleLevelBuilders() {
		return Collections.singletonList(new TaraBuilder());
	}
}