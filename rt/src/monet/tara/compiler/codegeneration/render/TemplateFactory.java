package monet.tara.compiler.codegeneration.render;

import java.util.ResourceBundle;

public class TemplateFactory {

	private static final String GRAMMAR = "Tara.bnf";
	private static final String PLUGIN_DESCRIPTOR = "plugin.xml";
	private static final String[] LEXERS = new String[]{"Tara.flex", "TaraHighlighterLex.flex"};

	private TemplateFactory() {
	}

	public static java.util.Set<String> getTemplates() {
		return TemplateBundle.getBundle().keySet();
	}

	public static String getTemplate(String key) {
		return TemplateBundle.message(key);
	}

	public static String[] getLexerTemplates() {
		return new String[]{TemplateBundle.message(LEXERS[0]), TemplateBundle.message(LEXERS[1])};
	}

	public static String getPluginDescriptorTemplate() {
		return PLUGIN_DESCRIPTOR;
	}

	public static String getGrammarTemplate() {
		return TemplateBundle.message(GRAMMAR);
	}

	private static class TemplateBundle {
		protected static final String PATH_TO_BUNDLE = "intellij.templates";
		private static ResourceBundle ourBundle;

		private TemplateBundle() {
		}

		public static String message(String key) {
			return getBundle().getString(key);
		}

		private static ResourceBundle getBundle() {
			ourBundle = ResourceBundle.getBundle(PATH_TO_BUNDLE);
			return ourBundle;
		}
	}
}