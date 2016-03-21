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
import org.jetbrains.jps.model.java.JavaResourceRootProperties;
import org.jetbrains.jps.model.java.JavaResourceRootType;
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

import static java.util.stream.Collectors.toList;
import static org.jetbrains.jps.builders.java.JavaBuilderUtil.isCompileJavaIncrementally;
import static org.jetbrains.jps.incremental.ModuleLevelBuilder.ExitCode.*;
import static org.jetbrains.jps.tara.compiler.CopyResourcesUtil.copy;
import static tara.compiler.constants.TaraBuildConstants.REFRESH_BUILDER_MESSAGE;
import static tara.compiler.constants.TaraBuildConstants.TARAC;

public class TaraBuilder extends ModuleLevelBuilder {

	public static final Key<Set<String>> REMEMBERED_SOURCES = Key.create("STUB_TO_SRC");
	private static final Key<Boolean> CHUNK_REBUILD_ORDERED = Key.create("CHUNK_REBUILD_ORDERED");
	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String GEN = "gen";
	private static final String TARA = ".tara";
	private static final String STASH = ".stash";
	private static final String MODEL = "model";
	private static final String MODEL_TEST = "test-model";
	private static final String DEFINITIONS = "definitions";
	private static final String FUNCTIONS = "functions";
	private static final String RULES = "rules";
	private static final String TEST_RES = "test-res";

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
		long start = System.currentTimeMillis();
		try {
			return doBuild(context, chunk, dirtyFilesHolder, outputConsumer);
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (start > 0 && LOG.isDebugEnabled())
				LOG.debug(builderName + " took " + (System.currentTimeMillis() - start) + " on " + chunk.getName());
			FILES_MARKED_DIRTY_FOR_NEXT_ROUND.set(context, null);
		}
	}

	public ExitCode doBuild(CompileContext context, ModuleChunk chunk, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder, OutputConsumer outputConsumer) throws IOException {
		JpsProject project = context.getProjectDescriptor().getProject();
		final JpsTaraExtensionService service = JpsTaraExtensionService.getInstance();
		JpsTaraFacet facetConfiguration = service.getExtension(chunk.getModules().iterator().next());
		final TaraJpsCompilerSettings settings = service.getSettings(project);
		if (facetConfiguration == null) return NOTHING_DONE;
		Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
		if (finalOutputs == null) return ExitCode.ABORT;
		final List<Map<File, Boolean>> toCompile = collectChangedFiles(chunk.containsTests(), chunk.getModules(), dirtyFilesHolder);
		if (toCompile.stream().filter(this::hasDirtyFiles).count() == 0)
			return hasFilesToCompileForNextRound(context) ? ADDITIONAL_PASS_REQUIRED : NOTHING_DONE;
		final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
		List<String> paths = collectPaths(chunk, finalOutputs, context.getProjectDescriptor().getProject(), facetConfiguration.generatedDsl());
		TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), facetConfiguration, settings.destinyLanguage(), isMake(context), files(toCompile), encoding, chunk.containsTests(), paths);
		final TaracOSProcessHandler handler = runner.runTaraCompiler(context);
		processMessages(chunk, context, handler);
		if (checkChunkRebuildNeeded(context, handler)) return CHUNK_REBUILD_REQUIRED;
		finish(context, chunk, outputConsumer, finalOutputs, handler);
		context.processMessage(new CustomBuilderMessage(TARAC, REFRESH_BUILDER_MESSAGE, facetConfiguration.generatedDsl() + "#" + getOutDir(chunk.getModules().iterator().next())));
		context.setDone(1);
		return hasFilesToCompileForNextRound(context) ? ADDITIONAL_PASS_REQUIRED : OK;
	}

	public boolean isMake(CompileContext context) {
		return isCompileJavaIncrementally(context);
	}

	public void finish(CompileContext context, ModuleChunk chunk, OutputConsumer outputConsumer, Map<ModuleBuildTarget, String> finalOutputs, TaracOSProcessHandler handler) throws IOException {
		Map<ModuleBuildTarget, List<String>> generationOutputs = getStubGenerationOutputs(chunk);
		Map<ModuleBuildTarget, List<OutputItem>> compiled = processCompiledFiles(context, chunk, generationOutputs, generationOutputs.get(chunk.representativeTarget()).get(0), handler.getSuccessfullyCompiled());
		commit(context, chunk, outputConsumer, generationOutputs, finalOutputs, compiled);
	}

	public void commit(CompileContext context,
					   ModuleChunk chunk,
					   OutputConsumer outputConsumer,
					   Map<ModuleBuildTarget, List<String>> generationOutputs,
					   Map<ModuleBuildTarget, String> finalOutputs,
					   Map<ModuleBuildTarget, List<OutputItem>> compiled) throws IOException {
		copyGeneratedStashes(chunk, finalOutputs);
		addStubRootsToJavacSourcePath(context, generationOutputs);
		registerOutputs(outputConsumer, compiled);
		commitToJava(context, compiled);
		rememberStubSources(context, compiled);
	}

	public static void copyGeneratedStashes(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs) {
		for (JpsModule module : chunk.getModules()) {
			final File resourcesDirectory = chunk.containsTests() ? getTestResourcesDirectory(module) : getResourcesDirectory(module);
			if (!resourcesDirectory.exists()) resourcesDirectory.mkdirs();
			for (File file : resourcesDirectory.listFiles((dir, name) -> {
				return name.endsWith(STASH);
			}))
				copy(file, new File(FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()))));
		}
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
			toCompile.addAll(outputItems.stream().map(outputItem -> new File(outputItem.getOutputPath())).collect(toList()));
		JavaBuilderUtil.registerFilesToCompile(context, toCompile);
	}

	private List<Map<File, Boolean>> collectChangedFiles(boolean isTest, Set<JpsModule> modules, DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder) throws IOException {
		final Map<File, Boolean> toCompile = new HashMap<>();
		dirtyFilesHolder.processDirtyFiles((target, file, sourceRoot) -> {
			if (TaraBuilder.this.isTaraFile(file.getPath())) toCompile.put(file, true);
			return true;
		});
		for (JpsModule module : modules)
			module.getSourceRoots().stream().
				filter(root -> isTest ? MODEL_TEST.equals(root.getFile().getName()) : MODEL.equals(root.getFile().getName()) || DEFINITIONS.equals(root.getFile().getName())).
				forEach(root -> collectAllTaraFilesIn(root.getFile(), toCompile));
		return split(toCompile, modules);
	}

	private List<Map<File, Boolean>> split(Map<File, Boolean> toCompile, Set<JpsModule> modules) {
		Map<File, Boolean> model = new LinkedHashMap<>();
		Map<File, Boolean> definitions = new LinkedHashMap<>();
		Map<File, Boolean> tests = new LinkedHashMap<>();
		List<String> modelPaths = new ArrayList<>();
		List<String> definitionsPaths = new ArrayList<>();
		List<String> modelTestsPaths = new ArrayList<>();
		for (JpsModule module : modules) {
			definitionsPaths.addAll(module.getSourceRoots().stream().filter(root -> DEFINITIONS.equals(root.getFile().getName())).map(f -> f.getFile().getPath()).collect(toList()));
			modelPaths.addAll(module.getSourceRoots().stream().filter(root -> MODEL.equals(root.getFile().getName())).map(f -> f.getFile().getPath()).collect(toList()));
			modelTestsPaths.addAll(module.getSourceRoots().stream().filter(root -> MODEL_TEST.equals(root.getFile().getName())).map(f -> f.getFile().getPath()).collect(toList()));
		}
		for (File file : toCompile.keySet()) {
			definitionsPaths.stream().filter(defPath -> file.getPath().startsWith(defPath)).forEach(defPath -> definitions.put(file, toCompile.get(file)));
			modelPaths.stream().filter(modelPath -> file.getPath().startsWith(modelPath)).forEach(modelPath -> model.put(file, toCompile.get(file)));
			modelTestsPaths.stream().filter(testPath -> file.getPath().startsWith(testPath)).forEach(testPath -> tests.put(file, toCompile.get(file)));
		}
		return Arrays.asList(definitions, model, tests);
	}

	private static void rememberStubSources(CompileContext context, Map<ModuleBuildTarget, List<OutputItem>> compiled) {
		Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
		Set<String> outputs = REMEMBERED_SOURCES.get(context);
		if (stubToSrc == null) STUB_TO_SRC.set(context, stubToSrc = new HashMap<>());
		if (outputs == null) REMEMBERED_SOURCES.set(context, outputs = new HashSet<>());
		for (Collection<OutputItem> items : compiled.values()) {
			for (OutputItem item : items) {
				stubToSrc.put(FileUtil.toSystemIndependentName(item.getOutputPath()), item.getSourcePath());
				outputs.add(item.getOutputPath());
			}
		}
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

	public static void collectAllTaraFilesIn(File dir, Map<File, Boolean> fileList) {
		File[] files = dir.listFiles();
		for (File file : files != null ? files : new File[0])
			if (file.getName().endsWith("." + TARA_EXTENSION) && !fileList.containsKey(file)) fileList.put(file, false);
			else if (file.isDirectory()) collectAllTaraFilesIn(file, fileList);
	}

	private static void addStubRootsToJavacSourcePath(CompileContext context, Map<ModuleBuildTarget, List<String>> generationOutputs) {
		final BuildRootIndex rootsIndex = context.getProjectDescriptor().getBuildRootIndex();
		for (Map.Entry<ModuleBuildTarget, List<String>> target : generationOutputs.entrySet())
			for (String value : target.getValue())
				rootsIndex.associateTempRoot(context, target.getKey(), new JavaSourceRootDescriptor(new File(value), target.getKey(), true, false, "", Collections.emptySet()));
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
		final File testResourcesDirectory = getTestResourcesDirectory(modules.iterator().next());
		final File resourcesDirectory = getResourcesDirectory(modules.iterator().next());
		List<String> list = new ArrayList<>();
		list.add(getOutDir(chunk.getModules().iterator().next()));
		list.add(finalOutput);
		list.add(getMagritteLib(chunk));
		list.add(getSrcSourceRoot(modules.iterator().next()).getFile().getAbsolutePath());
		list.add(getDirInSource(modules, generatedDslName, RULES));
		list.add(chunk.containsTests() ? testResourcesDirectory.getPath() : resourcesDirectory.getPath());
		list.add(getDirInSource(chunk.getModules(), generatedDslName, FUNCTIONS));
		list.add(new File(JpsModelSerializationDataService.getBaseDirectory(project), TARA).getAbsolutePath());
		return list;
	}

	private String getDirInSource(Set<JpsModule> modules, String dsl, String name) {
		if (modules.iterator().next() == null) return null;
		final JpsModuleSourceRoot root = getSrcSourceRoot(modules.iterator().next());
		return new File(root.getFile(), dsl.toLowerCase() + "/" + name).getPath();
	}

	private JpsModuleSourceRoot getSrcSourceRoot(JpsModule module) {
		return module.getSourceRoots().stream().filter(root -> "src".equals(root.getFile().getName())).findFirst().get();
	}

	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		handler.getCompilerMessages(chunk.getName()).forEach(context::processMessage);
	}

	private static File getResourcesDirectory(JpsModule module) {
		final Iterator<JpsTypedModuleSourceRoot<JavaResourceRootProperties>> iterator = module.getSourceRoots(JavaResourceRootType.RESOURCE).iterator();
		return iterator.hasNext() ? iterator.next().getFile() : new File(module.getSourceRoots().get(0).getFile().getParentFile(), RES);
	}

	private static File getTestResourcesDirectory(JpsModule module) {
		final Iterator<JpsTypedModuleSourceRoot<JavaResourceRootProperties>> iterator = module.getSourceRoots(JavaResourceRootType.TEST_RESOURCE).iterator();
		return iterator.hasNext() ? iterator.next().getFile() : new File(module.getSourceRoots().get(0).getFile().getParentFile(), TEST_RES);
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

	private List<Map<String, Boolean>> files(List<Map<File, Boolean>> toCompile) {
		return toCompile.stream().map(this::toPath).collect(Collectors.toList());
	}

	private Map<String, Boolean> toPath(Map<File, Boolean> fileBooleanMap) {
		Map<String, Boolean> map = new LinkedHashMap<>();
		for (Map.Entry<File, Boolean> file : fileBooleanMap.entrySet()) {
			if (LOG.isDebugEnabled()) LOG.debug("Path to compile: " + file.getKey().getPath());
			map.put(FileUtil.toSystemIndependentName(file.getKey().getPath()), file.getValue());
		}
		return map;
	}

	private Map<ModuleBuildTarget, List<String>> getStubGenerationOutputs(ModuleChunk chunk) throws IOException {
		Map<ModuleBuildTarget, List<String>> generationOutputs = new HashMap<>();
		File targetRoot = new File(getOutDir(chunk.getModules().iterator().next()));
		targetRoot.mkdirs();
		final ModuleBuildTarget buildTarget = chunk.getTargets().iterator().next();
		add(generationOutputs, buildTarget, targetRoot.getPath());
		File resourcesFileRoot = getResourcesDirectory(chunk.getModules().iterator().next());
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
			filter(file -> file.getPath().contains("proteo")).findFirst().
			map(File::getPath).orElse(null);
	}

	private static class RecompileStubSources implements ClassPostProcessor {

		public void process(CompileContext context, OutputFileObject out) {
			Set<String> sources = REMEMBERED_SOURCES.get(context);
			File src = out.getSourceFile();
			if (src == null || sources == null) return;
			try {
				for (String source : sources) {
					final File file = new File(source);
					if (!FSOperations.isMarkedDirty(context, CompilationRound.CURRENT, file) && file.exists())
						FSOperations.markDirty(context, CompilationRound.CURRENT, file);
					else if (!file.exists())
						FSOperations.markDeleted(context, file);
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}

}