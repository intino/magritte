package siani.tara.intellij.project.module.ui;

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
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.TaraModuleBuilder;
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
	private String language;
	private boolean terminal;
	private JPanel mainPanel;
	private JComboBox languageBox;
	private JPanel myPanel;
	private JComboBox locale;
	private JTextField generatedLanguage;

	public TaraWizardStep(TaraModuleBuilder builder, WizardContext context, Project project) {
		this.context = context;
		this.builder = builder;
		this.project = project;
		generativeCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				terminal = !((JCheckBox) e.getSource()).isSelected();
				generatedLanguage.setEnabled(!terminal);
			}
		});
		if (project == null) {
			languageBox.addItem(PROTEO);
			languageBox.setEnabled(false);
		} else {
			languageBox.addItem(PROTEO);
			addModuleMetaModels();
			addSdkMetamodel();
			languageBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					language = (String) ((JComboBox) e.getSource()).getSelectedItem();
				}
			});
		}
	}

	private void addModuleMetaModels() {
		for (Module candidate : getParentModulesCandidates(project)) {
			ModuleConfiguration candidateConf = ModuleConfiguration.getInstance(candidate);
			languageBox.addItem(candidateConf.getGeneratedModelName());
		}
		languageBox.setSelectedItem(PROTEO);
	}

	private void addSdkMetamodel() {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk == null || !projectSdk.getSdkType().equals(TaraJdk.getInstance())) return;
		String modelRoot = projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator;
		File sdkFile = new File(modelRoot);
		if (!sdkFile.exists()) return;
		for (File file : sdkFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(MODEL_EXT);
			}
		}))
			languageBox.addItem(file.getName().substring(0, file.getName().lastIndexOf(".")));
	}

	private Module[] getParentModulesCandidates(Project project) {
		List<Module> candidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (!ModuleConfiguration.getInstance(aModule).isTerminal()) candidates.add(aModule);
		return candidates.toArray(new Module[candidates.size()]);
	}

	@Override
	public boolean validate() throws ConfigurationException {
		if (generativeCheckBox.isSelected() && generatedLanguage.getText().isEmpty())
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
		builder.setParentLanguage(language);
		builder.setLocale(locale.getSelectedItem().toString());
		builder.setTerminal(terminal);
		builder.setGeneratedLanguage(!terminal ? generatedLanguage.getText() : "");
	}

}
