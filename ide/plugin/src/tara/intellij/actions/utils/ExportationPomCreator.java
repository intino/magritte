package tara.intellij.actions.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.idea.maven.model.MavenId;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.siani.itrules.model.Frame;
import tara.templates.ExportPomTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExportationPomCreator {
	private static final Logger LOG = Logger.getInstance(ExportationPomCreator.class.getName());

	public static File createPom(Module module, String languageName) throws IOException {
		return createPom(MavenProjectsManager.getInstance(module.getProject()).findProject(module), FileUtil.createTempFile("pom", ".xml", true), languageName);
	}

	private static File createPom(MavenProject mavenProject, File pom, String languageName) {
		Frame frame = new Frame();
		frame.addTypes("pom");
		frame.addFrame("name", languageName);
		Frame dependency = createDependencyFrame(mavenProject.getMavenId());
		frame.addFrame("dependency", dependency);
		writePom(pom, frame);
		return pom;
	}

	private static Frame createDependencyFrame(MavenId id) {
		return new Frame().addTypes("dependency").addFrame("groupId", id.getGroupId()).addFrame("artifactId", id.getArtifactId()).addFrame("version", id.getVersion());
	}

	private static void writePom(File pom, Frame frame) {
		try {
			Files.write(pom.toPath(), ExportPomTemplate.create().format(frame).getBytes());
		} catch (IOException e) {
			LOG.error("Error creating pom to export: " + e.getMessage());
		}
	}
}
