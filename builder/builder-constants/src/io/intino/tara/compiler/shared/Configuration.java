package io.intino.tara.compiler.shared;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Configuration {

	default Configuration init() {
		return null;
	}

	default boolean isSuitable() {
		return false;
	}

	default void reload() {
	}

	String groupId();

	String artifactId();

	String version();

	void version(String newVersion);

	String workingPackage();

	String nativeLanguage();

	Level level();

	List<? extends LanguageLibrary> languages();

	default LanguageLibrary language(java.util.function.Predicate<LanguageLibrary> predicate) {
		return languages().stream().filter(predicate).findFirst().orElse(null);
	}

	@Deprecated
	default String outDSL() {
		return outLanguage();
	}

	String outLanguage();

	String outLanguageVersion();

	String boxVersion();

	String boxPackage();

	default Map<String, String> releaseRepositories() {
		return Collections.emptyMap();
	}

	default String snapshotRepository() {
		return "";
	}

	default SimpleEntry<String, String> distributionLanguageRepository() {
		return new SimpleEntry<>("", "");
	}

	default SimpleEntry<String, String> distributionReleaseRepository() {
		return new SimpleEntry<>("", "");
	}

	default Map<String, String> languageRepositories() {
		return Collections.emptyMap();
	}

	default List<DeployConfiguration> deployConfigurations() {
		return Collections.emptyList();
	}

	enum Level {
		Solution, Product, Platform;

		public int compareLevelWith(Level type) {
			return type.ordinal() - this.ordinal();
		}

		public boolean is(Level type, int level) {
			return type.ordinal() == level;
		}
	}

	interface LanguageLibrary {

		String name();

		String version();

		String effectiveVersion();

		void version(String version);

		String generationPackage();
	}

	interface DeployConfiguration {
		String name();

		boolean pro();

		List<Parameter> parameters();

		interface Parameter {
			String name();

			String value();
		}
	}

}
