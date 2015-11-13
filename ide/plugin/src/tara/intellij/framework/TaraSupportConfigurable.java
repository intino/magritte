package tara.intellij.framework;

import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelListener;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.actions.dialog.SourceProjectChooserDescriptor;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.io.File;
import java.util.*;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String PROTEO_LIB = "Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";

	private static final String NONE = "";
	private static final String IMPORT = "Import...";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	Map<String, File> languages = new LinkedHashMap<>();
	Map<String, File> importedLanguages = new LinkedHashMap<>();
	private JPanel myMainPanel;
	private JComboBox dslBox;
	private JTextField dslGeneratedName;
	private JPanel generatedLanguagePane;
	private JCheckBox customizedMorphs;
	private JCheckBox dynamicLoadCheckBox;
	private JCheckBox languageExtension;
	private JRadioButton newLanguage;
	private JTextField extensionSource;
	private JButton importButton;
	private JRadioButton newModel;
	private JLabel dslName;
	private JLabel sourceLabel;
	private Module[] candidates;


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
		return myMainPanel;
	}

	public void createDslBox() {
		updateDslBox(PROTEO);
		dslBox.addItemListener(e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			final String selectedItem = e.getItem().toString();
			if (IMPORT.equals(selectedItem)) importLanguage();
			dynamicLoadCheckBox.setEnabled(PROTEO.equals(selectedItem));
			customizedMorphs.setEnabled(PROTEO.equals(selectedItem));
		});
	}

	private void addListeners() {
		newLanguage.addItemListener(e -> {
			final boolean selected = ((JRadioButton) e.getSource()).isSelected();
			dslName.setEnabled(selected);
			dslGeneratedName.setEnabled(selected);
			languageExtension.setEnabled(selected);
			extensionSource.setEnabled(false);
			importButton.setEnabled(false);
			languageExtension.setSelected(false);
			updateDslBox(null);
		});
		languageExtension.addItemListener(e -> {
			final boolean selected = ((JCheckBox) e.getSource()).isSelected();
			importButton.setVisible(selected);
			extensionSource.setVisible(selected);
			sourceLabel.setEnabled(!selected);
			dslBox.setEnabled(!selected);
			updateDslBox(null);
		});
		importButton.addActionListener(e -> {
			VirtualFile file = FileChooser.chooseFile(new SourceProjectChooserDescriptor(), null, null);
			if (file != null) extensionSource.setText(file.getPath());
		});
	}

	private void importLanguage() {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, null);
		if (file == null) return;
		String newLang = FileUtilRt.getNameWithoutExtension(file.getName());
		if (importedLanguages.containsKey(newLang)) return;
		importedLanguages.put(newLang, new File(file.getPath()));
		updateDslBox(newLang);
	}

	private void updateDslBox(String selection) {
		dslBox.removeAllItems();
		buildAvailableLanguages();
		languages.keySet().forEach(dslBox::addItem);
		addModuleDsls(!newLanguage.isSelected());
		if (dslBox.getItemCount() == 0) {
			dslBox.addItem("");
			dslBox.setSelectedItem("");
		}
		dslBox.addItem(IMPORT);
		if (selection != null) dslBox.setSelectedItem(selection);
	}

	private void buildAvailableLanguages() {
		Map<String, File> map = new HashMap<>();
		if (newLanguage.isSelected()) {
			map.put(PROTEO, new File(PROTEO_DIRECTORY, PROTEO_LIB));
			languages.keySet().stream().filter(lang -> !lang.equals(PROTEO) && !lang.equals(IMPORT)).forEach(lang -> map.put(lang, languages.get(lang)));
		}
		languages.clear();
		languages.putAll(map);
		languages.putAll(importedLanguages);
	}

	private void addModuleDsls(boolean terminal) {
		moduleInfo.entrySet().stream().
			filter(entry -> terminal ? entry.getValue().level == 1 : entry.getValue().level > 1).
			forEach(entry -> dslBox.addItem(entry.getValue().generatedDslName));
	}

	@Override
	public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
		extensionSource.setVisible(false);
		importButton.setVisible(false);
	}

	@Override
	public void frameworkUnselected(@NotNull FrameworkSupportProvider provider) {

	}


	@Override
	public void wizardStepUpdated() {
		languageExtension.setEnabled(false);
	}

	@Override
	public void addSupport(@NotNull Module module,
	                       @NotNull ModifiableRootModel rootModel,
	                       @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		provider.languages.putAll(languages);
		provider.dsl = dslBox.getSelectedItem().toString();
		provider.level = getLevel();
		provider.dslGenerated = !newModel.isSelected() ? dslGeneratedName.getText() : NONE;
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.customMorphs = customizedMorphs.isSelected();
		provider.languageExtension = languageExtension.isSelected() ? findPathToSource() : "";
		provider.selectedModuleParent = getSelectedParentModule();
		provider.addSupport(module, rootModel);
	}

	public int getLevel() {
		if (provider.dsl.equals(PROTEO)) return 2;
		else if (newLanguage.isSelected()) return 1;
		else return 0;
	}

	private String findPathToSource() {
		final String dsl = dslBox.getSelectedItem().toString();
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(dsl))
				return findModelSourceOf(entry.getKey());
		return extensionSource.getText();
	}

	private String findModelSourceOf(Module module) {
		for (VirtualFile virtualFile : ModuleRootManager.getInstance(module).getContentRoots())
			if (virtualFile.getName().equals("model")) return virtualFile.getPath();
		return "";
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
