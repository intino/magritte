package tara.intellij.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.OrderEnumerator;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.util.io.ZipUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.MessageProvider;
import tara.intellij.actions.utils.ExportationPomCreator;
import tara.intellij.framework.FrameworkExporter;
import tara.intellij.lang.LanguageManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipOutputStream;

import static tara.intellij.lang.LanguageManager.DSL;
import static tara.intellij.lang.LanguageManager.FRAMEWORK;

public abstract class ExportLanguageAbstractAction extends AnAction implements DumbAware {


	private static final String TEMP_POM_XML = "_pom.xml.itr";
	private static final String INFO_JSON = "info.json";
	protected List<String> errorMessages = new ArrayList<>();
	protected List<String> successMessages = new ArrayList<>();

	private static final Logger LOG = Logger.getInstance(ExportLanguageAbstractAction.class.getName());
	@NonNls
	private static final String JAR_EXTENSION = ".jar";
	@NonNls
	private static final String TEMP_PREFIX = "temp";
	private static final String JSON_EXTENSION = ".json";

	public static OrderEnumerator productionRuntimeDependencies(Module module) {
		return OrderEnumerator.orderEntries(module).productionOnly();
	}

	protected boolean doPrepare(final Module module) {
		final String languageName = TaraFacet.of(module).getConfiguration().outputDsl();
		final File dstFile = new File(module.getProject().getBasePath() + File.separator + languageName + LanguageManager.LANGUAGE_EXTENSION);
		FileUtil.delete(dstFile);
		final Set<Module> moduleDependencies = new HashSet<>(Collections.singletonList(module));
		getDependencies(module, moduleDependencies);
		final Set<Library> libs = new HashSet<>();
		moduleDependencies.forEach(dep -> libs.addAll(getLibraries(dep)));
		return run(dstFile, module, moduleDependencies, libs);
	}

