package org.jetbrains.jps.incremental.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.java.JpsJavaSdkType;
import org.jetbrains.jps.model.library.sdk.JpsSdk;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaraBuilder extends ModuleLevelBuilder {

	private static final Logger LOG = Logger.getInstance(TaraBuilder.class.getName());
	private static final String TARA_EXTENSION = "tara";
	private static final String RES = "res";
	private static final String ICONS = "icons";
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

	private static JpsSdk<JpsDummyElement> getMagritteJdk(ModuleChunk chunk) {
		return chunk.getModules().iterator().next().getSdk(JpsJavaSdkType.INSTANCE);
	}

	@Override
	public List<String> getCompilableFileExtensions() {
		return Arrays.asList(TARA_EXTENSION);
	}

	public ExitCode build(CompileContext context, ModuleChunk chunk,
	                      DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
	                      OutputConsumer outputConsumer) throws ProjectBuildException, IOException {
		long start = 0;
		try {
			if (done) return ExitCode.NOTHING_DONE;
			done = true;
			JpsProject project = context.getProjectDescriptor().getProject();
			JpsTaraSettings settings = JpsTaraSettings.getSettings(project);
			javaGeneration = settings.pluginGeneration;
			final List<File> toCompile = collectFiles(chunk.getModules().iterator().next());
			if (toCompile.isEmpty()) return ExitCode.NOTHING_DONE;
			if (Utils.IS_TEST_MODE || LOG.isDebugEnabled()) LOG.info("java-generation = " + javaGeneration);
			Map<ModuleBuildTarget, String> finalOutputs = getCanonicalModuleOutputs(context, chunk);
			if (finalOutputs == null) return ExitCode.ABORT;
			start = System.currentTimeMillis();
			final Set<String> toCompilePaths = getPathsToCompile(toCompile);
			final boolean isSystem = isSystem(getModuleConfigurationFile(chunk.getModules().iterator().next()));
			final String encoding = context.getProjectDescriptor().getEncodingConfiguration().getPreferredModuleChunkEncoding(chunk);
			Map<ModuleBuildTarget, String> generationOutputs = javaGeneration ? getStubGenerationOutputs(chunk, context) : finalOutputs;
			String compilerOutput = generationOutputs.get(chunk.representativeTarget()); //TODO replacement to getOutDir
			String finalOutput = FileUtil.toSystemDependentName(finalOutputs.get(chunk.representativeTarget()));
			TaraRunner runner = new TaraRunner(project.getName(), chunk.getName(), getOutDir(chunk.getModules()),
				isSystem, context.getCompilationStartStamp(), toCompilePaths, finalOutput, encoding, getRulesDir(chunk.getModules()),
				collectIconDirectories(chunk.getModules()), getMagritteJdk(chunk).getHomePath(), finalOutput);
			final TaracOSProcessHandler handler = runner.runTaraCompiler(context, settings, javaGeneration);
			processMessages(chunk, context, handler);
			context.setDone(1);
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

	private String[] collectIconDirectories(Set<JpsModule> jpsModules) {
		ArrayList<String> iconDirectories = new ArrayList<>();
		for (JpsModule module : jpsModules) {
			File res = new File(module.getSourceRoots().get(0).getFile().getParentFile(), RES);
			if (res.exists()) {
				File icons = new File(res, ICONS);
				if (icons.exists()) iconDirectories.add(icons.getAbsolutePath());
			}
		}
		return iconDirectories.toArray(new String[iconDirectories.size()]);
	}

	public String getRulesDir(Set<JpsModule> jpsModules) {
		for (JpsModule module : jpsModules) {
			File moduleFile = module.getSourceRoots().get(0).getFile().getParentFile();
			File res = new File(moduleFile, RES);
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
			if (moduleSourceRoot.getFile().getName().equals("gen"))
				return moduleSourceRoot.getFile().getAbsolutePath();
		File moduleFile = module.getSourceRoots().get(0).getFile().getParentFile();
		File gen = new File(moduleFile, "gen");
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

	private File getModuleConfigurationFile(JpsModule module) {
		List<String> urls = module.getContentRootsList().getUrls();
		if (urls.isEmpty()) return null;
		File file = new File(urls.get(0).substring(7), module.getName() + ".iml");
		if (!file.exists()) return null;
		return file;
	}

	public boolean isSystem(File moduleFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(moduleFile);
			NodeList nList = doc.getElementsByTagName("component");
			for (int i = 0; i < nList.getLength(); i++)
				if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nList.item(i);
					if (!element.getAttribute("name").equals("ModuleConfiguration")) continue;
					NodeList componentChildren = element.getElementsByTagName("option");
					for (int j = 0; j < componentChildren.getLength(); j++)
						if (componentChildren.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Element option = (Element) componentChildren.item(j);
							if (option.getAttribute("name").equals("system"))
								return Boolean.valueOf(option.getAttribute("value"));
						}
				}
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}