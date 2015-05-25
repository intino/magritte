package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.ProjectPaths;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class TaraBuilder extends ModuleLevelBuilder {

	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String ICONS = "icons";
	private static final String GEN = "gen";
	private static Boolean done = false;
	private final String builderName;
	private boolean javaGeneration;

	public TaraBuilder() {
		super(BuilderCategory.OVERWRITING_TRANSLATOR);
		LOG.setLevel(Level.ALL);
		builderName = "Tara compiler";
	}

	public static boolean isTaraFile(String path) {
		return path.endsWith("." + TARA_EXTENSION);
	}

	public ExitCode build(CompileContext context, ModuleChunk chunk,
	                      DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
	                      OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		long start = 0;
		try {
			if (done) return ExitCode.NOTHING_DONE;
			JpsProject project = context.getProjectDescriptor().getProject();
			JpsTaraSettings settings = JpsTaraSettings.getSettings(project);
			JpsTaraModuleExtension extension = JpsTaraExtensionService.getInstance().getExtension(chunk.getModules().iterator().next());
			if (extension == null) return ExitCode.ABORT;
			javaGeneration = settings.pluginGeneration;
			final List<File> toCompile = collectFiles(chunk.getModules().iterator().next());
			if (toCompile.isEmpty()) return ExitCode.NOTHING_DONE;
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("java-generation = " + javaGeneration);
			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null) return ExitCode.ABORT;
			start = System.currentTimeMillis();
			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
			List<String> paths = collectPaths(chunk, context, finalOutputs);
			paths.add(getNativeInterfacesDir(chunk.getModules(), extension.getGeneratedDslName()));
			TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), extension.getDsl(),
				extension.getGeneratedDslName(), extension.getDictionary(), extension.isPlateRequired(), toCompilePaths, encoding, collectIconDirectories(chunk.getModules()), paths);
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings, javaGeneration);
			processMessages(chunk, context, handler);
			context.setDone(1);
			done = true;
			return ExitCode.OK;
		} catch (Exception e) {
			throw new ProjectBuildException(e);
		} finally {
			if (start > 0 && LOG.isDebugEnabled()) {
				LOG.debug(builderName + " took " + (System.currentTimeMillis() - start) + " on " + chunk.getName());
			}
		}
	}

	@Override
	public List<String> getCompilableFileExtensions() {
		return Collections.singletonList(TARA_EXTENSION);
	}

	private List<String> collectPaths(ModuleChunk chunk, CompileContext context, Map<ModuleBuildTarget, String> finalOutputs) throws IOException {
		Set<JpsModule> modules = chunk.getModules();
		Map<ModuleBuildTarget, String> generationOutputs = javaGeneration ? getStubGenerationOutputs(chunk, context) : finalOutputs;
		String compilerOutput = generationOutputs.get(chunk.representativeTarget()); //TODO replace getOutDir for it
		String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
		List<String> list = new ArrayList<>();
		list.add(getOutDir(chunk.getModules()));
		list.add(finalOutput);
		list.add(getMagritteLib(chunk));
		list.add(getRulesDir(modules));
		list.add(finalOutput);//metrics path
		list.add(getResourcesFile(modules.iterator().next()).getPath());
		return list;
	}

	private static String getMagritteLib(ModuleChunk chunk) {
		return ProjectPaths.getCompilationClasspath(chunk, true).stream().
			filter(file -> file.getPath().contains("magritte")).findFirst().
			map(File::getPath).orElse("Magritte not found");
	}

	private void processMessages(ModuleChunk chunk, CompileContext context, TaracOSProcessHandler handler) {
		handler.getCompilerMessages(chunk.getName()).forEach(context::processMessage);
	}

	private String getNativeInterfacesDir(Set<JpsModule> modules, String dsl) {
		JpsModule module = modules.iterator().next();
		if (module == null) return null;
		return module.getSourceRoots().stream().
			filter(new Predicate<JpsModuleSourceRoot>() {
				@Override
				public boolean test(JpsModuleSourceRoot root) {
					return root.getFile().getName().equals("src") && new File(root.getFile(), dsl.toLowerCase() + "/natives").exists();
				}
			}).findFirst().
			map(new Function<JpsModuleSourceRoot, String>() {
				@Override
				public String apply(JpsModuleSourceRoot root) {
					return new File(root.getFile(), dsl.toLowerCase() + "/natives").getPath();
				}
			}).orElse(null);
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

	public String getOutDir(Set<JpsModule> jpsModules) {
		JpsModule module = jpsModules.iterator().next();
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

	private List<File> collectFiles(JpsModule module) throws IOException {
		final List<File> toCompile = new ArrayList<>();
		for (JpsModuleSourceRoot root : module.getSourceRoots())
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
}