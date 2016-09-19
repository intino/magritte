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
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaBuilderUtil;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.builders.storage.SourceToOutputMapping;
import org.jetbrains.jps.cmdline.ProjectDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.fs.CompilationRound;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.messages.CustomBuilderMessage;
import org.jetbrains.jps.incremental.storage.BuildDataManager;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.java.JavaResourceRootProperties;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.model.module.JpsTypedModuleSourceRoot;
import org.jetbrains.jps.model.serialization.JpsModelSerializationDataService;
import org.jetbrains.jps.tara.compiler.TaracOSProcessHandler.OutputItem;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraFacet;
import org.jetbrains.jps.tara.model.impl.TaraJpsCompilerSettings;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.jetbrains.jps.builders.java.JavaBuilderUtil.isCompileJavaIncrementally;
import static org.jetbrains.jps.incremental.ModuleLevelBuilder.ExitCode.*;
import static org.jetbrains.jps.model.java.JavaSourceRootType.SOURCE;
import static org.jetbrains.jps.model.java.JavaSourceRootType.TEST_SOURCE;
import static org.jetbrains.jps.tara.compiler.CopyResourcesUtil.copy;
import static tara.compiler.constants.TaraBuildConstants.*;

class TaraBuilder extends ModuleLevelBuilder {

	private static final Key<Boolean> CHUNK_REBUILD_ORDERED = Key.create("CHUNK_REBUILD_ORDERED");
	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String GEN = "gen";
	private static final String TEST_RES = "test-res";
	private static final String TEST = "test";
	private static final String TEST_GEN = "test-gen";
	private static final String TARA = ".tara";
	private static final String STASH = ".stash";
	private static final String PROTEO = "proteo";

	private final String builderName;
	private JpsTaraFacet conf;
	private TaraJpsCompilerSettings settings;

	TaraBuilder() {
		super(BuilderCategory.SOURCE_GENERATOR);
		LOG.setLevel(Level.WARN);
		builderName = "Tara compiler";
	}

	public ExitCode build(CompileContext context,
						  ModuleChunk chunk,
						  DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
						  OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		long start = System.currentTimeMillis();
		try {
			final JpsTaraExtensionService service = JpsTaraExtensionService.instance();
			conf = service.getExtension(chunk.getModules().iterator().next());
			settings = service.getSettings(context.getProjectDescriptor().getProject());
			return doBuild(context, chunk, dirtyFilesHolder, outputConsumer);
		} catch (Exception e) {
			System.err.println(e.getCause().getMessage());
			throw new ProjectBuildException(e);
		} finally {
			if (start > 0 && LOG.isDebugEnabled())
				LOG.debug(builderName + " took " + (System.currentTimeMillis() - start) + " on " + chunk.getName());
		}
	}

