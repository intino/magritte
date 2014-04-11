package org.jetbrains.jps.incremental.tara.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.incremental.CompileContext;

import java.util.Collection;

/**
 * @author Sergey Evdokimov
 */
public interface TaraBuilderExtension {

	@NotNull
	Collection<String> getCompilationClassPath(@NotNull CompileContext context, @NotNull ModuleChunk chunk);

	@NotNull
	Collection<String> getCompilationUnitPatchers(@NotNull CompileContext context, @NotNull ModuleChunk chunk);

}