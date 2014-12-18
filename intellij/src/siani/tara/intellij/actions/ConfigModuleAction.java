package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.actions.dialog.ConfigModuleDialogPane;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

public class ConfigModuleAction extends AnAction implements DumbAware {
	public static final Logger LOG = Logger.getInstance("Config module Action");

	private static boolean isEnabled(AnActionEvent e) {
		Project project = e.getData(CommonDataKeys.PROJECT);
		final Module module = e.getData(LangDataKeys.MODULE);
		if (project == null || module == null)
			return false;
		final PsiElement dir = e.getData(LangDataKeys.PSI_ELEMENT);
		if (dir instanceof PsiDirectory) {
			PsiDirectory psiDirectory = (PsiDirectory) dir;
			return module.getModuleFile() != null && psiDirectory.getVirtualFile().getPath().equals(module.getModuleFile().getParent().getPath());
		} else return false;
	}

	@Override
	public void update(@NotNull AnActionEvent e) {
		boolean enabled = isEnabled(e);
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		e.getPresentation().setIcon(TaraIcons.getIcon(TaraIcons.ICON_13));
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		if (e.getProject() == null) {
			LOG.error("actionPerformed no project for " + e);
			return;
		}
		final Module module = e.getData(LangDataKeys.MODULE);
		ConfigModuleDialogPane configDialog = new ConfigModuleDialogPane(e.getProject(), module);
		configDialog.getPeer().setTitle("Configure Module " + (module != null ? module.getName() : ""));
		configDialog.show();
		if (configDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
			configDialog.saveValues();
			changeFilesHeaders(module);
		}
	}

	private void changeFilesHeaders(Module module) {
		for (final TaraBoxFileImpl file : TaraUtil.getTaraFilesOfModule(module)) {
			WriteCommandAction action = new WriteCommandAction(file.getProject(), file) {
				@Override
				protected void run(@NotNull Result result) throws Throwable {
					file.updateMetamodelImport();
				}
			};
			action.execute();
		}


	}

}
