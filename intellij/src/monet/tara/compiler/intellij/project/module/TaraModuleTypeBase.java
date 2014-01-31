package monet.tara.compiler.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import monet.tara.compiler.intellij.metamodel.TaraIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class TaraModuleTypeBase<T extends ModuleBuilder> extends ModuleType<T> {

	@NonNls
	public static final String TARA_MODULE = "TARA_MODULE";


	public static ModuleType getInstance() {
		return ModuleTypeManager.getInstance().findByID(TARA_MODULE);
	}

	protected TaraModuleTypeBase() {
		super(TARA_MODULE);
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
		return TaraIcons.ICON;
	}

	public Icon getBigIcon() {
		return TaraIcons.ICON;
	}
}
