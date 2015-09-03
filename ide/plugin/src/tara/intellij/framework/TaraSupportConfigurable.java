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
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.TaraRuntimeException;
import tara.intellij.actions.ImportLanguageAction;
import tara.intellij.lang.LanguageFactory.ImportedLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tara.intellij.lang.TaraLanguage.PROTEO;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String NONE = "";
	private static final String IMPORT = "Import...";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	private JPanel myMainPanel;
	private JComboBox dslBox;
	private JTextField dslGeneratedName;
	private JLabel level;
	private JLabel levelLabel;
	private JPanel generatedLanguagePane;
	private JCheckBox customizedMorphs;
	private JCheckBox dynamicLoadCheckBox;
	private JCheckBox languageExtension;
	private Module[] candidates;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
		this.provider = provider;
		this.project = model.getProject();
		candidates = getParentModulesCandidates(project);
		moduleInfo = collectModulesInfo();
		dslBox.addItemListener(e -> {
			if (PROTEO.equals(e.getItem().toString())) setLevel(2);
			else if (IMPORT.equals(e.getItem().toString())) importLanguage();
			else moduleInfo.values().stream().
					filter(entry -> entry.generatedDslName.equals(e.getItem().toString())).
					forEach(entry -> setLevel(entry.level - 1));
		});
		level.addPropertyChangeListener("text", e -> editionOfGenerativeLanguage(Integer.parseInt(e.getNewValue().toString()) != 0));
	}

	private void importLanguage() {
		try {
			final ImportedLanguage file = new ImportLanguageAction().importLanguage(project);
			if (file == null) return;
			selectLanguage(file);
		} catch (IOException e) {
			throw new TaraRuntimeException("Error importing language");
		}
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		addModuleDsls();
		if (project == null || !project.isInitialized()) {
			dslBox.addItem(PROTEO);
			setLevel(2);
		} else setLevel(getLevel(dslBox.getSelectedItem().toString()));
		dslBox.addItem(IMPORT);
		return myMainPanel;
	}

	private int getLevel(String selectedItem) {
		for (ModuleInfo entry : moduleInfo.values())
			if (entry.generatedDslName.equals(selectedItem)) return entry.level - 1;
		return 2;
	}

	private void selectLanguage(ImportedLanguage language) {
		String newLang = FileUtilRt.getNameWithoutExtension(language.path().getName());
		provider.languages.put(newLang, language);
		dslBox.addItem(newLang);
		dslBox.setSelectedItem(newLang);
		level.setText("1");
	}

	private void addModuleDsls() {
		moduleInfo.entrySet().stream().
			filter(entry -> entry.getValue().level != 0).
			forEach(entry -> dslBox.addItem(entry.getValue().generatedDslName));
	}

	private void editionOfGenerativeLanguage(boolean editable) {
		dslGeneratedName.setVisible(editable);
	}

	private void setLevel(int level) {
		this.level.setText(level + "");
	}

	@Override
	public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
		dslBox.setEnabled(true);
		levelLabel.setEnabled(true);
		dslGeneratedName.setVisible(true);
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void frameworkUnselected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
		dslBox.setEnabled(false);
		levelLabel.setEnabled(false);
		dslGeneratedName.setEnabled(false);
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void wizardStepUpdated() {
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void addSupport(@NotNull Module module,
	                       @NotNull ModifiableRootModel rootModel,
	                       @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		provider.dsl = dslBox.getSelectedItem().toString();
		provider.dslGenerate = "0".equals(level.getText()) ? NONE : dslGeneratedName.getText();
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.customMorphs = customizedMorphs.isSelected();
		provider.languageExtension = languageExtension.isSelected() ? findPathToSource() : "";
		provider.level = Integer.parseInt(level.getText());
		provider.selectedModuleParent = getSelectedParentModule();
		provider.addSupport(module, rootModel);
	}

	private String findPathToSource() {
		final String dsl = dslBox.getSelectedItem().toString();
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet()) {
			if (entry.getValue().generatedDslName.equals(dsl))
				return findModelSourceOf(entry.getKey());
		}
		for (Map.Entry<String, ImportedLanguage> entry : provider.languages.entrySet()) {
			if (entry.getKey().equals(dsl) && entry.getValue().isExtensible())
				return entry.getValue().pathToSource().getAbsolutePath();
		}
		return "";
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
			TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	private Map<Module, ModuleInfo> collectModulesInfo() {
		Map<Module, ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(candidate);
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
