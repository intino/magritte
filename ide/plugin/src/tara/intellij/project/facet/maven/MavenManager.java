package tara.intellij.project.facet.maven;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.vcsUtil.VcsUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.siani.itrules.model.Frame;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.templates.ModulePomTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.io.File.separator;

public class MavenManager {

	private static final String POM_XML = "pom.xml";

	final String dsl;
	final Module module;

	public MavenManager(String dsl, Module module) {
		this.dsl = dsl;
		this.module = module;
	}

	public void mavenize() {
		List<VirtualFile> pomFiles = createPoms(module);
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		manager.addManagedFilesOrUnignore(pomFiles);
		manager.importProjects();
		manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles();
	}

	private List<VirtualFile> createPoms(Module module) {
		List<VirtualFile> files = new ArrayList<>();
		files.addAll(isProjectModule(module) ? projectModulePom(module) : modulePom(module));
		return files;
	}

	private Collection<VirtualFile> modulePom(final Module module) {
		final PsiFile[] files = new PsiFile[2];
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
				if (project == null) {
					PsiDirectory root = getModuleRoot(module);
					files[0] = findPom(root);
					createPoms(root);
				} else updateModulePom(project);
			}

			private void createPoms(PsiDirectory root) {
				files[0] = root.createFile(POM_XML);
				createPom(files[0].getVirtualFile().getPath(), ModulePomTemplate.create().format(createModuleFrame(module)));
				if (!getProjectPom(module).exists())
					files[1] = createProjectPom(module);
			}
		});
		return toVirtual(files);
	}

	private Collection<VirtualFile> toVirtual(PsiFile[] files) {
		List<VirtualFile> vFiles = new ArrayList<>();
		for (PsiFile file : files)
			if (file != null) vFiles.add(file.getVirtualFile());
		return vFiles;
	}

	private PsiFile createProjectPom(Module module) {
		VirtualFile pom = projectPom(module);
		return PsiManager.getInstance(module.getProject()).findFile(pom);
	}

	@NotNull
	private File getProjectPom(Module module) {
		return new File(module.getProject().getBaseDir().getPath() + separator + POM_XML);
	}

	private Collection<VirtualFile> projectModulePom(final Module module) {
		final PsiFile[] file = new PsiFile[1];
		ApplicationManager.getApplication().runWriteAction(() -> {
			MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
			if (project == null) {
				PsiDirectory root = getModuleRoot(module);
				file[0] = findPom(root);
				file[0] = root.createFile(POM_XML);
				createPom(file[0].getVirtualFile().getPath(), ModulePomTemplate.create().format(createModuleFrame(module)));
			} else updateModulePom(project);
		});
		return new ArrayList<VirtualFile>() {{
			add(file[0].getVirtualFile());
		}};
	}


	private void updateModulePom(MavenProject mavenProject) {
		MavenHelper helper = new MavenHelper(module, mavenProject);
		if (!helper.hasMagritteDependency()) helper.addMagritte();
	}

	private boolean isProjectModule(Module module) {
		return module.getProject().getBaseDir().getPath().equals(new File(module.getModuleFilePath()).getParent());
	}

	private PsiDirectory getModuleRoot(Module module) {
		VirtualFile moduleFile = module.getModuleFile();
		if (moduleFile != null)
			return PsiManager.getInstance(module.getProject()).findDirectory(moduleFile.getParent());
		else {
			VirtualFile baseDir = module.getProject().getBaseDir();
			return PsiManager.getInstance(module.getProject()).findDirectory(baseDir).findSubdirectory(module.getName());
		}
	}

	private PsiFile findPom(PsiDirectory root) {
		for (PsiElement element : root.getChildren())
			if (element instanceof PsiFile && POM_XML.equals(((PsiFile) element).getVirtualFile().getName()))
				return (PsiFile) element;
		return null;
	}

	private File createPom(String path, String text) {
		try {
			File file = new File(path);
			FileWriter writer = new FileWriter(file);
			writer.write(text);
			writer.close();
			return file;
		} catch (IOException ignored) {
		}
		return null;
	}

	private VirtualFile projectPom(final Module module) {
		final PsiFile[] file = new PsiFile[1];
		ApplicationManager.getApplication().runWriteAction(() -> {
			File pomFile = getProjectPom(module);
			VirtualFile directory = VcsUtil.getVcsRootFor(module.getProject(), VcsUtil.getFilePath(pomFile));
			PsiDirectory root = PsiManager.getInstance(module.getProject()).findDirectory(directory);
			file[0] = findPom(root);
			if (file[0] == null) file[0] = root.createFile("pom.xml");
//				createPom(file[0].getVirtualFile().getPath(), ProjectPomTemplate.create().format(createProjectFrame(module)));
		});
		return file[0].getVirtualFile();
	}


	private Frame createModuleFrame(Module module) {
		Frame frame = new Frame().addTypes("pom");
		frame.addFrame("project", module.getProject().getName());
		frame.addFrame("name", module.getName());
		frame.addFrame("version", "1.0");
		Frame parentFrame = createParentFrame(module);
		if (parentFrame != null) frame.addFrame("parentModule", parentFrame);
		else {
			Frame magritte = createMagritteFrame(module);
			frame.addFrame("magritte", magritte);
		}
		return frame;
	}

	private Frame createMagritteFrame(Module module) {
		Frame frame = new Frame().addTypes("magritte","local");
		frame.addFrame("filePath", "");
		return frame;
	}

	private Frame createParentFrame(Module module) {
		Module parentModule = searchParent(module.getProject(), dsl);
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		if (parentModule == null || !manager.isMavenizedModule(parentModule)) return null;
		Frame frame = new Frame().addTypes("parent");
		MavenProject project = manager.findProject(parentModule);
		if (project == null) return null;
		frame.addFrame("groupId", project.getMavenId().getGroupId());
		frame.addFrame("artifactId", project.getMavenId().getArtifactId());
		frame.addFrame("version", project.getMavenId().getVersion());
		return frame;
	}


	private Module searchParent(Project project, String parentName) {
		for (Module candidate : getParentModulesCandidates(project)) {
			TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(candidate).getConfiguration();
			if (configuration.getGeneratedDslName().equals(parentName))
				return candidate;
		}
		return null;
	}


	private Frame createProjectFrame(Module module) {
		Project project = module.getProject();
		Frame frame = new Frame().addTypes("pom");
		frame.addFrame("name", project.getName());
		frame.addFrame("version", "1.0");
		return frame;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
			if (taraFacet == null) continue;
			if (!taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}
}
