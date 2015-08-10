package tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ChooseModulesDialog;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.project.facet.TaraFacet;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeModules extends AnAction implements DumbAware {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project == null) return;
		List<Module> taraModules = loadModules(project);
		ChooseModulesDialog dialog = createDialog(project, taraModules);
		dialog.show();
		if (dialog.isOK()) sync(dialog.getChosenElements(), project);
	}

	private ChooseModulesDialog createDialog(Project project, List<Module> taraModules) {
		final ChooseModulesDialog chooseModulesDialog = new ChooseModulesDialog(project,
			taraModules,
			MessageProvider.message("select.tara.module.title"),
			MessageProvider.message("select.tara.module.description"));
		chooseModulesDialog.setSingleSelectionMode();
		return chooseModulesDialog;
	}

	private List<Module> loadModules(Project project) {
		List<Module> taraModules = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (TaraFacet.isOfType(aModule) && TaraFacet.getTaraFacetByModule(aModule).getClass() != null && !TaraFacet.getTaraFacetByModule(aModule).getConfiguration().isM0())
				taraModules.add(aModule);
		return taraModules;
	}

	public void sync(final List<Module> modules, Project project) {
		final List<String> errorMessages = new ArrayList<>();
		final List<String> successMessages = new ArrayList<>();
		for (Module module : modules) {
			sync(module);
		}
	}

	private void sync(Module module) {

	}


	private void processMessages(List<String> successMessages, List<Module> modules) {
		StringBuilder messageBuf = new StringBuilder();
		for (String message : successMessages) {
			if (messageBuf.length() != 0) messageBuf.append('\n');
			messageBuf.append(message);
		}
		Messages.showInfoMessage(messageBuf.toString(),
			modules.size() == 1
				? MessageProvider.message("success.deployment.message", modules.get(0).getName())
				: MessageProvider.message("success.language.exportation.message"));
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
			if (module == null || !TaraFacet.isOfType(module))
				enabled = true;
		}
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		if (enabled) e.getPresentation().setText(MessageProvider.message("export.language"));
	}

}
