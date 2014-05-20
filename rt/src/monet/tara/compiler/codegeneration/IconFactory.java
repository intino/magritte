package monet.tara.compiler.codegeneration;

import monet.tara.compiler.core.errorcollection.TaraRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class IconFactory {

	public static final String IDE_TPL = "intellij/tpl/";
	private static final String RES = "res";
	private static final Map<String, String> ICONS = new HashMap<>();

	static {
		ICONS.put("-.png", IDE_TPL + RES + "/icons/-.png");
		ICONS.put("_.png", IDE_TPL + RES + "/icons/_.png");
		ICONS.put("monet.png", IDE_TPL + RES + "/icons/monet.png");
		ICONS.put("case.png", IDE_TPL + RES + "/icons/case.png");
		ICONS.put("base.png", IDE_TPL + RES + "/icons/base.png");
		ICONS.put("tara100.png", IDE_TPL + RES + "/icons/-100.png");
	}


	private IconFactory() {
	}

	public static Map<String, String> getIcons() {
		return ICONS;
	}

	public static String getIcon(String className) {
		String icon = ICONS.get(className);
		if (icon != null) return icon;
		else throw new TaraRuntimeException("Icon not defined " + className);
	}
}