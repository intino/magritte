package tara.intellij.lang;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.awt.*;

public class TaraIcons {


	private static final boolean RETINA = retina();

	public static final Icon ICON_16 = IconLoader.getIcon(RETINA ? "/icons/icon_retina.png" : "/icons/icon.png");
	public static final Icon LOGO_16 = IconLoader.getIcon(RETINA ? "/icons/logo_retina.png" : "/icons/logo_16.png");
	public static final Icon NODE = IconLoader.getIcon(RETINA ? "/icons/icon_retina.png" : "/icons/icon.png");
	public static final Icon STASH_16 = IconLoader.getIcon(RETINA ? "/icons/stash_retina.png" : "/icons/stash_16.png");

	private TaraIcons() {
	}

	private static boolean retina() {
		Object obj = Toolkit.getDefaultToolkit().getDesktopProperty("apple.awt.contentScaleFactor");
		return obj instanceof Float && (((Float) obj).intValue() == 2);
	}

}