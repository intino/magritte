package tara.compiler.shared;

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

	Level level();

	String artifactId();

	String groupId();

	String workingPackage();

	String dslWorkingPackage();

	default List<String> repositories() {
		return Collections.emptyList();
	}

	default Map<String, String> releaseRepositories() {
		return Collections.emptyMap();
	}

	default String snapshotRepository() {
		return "";
	}

	default AbstractMap.SimpleEntry<String, String> distributionRepository() {
		return new AbstractMap.SimpleEntry<>("", "");
	}

	default String languageRepository() {
		return "";
	}

	default String languageRepositoryId() {
		return "";
	}

	String dsl();

	String outDSL();

	String dslVersion();

	String dslEffectiveVersion();

	void dslVersion(String version);

	String modelVersion();

	void modelVersion(String newVersion);

}
