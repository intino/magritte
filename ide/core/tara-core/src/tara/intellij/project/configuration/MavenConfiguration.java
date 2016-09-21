package tara.intellij.project.configuration;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.project.configuration.maven.MavenHelper;
import tara.intellij.project.configuration.maven.ModuleMavenCreator;

import java.util.List;

public class MavenConfiguration implements Configuration {

	private final Module module;
	private final MavenHelper mavenHelper;

	public MavenConfiguration(Module module) {
		this.module = module;
		this.mavenHelper = new MavenHelper(module);
	}

	@Override
	public Configuration init() {
		ModuleMavenCreator mavenizer = new ModuleMavenCreator(module);
		if (module.getProject().isInitialized()) mavenizer.mavenize();
		else startWithMaven(mavenizer, module.getProject());
		return this;
	}

	private void startWithMaven(final ModuleMavenCreator mavenizer, Project project) {
		StartupManager.getInstance(project).registerPostStartupActivity(mavenizer::mavenize);
	}

	@Override
	public boolean isSuitable() {
		return MavenProjectsManager.getInstance(module.getProject()).findProject(module) != null;
	}

	@Override
	public ModuleType type() {
		return ModuleType.valueOf(mavenHelper.moduleType());
	}

	@Override
	public List<String> supportedLanguages() {
		return mavenHelper.supportedLanguages();
	}

	@Override
	public String repository() {
		return mavenHelper.releaseRepository();
	}

	@Override
	public String dsl(Configuration.ModuleType type) {
		return mavenHelper.dslOf(type);
	}

	@Override
	public String platformDsl() {
		return mavenHelper.dslOf(ModuleType.Platform);
	}

	@Override
	public String applicationDsl() {
		return mavenHelper.dslOf(ModuleType.Application);
	}

	@Override
	public String systemDsl() {
		return mavenHelper.dslOf(ModuleType.System);
	}

	@Override
	public String outDsl(Configuration.ModuleType type) {
		return mavenHelper.outDSLOf(ModuleType.System);
	}

	@Override
	public String platformOutDsl() {
		return mavenHelper.outDSLOf(ModuleType.Platform);
	}

	@Override
	public String applicationOutDsl() {
		return mavenHelper.outDSLOf(ModuleType.Application);
	}

	@Override
	public String dslVersion(String dsl) {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		return project == null ? "" : new MavenHelper(module).dslVersion(mavenHelper.dslMavenId(module, dsl));
	}

	@Override
	public void dslVersion(String dsl, String version) {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project != null) new MavenHelper(module).dslVersion(mavenHelper.dslMavenId(module, dsl), version);
	}

	@Override
	public String modelVersion() {
		return mavenHelper.version();
	}

	@Override
	public void modelVersion(String newVersion) {
		mavenHelper.version(newVersion);
	}

	@Override
	public boolean isApplicationImportedDsl() {
		return mavenHelper.importedDSL(ModuleType.Application);
	}

	@Override
	public boolean isSystemImportedDsl() {
		return mavenHelper.importedDSL(ModuleType.System);
	}

	@Override
	public int platformRefactorId() {
		return 0;
	}

	@Override
	public boolean isPersistent() {
		return false;
	}

	@Override
	public void platformRefactorId(int id) {

	}

	@Override
	public int applicationRefactorId() {
		return 0;
	}

	@Override
	public void applicationRefactorId(int id) {

	}


}
