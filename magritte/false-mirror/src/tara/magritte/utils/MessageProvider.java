package tara.magritte.utils;

import java.text.MessageFormat;
import java.util.*;

import static java.util.ResourceBundle.getBundle;

public class MessageProvider {

	private static String BUNDLE = "messages.message";
	private static Map<String, PropertyResourceBundle> resourceBundle = new HashMap<>();

	private MessageProvider() {
	}

	public static String message(String locale, String key, Object... params) {
		if (!resourceBundle.containsKey(locale))
			resourceBundle.put(locale, (PropertyResourceBundle) getBundle(BUNDLE, new Locale(locale, "")));
		return resolveMessage(locale, key, params);
	}

	private static String resolveMessage(String locale, String key, Object[] params) {
		try {
			return MessageFormat.format(resourceBundle.get(locale).getString(key), params);
		} catch (MissingResourceException e) {
			return key;
		}
	}

}
