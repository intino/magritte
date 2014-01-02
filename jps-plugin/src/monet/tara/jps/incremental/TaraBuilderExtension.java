package monet.tara.jps.incremental;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.incremental.CompileContext;

import java.util.Collection;

public interface TaraBuilderExtension {

	@NotNull
	Collection<String> getCompilationClassPath(@NotNull CompileContext context, @NotNull ModuleChunk chunk);

	@NotNull
	Collection<String> getCompilationUnitPatchers(@NotNull CompileContext context, @NotNull ModuleChunk chunk);
}