package tara.intellij.project;

import com.intellij.ide.DataManager;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import tara.intellij.lang.TaraIcons;
import tara.intellij.project.configuration.MavenConfiguration;

import javax.swing.*;

import static tara.intellij.project.TaraModuleType.TARA_MODULE_OPTION_NAME;
import static tara.intellij.project.configuration.ConfigurationManager.*;

public class TaraModuleBuilder extends JavaModuleBuilder {


	@Override
	public String getPresentableName() {
		return "Tara";
	}

	@Override
	public Icon getBigIcon() {
		return TaraIcons.LOGO_24;
	}

	@Override
	public Icon getNodeIcon() {
		return getBigIcon();
	}

	@Override
	public String getDescription() {
		return "Tara project";
	}

	@Override
	public ModuleType getModuleType() {
		return TaraModuleType.getModuleType();
	}

	@Override
	protected boolean isAvailable() {
		final DataContext result = DataManager.getInstance().getDataContextFromFocus().getResult();
		return (result != null ? (Project) result.getData(PlatformDataKeys.PROJECT.getName()) : null) == null;
	}

	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		super.setupRootModel(rootModel);
		final Module module = rootModel.getModule();
		module.setOption(TARA_MODULE_OPTION_NAME, "true");
		register(module, !hasExternalProviders() ? new MavenConfiguration(module).init() : newExternalProvider(module));
	}

	@Override
	public int getWeight() {
		return 90;
	}
}
