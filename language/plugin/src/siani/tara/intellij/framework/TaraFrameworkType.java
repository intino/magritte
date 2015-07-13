package siani.tara.intellij.framework;

import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;

import javax.swing.*;

public class TaraFrameworkType extends FrameworkTypeEx {
	public static final String ID = "tara-java";

	public TaraFrameworkType() {
		super(ID);
	}

	public static TaraFrameworkType getFrameworkType() {
		return EP_NAME.findExtension(TaraFrameworkType.class);
	}

	@NotNull
	@Override
	public FrameworkSupportInModuleProvider createProvider() {
		return new TaraSupportProvider();
	}

	@NotNull
	@Override
	public String getPresentableName() {
		return "Tara";
	}

	@NotNull
	@Override
	public Icon getIcon() {
		return TaraIcons.ICON_13;
	}
}
