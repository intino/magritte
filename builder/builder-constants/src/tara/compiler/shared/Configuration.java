package tara.compiler.shared;

import java.util.Collections;
import java.util.List;

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

	default List<String> repositories() {
		return Collections.emptyList();
	}

	default List<String> releaseRepositories() {
		return Collections.emptyList();
	}

	default List<String> snapshotRepositories() {
		return Collections.emptyList();
	}

	default String distributionRepository() {
		return "";
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

	int refactorId();

	void refactorId(int id);

	boolean isPersistent();
}
