package tara.intellij.framework;

import tara.dsl.ProteoConstants;

public class LanguageInfo {

	public static LanguageInfo PROTEO = new LanguageInfo(ProteoConstants.PROTEO, "LATEST");
	public static String LATEST_VERSION = "LATEST";
	public static String SNAPSHOT_VERSION = "SNAPSHOT";

	private String name;
	private String version;

	public LanguageInfo(String name, String version) {
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return name;
	}
}
