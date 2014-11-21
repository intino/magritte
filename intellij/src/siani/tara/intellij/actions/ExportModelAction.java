package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileStatusNotification;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ChooseModulesDialog;
import com.intellij.openapi.ui.Messages;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.project.module.TaraModuleType;

import java.util.ArrayList;
import java.util.List;

public class ExportModelAction extends ExportModelAbstractAction {

	public ExportModelAction() {
	}

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project == null) return;

		List<Module> taraModules = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			if (TaraModuleType.isOfType(aModule)) {
				taraModules.add(aModule);
			}
		}
		ChooseModulesDialog dialog = new ChooseModulesDialog(project,
			taraModules,
			MessageProvider.message("select.tara.module.title"),
			MessageProvider.message("select.tara.module.description"));
		dialog.show();
		if (dialog.isOK()) {
			doPrepare(dialog.getChosenElements(), project);
		}
	}

	public void doPrepare(final List<Module> modules, Project project) {
		final List<String> errorMessages = new ArrayList<>();
		final List<String> successMessages = new ArrayList<>();
		final CompilerManager compilerManager = CompilerManager.getInstance(project);
		compilerManager.make(compilerManager.createModulesCompileScope(modules.toArray(new Module[modules.size()]), true),
			new CompileStatusNotification() {
				public void finished(final boolean aborted,
				                     final int errors,
				                     final int warnings,
				                     final CompileContext compileContext) {
					if (aborted || errors != 0) return;
					ApplicationManager.getApplication().invokeLater(new Runnable() {
						public void run() {
							for (Module aModule : modules)
								if (!doPrepare(aModule, errorMessages, successMessages)) return;
							if (!errorMessages.isEmpty())
								Messages.showErrorDialog(errorMessages.iterator().next(), MessageProvider.message("error.occurred"));
							else if (!successMessages.isEmpty()) {
								StringBuilder messageBuf = new StringBuilder();
								for (String message : successMessages) {
									if (messageBuf.length() != 0) messageBuf.append('\n');
									messageBuf.append(message);
								}
								Messages.showInfoMessage(messageBuf.toString(),
									modules.size() == 1
										? MessageProvider.message("success.deployment.message", modules.get(0).getName())
										: MessageProvider.message("success.deployment.message.all"));
							}
						}
					});
				}
			});
	}

	@Override
	public void update(AnActionEvent e) {
		int moduleCount = 0;
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project != null)
			for (Module aModule : (ModuleManager.getInstance(project).getModules()))
				if (TaraModuleType.isOfType(aModule))
					moduleCount++;
		boolean enabled = false;
		if (moduleCount > 1) enabled = true;
		else if (moduleCount > 0) {
			final Module module = e.getData(LangDataKeys.MODULE);
			if (module == null || !TaraModuleType.isOfType(module))
				enabled = true;
		}
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		if (enabled) e.getPresentation().setText(MessageProvider.message("prepare.for.deployment"));
	}

}
