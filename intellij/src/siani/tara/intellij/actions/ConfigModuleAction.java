package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.actions.dialog.ConfigModuleDialogPane;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

public class ConfigModuleAction extends AnAction implements DumbAware {
	public static final Logger LOG = Logger.getInstance("Config module Action");

	public ConfigModuleAction() {
		super(TaraIcons.getIcon(TaraIcons.ICON_13));
	}

	private static boolean isEnabled(AnActionEvent e) {
		return true;
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
		Project project = e.getProject();
		if (project == null) {
			LOG.error("actionPerformed no project for " + e);
			return;
		}
		final Module module = e.getData(LangDataKeys.MODULE);
		if (module == null) throw new RuntimeException("Module not found!");
		configureModule(module);
	}

	public void configureModule(Module module) {
		Project project = module.getProject();
		ConfigModuleDialogPane configDialog = new ConfigModuleDialogPane(project, module);
		configDialog.getPeer().setTitle("Configure Module " + (module.getName()));
		configDialog.show();
		if (configDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
			configDialog.saveValues(); //TODO Refactor
			changeFilesHeaders(module);
		}
	}

	private void changeFilesHeaders(Module module) {
		for (final TaraBoxFileImpl file : TaraUtil.getTaraFilesOfModule(module)) {
			WriteCommandAction action = new WriteCommandAction(file.getProject(), file) {
				@Override
				protected void run(@NotNull Result result) throws Throwable {
					file.updateDSL();
				}
			};
			action.execute();
		}
	}


}
