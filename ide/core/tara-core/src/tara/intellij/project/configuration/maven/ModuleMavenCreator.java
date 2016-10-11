package tara.intellij.project.configuration.maven;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.siani.itrules.model.Frame;
import tara.dsl.ProteoConstants;
import tara.intellij.lang.LanguageManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static java.io.File.separator;

public class ModuleMavenCreator {

	private static final String POM_XML = "pom.xml";
	private final Module module;

	public ModuleMavenCreator(Module module) {
		this.module = module;
	}

	public void mavenize() {
		VirtualFile pomFile = createPom(module);
		if (pomFile == null) return;
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		manager.addManagedFilesOrUnignore(Collections.singletonList(pomFile));
		manager.importProjects();
		manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles();
		FileEditorManager.getInstance(module.getProject()).openFile(pomFile, true);
	}

	private VirtualFile createPom(final Module module) {
		final PsiFile[] files = new PsiFile[1];
		ApplicationManager.getApplication().runWriteAction(() -> {
			MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
			if (project == null) {
				PsiDirectory root = getModuleRoot(module);
				files[0] = root.findFile(POM_XML);
				if (files[0] == null)
					createPom((files[0] = root.createFile(POM_XML)).getVirtualFile().getPath(), ModulePomTemplate.create().format(createModuleFrame(module)));
			}
		});
		return files[0] == null ? null : files[0].getVirtualFile();
	}


	private PsiDirectory getModuleRoot(Module module) {
		VirtualFile moduleFile = module.getModuleFile();
		final PsiManager manager = PsiManager.getInstance(module.getProject());
		PsiDirectory directory = moduleFile != null ?
			manager.findDirectory(moduleFile.getParent()) :
			manager.findDirectory(module.getProject().getBaseDir()).findSubdirectory(module.getName());
		if (directory == null) directory = create(manager, new File(module.getModuleFilePath()).getParentFile());
		return directory;
	}

	private PsiDirectory create(PsiManager manager, File moduleDir) {
		moduleDir.mkdirs();
		final VirtualFile file = VfsUtil.findFileByIoFile(moduleDir, true);
		if (file != null)
			return manager.findDirectory(file);
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

	private Frame createModuleFrame(Module module) {
		Frame frame = new Frame().addTypes("pom");
		frame.addFrame("project", module.getProject().getName());
		frame.addFrame("name", module.getName());
		frame.addFrame("version", "1.0");
		if (new File(module.getModuleFilePath()).getParent().equals(new File(module.getProject().getBasePath()).getAbsolutePath()))
			frame.addFrame("default", "");
		frame.addFrame("magritte", createMagritteFrame(module));
		return frame;
	}

	private Frame createMagritteFrame(Module module) {
		Frame frame = new Frame().addTypes("magritte", "local");
		String projectDirectory = module.getProject().getBasePath();
		final String moduleDirectory = new File(module.getModuleFilePath()).getParent();
		frame.addFrame("filePath", (moduleDirectory.equals(projectDirectory) ? "" : "../") + LanguageManager.TARA + separator + LanguageManager.FRAMEWORK + separator + ProteoConstants.PROTEO + separator + ProteoConstants.PROTEO + ".jar");
		return frame;
	}
}
