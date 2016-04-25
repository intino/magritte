package tara.intellij.framework;

import com.intellij.facet.impl.ui.FacetErrorPanel;
import com.intellij.facet.ui.FacetEditorValidator;
import com.intellij.facet.ui.ValidationResult;
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
import tara.intellij.project.facet.ModuleInfo;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.facet.TaraFacetConfiguration.ModuleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.*;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String NONE = "";
	private static final String IMPORT = "Import...";
	private static final String PRODUCT_LINE = "Product Line";
	private static final String PLATFORM = "Platform";
	private static final String APPLICATION_PRODUCT = "Application (Product)";
	private static final String APPLICATION_ONTOLOGY = "Application (Ontology)";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, ModuleInfo> moduleInfo;
	private Map<String, LanguageInfo> languages = new LinkedHashMap<>();
	private Module[] candidates;
	private Map<ModuleType, Integer> levels = new LinkedHashMap<>();
	private JPanel myMainPanel;
	private JPanel modelPanel;
	private JPanel advanced;
	private JLabel platformOutDslLabel;
	private JTextField platformOutDsl;
	private JComboBox inputDsl;
	private JComboBox moduleType;
	private JPanel errorPanel;
	private JTextField applicationOutDsl;
	private JLabel dslLabel;
	private JLabel applicationOutLabel;
	private JCheckBox lazyLoadCheckBox;
	private JCheckBox persistentCheckBox;
	private JCheckBox testBox;
	private ActionListener dslListener;
	private FacetErrorPanel facetErrorPanel;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
		levels.put(ModuleType.ProductLine, 3);
		levels.put(Platform, 3);
		levels.put(Application, 2);
		levels.put(Ontology, 2);
		levels.put(ModuleType.System, 1);
		this.provider = provider;
		this.project = model.getProject();
		this.candidates = getParentModulesCandidates(project);
		this.moduleInfo = collectModulesInfo();
		model.addFrameworkListener(this);
		initErrorValidation();
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		updateDslBox(PROTEO);
		addListeners();
		modelPanel.setBorder(null);
		myMainPanel.revalidate();
		initErrorValidation();
		return myMainPanel;
	}


	@Override
	public void addSupport(@NotNull Module module,
						   @NotNull ModifiableRootModel rootModel,
						   @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		if (inputDsl.getSelectedItem() instanceof LanguageInfo && !inputDsl.getSelectedItem().toString().equals(PROTEO)) {
			final LanguageInfo selectedItem = (LanguageInfo) inputDsl.getSelectedItem();
			provider.toImport.put(selectedItem.getName(), selectedItem);
		}
		final ModuleType moduleType = selectedType();
		provider.type = moduleType;
		provider.inputDsl = inputDsl.getSelectedItem().toString();
		provider.platformOutDsl = moduleType == Platform || moduleType == ModuleType.ProductLine ? platformOutDsl.getText() : NONE;
		provider.applicationOutDsl = moduleType == Application || moduleType == Ontology ? applicationOutDsl.getText() : NONE;
		provider.selectedModuleParent = getSelectedParentModule();
		provider.lazyLoad = lazyLoadCheckBox.isSelected();
		provider.persistent = persistentCheckBox.isSelected();
		provider.test = inputDsl.getSelectedIndex() == 0 && testBox.isSelected();
		provider.addSupport(module, rootModel);
	}

	private void initErrorValidation() {
		facetErrorPanel = new FacetErrorPanel();
		errorPanel.add(facetErrorPanel.getComponent(), BorderLayout.CENTER);
		facetErrorPanel.getValidatorsManager().registerValidator(facetValidator(), moduleType, platformOutDsl);
	}

	@NotNull
	private FacetEditorValidator facetValidator() {
		return new FacetEditorValidator() {
			@NotNull
			@Override
			public ValidationResult check() {
				if (requiresOutputDsl() && platformOutDsl.getText().isEmpty())
					return new ValidationResult("Selected model type requires output dsl");
				else if (!platformOutDsl.getText().isEmpty() && invalidOutDslName())
					return new ValidationResult("The Name of the output dsl is not Valid. Use [a-Z][0-9] starting with letter");
				else return ValidationResult.OK;
			}

			private boolean invalidOutDslName() {
				return !platformOutDsl.getText().matches("^[a-zA-Z][a-zA-Z0-9]*$");
			}

			private boolean requiresOutputDsl() {
				return selectedType() != ModuleType.System;
			}
		};
	}

	private void updateDslBox(String selection) {
		inputDsl.removeActionListener(dslListener);
		fillDslBox(selection);
		dslListener = e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			final String selectedItem = inputDsl.getSelectedItem().toString();
			if (IMPORT.equals(selectedItem)) importLanguage();
			updateLazyLoadOption();
			updatePersistentOption();
		};
		inputDsl.addActionListener(dslListener);
	}

	private void updateLazyLoadOption() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return;
		final TaraFacetConfiguration configuration = TaraFacet.of(parent).getConfiguration();
		lazyLoadCheckBox.setSelected(configuration.isLazyLoad());
	}

	private void updatePersistentOption() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return;
		final TaraFacetConfiguration configuration = TaraFacet.of(parent).getConfiguration();
		persistentCheckBox.setSelected(configuration.isPersistent());
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
		for (LanguageInfo languageInfo : languageInfos)
			if (languageInfo.getName().equals(selection)) return languageInfo;
		return null;
	}

	private List<LanguageInfo> importedLanguages() {
		List<LanguageInfo> list = new ArrayList<>();
		final String modelType = this.moduleType.getSelectedItem().toString();
		if (modelType.equals(PLATFORM) || modelType.equals(APPLICATION_ONTOLOGY))
			list.add(LanguageInfo.PROTEO);
		else {
			list.addAll(languages.values());
			inputDsl.addItem(IMPORT);
		}
		return list;
	}

	private void availableModuleDsls() {
		final ModuleType selectedType = selectedType();
		moduleInfo.entrySet().stream().
			filter(entry -> parentOf(selectedType).contains(entry.getValue().type())).
			forEach(entry -> inputDsl.addItem(entry.getValue().platformOutDsl()));
	}

	private List<ModuleType> parentOf(ModuleType type) {
		if (type.equals(Application) || type.equals(Ontology))
			return Arrays.asList(Platform, ModuleType.ProductLine);
		if (type.equals(ModuleType.System)) return Arrays.asList(Application, Ontology);
		else return Collections.emptyList();
	}

	private void empty() {
		if (inputDsl.getItemCount() == 0 || (inputDsl.getItemCount() == 1 && inputDsl.getSelectedItem().equals(IMPORT))) {
			inputDsl.addItem("");
			inputDsl.setSelectedItem("");
		}
	}

	private ModuleType selectedType() {
		return ModuleType.valueOf(moduleType.getSelectedItem().toString().replace(" ", "").replaceAll("\\(.*\\)", ""));
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().platformOutDsl().equals(inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.of(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().type().equals(ModuleType.System))
				moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	private Map<Module, ModuleInfo> collectModulesInfo() {
		Map<Module, ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.of(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration conf = facet.getConfiguration();
			map.put(candidate, new ModuleInfo(conf.type(), conf.platformOutDsl(), conf.applicationOutDsl()));
		}
		return map;
	}

	private void createUIComponents() {
		advanced = new HideableTitledPanel("Advanced", false);
		testBox = new JBCheckBox("Create test folders", false);
		testBox.setVisible(true);
		lazyLoadCheckBox = new JBCheckBox("Lazy load", false);
		persistentCheckBox = new JBCheckBox("Persistent", false);
		final JPanel jbPanel = new JPanel();
		jbPanel.setLayout(new GridLayout(2, 1));
		((HideableTitledPanel) advanced).setContentComponent(jbPanel);
		jbPanel.add(lazyLoadCheckBox);
		jbPanel.add(persistentCheckBox);
		jbPanel.add(testBox);
	}

	@Override
	public void onFrameworkSelectionChanged(boolean selected) {
		if (selected) facetErrorPanel.getValidatorsManager().validate();
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

	private void addListeners() {
		moduleType.addItemListener(e -> {
			final String type = ((JComboBox) e.getSource()).getSelectedItem().toString();
			if (PRODUCT_LINE.equals(type)) {
				applicationOutLabel.setText("Application output dsl");
				mask(true, true, true, true, true, true, false, false);
			} else if (PLATFORM.equals(type)) {
				applicationOutLabel.setText("Application output dsl");
				mask(true, true, false, false, true, true, false, false);
			} else if (type.equals(APPLICATION_PRODUCT)) {
				dslLabel.setText("Platform");
				applicationOutLabel.setText("Application output dsl");
				mask(false, false, true, true, false, false, true, true);
			} else if (APPLICATION_ONTOLOGY.equals(type)) {
				dslLabel.setText("Platform");
				applicationOutLabel.setText("Ontology output dsl");
				mask(false, false, true, true, false, false, true, true);
			} else {
				dslLabel.setText("Application");
				mask(false, false, false, false, false, false, true, true);
			}
			updateDslBox(null);
			updateLazyLoadOption();
			updatePersistentOption();
		});
	}

	private void mask(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean h) {
		platformOutDslLabel.setVisible(a);
		platformOutDsl.setVisible(b);
		applicationOutLabel.setVisible(c);
		applicationOutDsl.setVisible(d);
		lazyLoadCheckBox.setVisible(e);
		persistentCheckBox.setVisible(f);
		dslLabel.setVisible(g);
		inputDsl.setVisible(h);
	}

}
