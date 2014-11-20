package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.project.module.ModuleConfiguration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigModuleDialogPane extends DialogWrapper {

	private static final String NO_PARENT = "None";
	private final Module module;
	protected JCheckBox terminalCheckBox;
	private JPanel dialogContents;
	private JComboBox metamodelBox;
	private JLabel metamodelField;
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
		return dialogContents;
	}

	public void loadValues() {
		metamodelBox.addItem(NO_PARENT);
		for (Module candidate : candidates) {
			metamodelBox.addItem(candidate.getName());
			if (ModuleConfiguration.getInstance(module).getParentName().equals(candidate.getName()))
				metamodelBox.setSelectedItem(candidate.getName());
		}
		if (metamodelBox.getSelectedItem() == null) metamodelBox.setSelectedItem(NO_PARENT);
		terminalCheckBox.setSelected(ModuleConfiguration.getInstance(module).isTerminal());
	}

	public void saveValues() {
		setParent(!metamodelBox.getSelectedItem().equals(NO_PARENT) ? searchParent((String) metamodelBox.getSelectedItem()) : null);
		setTerminal(terminalCheckBox.isSelected());
	}

	private void setTerminal(boolean selected) {
		ModuleConfiguration.getInstance(module).setTerminal(selected);
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
			if (aModule != module && !ModuleConfiguration.getInstance(aModule).isTerminal()) candidates.add(aModule);
		return candidates.toArray(new Module[candidates.size()]);
	}


	@Override
	public String toString() {
		return "ConfigANTLRPerGrammar{" + '}';
	}
}
