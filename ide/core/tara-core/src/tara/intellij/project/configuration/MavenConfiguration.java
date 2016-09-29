package tara.intellij.project.configuration;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import org.jetbrains.idea.maven.model.MavenRemoteRepository;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.project.configuration.maven.MavenHelper;
import tara.intellij.project.configuration.maven.MavenTags;
import tara.intellij.project.configuration.maven.ModuleMavenCreator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MavenConfiguration implements Configuration {

	private final Module module;
	private final MavenProject maven;
	private final MavenHelper mavenHelper;


	public MavenConfiguration(Module module) {
		this.module = module;
		maven = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
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
		final String property = maven.getProperties().getProperty(MavenTags.LEVEL);
		return property == null ? ModuleType.Platform : ModuleType.valueOf(property);
	}

	@Override
	public String artifactId() {
		return maven.getMavenId().getArtifactId();
	}

	@Override
	public String groupId() {
		return maven.getMavenId().getGroupId();
	}

	@Override
	public String workingPackage() {
		final String property = maven.getProperties().getProperty(MavenTags.WORKING_PACKAGE);
		return property == null ? outDSL() : property;
	}

	@Override
	public List<String> repositories() {
		return maven.getRemoteRepositories().stream().
			filter(repository -> repository.getSnapshotsPolicy() == null).
			map(MavenRemoteRepository::getUrl).collect(Collectors.toList());
	}

	@Override
	public List<String> snapshotRepositories() {
		return maven.getRemoteRepositories().stream().
			filter(repository -> repository.getSnapshotsPolicy() != null).
			map(MavenRemoteRepository::getUrl).collect(Collectors.toList());
	}

	@Override
	public String dsl() {
		return maven.getProperties().getProperty(MavenTags.DSL);
	}

	@Override
	public String outDSL() {
		return maven.getProperties().getProperty(MavenTags.OUT_DSL);
	}

	@Override
	public String dslVersion() {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		return project == null ? "" : new MavenHelper(module).dslVersion(mavenHelper.dslMavenId(module, dsl()));
	}

	@Override
	public void dslVersion(String version) {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project != null) new MavenHelper(module).dslVersion(mavenHelper.dslMavenId(module, dsl()), version);
	}

	@Override
	public String modelVersion() {
		return maven.getMavenId().getVersion();
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
