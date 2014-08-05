package siani.tara.intellij.project.module;

import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;

import javax.swing.*;


public class TaraModuleType extends JavaModuleType {

	@NonNls
	public static final String TARA_MODULE = "TARA_MODULE";

	public TaraModuleType() {
		super(TARA_MODULE);
	}

	public static com.intellij.openapi.module.ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(TARA_MODULE);
	}

	@NotNull
	@Override
	public TaraModuleBuilder createModuleBuilder() {
		return new TaraModuleBuilder();
	}

	public static boolean isOfType(Module module){
		return ModuleType.get(module) instanceof TaraModuleType;
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
		return TaraIcons.getIcon(TaraIcons.ICON_13);
	}

	public Icon getBigIcon() {
		return TaraIcons.getIcon(TaraIcons.ICON_100);
	}
}
