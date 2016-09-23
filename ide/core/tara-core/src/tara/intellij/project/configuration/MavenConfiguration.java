package tara.intellij.project.configuration;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.project.configuration.maven.MavenHelper;
import tara.intellij.project.configuration.maven.ModuleMavenCreator;

import java.util.Collections;
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
	public void reload() {
		final MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		final MavenProject project = manager.findProject(module);
		manager.forceUpdateProjects(Collections.singletonList(project));
	}

	@Override
	public ModuleType type() {
		return ModuleType.valueOf(mavenHelper.moduleType());
	}

	@Override
	public String workingPackage() {
		return mavenHelper.workingPackage();
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
	public String dsl() {
		return mavenHelper.dsl();
	}

	@Override
	public boolean isImportedDsl() {
		return false;
	}

	@Override
	public String outDSL() {
		return mavenHelper.outDSL();
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
	public int refactorId() {
		return 0;
	}

	@Override
	public void refactorId(int id) {

	}

	@Override
	public boolean isPersistent() {
		return false;
	}

}
