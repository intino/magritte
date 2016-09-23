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
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.framework.LanguageImporter;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.configuration.Configuration;

import static com.intellij.notification.NotificationType.ERROR;
import static com.intellij.notification.NotificationType.INFORMATION;
import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.lang.LanguageManager.applyRefactors;
import static tara.intellij.lang.LanguageManager.reloadLanguage;
import static tara.intellij.messages.MessageProvider.message;
import static tara.intellij.project.configuration.Configuration.ModuleType.*;
import static tara.intellij.project.configuration.Configuration.ModuleType.System;
import static tara.intellij.project.module.ModuleProvider.moduleOf;

public class UpdateLanguageAction extends AnAction implements DumbAware {
	@Override
	public void update(@NotNull AnActionEvent e) {
		int moduleCount = 0;
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project != null)
			for (Module module : ModuleManager.getInstance(project).getModules())
				if (!TaraModuleType.isTara(module)) moduleCount++;
		boolean enabled = false;
		if (moduleCount > 1) enabled = true;
		else if (moduleCount > 0) {
			final Module module = e.getData(LangDataKeys.MODULE);
			if (module == null || !TaraModuleType.isTara(module))
				enabled = true;
		}
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		e.getPresentation().setIcon(TaraIcons.LOGO_16);
		if (enabled) e.getPresentation().setText(message("update.language"));
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		final PsiFile file = e.getData(LangDataKeys.PSI_FILE);
		if (file != null) importLanguage(file, LanguageInfo.LATEST_VERSION);
		else importLanguage(e.getData(LangDataKeys.MODULE), LanguageInfo.LATEST_VERSION);
	}

	private void importLanguage(Module module, String version) {
		saveAll(module.getProject());
		final Configuration conf = TaraUtil.configurationOf(module);
		importDsl(version, module, conf, highestDsl(null, conf));
	}

	private void importLanguage(PsiFile psiFile, String version) {
		final Module module = moduleOf(psiFile);
		saveAll(module.getProject());
		final Configuration conf = TaraUtil.configurationOf(module);
		importDsl(version, module, conf, highestDsl(psiFile, conf));
		reloadProject();
	}

	private void importDsl(String version, Module module, Configuration conf, String dsl) {
		ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator indicator = createProgressIndicator();
			String newVersion = "";
			if (!isImportedDsl(conf, dsl) && !PROTEO.equals(dsl)) reload(module, dsl, indicator);
			else {
				newVersion = importLanguage(module, dsl, version);
				if (dsl.isEmpty()) error(module.getProject());
				if (!dsl.isEmpty()) success(module.getProject(), dsl, conf == null ? newVersion : conf.dslVersion(dsl));
			}
		}, message("updating.language"), true, module.getProject());

	}

	private String highestDsl(PsiFile psiFile, Configuration conf) {
		if (psiFile != null && psiFile instanceof TaraModel) return ((TaraModel) psiFile).dsl();
		if (!conf.dsl(Platform).isEmpty()) return conf.dsl(Platform);
		if (!conf.dsl(Application).isEmpty()) return conf.dsl(Application);
		if (!conf.dsl(System).isEmpty()) return conf.dsl(System);
		return "";
	}

	private boolean isImportedDsl(Configuration conf, String dsl) {
		if (conf == null) return true;
		if (!conf.dsl(Platform).isEmpty() && dsl.equals(conf.dsl(Platform))) return false;
		if (!conf.dsl(Application).isEmpty() && dsl.equals(conf.dsl(Application))) return conf.isApplicationImportedDsl();
		else if (!conf.dsl(System).isEmpty() && dsl.equals(conf.dsl(System))) return conf.isSystemImportedDsl();
		return true;
	}

	private void reload(Module module, String dsl, ProgressIndicator indicator) {
		reloadLanguage(module.getProject(), dsl);
		if (indicator != null) indicator.setText2("Applying refactors");
		applyRefactors(dsl, module.getProject());
	}

	private String importLanguage(Module module, String dsl, String version) {
		return new LanguageImporter(module).importLanguage(dsl, version);
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

	private void saveAll(Project project) {
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
