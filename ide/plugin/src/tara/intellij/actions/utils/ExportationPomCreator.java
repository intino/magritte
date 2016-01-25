package tara.intellij.actions.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.idea.maven.model.MavenArtifact;
import org.jetbrains.idea.maven.model.MavenArtifactNode;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.siani.itrules.model.Frame;
import tara.templates.ExportPomTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class ExportationPomCreator {
	private static final String JAR_EXTENSION = ".jar";
	private static final Logger LOG = Logger.getInstance(ExportationPomCreator.class.getName());

	public static File createPom(Collection<Module> modules, String languageName) throws IOException {
		return createPom(collectDependencies(modules), FileUtil.createTempFile("pom", ".xml", true), languageName);
	}

	private static File createPom(Set<MavenArtifact> mavenArtifacts, File pom, String languageName) {
		Frame frame = new Frame();
		frame.addTypes("pom");
		frame.addFrame("name", languageName);
		List<Frame> dependencies = createDependencyFrame(mavenArtifacts, languageName);
		frame.addFrame("dependency", dependencies.toArray(new Frame[dependencies.size()]));
		writePom(pom, frame);
		return pom;
	}

	private static void writePom(File pom, Frame frame) {
		try {
			Files.write(pom.toPath(), ExportPomTemplate.create().format(frame).getBytes());
		} catch (IOException e) {
			LOG.error("Error creating pom to export: " + e.getMessage());
		}
	}

	private static List<Frame> createDependencyFrame(Set<MavenArtifact> mavenArtifacts, String languageName) {
		List<Frame> dependencies = new ArrayList<>();
		for (MavenArtifact mavenArtifact : mavenArtifacts) {
			Frame dependency = new Frame();
			dependency.addTypes("dependency");
			dependency.addFrame("groupId", mavenArtifact.getGroupId());
			dependency.addFrame("artifactId", mavenArtifact.getArtifactId());
			dependency.addFrame("scope", mavenArtifact.getScope());
			dependency.addFrame("version", mavenArtifact.getVersion());
			if ("system".equalsIgnoreCase(mavenArtifact.getScope()))
				dependency.addFrame("path", "/../.tara/framework/" + languageName + "/" + mavenArtifact.getFile().getName());
			dependencies.add(dependency);
		}
		Frame dslDependency = new Frame();
		dslDependency.addTypes("dependency");
		dslDependency.addFrame("groupId", languageName);
		dslDependency.addFrame("artifactId", languageName);
		dslDependency.addFrame("scope", "system");
		dslDependency.addFrame("version", "1.0");
		dslDependency.addFrame("path", "/../.tara/framework/" + languageName + "/" + languageName + JAR_EXTENSION);
		dependencies.add(dslDependency);
		return dependencies;
	}

	private static Set<MavenArtifact> collectDependencies(Collection<Module> modules) {
		Set<MavenArtifact> dependencies = new HashSet<>();
		for (Module module : modules) {
			MavenProject mavenProject = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
			if (mavenProject != null)
				dependencies.addAll(mavenProject.getDependencyTree().stream().
					filter(d -> d.getArtifact().getScope().equalsIgnoreCase("compile") || d.getArtifact().getScope().equals("system")).
					map(MavenArtifactNode::getArtifact).
					collect(Collectors.toList()));
		}
		return dependencies;
	}

}
