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
import com.intellij.ui.HideableTitledPanel;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.actions.dialog.ImportFrameworkDialog;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static tara.intellij.lang.TaraLanguage.PROTEO;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String NONE = "";
	private static final String IMPORT = "Import...";
	private static final String PLATFORM_PRODUCT_LINE = "Platform (Product Line)";
	private static final String APPLICATION_PRODUCT = "Application (Product)";
	private static final String APPLICATION_ONTOLOGY = "Application (Ontology)";
	private static final String SYSTEM = "System";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	private Map<String, LanguageInfo> languages = new LinkedHashMap<>();
	private Module[] candidates;
	private Map<String, Integer> levels = new LinkedHashMap<>();
	private JPanel myMainPanel;
	private JPanel modelPanel;
	private JPanel advanced;
	private JLabel outputDsl;
	private JTextField outputDslName;
	private JComboBox inputDsl;
	private JComboBox modelType;
	private JCheckBox dynamicLoadCheckBox;
	private JCheckBox testBox;
	private ActionListener dslListener;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
		levels.put(PLATFORM_PRODUCT_LINE, 2);
		levels.put(APPLICATION_PRODUCT, 1);
		levels.put(APPLICATION_ONTOLOGY, 1);
		levels.put(SYSTEM, 0);
		this.provider = provider;
		this.project = model.getProject();
		this.candidates = getParentModulesCandidates(project);
		this.moduleInfo = collectModulesInfo();
		model.addFrameworkListener(this);
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		updateDslBox(PROTEO);
		addListeners();
		modelPanel.setBorder(null);
		myMainPanel.revalidate();
		return myMainPanel;
	}

	public void updateDslBox(String selection) {
		inputDsl.removeActionListener(dslListener);
		fillDslBox(selection);
		dslListener = e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			final String selectedItem = inputDsl.getSelectedItem().toString();
			if (IMPORT.equals(selectedItem)) importLanguage();
			updateDynamicLoadOption();
		};
		inputDsl.addActionListener(dslListener);
	}

	private void updateDynamicLoadOption() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return;
		final TaraFacetConfiguration configuration = TaraFacet.of(parent).getConfiguration();
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
	}

	private void addListeners() {
		modelType.addItemListener(e -> {
			final String selected = ((JComboBox) e.getSource()).getSelectedItem().toString();
			if (PLATFORM_PRODUCT_LINE.equals(selected)) {
				outputDsl.setEnabled(true);
				outputDslName.setEnabled(true);
				testBox.setVisible(false);
				dynamicLoadCheckBox.setEnabled(true);
			} else if (selected.equals(APPLICATION_PRODUCT) || APPLICATION_ONTOLOGY.equals(selected)) {
				outputDsl.setEnabled(true);
				outputDslName.setEnabled(true);
				testBox.setVisible(false);
				dynamicLoadCheckBox.setEnabled(false);
			} else {
				outputDsl.setEnabled(false);
				outputDslName.setEnabled(false);
				testBox.setVisible(true);
				outputDslName.setText("");
				dynamicLoadCheckBox.setEnabled(false);
			}
			updateDslBox(null);
			updateDynamicLoadOption();
		});
	}

	private void importLanguage() {
		final String importedLanguage = createImportDialog();
		if (importedLanguage == null) return;
		fillDslBox(importedLanguage);
	}

	private String createImportDialog() {
		final ImportFrameworkDialog dialog = new ImportFrameworkDialog();
		dialog.pack();
		dialog.setLocationRelativeTo(dialog.getParent());
		dialog.setVisible(true);
		if (dialog.isOk() && !dialog.language().isEmpty()) {
			languages.put(dialog.language(), new LanguageInfo(dialog.language(), dialog.selectedVersion()));
			return dialog.name();
		}
		return null;
	}

	private void fillDslBox(String selection) {
		inputDsl.removeAllItems();
		availableModuleDsls();
		final List<LanguageInfo> languageInfos = importedLanguages();
		languageInfos.forEach(inputDsl::addItem);
		empty();
		if (selection != null)
			inputDsl.setSelectedItem(getLanguageInfo(languageInfos, selection) != null ? getLanguageInfo(languageInfos, selection) : selection);
	}

	private LanguageInfo getLanguageInfo(List<LanguageInfo> languageInfos, String selection) {
		for (LanguageInfo languageInfo : languageInfos) if (languageInfo.getName().equals(selection)) return languageInfo;
		return null;
	}

	private List<LanguageInfo> importedLanguages() {
		List<LanguageInfo> list = new ArrayList<>();
		final String modelType = this.modelType.getSelectedItem().toString();
		if (modelType.equals(PLATFORM_PRODUCT_LINE) || modelType.equals(APPLICATION_ONTOLOGY)) list.add(LanguageInfo.PROTEO);
		else {
			list.addAll(languages.values());
			inputDsl.addItem(IMPORT);
		}
		return list;
	}

	private void availableModuleDsls() {
		final int selectedLevel = selectedLevel();
		moduleInfo.entrySet().stream().
			filter(entry -> entry.getValue().level == selectedLevel + 1).
			forEach(entry -> inputDsl.addItem(entry.getValue().generatedDslName));
	}

	private void empty() {
		if (inputDsl.getItemCount() == 0 || (inputDsl.getItemCount() == 1 && inputDsl.getSelectedItem().equals(IMPORT))) {
			inputDsl.addItem("");
			inputDsl.setSelectedItem("");
		}
	}

	@Override
	public void addSupport(@NotNull Module module,
						   @NotNull ModifiableRootModel rootModel,
						   @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		if (inputDsl.getSelectedItem() instanceof LanguageInfo && !inputDsl.getSelectedItem().toString().equals(PROTEO)) {
			final LanguageInfo selectedItem = (LanguageInfo) inputDsl.getSelectedItem();
			provider.toImport.put(selectedItem.getName(), selectedItem);
		}
		provider.dslName = inputDsl.getSelectedItem().toString();
		provider.level = selectedLevel();
		provider.dslGenerated = selectedLevel() == 0 ? NONE : outputDslName.getText();
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.ontology = modelType.getSelectedItem().toString().equals(APPLICATION_ONTOLOGY);
		provider.selectedModuleParent = getSelectedParentModule();
		provider.test = inputDsl.getSelectedIndex() == 0 && testBox.isSelected();
		provider.addSupport(module, rootModel);
	}

	public int selectedLevel() {
		return levels.get(modelType.getSelectedItem().toString());
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(inputDsl.getSelectedItem().toString()))
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
			map.put(candidate, new ModuleInfo(configuration.outputDsl(), configuration.getLevel()));
		}
		return map;
	}

	private void createUIComponents() {
		advanced = new HideableTitledPanel("Advanced", false);
		testBox = new JBCheckBox("Create test folders", false);
		dynamicLoadCheckBox = new JBCheckBox("Dynamic load model", false);
		final JPanel jbPanel = new JPanel();
		jbPanel.setLayout(new GridLayout(2, 1));
		((HideableTitledPanel) advanced).setContentComponent(jbPanel);
		jbPanel.add(dynamicLoadCheckBox);
		jbPanel.add(testBox);
	}


	@Override
	public void onFrameworkSelectionChanged(boolean selected) {
		testBox.setVisible(false);
	}

	@Override
	public void frameworkSelected(@NotNull FrameworkSupportProvider provider) {
	}

	@Override
	public void frameworkUnselected(@NotNull FrameworkSupportProvider provider) {
	}

	@Override
	public void wizardStepUpdated() {
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
