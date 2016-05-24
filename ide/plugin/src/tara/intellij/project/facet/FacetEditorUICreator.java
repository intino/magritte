package tara.intellij.project.facet;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.project.facet.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.*;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.System;

class FacetEditorUICreator {
	private static final Logger LOG = Logger.getInstance(FacetEditorUICreator.class.getName());

	private final TaraFacetEditor editor;
	private final TaraFacetConfiguration conf;
	private final int platform = 2;
	private final int application = 1;
	private final int system = 0;
	private Module[] candidates;
	private List<String> versions = new ArrayList<>();

	FacetEditorUICreator(TaraFacetEditor editor, TaraFacetConfiguration configuration) {
		this.editor = editor;
		this.conf = configuration;
		this.candidates = getParentModulesCandidates(editor.context.getProject());
		editor.moduleInfo = collectModulesInfo();
	}

	void createUI() {
		createDslBox();
		addOutDsls();
		selectLevel(conf.type());
		if (conf.type() == System) {
			editor.platformOutDsl.setEnabled(false);
			editor.outputDslLabel.setEnabled(false);
		}
//		updateDslBox(conf.dsl());
		updateValues();
		getVersions();
		initVersionBox();
		addListeners();
		initUpdateButton();
		testBox();
	}

	private void addOutDsls() {
		editor.platformOutDsl.setText(conf.platformOutDsl());
		editor.applicationOutDsl.setText(conf.applicationOutDsl());
	}

	private void getVersions() {
		if (!conf.isApplicationImportedDsl() && !PROTEO.equals(conf.platformDsl())) return;
		try {
			ArtifactoryConnector connector = new ArtifactoryConnector(TaraSettings.getSafeInstance(editor.context.getProject()), new MavenHelper(editor.context.getModule()).snapshotRepository());
			versions = connector.versions(conf.languageByModuleType(conf.type()));
			Collections.reverse(versions);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	private void initVersionBox() {
		editor.versionBox.removeAllItems();
		final Module module = editor.context.getModule();
		for (String version : versions) editor.versionBox.addItem(version);
		final String dsl = conf.languageByModuleType(conf.type());
		if (!versions.contains(conf.dslVersion(module, dsl)))
			editor.versionBox.addItem(conf.dslVersion(module, dsl));
		editor.versionBox.setSelectedItem(conf.dslVersion(module, dsl));
	}

	void createDslBox() {
		updateDslBox(conf.languageByModuleType(conf.type()));
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
		editor.lazyLoadCheckBox.setEnabled(conf.type() == Platform);
		if (conf.type() == Platform) editor.lazyLoadCheckBox.setSelected(conf.isLazyLoad());
		else {
			editor.testBox.setSelected(conf.isTest());
			resolveLazyLoadBoxValue();
		}
	}

	private boolean resolveLazyLoadBoxValue() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return true;
		final TaraFacetConfiguration parentConf = TaraFacet.of(parent).getConfiguration();
		editor.lazyLoadCheckBox.setSelected(parentConf.isLazyLoad());
		return false;
	}

	private void testBox() {
		editor.testBox.setSelected(conf.isTest());
	}

	private void availableModuleDsls() {
		final TaraFacetConfiguration.ModuleType selectedType = conf.type();
		editor.moduleInfo.entrySet().stream().
			filter(entry -> parentOf(selectedType).contains(entry.getValue().type())).
			forEach(entry -> editor.inputDsl.addItem(entry.getValue().platformOutDsl()));
	}

	private List<TaraFacetConfiguration.ModuleType> parentOf(TaraFacetConfiguration.ModuleType type) {
		if (type.equals(Application) || type.equals(Ontology))
			return Arrays.asList(Platform, TaraFacetConfiguration.ModuleType.ProductLine);
		if (type.equals(TaraFacetConfiguration.ModuleType.System)) return Arrays.asList(Application, Ontology);
		else return Collections.emptyList();
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
				editor.platformOutDsl.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.lazyLoadCheckBox.setEnabled(true);
			} else if (selected == application) {
				editor.outputDslLabel.setEnabled(true);
				editor.platformOutDsl.setEnabled(true);
				editor.testBox.setVisible(false);
				editor.lazyLoadCheckBox.setEnabled(false);
			} else {
				editor.outputDslLabel.setEnabled(false);
				editor.platformOutDsl.setEnabled(false);
				editor.testBox.setVisible(true);
				editor.lazyLoadCheckBox.setEnabled(false);
			}
			initUpdateButton();
		});
	}

	private void selectLevel(TaraFacetConfiguration.ModuleType type) {
		editor.modelType.setSelectedItem(type.name());
	}

	private int selectedLevel() {
		return 2 - editor.modelType.getSelectedIndex();
	}

	private void initUpdateButton() {
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
		final String dsl = conf.languageByModuleType(conf.type());
		if (dsl.isEmpty() || editor.inputDsl.getSelectedItem() == null || !dsl.equals(editor.inputDsl.getSelectedItem().toString()))
			return 0;
		final String dslVersion = conf.dslVersion(editor.context.getModule(), dsl);
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

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, tara.intellij.project.facet.ModuleInfo> entry : editor.moduleInfo.entrySet())
			if (entry.getValue().platformOutDsl().equals(editor.inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.of(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().type().equals(TaraFacetConfiguration.ModuleType.System))
				moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	private Map<Module, tara.intellij.project.facet.ModuleInfo> collectModulesInfo() {
		Map<Module, tara.intellij.project.facet.ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.of(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration conf = facet.getConfiguration();
			map.put(candidate, new tara.intellij.project.facet.ModuleInfo(conf.type(), conf.platformOutDsl(), conf.applicationOutDsl()));
		}
		return map;
	}
}
