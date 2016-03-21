package tara.intellij.project.facet;

import com.intellij.facet.FacetManager;
import com.intellij.facet.impl.ui.FacetErrorPanel;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetEditorValidator;
import com.intellij.facet.ui.ValidationResult;
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
import static tara.intellij.lang.LanguageManager.LEVELS;
import static tara.intellij.messages.MessageProvider.message;

public class TaraFacetEditor extends FacetEditorTab {

	private static final String NONE = "";
	private final TaraFacetConfiguration configuration;
	final FacetEditorContext context;

	JPanel myMainPanel;
	JPanel modelPanel;
	JPanel advanced;
	JLabel inputDslLabel;
	JComboBox inputDsl;
	JTextField outputDsl;
	JComboBox modelType;
	JButton update;
	JLabel reloadLabel;
	JComboBox versionBox;
	JLabel outputDslLabel;
	private JPanel errorPanel;
	JCheckBox dynamicLoadCheckBox;
	JCheckBox testBox;
	Map<Module, FacetEditorUICreator.ModuleInfo> moduleInfo;
	private FacetErrorPanel facetErrorPanel;
	private final FacetEditorUICreator facetEditorUICreator;

	public TaraFacetEditor(TaraFacetConfiguration configuration, FacetEditorContext context) {
		this.configuration = configuration;
		this.context = context;
		this.facetEditorUICreator = new FacetEditorUICreator(this, configuration);
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		facetEditorUICreator.createUI();
		initErrorValidation();
		return myMainPanel;
	}

	public boolean isModified() {
		return !outputDsl().equals(configuration.outputDsl()) ||
			!inputDsl.getSelectedItem().toString().equals(configuration.dsl()) ||
			!versionBox.getSelectedItem().toString().equals(configuration.dslVersion(this.context.getModule())) ||
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad();
	}

	public void apply() throws ConfigurationException {
		if (!facetErrorPanel.isOk()) throw new ConfigurationException(message("required.tara.facet.outdsl"));
		updateTaraFacetConfiguration();
	}

	private void initErrorValidation() {
		facetErrorPanel = new FacetErrorPanel();
		errorPanel.add(facetErrorPanel.getComponent(), BorderLayout.CENTER);
		facetErrorPanel.getValidatorsManager().registerValidator(new FacetEditorValidator() {
			@NotNull
			@Override
			public ValidationResult check() {
				if (requiresOutputDsl() && outputDsl.getText().isEmpty())
					return new ValidationResult(message("required.tara.facet.outdsl"));
				else if (!outputDsl.getText().isEmpty() && invalidOutDslName())
					return new ValidationResult(message("required.outdsl.wrong.pattern"));
				else return ValidationResult.OK;
			}
		}, modelType, outputDsl);
		facetErrorPanel.getValidatorsManager().validate();
	}

	private boolean requiresOutputDsl() {
		return !configuration.isM0();
	}

	private boolean invalidOutDslName() {
		return !outputDsl.getText().matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	public void reset() {
		inputDsl.setSelectedItem(configuration.dsl());
		outputDsl.setText(configuration.outputDsl());
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}

	private void updateTaraFacetConfiguration() {
		configuration.setDsl(inputDsl.getSelectedItem().toString());
		if (!outputDsl().equals(configuration.outputDsl())) {
			propagateToJava();
			configuration.outputDsl(outputDsl());
		}
		if (!versionBox.getSelectedItem().toString().equals(configuration.dslVersion(this.context.getModule())))
			updateLanguage(versionBox.getSelectedItem().toString());
		configuration.setDynamicLoad(dynamicLoadCheckBox.isSelected());
		propagateChanges(configuration);
	}

	private void propagateChanges(TaraFacetConfiguration configuration) {
		final Module contextModule = context.getModule();
		List<Module> dependentModules = dependentModules(contextModule);
		for (Module module : dependentModules) propagateChanges(module, configuration);

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

	private void runRefactor(Project project) {
		final JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(project);
		final PsiClass levelClass = psiFacade.findClass(configuration.outputDsl().toLowerCase() + "." + Format.firstUpperCase().format(configuration.outputDsl()) + LEVELS[configuration.getLevel()], GlobalSearchScope.moduleScope(context.getModule()));
		if (levelClass != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, levelClass, Format.firstUpperCase().format(outputDsl()).toString() + LEVELS[configuration.getLevel()], false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
		final PsiPackage aPackage = psiFacade.findPackage(configuration.outputDsl().toLowerCase());
		if (aPackage != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, aPackage, outputDsl().toLowerCase(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
	}

	private void propagateChanges(Module module, TaraFacetConfiguration conf) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final TaraFacet facet = TaraFacet.of(module);
			if (facet == null) return;
			facet.disposeFacet();
			facet.getConfiguration().setDsl(outputDsl());
			facet.getConfiguration().setDynamicLoad(dynamicLoadCheckBox.isSelected());
			FacetManager.getInstance(module).createModifiableModel().commit();
		});
		WriteCommandAction.runWriteCommandAction(module.getProject(), () -> {
			final TaraFacetConfiguration facet = TaraUtil.getFacetConfiguration(module);
			if (facet == null) return;
			TaraUtil.getTaraFilesOfModule(module).stream().
				filter(model -> facet.isM0() || TaraUtil.isDefinitionFile(model)).
				forEach(model -> model.updateDSL(conf.outputDsl()));
		});
	}

	void updateLanguage(String version) {
		if (getSelectedParentModule() == null) new UpdateLanguageAction().importLanguage(context.getModule(), version);
		update.setVisible(false);
		reloadLabel.setVisible(false);
	}

	private String outputDsl() {
		return outputDsl.isEnabled() ? outputDsl.getText() : NONE;
	}

	@Override
	public void onTabEntering() {
		this.facetEditorUICreator.createDslBox();
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, FacetEditorUICreator.ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void createUIComponents() {
		advanced = new HideableTitledPanel("Advanced", false);
		((HideableTitledPanel) advanced).setOn(true);
		testBox = new JBCheckBox("Test system", false);
		testBox.setEnabled(false);
		dynamicLoadCheckBox = new JBCheckBox("Dynamic load model", false);
		dynamicLoadCheckBox.setVerticalAlignment(TOP);
		testBox.setVerticalAlignment(TOP);
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 5, 10));
		((HideableTitledPanel) advanced).setContentComponent(panel);
		panel.add(dynamicLoadCheckBox);
		panel.add(testBox);
	}
}
