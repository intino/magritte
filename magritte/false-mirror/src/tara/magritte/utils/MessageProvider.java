package tara.magritte.utils;

import tara.magritte.ModelHandler;

import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.ResourceBundle.getBundle;

public class MessageProvider {
	protected static final Logger LOG = Logger.getLogger(ModelHandler.class.getName());

	private static String BUNDLE = "messages.";
	private List<String> levels = new ArrayList<>();
	private Map<String, List<PropertyResourceBundle>> bundles = new HashMap<>();

	public void register(String level) {
		levels.add(0, level);
	}

	public String message(String locale, String key, Object... params) {
		if (!bundles.containsKey(locale)) bundles.put(locale, collectBundles(locale));
		return resolveMessage(locale, key, params);
	}

	public Map<String, String> wordsIn(String language) {
		Map<String, String> map = new HashMap();
		extractKeys(language, map);
		return map;
	}

	private List<PropertyResourceBundle> collectBundles(String locale) {
		return levels.stream().map(level -> (PropertyResourceBundle) getBundle(BUNDLE + level, new Locale(locale, ""))).collect(Collectors.toList());
	}

	private String resolveMessage(String locale, String key, Object[] params) {
		for (PropertyResourceBundle bundle : bundles.get(locale)) {
			try {
				return MessageFormat.format(bundle.getString(key), params);
			} catch (MissingResourceException ignored) {
			}
		}
		LOG.warning("Message key not found");
		return key;
	}

	private void extractKeys(String language, Map<String, String> map) {
		for (PropertyResourceBundle bundle : bundles.get(language)) {
			final Enumeration<String> keys = bundle.getKeys();
			while (keys.hasMoreElements()) {
				final String key = keys.nextElement();
				map.put(key, bundle.getString(key));
			}
		}
	}

}
