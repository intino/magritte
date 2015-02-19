package siani.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.project.sdk.TaraJdk;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class TaraWizardStep extends ModuleWizardStep {
	private static final String PROTEO = "Proteo";
	private static final String MODEL_EXT = ".json";
	private final TaraModuleBuilder builder;
	private final WizardContext context;
	protected JCheckBox generativeCheckBox;
	private Project project;
	private String metamodel;
	private boolean terminal;
	private JPanel mainPanel;
	private JComboBox metamodelBox;
	private JPanel MyPanel;
	private JComboBox language;
	private JTextField languageName;

	public TaraWizardStep(TaraModuleBuilder builder, WizardContext context, Project project) {
		this.context = context;
		this.builder = builder;
		this.project = project;
		generativeCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				terminal = !((JCheckBox) e.getSource()).isSelected();
				languageName.setEnabled(!terminal);
			}
		});
		if (project == null) metamodelBox.setEnabled(false);
		else {
			metamodelBox.addItem(PROTEO);
			addModuleMetaModels();
			addSdkMetamodel();
			metamodelBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					metamodel = (String) ((JComboBox) e.getSource()).getSelectedItem();
				}
			});
		}
	}

	private void addModuleMetaModels() {
		for (Module candidate : getParentModulesCandidates(project)) {
			ModuleConfiguration candidateConf = ModuleConfiguration.getInstance(candidate);
			metamodelBox.addItem(candidateConf.getGeneratedModelName());
		}
		metamodelBox.setSelectedItem(PROTEO);
	}

	private void addSdkMetamodel() {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk == null || !projectSdk.getSdkType().equals(TaraJdk.getInstance())) return;
		String modelRoot = projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator;
		for (File file : new File(modelRoot).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(MODEL_EXT));
			}
		})) {
			metamodelBox.addItem(file.getName().substring(0, file.getName().lastIndexOf(".")));
		}
	}

	private Module[] getParentModulesCandidates(Project project) {
		List<Module> candidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (!ModuleConfiguration.getInstance(aModule).isTerminal()) candidates.add(aModule);
		return candidates.toArray(new Module[candidates.size()]);
	}

	@Override
	public boolean validate() throws ConfigurationException {
		if (generativeCheckBox.isSelected() && languageName.getText().isEmpty())
			throw new ConfigurationException(MessageProvider.message("prompt.generative.language.name"));
		return true;
	}

	@Override
	public JComponent getComponent() {
		return mainPanel;
	}

	@Override
	public void updateDataModel() {
		context.setProjectBuilder(builder);
		builder.setParentLanguage(metamodel);
		builder.setLanguage(language.getSelectedItem().toString());
		builder.setTerminal(terminal);
		builder.setModelName(!terminal ? languageName.getText() : "");
	}

}
