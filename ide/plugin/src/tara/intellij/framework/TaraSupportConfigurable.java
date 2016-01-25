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
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	private Map<String, LanguageInfo> languages = new LinkedHashMap<>();
	private Module[] candidates;
	private final int Platform = 2;
	private final int Application = 1;
	private final int System = 0;
	private JPanel myMainPanel;
	private JPanel modelPanel;
	private JPanel advanced;
	private JLabel dslName;
	private JComboBox dslBox;
	private JTextField dslGeneratedName;
	private JComboBox modelType;
	private JCheckBox dynamicLoadCheckBox;
	private JCheckBox testBox;
	private ActionListener dslListener;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
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
		dslBox.removeActionListener(dslListener);
		fillDslBox(selection);
		dslListener = e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			final String selectedItem = dslBox.getSelectedItem().toString();
			if (IMPORT.equals(selectedItem)) importLanguage();
			updateDynamicLoadOption();
		};
		dslBox.addActionListener(dslListener);
	}

	private void updateDynamicLoadOption() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return;
		final TaraFacetConfiguration configuration = TaraFacet.of(parent).getConfiguration();
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
	}

	private void addListeners() {
		modelType.addItemListener(e -> {
			final int selected = 2 - ((JComboBox) e.getSource()).getSelectedIndex();
			if (selected == Platform) {
				dslName.setEnabled(true);
				dslGeneratedName.setEnabled(true);
				testBox.setVisible(false);
				dynamicLoadCheckBox.setEnabled(true);
			} else if (selected == Application) {
				dslName.setEnabled(true);
				dslGeneratedName.setEnabled(true);
				testBox.setVisible(false);
				dynamicLoadCheckBox.setEnabled(false);
			} else {
				dslName.setEnabled(false);
				dslGeneratedName.setEnabled(false);
				testBox.setVisible(true);
				dynamicLoadCheckBox.setEnabled(false);
			}
			updateDslBox(null);
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
		if (dialog.isOk()) {
			languages.put(dialog.name(), new LanguageInfo(dialog.name(), dialog.language(), dialog.selectedVersion()));
			return dialog.name();
		}
		return null;
	}

	private void fillDslBox(String selection) {
		dslBox.removeAllItems();
		availableModuleDsls();
		availableLanguages().forEach(dslBox::addItem);
		empty();
		if (selection != null) dslBox.setSelectedItem(selection);
	}

	private List<LanguageInfo> availableLanguages() {
		List<LanguageInfo> list = new ArrayList<>();
		if (selectedLevel() == Platform) list.add(LanguageInfo.PROTEO);
		else {
			list.addAll(languages.values());
			dslBox.addItem(IMPORT);
		}
		return list;
	}

	private void availableModuleDsls() {
		final int selectedLevel = selectedLevel();
		moduleInfo.entrySet().stream().
			filter(entry -> entry.getValue().level == selectedLevel + 1).
			forEach(entry -> dslBox.addItem(entry.getValue().generatedDslName));
	}

	private void empty() {
		if (dslBox.getItemCount() == 0 || (dslBox.getItemCount() == 1 && dslBox.getSelectedItem().equals(IMPORT))) {
			dslBox.addItem("");
			dslBox.setSelectedItem("");
		}
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
		provider.level = selectedLevel();
		provider.dslGenerated = selectedLevel() == System ? NONE : dslGeneratedName.getText();
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.selectedModuleParent = getSelectedParentModule();
		provider.test = dslBox.getSelectedIndex() == System && testBox.isSelected();
		provider.addSupport(module, rootModel);
	}

	public int selectedLevel() {
		return 2 - modelType.getSelectedIndex();
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

	private void createUIComponents() {
		advanced = new HideableTitledPanel("Advanced", false);
		testBox = new JBCheckBox("Test system", false);
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
