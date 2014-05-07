package org.jetbrains.jps.incremental.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import monet.tara.compiler.rt.TaraRtConstants;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.java.ClassPostProcessor;
import org.jetbrains.jps.incremental.java.JavaBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.javac.OutputFileObject;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.java.compiler.JpsJavaCompilerConfiguration;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaraBuilder extends ModuleLevelBuilder {

	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "m2";
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static Boolean done = false;
	private final String builderName;
	private boolean pluginGeneration;

	public TaraBuilder() {
		super(BuilderCategory.OVERWRITING_TRANSLATOR);
		LOG.setLevel(Level.ALL);
		builderName = "Tara compiler";
	}

	static {
		JavaBuilder.registerClassPostProcessor(new RecompileStubSources());
	}

	public static boolean isTaraFile(String path) {
		return path.endsWith("." + TARA_EXTENSION);
	}

	@Override
	public List<String> getCompilableFileExtensions() {
		return Arrays.asList("m2");
	}

	public ExitCode build(CompileContext context, ModuleChunk chunk, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder, OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		long start = 0;
		try {
			if (done) return ExitCode.NOTHING_DONE;
			done = true;
			JpsProject project = context.getProjectDescriptor().getProject();
			JpsTaraSettings settings = JpsTaraSettings.getSettings(project);
			pluginGeneration = settings.pluginGeneration;
			final List<File> toCompile = collectFiles(project, settings);
			if (toCompile.isEmpty()) return ExitCode.NOTHING_DONE;
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("plugin-generation = " + pluginGeneration);
			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null) return ExitCode.ABORT;
			start = System.currentTimeMillis();
			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
			Map<ModuleBuildTarget, String> generationOutputs = pluginGeneration ? getStubGenerationOutputs(chunk, context) : finalOutputs;
			String compilerOutput = generationOutputs.get(chunk.representativeTarget());
			String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
			TaraRunner runner = new TaraRunner(project.getName(), compilerOutput, toCompilePaths, finalOutput, encoding,
				getProjectIcon(chunk.getModules(), project.getName()));
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings, pluginGeneration);
			processMessages(chunk, context, handler);
			return ExitCode.OK;
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (start > 0 && LOG.isDebugEnabled()) {
				LOG.debug(builderName + " took " + (System.currentTimeMillis() - start) + " on " + chunk.getName());
			}
		}
	}

	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		for (CompilerMessage message : handler.getCompilerMessages(chunk.getName()))
			context.processMessage(message);
	}

	private String getProjectIcon(Set<JpsModule> jpsModules, String projectName) {
		for (JpsModule module : jpsModules)
			for (JpsModuleSourceRoot root : module.getSourceRoots())
				if ("res".equals(root.getFile().getName())) {
					String logoFile = root.getFile().getAbsoluteFile() + File.separator + TaraRtConstants.LOGO_PATH + File.separator + projectName + ".png";
					File file = new File(logoFile);
					if (file.exists())
						return file.getAbsolutePath();
				}
		return null;
	}

	private Set<String> getPathsToCompile(List<File> toCompile) {
		final Set<String> toCompilePaths = new LinkedHashSet<>();
		for (File file : toCompile) {
			if (LOG.isDebugEnabled())
				LOG.debug("Path to compile: " + file.getPath());
			toCompilePaths.add(FileUtil.toSystemIndependentName(file.getPath()));
		}
		return toCompilePaths;
	}

	private Map<ModuleBuildTarget, String> getStubGenerationOutputs(ModuleChunk chunk, CompileContext
		context) throws IOException {
		Map<ModuleBuildTarget, String> generationOutputs = new HashMap<>();
		File commonRoot = new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "taraStubs");
		for (ModuleBuildTarget target : chunk.getTargets()) {
			File targetRoot = new File(commonRoot, target.getModule().getName() + File.separator + target.getTargetType().getTypeId());
			if (!FileUtil.delete(targetRoot))
				throw new IOException("External make cannot clean " + targetRoot.getPath());
			if (!targetRoot.mkdirs())
				throw new IOException("External make cannot create " + targetRoot.getPath());
			generationOutputs.put(target, targetRoot.getPath());
		}
		return generationOutputs;
	}

	@Nullable
	private Map<ModuleBuildTarget, String> getCanonicalModuleOutputs(CompileContext context, ModuleChunk chunk) {
		Map<ModuleBuildTarget, String> finalOutputs = new HashMap<>();
		for (ModuleBuildTarget target : chunk.getTargets()) {
			File moduleOutputDir = target.getOutputDir();
			if (moduleOutputDir == null) {
				context.processMessage(new CompilerMessage(builderName, BuildMessage.Kind.ERROR,
					"Output directory not specified for module " + target.getModule().getName()));
				return null;
			}
			String moduleOutputPath = FileUtil.toCanonicalPath(moduleOutputDir.getPath());
			assert moduleOutputPath != null;
			finalOutputs.put(target, moduleOutputPath.endsWith("/") ? moduleOutputPath : moduleOutputPath + "/");
		}
		return finalOutputs;
	}

	@Override
	public String toString() {
		return builderName;
	}

	@NotNull
	public String getPresentableName() {
		return builderName;
	}

	private List<File> collectFiles(JpsProject project, JpsTaraSettings settings) {
		final JpsJavaCompilerConfiguration configuration = JpsJavaExtensionService.getInstance().getCompilerConfiguration(project);
		assert configuration != null;
		final List<File> toCompile = new ArrayList<>();
		for (JpsModule module : project.getModules())
			for (JpsModuleSourceRoot root : module.getSourceRoots())
				if (!settings.isExcludedFromCompilation(root.getFile()))
					toCompile.addAll(getTaraFilesFromRoot(root.getFile()));
		return toCompile;
	}

	private List<File> getTaraFilesFromRoot(@NotNull File root) {
		List<File> list = new ArrayList<>();
		File[] files = root.listFiles();
		if (files != null) {
			for (File file : files)
				if (file.isDirectory())
					list.addAll(getTaraFilesFromRoot(file));
				else if (isTaraFile(file.getPath()))
					list.add(file);
		}
		return list;
	}

	private static class RecompileStubSources implements ClassPostProcessor {

		public void process(CompileContext context, OutputFileObject out) {
			Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
			if (stubToSrc == null)
				return;
			File src = out.getSourceFile();
			if (src == null)
				return;
			String groovy = stubToSrc.get(FileUtil.toSystemIndependentName(src.getPath()));
			if (groovy == null)
				return;
			try {
				final File groovyFile = new File(groovy);
				if (!FSOperations.isMarkedDirty(context, groovyFile)) {
					FSOperations.markDirty(context, groovyFile);
					FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, Boolean.TRUE);
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}
}