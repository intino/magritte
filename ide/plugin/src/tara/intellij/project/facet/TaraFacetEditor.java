package tara.intellij.project.facet;

import com.intellij.facet.FacetManager;
import com.intellij.facet.impl.ui.FacetErrorPanel;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.refactoring.openapi.impl.JavaRenameRefactoringImpl;
import com.intellij.ui.HideableTitledPanel;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.UpdateLanguageAction;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static javax.swing.SwingConstants.TOP;
import static tara.intellij.messages.MessageProvider.message;

public class TaraFacetEditor extends FacetEditorTab {

	private final TaraFacetConfiguration conf;
	private FacetEditorUICreator creator;
	final FacetEditorContext context;

	JPanel myMainPanel;
	JPanel modelPanel;
	JPanel advanced;
	JLabel inputDslLabel;
	JComboBox inputDsl;
	JTextField systemDsl;
	JComboBox modelType;
	JButton update;
	JLabel reloadLabel;
	JComboBox versionBox;
	JLabel applicationDslLabel;
	JTextField applicationDsl;
	JLabel systemDslLabel;
	JPanel errorPanel;
	JCheckBox persistentCheckBox;
	JCheckBox testBox;
	Map<Module, ModuleInfo> moduleInfo;
	FacetErrorPanel facetErrorPanel;

	TaraFacetEditor(TaraFacetConfiguration conf, FacetEditorContext context) {
		this.conf = conf;
		this.context = context;
		this.creator = new FacetEditorUICreator(this, context);
	}

	@Override
	public void onTabEntering() {
		this.creator.createUI();
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		creator.createUI();
		return myMainPanel;
	}

	public boolean isModified() {
		return creator.isModified();
	}

	public void apply() throws ConfigurationException {
		if (!facetErrorPanel.isOk()) throw new ConfigurationException(message("required.tara.facet.outdsl"));
		updateTaraFacetConfiguration();
	}

	private void updateTaraFacetConfiguration() {
		conf.dsl(conf.type(), inputDsl.getSelectedItem().toString());
		if (!newApplicationDsl().equals(conf.applicationDsl())) {
			propagateToJava();
			propagateInsideModule(conf.applicationDsl(), newApplicationDsl(), context.getModule());
			conf.applicationDsl(newApplicationDsl());
			conf.platformOutDsl(newApplicationDsl());
		}
		if (!newSystemDsl().equals(conf.systemDsl())) {
			propagateToJava();
			propagateInsideModule(conf.systemDsl(), newSystemDsl(), context.getModule());
			conf.systemDsl(newSystemDsl());
			conf.applicationOutDsl(newSystemDsl());
		}
		if (!versionBox.getSelectedItem().toString().equals(conf.dslVersion(this.context.getModule(), inputDsl.getSelectedItem().toString())))
			updateLanguage(versionBox.getSelectedItem().toString());
		conf.persistent(persistentCheckBox.isSelected());
		propagateChanges(conf);
	}

	public void reset() {
		creator.createUI();
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}

	public void disposeUIResources() {
	}

	private void propagateChanges(TaraFacetConfiguration configuration) {
		final Module contextModule = context.getModule();
		List<Module> dependentModules = dependentModules(contextModule);
		for (Module dependent : dependentModules) propagateChanges(dependent, configuration);

	}

	private List<Module> dependentModules(Module contextModule) {
		List<Module> dependentModules = new ArrayList<>();
		for (Module module : ModuleManager.getInstance(context.getProject()).getModules()) {
			final ModuleRootManager manager = ModuleRootManager.getInstance(module);
			if (Arrays.asList(manager.getDependencies()).contains(contextModule))
				dependentModules.add(module);
		}
		return dependentModules;
	}

	private void propagateToJava() {
		final Project project = context.getProject();
		ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
			final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
			progressIndicator.setText("Refactoring Java");
			progressIndicator.setIndeterminate(true);
			runRefactor(project);
		}, "Refactoring Java", true, project, myMainPanel);
	}

	private void propagateInsideModule(String oldDSL, String newDSL, Module contextModule) {
		WriteCommandAction.runWriteCommandAction(contextModule.getProject(), () -> {
			TaraUtil.getTaraFilesOfModule(contextModule).stream().filter(model -> model.dsl().equals(oldDSL)).
				forEach(model -> model.updateDSL(newDSL));
		});
	}

	private void runRefactor(Project project) {
		final JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(project);
		final PsiClass levelClass = psiFacade.findClass(conf.applicationDsl().toLowerCase() + "." + Format.firstUpperCase().format(conf.applicationDsl()) + conf.type().name(), GlobalSearchScope.moduleScope(context.getModule()));
		if (levelClass != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, levelClass, Format.firstUpperCase().format(newApplicationDsl()).toString() + conf.type().name(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
		final PsiPackage aPackage = psiFacade.findPackage(conf.applicationDsl().toLowerCase());
		if (aPackage != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, aPackage, newApplicationDsl().toLowerCase(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
	}

	private String newApplicationDsl() {
		return applicationDsl.getText();
	}

	private String newSystemDsl() {
		return systemDsl.getText();
	}

	private void propagateChanges(Module module, TaraFacetConfiguration conf) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final TaraFacet facet = TaraFacet.of(module);
			if (facet == null) return;
			facet.disposeFacet();
			facet.getConfiguration().dsl(TaraFacetConfiguration.ModuleType.Application, applicationDsl.getText());
			facet.getConfiguration().persistent(persistentCheckBox.isSelected());
			FacetManager.getInstance(module).createModifiableModel().commit();
		});
		WriteCommandAction.runWriteCommandAction(module.getProject(), () -> {
			final TaraFacetConfiguration facet = TaraUtil.getFacetConfiguration(module);
			if (facet == null) return;
			TaraUtil.getTaraFilesOfModule(module).stream().
				filter(model -> TaraFacetConfiguration.ModuleType.System.equals(facet.type())).
				forEach(model -> model.updateDSL(conf.systemDsl()));
		});
	}

	void updateLanguage(String version) {
		if (getSelectedParentModule() == null) new UpdateLanguageAction().importLanguage(context.getModule(), version);
		update.setVisible(false);
		reloadLabel.setVisible(false);
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().applicationDsl().equals(inputDsl.getSelectedItem().toString()))//TODO
				return entry.getKey();
		return null;
	}

	private void createUIComponents() {
		advanced = new HideableTitledPanel("Advanced", false);
		((HideableTitledPanel) advanced).setOn(true);
		testBox = new JBCheckBox("Test system", false);
		testBox.setEnabled(true);
		persistentCheckBox = new JBCheckBox("Persistent", false);
		persistentCheckBox.setVerticalAlignment(TOP);
		testBox.setVerticalAlignment(TOP);
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 5, 10));
		((HideableTitledPanel) advanced).setContentComponent(panel);
		panel.add(persistentCheckBox);
		panel.add(testBox);
	}
}