	private ExitCode doBuild(CompileContext context, ModuleChunk chunk, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder, OutputConsumer outputConsumer) throws IOException {
		JpsProject project = context.getProjectDescriptor().getProject();
		Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
		if (finalOutputs == null) return ExitCode.ABORT;
		final Map<File, Boolean> toCompile = collectChangedFiles(chunk, dirtyFilesHolder);
		if (toCompile.isEmpty()) return NOTHING_DONE;
		final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
		List<String> paths = collectPaths(chunk, finalOutputs, context.getProjectDescriptor().getProject());
		TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), conf, settings.destinyLanguage(), isMake(context), files(toCompile), encoding, chunk.containsTests(), paths);
		final TaracOSProcessHandler handler = runner.runTaraCompiler(context);
		processMessages(chunk, context, handler);
		if (checkChunkRebuildNeeded(context, handler)) return CHUNK_REBUILD_REQUIRED;
		if (handler.shouldRetry()) return ABORT;
		finish(context, chunk, outputConsumer, finalOutputs, handler);
		context.processMessage(new CustomBuilderMessage(TARAC, REFRESH_BUILDER_MESSAGE, outDSLs(conf) + REFRESH_BUILDER_MESSAGE_SEPARATOR + getGenDir(chunk.getModules().iterator().next())));
		context.setDone(1);
		return OK;
	}

	private String outDSLs(JpsTaraFacet conf) {
		String dsls = "";
		if (conf == null) return dsls;
		if (conf.platformOutDsl() != null && !conf.platformOutDsl().isEmpty()) dsls += conf.platformOutDsl();
		if (conf.applicationOutDsl() != null && !conf.applicationOutDsl().isEmpty())
			dsls += REFRESH_BUILDER_MESSAGE_SEPARATOR + conf.applicationOutDsl();
		return dsls;
	}

	private boolean isMake(CompileContext context) {
		return isCompileJavaIncrementally(context);
	}

	private void finish(CompileContext context, ModuleChunk chunk, OutputConsumer outputConsumer, Map<ModuleBuildTarget, String> finalOutputs, TaracOSProcessHandler handler) throws IOException {
		Map<ModuleBuildTarget, List<String>> generationOutputs = getStubGenerationOutputs(chunk);
		Map<ModuleBuildTarget, List<OutputItem>> compiled = processCompiledFiles(context, chunk, generationOutputs, generationOutputs.get(chunk.representativeTarget()).get(0), handler.getSuccessfullyCompiled());
		commit(context, chunk, outputConsumer, finalOutputs, compiled);
	}

	private void commit(CompileContext context,
						ModuleChunk chunk,
						OutputConsumer outputConsumer,
						Map<ModuleBuildTarget, String> finalOutputs,
						Map<ModuleBuildTarget, List<OutputItem>> compiled) throws IOException {
		copyGeneratedStashes(chunk, finalOutputs);
		registerOutputs(context, outputConsumer, compiled);
		removeOldClasses(context, compiled);
	}

	private static void copyGeneratedStashes(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs) {
		for (JpsModule module : chunk.getModules()) {
			final File resourcesDirectory = chunk.containsTests() ? testResourcesDirectory(module) : getResourcesDirectory(module);
			if (!resourcesDirectory.exists()) resourcesDirectory.mkdirs();
			File[] files = resourcesDirectory.listFiles((dir, name) -> name.endsWith(STASH));
			for (File file : files == null ? new File[0] : files)
				copy(file, new File(FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()))));
		}
	}

	private void registerOutputs(CompileContext context, OutputConsumer outputConsumer, Map<ModuleBuildTarget, List<OutputItem>> compiled) throws IOException {
		for (Map.Entry<ModuleBuildTarget, List<OutputItem>> entry : compiled.entrySet())
			for (OutputItem outputItem : entry.getValue()) {
				final File generatedFile = new File(outputItem.getOutputPath());
				outputConsumer.registerOutputFile(entry.getKey(), generatedFile, Collections.singleton(outputItem.getSourcePath()));
				FSOperations.markDirty(context, CompilationRound.CURRENT, generatedFile);
			}
	}

	private void removeOldClasses(CompileContext context, Map<ModuleBuildTarget, List<OutputItem>> compiled) {
		for (Map.Entry<ModuleBuildTarget, List<OutputItem>> entry : compiled.entrySet())
			try {
				BuildDataManager dm = context.getProjectDescriptor().dataManager;
				SourceToOutputMapping mapping = dm.getSourceToOutputMap(entry.getKey());
				for (String source : mapping.getSources()) {
					if (new File(source).exists()) continue;
					mapping.remove(source);
					FSOperations.markDeleted(context, new File(source));
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
	}

	private Map<File, Boolean> collectChangedFiles(ModuleChunk chunk, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder) throws IOException {
		final Map<File, Boolean> toCompile = new LinkedHashMap<>();
		dirtyFilesHolder.processDirtyFiles((target, file, sourceRoot) -> {
			if (isTaraFile(file.getPath())) toCompile.put(file, true);
			return true;
		});
		if (chunk.containsTests() || toCompile.isEmpty()) return toCompile;
		for (JpsModule module : chunk.getModules())
			module.getSourceRoots().stream().filter(s -> s.getRootType().equals(SOURCE)).forEach(root -> collectAllTaraFilesIn(root.getFile(), toCompile));
		return toCompile;
	}

	private static boolean checkChunkRebuildNeeded(CompileContext context, TaracOSProcessHandler handler) {
		if (JavaBuilderUtil.isForcedRecompilationAllJavaModules(context) || !handler.shouldRetry()) return false;
		if (CHUNK_REBUILD_ORDERED.get(context) != null) {
			CHUNK_REBUILD_ORDERED.set(context, null);
			return false;
		}
		CHUNK_REBUILD_ORDERED.set(context, Boolean.TRUE);
		LOG.info("Order chunk rebuild");
		return true;
	}

	private void collectAllTaraFilesIn(File dir, Map<File, Boolean> fileList) {
		File[] files = dir.listFiles();
		for (File file : files != null ? files : new File[0])
			if (isTaraFile(file.getPath()) && !fileList.containsKey(file)) fileList.put(file, false);
			else if (file.isDirectory()) collectAllTaraFilesIn(file, fileList);
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

	private static void processOutputItem(CompileContext context,
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

	private List<String> collectPaths(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs, JpsProject project) throws IOException {
		final JpsModule module = chunk.getModules().iterator().next();
		String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
		final File testResourcesDirectory = testResourcesDirectory(module);
		final File resourcesDirectory = getResourcesDirectory(module);
		List<String> list = new ArrayList<>();
		final JpsModuleSourceRoot testGen = getTestGenRoot(module);
		list.add(chunk.containsTests() ? testGen == null ? createTestGen(module).getAbsolutePath() : testGen.getFile().getAbsolutePath() : getGenDir(module));
		list.add(finalOutput);
		list.add(proteoLib(chunk));
		list.add(chunk.containsTests() ? testResourcesDirectory.getPath() : resourcesDirectory.getPath());
		list.add(new File(new File(System.getProperty("user.home")), TARA).getAbsolutePath());
		list.add(new File(JpsModelSerializationDataService.getBaseDirectory(project), TARA).getAbsolutePath());
		final JpsModuleSourceRoot testSourceRoot = getTestSourceRoot(module);
		if (chunk.containsTests()) list.add(testSourceRoot != null ? testSourceRoot.getFile().getAbsolutePath() : null);
		else list.addAll(getSourceRoots(module).stream().map(root -> root.getFile().getAbsolutePath()).collect(Collectors.toList()));
		return list;
	}

	private List<JpsModuleSourceRoot> getSourceRoots(JpsModule module) {
		return module.getSourceRoots().stream().filter(root -> root.getRootType().equals(SOURCE) && !((JavaSourceRootProperties) root.getProperties()).isForGeneratedSources()).collect(Collectors.toList());
	}

	private JpsModuleSourceRoot getTestSourceRoot(JpsModule module) {
		return module.getSourceRoots().stream().filter(root -> root.getRootType().equals(TEST_SOURCE) && TEST.equals(root.getFile().getName())).findFirst().orElse(null);
	}

	@Nullable
	private JpsModuleSourceRoot getTestGenRoot(JpsModule module) {
		return module.getSourceRoots().stream().filter(root -> root.getRootType().equals(TEST_SOURCE) && TEST_GEN.equals(root.getFile().getName())).findFirst().orElse(null);
	}

	private File createTestGen(JpsModule root) {
		final File file = new File(root.getSourceRoots().get(0).getFile().getParentFile(), TEST_GEN);
		file.mkdirs();
		return file;
	}

	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		handler.getCompilerMessages(chunk.getName()).forEach(context::processMessage);
	}

	private static File getResourcesDirectory(JpsModule module) {
		final Iterator<JpsTypedModuleSourceRoot<JavaResourceRootProperties>> iterator = module.getSourceRoots(JavaResourceRootType.RESOURCE).iterator();
		return iterator.hasNext() ? iterator.next().getFile() : new File(module.getSourceRoots().get(0).getFile().getParentFile(), RES);
	}

	private static File testResourcesDirectory(JpsModule module) {
		final Iterator<JpsTypedModuleSourceRoot<JavaResourceRootProperties>> iterator = module.getSourceRoots(JavaResourceRootType.TEST_RESOURCE).iterator();
		return iterator.hasNext() ? iterator.next().getFile() : new File(module.getSourceRoots().get(0).getFile().getParentFile(), TEST_RES);
	}

	private String getGenDir(JpsModule module) {
		for (JpsModuleSourceRoot moduleSourceRoot : module.getSourceRoots())
			if (GEN.equals(moduleSourceRoot.getFile().getName())) return moduleSourceRoot.getFile().getAbsolutePath();
		File moduleFile = module.getSourceRoots().get(0).getFile().getParentFile();
		File gen = new File(moduleFile, GEN);
		gen.mkdir();
		return gen.getAbsolutePath();
	}

	private Map<String, Boolean> files(Map<File, Boolean> toCompile) {
		Map<String, Boolean> map = new LinkedHashMap<>();
		for (Map.Entry<File, Boolean> file : toCompile.entrySet()) {
			if (LOG.isDebugEnabled()) LOG.debug("Path to compile: " + file.getKey().getPath());
			map.put(FileUtil.toSystemIndependentName(file.getKey().getPath()), file.getValue());
		}
		return map;
	}

	private Map<ModuleBuildTarget, List<String>> getStubGenerationOutputs(ModuleChunk chunk) throws IOException {
		Map<ModuleBuildTarget, List<String>> generationOutputs = new HashMap<>();
		File targetRoot = new File(getGenDir(chunk.getModules().iterator().next()));
		targetRoot.mkdirs();
		final ModuleBuildTarget buildTarget = chunk.getTargets().iterator().next();
		add(generationOutputs, buildTarget, targetRoot.getPath());
		File resourcesFileRoot = getResourcesDirectory(chunk.getModules().iterator().next());
		resourcesFileRoot.mkdirs();
		add(generationOutputs, buildTarget, resourcesFileRoot.getPath());
		return generationOutputs;
	}

	private void add(Map<ModuleBuildTarget, List<String>> outputs, ModuleBuildTarget target, String path) {
		outputs.putIfAbsent(target, new ArrayList<>());
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
	}

	@Override
	public void chunkBuildFinished(CompileContext context, ModuleChunk chunk) {
		JavaBuilderUtil.cleanupChunkResources(context);
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
		if (conf == null) return false;
		for (String language : conf.supportedLanguages())
			if (path.endsWith("." + language)) return true;
		return false;
	}

	private String proteoLib(ModuleChunk chunk) {
		return ProjectPaths.getCompilationClasspath(chunk, true).stream().
			filter(file -> file.getPath().contains(PROTEO)).findFirst().
			map(File::getPath).orElse(null);
	}
}