package tara.intellij.project;

import com.intellij.ide.DataManager;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import tara.intellij.lang.TaraIcons;

import javax.swing.*;

public class TaraModuleBuilder extends JavaModuleBuilder {

	@Override
	public String getPresentableName() {
		return "Tara";
	}

	@Override
	public Icon getBigIcon() {
		return TaraIcons.LOGO_16;
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
	protected boolean isAvailable() {
		final DataContext result = DataManager.getInstance().getDataContextFromFocus().getResult();
		return (result != null ? (Project) result.getData(PlatformDataKeys.PROJECT.getName()) : null) == null;
	}
}
