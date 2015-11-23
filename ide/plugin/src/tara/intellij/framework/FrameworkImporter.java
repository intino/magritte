package tara.intellij.framework;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import siani.lasso.Lasso;
import siani.lasso.LassoComment;
import tara.intellij.lang.LanguageManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

public class FrameworkImporter {

	private static final Logger LOG = Logger.getInstance(FrameworkImporter.class.getName());
	private static final String MODULE_TAG = "$module";
	private static final String POM_XML = "pom.xml";
	private static final String TEMP_POM_XML = "_pom.xml.itr";

	private Module module;

	public FrameworkImporter(Module module) {
		this.module = module;
	}

	public void importLanguage(String key, String version) {
		doImportLanguage(downloadLanguage(key, version));
	}

	private File downloadLanguage(String key, String version) {
		try {
			final String versionCode = getVersion(key, version);
			File dslFile = new File(FileUtil.getTempDirectory(), key + "_" + versionCode + ".dsl");
			new TaraHubConnector(key, versionCode).downloadTo(dslFile);
			return dslFile;
		} catch (IOException e) {
			error(e);
			return null;
		}
	}

	private String getVersion(String key, String version) throws IOException {
		if (version.equals(LanguageInfo.LATEST_VERSION)) {
			final List<String> versions = new TaraHubConnector().versions(key);
			Collections.sort(versions);
			return versions.get(versions.size() - 1);
		} else return version;
	}

	private void doImportLanguage(File file) {
		if (file == null || !file.exists()) {
			error(file);
			return;
		}
		final VirtualFile taraDirectory = LanguageManager.getTaraDirectory(module.getProject());
		saveAll(module.getProject());
		boolean success = unzip(file, taraDirectory);
		if (!success) error(file);
		pom(module);
		reload(file.getName(), module.getProject());
		if (file.exists()) file.delete();
	}


	private boolean unzip(File file, VirtualFile taraDirectory) {
		try {
			ZipUtil.unzip(null, new File(taraDirectory.getPath()), new File(file.getPath()), null, null, false);
			return true;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			return false;
		}
	}

	private void pom(Module module) {
		try {
			customizePom(LanguageManager.getTaraDirectory(module.getProject()), module);
			syncPom(module, new File(module.getProject().getBaseDir().getPath()));
		} catch (IOException e) {
			LOG.error(e.getMessage());
			error(e);
		}
	}

	private void customizePom(VirtualFile taraDirectory, Module module) throws IOException {
		final File pom = new File(taraDirectory.getPath(), TEMP_POM_XML);
		if (!pom.exists()) return;
		Files.write(pom.toPath(), new String(Files.readAllBytes(pom.toPath())).replace(MODULE_TAG, module.getName()).getBytes());
		Files.move(pom.toPath(), getDestiny(pom));
	}

	private Path getDestiny(File pom) {
		return new File(pom.getParentFile().getParentFile(), pom.getName()).toPath();
	}

	private void syncPom(Module module, File projectDirectory) throws IOException {
		final String parentPomPath = FileUtil.findFileInProvidedPath(projectDirectory.getPath(), TEMP_POM_XML);
		if (parentPomPath == null || parentPomPath.isEmpty()) return;
		final File parentPom = new File(parentPomPath);
		final File childPom = new File(new File(module.getModuleFilePath()).getParentFile(), POM_XML);
		if (childPom.exists()) {
			new Lasso(parentPom, childPom, true, LassoComment.XML).execute();
			parentPom.delete();
		} else {
			Files.move(parentPom.toPath(), childPom.toPath(), StandardCopyOption.REPLACE_EXISTING);
			initPom(module, childPom);
		}
	}

	private void initPom(Module module, File pomFile) {
		final VirtualFile pomVirtualFile = VfsUtil.findFileByIoFile(pomFile, true);
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		manager.addManagedFilesOrUnignore(Collections.singletonList(pomVirtualFile));
		manager.importProjects();
		manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles();
	}

	private void reload(String fileName, Project project) {
		LanguageManager.reloadLanguage(FileUtil.getNameWithoutExtension(fileName), project);
		reloadProject();
	}

	public void saveAll(Project project) {
		project.save();
		FileDocumentManager.getInstance().saveAllDocuments();
		ProjectManagerEx.getInstanceEx().blockReloadingProjectOnExternalChanges();
	}

	private void reloadProject() {
		SaveAndSyncHandlerImpl.getInstance().refreshOpenFiles();
		VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
	}

	private void error(File file) {
		Notifications.Bus.notify(new Notification("Tara Language", "Error reading file.", file.getName(), NotificationType.ERROR));
	}

	private void success(Project project, String language) {
		Notifications.Bus.notify(new Notification("Tara Language", "Language Importer successfully", language, NotificationType.INFORMATION), project);
	}

	private void error(IOException e) {
		Notifications.Bus.notify(new Notification("Tara Language", "Error trying to connect Tara Hub.", e.getMessage(), NotificationType.ERROR));
	}
}
