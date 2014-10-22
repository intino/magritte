package siani.tara.intellij.codegeneration;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraBoxFile;

public class TaraFileDocumentManagerListener implements FileDocumentManagerListener {

	private Project project;

	@Override
	public void beforeAllDocumentsSaving() {

	}

	@Override
	public void beforeDocumentSaving(@NotNull Document document) {
		project = ProjectManager.getInstance().getOpenProjects()[0];
		VirtualFile file = FileDocumentManager.getInstance().getFile(document);
		if (!saveAndBLock()) return;
		if (file != null) {
			PsiFile box = PsiManager.getInstance(project).findFile(file);
			if (box instanceof TaraBoxFile) {
				TaraBoxFile taraBoxFile = (TaraBoxFile) box;
				IntentionsGenerator generator = new IntentionsGenerator(project, taraBoxFile);
				generator.generate();
			}
		}
		refresh();
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
