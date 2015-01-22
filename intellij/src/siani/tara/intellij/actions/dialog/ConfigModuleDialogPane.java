package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
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
	private JComboBox language;
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
			if (ModuleConfiguration.getInstance(module).getMetamodelName().equals(candidate.getName()))
				metamodelBox.setSelectedItem(candidate.getName());
		}
		if (metamodelBox.getSelectedItem() == null) metamodelBox.setSelectedItem(NO_PARENT);
		terminalCheckBox.setSelected(ModuleConfiguration.getInstance(module).isTerminal());
		language.setSelectedItem(ModuleConfiguration.getInstance(module).getLanguage());
	}

	public void saveValues() {
		setParent(!metamodelBox.getSelectedItem().equals(NO_PARENT) ? searchParent((String) metamodelBox.getSelectedItem()) : null);
		setTerminal(terminalCheckBox.isSelected());
		setLanguage(language.getSelectedItem().toString());
	}

	private void setLanguage(String language) {
		ModuleConfiguration.getInstance(module).setLanguage(language);
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
			removeParentDependency();
			ModuleConfiguration.getInstance(module).setMetamodelName("");
			ModuleConfiguration.getInstance(module).setMetamodelFilePath("");
		} else {
			addModelDependency(parent);
			ModuleConfiguration.getInstance(module).setMetamodelName(parent.getName());
			ModuleConfiguration.getInstance(module).setMetamodelFilePath(parent.getModuleFilePath());
		}
	}

	private void addModelDependency(final Module parent) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				if (!ModuleRootManager.getInstance(module).isDependsOn(parent)) {
					ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
					modifiableModel.addModuleOrderEntry(parent);
					modifiableModel.commit();
				}
			}
		});

	}

	private void removeParentDependency() {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				String parentName;
				if ((parentName = ModuleConfiguration.getInstance(module).getMetamodelName()).isEmpty()) return;
				Module parentModule = searchParent(parentName);
				if (parentModule == null) return;
				ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
				OrderEntry[] orderEntries = modifiableModel.getOrderEntries();
				OrderEntry orderEntry = findOrderEntry(orderEntries, parentModule);
				if (orderEntry != null)
					modifiableModel.removeOrderEntry(orderEntry);
				modifiableModel.commit();
			}
		});
	}


	private OrderEntry findOrderEntry(OrderEntry[] orderEntries, Module parentModule) {
		for (OrderEntry entry : orderEntries)
			if (entry instanceof ModuleOrderEntry && parentModule.equals(((ModuleOrderEntry) entry).getModule()))
				return entry;
		return null;
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
