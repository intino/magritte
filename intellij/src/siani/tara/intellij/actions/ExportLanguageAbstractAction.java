package siani.tara.intellij.actions;

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
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.project.facet.TaraFacet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static siani.tara.intellij.lang.TaraLanguage.LANGUAGES_PACKAGE;
import static siani.tara.intellij.lang.TaraLanguage.LANGUAGES_PATH;

public abstract class ExportLanguageAbstractAction extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ExportLanguageAbstractAction.class.getName());

	@NonNls
	private static final String LANGUAGE_EXTENSION = ".language";
	@NonNls
	private static final String JAR_EXTENSION = ".jar";
	private static final String CLASS_EXTENSION = ".class";
	@NonNls
	private static final String TEMP_PREFIX = "temp";
	@NonNls
	private static final String METAMODEL_DIR = "metamodel";
	private static final String MIDDLE_MODEL_DIR = TaraLanguage.DSL;

	public static void getDependencies(Module module, final Set<Module> modules) {
		productionRuntimeDependencies(module).forEachModule(dep -> {
			if (!modules.contains(dep)) {
				modules.add(dep);
				getDependencies(dep, modules);
			}
			return true;
		});
	}

	public static void getLibraries(Module module, final Set<Library> libs) {
		productionRuntimeDependencies(module).forEachLibrary(library -> {
			libs.add(library);
			return true;
		});
	}

	public static OrderEnumerator productionRuntimeDependencies(Module module) {
		return OrderEnumerator.orderEntries(module).productionOnly().runtimeOnly();
	}

	protected boolean doPrepare(final Module module, final List<String> errorMessages, final List<String> successMessages) {
		final String languageName = TaraFacet.getTaraFacetByModule(module).getConfiguration().getGeneratedDslName();
		final String defaultPath = new File(module.getModuleFilePath()).getParent() + File.separator + languageName;
		final Set<Module> modules = new HashSet<>();
		getDependencies(module, modules);
		modules.add(module);
		final Set<Library> libs = new HashSet<>();
		for (Module dep : modules)
			getLibraries(dep, libs);
		final String destinyPath = defaultPath + (LANGUAGE_EXTENSION);
		final File dstFile = new File(destinyPath);
		return clearReadOnly(module.getProject(), dstFile) && ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
			if (progressIndicator != null) {
				progressIndicator.setText(MessageProvider.message("prepare.for.deployment.common"));
				progressIndicator.setIndeterminate(true);
			}
			try {
				File jarFile = jarModulesOutput(modules);
				processLibrariesAndJpsModules(jarFile, dstFile, languageName, libs, progressIndicator);
				successMessages.add(MessageProvider.message("saved.message", languageName, destinyPath));
			} catch (final IOException e) {
				LOG.info(e.getMessage(), e);
				errorMessages.add(e.getMessage() + "\n(" + destinyPath + ")");
			}
		}, MessageProvider.message("export.language", languageName), true, module.getProject());
	}

	private void addLanguage(ZipOutputStream zos, String languageName) throws IOException {
		File file = new File(LANGUAGES_PATH + File.separator + languagePath(languageName));
		if (!file.exists()) throw new IOException("Language file not found");
		String entryName = languagePath(languageName);
		final String path = "/" + languageName + "/" + MIDDLE_MODEL_DIR + "/" + entryName;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, file, path, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	@NotNull
	private String languagePath(String languageName) {
		return LANGUAGES_PACKAGE.replace(".", File.separator) + File.separator + languageName + CLASS_EXTENSION;
	}

	private void processLibrariesAndJpsModules(final File jarFile, final File zipFile, final String languageName,
	                                           final Set<Library> libs,
	                                           final ProgressIndicator progressIndicator) throws IOException {
		if (FileUtil.ensureCanCreateFile(zipFile)) {
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
				addStructure(languageName, zos);
				addStructure(languageName + "/" + METAMODEL_DIR, zos);
				final String entryName = languageName + JAR_EXTENSION;
				ZipUtil.addFileToZip(zos, jarFile, getZipPath(languageName, entryName),
					new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
				addLanguage(zos, languageName);
				Set<String> usedJarNames = new HashSet<>();
				usedJarNames.add(entryName);
				Set<VirtualFile> jarredVirtualFiles = new HashSet<>();
				for (Library library : libs) {
					final VirtualFile[] files = library.getFiles(OrderRootType.CLASSES);
					for (VirtualFile virtualFile : files)
						if (jarredVirtualFiles.add(virtualFile))
							if (virtualFile.getFileSystem() instanceof JarFileSystem)
								addLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator);
							else
								makeAndAddLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator, library.getName());
				}
			} finally {
				if (zos != null) zos.close();
			}
		}
	}

	private String getZipPath(final String modelName, final String entryName) {
		return "/" + modelName + "/" + METAMODEL_DIR + "/" + entryName;
	}

	private FileFilter createFilter(final ProgressIndicator progressIndicator, @Nullable final FileTypeManager fileTypeManager) {
		return pathName -> {
			if (progressIndicator != null) progressIndicator.setText2("");
			return fileTypeManager == null || !fileTypeManager.isFileIgnored(FileUtil.toSystemIndependentName(pathName.getName()));
		};
	}

	private File jarModulesOutput(@NotNull Set<Module> modules) throws IOException {
		File jarFile = FileUtil.createTempFile(TEMP_PREFIX, JAR_EXTENSION);
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

	private void addStructure(@NonNls final String relativePath, final ZipOutputStream zos) throws IOException {
		ZipEntry e = new ZipEntry(relativePath + '/');
		e.setMethod(ZipEntry.STORED);
		e.setSize(0);
		e.setCrc(0);
		zos.putNextEntry(e);
		zos.closeEntry();
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
		return vfile == null || !ReadonlyStatusHandler.getInstance(project).ensureFilesWritable(vfile).hasReadonlyFiles();
	}

}
