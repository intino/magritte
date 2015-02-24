package siani.tara.intellij.project.module;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleConfigurationEditor;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationEditorProvider;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;
import siani.tara.intellij.project.module.ui.TaraModuleBuildConfEditor;

import java.util.ArrayList;
import java.util.List;

public class PluginModuleEditorsProvider implements ModuleConfigurationEditorProvider {


	@Override
	public ModuleConfigurationEditor[] createEditors(ModuleConfigurationState state) {
		Module module = state.getRootModel().getModule();
		if (!TaraModuleType.isOfType(module)) {
			return ModuleConfigurationEditor.EMPTY;
		}
		List<ModuleConfigurationEditor> editors = new ArrayList<>();
		editors.add(new TaraModuleBuildConfEditor(state));
		return editors.toArray(new ModuleConfigurationEditor[editors.size()]);
	}
}
