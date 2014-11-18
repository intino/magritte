package siani.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TaraWizardStep extends ModuleWizardStep {

	private final TaraModuleBuilder builder;
	private final WizardContext context;
	protected JCheckBox terminalCheckBox;
	private Project project;
	private Module myParent;
	private boolean terminal;
	private JPanel mainPanel;
	private JComboBox metamodelBox;


	public TaraWizardStep(TaraModuleBuilder builder, WizardContext context, Project project) {
		this.context = context;
		this.builder = builder;
		this.project = project;
		terminalCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				terminal = ((JCheckBox) e.getSource()).isSelected();
			}
		});
		if (project == null) metamodelBox.setEnabled(false);
		else {
			loadBoxValues();
			metamodelBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					myParent = (Module) ((JComboBox) e.getSource()).getSelectedItem();
				}
			});
		}
	}

	private void loadBoxValues() {
		for (Module candidate : ModuleManager.getInstance(this.project).getModules())
			metamodelBox.addItem(candidate.getName());
	}

	@Override
	public JComponent getComponent() {
		return mainPanel;
	}

	@Override
	public void updateDataModel() {
		context.setProjectBuilder(builder);
		builder.setParentModule(myParent);
		builder.setSystem(terminal);
	}

}
