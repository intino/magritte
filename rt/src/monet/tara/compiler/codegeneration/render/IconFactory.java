package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class IconFactory {

	public static final String IDE_TPL = "intellij/tpl/";
	public static final String IDE = "intellij/";
	private static final String PROJECT_PATH = "monet/tara/" + IDE;
	public static final String CONCEPT_KEY = "_";
	private static final String SRC = "src/";
	private static final Map<String, String> ICONS = new HashMap<>();

	static {
		ICONS.put("-.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/-.png");
		ICONS.put("_.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/_.png");
		ICONS.put("monet.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/monet.png");
		ICONS.put("morph.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/morph.png");
		ICONS.put("polymorphic.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/polymorphic.png");
		ICONS.put("tara100.png", IDE_TPL + SRC + PROJECT_PATH + "lang/icons/-100.png");
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