	private boolean run(File zipFile, Module module, Set<Module> moduleDependencies, Set<Library> libs) {
		final String languageName = FileUtil.getNameWithoutExtension(zipFile);
		return clearReadOnly(module.getProject(), zipFile) && ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator progressIndicator = createProgressIndicator();
			try {
				File pom = ExportationPomCreator.createPom(moduleDependencies, languageName);
				zipAll(zipFile, languageName, module, jarModulesOutput(moduleDependencies), pom, libs, progressIndicator);
				LocalFileSystem.getInstance().refreshIoFiles(Collections.singleton(zipFile), true, false, null);
				final int i = uploadLanguage(module, zipFile);
				if (i != 200) throw new IOException("Error uploading language. Code: " + i);
				successMessages.add(MessageProvider.message("saved.message", languageName));
				zipFile.delete();
			} catch (final IOException e) {
				LOG.info(e.getMessage(), e);
				errorMessages.add(e.getMessage() + "\n(" + FileUtil.getNameWithoutExtension(zipFile) + ")");
			}
		}, MessageProvider.message("export.language", languageName), true, module.getProject());
	}

	private int uploadLanguage(Module module, File file) throws IOException {
		return new FrameworkExporter(module, file).export();
	}

	@Nullable
	private ProgressIndicator createProgressIndicator() {
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		if (progressIndicator != null) {
			progressIndicator.setText(MessageProvider.message("prepare.for.deployment.common"));
			progressIndicator.setIndeterminate(true);
		}
		return progressIndicator;
	}

	private void zipAll(final File zipFile, final String languageName, Module module, final File modulesJar, File pom,
	                    final Set<Library> libs, final ProgressIndicator progressIndicator) throws IOException {
		if (FileUtil.ensureCanCreateFile(zipFile)) {
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
				final String entryName = languageName + JAR_EXTENSION;
				ZipUtil.addFileToZip(zos, modulesJar, getZipPath(languageName, entryName), new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
				addLanguage(module.getProject(), zos, languageName);
				addPom(zos, pom);
				addInfo(zos, module, languageName);
				Set<String> usedJarNames = new HashSet<>();
				usedJarNames.add(entryName);
				Set<VirtualFile> jarredVirtualFiles = new HashSet<>();
				for (Library library : libs)
					zipLibrary(zipFile, languageName, progressIndicator, zos, usedJarNames, jarredVirtualFiles, library);
			} finally {
				if (zos != null) zos.close();
			}
		}
	}

	private void addInfo(ZipOutputStream zos, Module module, String languageName) throws IOException {
		File file = createInfo(module);
		if (file == null) return;
		final File dest = new File(file.getParent(), INFO_JSON);
		dest.delete();
		file.renameTo(dest);
		final String entryPath = "/" + DSL + "/" + languageName + "/" + INFO_JSON;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, dest, entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	private File createInfo(Module module) {
		Map<String, Object> values = new HashMap<>();
		final TaraFacetConfiguration conf = TaraFacet.of(module).getConfiguration();
		for (Field field : conf.getProperties().getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				values.put(field.getName(), field.get(conf.getProperties()));
			} catch (IllegalAccessException ignored) {
			} finally {
				field.setAccessible(false);
			}

		}
		return saveInfo(values);
	}

	private File saveInfo(Map<String, Object> values) {
		try {
			final File info = FileUtil.createTempFile("info", ".json", true);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			final String txt = gson.toJson(values);
			if (!info.exists()) info.getParentFile().mkdirs();
			Files.write(info.toPath(), txt.getBytes());
			return info;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	private void getDependencies(Module module, final Set<Module> modules) {
		productionRuntimeDependencies(module).forEachModule(dep -> {
			if (!modules.contains(dep)) {
				modules.add(dep);
				getDependencies(dep, modules);
			}
			return true;
		});
	}


	private static Set<Library> getLibraries(Module module) {
		final Set<Library> libs = new HashSet<>();
		productionRuntimeDependencies(module).compileOnly().forEachLibrary(library -> {
			if (library.getTable() == null) libs.add(library);
			return true;
		});
		return libs;
	}

	private void zipLibrary(File zipFile, String languageName, ProgressIndicator progressIndicator, ZipOutputStream zos, Set<String> usedJarNames, Set<VirtualFile> jarredVirtualFiles, Library library) throws IOException {
		final VirtualFile[] files = library.getFiles(OrderRootType.CLASSES);
		for (VirtualFile virtualFile : files)
			if (jarredVirtualFiles.add(virtualFile))
				if (virtualFile.getFileSystem() instanceof JarFileSystem)
					addLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator);
				else
					makeAndAddLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator, library.getName());
	}

	private void addLanguage(Project project, ZipOutputStream zos, String languageName) throws IOException {
		File taraDirectory = LanguageManager.getLanguageDirectory(languageName, project);
		if (!taraDirectory.exists()) throw new IOException("Language file not found");
		String entryPath = "/" + DSL + "/" + languageName + "/" + languageName + JAR_EXTENSION;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, new File(taraDirectory.getPath(), languageName + JAR_EXTENSION), entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
		final File refactors = new File(taraDirectory.getPath(), "refactors" + JSON_EXTENSION);
		if (refactors.exists()) {
			entryPath = "/" + FRAMEWORK + "/" + languageName + "/" + "refactors" + JSON_EXTENSION;
			ZipUtil.addFileToZip(zos, refactors, entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
		}
	}

	private void addPom(ZipOutputStream zos, File pom) throws IOException {
		final File dest = new File(pom.getParent(), TEMP_POM_XML);
		dest.delete();
		pom.renameTo(dest);
		final String entryPath = "/" + TEMP_POM_XML;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, dest, entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}


	private String getZipPath(final String langName, final String entryName) {
		return "/" + FRAMEWORK + "/" + langName + "/" + entryName;
	}

	private FileFilter createFilter(final ProgressIndicator progressIndicator, @Nullable final FileTypeManager fileTypeManager) {
		return pathName -> {
			if (progressIndicator != null) progressIndicator.setText2("");
			return fileTypeManager == null || !fileTypeManager.isFileIgnored(FileUtil.toSystemIndependentName(pathName.getName()));
		};
	}

	private File jarModulesOutput(@NotNull Set<Module> modules) throws IOException {
		File jarFile = FileUtil.createTempFile(TEMP_PREFIX, JAR_EXTENSION, true);
		jarFile.deleteOnExit();
		ZipOutputStream jarModel = null;
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(jarFile));
			jarModel = new JarOutputStream(out);
			final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
			final Set<String> writtenItemRelativePaths = new HashSet<>();
			for (Module module : modules) {
				final VirtualFile compilerOutputPath = CompilerModuleExtension.getInstance(module).getCompilerOutputPath();
				if (compilerOutputPath == null) continue;
				ZipUtil.addDirToZipRecursively(jarModel, jarFile, new File(compilerOutputPath.getPath()), "",
					createFilter(progressIndicator, FileTypeManager.getInstance()), writtenItemRelativePaths);
			}
		} finally {
			if (jarModel != null) jarModel.close();
		}
		return jarFile;
	}

	private void addLibraryJar(final VirtualFile virtualFile,
	                           final File zipFile,
	                           final String modelName,
	                           final ZipOutputStream zos,
	                           final Set<String> usedJarNames,
	                           final ProgressIndicator progressIndicator) throws IOException {
		File ioFile = VfsUtil.virtualToIoFile(virtualFile);
		final String jarName = getLibraryJarName(ioFile.getName(), usedJarNames, null);
		ZipUtil.addFileOrDirRecursively(zos, zipFile, ioFile, getZipPath(modelName, jarName), createFilter(progressIndicator, null), null);
	}

	private String getLibraryJarName(final String fileName, Set<String> usedJarNames, @Nullable final String preferredName) {
		String uniqueName;
		if (preferredName != null && !usedJarNames.contains(preferredName)) {
			uniqueName = preferredName;
		} else {
			uniqueName = fileName;
			if (usedJarNames.contains(uniqueName)) {
				final int dotPos = uniqueName.lastIndexOf('.');
				final String name = dotPos < 0 ? uniqueName : uniqueName.substring(0, dotPos);
				final String ext = dotPos < 0 ? "" : uniqueName.substring(dotPos);

				int i = 0;
				do {
					i++;
					uniqueName = name + i + ext;
				}
				while (usedJarNames.contains(uniqueName));
			}
		}
		usedJarNames.add(uniqueName);
		return uniqueName;
	}

	private void makeAndAddLibraryJar(final VirtualFile virtualFile,
	                                  final File zipFile,
	                                  final String modelName,
	                                  final ZipOutputStream zos,
	                                  final Set<String> usedJarNames,
	                                  final ProgressIndicator progressIndicator,
	                                  final String preferredName) throws IOException {
		File libraryJar = FileUtil.createTempFile(TEMP_PREFIX, JAR_EXTENSION);
		libraryJar.deleteOnExit();
		ZipOutputStream jar = null;
		try {
			jar = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(libraryJar)));
			ZipUtil.addFileOrDirRecursively(jar, libraryJar, VfsUtilCore.virtualToIoFile(virtualFile), "",
				createFilter(progressIndicator, FileTypeManager.getInstance()), null);
		} finally {
			if (jar != null) jar.close();
		}
		final String jarName =
			getLibraryJarName(virtualFile.getName() + JAR_EXTENSION, usedJarNames, preferredName == null ? null : preferredName + JAR_EXTENSION);
		ZipUtil.addFileOrDirRecursively(zos, zipFile, libraryJar, getZipPath(modelName, jarName), createFilter(progressIndicator, null), null);
	}

	private boolean clearReadOnly(final Project project, final File dstFile) {
		final URL url;
		try {
			url = dstFile.toURI().toURL();
		} catch (MalformedURLException e) {
			return true;
		}
		final VirtualFile vfile = VfsUtil.findFileByURL(url);
		return !dstFile.exists() || vfile == null || !ReadonlyStatusHandler.getInstance(project).ensureFilesWritable(vfile).hasReadonlyFiles();
	}

}
