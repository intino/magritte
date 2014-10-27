package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.project.module.ModuleConfiguration;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ConfigModuleDialogPane extends DialogWrapper {

	private final Module module;
	protected JCheckBox systemCheckBox;
	private JPanel dialogContents;
	private JCheckBox parentCheckBox;
	private JComboBox parentComboBox;
	private Module[] candidates;

	public ConfigModuleDialogPane(final Project project, Module module) {
		super(project, false);
		this.module = module;
		init();
		candidates = getParentModulesCandidates(project, module);
		loadValues();
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		parentCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				parentComboBox.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		return dialogContents;
	}

	public void loadValues() {
		if (ModuleConfiguration.getInstance(module).getParentName().isEmpty()) {
			parentCheckBox.setSelected(false);
			parentComboBox.setEnabled(false);
		} else parentCheckBox.setSelected(true);
		for (Module candidate : candidates) {
			parentComboBox.addItem(candidate.getName());
			if (ModuleConfiguration.getInstance(module).getParentName().equals(candidate.getName()))
				parentComboBox.setSelectedItem(candidate.getName());
		}
		systemCheckBox.setSelected(ModuleConfiguration.getInstance(module).isSystem());
	}

	public void saveValues() {
		setParent(parentCheckBox.isSelected() ? searchParent((String) parentComboBox.getSelectedItem()) : null);
		setSystem(systemCheckBox.isSelected());
	}

	private void setSystem(boolean selected) {
		ModuleConfiguration.getInstance(module).setSystem(selected);
	}

	private Module searchParent(String parentName) {
		for (Module candidate : candidates)
			if (parentName.equals(candidate.getName()))
				return candidate;
		return null;
	}

	private void setParent(Module parent) {
		if (parent == null) {
			ModuleConfiguration.getInstance(module).setParentName("");
			ModuleConfiguration.getInstance(module).setParentFilePath("");
		} else {
			ModuleConfiguration.getInstance(module).setParentName(parent.getName());
			ModuleConfiguration.getInstance(module).setParentFilePath(parent.getModuleFilePath());
		}
	}

	private Module[] getParentModulesCandidates(Project project, Module module) {
		List<Module> candidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules())
			if (aModule != module && !ModuleConfiguration.getInstance(aModule).isSystem()) candidates.add(aModule);
		return candidates.toArray(new Module[candidates.size()]);
	}


	@Override
	public String toString() {
		return "ConfigANTLRPerGrammar{" +
			"ParentCheckBox = " + parentCheckBox +
			", ParentModule = " + parentComboBox + '}';
	}
}
