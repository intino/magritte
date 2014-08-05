package siani.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class TaraWizardStep extends ModuleWizardStep {

	private final TaraModuleBuilder builder;
	private final WizardContext context;
	private Project project;
	private Module myParent;
	private boolean system;

	private JPanel myMainPanel;
	private JCheckBox systemCheckBox;
	private JPanel whitePanel;
	private JList moduleChooser;

	public TaraWizardStep(TaraModuleBuilder builder, WizardContext context, Project project) {
		this.context = context;
		this.builder = builder;
		this.project = project;
		systemCheckBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				system = ((JCheckBox) e.getSource()).isSelected();
			}
		});
		List<Module> modules = new ArrayList<>();

		for (Module module : ModuleManager.getInstance(project).getModules())
			if (TaraModuleType.TARA_MODULE.equals(module.getOptionValue(Module.ELEMENT_TYPE)) && !ModuleConfiguration.getInstance(module).isSystem())
				modules.add(module);
		moduleChooser.setListData(modules.toArray());
		moduleChooser.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				myParent = (Module) ((JList) e.getSource()).getSelectedValue();
			}
		});
	}


	@Override
	public JComponent getComponent() {
		return myMainPanel;
	}

	@Override
	public void updateDataModel() {
		context.setProjectBuilder(builder);
		builder.setParentModule(myParent);
		builder.setSystem(system);
	}
}
