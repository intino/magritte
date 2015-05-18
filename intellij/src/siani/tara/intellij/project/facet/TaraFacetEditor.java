package siani.tara.intellij.project.facet;

import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import siani.tara.intellij.framework.MavenHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class TaraFacetEditor extends FacetEditorTab {

	private static final String PROTEO = "Proteo";
	private static final String NONE = "";

	private final TaraFacetConfiguration facetConfiguration;
	private final Module module;
	private JComboBox<String> dictionaryBox;
	private JComboBox<String> dslBox;
	private JCheckBox generateDslCheck;
	private JTextField dslGeneratedName;
	private JPanel myMainPanel;
	private JCheckBox addressRequired;
	private Module[] candidates;

	public TaraFacetEditor(TaraFacetConfiguration facetConfiguration, Module module) {
		this.module = module;
		candidates = getParentModulesCandidates();
		this.facetConfiguration = facetConfiguration;
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}


	@NotNull
	public JComponent createComponent() {
		dslBox.addItem(PROTEO);
		addModuleDsls();
		addDictionaries();
		addGeneratedLanguageName();
		addCheckListener();
		return myMainPanel;
	}

	private void addModuleDsls() {
		for (Module candidate : candidates) {
			if (getTaraFacetByModule(candidate) == null) continue;
			TaraFacetConfiguration configuration = getTaraFacetByModule(candidate).getConfiguration();
			if (configuration.isCase()) continue;
			dslBox.addItem(configuration.getGeneratedDslName());
			if (facetConfiguration.getDsl().equals(configuration.getGeneratedDslName()))
				dslBox.setSelectedItem(configuration.getGeneratedDslName());
		}
	}

	private void addDictionaries() {
		dictionaryBox.addItem("English");
		dictionaryBox.addItem("Español");
	}


	private void addGeneratedLanguageName() {
		dslGeneratedName.setText(facetConfiguration.getGeneratedDslName());
	}

	private void addCheckListener() {
		generateDslCheck.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dslGeneratedName.setEnabled(((JCheckBox) e.getSource()).isSelected());
				addressRequired.setEnabled(((JCheckBox) e.getSource()).isSelected());
			}
		});
		dslGeneratedName.setEnabled(generateDslCheck.isSelected());

	}

	public boolean isModified() {
		return !getDslGeneratedName().equals(facetConfiguration.getGeneratedDslName()) ||
			!dictionaryBox.getSelectedItem().equals(facetConfiguration.getDictionary()) ||
			!dslBox.getSelectedItem().equals(facetConfiguration.getDsl()) ||
			!addressRequired.isSelected() == (facetConfiguration.isAddressRequired());

	}

	public void apply() {
		updateDependencies(searchParentByDslGeneration(dslBox.getSelectedItem().toString()));
		facetConfiguration.setDsl((String) dslBox.getSelectedItem());
		facetConfiguration.setDictionary((String) dictionaryBox.getSelectedItem());
		facetConfiguration.setGeneratedDslName(getDslGeneratedName());
		facetConfiguration.setAddressRequired(addressRequired.isSelected());
	}

	private String getDslGeneratedName() {
		return generateDslCheck.isSelected() ? dslGeneratedName.getText() : NONE;
	}


	private void updateDependencies(final Module newParent) {
		MavenHelper helper = new MavenHelper(module, MavenProjectsManager.getInstance(module.getProject()).findProject(module));
		Module parent = getSavedParentModule();
		if (parent != null) helper.remove(parent);
		if (newParent != null) helper.add(newParent);
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
		generateDslCheck.setSelected(!facetConfiguration.isCase());
		dslGeneratedName.setText(facetConfiguration.getGeneratedDslName());
		addressRequired.setSelected(facetConfiguration.isAddressRequired());
	}

	private Module[] getParentModulesCandidates() {
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(module.getProject()).getModules()) {
			TaraFacet taraFacet = getTaraFacetByModule(aModule);
			if (taraFacet == null) continue;
			if (!aModule.equals(module) && !taraFacet.getConfiguration().isCase()) moduleCandidates.add(aModule);
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
