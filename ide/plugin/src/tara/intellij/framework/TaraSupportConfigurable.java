package tara.intellij.framework;

import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelListener;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.actions.dialog.ImportFrameworkDialog;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.util.*;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String NONE = "";
	private static final String IMPORT = "Import...";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	private Map<String, LanguageInfo> languages = new LinkedHashMap<>();
	private Module[] candidates;
	private JPanel myMainPanel;
	private JComboBox dslBox;
	private JTextField dslGeneratedName;
	private JCheckBox customizedLayers;
	private JCheckBox dynamicLoadCheckBox;
	private JRadioButton newLanguage;
	private JRadioButton newModel;
	private JLabel dslName;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
		this.provider = provider;
		this.project = model.getProject();
		candidates = getParentModulesCandidates(project);
		moduleInfo = collectModulesInfo();
		model.addFrameworkListener(this);
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		createDslBox();
		addListeners();
		myMainPanel.revalidate();
		return myMainPanel;
	}

	public void createDslBox() {
		updateDslBox(TaraLanguage.PROTEO);
		dslBox.addActionListener(e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			final String selectedItem = dslBox.getSelectedItem().toString();
			if (IMPORT.equals(selectedItem)) importLanguage();
			dynamicLoadCheckBox.setEnabled(TaraLanguage.PROTEO.equals(selectedItem));
			customizedLayers.setEnabled(TaraLanguage.PROTEO.equals(selectedItem));
		});
	}

	private void addListeners() {
		newLanguage.addItemListener(e -> {
			final boolean selected = ((JRadioButton) e.getSource()).isSelected();
			dslName.setEnabled(selected);
			dslGeneratedName.setEnabled(selected);
			updateDslBox(null);
		});
	}

	private void importLanguage() {
		final String importedLanguage = createImportDialog();
		if (importedLanguage == null) return;
		updateDslBox(importedLanguage);
	}

	private String createImportDialog() {
		final ImportFrameworkDialog dialog = new ImportFrameworkDialog();
		dialog.pack();
		dialog.setLocationRelativeTo(dialog.getParent());
		dialog.setVisible(true);
		if (dialog.isOk()) {
			languages.put(dialog.name(), new LanguageInfo(dialog.name(), dialog.language(), dialog.selectedVersion()));
			return dialog.name();
		}
		return null;
	}

	private void updateDslBox(String selection) {
		dslBox.removeAllItems();
		final List<LanguageInfo> availableLanguages = getAvailableLanguages();
		availableLanguages.forEach(dslBox::addItem);
		addModuleDsls();
		addEmpty();
		dslBox.addItem(IMPORT);
		if (selection != null) dslBox.setSelectedItem(selection);
	}

	private void addEmpty() {
		if (dslBox.getItemCount() == 0) {
			dslBox.addItem("");
			dslBox.setSelectedItem("");
		}
	}

	private List<LanguageInfo> getAvailableLanguages() {
		List<LanguageInfo> list = new ArrayList<>(languages.values());
		if (newLanguage.isSelected()) list.add(LanguageInfo.PROTEO);
		return list;
	}

	private void addModuleDsls() {
		moduleInfo.entrySet().stream().
			filter(entry -> !newLanguage.isSelected() ? entry.getValue().level == 1 : entry.getValue().level > 1).
			forEach(entry -> dslBox.addItem(entry.getValue().generatedDslName));
	}

	@Override
	public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
	}

	@Override
	public void frameworkUnselected(@NotNull FrameworkSupportProvider provider) {
	}


	@Override
	public void wizardStepUpdated() {
	}

	@Override
	public void addSupport(@NotNull Module module,
	                       @NotNull ModifiableRootModel rootModel,
	                       @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		if (dslBox.getSelectedItem() instanceof LanguageInfo) {
			final LanguageInfo selectedItem = (LanguageInfo) dslBox.getSelectedItem();
			provider.toImport.put(selectedItem.getName(), selectedItem);
		}
		provider.dslName = dslBox.getSelectedItem().toString();
		provider.level = getLevel();
		provider.dslGenerated = !newModel.isSelected() ? dslGeneratedName.getText() : NONE;
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.customLayers = customizedLayers.isSelected();
		provider.selectedModuleParent = getSelectedParentModule();
		provider.addSupport(module, rootModel);
	}

	public int getLevel() {
		if (provider.dslName.equals(TaraLanguage.PROTEO)) return 2;
		else if (newLanguage.isSelected()) return 1;
		else return 0;
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.of(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
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

	private static class ModuleInfo {
		String generatedDslName;
		int level;

		public ModuleInfo(String generatedDslName, int level) {
			this.generatedDslName = generatedDslName;
			this.level = level;
		}
	}

}
