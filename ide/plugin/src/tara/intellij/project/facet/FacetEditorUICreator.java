package tara.intellij.project.facet;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.settings.TaraSettings;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.project.facet.TaraFacet.of;

public class FacetEditorUICreator {
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
		testBox();
	}

	public void getVersions() {
		if (!conf.isArtifactoryDsl() && !PROTEO.equals(conf.dsl())) return;
		try {
			ArtifactoryConnector connector = new ArtifactoryConnector(TaraSettings.getSafeInstance(editor.context.getProject()));
			versions = connector.versions(conf.dsl());
			Collections.reverse(versions);
		} catch (IOException ignored) {
			System.out.println(ignored.getMessage());
		}
	}

	private void initVersionBox() {
		editor.versionBox.removeAllItems();
		final Module module = editor.context.getModule();
		for (String version : versions) editor.versionBox.addItem(version);
		if (!versions.contains(conf.dslVersion(module))) editor.versionBox.addItem(conf.dslVersion(module));
		editor.versionBox.setSelectedItem(conf.dslVersion(module));
	}

	public void createDslBox() {
		updateDslBox(conf.dsl());
		if (editor.inputDsl.getActionListeners().length == 0)
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
		editor.testBox.setVisible(selectedLevel() == system || selectedLevel() == application);
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
		return false;
	}

	private void testBox() {
		editor.testBox.setSelected(conf.isTest());
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
			editor.updateLanguage(LanguageInfo.LATEST_VERSION);
			initVersionBox();
		});
		final int versions = countVersions();
		editor.update.setVisible(versions != 0);
		editor.reloadLabel.setVisible(versions != 0);
	}

	private int countVersions() {
		if (conf.dsl().isEmpty() || editor.inputDsl.getSelectedItem() == null || !conf.dsl().equals(editor.inputDsl.getSelectedItem().toString()))
			return 0;
		final String dslVersion = conf.dslVersion(editor.context.getModule());
		if (versions.isEmpty() || versions.get(0).isEmpty() || dslVersion.isEmpty()) return 0;
		try {
			return Integer.parseInt(versionNumber(versions.get(0))) - Integer.parseInt(versionNumber(dslVersion));
		} catch (NumberFormatException e) {
			return 0;
		}
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
