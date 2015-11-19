package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import gnu.trove.THashMap;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.ProjectPaths;
import org.jetbrains.jps.builders.BuildRootIndex;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaBuilderUtil;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.cmdline.ProjectDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.fs.CompilationRound;
import org.jetbrains.jps.incremental.java.ClassPostProcessor;
import org.jetbrains.jps.incremental.java.JavaBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.messages.CustomBuilderMessage;
import org.jetbrains.jps.javac.OutputFileObject;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.model.serialization.JpsModelSerializationDataService;
import org.jetbrains.jps.tara.compiler.TaracOSProcessHandler.OutputItem;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static tara.compiler.constants.TaraBuildConstants.FILE_INVALIDATION_BUILDER_MESSAGE;
import static tara.compiler.constants.TaraBuildConstants.TARAC;

public class TaraBuilder extends ModuleLevelBuilder {

	public static final Key<Set<String>> REMEMBERED_SOURCES = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> CHUNK_REBUILD_ORDERED = Key.create("CHUNK_REBUILD_ORDERED");
	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String ICONS = "icons";
	private static final String GEN = "gen";
	private static final String TARA = ".tara";
	private static final String MODEL = "model";

	private final String builderName;

	public TaraBuilder() {
		super(BuilderCategory.SOURCE_GENERATOR);
		LOG.setLevel(Level.WARN);
		builderName = "Tara compiler";
	}

	static {
		JavaBuilder.registerClassPostProcessor(new RecompileStubSources());
	}

