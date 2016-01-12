package tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import tara.intellij.framework.FrameworkImporter;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import static tara.intellij.MessageProvider.message;

public class ImportLanguageAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(AnActionEvent e) {
		final Module module = e.getData(LangDataKeys.MODULE);
		if (module == null) return;
		final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
		if (conf == null) return;
		if (conf.getDslKey().isEmpty()) {
			LanguageManager.reloadLanguage(conf.getDsl(), module.getProject());
			LanguageManager.applyRefactors(conf.getDsl(), module.getProject());
		} else importLanguage(module, conf);
	}

	public void importLanguage(Module module, TaraFacetConfiguration conf) {
		FrameworkImporter importer = new FrameworkImporter(module);
		importer.importLanguage(conf.getDslKey(), LanguageInfo.LATEST_VERSION);
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
