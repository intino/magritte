package tara.intellij.project.configuration;

import org.jetbrains.annotations.NotNull;
import tara.dsl.ProteoConstants;

import java.util.List;

public interface Configuration {


	boolean isSuitable();

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

	List<String> supportedLanguages();

	default boolean isOntology() {
		return type().equals(ModuleType.Application) && applicationDsl().equals(ProteoConstants.PROTEO);
	}

	String repository();

	String dsl(ModuleType type);


	String platformDsl();

	String applicationDsl();

	String systemDsl();

	String outDsl(Configuration.ModuleType type);


	String platformOutDsl();

	String applicationOutDsl();

	String dslVersion(String dsl);


	void dslVersion(String dsl, String version);

	String modelVersion();


	void modelVersion(String newVersion);

	boolean isApplicationImportedDsl();


	boolean isSystemImportedDsl();

	int platformRefactorId();


	boolean isPersistent();

	void platformRefactorId(int id);

	int applicationRefactorId();

	void applicationRefactorId(int id);
}
