package monet.tara.compiler.code_generation.render;

import java.util.HashMap;
import java.util.Map;

public class IconFactory {

	public static final String IDE_TPL = "/intellij/tpl/";
	public static final String IDE = "intellij/";
	private static final String PROJECT_PATH = "monet/tara/" + IDE;
	public static final String CONCEPT_KEY = "_";
	private static final String SRC = "src/";
	private static final Map<String, String> ICONS = new HashMap<>();


	static {
		ICONS.put("-.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/-.png");
		ICONS.put("_.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/_.png");
		ICONS.put("monet.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/monet.png");
		ICONS.put("morph.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/morph.png");
		ICONS.put("polymorphic.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/polymorphic.png");
		ICONS.put("tara100.png", IDE_TPL + SRC + PROJECT_PATH + "metamodel/icons/tara100.png");
	}


	public static Map<String, String> getIcons() {
		return ICONS;
	}

	public static String getIcon(String className) {
		String icon;
		if ((icon = ICONS.get(className)) != null)
			return icon;
		else
			throw new RuntimeException("Icon not defined " + className);
	}
}