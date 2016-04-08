package tara.intellij.framework;

import com.intellij.openapi.module.Module;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.facet.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import java.io.File;
import java.io.IOException;

public class LanguageExporter {

	private final File source;
	private final TaraFacetConfiguration configuration;
	private final Module module;

	public LanguageExporter(Module module, File source) {
		this.module = module;
		configuration = TaraUtil.getFacetConfiguration(module);
		this.source = source;
	}

	public int export() throws IOException {
		ArtifactoryConnector connector = new ArtifactoryConnector(TaraSettings.getSafeInstance(module.getProject()), new MavenHelper(module).snapshotRepository());
		return connector.put(source, configuration.outputDsl(), version());
	}

	private String version() {
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project == null) return "1.0";
		return project.getMavenId().getVersion();
	}
}