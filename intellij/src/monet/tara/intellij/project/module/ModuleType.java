package monet.tara.intellij.project.module;

import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import monet.tara.intellij.lang.TaraIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class ModuleType extends JavaModuleType {

	@NonNls
	public static final String TARA_MODULE = "TARA_MODULE";

	public ModuleType() {
		super(TARA_MODULE);
	}

	public static com.intellij.openapi.module.ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(TARA_MODULE);
	}


	@NotNull
	@Override
	public ModuleBuilder createModuleBuilder() {
		return new ModuleBuilder();
	}


	@NotNull
	public String getDescription() {
		return "Tara modules are used for developing <b>Tara</b> plugins.";
	}

	@NotNull
	public String getName() {
		return "Tara Module";
	}

	public Icon getNodeIcon(final boolean isOpened) {
		return TaraIcons.ICON_13;
	}

	public Icon getBigIcon() {
		return TaraIcons.ICON_100;
	}
}
