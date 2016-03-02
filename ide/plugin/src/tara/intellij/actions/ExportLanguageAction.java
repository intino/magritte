package tara.intellij.actions;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileStatusNotification;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.roots.ui.configuration.ChooseModulesDialog;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intellij.openapi.vcs.VcsShowConfirmationOption.STATIC_SHOW_CONFIRMATION;
import static com.intellij.util.ui.ConfirmationDialog.requestForConfirmation;
import static tara.intellij.messages.MessageProvider.message;

public class ExportLanguageAction extends ExportLanguageAbstractAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		successMessages.clear();
		errorMessages.clear();
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project == null) return;
		List<Module> taraModules = loadModules(project);
		ChooseModulesDialog dialog = createDialog(project, taraModules);
		dialog.show();
		if (dialog.isOK()) export(dialog.getChosenElements(), project);
	}

	private ChooseModulesDialog createDialog(Project project, List<Module> taraModules) {
		final ChooseModulesDialog chooseModulesDialog = new ChooseModulesDialog(project,
			taraModules,
			message("select.tara.module.title"),
			message("select.tara.module.description"));
		chooseModulesDialog.setSingleSelectionMode();
		chooseModulesDialog.selectElements(Collections.singletonList(taraModules.get(0)));
		return chooseModulesDialog;
	}

	public void export(final List<Module> modules, Project project) {
		final CompilerManager compilerManager = CompilerManager.getInstance(project);
		compilerManager.make(compilerManager.createModulesCompileScope(modules.toArray(new Module[modules.size()]), true), export(modules));
	}

	public CompileStatusNotification export(final List<Module> modules) {
		return new CompileStatusNotification() {
			public void finished(final boolean aborted, final int errors, final int warnings, final CompileContext compileContext) {
				if (aborted || errors != 0) return;
				finish();
			}

			private void finish() {
				doExport(modules);
			}
		};
	}

	private void doExport(List<Module> modules) {
		ApplicationManager.getApplication().invokeLater(() -> {
			deployLanguage(modules);
			if (!errorMessages.isEmpty())
				Messages.showErrorDialog(errorMessages.iterator().next(), message("error.occurred"));
			else if (!successMessages.isEmpty()) processMessages(successMessages, modules);
		});
	}

	private void deployLanguage(final List<Module> modules) {
		saveAll(modules.get(0).getProject());
		for (Module module : modules)
			if (checkOverrideVersion(module) && !deploy(module)) return;
		reloadProject();
	}

	private boolean checkOverrideVersion(Module module) {
		final MavenProject mavenProject = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		return mavenProject != null && (!exists(module, mavenProject.getMavenId().getVersion()) ||
			requestForConfirmation(STATIC_SHOW_CONFIRMATION, module.getProject(), message("artifactory.overrides"), "Artifactory", TaraIcons.LOGO_80));

	}

	private boolean exists(Module module, String version) {
		try {
			return new ArtifactoryConnector(null).versions(TaraUtil.getFacetConfiguration(module).outputDsl()).contains(version);
		} catch (IOException e) {
			return false;
		}
	}

	public void saveAll(Project project) {
		project.save();
		FileDocumentManager.getInstance().saveAllDocuments();
		ProjectManagerEx.getInstanceEx().blockReloadingProjectOnExternalChanges();
	}

	private void reloadProject() {
		SaveAndSyncHandlerImpl.getInstance().refreshOpenFiles();
		VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
	}

	private List<Module> loadModules(Project project) {
		List<Module> taraModules = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (TaraFacet.isOfType(aModule) && TaraFacet.of(aModule).getClass() != null && !TaraFacet.of(aModule).getConfiguration().isM0())
				taraModules.add(aModule);
		return taraModules;
	}

	private void processMessages(List<String> successMessages, List<Module> modules) {
		StringBuilder messageBuf = new StringBuilder();
		for (String message : successMessages) {
			if (messageBuf.length() != 0) messageBuf.append('\n');
			messageBuf.append(message);
		}
		notify(modules.get(0).getProject(), messageBuf.toString(), modules.size() == 1 ?
			message("success.deployment.message", modules.get(0).getName()) :
			message("success.language.exportation.message"));
	}

	private void notify(Project project, String title, String body) {
		Notifications.Bus.notify(new Notification("Tara Language", title, body, NotificationType.INFORMATION), project);
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
		if (enabled) e.getPresentation().setText(message("export.language"));
	}
}
