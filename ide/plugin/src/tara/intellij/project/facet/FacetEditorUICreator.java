package tara.intellij.project.facet;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.settings.ArtifactorySettings;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;
import static tara.intellij.project.facet.TaraFacet.of;

public class FacetEditorUICreator {
	private static final String PROTEO_LIB = "lib/Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";
	private final TaraFacetEditor editor;
	private final TaraFacetConfiguration conf;
	private final int platform = 2;
	private final int application = 1;
	private final int system = 0;
	private Module[] candidates;
	private List<String> versions = new ArrayList<>();

	public FacetEditorUICreator(TaraFacetEditor editor, TaraFacetConfiguration configuration) {
		this.editor = editor;
		this.conf = configuration;
		this.candidates = getParentModulesCandidates();
		editor.moduleInfo = collectModulesInfo();
		editor.languages.put(PROTEO, new AbstractMap.SimpleEntry<>(2, new File(PROTEO_DIRECTORY, PROTEO_LIB)));
	}

	public void createUI() {
		createDslBox();
		addGeneratedLanguageName();
		selectLevel(conf.getLevel());
		if (conf.getLevel() == system) {
			editor.outputDsl.setEnabled(false);
			editor.outputDslLabel.setEnabled(false);
		}
		updateDslBox(conf.dsl());
		updateValues();
		getVersions();
		initVersionBox();
		addListeners();
		initUpdateButton();
	}

	public void getVersions() {
		if (!conf.isArtifactoryDsl()) return;
		try {
			ArtifactoryConnector connector = new ArtifactoryConnector(ArtifactorySettings.getSafeInstance(editor.context.getProject()));
			versions = connector.versions(conf.dsl());
			Collections.reverse(versions);
		} catch (IOException ignored) {
		}
	}

	private void initVersionBox() {
		if (!versions.contains(conf.getDslVersion())) editor.versionBox.addItem(conf.getDslVersion());
		for (String version : versions) editor.versionBox.addItem(version);
		editor.versionBox.setSelectedItem(conf.getDslVersion());
	}

	public void createDslBox() {
		updateDslBox(conf.dsl());
		editor.inputDsl.addActionListener(e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			updateValues();
		});
	}

	private void updateDslBox(String selection) {
		editor.inputDsl.removeAllItems();
		if (selectedLevel() == platform) editor.inputDsl.addItem(LanguageInfo.PROTEO);
		else {
			availableModuleDsls();
			if (selection != null && !contains(selection)) editor.inputDsl.addItem(selection);
			empty();
		}
		if (selection != null) editor.inputDsl.setSelectedItem(selection);
	}

	private boolean contains(String selection) {
		for (int i = 0; i < editor.inputDsl.getItemCount(); i++)
			if (editor.inputDsl.getItemAt(i).toString().equals(selection)) return true;
		return false;
	}

	private void updateValues() {
		editor.dynamicLoadCheckBox.setEnabled(conf.getLevel() == platform);
		editor.testBox.setVisible(selectedLevel() == system);
		if (conf.getLevel() == platform) editor.dynamicLoadCheckBox.setSelected(conf.isDynamicLoad());
		else {
			if (conf.getLevel() == system) editor.testBox.setSelected(conf.isTest());
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
			if (entry.getValue().generatedDslName.equals(editor.inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void availableModuleDsls() {
		final int selectedLevel = selectedLevel();
		editor.moduleInfo.entrySet().stream().
			filter(entry -> entry.getValue().level == selectedLevel + 1).
			forEach(entry -> editor.inputDsl.addItem(entry.getValue().generatedDslName));
	}

	private void empty() {
		if (editor.inputDsl.getItemCount() == 0) {
			editor.inputDsl.addItem("");
			editor.inputDsl.setSelectedItem("");
		}
	}

	private void addListeners() {
		editor.modelType.addItemListener(e -> {
			final int selected = 2 - ((JComboBox) e.getSource()).getSelectedIndex();
			if (selected == platform) {
				editor.outputDslLabel.setEnabled(true);
				editor.outputDsl.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.dynamicLoadCheckBox.setEnabled(true);
			} else if (selected == application) {
				editor.outputDslLabel.setEnabled(true);
				editor.outputDsl.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.dynamicLoadCheckBox.setEnabled(false);
			} else {
				editor.outputDslLabel.setEnabled(false);
				editor.outputDsl.setEnabled(false);
				editor.testBox.setVisible(true);
				editor.dynamicLoadCheckBox.setEnabled(false);
			}
			initUpdateButton();
		});
	}

	private void selectLevel(int level) {
		editor.modelType.setSelectedIndex(Math.abs(level - 2));
	}

	public int selectedLevel() {
		return 2 - editor.modelType.getSelectedIndex();
	}

	public void initUpdateButton() {
		editor.update.setContentAreaFilled(false);
		editor.update.addActionListener(e -> {
			editor.reload();
			initVersionBox();
		});
		final int versions = countVersions();
		editor.update.setVisible(versions != 0);
		editor.reloadLabel.setVisible(versions != 0);
	}

	private int countVersions() {
		if (conf.dsl().isEmpty() || editor.inputDsl.getSelectedItem() == null || !conf.dsl().equals(editor.inputDsl.getSelectedItem().toString()))
			return 0;
		if (versions.isEmpty()) return 0;
		return Integer.parseInt(versionNumber(versions.get(0))) - Integer.parseInt(versionNumber(conf.getDslVersion()));
	}

	private String versionNumber(String version) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(version);
		String result = "";
		while (m.find()) result += m.group() + m.start() + m.end();
		return result;
	}

	private Map<Module, ModuleInfo> collectModulesInfo() {
		Map<Module, ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.of(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			map.put(candidate, new ModuleInfo(configuration.outputDsl(), configuration.getLevel()));
		}
		return map;
	}

	private void addGeneratedLanguageName() {
		editor.outputDsl.setText(conf.outputDsl());
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
