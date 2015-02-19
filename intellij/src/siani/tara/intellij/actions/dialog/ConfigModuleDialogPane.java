package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;
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

public class ConfigModuleDialogPane extends DialogWrapper {

	private static final String PROTEO = "Proteo";
	private static final String MODEL_EXT = ".json";
	private final Project project;
	private final Module module;
	protected JCheckBox generativeModelCheckBox;
	private JPanel dialogContents;
	private JComboBox metamodelBox;
	private JLabel metamodelField;
	private JComboBox language;
	private JTextField languageName;
	private Module[] candidates;

	public ConfigModuleDialogPane(final Project project, Module module) {
		super(project, false);
		this.project = project;
		this.module = module;
		initDialog();
		candidates = getParentModulesCandidates(project, module);
		loadValues();
	}

	@Nullable
	@Override
	protected ValidationInfo doValidate() {
		if (generativeModelCheckBox.isSelected() && languageName.getText().isEmpty())
			return new ValidationInfo("Name for the new generated Language is required", languageName);
		return null;
	}

	void initDialog() {
		super.init();
		generativeModelCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				languageName.setEnabled(((JCheckBox) e.getSource()).isSelected());
			}
		});
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		return dialogContents;
	}

	public void loadValues() {
		metamodelBox.addItem(PROTEO);
		addModuleMetaModels();
		addSdkMetamodel();
		if (metamodelBox.getSelectedItem() == null) metamodelBox.setSelectedItem(PROTEO);
		generativeModelCheckBox.setSelected(!ModuleConfiguration.getInstance(module).isTerminal());
		languageName.setText(ModuleConfiguration.getInstance(module).getGeneratedModelName());
		language.setSelectedItem(ModuleConfiguration.getInstance(module).getLanguage().equals(Locale.ENGLISH) ? "English" : "Español");
	}

	private void addModuleMetaModels() {
		for (Module candidate : candidates) {
			ModuleConfiguration candidateConf = ModuleConfiguration.getInstance(candidate);
			if (candidateConf.isTerminal()) continue;
			metamodelBox.addItem(candidateConf.getGeneratedModelName());
			if (ModuleConfiguration.getInstance(module).getMetamodelName().equals(candidateConf.getGeneratedModelName()))
				metamodelBox.setSelectedItem(candidateConf.getGeneratedModelName());
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
				return (name.endsWith(MODEL_EXT));
			}
		})) {
			metamodelBox.addItem(file.getName().substring(0, file.getName().lastIndexOf(".")));
		}
	}

	public void saveValues() {
		setParent(metamodelBox.getSelectedItem());
		setTerminal(!generativeModelCheckBox.isSelected());
		setGeneratedLanguageName(generativeModelCheckBox.isSelected() ? languageName.getText() : "");
		setLanguage(language.getSelectedItem().toString());
	}

	private void setParent(Object selectedItem) {
		Module parentModule;
		if (selectedItem.equals(PROTEO)) {
			updateDependencies(null);
			ModuleConfiguration.getInstance(module).setMetamodelName("");
			ModuleConfiguration.getInstance(module).setMetamodelFilePath("");
		} else if ((parentModule = searchParent(selectedItem.toString())) != null) {
			updateDependencies(parentModule);
			String generatedModelName = ModuleConfiguration.getInstance(parentModule).getGeneratedModelName();
			ModuleConfiguration.getInstance(module).setMetamodelName(generatedModelName);
			ModuleConfiguration.getInstance(module).setMetamodelFilePath(new File(TaraLanguage.MODELS_PATH + generatedModelName + MODEL_EXT).getAbsolutePath());
		} else {
			updateDependencies(null);
			ModuleConfiguration.getInstance(module).setMetamodelName(selectedItem.toString());
			Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
			if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance())) {
				File file = new File(projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator + selectedItem.toString() + MODEL_EXT);
				ModuleConfiguration.getInstance(module).setMetamodelFilePath(file.getAbsolutePath());
			}
		}
	}

	private void setLanguage(String language) {
		ModuleConfiguration.getInstance(module).setLanguage(language);
	}

	private void setGeneratedLanguageName(String languageName) {
		ModuleConfiguration.getInstance(module).setGeneratedModelName(languageName);
	}

	private void setTerminal(boolean selected) {
		ModuleConfiguration.getInstance(module).setTerminal(selected);
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
				if ((parentName = ModuleConfiguration.getInstance(module).getMetamodelName()).isEmpty()) return;
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
}
