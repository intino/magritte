package monet.::projectName::.intellij.project.module;

import com.intellij.openapi.module.ModuleTypeManager;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class ModuleType extends com.intellij.openapi.module.ModuleType<ModuleBuilder> {

	\@NonNls
	public static final String TARA_MODULE = "TARA_MODULE";

	public ModuleType() {
		super(TARA_MODULE);
	}

	public static com.intellij.openapi.module.ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(TARA_MODULE);
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
