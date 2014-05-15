package monet.::projectName::.intellij.project.module;

import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class ModuleType extends JavaModuleType {

	\@NonNls
	public static final String ::projectUpperName::_MODULE = "::projectUpperName::_MODULE";

	public ModuleType() {
		super(::projectUpperName::_MODULE);
	}

	public static com.intellij.openapi.module.ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(::projectUpperName::_MODULE);
	}


	\@NotNull
	\@Override
	public ModuleBuilder createModuleBuilder() {
		return new ModuleBuilder();
	}


	\@NotNull
	public String getDescription() {
		return "::projectProperName:: modules are used for developing <b>::projectProperName::</b> plugins.";
	}

	\@NotNull
	public String getName() {
		return "::projectProperName:: Module";
	}

	public Icon getNodeIcon(final boolean isOpened) {
		return ::projectProperName::Icons.ICON_13;
	}

	public Icon getBigIcon() {
		return ::projectProperName::Icons.ICON_100;
	}
}
