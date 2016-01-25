package tara.intellij.project.facet;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.framework.TaraHubConnector;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;
import static tara.intellij.project.facet.TaraFacet.of;

public class FacetEditorUICreator {
	private static final String PROTEO_LIB = "lib/Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";
	private final TaraFacetEditor editor;
	private final TaraFacetConfiguration conf;
	private final int Platform = 2;
	private final int Application = 1;
	private final int System = 0;
	private Module[] candidates;

	public FacetEditorUICreator(TaraFacetEditor editor, TaraFacetConfiguration configuration) {
		this.editor = editor;
		this.conf = configuration;
		this.candidates = getParentModulesCandidates();
		editor.moduleInfo = collectModulesInfo();
		editor.languages.put(PROTEO, new AbstractMap.SimpleEntry<>(2, new File(PROTEO_DIRECTORY, PROTEO_LIB)));
	}

	public void createUI() {
		createDslBox();
		addListeners();
		addGeneratedLanguageName();
		selectLevel(conf.getLevel());
		updateValues();
		if (conf.getLevel() == System) {
			editor.dslGeneratedName.setEnabled(false);
			editor.dslName.setEnabled(false);
		}
		initReloadButton();
	}

	public void createDslBox() {
		updateDslBox(conf.getDsl());
		editor.dslBox.addActionListener(e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			updateValues();
		});
	}

	private void updateDslBox(String selection) {
		editor.dslBox.removeAllItems();
		if (selectedLevel() == Platform) editor.dslBox.addItem(LanguageInfo.PROTEO);
		else {
			availableModuleDsls();
			empty();
		}
		if (selection != null) editor.dslBox.setSelectedItem(selection);
	}

	private void updateValues() {
		editor.dynamicLoadCheckBox.setEnabled(conf.getLevel() == Platform);
		editor.testBox.setVisible(selectedLevel() == System);
		if (conf.getLevel() == Platform) editor.dynamicLoadCheckBox.setSelected(conf.isDynamicLoad());
		else {
			if (conf.getLevel() == System) editor.testBox.setSelected(conf.isTest());
			resolveDynamicLoadBoxValue();
		}
	}

	private boolean resolveDynamicLoadBoxValue() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return true;
		final TaraFacetConfiguration parentConf = TaraFacet.of(parent).getConfiguration();
		editor.dynamicLoadCheckBox.setSelected(parentConf.isDynamicLoad());
		editor.testBox.setSelected(parentConf.isTest());
		return false;
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, ModuleInfo> entry : editor.moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(editor.dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void availableModuleDsls() {
		final int selectedLevel = selectedLevel();
		editor.moduleInfo.entrySet().stream().
			filter(entry -> entry.getValue().level == selectedLevel + 1).
			forEach(entry -> editor.dslBox.addItem(entry.getValue().generatedDslName));
	}

	private void empty() {
		if (editor.dslBox.getItemCount() == 0) {
			editor.dslBox.addItem("");
			editor.dslBox.setSelectedItem("");
		}
	}

	private void addListeners() {
		editor.modelType.addItemListener(e -> {
			final int selected = 2 - ((JComboBox) e.getSource()).getSelectedIndex();
			if (selected == Platform) {
				editor.dslName.setEnabled(true);
				editor.dslGeneratedName.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.dynamicLoadCheckBox.setEnabled(true);
			} else if (selected == Application) {
				editor.dslName.setEnabled(true);
				editor.dslGeneratedName.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.dynamicLoadCheckBox.setEnabled(false);
			} else {
				editor.dslName.setEnabled(false);
				editor.dslGeneratedName.setEnabled(false);
				editor.testBox.setVisible(true);
				editor.dynamicLoadCheckBox.setEnabled(false);
			}
			updateDslBox(null);
			initReloadButton();
		});
	}

	private void selectLevel(int level) {
		editor.modelType.setSelectedIndex(Math.abs(level - 2));
	}

	public int selectedLevel() {
		return 2 - editor.modelType.getSelectedIndex();
	}

	public void initReloadButton() {
		editor.reload.setContentAreaFilled(false);
		editor.reload.addActionListener(e -> editor.reload());
		final int i = countVersions();
		editor.reload.setVisible(i != 0);
		editor.reloadLabel.setVisible(i != 0);
	}

	private int countVersions() {
		TaraHubConnector connector = new TaraHubConnector();
		if (conf.getDslKey().isEmpty() || !conf.getDsl().equals(editor.dslBox.getSelectedItem().toString())) return 0;
		try {
			final List<String> versions = connector.versions(conf.getDslKey());
			if (versions.isEmpty()) return 0;
			Collections.reverse(versions);
			return Integer.parseInt(versions.get(0)) - Integer.parseInt(conf.getDslVersion());
		} catch (IOException ignored) {
			return 0;
		}
	}

	private Map<Module, ModuleInfo> collectModulesInfo() {
		Map<Module, ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.of(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			map.put(candidate, new ModuleInfo(configuration.getGeneratedDslName(), configuration.getLevel()));
		}
		return map;
	}

	private void addGeneratedLanguageName() {
		editor.dslGeneratedName.setText(conf.getGeneratedDslName());
	}

	private Module[] getParentModulesCandidates() {
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(editor.context.getProject()).getModules()) {
			TaraFacet taraFacet = of(aModule);
			if (taraFacet == null) continue;
			if (!aModule.equals(editor.context) && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	static class ModuleInfo {
		String generatedDslName;
		int level;

		public ModuleInfo(String generatedDslName, int level) {
			this.generatedDslName = generatedDslName;
			this.level = level;
		}

		@Override
		public String toString() {
			return generatedDslName;
		}
	}
}
