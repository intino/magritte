package siani.tara.intellij.project.module.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleConfigurationEditor;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.sdk.TaraJdk;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaraModuleBuildConfEditor implements ModuleConfigurationEditor {

	private static final String PROTEO = "Proteo";
	private static final String MODEL_EXT = ".json";

	private final Module module;
	private final Project project;
	private final ModuleConfiguration configuration;

	public JPanel dialogContents;
	private JComboBox dslBox;
	private int defaultDslBox;
	private JLabel dslLabel;
	private JComboBox dictionary;
	private int defaultDictionaryBox;
	protected JCheckBox generativeModelCheckBox;
	protected boolean defaultValueModelBox;
	private JTextField genDslName;
	private String defaultDslName;
	private Module[] candidates;

	public TaraModuleBuildConfEditor(ModuleConfigurationState state) {
		module = state.getRootModel().getModule();
		project = state.getRootModel().getProject();
		configuration = ModuleConfiguration.getInstance(module);
	}

	public JComponent createComponent() {
		initDialog();
		candidates = getParentModulesCandidates(project, module);
		loadValues();
		return dialogContents;
	}

	void initDialog() {
		generativeModelCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				genDslName.setEnabled(((JCheckBox) e.getSource()).isSelected());
			}
		});
	}

	public void loadValues() {
		dslBox.addItem(PROTEO);
		addModuleMetaModels();
		addSdkMetamodel();
		if (dslBox.getSelectedItem() == null) dslBox.setSelectedItem(PROTEO);
		generativeModelCheckBox.setSelected(!configuration.isTerminal());
		genDslName.setText(configuration.getGeneratedModelName());
		dictionary.setSelectedItem(configuration.getLanguage().equals(Locale.ENGLISH) ? "English" : "Español");
		defaultDslBox = dslBox.getSelectedIndex();
		defaultDictionaryBox = dictionary.getSelectedIndex();
		defaultDslName = genDslName.getText();
		defaultValueModelBox = generativeModelCheckBox.isSelected();
	}

	private void addModuleMetaModels() {
		for (Module candidate : candidates) {
			ModuleConfiguration candidateConf = ModuleConfiguration.getInstance(candidate);
			if (candidateConf.isTerminal()) continue;
			dslBox.addItem(candidateConf.getGeneratedModelName());
			if (configuration.getMetamodelName().equals(candidateConf.getGeneratedModelName()))
				dslBox.setSelectedItem(candidateConf.getGeneratedModelName());
		}
	}

	private void addSdkMetamodel() {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk == null || !projectSdk.getSdkType().equals(TaraJdk.getInstance())) return;
		String modelRoot = projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator;
		File tdkDir = new File(modelRoot);
		if (!tdkDir.exists()) return;
		for (File file : tdkDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(MODEL_EXT);
			}
		})) {
			dslBox.addItem(file.getName().substring(0, file.getName().lastIndexOf(".")));
		}
	}


	private void setParent(Object selectedItem) {
		Module parentModule;
		if (selectedItem.equals(PROTEO)) {
			updateDependencies(null);
			configuration.setMetamodelName("");
			configuration.setMetamodelFilePath("");
		} else if ((parentModule = searchParent(selectedItem.toString())) != null) {
			updateDependencies(parentModule);
			String generatedModelName = ModuleConfiguration.getInstance(parentModule).getGeneratedModelName();
			configuration.setMetamodelName(generatedModelName);
			configuration.setMetamodelFilePath(new File(TaraLanguage.MODELS_PATH + generatedModelName + MODEL_EXT).getAbsolutePath());
		} else {
			updateDependencies(null);
			configuration.setMetamodelName(selectedItem.toString());
			Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
			if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance())) {
				File file = new File(projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator + selectedItem.toString() + MODEL_EXT);
				configuration.setMetamodelFilePath(file.getAbsolutePath());
			}
		}
	}

	private void setDictionary(String dictionary) {
		configuration.setLanguage(dictionary);
	}

	private void setGeneratedLanguageName(String languageName) {
		configuration.setGeneratedModelName(languageName);
	}

	private void setTerminal(boolean selected) {
		configuration.setTerminal(selected);
	}

	private Module searchParent(String parentName) {
		for (Module candidate : candidates) {
			ModuleConfiguration conf = ModuleConfiguration.getInstance(candidate);
			if (conf.getGeneratedModelName().equals(parentName))
				return candidate;
		}
		return null;
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
				String parentName;
				if ((parentName = configuration.getMetamodelName()).isEmpty()) return;
				Module parentModule = searchParent(parentName.contains(".") ? parentName.split("\\.")[1] : parentName);
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

	private OrderEntry findOrderEntry(OrderEntry[] orderEntries, Module parentModule) {
		for (OrderEntry entry : orderEntries)
			if (entry instanceof ModuleOrderEntry && parentModule.equals(((ModuleOrderEntry) entry).getModule()))
				return entry;
		return null;
	}

	private Module[] getParentModulesCandidates(Project project, Module module) {
		List<Module> candidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (aModule != module && !ModuleConfiguration.getInstance(aModule).isTerminal()) candidates.add(aModule);
		return candidates.toArray(new Module[candidates.size()]);
	}


	@Override
	public boolean isModified() {
		return dslBox.getSelectedIndex() != defaultDslBox ||
			generativeModelCheckBox.isSelected() != defaultValueModelBox ||
			dictionary.getSelectedIndex() != defaultDictionaryBox ||
			!genDslName.getText().equals(defaultDslName);

	}

	public void apply() throws ConfigurationException {
		setParent(dslBox.getSelectedItem());
		setTerminal(!generativeModelCheckBox.isSelected());
		setGeneratedLanguageName(generativeModelCheckBox.isSelected() ? genDslName.getText() : "");
		setDictionary(dictionary.getSelectedItem().toString());
	}

	public void reset() {
	}

	public void disposeUIResources() {
	}

	public void saveData() {
	}

	public String getDisplayName() {
		return "Tara Settings";
	}

	public String getHelpTopic() {
		return "tara.plugin.configuring";
	}

	public void moduleStateChanged() {
	}
}
