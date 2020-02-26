package io.intino.magritte.lang.semantics;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

public class MessageProvider {

	private static final String BUNDLE = "messages.semantic";
	private static PropertyResourceBundle resourceBundle;

	private MessageProvider() {
	}

	public static String message(String key, Object... params) {
		try {
			if (resourceBundle == null)
				resourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(BUNDLE);
			final String text = resourceBundle.getString(key);
			return MessageFormat.format(text, params);
		} catch (MissingResourceException e) {
			return key;
		}
	}

}
