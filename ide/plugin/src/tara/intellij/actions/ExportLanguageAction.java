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
import com.intellij.util.ui.ConfirmationDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.intellij.openapi.vcs.VcsShowConfirmationOption.STATIC_SHOW_CONFIRMATION;
import static tara.intellij.messages.MessageProvider.message;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.ProductLine;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.System;

public class ExportLanguageAction extends ExportLanguageAbstractAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		successMessages.clear();
		errorMessages.clear();
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project == null) return;
		List<Module> taraModules = loadModules(project).stream().filter(m -> !ProductLine.equals(TaraUtil.moduleType(m))).collect(Collectors.toList());
		ChooseModulesDialog dialog = createDialog(project, taraModules);
		dialog.show();
		if (dialog.isOK()) {
			final List<Module> selectedModules = dialog.getChosenElements();
			export(extractDsls(selectedModules), project);
		}
	}

	private Map<Module, String> extractDsls(List<Module> modules) {
		return new HashMap<>();//TODO
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

	private void export(final Map<Module, String> modules, Project project) {
		final CompilerManager compilerManager = CompilerManager.getInstance(project);
		compilerManager.rebuild(export(modules));
	}

	private CompileStatusNotification export(final Map<Module, String> modules) {
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

	private void doExport(Map<Module, String> dslToDeploy) {
		ApplicationManager.getApplication().invokeLater(() -> {
			deployLanguage(dslToDeploy);
			if (!errorMessages.isEmpty())
				Messages.showErrorDialog(errorMessages.iterator().next(), message("error.occurred"));
			else if (!successMessages.isEmpty()) processMessages(successMessages, dslToDeploy);
		});
	}

	private void deployLanguage(final Map<Module, String> modules) {
		saveAll(modules.keySet().iterator().next().getProject());
		for (Module module : modules.keySet())
			if (checkOverrideVersion(module, modules.get(module)) && !deploy(module, modules.get(module))) return;
		reloadProject();
	}

	private boolean checkOverrideVersion(Module module, String dsl) {
		final MavenProject mavenProject = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		ConfirmationDialog dialog = new ConfirmationDialog(module.getProject(), message("artifactory.overrides"), "Artifactory", TaraIcons.LOGO_80, STATIC_SHOW_CONFIRMATION);
		dialog.setDoNotAskOption(null);
		return mavenProject != null && (!exists(module, dsl, mavenProject.getMavenId().getVersion()) || TaraSettings.getSafeInstance(module.getProject()).overrides() ||
			dialog.showAndGet());

	}

	private boolean exists(Module module, String dsl, String version) {
		try {
			return new ArtifactoryConnector(null, new MavenHelper(module).snapshotRepository()).versions(dsl).contains(version);
		} catch (IOException e) {
			return false;
		}
	}

	private void saveAll(Project project) {
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
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			final TaraFacet facet = TaraFacet.of(aModule);
			if (facet != null && !System.equals(facet.getConfiguration().type()))
				taraModules.add(aModule);
		}
		return taraModules;
	}

	private void processMessages(List<String> successMessages, Map<Module, String> modules) {
		StringBuilder messageBuf = new StringBuilder();
		for (String message : successMessages) {
			if (messageBuf.length() != 0) messageBuf.append('\n');
			messageBuf.append(message);
		}
		final Module next = modules.keySet().iterator().next();
		notify(next.getProject(), messageBuf.toString(), modules.size() == 1 ?
			message("success.deployment.message", next.getName()) : message("success.language.exportation.message"));
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
		e.getPresentation().setIcon(TaraIcons.LOGO_16);
		if (enabled) e.getPresentation().setText(message("export.language"));
	}
}
