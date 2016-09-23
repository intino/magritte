package tara.intellij.project.configuration.maven;

public interface MavenTags {

	String PROTEO_GROUP_ID = "org.siani.tara";
	String PROTEO_ARTIFACT_ID = "proteo";
	String PROTEO_VERSION = "LATEST";
	String PROTEO_TYPE = "jar";
	String VERSION = "version";
	String DEPENDENCY = "dependency";
	String DEPENDENCIES = "dependencies";
	String REPOSITORY = "repository";
	String GROUP_ID = "groupId";
	String ARTIFACT_ID = "artifactId";
	String URL = "url";
	String ID = "id";

	String MODULE_TYPE = "tara.moduletype";

	String DSL = "tara.dsl";
	String OUT_DSL = "tara.application.dsl";
	String APPLICATION_DSL = "tara.application.dsl";
	String APPLICATION_OUT_DSL = "tara.system.dsl";
	String SYSTEM_DSL = "tara.system.dsl";
	String APPLICATION_IMPORTED_DSL = "tara.application.dsl.from.artifactory";
	String SYSTEM_IMPORTED_DSL = "tara.system.dsl.from.artifactory";
	String SUPPORTED_LANGUAGES = "tara.supported.languages";
	String WORKING_PACKAGE = "tara.working.package";
	String PLATFORM_REFACTOR = "tara.platform.refactor.id";
	String APPLICATION_REFACTOR = "tara.platform.refactor.id";
	String PERSISTENT = "tara.platform.refactor.id";
}
