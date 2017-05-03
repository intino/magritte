package io.intino.tara.plugin.stash;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.pom.Navigatable;
import io.intino.tara.plugin.lang.file.StashFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class StashEditor implements TextEditor {
	private final VirtualFile stash;
	private StashEditorComponent myComponent;


	StashEditor(Project project, VirtualFile stash) {
		this.stash = stash;
		try {
			final Path path = StashToTara.createTara(stash, new File(FileUtilRt.getTempDirectory(), "__temp" + stash.getName() + ".tara"));
			final VirtualFile fileByURL = VfsUtil.findFileByIoFile(path.toFile(), true);
			refreshFiles();
			if (fileByURL != null) myComponent = createEditorComponent(project, fileByURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void refreshFiles() {
		final Application application = ApplicationManager.getApplication();
		if (application.isWriteAccessAllowed()) VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		else application.invokeAndWait(() -> VirtualFileManager.getInstance().refreshWithoutFileWatcher(false));
	}

	@NotNull
	@Override
	public JComponent getComponent() {
		return myComponent;
	}

	@NotNull
	private StashEditorComponent createEditorComponent(final Project project, final VirtualFile file) {
		return new StashEditorComponent(project, file, this);
	}

	@Nullable
	@Override
	public JComponent getPreferredFocusedComponent() {
		return myComponent;
	}

	@NotNull
	@Override
	public String getName() {
		return "Stash editor";
	}

	@NotNull
	@Override
	public FileEditorState getState(@NotNull FileEditorStateLevel level) {
		final Document document = FileDocumentManager.getInstance().getCachedDocument(stash);
		long modificationStamp = document != null ? document.getModificationStamp() : stash.getModificationStamp();
		return new StashEditorState(modificationStamp, new String[0]);
	}

	@NotNull
	@Override
	public Editor getEditor() {
		return myComponent.getEditor();
	}

	@Override
	public void setState(@NotNull FileEditorState state) {

	}

	@Override
	public boolean isModified() {
		return false;
	}

	@Override
	public boolean canNavigateTo(@NotNull Navigatable navigatable) {
		return false;
	}

	@Override
	public void navigateTo(@NotNull Navigatable navigatable) {

	}

	@Override
	public boolean isValid() {
		return FileDocumentManager.getInstance().getDocument(stash) != null && stash.getFileType() == StashFileType.INSTANCE;
	}

	@Override
	public void selectNotify() {

	}

	@Override
	public void deselectNotify() {

	}

	@Override
	public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

	}

	@Override
	public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

	}

	@Nullable
	@Override
	public BackgroundEditorHighlighter getBackgroundHighlighter() {
		return null;
	}

	@Nullable
	@Override
	public FileEditorLocation getCurrentLocation() {
		return null;
	}

	@Nullable
	@Override
	public StructureViewBuilder getStructureViewBuilder() {
		return null;
	}

	@Override
	public void dispose() {
		myComponent.dispose();
	}

	@Nullable
	@Override
	public <T> T getUserData(@NotNull Key<T> key) {
		return null;
	}

	@Override
	public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {

	}
}
