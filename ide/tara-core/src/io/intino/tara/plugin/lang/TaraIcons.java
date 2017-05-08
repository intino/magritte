package io.intino.tara.plugin.lang;

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.IconUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;

import static com.intellij.openapi.util.IconLoader.getIcon;

public class TaraIcons {

	private static final boolean RETINA = UIUtil.isRetina();

	public static final Icon MODEL_16 = RETINA ? scale(IconLoader.getIcon("/icons/files/model-32.png")) : IconLoader.getIcon("/icons/files/model-16.png");
	public static final Icon ICON_16 = RETINA ? scale(getIcon("/icons/files/model-32.png")) : getIcon("/icons/files/model-16.png");
	public static final Icon LOGO_24 = RETINA ? scale(getIcon("/icons/logo-48.png")) : getIcon("/icons/logo-24.png");
	public static final Icon NODE = RETINA ? scale(getIcon("/icons/files/model-32.png")) : getIcon("/icons/files/model-16.png");
	public static final Icon STASH_16 = RETINA ? scale(getIcon("/icons/files/stash-32.png")) : getIcon("/icons/files/stash-16.png");

	private TaraIcons() {
	}

	private static Icon scale(Icon icon) {
		return IconUtil.scale(icon, 0.5);
	}
}