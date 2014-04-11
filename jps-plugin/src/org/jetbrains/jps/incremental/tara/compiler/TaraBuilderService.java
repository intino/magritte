package org.jetbrains.jps.incremental.tara.compiler;

import org.jetbrains.jps.incremental.tara.model.impl.TaraTargetType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildTargetType;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.TargetBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TaraBuilderService extends BuilderService {

	@NotNull
	@Override
	public List<? extends BuildTargetType<?>> getTargetTypes() {
		return Arrays.asList(TaraTargetType.PRODUCTION);
	}

	@NotNull
	@Override
	public List<? extends TargetBuilder<?, ?>> createBuilders() {
		return Collections.singletonList(new TaraBuilder());
	}
}