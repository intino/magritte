package tara.magritte.utils;

import java.text.MessageFormat;
import java.util.*;

import static java.util.ResourceBundle.getBundle;

public class MessageProvider {

	private static String BUNDLE = "messages.";
	private Map<String, PropertyResourceBundle> resourceBundle = new HashMap<>();

	public String message(String level, String locale, String key, Object... params) {
		if (!resourceBundle.containsKey(locale))
			resourceBundle.put(locale, (PropertyResourceBundle) getBundle(BUNDLE + level, new Locale(locale, "")));
		return resolveMessage(locale, key, params);
	}

	private String resolveMessage(String locale, String key, Object[] params) {
		try {
			return MessageFormat.format(resourceBundle.get(locale).getString(key), params);
		} catch (MissingResourceException e) {
			return key;
		}
	}

}
