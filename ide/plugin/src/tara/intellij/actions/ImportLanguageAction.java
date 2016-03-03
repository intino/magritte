package tara.intellij.actions;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.framework.LanguageImporter;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.IOException;
import java.util.List;

import static com.intellij.notification.NotificationType.ERROR;
import static com.intellij.notification.NotificationType.INFORMATION;
import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.lang.LanguageManager.applyRefactors;
import static tara.intellij.lang.LanguageManager.reloadLanguage;
import static tara.intellij.messages.MessageProvider.message;

public class ImportLanguageAction extends AnAction implements DumbAware {
	@Override
	public void update(@NotNull AnActionEvent e) {
		int moduleCount = 0;
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project != null)
			for (Module aModule : ModuleManager.getInstance(project).getModules())
				if (TaraFacet.isOfType(aModule))
					moduleCount++;
		boolean enabled = false;
		if (moduleCount > 1) enabled = true;
		else if (moduleCount > 0) {
			final Module module = e.getData(LangDataKeys.MODULE);
			if (module == null || TaraFacet.isOfType(module))
				enabled = true;
		}
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		if (enabled) e.getPresentation().setText(message("update.language"));
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Module module = e.getData(LangDataKeys.MODULE);
		importLanguage(module);
	}

	public void importLanguage(Module module) {
		if (module == null) return;
		final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
		if (conf == null) return;
		saveAll(module.getProject());
		ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator indicator = createProgressIndicator();
			if (!conf.isArtifactoryDsl() && !PROTEO.equals(conf.dsl())) {
				reloadLanguage(conf.dsl(), module.getProject());
				if (indicator != null) indicator.setText2("Applying refactors");
				applyRefactors(conf.dsl(), module.getProject());
			} else importLanguage(module, conf);
		}, message("updating.language"), false, module.getProject());
		if (conf.dsl().isEmpty()) error(module.getProject());
		if (!conf.dsl().isEmpty()) success(module.getProject(), conf.dsl(), conf.getDslVersion(module));
		reloadProject();
	}

	private void importLanguage(Module module, TaraFacetConfiguration conf) {
		if (PROTEO.equals(conf.dsl())) updateProteoVersion(module, conf);
		else {
			LanguageImporter importer = new LanguageImporter(module);
			importer.importLanguage(conf.dsl(), LanguageInfo.LATEST_VERSION);
		}
	}

	private void updateProteoVersion(Module module, TaraFacetConfiguration conf) {
		try {
			ArtifactoryConnector connector = new ArtifactoryConnector(null);
			final List<String> versions = connector.versions(PROTEO);
			conf.setDslVersion(module, versions.get(versions.size() - 1));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void success(Project project, String language, String version) {
		final Notification notification = new Notification("Tara Language", "Language updated successfully", language + " " + version, INFORMATION).setImportant(true);
		Notifications.Bus.notify(notification, project);
	}

	private void error(Project project) {
		final Notification notification = new Notification("Tara Language", "Language importation error", "Language name is empty", ERROR).setImportant(true);
		Notifications.Bus.notify(notification, project);
	}

	@Nullable
	private ProgressIndicator createProgressIndicator() {
		final ProgressIndicator indicator = ProgressManager.getInstance().getProgressIndicator();
		if (indicator != null) {
			indicator.setText(message("import.language.message"));
			indicator.setIndeterminate(true);
		}
		return indicator;
	}

	public void saveAll(Project project) {
		project.save();
		ApplicationManager.getApplication().invokeLater(() -> FileDocumentManager.getInstance().saveAllDocuments());
		ProjectManagerEx.getInstanceEx().blockReloadingProjectOnExternalChanges();
	}

	private void reloadProject() {
		SaveAndSyncHandlerImpl.getInstance().refreshOpenFiles();
//		VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
	}
}
