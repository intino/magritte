package monet.tara.compiler.codegeneration.render;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
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
		@NonNls
		protected static final String PATH_TO_BUNDLE = "intellij.templates";
		private static Reference<ResourceBundle> ourBundle;

		private TemplateBundle() {
		}

		public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, @NotNull Object... params) {
			return CommonBundle.message(getBundle(), key, params);
		}

		private static ResourceBundle getBundle() {
			ResourceBundle bundle = com.intellij.reference.SoftReference.dereference(ourBundle);
			if (bundle == null) {
				bundle = ResourceBundle.getBundle(PATH_TO_BUNDLE);
				ourBundle = new SoftReference<>(bundle);
			}
			return bundle;
		}
	}
}