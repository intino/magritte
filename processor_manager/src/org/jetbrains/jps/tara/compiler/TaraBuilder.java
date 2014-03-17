package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import gnu.trove.THashMap;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.cmdline.ProjectDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.java.ClassPostProcessor;
import org.jetbrains.jps.incremental.java.JavaBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.javac.OutputFileObject;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.java.compiler.JpsJavaCompilerConfiguration;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.service.SharedThreadPool;
import org.jetbrains.jps.tara.model.impl.TaraRootDescriptor;
import org.jetbrains.jps.tara.model.impl.TaraTarget;
import org.jetbrains.jps.tara.model.impl.TaraTargetType;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

public class TaraBuilder extends TargetBuilder<TaraRootDescriptor, TaraTarget> {
	private static final Logger LOG = Logger.getInstance("#TaraBuilder");
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static final String TARA_EXTENSION = "m2";
	private final boolean pluginGeneration;
	private final String builderName;

	public TaraBuilder(boolean pluginGeneration) {
		super(pluginGeneration ? Arrays.asList(TaraTargetType.PRODUCTION) : null);
		this.pluginGeneration = pluginGeneration;
		LOG.setLevel(Level.ALL);
		builderName = "Tara " + (pluginGeneration ? "plugin generator" : "compiler");
	}

	static {
		JavaBuilder.registerClassPostProcessor(new RecompileStubSources());
	}

	private static Set<String> getPathsToCompile(List<File> toCompile) {
		final Set<String> toCompilePaths = new LinkedHashSet<>();
		for (File file : toCompile) {
			if (LOG.isDebugEnabled())
				LOG.debug("Path to compile: " + file.getPath());
			toCompilePaths.add(FileUtil.toSystemIndependentName(file.getPath()));
		}
		return toCompilePaths;
	}

