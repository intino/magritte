package monet.tara.jps.incremental;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.asm4.ClassReader;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.ProjectPaths;
import org.jetbrains.jps.builders.BuildRootIndex;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.FileProcessor;
import org.jetbrains.jps.builders.java.JavaBuilderUtil;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.builders.java.dependencyView.Callbacks;
import org.jetbrains.jps.builders.java.dependencyView.Mappings;
import org.jetbrains.jps.builders.storage.SourceToOutputMapping;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.cmdline.ProjectDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.java.ClassPostProcessor;
import org.jetbrains.jps.incremental.java.JavaBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.javac.OutputFileObject;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.java.JpsJavaSdkType;
import org.jetbrains.jps.model.java.compiler.JpsJavaCompilerConfiguration;
import org.jetbrains.jps.model.library.sdk.JpsSdk;
import org.jetbrains.jps.service.JpsServiceManager;
import org.jetbrains.jps.service.SharedThreadPool;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class TaraBuilder extends ModuleLevelBuilder {
	private static final Logger LOG = Logger.getInstance("#monet.tara.jps.incremental.TaraBuilder");
	private static final Key<Boolean> CHUNK_REBUILD_ORDERED = Key.create("CHUNK_REBUILD_ORDERED");
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static final String TARA_EXTENSION = "m2";
	private final boolean myForStubs;
	private final String myBuilderName;

	public TaraBuilder(boolean forStubs) {
		super(forStubs ? BuilderCategory.SOURCE_GENERATOR : BuilderCategory.OVERWRITING_TRANSLATOR);
		myForStubs = forStubs;
		myBuilderName = "Tara " + (forStubs ? "stub generator" : "compiler");
	}

	static {
		JavaBuilder.registerClassPostProcessor(new RecompileStubSources());
	}

	public ModuleLevelBuilder.ExitCode build(final CompileContext context,
	                                         ModuleChunk chunk,
	                                         DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
	                                         OutputConsumer outputConsumer) throws ProjectBuildException {
		try {
			JpsTaraSettings settings = JpsTaraSettings.getSettings(context.getProjectDescriptor().getProject());
			final List<File> toCompile = collectChangedFiles(context, dirtyFilesHolder);
			if (toCompile.isEmpty())
				return hasFilesToCompileForNextRound(context) ? ExitCode.ADDITIONAL_PASS_REQUIRED : ExitCode.NOTHING_DONE;

			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled())
				LOG.info("forStubs=" + myForStubs);

			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null)
				return ExitCode.ABORT;

			final Set<String> toCompilePaths = getPathsToCompile(toCompile);

			Map<String, String> class2Src = buildClassToSourceMap(chunk, context, toCompilePaths, finalOutputs);

			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);

			Map<ModuleBuildTarget, String> generationOutputs = myForStubs ? getStubGenerationOutputs(chunk, context) : finalOutputs;
			String compilerOutput = generationOutputs.get(chunk.representativeTarget());

			String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
			final File tempFile = TaracOSProcessHandler.fillFileWithTaracParameters(
				compilerOutput, toCompilePaths, finalOutput, class2Src, encoding);

			final TaracOSProcessHandler handler = runTarac(context, chunk, tempFile, settings);

			Map<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>>
				compiled = processCompiledFiles(context, chunk, generationOutputs, compilerOutput, handler);

			if (checkChunkRebuildNeeded(context, handler))
				return ExitCode.CHUNK_REBUILD_REQUIRED;

			if (myForStubs) {
				addStubRootsToJavacSourcePath(context, generationOutputs);
				rememberStubSources(context, compiled);
			}

			for (CompilerMessage message : handler.getCompilerMessages(chunk.representativeTarget().getModule().getName()))
				context.processMessage(message);

			if (!myForStubs && updateDependencies(context, chunk, dirtyFilesHolder, toCompile, compiled, outputConsumer))
				return ExitCode.ADDITIONAL_PASS_REQUIRED;
			return hasFilesToCompileForNextRound(context) ? ExitCode.ADDITIONAL_PASS_REQUIRED : ExitCode.OK;
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (!myForStubs) {
				FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, null);
			}
		}
	}

	private Boolean hasFilesToCompileForNextRound(CompileContext context) {
		return !myForStubs && FILES_MARKED_DIRTY_FOR_NEXT_ROUND.get(context, Boolean.FALSE);
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

	private TaracOSProcessHandler runTarac(final CompileContext context,
	                                       ModuleChunk chunk,
	                                       File tempFile,
	                                       final JpsTaraSettings settings) throws IOException {
		ArrayList<String> classpath = new ArrayList<>(generateClasspath(context, chunk));
		if (LOG.isDebugEnabled()) {
			LOG.debug("Tarac classpath: " + classpath);
		}

		List<String> programParams = ContainerUtilRt.newArrayList(myForStubs ? "stubs" : "tarac", tempFile.getPath());
		if (settings.invokeDynamic) {
			programParams.add("--indy");
		}

		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + settings.heapSize + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		//vmParams.add("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5239");

		String grapeRoot = System.getProperty(TaracOSProcessHandler.GRAPE_ROOT);
		if (grapeRoot != null)
			vmParams.add("-D" + TaracOSProcessHandler.GRAPE_ROOT + "=" + grapeRoot);
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(chunk), "monet.tara.compiler.rt.TaracRunner",
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

	private static boolean checkChunkRebuildNeeded(CompileContext context, TaracOSProcessHandler handler) {
		if (JavaBuilderUtil.isForcedRecompilationAllJavaModules(context) || !handler.shouldRetry()) {
			return false;
		}

		if (CHUNK_REBUILD_ORDERED.get(context) != null) {
			CHUNK_REBUILD_ORDERED.set(context, null);
			return false;
		}

		CHUNK_REBUILD_ORDERED.set(context, Boolean.TRUE);
		LOG.info("Order chunk rebuild");
		return true;
	}

	private static void rememberStubSources(CompileContext context, Map<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>> compiled) {
		Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
		if (stubToSrc == null)
			STUB_TO_SRC.set(context, stubToSrc = new ConcurrentHashMap<>());
		for (Collection<TaracOSProcessHandler.OutputItem> items : compiled.values())
			for (TaracOSProcessHandler.OutputItem item : items)
				stubToSrc.put(FileUtil.toSystemIndependentName(item.outputPath), item.sourcePath);
	}

	private static void addStubRootsToJavacSourcePath(CompileContext context, Map<ModuleBuildTarget, String> generationOutputs) {
		final BuildRootIndex rootsIndex = context.getProjectDescriptor().getBuildRootIndex();
		for (ModuleBuildTarget target : generationOutputs.keySet()) {
			File root = new File(generationOutputs.get(target));
			rootsIndex.associateTempRoot(context, target, new JavaSourceRootDescriptor(root, target, true, true, "", Collections.<File>emptySet()));
		}
	}

	private static Map<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>> processCompiledFiles(CompileContext context,
	                                                                                                         ModuleChunk chunk,
	                                                                                                         Map<ModuleBuildTarget, String> generationOutputs,
	                                                                                                         String compilerOutput, TaracOSProcessHandler handler)
		throws IOException {
		ProjectDescriptor pd = context.getProjectDescriptor();
		final Map<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>> compiled = new THashMap<>();
		for (final TaracOSProcessHandler.OutputItem item : handler.getSuccessfullyCompiled()) {
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) {
				LOG.info("compiled=" + item);
			}
			final JavaSourceRootDescriptor rd = pd.getBuildRootIndex().findJavaRootDescriptor(context, new File(item.sourcePath));
			if (rd != null) {
				final String outputPath = ensureCorrectOutput(chunk, item, generationOutputs, compilerOutput, rd.target);

				Collection<TaracOSProcessHandler.OutputItem> items = compiled.get(rd.target);
				if (items == null) {
					items = new ArrayList<>();
					compiled.put(rd.target, items);
				}

				items.add(new TaracOSProcessHandler.OutputItem(outputPath, item.sourcePath));
			} else {
				if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) {
					LOG.info("No java source root descriptor for th e item found =" + item);
				}
			}
		}
		return compiled;
	}

	@Override
	public void chunkBuildFinished(CompileContext context, ModuleChunk chunk) {
		JavaBuilderUtil.cleanupChunkResources(context);
		STUB_TO_SRC.set(context, null);
	}

	private static Map<ModuleBuildTarget, String> getStubGenerationOutputs(ModuleChunk chunk, CompileContext context) throws IOException {
		Map<ModuleBuildTarget, String> generationOutputs = new HashMap<>();
		File commonRoot = new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "taraStubs");
		for (ModuleBuildTarget target : chunk.getTargets()) {
			File targetRoot = new File(commonRoot, target.getModule().getName() + File.separator + target.getTargetType().getTypeId());
			if (!FileUtil.delete(targetRoot)) {
				throw new IOException("External make cannot clean " + targetRoot.getPath());
			}
			if (!targetRoot.mkdirs()) {
				throw new IOException("External make cannot create " + targetRoot.getPath());
			}
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
				context.processMessage(new CompilerMessage(myBuilderName, BuildMessage.Kind.ERROR,
					"Output directory not specified for module " + target.getModule().getName()));
				return null;
			}
			String moduleOutputPath = FileUtil.toCanonicalPath(moduleOutputDir.getPath());
			assert moduleOutputPath != null;
			finalOutputs.put(target, moduleOutputPath.endsWith("/") ? moduleOutputPath : moduleOutputPath + "/");
		}
		return finalOutputs;
	}

	private static String ensureCorrectOutput(ModuleChunk chunk,
	                                          TaracOSProcessHandler.OutputItem item,
	                                          Map<ModuleBuildTarget, String> generationOutputs,
	                                          String compilerOutput,
	                                          ModuleBuildTarget srcTarget) throws IOException {
		if (chunk.getModules().size() > 1 && !srcTarget.equals(chunk.representativeTarget())) {
			File output = new File(item.outputPath);

			//todo honor package prefixes
			File correctRoot = new File(generationOutputs.get(srcTarget));
			File correctOutput = new File(correctRoot, FileUtil.getRelativePath(new File(compilerOutput), output));

			FileUtil.rename(output, correctOutput);
			return correctOutput.getPath();
		}
		return item.outputPath;
	}

	private static String getJavaExecutable(ModuleChunk chunk) {
		JpsSdk<?> sdk = chunk.getModules().iterator().next().getSdk(JpsJavaSdkType.INSTANCE);
		if (sdk != null) {
			return JpsJavaSdkType.getJavaExecutable(sdk);
		}
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private List<File> collectChangedFiles(CompileContext context,
	                                       DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder) throws IOException {
		final JpsJavaCompilerConfiguration configuration = JpsJavaExtensionService.getInstance().
			getCompilerConfiguration(context.getProjectDescriptor().getProject());
		assert configuration != null;
		final List<File> toCompile = new ArrayList<>();
		dirtyFilesHolder.processDirtyFiles(new FileProcessor<JavaSourceRootDescriptor, ModuleBuildTarget>() {
			public boolean apply(ModuleBuildTarget target, File file, JavaSourceRootDescriptor sourceRoot) throws IOException {
				if (isTaraFile(file.getPath()))
					toCompile.add(file);
				return true;
			}
		});
		return toCompile;
	}

	private static boolean updateDependencies(CompileContext context,
	                                          ModuleChunk chunk,
	                                          DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
	                                          List<File> toCompile,
	                                          Map<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>> successfullyCompiled,
	                                          OutputConsumer outputConsumer) throws IOException {
		final Mappings delta = context.getProjectDescriptor().dataManager.getMappings().createDelta();
		final List<File> successfullyCompiledFiles = new ArrayList<>();
		if (!successfullyCompiled.isEmpty()) {
			final Callbacks.Backend callback = delta.getCallback();
			for (Map.Entry<ModuleBuildTarget, Collection<TaracOSProcessHandler.OutputItem>> entry : successfullyCompiled.entrySet()) {
				final ModuleBuildTarget target = entry.getKey();
				final Collection<TaracOSProcessHandler.OutputItem> compiled = entry.getValue();
				for (TaracOSProcessHandler.OutputItem item : compiled) {
					final String sourcePath = FileUtil.toSystemIndependentName(item.sourcePath);
					final String outputPath = FileUtil.toSystemIndependentName(item.outputPath);
					final File outputFile = new File(outputPath);
					outputConsumer.registerOutputFile(target, outputFile, Collections.singleton(sourcePath));
					callback.associate(outputPath, sourcePath, new ClassReader(FileUtil.loadFileBytes(outputFile)));
					successfullyCompiledFiles.add(new File(sourcePath));
				}
			}
		}

		return JavaBuilderUtil.updateMappings(context, delta, dirtyFilesHolder, chunk, toCompile, successfullyCompiledFiles);
	}

	private static Collection<String> generateClasspath(CompileContext context, ModuleChunk chunk) {
		final Set<String> clashPath = new LinkedHashSet<>();
		clashPath.add(getTaraRtRoot().getPath());
		clashPath.add(getAntlrLib().getPath());
		for (File file : ProjectPaths.getCompilationClasspathFiles(chunk, chunk.containsTests(), false, false))
			clashPath.add(FileUtil.toCanonicalPath(file.getPath()));
		for (TaraBuilderExtension extension : JpsServiceManager.getInstance().getExtensions(TaraBuilderExtension.class))
			clashPath.addAll(extension.getCompilationClassPath(context, chunk));
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

	@Override
	public List<String> getCompilableFileExtensions() {
		return Arrays.asList(TARA_EXTENSION);
	}

	private static Map<String, String> buildClassToSourceMap(ModuleChunk chunk, CompileContext context, Set<String> toCompilePaths,
	                                                         Map<ModuleBuildTarget, String> finalOutputs) throws IOException {
		final Map<String, String> class2Src = new HashMap<>();
		JpsJavaCompilerConfiguration configuration = JpsJavaExtensionService.getInstance().getOrCreateCompilerConfiguration(
			context.getProjectDescriptor().getProject());
		for (ModuleBuildTarget target : chunk.getTargets()) {
			String moduleOutputPath = finalOutputs.get(target);
			final SourceToOutputMapping srcToOut = context.getProjectDescriptor().dataManager.getSourceToOutputMap(target);
			for (String src : srcToOut.getSources()) {
				if (!toCompilePaths.contains(src) && isTaraFile(src) && !configuration.getCompilerExcludes().isExcluded(new File(src))) {
					final Collection<String> outs = srcToOut.getOutputs(src);
					if (outs != null) {
						for (String out : outs) {
							if (out.endsWith(".class") && out.startsWith(moduleOutputPath)) {
								final String className = out.substring(moduleOutputPath.length(),
									out.length() - ".class".length()).replace('/', '.');
								class2Src.put(className, src);
							}
						}
					}
				}
			}
		}
		return class2Src;
	}

	@Override
	public String toString() {
		return myBuilderName;
	}

	@NotNull
	public String getPresentableName() {
		return myBuilderName;
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
			String groovy = stubToSrc.get(FileUtil.toSystemIndependentName(src.getPath()));
			if (groovy == null) {
				return;
			}
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