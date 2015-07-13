package siani.tara.intellij.actions;

import org.jetbrains.annotations.NonNls;
import siani.tara.intellij.lang.file.TaraFileType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaraTemplates {

	@NonNls
	public static Map<String, String> templates = new HashMap<>();

	static {
		templates.put("MODEL", "TaraModel." + TaraFileType.INSTANCE.getDefaultExtension());
	}

	private TaraTemplates() {
	}

	public static String getTemplate(String template) {
		return templates.get(template);
	}

	public static Set<Map.Entry<String, String>> getTemplates() {
		return templates.entrySet();
	}

	public static Collection<String> getTemplateValues() {
		return templates.values();
	}


}
