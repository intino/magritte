package siani.tara.intellij.project.facet;

import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TaraFacetEditor extends FacetEditorTab {

	private static final String PROTEO = "Proteo";

	private final TaraFacetConfiguration facetConfiguration;
	private final Module module;
	private JComboBox<String> dictionaryBox;
	private JComboBox<String> dslBox;
	private JCheckBox generateDslCheck;
	private JTextField dslGeneratedName;
	private JPanel myMainPanel;
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
			if (TaraFacet.getTaraFacetByModule(candidate) == null) continue;
			TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(candidate).getConfiguration();
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
			}
		});

	}

	public boolean isModified() {
		return !getDslGeneratedName().equals(facetConfiguration.getGeneratedDslName()) ||
			!dictionaryBox.getSelectedItem().equals(facetConfiguration.getDictionary()) ||
			!dslBox.getSelectedItem().equals(facetConfiguration.getDsl());

	}

	public void apply() {
		setParent(dslBox.getSelectedItem().toString());
		facetConfiguration.setDsl((String) dslBox.getSelectedItem());
		facetConfiguration.setDictionary((String) dictionaryBox.getSelectedItem());
		facetConfiguration.setGeneratedDslName(getDslGeneratedName());
	}

	private String getDslGeneratedName() {
		return generateDslCheck.isSelected() ? dslGeneratedName.getText() : "";
	}


	private void setParent(String selectedItem) {
		Module parentModule;
		if (selectedItem.equals(PROTEO)) {
			updateDependencies(null);
			facetConfiguration.setDsl("");
		} else if ((parentModule = searchParent(selectedItem)) != null) {
			updateDependencies(parentModule);
			facetConfiguration.setDsl(TaraFacet.getTaraFacetByModule(parentModule).getConfiguration().getGeneratedDslName());
		} else {
			updateDependencies(null);
			facetConfiguration.setDsl(selectedItem);
		}
	}

	private void updateDependencies(final Module parent) {
		removeParentDependency();
		if (parent != null) addModelDependency(parent);
	}

	private void addModelDependency(final Module parent) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				if (!ModuleRootManager.getInstance(module).isDependsOn(parent)) {
					ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
					modifiableModel.addModuleOrderEntry(parent);
					modifiableModel.commit();
				}
			}
		});
	}

	private void removeParentDependency() {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				String dsl = facetConfiguration.getDsl();
				if (dsl.isEmpty()) return;
				Module parentModule = searchParent(dsl.contains(".") ? dsl.split("\\.")[1] : dsl);
				if (parentModule == null) return;
				ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
				OrderEntry[] orderEntries = modifiableModel.getOrderEntries();
				OrderEntry orderEntry = findOrderEntry(orderEntries, parentModule);
				if (orderEntry != null)
					modifiableModel.removeOrderEntry(orderEntry);
				modifiableModel.commit();
			}
		});
	}

	private Module searchParent(String parentName) {
		for (Module candidate : getParentModulesCandidates()) {
			TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(candidate).getConfiguration();
			if (configuration.getGeneratedDslName().equals(parentName))
				return candidate;
		}
		return null;
	}

	private OrderEntry findOrderEntry(OrderEntry[] orderEntries, Module parentModule) {
		for (OrderEntry entry : orderEntries)
			if (entry instanceof ModuleOrderEntry && parentModule.equals(((ModuleOrderEntry) entry).getModule()))
				return entry;
		return null;
	}

	public void reset() {
		dslBox.setSelectedItem(facetConfiguration.getDsl());
		dictionaryBox.setSelectedItem(facetConfiguration.getDictionary());
		generateDslCheck.setSelected(!facetConfiguration.isCase());
		dslGeneratedName.setText(facetConfiguration.getGeneratedDslName());
	}

	private Module[] getParentModulesCandidates() {
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(module.getProject()).getModules()) {
			TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
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
