package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import monet.tara.compiler.rt.TaraRtConstants;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.Utils;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.java.compiler.JpsJavaCompilerConfiguration;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.tara.model.impl.TaraRootDescriptor;
import org.jetbrains.jps.tara.model.impl.TaraTarget;
import org.jetbrains.jps.tara.model.impl.TaraTargetType;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaraBuilder extends TargetBuilder<TaraRootDescriptor, TaraTarget> {

	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "m2";
	private final boolean pluginGeneration;
	private final String builderName;

	public TaraBuilder(boolean pluginGeneration) {
		super(pluginGeneration ? Arrays.asList(TaraTargetType.PRODUCTION) : null);
		this.pluginGeneration = pluginGeneration;
		LOG.setLevel(Level.ALL);
		builderName = "Tara " + (pluginGeneration ? "plugin generator" : "compiler");
	}

	@Override
	public void build(@NotNull TaraTarget target,
	                  @NotNull DirtyFilesHolder<TaraRootDescriptor, TaraTarget> dirtyFilesHolder,
	                  @NotNull BuildOutputConsumer outputConsumer,
	                  @NotNull CompileContext context) throws ProjectBuildException, IOException {
		try {
			JpsProject project = context.getProjectDescriptor().getProject();
			JpsTaraSettings settings = JpsTaraSettings.getSettings(project);
			final List<File> toCompile = collectFiles(project);
			if (toCompile.isEmpty()) return;
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("plugin-generation=" + pluginGeneration);
			Map<TaraTarget, String> finalOutputs = getCanonicalModuleOutputs(context, target);
			if (finalOutputs == null) return;
			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleEncoding(target.getModule());
			Map<TaraTarget, String> generationOutputs = pluginGeneration ? getStubGenerationOutputs(target, context) : finalOutputs;
			String compilerOutput = generationOutputs.get(target);
			String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(target));
			TaraRunner runner = new TaraRunner(project.getName(), compilerOutput, toCompilePaths, finalOutput, encoding,
				getProjectIcon(target.getModule().getSourceRoots(), project.getName()));
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings, pluginGeneration);
			processMessages(target, context, handler);
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		}
	}

	private void processMessages(TaraTarget target, CompileContext context, TaracOSProcessHandler handler) {
		for (CompilerMessage message : handler.getCompilerMessages(target.getModule().getName()))
			context.processMessage(message);
	}

	private String getProjectIcon(List<JpsModuleSourceRoot> sourceRoots, String projectName) {
		for (JpsModuleSourceRoot root : sourceRoots) {
			if ("res".equals(root.getFile().getName())) {
				String logoFile = root.getFile().getAbsoluteFile() + File.separator + TaraRtConstants.LOGO_PATH + File.separator + projectName + ".png";
				File file = new File(logoFile);
				if (file.exists())
					return file.getAbsolutePath();
			}
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

	private Map<TaraTarget, String> getStubGenerationOutputs(TaraTarget target, CompileContext
		context) throws IOException {
		Map<TaraTarget, String> generationOutputs = new HashMap<>();
		File commonRoot = new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "taraStubs");
		File targetRoot = new File(commonRoot, target.getModule().getName() + File.separator + target.getTargetType().getTypeId());
		if (!FileUtil.delete(targetRoot))
			throw new IOException("External make cannot clean " + targetRoot.getPath());
		if (!targetRoot.mkdirs()) throw new IOException("External make cannot create " + targetRoot.getPath());
		generationOutputs.put(target, targetRoot.getPath());
		return generationOutputs;
	}


	private boolean isTaraFile(String path) {
		return path.endsWith("." + TARA_EXTENSION);
	}

	@Nullable
	private Map<TaraTarget, String> getCanonicalModuleOutputs(CompileContext context, TaraTarget target) {
		Map<TaraTarget, String> finalOutputs = new HashMap<>();
		File moduleOutputDir = target.getModuleOutputDir();
		if (moduleOutputDir == null) {
			context.processMessage(new CompilerMessage(builderName, BuildMessage.Kind.ERROR,
				"Output directory not specified for module " + target.getModule().getName()));
			return null;
		}
		String moduleOutputPath = FileUtil.toCanonicalPath(moduleOutputDir.getPath());
		assert moduleOutputPath != null;
		finalOutputs.put(target, moduleOutputPath.endsWith(File.separator) ? moduleOutputPath : moduleOutputPath + File.separator);
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

	private List<File> collectFiles(JpsProject project) {
		final JpsJavaCompilerConfiguration configuration = JpsJavaExtensionService.getInstance().getCompilerConfiguration(project);
		assert configuration != null;
		final List<File> toCompile = new ArrayList<>();
		for (JpsModule module : project.getModules())
			for (JpsModuleSourceRoot root : module.getSourceRoots())
				toCompile.addAll(getTaraFilesFromRoot(root.getFile()));
		return toCompile;
	}

	private List<File> getTaraFilesFromRoot(@NotNull File root) {
		List<File> list = new ArrayList<>();
		for (File file : root.listFiles())
			if (file.isDirectory())
				list.addAll(getTaraFilesFromRoot(file));
			else if (isTaraFile(file.getPath()))
				list.add(file);
		return list;
	}
}