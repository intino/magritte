package io.intino.tara.compiler.shared;

import java.util.AbstractMap;
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

	Level level();

	List<? extends LanguageLibrary> languages();

	default LanguageLibrary language(java.util.function.Predicate<LanguageLibrary> predicate) {
		return languages().stream().filter(predicate).findFirst().orElse(null);
	}

	String outDSL();

	String interfaceVersion();

	default List<String> repositories() {
		return Collections.emptyList();
	}

	default Map<String, String> releaseRepositories() {
		return Collections.emptyMap();
	}

	default String snapshotRepository() {
		return "";
	}

	default AbstractMap.SimpleEntry<String, String> distributionLanguageRepository() {
		return new AbstractMap.SimpleEntry<>("", "");
	}

	default AbstractMap.SimpleEntry<String, String> distributionReleaseRepository() {
		return new AbstractMap.SimpleEntry<>("", "");
	}

	default AbstractMap.SimpleEntry<String, String> distributionSnapshotRepository() {
		return new AbstractMap.SimpleEntry<>("", "");
	}

	default String languageRepository() {
		return "";
	}

	default String languageRepositoryId() {
		return "";
	}

	interface LanguageLibrary {

		String name();

		String version();

		String effectiveVersion();

		void version(String version);

		String generationPackage();
	}

}
