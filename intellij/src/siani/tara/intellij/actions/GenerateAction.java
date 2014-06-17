package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import siani.tara.intellij.codegeneration.IntentionsGenerator;
import siani.tara.intellij.lang.psi.TaraFile;

public class GenerateAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = getEventProject(e);
		final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		if (project == null || files == null) return;
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		IntentionsGenerator generator = new IntentionsGenerator(project, (TaraFile) PsiManager.getInstance(project).findFile(files[0]));
		generator.generate();
	}

	@Override
	public void update(AnActionEvent e) {
		Project project = getEventProject(e);
		VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		boolean taraFound = false;
		if (project != null && files != null) {
			PsiManager manager = PsiManager.getInstance(project);
			for (VirtualFile virtualFile : files) {
				PsiFile psiFile = manager.findFile(virtualFile);
				taraFound = psiFile instanceof TaraFile;
				if (taraFound) break;
			}
		}
		e.getPresentation().setEnabled(taraFound);
		e.getPresentation().setVisible(taraFound);
	}


}
