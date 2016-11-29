package tara.intellij.project;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraIcons;

import javax.swing.*;

public class TaraModuleType extends JavaModuleType {

	private static final String TARA_MODULE = "TARA_MODULE";

	@SuppressWarnings("WeakerAccess")
	public static final String TARA_MODULE_OPTION_NAME = "io.intino.tara.isTaraModule";

	public TaraModuleType(@NonNls String id) {
		super(id);
	}

	public TaraModuleType() {
		this(TARA_MODULE);
	}

	@NotNull
	@Override
	public JavaModuleBuilder createModuleBuilder() {
		return new TaraModuleBuilder();
	}

	@NotNull
	@Override
	public String getName() {
		return "Tara";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "Tara Module";
	}

	@Override
	public Icon getBigIcon() {
		return TaraIcons.LOGO_24;
	}

	@Override
	public Icon getNodeIcon(@Deprecated boolean isOpened) {
		return TaraIcons.ICON_16;
	}

	public static boolean isTara(Module module) {
		return module != null && !module.isDisposed() && ("true".equals(module.getOptionValue(TARA_MODULE_OPTION_NAME)) || ModuleType.is(module, ModuleTypeManager.getInstance().findByID(TARA_MODULE)));
	}
}
