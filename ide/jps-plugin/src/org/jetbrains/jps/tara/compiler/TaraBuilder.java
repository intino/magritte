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
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.incremental.messages.CustomBuilderMessage;
import org.jetbrains.jps.javac.OutputFileObject;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.model.serialization.JpsModelSerializationDataService;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static tara.compiler.constants.TaraBuildConstants.FILE_INVALIDATION_BUILDER_MESSAGE;
import static tara.compiler.constants.TaraBuildConstants.TARAC;

public class TaraBuilder extends ModuleLevelBuilder {

	private static final Key<Boolean> FILES_MARKED_DIRTY_FOR_NEXT_ROUND = Key.create("SRC_MARKED_DIRTY");
	private static final Key<Map<String, String>> STUB_TO_SRC = Key.create("STUB_TO_SRC");
	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String ICONS = "icons";
	private static final String GEN = "gen";
	private static final String DSL = "dsl";

	private final String builderName;

	public TaraBuilder() {
		super(BuilderCategory.SOURCE_GENERATOR);
		LOG.setLevel(Level.ALL);
		builderName = "Tara compiler";
	}

//	static {
//		JavaBuilder.registerClassPostProcessor(new RecompileStubSources());
//	}

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
			Map<ModuleBuildTarget, String> generationOutputs = getStubGenerationOutputs(chunk);
			String compilerOutput = generationOutputs.get(chunk.representativeTarget());
			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null) return ExitCode.ABORT;
			final List<File> toCompile = collectChangedFiles(dirtyFilesHolder);
			if (toCompile.isEmpty()) return ExitCode.OK;
			start = System.currentTimeMillis();
			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
			List<String> paths = collectPaths(chunk, finalOutputs);
			paths.add(getNativeInterfacesDir(chunk.getModules(), extension.getGeneratedDslName()));
			paths.add(new File(JpsModelSerializationDataService.getBaseDirectory(context.getProjectDescriptor().getProject()), DSL).getAbsolutePath());
			TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), extension.getDsl(),
				extension.getGeneratedDslName(), extension.getLevel(), extension.getDictionary(), extension.isPlateRequired(), toCompilePaths, encoding, collectIconDirectories(chunk.getModules()), paths);
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings);
			Map<ModuleBuildTarget, List<TaracOSProcessHandler.OutputItem>>
				compiled = processCompiledFiles(context, chunk, generationOutputs, compilerOutput, handler.getSuccessfullyCompiled());
			addStubRootsToJavacSourcePath(context, generationOutputs);
			rememberStubSources(context, compiled);
			processMessages(chunk, context, handler);
			updateDependencies(context, compiled);
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

	public void updateDependencies(CompileContext context,
	                               Map<ModuleBuildTarget, List<TaracOSProcessHandler.OutputItem>> successfullyCompiled) throws IOException {
		List<File> toCompile = new ArrayList<>();
		for (Collection<TaracOSProcessHandler.OutputItem> outputItems : successfullyCompiled.values())
			toCompile.addAll(outputItems.stream().map(outputItem -> new File(outputItem.getOutputPath())).collect(Collectors.toList()));
		JavaBuilderUtil.registerFilesToCompile(context, toCompile);
	}

	private static void addStubRootsToJavacSourcePath(CompileContext context, Map<ModuleBuildTarget, String> generationOutputs) {
		final BuildRootIndex rootsIndex = context.getProjectDescriptor().getBuildRootIndex();
		for (ModuleBuildTarget target : generationOutputs.keySet()) {
			File root = new File(generationOutputs.get(target));
			rootsIndex.associateTempRoot(context, target, new JavaSourceRootDescriptor(root, target, true, false, "", Collections.emptySet()));
		}
	}

	private Boolean hasFilesToCompileForNextRound(CompileContext context) {
		return FILES_MARKED_DIRTY_FOR_NEXT_ROUND.get(context, Boolean.FALSE);
	}

	private static void rememberStubSources(CompileContext context, Map<ModuleBuildTarget, List<TaracOSProcessHandler.OutputItem>> compiled) {
		Map<String, String> stubToSrc = STUB_TO_SRC.get(context);
		if (stubToSrc == null) STUB_TO_SRC.set(context, stubToSrc = new HashMap<>());
		for (Collection<TaracOSProcessHandler.OutputItem> items : compiled.values())
			for (TaracOSProcessHandler.OutputItem item : items)
				stubToSrc.put(FileUtil.toSystemIndependentName(item.getOutputPath()), item.getSourcePath());
	}

	public static Map<ModuleBuildTarget, List<TaracOSProcessHandler.OutputItem>> processCompiledFiles(CompileContext context,
	                                                                                                  ModuleChunk chunk,
	                                                                                                  Map<ModuleBuildTarget, String> generationOutputs,
	                                                                                                  String compilerOutput,
	                                                                                                  List<TaracOSProcessHandler.OutputItem> successfullyCompiled)
		throws IOException {
		ProjectDescriptor pd = context.getProjectDescriptor();

		final Map<ModuleBuildTarget, List<TaracOSProcessHandler.OutputItem>> compiled = new THashMap<>();
		for (final TaracOSProcessHandler.OutputItem item : successfullyCompiled) {
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("compiled=" + item);
			final JavaSourceRootDescriptor rd = pd.getBuildRootIndex().findJavaRootDescriptor(context, new File(item.getSourcePath()));
			if (rd != null) {
				ensureCorrectOutput(chunk, item, generationOutputs, compilerOutput, rd.target);
				List<TaracOSProcessHandler.OutputItem> items = compiled.get(rd.target);
				if (items == null) {
					items = new ArrayList<>();
					compiled.put(rd.target, items);
				}
				if (new File(item.getOutputPath()).exists())
					items.add(new TaracOSProcessHandler.OutputItem(item.getOutputPath(), item.getSourcePath()));
			} else if (Utils.IS_TEST_MODE || LOG.isDebugEnabled())
				LOG.info("No java source root descriptor for the item found =" + item);
		}
		if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("Chunk " + chunk + " compilation finished");
		return compiled;
	}

	private static String ensureCorrectOutput(ModuleChunk chunk,
	                                          TaracOSProcessHandler.OutputItem item,
	                                          Map<ModuleBuildTarget, String> generationOutputs,
	                                          String compilerOutput,
	                                          @NotNull ModuleBuildTarget srcTarget) throws IOException {
		if (chunk.getModules().size() > 1 && !srcTarget.equals(chunk.representativeTarget())) {
			File output = new File(item.getSourcePath());
			String srcTargetOutput = generationOutputs.get(srcTarget);
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

	private List<String> collectPaths(ModuleChunk chunk, Map<ModuleBuildTarget, String> finalOutputs) throws IOException {
		Set<JpsModule> modules = chunk.getModules();
		Map<ModuleBuildTarget, String> generationOutputs = getStubGenerationOutputs(chunk);
		String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
		List<String> list = new ArrayList<>();
		list.add(getOutDir(chunk.getModules().iterator().next()));
		list.add(finalOutput);
		list.add(getMagritteLib(chunk));
		list.add(getRulesDir(modules));
		list.add(finalOutput);//metrics path
		list.add(getResourcesFile(modules.iterator().next()).getPath());
		return list;
	}

	List<File> collectChangedFiles(DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder) throws IOException {
		final List<File> toCompile = new ArrayList<>();
		dirtyFilesHolder.processDirtyFiles((target, file, sourceRoot) -> {
			if ((isTaraFile(file.getPath()))) toCompile.add(file);
			return true;
		});
		return toCompile;
	}


	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		handler.getCompilerMessages(chunk.getName()).forEach(context::processMessage);
	}

	private String getNativeInterfacesDir(Set<JpsModule> modules, String dsl) {
		JpsModule module = modules.iterator().next();
		if (module == null) return null;
		return module.getSourceRoots().stream().
			filter(root -> root.getFile().getName().equals("src") && new File(root.getFile(), dsl.toLowerCase() + "/natives").exists()).findFirst().
			map(root -> new File(root.getFile(), dsl.toLowerCase() + "/natives").getPath()).orElse(null);
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

	private File getResourcesFile(JpsModule module) {
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

	public String getOutDir(JpsModule module) {
		for (JpsModuleSourceRoot moduleSourceRoot : module.getSourceRoots())
			if (moduleSourceRoot.getFile().getName().equals(GEN))
				return moduleSourceRoot.getFile().getAbsolutePath();
		File moduleFile = module.getSourceRoots().get(0).getFile().getParentFile();
		File gen = new File(moduleFile, GEN);
		gen.mkdir();
		return gen.getAbsolutePath();
	}

	private Set<String> getPathsToCompile(List<File> toCompile) {
		final Set<String> toCompilePaths = new LinkedHashSet<>();
		for (File file : toCompile) {
			if (LOG.isDebugEnabled()) LOG.debug("Path to compile: " + file.getPath());
			toCompilePaths.add(FileUtil.toSystemIndependentName(file.getPath()));
		}
		return toCompilePaths;
	}

	private Map<ModuleBuildTarget, String> getStubGenerationOutputs(ModuleChunk chunk) throws IOException {
		Map<ModuleBuildTarget, String> generationOutputs = new HashMap<>();
		File targetRoot = new File(getOutDir(chunk.getModules().iterator().next()));
		targetRoot.mkdirs();
		generationOutputs.put(chunk.getTargets().iterator().next(), targetRoot.getPath());
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
	public void buildStarted(CompileContext context) {
		File stubRoot = getStubRoot(context);
		if (stubRoot.exists() && !FileUtil.deleteWithRenaming(stubRoot))
			context.processMessage(new CompilerMessage(builderName, BuildMessage.Kind.ERROR, "External make cannot clean " + stubRoot.getPath()));
	}


	@Override
	public void chunkBuildStarted(CompileContext context, ModuleChunk chunk) {
		super.chunkBuildStarted(context, chunk);
	}

	@Override
	public void chunkBuildFinished(CompileContext context, ModuleChunk chunk) {
		JavaBuilderUtil.cleanupChunkResources(context);
		STUB_TO_SRC.set(context, null);
	}

	private static File getStubRoot(CompileContext context) {
		return new File(context.getProjectDescriptor().dataManager.getDataPaths().getDataStorageRoot(), "groovyStubs");
	}

	@Override
	public String toString() {
		return builderName;
	}

	@NotNull
	public String getPresentableName() {
		return builderName;
	}

	public boolean isTaraFile(String path) {
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
			if (stubToSrc == null) return;
			File src = out.getSourceFile();
			if (src == null) return;
			String tara = stubToSrc.get(FileUtil.toSystemIndependentName(src.getPath()));
			if (tara == null) return;
			try {
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