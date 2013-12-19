package monet.tara.plugin.project;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import monet.tara.metamodel.TaraIcons;

import javax.swing.*;

public class TaraModuleBuilder extends ModuleBuilder {
	@Override
	public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {

	}

	@Override
	public ModuleType getModuleType() {
		return null

	}

	@Override
	public Icon getNodeIcon() {
		return TaraIcons.ICON;
	}
}
