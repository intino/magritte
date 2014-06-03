import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.SoftReference;
import java.util.ResourceBundle;
import java.util.Set;

public class ExtensionFactory {

	private ExtensionFactory() {
	}

	public static Set<String> getTemplates() {
		return TemplateBundle.getBundle().keySet();
	}

	public static String getTemplate(String key) {
		return TemplateBundle.message(key);
	}


	private static class TemplateBundle {
		@NonNls
		protected static final String PATH_TO_BUNDLE = "extensions";
		private static java.lang.ref.Reference<ResourceBundle> ourBundle;

		private TemplateBundle() {
		}

		public static String message(
			@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, @NotNull Object... params) {
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
