package siani.tara.intellij.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.project.facet.TaraFacet;

public class LanguageReloadAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = LangDataKeys.PROJECT.getData(e.getDataContext());
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
			if (facet == null) continue;
			final String dsl = facet.getConfiguration().getDsl();
			final Language language = TaraLanguage.getLanguage(dsl, project);
			if (language == null)
				Notifications.Bus.notify(new Notification("Model Reload", "", "language" + dsl + " cannot be reloaded", NotificationType.ERROR), project);
		}
	}
}
