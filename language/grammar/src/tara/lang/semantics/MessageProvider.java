package tara.lang.semantics;

import java.text.MessageFormat;
import java.util.PropertyResourceBundle;

public class MessageProvider {

	private static final String BUNDLE = "messages.semantic";
	private static PropertyResourceBundle resourceBundle;

	private MessageProvider() {

	}

	public static String message(String key, Object... params) {
		if (resourceBundle == null)
			resourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(BUNDLE);
		return MessageFormat.format(resourceBundle.getString(key), params);
	}

}
