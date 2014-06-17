package siani.tara.intellij.codegeneration;

import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

public class TaraFileDocumentManagerListener implements FileDocumentManagerListener {

	@Override
	public void beforeAllDocumentsSaving() {
	}

	@Override
	public void beforeDocumentSaving(@NotNull Document document) {
		if (!saveAndBLock()) return;
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
