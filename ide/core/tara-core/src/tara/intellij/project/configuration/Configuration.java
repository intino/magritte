package tara.intellij.project.configuration;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Configuration {


	Configuration init();

	boolean isSuitable();

	void reload();

	enum ModuleType {
		System, Application, Platform;

		public int compareLevelWith(@NotNull ModuleType type) {
			if (type.ordinal() == this.ordinal()) return 0;
			if ((type.ordinal() == 1 || type.ordinal() == 2) && (this.ordinal() == 1 || this.ordinal() == 2)) return 0;
			if ((type.ordinal() == 3 || type.ordinal() == 4) && (this.ordinal() == 3 || this.ordinal() == 4)) return 0;
			return type.ordinal() - this.ordinal();
		}

	}

	ModuleType type();

	String workingPackage();

	List<String> repository();

	List<String> snapshotRepository();

	String dsl();

	boolean isImportedDsl();

	String outDSL();

	String dslVersion();

	void dslVersion(String version);

	String modelVersion();

	void modelVersion(String newVersion);

	int refactorId();

	void refactorId(int id);

	boolean isPersistent();
}
