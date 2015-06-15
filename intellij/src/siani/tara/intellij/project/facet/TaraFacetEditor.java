package siani.tara.intellij.project.facet;

import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import siani.tara.intellij.framework.MavenHelper;
import siani.tara.intellij.framework.MavenManager;

import javax.swing.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static siani.tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class TaraFacetEditor extends FacetEditorTab {

	private static final String PROTEO = "Proteo";
	private static final String NONE = "";

	private final TaraFacetConfiguration facetConfiguration;
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

	public TaraFacetEditor(TaraFacetConfiguration facetConfiguration, Module module) {
		this.module = module;
		candidates = getParentModulesCandidates();
		this.facetConfiguration = facetConfiguration;
		moduleInfo = collectModulesInfo();
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		dslBox.addItem(PROTEO);
		level.setValue(2);
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
			if (facetConfiguration.getDsl().equals(entry.getValue().getKey()))
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
		dslGeneratedName.setText(facetConfiguration.getGeneratedDslName());
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
		return !getDslGeneratedName().equals(facetConfiguration.getGeneratedDslName()) ||
			!dictionaryBox.getSelectedItem().equals(facetConfiguration.getDictionary()) ||
			!dslBox.getSelectedItem().equals(facetConfiguration.getDsl()) ||
			!plateRequired.isSelected() == (facetConfiguration.isPlateRequired());
	}

	public void apply() {
		updateDependencies(searchParentByDslGeneration(dslBox.getSelectedItem().toString()));
		facetConfiguration.setDsl((String) dslBox.getSelectedItem());
		facetConfiguration.setDictionary((String) dictionaryBox.getSelectedItem());
		facetConfiguration.setGeneratedDslName(getDslGeneratedName());
		facetConfiguration.setPlateRequired(plateRequired.isSelected());
		facetConfiguration.setLevel((Integer) level.getValue());
	}

	private String getDslGeneratedName() {
		return dslGeneratedName.isEnabled() ? dslGeneratedName.getText() : NONE;
	}

	private void updateDependencies(final Module newParent) {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project == null) mavenize();
		else {
			MavenHelper helper = new MavenHelper(module, project);
			Module parent = getSavedParentModule();
			if (parent != null) helper.remove(parent);
			if (newParent != null) helper.add(newParent);
		}
	}

	private void mavenize() {
		new MavenManager(dslBox.getSelectedItem().toString(), module).mavenize();
	}

	private Module getSavedParentModule() {
		String dsl = facetConfiguration.getDsl();
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
		dslBox.setSelectedItem(facetConfiguration.getDsl());
		dictionaryBox.setSelectedItem(facetConfiguration.getDictionary());
		setLevel(facetConfiguration.getLevel(), facetConfiguration.getDsl().equals(PROTEO));
		dslGeneratedName.setText(facetConfiguration.getGeneratedDslName());
		plateRequired.setSelected(facetConfiguration.isPlateRequired());
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
