package tara.intellij.actions;

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
import org.jetbrains.idea.maven.model.MavenArtifact;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.siani.itrules.model.Frame;
import tara.intellij.MessageProvider;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.templates.ExportPomTemplate;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import static tara.intellij.lang.TaraLanguage.DSL;
import static tara.intellij.lang.TaraLanguage.FRAMEWORK;

public abstract class ExportLanguageAbstractAction extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ExportLanguageAbstractAction.class.getName());
	@NonNls
	private static final String JAR_EXTENSION = ".jar";
	@NonNls
	private static final String TEMP_PREFIX = "temp";

	public static OrderEnumerator productionRuntimeDependencies(Module module) {
		return OrderEnumerator.orderEntries(module).productionOnly();
	}

	protected boolean doPrepare(final Module module, final List<String> errorMessages, final List<String> successMessages) {
		final String languageName = TaraFacet.of(module).getConfiguration().getGeneratedDslName();
		final String destinyPath = module.getProject().getBasePath() + File.separator + languageName + TaraLanguage.LANGUAGE_EXTENSION;
		final File dstFile = new File(destinyPath);
		FileUtil.delete(dstFile);
		final Set<Module> modules = new HashSet<>();
		getDependencies(module, modules);
		modules.add(module);
		final Set<Library> libs = new HashSet<>();
		for (Module dep : modules) getLibraries(dep, libs);
		return clearReadOnly(module.getProject(), dstFile) && ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
			if (progressIndicator != null) {
				progressIndicator.setText(MessageProvider.message("prepare.for.deployment.common"));
				progressIndicator.setIndeterminate(true);
			}
			try {
				File modulesJarFile = jarModulesOutput(modules);
				File pom = createPom(modules, languageName);
				processLibrariesAndJpsModules(module.getProject(), modulesJarFile, pom, dstFile, languageName, libs, progressIndicator);
				LocalFileSystem.getInstance().refreshIoFiles(Collections.singleton(dstFile), true, false, null);
				successMessages.add(MessageProvider.message("saved.message", languageName, destinyPath));
			} catch (final IOException e) {
				LOG.info(e.getMessage(), e);
				errorMessages.add(e.getMessage() + "\n(" + destinyPath + ")");
			}
		}, MessageProvider.message("export.language", languageName), true, module.getProject());
	}

	private void processLibrariesAndJpsModules(Project project, final File modulesJar, File pom, final File zipFile, final String languageName,
	                                           final Set<Library> libs,
	                                           final ProgressIndicator progressIndicator) throws IOException {
		if (FileUtil.ensureCanCreateFile(zipFile)) {
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
				final String entryName = languageName + JAR_EXTENSION;
				ZipUtil.addFileToZip(zos, modulesJar, getZipPath(languageName, entryName), new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
				addLanguage(project, zos, languageName);
				addPom(zos, pom);
				Set<String> usedJarNames = new HashSet<>();
				usedJarNames.add(entryName);
				Set<VirtualFile> jarredVirtualFiles = new HashSet<>();
				for (Library library : libs) {
					processLibrary(zipFile, languageName, progressIndicator, zos, usedJarNames, jarredVirtualFiles, library);
				}
			} finally {
				if (zos != null) zos.close();
			}
		}
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


	private static void getLibraries(Module module, final Set<Library> libs) {
		productionRuntimeDependencies(module).compileOnly().forEachLibrary(library -> {
			if (library.getTable() == null) libs.add(library);
			return true;
		});
	}

	private void processLibrary(File zipFile, String languageName, ProgressIndicator progressIndicator, ZipOutputStream zos, Set<String> usedJarNames, Set<VirtualFile> jarredVirtualFiles, Library library) throws IOException {
		final VirtualFile[] files = library.getFiles(OrderRootType.CLASSES);
		for (VirtualFile virtualFile : files)
			if (jarredVirtualFiles.add(virtualFile))
				if (virtualFile.getFileSystem() instanceof JarFileSystem)
					addLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator);
				else
					makeAndAddLibraryJar(virtualFile, zipFile, languageName, zos, usedJarNames, progressIndicator, library.getName());
	}

	private void addLanguage(Project project, ZipOutputStream zos, String languageName) throws IOException {
		File file = TaraLanguage.getLanguageDirectory(languageName, project.getBaseDir().getPath());
		if (file == null || !file.exists()) throw new IOException("Language file not found");
		final String entryPath = "/" + DSL + "/" + languageName + "/" + languageName + JAR_EXTENSION;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, new File(file.getPath(), languageName + JAR_EXTENSION), entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	private void addPom(ZipOutputStream zos, File pom) throws IOException {
		final String detinyFile = "_pom.xml";
		final File dest = new File(pom.getParent(), detinyFile);
		dest.delete();
		pom.renameTo(dest);
		final String entryPath = "/" + detinyFile;
		final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
		ZipUtil.addFileToZip(zos, dest, entryPath, new HashSet<>(), createFilter(progressIndicator, FileTypeManager.getInstance()));
	}

	private File createPom(Collection<Module> modules, String languageName) throws IOException {
		return createPom(collectDependencies(modules), FileUtil.createTempFile("pom", ".xml", true), languageName);
	}

	private File createPom(Set<MavenArtifact> mavenArtifacts, File pom, String languageName) {
		Frame frame = new Frame();
		frame.addTypes("pom");
		frame.addFrame("name", languageName);
		List<Frame> dependencies = createDependencyFrame(mavenArtifacts, languageName);
		frame.addFrame("dependency", dependencies.toArray(new Frame[dependencies.size()]));
		writePom(pom, frame);
		return pom;
	}

	private void writePom(File pom, Frame frame) {
		try {
			Files.write(pom.toPath(), ExportPomTemplate.create().format(frame).getBytes());
		} catch (IOException e) {
			LOG.error("Error creating pom to export: " + e.getMessage());
		}
	}

	private List<Frame> createDependencyFrame(Set<MavenArtifact> mavenArtifacts, String languageName) {
		List<Frame> dependencies = new ArrayList<>();
		for (MavenArtifact mavenArtifact : mavenArtifacts) {
			Frame dependency = new Frame();
			dependency.addTypes("dependency");
			dependency.addFrame("groupId", mavenArtifact.getGroupId());
			dependency.addFrame("artifactId", mavenArtifact.getArtifactId());
			dependency.addFrame("scope", mavenArtifact.getScope());
			dependency.addFrame("version", mavenArtifact.getVersion());
			if ("system".equalsIgnoreCase(mavenArtifact.getScope()))
				dependency.addFrame("path", "/../.tara/framework/" + languageName + "/" + mavenArtifact.getFile().getName());
			dependencies.add(dependency);
		}
		Frame dslDependency = new Frame();
		dslDependency.addTypes("dependency");
		dslDependency.addFrame("groupId", languageName);
		dslDependency.addFrame("artifactId", languageName);
		dslDependency.addFrame("scope", "system");
		dslDependency.addFrame("version", "1.0");
		dslDependency.addFrame("path", "/../.tara/framework/" + languageName + "/" + languageName + JAR_EXTENSION);
		dependencies.add(dslDependency);
		return dependencies;
	}

	private Set<MavenArtifact> collectDependencies(Collection<Module> modules) {
		Set<MavenArtifact> dependencies = new HashSet<>();
		for (Module module : modules) {
			MavenProject mavenProject = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
			if (mavenProject != null)
				dependencies.addAll(mavenProject.getDependencies().stream().
					filter(d -> d.getScope().equalsIgnoreCase("compile") || d.getScope().equals("system")).
					collect(Collectors.toList()));
		}
		return dependencies;
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
