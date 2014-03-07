package monet.::projectName::.intellij.project.runner;

import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.roots.ui.configuration.ModulesAlphaComparator;
import com.intellij.ui.*;
import com.intellij.ui.components.JBLabel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::RunConfigurationEditor extends SettingsEditor<::projectProperName::RunConfiguration> implements PanelWithAnchor {
	private final JTextField workDirField;
	private DefaultComboBoxModel myModulesModel;
	private JComboBox myModulesBox;
	private JPanel myMainPanel;
	private RawCommandLineEditor deployParams;
	private JPanel workDirPanel;
	private JCheckBox myDebugCB;
	private JBLabel myScriptParametersLabel;
	private JTextField deployUrlField;
	private JComponent anchor;

	public ::projectProperName::RunConfigurationEditor() {
		workDirField = new JTextField();
		workDirField.setVisible(true);
		final BrowseFilesListener workDirBrowseFilesListener = new BrowseFilesListener(workDirField,
			"Working directory",
			"Specify working directory",
			BrowseFilesListener.SINGLE_DIRECTORY_DESCRIPTOR);
		final FieldPanel workDirFieldPanel = new FieldPanel(workDirField, null, null, workDirBrowseFilesListener, null);
		workDirPanel.setLayout(new BorderLayout());
		workDirPanel.add(workDirFieldPanel, BorderLayout.CENTER);
	}

	public void resetEditorFrom(::projectProperName::RunConfiguration configuration) {
		workDirField.setText(configuration.getWorkDir());

		myDebugCB.setEnabled(true);
		myDebugCB.setSelected(configuration.isDebugEnabled());

		myModulesModel.removeAllElements();
		List<Module> modules = new ArrayList<>(configuration.getValidModules());
		Collections.sort(modules, ModulesAlphaComparator.INSTANCE);
		for (Module module : modules)
			myModulesModel.addElement(module);
		myModulesModel.setSelectedItem(configuration.getModule());

	}

	public void applyEditorTo(::projectProperName::RunConfiguration configuration) throws ConfigurationException {
		configuration.setModule((Module) myModulesBox.getSelectedItem());
		configuration.setDebugEnabled(myDebugCB.isSelected());
		configuration.setDeployURL(deployUrlField.getText());
	}

	@NotNull
	public JComponent createEditor() {
		myModulesModel = new DefaultComboBoxModel();
		myModulesBox.setModel(myModulesModel);
		myDebugCB.setEnabled(true);
		myDebugCB.setSelected(false);

		myModulesBox.setRenderer(new ListCellRendererWrapper<Module>() {
			@Override
			public void customize(JList list, Module module, int index, boolean selected, boolean hasFocus) {
				if (module != null) {
					setIcon(ModuleType.get(module).getIcon());
					setText(module.getName());
				}
			}
		});
		new ComboboxSpeedSearch(myModulesBox) {
			@Override
			protected String getElementText(Object element) {
				return element instanceof Module ? ((Module) element).getName() : "";
			}
		};
		return myMainPanel;
	}

	@Override
	public JComponent getAnchor() {
		return anchor;
	}

	@Override
	public void setAnchor(JComponent anchor) {
		this.anchor = anchor;
		myScriptParametersLabel.setAnchor(anchor);
	}
}