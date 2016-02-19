package tara.intellij.project.facet;

import com.intellij.facet.FacetManager;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.ui.HideableTitledPanel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.ImportLanguageAction;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.SwingConstants.TOP;

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
	JCheckBox dynamicLoadCheckBox;
	JCheckBox testBox;
	JLabel artifactoryLabel;
	JTextField artifactoryUser;
	JPasswordField artifactoryPassword;

	AbstractMap.SimpleEntry<String, String> credentials = new AbstractMap.SimpleEntry<>(loadCredentials());
	Map<Module, FacetEditorUICreator.ModuleInfo> moduleInfo;
	Map<String, AbstractMap.SimpleEntry<Integer, File>> languages = new HashMap<>();

	public TaraFacetEditor(TaraFacetConfiguration configuration, FacetEditorContext context) {
		this.configuration = configuration;
		this.context = context;
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		new FacetEditorUICreator(this, configuration).createUI();
		return myMainPanel;
	}

	public boolean isModified() {
		return !getOutputDsl().equals(configuration.outputDsl()) ||
			!inputDsl.getSelectedItem().toString().equals(configuration.getDsl()) ||
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad() ||
			!credentials.getKey().equals(artifactoryUser.getText()) ||
			!credentials.getValue().equals(new String(artifactoryPassword.getPassword()));

	}

	public void apply() {
		if (isModified()) {
			updateFacetConfiguration();
			saveArtifactoryCredentials();
		}
	}

	public void reset() {
		inputDsl.setSelectedItem(configuration.getDsl());
		outputDsl.setText(configuration.outputDsl());
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}

	private void updateFacetConfiguration() {
		configuration.setDsl(inputDsl.getSelectedItem().toString());
		configuration.outputDsl(getOutputDsl());
		configuration.setDynamicLoad(dynamicLoadCheckBox.isSelected());
		propagateChanges(configuration);
	}

	private void saveArtifactoryCredentials() {
		new ArtifactoryManager().saveCredentials(artifactoryUser.getText(), new String(artifactoryPassword.getPassword()));
	}

	private void propagateChanges(TaraFacetConfiguration configuration) {
		final Module contextModule = context.getModule();
		for (Module aModule : ModuleManager.getInstance(context.getProject()).getModules())
			if (Arrays.asList(ModuleRootManager.getInstance(aModule).getDependencies()).contains(contextModule))
				propagateChanges(aModule, configuration);
	}

	private void propagateChanges(Module module, TaraFacetConfiguration conf) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final TaraFacet facet = TaraFacet.of(module);
			if (facet == null) return;
			facet.disposeFacet();
			facet.getConfiguration().setDsl(conf.outputDsl());
			facet.getConfiguration().setDynamicLoad(conf.isDynamicLoad());
			FacetManager.getInstance(module).createModifiableModel().commit();
		});
		WriteCommandAction.runWriteCommandAction(module.getProject(), () -> {
			for (TaraModel model : TaraUtil.getTaraFilesOfModule(module))
				model.updateDSL(conf.outputDsl());
		});
	}

	void reload() {
		if (getSelectedParentModule() == null) {
			new ImportLanguageAction().importLanguage(context.getModule());
		}
		update.setVisible(false);
		reloadLabel.setVisible(false);
	}

	private String getOutputDsl() {
		return outputDsl.isEnabled() ? outputDsl.getText() : NONE;
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, FacetEditorUICreator.ModuleInfo> entry : moduleInfo.entrySet())
			if (entry.getValue().generatedDslName.equals(inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void createUIComponents() {
		Map.Entry<String, String> credentials = loadCredentials();
		advanced = new HideableTitledPanel("Advanced", false);
		((HideableTitledPanel) advanced).setOn(true);
		testBox = new JBCheckBox("Test system", false);
		dynamicLoadCheckBox = new JBCheckBox("Dynamic load model", false);
		dynamicLoadCheckBox.setVerticalAlignment(TOP);
		artifactoryLabel = new JLabel("Artifactory");
		artifactoryLabel.setFont(artifactoryLabel.getFont().deriveFont(Font.BOLD));
		artifactoryLabel.setVerticalAlignment(TOP);
		testBox.setVerticalAlignment(TOP);
		artifactoryUser = new JBTextField(credentials.getKey());
		artifactoryUser.setHorizontalAlignment(SwingConstants.LEADING);
		artifactoryPassword = new JPasswordField();
		artifactoryPassword.setText(credentials.getValue());
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 5, 10));
		((HideableTitledPanel) advanced).setContentComponent(panel);
		panel.add(dynamicLoadCheckBox);
		panel.add(testBox);
		panel.add(artifactoryLabel);
		panel.add(new JBLabel(""));
		panel.add(new JBLabel("User:"));
		panel.add(artifactoryUser);
		panel.add(new JBLabel("Password:"));
		panel.add(artifactoryPassword);
	}

	private Map.Entry<String, String> loadCredentials() {
		return new ArtifactoryManager().loadCredentials();
	}

}