	public ExitCode build(CompileContext context,
	                      ModuleChunk chunk,
	                      DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
	                      OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		long start = 0;
		try {
			JpsProject project = context.getProjectDescriptor().getProject();
			JpsTaraSettings settings = JpsTaraSettings.getSettings(project);
			JpsTaraModuleExtension extension = JpsTaraExtensionService.getInstance().getExtension(chunk.getModules().iterator().next());
			if (extension == null) return ExitCode.NOTHING_DONE;
			Map<ModuleBuildTarget, List<String>> generationOutputs = getStubGenerationOutputs(chunk);
			String compilerOutput = generationOutputs.get(chunk.representativeTarget()).get(0);
			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null) return ExitCode.ABORT;
			final Map<File, Boolean> toCompile = collectChangedFiles(chunk.getModules(), dirtyFilesHolder);
			if (!hasDirtyFiles(toCompile))
				return hasFilesToCompileForNextRound(context) ? ExitCode.ADDITIONAL_PASS_REQUIRED : ExitCode.NOTHING_DONE;
			start = System.currentTimeMillis();
			final Map<String, Boolean> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
			List<String> paths = collectPaths(chunk, finalOutputs, context.getProjectDescriptor().getProject(), extension.getGeneratedDslName());
			TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), extension.getDsl(),
				extension.getGeneratedDslName(), extension.getLevel(), extension.customMorphs(), extension.isDynamicLoad(), JavaBuilderUtil.isCompileJavaIncrementally(context),toCompilePaths, encoding, collectIconDirectories(chunk.getModules()), paths);
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings);
			if (checkChunkRebuildNeeded(context, handler)) return ExitCode.CHUNK_REBUILD_REQUIRED;
			Map<ModuleBuildTarget, List<OutputItem>> compiled = processCompiledFiles(context, chunk, generationOutputs, compilerOutput, handler.getSuccessfullyCompiled());
			addStubRootsToJavacSourcePath(context, generationOutputs);
			copyResources(chunk, finalOutputs);
			registerOutputs(outputConsumer, compiled);
			commitToJava(context, compiled);
			rememberStubSources(context, compiled);
			processMessages(chunk, context, handler);
			context.processMessage(new CustomBuilderMessage(TARAC, FILE_INVALIDATION_BUILDER_MESSAGE, getOutDir(chunk.getModules().iterator().next())));
			context.setDone(1);
			return hasFilesToCompileForNextRound(context) ? ExitCode.ADDITIONAL_PASS_REQUIRED : ExitCode.OK;
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (start > 0 && LOG.isDebugEnabled())
				LOG.debug(builderName + " took " + (System.currentTimeMillis() - start) + " on " + chunk.getName());
			FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, null);
		}
	}

	public static void copyResources(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs) {
		for (JpsModule module : chunk.getModules())
			CopyResourcesUtil.copy(getResourcesFile(module), new File(FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()))));
	}

	private boolean hasDirtyFiles(Map<File, Boolean> toCompile) {
		return toCompile.values().contains(Boolean.TRUE);
	}

	private void registerOutputs(OutputConsumer outputConsumer, Map<ModuleBuildTarget, List<OutputItem>> compiled) throws IOException {
		for (Map.Entry<ModuleBuildTarget, List<OutputItem>> entry : compiled.entrySet())
			for (OutputItem outputItem : entry.getValue())
				outputConsumer.registerOutputFile(entry.getKey(), new File(outputItem.getOutputPath()), Collections.singleton(outputItem.getSourcePath()));
	}

	public void commitToJava(CompileContext context, Map<ModuleBuildTarget, List<OutputItem>> successfullyCompiled) throws IOException {
		List<File> toCompile = new ArrayList<>();
		for (Collection<OutputItem> outputItems : successfullyCompiled.values())
			toCompile.addAll(outputItems.stream().map(outputItem -> new File(outputItem.getOutputPath())).collect(Collectors.toList()));
		JavaBuilderUtil.registerFilesToCompile(context, toCompile);
	}

	private Map<File, Boolean> collectChangedFiles(Set<JpsModule> modules, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder) throws IOException {
		final Map<File, Boolean> toCompile = new HashMap<>();
		dirtyFilesHolder.processDirtyFiles((target, file, sourceRoot) -> {
			if (TaraBuilder.this.isTaraFile(file.getPath())) toCompile.put(file, true);
			return true;
		});
		for (JpsModule module : modules)
			module.getSourceRoots().stream().filter(root -> MODEL.equals(root.getFile().getName())).forEach(root -> collectAllTaraFilesIn(root.getFile(), toCompile));
		return toCompile;
	}

	private static void rememberStubSources(CompileContext context, Map<ModuleBuildTarget, List<OutputItem>> compiled) {
		Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
		Set<String> outputs = REMEMBERED_SOURCES.get(context);
		if (stubToSrc == null) {
			STUB_TO_SRC.set(context, stubToSrc = new HashMap<>());
		}
		if (outputs == null) {
			REMEMBERED_SOURCES.set(context, outputs = new HashSet<>());
		}
		for (Collection<OutputItem> items : compiled.values()) {
			for (OutputItem item : items) {
				stubToSrc.put(FileUtil.toSystemIndependentName(item.getOutputPath()), item.getSourcePath());
				outputs.add(item.getOutputPath());
			}
		}
	}

	private static boolean checkChunkRebuildNeeded(CompileContext context, TaracOSProcessHandler parser) {
		if (JavaBuilderUtil.isForcedRecompilationAllJavaModules(context) || !parser.shouldRetry()) {
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

	public static void collectAllTaraFilesIn(File dir, Map<File, Boolean> fileList) {
		File[] files = dir.listFiles();
		for (File file : files != null ? files : new File[0]) {
			if (file.getName().endsWith("." + TARA_EXTENSION) && !fileList.containsKey(file)) fileList.put(file, false);
			if (file.isDirectory()) collectAllTaraFilesIn(file, fileList);
		}
	}

	private static void addStubRootsToJavacSourcePath(CompileContext context, Map<ModuleBuildTarget, List<String>> generationOutputs) {
		final BuildRootIndex rootsIndex = context.getProjectDescriptor().getBuildRootIndex();
		for (Map.Entry<ModuleBuildTarget, List<String>> target : generationOutputs.entrySet()) {
			for (String value : target.getValue()) {
				final JavaSourceRootDescriptor sourceRoot = new JavaSourceRootDescriptor(new File(value), target.getKey(), true, false, "", Collections.emptySet());
				rootsIndex.associateTempRoot(context, target.getKey(), sourceRoot);
			}
		}
	}

	private Boolean hasFilesToCompileForNextRound(CompileContext context) {
		return FILES_MARKED_DIRTY_FOR_NEXT_ROUND.get(context, Boolean.FALSE);
	}

	private static Map<ModuleBuildTarget, List<OutputItem>> processCompiledFiles(CompileContext context,
	                                                                             ModuleChunk chunk,
	                                                                             Map<ModuleBuildTarget, List<String>> generationOutputs,
	                                                                             String compilerOutput,
	                                                                             List<OutputItem> successfullyCompiled) throws IOException {
		ProjectDescriptor pd = context.getProjectDescriptor();
		final Map<ModuleBuildTarget, List<OutputItem>> compiled = new THashMap<>();
		for (final OutputItem item : successfullyCompiled)
			processOutputItem(context, chunk, generationOutputs, compilerOutput, pd, compiled, item);
		if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("Chunk " + chunk + " compilation finished");
		return compiled;
	}

	public static void processOutputItem(CompileContext context,
	                                     ModuleChunk chunk,
	                                     Map<ModuleBuildTarget, List<String>> generationOutputs,
	                                     String compilerOutput, ProjectDescriptor pd,
	                                     Map<ModuleBuildTarget, List<OutputItem>> compiled,
	                                     OutputItem item) throws IOException {
		if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("compiled=" + item);
		final JavaSourceRootDescriptor rd = pd.getBuildRootIndex().findJavaRootDescriptor(context, new File(item.getSourcePath()));
		if (rd != null) {
			ensureCorrectOutput(chunk, item, generationOutputs, compilerOutput, rd.target);
			List<OutputItem> items = compiled.get(rd.target);
			if (items == null) {
				items = new ArrayList<>();
				compiled.put(rd.target, items);
			}
			if (new File(item.getOutputPath()).exists())
				items.add(new OutputItem(item.getOutputPath(), item.getSourcePath()));
		} else if (Utils.IS_TEST_MODE || LOG.isDebugEnabled())
			LOG.info("No java source root descriptor for the item found =" + item);
	}

	private static String ensureCorrectOutput(ModuleChunk chunk,
	                                          OutputItem item,
	                                          Map<ModuleBuildTarget, List<String>> generationOutputs,
	                                          String compilerOutput,
	                                          @NotNull ModuleBuildTarget srcTarget) throws IOException {
		if (chunk.getModules().size() > 1 && !srcTarget.equals(chunk.representativeTarget())) {
			File output = new File(item.getSourcePath());
			String srcTargetOutput = generationOutputs.get(srcTarget).get(0);
			if (srcTargetOutput == null) {
				LOG.info("No output for " + srcTarget + "; outputs=" + generationOutputs + "; targets = " + chunk.getTargets());
				return item.getSourcePath();
			}
			File correctRoot = new File(srcTargetOutput);
			File correctOutput = new File(correctRoot, FileUtil.getRelativePath(new File(compilerOutput), output));
			FileUtil.rename(output, correctOutput);
			return correctOutput.getPath();
		}
		return item.getSourcePath();
	}

	@Override
	public List<String> getCompilableFileExtensions() {
		return Collections.singletonList(TARA_EXTENSION);
	}

	private List<String> collectPaths(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs, JpsProject project, String generatedDslName) throws IOException {
		Set<JpsModule> modules = chunk.getModules();
		String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
		List<String> list = new ArrayList<>();
		list.add(getOutDir(chunk.getModules().iterator().next()));
		list.add(finalOutput);
		list.add(getMagritteLib(chunk));
		list.add(getRulesDir(modules));
		list.add(getDirInSource(modules, generatedDslName, "rules"));
		list.add(getResourcesFile(modules.iterator().next()).getPath());
		list.add(getDirInSource(chunk.getModules(), generatedDslName, "natives"));
		list.add(new File(JpsModelSerializationDataService.getBaseDirectory(project), TARA).getAbsolutePath());
		return list;
	}

	private String getDirInSource(Set<JpsModule> modules, String dsl, String name) {
		JpsModule module = modules.iterator().next();
		String directory = "/" + name;
		if (module == null) return null;
		return module.getSourceRoots().stream().
			filter(root -> "src".equals(root.getFile().getName()) && new File(root.getFile(), dsl.toLowerCase() + directory).exists()).findFirst().
			map(root -> new File(root.getFile(), dsl.toLowerCase() + directory).getPath()).orElse(null);
	}

	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		handler.getCompilerMessages(chunk.getName()).forEach(context::processMessage);
	}

	private String[] collectIconDirectories(Set<JpsModule> jpsModules) {
		ArrayList<String> iconDirectories = new ArrayList<>();
		for (JpsModule module : jpsModules) {
			File res = getResourcesFile(module);
			if (res.exists()) {
				File icons = new File(res, ICONS);
				if (icons.exists()) iconDirectories.add(icons.getAbsolutePath());
			}
		}
		return iconDirectories.toArray(new String[iconDirectories.size()]);
	}

	private static File getResourcesFile(JpsModule module) {
		return new File(module.getSourceRoots().get(0).getFile().getParentFile(), RES);
	}

	public String getRulesDir(Set<JpsModule> jpsModules) {
		for (JpsModule module : jpsModules) {
			File res = getResourcesFile(module);
			if (res.exists()) {
				File file = new File(res, "itrules");
				if (file.exists()) return file.getAbsolutePath();
			}
		}
		return null;
	}

	private String getOutDir(JpsModule module) {
		for (JpsModuleSourceRoot moduleSourceRoot : module.getSourceRoots())
			if (moduleSourceRoot.getFile().getName().equals(GEN))
				return moduleSourceRoot.getFile().getAbsolutePath();
		File moduleFile = module.getSourceRoots().get(0).getFile().getParentFile();
		File gen = new File(moduleFile, GEN);
		gen.mkdir();
		return gen.getAbsolutePath();
	}

	private Map<String, Boolean> getPathsToCompile(Map<File, Boolean> toCompile) {
		final Map<String, Boolean> toCompilePaths = new LinkedHashMap<>();
		for (Map.Entry<File, Boolean> file : toCompile.entrySet()) {
			if (LOG.isDebugEnabled()) LOG.debug("Path to compile: " + file.getKey().getPath());
			toCompilePaths.put(FileUtil.toSystemIndependentName(file.getKey().getPath()), file.getValue());
		}
		return toCompilePaths;
	}

	private Map<ModuleBuildTarget, List<String>> getStubGenerationOutputs(ModuleChunk chunk) throws IOException {
		Map<ModuleBuildTarget, List<String>> generationOutputs = new HashMap<>();
		File targetRoot = new File(getOutDir(chunk.getModules().iterator().next()));
		targetRoot.mkdirs();
		final ModuleBuildTarget buildTarget = chunk.getTargets().iterator().next();
		add(generationOutputs, buildTarget, targetRoot.getPath());
		File resourcesFileRoot = getResourcesFile(chunk.getModules().iterator().next());
		resourcesFileRoot.mkdirs();
		add(generationOutputs, buildTarget, resourcesFileRoot.getPath());
		return generationOutputs;
	}

	private void add(Map<ModuleBuildTarget, List<String>> outputs, ModuleBuildTarget target, String path) {
		if (outputs.get(target) == null) outputs.put(target, new ArrayList<>());
		outputs.get(target).add(path);
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
	public void buildStarted(CompileContext context) {
		File stubRoot = getStubRoot(context);
		if (stubRoot.exists() && !FileUtil.deleteWithRenaming(stubRoot))
			context.processMessage(new CompilerMessage(builderName, BuildMessage.Kind.ERROR, "External make cannot clean " + stubRoot.getPath()));
	}


	@Override
	public void chunkBuildFinished(CompileContext context, ModuleChunk chunk) {
		JavaBuilderUtil.cleanupChunkResources(context);
		STUB_TO_SRC.set(context, null);
	}

	private static File getStubRoot(CompileContext context) {
		return new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "taraStubs");
	}

	@Override
	public String toString() {
		return builderName;
	}

	@NotNull
	public String getPresentableName() {
		return builderName;
	}

	private boolean isTaraFile(String path) {
		return path.endsWith("." + TARA_EXTENSION);
	}

	private String getMagritteLib(ModuleChunk chunk) {
		return ProjectPaths.getCompilationClasspath(chunk, true).stream().
			filter(file -> file.getPath().contains("Proteo")).findFirst().
			map(File::getPath).orElse(null);
	}

	private static class RecompileStubSources implements ClassPostProcessor {

		public void process(CompileContext context, OutputFileObject out) {
			Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
			Set<String> srcs = REMEMBERED_SOURCES.get(context);
			if (stubToSrc == null) return;
			File src = out.getSourceFile();
			if (src == null) return;
			String tara = stubToSrc.get(FileUtil.toSystemIndependentName(src.getPath()));
			try {
				for (String s : srcs)
					if (!FSOperations.isMarkedDirty(context, CompilationRound.CURRENT, new File(s)))
						FSOperations.markDirty(context, CompilationRound.CURRENT, new File(s));
				if (tara == null) return;
				final File taraFile = new File(tara);
				if (!FSOperations.isMarkedDirty(context, CompilationRound.CURRENT, taraFile)) {
					FSOperations.markDirty(context, CompilationRound.NEXT, taraFile);
					FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, Boolean.TRUE);
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}

}