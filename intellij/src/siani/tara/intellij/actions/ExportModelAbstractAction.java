package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
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
import com.intellij.util.Processor;
import com.intellij.util.io.ZipUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.project.module.TaraModuleType;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExportModelAbstractAction extends AnAction implements DumbAware {
	@NonNls
	private static final String ZIP_EXTENSION = ".zip";
	@NonNls
	private static final String JAR_EXTENSION = ".jar";
	private static final String JSON_EXTENSION = ".json";
	@NonNls
	private static final String TEMP_PREFIX = "temp";
	@NonNls
	private static final String MIDDLE_LIB_DIR = "lib";
	private static final String MIDDLE_BIN_DIR = "bin";
	private static final String MIDDLE_MODEL_DIR = "model";

	public static void getDependencies(Module module, final Set<Module> modules) {
		productionRuntimeDependencies(module).forEachModule(new Processor<Module>() {
			@Override
			public boolean process(Module dep) {
				if (ModuleType.get(dep) == TaraModuleType.getInstance() && !modules.contains(dep)) {
					modules.add(dep);
					getDependencies(dep, modules);
				}
				return true;
			}
		});
	}

	public static void getLibraries(Module module, final Set<Library> libs) {
		productionRuntimeDependencies(module).forEachLibrary(new Processor<Library>() {
			@Override
			public boolean process(Library library) {
				libs.add(library);
				return true;
			}
		});
	}

	public static OrderEnumerator productionRuntimeDependencies(Module module) {
		return OrderEnumerator.orderEntries(module).productionOnly().runtimeOnly();
	}

	protected boolean doPrepare(final Module module, final List<String> errorMessages, final List<String> successMessages) {
		final String modelName = module.getProject().getName() + "_" + module.getName();
		final String defaultPath = new File(module.getModuleFilePath()).getParent() + File.separator + modelName;
		final Set<Module> modules = new HashSet<>();
		getDependencies(module, modules);
		modules.add(module);
		final Set<Library> libs = new HashSet<>();
		for (Module dep : modules)
			getLibraries(dep, libs);
		final String dstPath = defaultPath + (ZIP_EXTENSION);
		final File dstFile = new File(dstPath);
		return clearReadOnly(module.getProject(), dstFile) && ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
			public void run() {
				final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
				if (progressIndicator != null) {
					progressIndicator.setText(MessageProvider.message("prepare.for.deployment.common"));
					progressIndicator.setIndeterminate(true);
				}
				try {
					File jarFile = jarModulesOutput(modules);
					processLibrariesAndJpsModules(jarFile, dstFile, modelName, libs, progressIndicator);
					successMessages.add(MessageProvider.message("saved.message", 1, modelName, dstPath));
				} catch (final IOException e) {
					errorMessages.add(e.getMessage() + "\n(" + dstPath + ")");
				}
			}
		}, MessageProvider.message("build.sdk.from.models", modelName), true, module.getProject());
	}

	private void addModelRepresentation(ZipOutputStream zos, String modelName) throws IOException {
		String project = modelName.split("_")[0];
		String module = modelName.split("_")[1];
		File file = new File(TaraLanguage.MODELS_PATH + project, project + "." + module + JSON_EXTENSION);
		if (!file.exists()) throw new IOException("model file not found");
		String entryName = project + "." + module + JSON_EXTENSION;
		final String path = "/" + modelName + "/" + MIDDLE_MODEL_DIR + "/" + entryName;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, file, path, new HashSet<String>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	private void processLibrariesAndJpsModules(final File jarFile, final File zipFile, final String modelName,
	                                           final Set<Library> libs,
	                                           final ProgressIndicator progressIndicator) throws IOException {
		if (FileUtil.ensureCanCreateFile(zipFile)) {
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
				addStructure(modelName, zos);
				addStructure(modelName + "/" + MIDDLE_LIB_DIR, zos);
				final String entryName = modelName + JAR_EXTENSION;
				ZipUtil.addFileToZip(zos, jarFile, getZipPath(modelName, entryName),
					new HashSet<String>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
				addModelRepresentation(zos, modelName);
				addStandAloneCompiler(zos, modelName);
				Set<String> usedJarNames = new HashSet<>();
				usedJarNames.add(entryName);
				Set<VirtualFile> jarredVirtualFiles = new HashSet<>();
				for (Library library : libs) {
					final VirtualFile[] files = library.getFiles(OrderRootType.CLASSES);
					for (VirtualFile virtualFile : files) {
						if (jarredVirtualFiles.add(virtualFile)) {
							if (virtualFile.getFileSystem() instanceof JarFileSystem) {
								addLibraryJar(virtualFile, zipFile, modelName, zos, usedJarNames, progressIndicator);
							} else {
								makeAndAddLibraryJar(virtualFile, zipFile, modelName, zos, usedJarNames, progressIndicator, library.getName());
							}
						}
					}
				}
			} finally {
				if (zos != null) zos.close();
			}
		}
	}

	private void addStandAloneCompiler(ZipOutputStream zos, String modelName) throws IOException {
		String entryName = "tarac" + JAR_EXTENSION;
		File file = new File(this.getClass().getResource("/compiler/" + entryName).getFile());
		if (!file.exists()) throw new IOException("tarac not found");
		final String path = "/" + modelName + "/" + MIDDLE_BIN_DIR + "/" + entryName;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, file, path, new HashSet<String>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	private String getZipPath(final String modelName, final String entryName) {
		return "/" + modelName + "/" + MIDDLE_LIB_DIR + "/" + entryName;
	}

	private FileFilter createFilter(final ProgressIndicator progressIndicator, @Nullable final FileTypeManager fileTypeManager) {
		return new FileFilter() {
			public boolean accept(File pathName) {
				if (progressIndicator != null) progressIndicator.setText2("");
				return fileTypeManager == null || !fileTypeManager.isFileIgnored(FileUtil.toSystemIndependentName(pathName.getName()));
			}
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
				if (compilerOutputPath == null) continue; //pre-condition: output dirs for all modules are up-to-date
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
				final int dotPos = uniqueName.lastIndexOf(".");
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
		ZipEntry e = new ZipEntry(relativePath + "/");
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

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {

	}
}
