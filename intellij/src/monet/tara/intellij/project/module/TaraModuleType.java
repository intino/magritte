package monet.tara.intellij.project.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import monet.tara.intellij.metamodel.TaraIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class TaraModuleType extends ModuleType<TaraModuleBuilder> {

	@NonNls
	public static final String TARA_MODULE = "TARA_MODULE";

	public TaraModuleType() {
		super(TARA_MODULE);
	}

	public static ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(TARA_MODULE);
	}


	@NotNull
	@Override
	public TaraModuleBuilder createModuleBuilder() {
		return new TaraModuleBuilder();
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
