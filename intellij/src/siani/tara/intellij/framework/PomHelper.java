package siani.tara.intellij.framework;

import org.jetbrains.idea.maven.model.MavenArtifact;
import org.jetbrains.idea.maven.project.MavenProject;

public class PomHelper {
	private final MavenProject mavenProject;

	public PomHelper(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public boolean hasMagritteDependency() {
		for (MavenArtifact mavenArtifact : mavenProject.getDependencies())
			if (mavenArtifact.getGroupId().equals("org.siani.magritte") && mavenArtifact.getArtifactId().equals("magritte"))
				return true;
		return false;
	}


	public void addMagritteDependency() {
		mavenProject.addDependency(createMagritteArtifact());
	}

	private MavenArtifact createMagritteArtifact() {
		return new MavenArtifact("org.siani.magritte", "magritte", "LATEST", "", "", "", "compilation", false, "", null, null, false, false);
	}
}
