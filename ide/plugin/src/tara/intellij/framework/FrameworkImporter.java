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

public class FrameworkImporter {

	private static final Logger LOG = Logger.getInstance(FrameworkImporter.class.getName());
	private static final String MODULE_TAG = "module";
	private static final String POM_XML = "pom.xml";
	private static final String TEMP_POM_XML = "_pom.xml.itr";

	private Module module;

	public FrameworkImporter(Module module) {
		this.module = module;
	}

	public File importLanguage(String key, String version) {
		return doImportLanguage(downloadLanguage(key, version));
	}

	private File downloadLanguage(String name, String version) {
		File dslFile = new File(FileUtil.getTempDirectory(), name + "_" + version + ".dsl");
		new TaraHubConnector(LanguageManager.PROTEO_KEY).downloadTo(dslFile);
		return dslFile;
	}

	private File doImportLanguage(File file) {
		final VirtualFile taraDirectory = LanguageManager.getTaraDirectory(module.getProject());
		saveAll(module.getProject());
		boolean success = unzip(file, taraDirectory);
		if (!success) return null;
		pom(module.getProject().getBaseDir(), module);
		reload(file.getName(), module.getProject());
		return new File(file.getPath());
	}

	private boolean unzip(File file, VirtualFile taraDirectory) {
		try {
			ZipUtil.unzip(null, new File(taraDirectory.getPath()), new File(file.getPath()), null, null, false);
			return true;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			error(file);
			return false;
		}
	}

	private void error(File file) {
		Notifications.Bus.notify(new Notification("Tara Language", "Error reading file.", file.getName(), NotificationType.ERROR));
	}

	private void success(Project project, String fileName) {
		Notifications.Bus.notify(new Notification("Tara Language", "Language reloaded successfully", fileName, NotificationType.INFORMATION), project);
	}

	private void pom(VirtualFile projectDirectory, Module module) {
		final File projectDir = new File(projectDirectory.getPath());
		try {
			customizePom(projectDirectory, module.getName());
			syncPom(module, projectDir);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void customizePom(VirtualFile projectDirectory, String module) throws IOException {
		final File pom = new File(projectDirectory.getPath(), TEMP_POM_XML);
		if (!pom.exists()) return;
		final Path pomPath = pom.toPath();
		String pomContent = new String(Files.readAllBytes(pomPath)).replace(MODULE_TAG, module);
		Files.write(pomPath, pomContent.getBytes());
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
}
