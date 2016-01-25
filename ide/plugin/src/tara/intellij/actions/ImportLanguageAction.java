package tara.intellij.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.framework.FrameworkImporter;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import static com.intellij.notification.NotificationType.INFORMATION;
import static tara.intellij.MessageProvider.message;

public class ImportLanguageAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(AnActionEvent e) {
		final Module module = e.getData(LangDataKeys.MODULE);
		if (module == null) return;
		final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
		if (conf == null) return;
		ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator progressIndicator = createProgressIndicator();
			if (conf.getDslKey().isEmpty()) {
				progressIndicator.setText2("Reloading Language");
				LanguageManager.reloadLanguage(conf.getDsl(), module.getProject());
				progressIndicator.setText2("Applying refactors");
				LanguageManager.applyRefactors(conf.getDsl(), module.getProject());
			} else importLanguage(module, progressIndicator, conf);
		}, message("import.language"), false, module.getProject());
		success(module.getProject(), conf.getDsl());
	}

	public void importLanguage(Module module, ProgressIndicator progressIndicator, TaraFacetConfiguration conf) {
		progressIndicator.setText2("Importing Language");
		FrameworkImporter importer = new FrameworkImporter(module);
		importer.importLanguage(conf.getDslKey(), LanguageInfo.LATEST_VERSION);
	}

	private void success(Project project, String language) {
		final Notification notification = new Notification("Tara Language", "Language imported successfully", language, INFORMATION).setImportant(true);
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
		if (enabled) e.getPresentation().setText(message("import.language"));
	}
}
