package siani.tara.intellij.codegeneration;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
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
		if (!saveAndBLock()) return;
		project = ProjectManager.getInstance().getOpenProjects()[0];
		PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
		VirtualFile virtualFile = psiFile.getVirtualFile();
		Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(virtualFile);
		if (module == null) return;
		ModuleRootManager mrm = ModuleRootManager.getInstance(module);
		List<VirtualFile> virtualFiles = mrm.getSourceRoots(JavaModuleSourceRootTypes.SOURCES);
		if (isGenCreated(virtualFiles) != null)
			processVirtualFile(psiFile);
		refresh();
	}

	private VirtualFile isGenCreated(List<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && GEN.equals(file.getName()))
				return file;
		throw new RuntimeException("gen directory not found");

	}

	private void processVirtualFile(PsiFile psiFile) {
		if (psiFile instanceof TaraFile) {
			Concept concept = getIntentionConcept((TaraFile) psiFile);
			if (concept != null)
				createWrapperClasses(psiFile, concept);
		}
	}

	private void createWrapperClasses(PsiFile psiFile, Concept concept) {
		try {
			VirtualFile parent = psiFile.getVirtualFile().getParent();
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


	private Concept getIntentionConcept(TaraFile file) {
		return file.getConcept() == null ? null : file.getConcept().isIntention() ? file.getConcept() : null;
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
