package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.codegeneration.LinkToJava;
import siani.tara.intellij.lang.psi.TaraModel;

public class GenerateAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Module module = LangDataKeys.MODULE.getData(e.getDataContext());
		if (module != null) LinkToJava.link(module);
	}

	@Override
	public void update(@NotNull AnActionEvent e) {
		Project project = getEventProject(e);
		VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		boolean taraFound = false;
		if (project != null && files != null) {
			PsiManager manager = PsiManager.getInstance(project);
			for (VirtualFile virtualFile : files) {
				PsiFile psiFile = manager.findFile(virtualFile);
				taraFound = psiFile instanceof TaraModel;
				if (taraFound) break;
			}
		}
		e.getPresentation().setEnabled(taraFound);
		e.getPresentation().setVisible(taraFound);
	}
}
