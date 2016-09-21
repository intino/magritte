package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.project.configuration.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import java.io.File;
import java.io.IOException;

public class LanguageExporter {

	private final File source;
	private final Module module;
	private final String dsl;

	public LanguageExporter(Module module, String dsl, File source) {
		this.module = module;
		this.dsl = dsl;
		this.source = source;
	}

	public int export() throws IOException {
		ArtifactoryConnector connector = new ArtifactoryConnector(TaraSettings.getSafeInstance(module.getProject()), new MavenHelper(module).snapshotRepository());
		return connector.put(source, dsl, version());
	}

	private String version() {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project == null) return "1.0";
		return project.getMavenId().getVersion();
	}
}