package tara.intellij.project;

import com.intellij.ide.DataManager;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import tara.intellij.lang.TaraIcons;

import javax.swing.*;

public class TaraModuleBuilder extends EmptyModuleBuilder {

	@Override
	public String getPresentableName() {
		return "Tara";
	}

	@Override
	public Icon getBigIcon() {
		return TaraIcons.ICON_24;
	}

	@Override
	public Icon getNodeIcon() {
		return getBigIcon();
	}

	@Override
	public String getDescription() {
		return "Empty Tara project without modules. Use it to create free-style module structure.";
	}

	@Override
	protected boolean isAvailable() {
		final DataContext result = DataManager.getInstance().getDataContextFromFocus().getResult();
		return (result != null ? (Project) result.getData(PlatformDataKeys.PROJECT.getName()) : null) == null;
	}
}
