package tara.intellij.actions;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import siani.lasso.Lasso;
import siani.lasso.LassoComment;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class ImportLanguageAction extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ImportLanguageAction.class.getName());
	private static final String MODULE_TAG = "##ChangeIt##";
	private static final String POM_XML = "pom.xml";
	private static final String TEMP_POM_XML = "_pom.xml";

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Module module = LangDataKeys.MODULE.getData(e.getDataContext());
		if (module == null) return;
		try {
			final File file = importLanguage(module);
			if (file == null) return;
			final TaraFacet facet = TaraFacet.of(module);
			if (facet == null) return;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			configuration.setImportedLanguagePath(file.getAbsolutePath());
			notify(module.getProject(), file.getName());
		} catch (IOException ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	private void notify(Project project, String fileName) {
		Notifications.Bus.notify(new Notification("Tara Language", "Language reloaded successfully", fileName, NotificationType.INFORMATION), project);
	}

	public File importLanguage(Module module) throws IOException {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		return sourceExists(configuration) ?
			doImportLanguage(module, VfsUtil.findFileByIoFile(new File(configuration.getImportedLanguagePath()), true)) :
			doImportLanguage(module);
	}

	private boolean sourceExists(TaraFacetConfiguration configuration) {
		return configuration.getImportedLanguagePath() != null && !configuration.getImportedLanguagePath().isEmpty() && new File(configuration.getImportedLanguagePath()).exists();
	}

	private File doImportLanguage(Module module) throws IOException {
		Project project = module.getProject();
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), project, project.getBaseDir());
		if (file != null) doImportLanguage(module, file);
		return file != null ? new File(file.getPath()) : null;
	}

	private File doImportLanguage(Module module, VirtualFile file) throws IOException {
		Project project = module.getProject();
		saveAll(project);
		ZipUtil.unzip(null, new File(project.getBaseDir().getPath()), new File(file.getPath()), null, null, false);
		final File languagesPath = TaraLanguage.getLanguagesDirectory(project);
		pom(project.getBaseDir(), module);
		reload(file.getName(), languagesPath.getPath());
		return new File(file.getPath());
	}

	private void pom(VirtualFile projectDirectory, Module module) {
		final File projectDir = new File(projectDirectory.getPath());
		try {
			customizePom(projectDirectory, module.getName());
			syncPom(module, projectDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void customizePom(VirtualFile projectDirectory, String module) throws IOException {
		final File pom = new File(projectDirectory.getPath(), TEMP_POM_XML);
		if (!pom.exists()) return;
		final Path pomPath = pom.toPath();
		String pomContent = new String(Files.readAllBytes(pomPath)).replace(MODULE_TAG, module);
		Files.write(pomPath, pomContent.getBytes(), TRUNCATE_EXISTING);
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

	private void reload(String fileName, String languagesPath) {
		File reload = new File(languagesPath, getPresentableName(fileName) + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
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

	@NotNull
	private String getPresentableName(String fileName) {
		return fileName != null ? fileName.substring(0, fileName.lastIndexOf('.')) : "";
	}
}
