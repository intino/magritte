package tara.intellij.project.configuration;

import java.util.List;

public interface Configuration {

	Configuration init();

	boolean isSuitable();

	void reload();

	enum ModuleType {System, Application, Platform}

	ModuleType type();

	String artifactId();

	String groupId();

	String workingPackage();

	List<String> repositories();

	List<String> releaseRepositories();

	List<String> snapshotRepositories();

	String languageRepository();

	String languageRepositoryId();

	String dsl();

	String outDSL();

	String dslVersion();

	void dslVersion(String version);

	String modelVersion();

	void modelVersion(String newVersion);

	int refactorId();

	void refactorId(int id);

	boolean isPersistent();
}
