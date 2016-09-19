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
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.util.*;
import java.util.stream.Collectors;

import static tara.intellij.messages.MessageProvider.message;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.System;

public class ExportSnapshotLanguageAction extends AnAction implements DumbAware {

	private static final String SNAPSHOT = "-SNAPSHOT";

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = e.getData(CommonDataKeys.PROJECT);
		if (project == null) return;
		List<Module> taraModules = loadModules(project).stream().
			collect(Collectors.toList());
		if (taraModules.isEmpty()) return;
		ChooseModulesDialog dialog = createDialog(project, taraModules);
		dialog.show();
		if (dialog.isOK()) {
			final List<Module> selectedModules = dialog.getChosenElements();
			Map<Module, String> trueVersions = extractTrueVersions(selectedModules);
			setSnapshotVersions(trueVersions);
			export(selectedModules, project);
		}
	}

	private Map<Module, String> extractTrueVersions(List<Module> selectedModules) {
		Map<Module, String> map = new HashMap<>();
		for (Module module : selectedModules) map.put(module, TaraUtil.getFacetConfiguration(module).modelVersion(module));
		return map;
	}

	private void setSnapshotVersions(Map<Module, String> versionMap) {
		for (Map.Entry<Module, String> entry : versionMap.entrySet()) {
			final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(entry.getKey());
			conf.modelVersion(entry.getKey(), nextVersionOf(entry.getValue()));
		}
	}

	private String nextVersionOf(String version) {
		if (version.contains(SNAPSHOT)) return version;
		String result = "";
		final String[] split = version.replace(SNAPSHOT, "").split("\\.");
		for (int i = 0; i < split.length - 1; i++) result += split[i] + ".";
		int index = Integer.parseInt(split[split.length - 1]) + 1;
		return result + index + SNAPSHOT;
	}

	private void export(List<Module> selectedModules, Project project) {
		ExportLanguageAction action = new ExportLanguageAction();
		action.export(extractDsls(selectedModules), project);
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

	private List<Module> loadModules(Project project) {
		List<Module> taraModules = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			final TaraFacet facet = TaraFacet.of(aModule);
			if (facet != null && !System.equals(facet.getConfiguration().type()))
				taraModules.add(aModule);
		}
		return taraModules;
	}

	private Map<Module, String> extractDsls(List<Module> modules) {
		Map<Module, String> map = new HashMap<>();
		for (Module module : modules) {
			final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
			if (conf == null) continue;
			if (!conf.platformOutDsl().isEmpty()) map.put(module, conf.platformOutDsl());
			else if (!conf.applicationOutDsl().isEmpty()) map.put(module, conf.applicationOutDsl());
		}
		return map;
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
	}
}