	private static Map<TaraTarget, Collection<TaracOSProcessHandler.OutputItem>> processCompiledFiles(CompileContext context,
	                                                                                                  TaraTarget target,
	                                                                                                  Map<TaraTarget, String> generationOutputs,
	                                                                                                  String compilerOutput,
	                                                                                                  TaracOSProcessHandler handler)
		throws IOException {
		ProjectDescriptor pd = context.getProjectDescriptor();
		final Map<TaraTarget, Collection<TaracOSProcessHandler.OutputItem>> compiled = new THashMap<>();
		for (final TaracOSProcessHandler.OutputItem item : handler.getSuccessfullyCompiled()) {
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) {
				LOG.info("compiled=" + item);
			}
			final ArrayList<BuildRootDescriptor> rd = (ArrayList<BuildRootDescriptor>) pd.getBuildRootIndex().findAllParentDescriptors(new File(item.sourcePath), context);
			if (!rd.isEmpty()) {
				final String outputPath = target.getModuleOutputDir().getPath();

				Collection<TaracOSProcessHandler.OutputItem> items = compiled.get(rd.get(0).getTarget());
				if (items == null) {
					items = new ArrayList<>();
					compiled.put((TaraTarget) rd.get(0).getTarget(), items);
				}

				items.add(new TaracOSProcessHandler.OutputItem(outputPath, item.sourcePath));
			} else {
				if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) {
					LOG.info("No tara source root descriptor for th e item found =" + item);
				}
			}
		}
		return compiled;
	}

	private static Map<TaraTarget, String> getStubGenerationOutputs(TaraTarget target, CompileContext context) throws IOException {
		Map<TaraTarget, String> generationOutputs = new HashMap<>();
		File commonRoot = new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "taraStubs");
		File targetRoot = new File(commonRoot, target.getModule().getName() + File.separator + target.getTargetType().getTypeId());
		if (!FileUtil.delete(targetRoot)) throw new IOException("External make cannot clean " + targetRoot.getPath());
		if (!targetRoot.mkdirs()) throw new IOException("External make cannot create " + targetRoot.getPath());
		generationOutputs.put(target, targetRoot.getPath());
		return generationOutputs;
	}

	private static String getJavaExecutable() {
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private static Collection<String> generateClasspath() {
		final Set<String> clashPath = new LinkedHashSet<>();
		clashPath.add(getTaraRtRoot().getPath());
		clashPath.add(getAntlrLib().getPath());
		return clashPath;
	}

	private static File getTaraRtRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		if (root.isFile()) {
			return new File(root.getParentFile(), "tara_rt.jar");
		}
		return root;
	}

	private static File getAntlrLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		return new File(root.getParentFile().getAbsolutePath(), "/lib/antlr-4.1-complete.jar");
	}

	public static boolean isTaraFile(String path) {
		return path.endsWith("." + TARA_EXTENSION);
	}

	private TaracOSProcessHandler runTarac(final CompileContext context,
	                                       TaraTarget taraTarget,
	                                       File tempFile,
	                                       final JpsTaraSettings settings) throws IOException {
		ArrayList<String> classpath = new ArrayList<>(generateClasspath());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Tarac classpath: " + classpath);
		}

		List<String> programParams = ContainerUtilRt.newArrayList(!pluginGeneration ? "--gen-plugin" : "tarac", tempFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + settings.heapSize + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		String grapeRoot = System.getProperty(TaracOSProcessHandler.GRAPE_ROOT);
		if (grapeRoot != null)
			vmParams.add("-D" + TaracOSProcessHandler.GRAPE_ROOT + "=" + grapeRoot);
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "monet.tara.TaracRunner",
			Collections.<String>emptyList(), classpath,
			vmParams, programParams);
		final Process process = Runtime.getRuntime().exec(ArrayUtil.toStringArray(cmd));
		final Consumer<String> updater = new Consumer<String>() {
			public void consume(String s) {
				context.processMessage(new ProgressMessage(s));
			}
		};
		final TaracOSProcessHandler handler = new TaracOSProcessHandler(process, updater) {
			@Override
			protected Future<?> executeOnPooledThread(Runnable task) {
				return SharedThreadPool.getInstance().executeOnPooledThread(task);
			}
		};
		handler.startNotify();
		handler.waitFor();
		return handler;
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
		finalOutputs.put(target, moduleOutputPath.endsWith("/") ? moduleOutputPath : moduleOutputPath + "/");
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

	@Override
	public void build(@NotNull TaraTarget target,
	                  @NotNull DirtyFilesHolder<TaraRootDescriptor, TaraTarget> dirtyFilesHolder,
	                  @NotNull BuildOutputConsumer outputConsumer,
	                  @NotNull CompileContext context) throws ProjectBuildException, IOException {
		try {
			JpsTaraSettings settings = JpsTaraSettings.getSettings(context.getProjectDescriptor().getProject());
			final List<File> toCompile = collectFiles(context.getProjectDescriptor().getProject());
			if (toCompile.isEmpty()) return;
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("plugin-generation=" + pluginGeneration);
			Map<TaraTarget, String> finalOutputs = getCanonicalModuleOutputs(context, target);
			if (finalOutputs == null) return;

			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleEncoding(target.getModule());
			Map<TaraTarget, String> generationOutputs = pluginGeneration ? getStubGenerationOutputs(target, context) : finalOutputs;
			String compilerOutput = generationOutputs.get(target);

			String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(target));
			final File tempFile = TaracOSProcessHandler.fillFileWithTaracParameters(compilerOutput, toCompilePaths, finalOutput, encoding);

			final TaracOSProcessHandler handler = runTarac(context, target, tempFile, settings);

			Map<TaraTarget, Collection<TaracOSProcessHandler.OutputItem>>
				compiled = processCompiledFiles(context, target, generationOutputs, compilerOutput, handler);

//			if (pluginGeneration) {
//				//addStubRootsToJavacSourcePath(context, generationOutputs);
//				//rememberStubSources(context, compiled);
//			}

			for (CompilerMessage message : handler.getCompilerMessages(target.getModule().getName()))
				context.processMessage(message);

//			if (!pluginGeneration && updateDependencies(context, compiled, outputConsumer)) {
//			}
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (!pluginGeneration) {
				FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, null);
			}
		}
	}

	private List<File> collectFiles(JpsProject project) {
		final JpsJavaCompilerConfiguration configuration = JpsJavaExtensionService.getInstance().getCompilerConfiguration(project);
		assert configuration != null;
		final List<File> toCompile = new ArrayList<>();
		for (JpsModule module : project.getModules()) {
			for (JpsModuleSourceRoot root : module.getSourceRoots()) {
				toCompile.addAll(getTaraFilesFromRoot(root.getFile()));
			}
		}
		return toCompile;
	}

	private List<File> getTaraFilesFromRoot(@NotNull File root) {
		ArrayList<File> list = new ArrayList<>();
		for (File file : root.listFiles())
			if (file.isDirectory())
				list.addAll(getTaraFilesFromRoot(file));
			else if (isTaraFile(file.getPath()))
				list.add(file);
		return list;
	}


	private static class RecompileStubSources implements ClassPostProcessor {

		public void process(CompileContext context, OutputFileObject out) {
			Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
			if (stubToSrc == null) {
				return;
			}
			File src = out.getSourceFile();
			if (src == null) {
				return;
			}
			String tara = stubToSrc.get(FileUtil.toSystemIndependentName(src.getPath()));
			if (tara == null) {
				return;
			}
			try {
				final File taraFile = new File(tara);
				if (!FSOperations.isMarkedDirty(context, taraFile)) {
					FSOperations.markDirty(context, taraFile);
					FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, Boolean.TRUE);
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}
}