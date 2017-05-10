package io.intino.tara.compiler.shared;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Configuration {

	enum Level {
		System, Application, Platform;

		public int compareLevelWith(Level type) {
			return type.ordinal() - this.ordinal();
		}

		public boolean is(Level type, int level) {
			return type.ordinal() == level;
		}
	}

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

	String outDSL();

	String interfaceVersion();

	default Map<String, String> repositories() {
		return Collections.emptyMap();
	}

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

	default SimpleEntry<String, String> distributionSnapshotRepository() {
		return new SimpleEntry<>("", "");
	}

	default Map<String, String> languageRepositories() {
		return Collections.emptyMap();
	}

	default List<RunConfiguration> deployConfigurations() {
		return Collections.emptyList();
	}

	interface LanguageLibrary {

		String name();

		String version();

		String effectiveVersion();

		void version(String version);

		String generationPackage();
	}

	interface RunConfiguration {
		String name();

		List<Parameter> parameters();

		List<Service> services();

		String store();

		interface Parameter {
			String name();

			String type();

			String value();
		}

		interface Service {
			String name();

			List<Parameter> parameters();
		}
	}

}
