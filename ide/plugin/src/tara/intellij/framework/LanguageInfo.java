package tara.intellij.framework;

import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.TaraLanguage;

public class LanguageInfo {

	public static LanguageInfo PROTEO = new LanguageInfo(TaraLanguage.PROTEO, LanguageManager.PROTEO_KEY, "LATEST");
	public static String LATEST_VERSION = "LATEST";
	public static String SNAPSHOT_VERSION = "SNAPSHOT";

	private String name;
	private String key;
	private String version;

	public LanguageInfo(String name, String key, String version) {
		this.name = name;
		this.key = key;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return name;
	}
}
