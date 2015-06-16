package siani.tara.intellij.project.facet;

import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static siani.tara.intellij.lang.TaraLanguage.PROTEO;
import static siani.tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class TaraFacetEditor extends FacetEditorTab {

	private static final String NONE = "";

	private final TaraFacetConfiguration configuration;
	private final Module module;
	private final Map<Module, SimpleEntry<String, Integer>> moduleInfo;
	private JComboBox<String> dictionaryBox;
	private JComboBox<String> dslBox;
	private JTextField dslGeneratedName;
	private JPanel myMainPanel;
	private JCheckBox plateRequired;
	private JLabel generativeLabel;
	private JSpinner level;
	private Module[] candidates;

	public TaraFacetEditor(TaraFacetConfiguration configuration, Module module) {
		this.module = module;
		candidates = getParentModulesCandidates();
		this.configuration = configuration;
		moduleInfo = collectModulesInfo();
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		if (configuration.getDsl().equals(PROTEO)) dslBox.addItem(PROTEO);
		level.setValue(configuration.getLevel());
		addModuleDsls();
		addDictionaries();
		addGeneratedLanguageName();
		addListeners();
		return myMainPanel;
	}

	private void addModuleDsls() {
		for (Entry<Module, SimpleEntry<String, Integer>> entry : moduleInfo.entrySet()) {
			if (entry.getValue().getValue().equals(0)) continue;
			dslBox.addItem(entry.getValue().getKey());
			if (configuration.getDsl().equals(entry.getValue().getKey()))
				dslBox.setSelectedItem(entry.getValue().getKey());
		}
	}

	private Map<Module, SimpleEntry<String, Integer>> collectModulesInfo() {
		Map<Module, SimpleEntry<String, Integer>> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = getTaraFacetByModule(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			map.put(candidate, new SimpleEntry<>(configuration.getGeneratedDslName(), configuration.getLevel()));
		}
		return map;
	}

	private void addDictionaries() {
		dictionaryBox.addItem("English");
		dictionaryBox.addItem("Español");
	}

	private void addGeneratedLanguageName() {
		dslGeneratedName.setText(configuration.getGeneratedDslName());
	}

	private void addListeners() {
		dslBox.addItemListener(e -> {
			if (e.getItem().toString().equals(PROTEO))
				setLevel(2, true);
			else moduleInfo.values().stream().
				filter(entry -> entry.getKey().equals(e.getItem().toString())).
				forEach(entry -> setLevel(entry.getValue() - 1, false));
		});
		level.addChangeListener(e -> visibilityOfGenerativeLanguage((Integer) ((JSpinner) e.getSource()).getValue() != 0));
	}

	private void visibilityOfGenerativeLanguage(boolean visibility) {
		generativeLabel.setEnabled(visibility);
		plateRequired.setEnabled(visibility);
		dslGeneratedName.setEnabled(visibility);
	}

	private void setLevel(int level, boolean enabled) {
		this.level.setValue(level);
		this.level.setEnabled(enabled);
	}

	public boolean isModified() {
		return !getDslGeneratedName().equals(configuration.getGeneratedDslName()) ||
			!dictionaryBox.getSelectedItem().equals(configuration.getDictionary()) ||
			!dslBox.getSelectedItem().equals(configuration.getDsl()) ||
			!plateRequired.isSelected() == (configuration.isPlateRequired());
	}

	public void apply() {
//		updateDependencies(searchParentByDslGeneration(dslBox.getSelectedItem().toString()));
		configuration.setDsl((String) dslBox.getSelectedItem());
		configuration.setDictionary((String) dictionaryBox.getSelectedItem());
		configuration.setGeneratedDslName(getDslGeneratedName());
		configuration.setPlateRequired(plateRequired.isSelected());
		configuration.setLevel((Integer) level.getValue());
		configuration.setDslsDirectory(module.getProject().getBasePath() + File.separator + "dsl" + File.separator);
	}

	private String getDslGeneratedName() {
		return dslGeneratedName.isEnabled() ? dslGeneratedName.getText() : NONE;
	}

	private Module getSavedParentModule() {
		String dsl = configuration.getDsl();
		if (dsl.isEmpty()) return null;
		return searchParentByDslGeneration(dsl);
	}

	private Module searchParentByDslGeneration(String dsl) {
		for (Module candidate : getParentModulesCandidates())
			if (getTaraFacetByModule(candidate).getConfiguration().getGeneratedDslName().equals(dsl))
				return candidate;
		return null;
	}

	public void reset() {
		dslBox.setSelectedItem(configuration.getDsl());
		dictionaryBox.setSelectedItem(configuration.getDictionary());
		setLevel(configuration.getLevel(), configuration.getDsl().equals(PROTEO));
		dslGeneratedName.setText(configuration.getGeneratedDslName());
		plateRequired.setSelected(configuration.isPlateRequired());
	}

	private Module[] getParentModulesCandidates() {
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(module.getProject()).getModules()) {
			TaraFacet taraFacet = getTaraFacetByModule(aModule);
			if (taraFacet == null) continue;
			if (!aModule.equals(module) && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}
}
