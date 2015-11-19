package tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbAware;
import tara.lang.refactor.Refactors;

public class ApplyRefactorAction extends AnAction implements DumbAware {
	private Refactors refactors;



	@Override
	public void actionPerformed(AnActionEvent e) {
		final Module module = LangDataKeys.MODULE.getData(e.getDataContext());
//		final TaraFacetConfiguration facetConfiguration = TaraUtil.getFacetConfiguration(file);
//		if (facetConfiguration == null) return;
//		new LanguageRefactor(refactors, facetConfiguration.getRefactorId()).apply(node);
	}

	public void apply(Module module) {
//		refactors=TaraUtil.getRefactors(module);
	}

}