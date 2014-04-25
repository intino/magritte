package monet.tara.intellij.codegeneration;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaraFileDocumentManagerListener implements FileDocumentManagerListener {

	public static final String SRC = "src";
	public static final String SRC_PATH = File.separator + SRC + File.separator;
	public static final String GEN = "gen";
	private static final String GEN_PATH = File.separator + GEN + File.separator;
	private Project project;

	@Override
	public void beforeAllDocumentsSaving() {

	}

	@Override
	public void beforeDocumentSaving(@NotNull Document document) {
		project = ProjectManager.getInstance().getOpenProjects()[0];
		if (!saveAndBLock()) return;
		Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			ModuleRootManager mrm = ModuleRootManager.getInstance(module);
			List<VirtualFile> virtualFiles = mrm.getSourceRoots(JavaModuleSourceRootTypes.SOURCES);
			isGenCreated(virtualFiles);
			for (VirtualFile file : virtualFiles)
				if (file.isDirectory() && SRC.equals(file.getName()))
					processExtensibleConcepts((VirtualDirectoryImpl) file);
		}
		refresh();
	}

	private VirtualFile isGenCreated(List<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && GEN.equals(file.getName()))
				return file;
		throw new RuntimeException("gen directory not found");

	}

	private void processVirtualFile(VirtualFile file) {
		PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
		if (psiFile instanceof TaraFile) {
			Concept concept = getExtensibleConcept((TaraFile) psiFile);
			if (concept != null)
				createExtensibleClasses(file, concept);
		}
	}

	private void createExtensibleClasses(VirtualFile virtualFile, Concept concept) {
		try {
			VirtualFile parent = virtualFile.getParent();
			File classSrcFile = new File(parent.getPath() + File.separator + concept.getName() + ".java");
			File extensibleGenFile = new File(parent.getPath().replaceFirst(SRC_PATH, GEN_PATH), concept.getName() + "Extensible.java");
			extensibleGenFile.getParentFile().mkdirs();
			if (!classSrcFile.exists()) {
				FileUtil.createIfDoesntExist(classSrcFile);
				FileUtil.createIfDoesntExist(extensibleGenFile);
				writeClass(classSrcFile, "package " + concept.getFile().getPackage().getHeaderReference().getText() + ";\n\npublic abstract class " +
					concept.getName() + " extends " + concept.getName() + "Extensible" + " {\n\n}");
				writeClass(extensibleGenFile, "package " + concept.getFile().getPackage().getHeaderReference().getText() + ";\n" +
					"\npublic abstract class " + concept.getName() + "Extensible" + " {\n\n}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeClass(File classSrcFile, String text) throws IOException {
		FileWriter writer = new FileWriter(classSrcFile);
		writer.write(text);
		writer.close();
	}


	private void processExtensibleConcepts(VirtualDirectoryImpl directory) {
		VfsUtilCore.visitChildrenRecursively(directory, new VirtualFileVisitor() {
			@Override
			public boolean visitFile(@NotNull VirtualFile file) {
				if (!(file instanceof VirtualDirectoryImpl))
					processVirtualFile(file);
				return true;
			}
		});
	}

	private Concept getExtensibleConcept(TaraFile file) {
		return file.getConcept().isExtensible() ? file.getConcept() : null;
	}


	private boolean saveAndBLock() {
		try {
			ProjectManagerEx.getInstanceEx().blockReloadingProjectOnExternalChanges();
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}


	private void refresh() {
		SaveAndSyncHandlerImpl.refreshOpenFiles();
		VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
	}


	@Override
	public void beforeFileContentReload(VirtualFile file, @NotNull Document document) {
	}

	@Override
	public void fileWithNoDocumentChanged(@NotNull VirtualFile file) {
	}

	@Override
	public void fileContentReloaded(VirtualFile file, @NotNull Document document) {
	}

	@Override
	public void fileContentLoaded(@NotNull VirtualFile file, @NotNull Document document) {
	}

	@Override
	public void unsavedDocumentsDropped() {

	}
}
