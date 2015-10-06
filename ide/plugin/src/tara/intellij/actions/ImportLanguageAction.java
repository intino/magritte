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
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;

public class ImportLanguageAction extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ImportLanguageAction.class.getName());
	public static final String LANGUAGE_EXTENSION = ".language";

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Module module = LangDataKeys.MODULE.getData(e.getDataContext());
		if (module == null) return;
		try {
			final File file = importLanguage(module);
			if (file == null) return;
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
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
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
		if (facet == null) return null;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		return sourceExists(configuration) ?
			importLanguage(module.getProject(), VfsUtil.findFileByIoFile(new File(configuration.getImportedLanguagePath()), true)) :
			importLanguage(module.getProject());
	}

	public boolean sourceExists(TaraFacetConfiguration configuration) {
		return configuration.getImportedLanguagePath() != null && !configuration.getImportedLanguagePath().isEmpty() && new File(configuration.getImportedLanguagePath()).exists();
	}

	private File importLanguage(Project project) throws IOException {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), project, project.getBaseDir());
		if (file != null) importLanguage(project, file);
		return file != null ? new File(file.getPath()) : null;
	}

	private File importLanguage(Project project, VirtualFile file) throws IOException {
		saveAll(project);
		ZipUtil.unzip(null, new File(project.getBaseDir().getPath()), new File(file.getPath()), null, null, false);
		final File languagesPath = TaraLanguage.getLanguagesDirectory(project);
		reload(file.getName(), languagesPath.getPath());
		return new File(file.getPath());
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
