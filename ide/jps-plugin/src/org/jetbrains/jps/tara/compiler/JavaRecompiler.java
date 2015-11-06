package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaBuilderUtil;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaRecompiler extends ModuleLevelBuilder {

	private final Key<Set<String>> rememberedSources;

	public JavaRecompiler(Key<Set<String>> rememberedSources) {
		super(BuilderCategory.CLASS_INSTRUMENTER);
		this.rememberedSources = rememberedSources;
	}

	@Override
	public ExitCode build(CompileContext context, ModuleChunk chunk, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder, OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		commitToJava(context);
		final Set<String> outputs = rememberedSources.get(context);
		final ExitCode exitCode = outputs == null || outputs.isEmpty() ? ExitCode.OK : ExitCode.CHUNK_REBUILD_REQUIRED;
		if (outputs != null) outputs.clear();
		return exitCode;
	}

	public void commitToJava(CompileContext context) throws IOException {
		final Set<String> paths = rememberedSources.get(context);
		if (paths != null)
			JavaBuilderUtil.registerFilesToCompile(context, paths.stream().map(File::new).collect(Collectors.toList()));
	}

	@Override
	public List<String> getCompilableFileExtensions() {
		return Collections.emptyList();
	}

	@NotNull
	@Override
	public String getPresentableName() {
		return "recompileSources";
	}
}
