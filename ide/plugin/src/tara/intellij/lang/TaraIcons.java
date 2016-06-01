package tara.intellij.lang;

import com.intellij.util.IconUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;

import static com.intellij.openapi.util.IconLoader.getIcon;

public class TaraIcons {

	private static final boolean RETINA = UIUtil.isRetina();

	public static final Icon ICON_16 = RETINA ? scale(getIcon("/icons/icon_retina.png")) : getIcon("/icons/icon.png");
	public static final Icon LOGO_16 = RETINA ? scale(getIcon("/icons/logo_retina.png")) : getIcon("/icons/logo_16.png");
	public static final Icon PLUS = getIcon("/icons/plus.png");
	public static final Icon DELETE = getIcon("/icons/delete.png");
	public static final Icon NODE = RETINA ? scale(getIcon("/icons/icon_retina.png")) : getIcon("/icons/icon.png");
	public static final Icon STASH_16 = RETINA ? scale(getIcon("/icons/stash_retina.png")) : getIcon("/icons/stash_16.png");
	public static final Icon LOGO_80 = getIcon("/icons/logo_80.png");

	private TaraIcons() {
	}

	private static Icon scale(Icon icon) {
		return IconUtil.scale(icon, 0.5);
	}
}