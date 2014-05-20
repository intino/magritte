package monet.tara.intellij.actions;

import org.jetbrains.annotations.NonNls;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaraTemplates {

	@NonNls
	public static HashMap<String, String> templates = new HashMap<>();

	static {
		//gen %templates%
		templates.put("CONCEPT", "TaraConcept.m2");
		//end
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
