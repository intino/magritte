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
import com.intellij.ui.components.JBPanel;
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
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad();
	}

	public void apply() {
		if (isModified()) updateFacetConfiguration();
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
		advanced = new HideableTitledPanel("Advanced", false);
		((HideableTitledPanel) advanced).setOn(true);
		testBox = new JBCheckBox("Test system", false);
		dynamicLoadCheckBox = new JBCheckBox("Dynamic load model", false);
		dynamicLoadCheckBox.setVerticalAlignment(SwingConstants.TOP);
		testBox.setVerticalAlignment(SwingConstants.TOP);
		final JPanel jbPanel = new JPanel();
		jbPanel.setLayout(new GridLayout(2, 1));
		((HideableTitledPanel) advanced).setContentComponent(jbPanel);
		jbPanel.add(dynamicLoadCheckBox);
		jbPanel.add(testBox);
		jbPanel.add(new JBPanel());
	}

}
