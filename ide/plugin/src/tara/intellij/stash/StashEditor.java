package tara.intellij.stash;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.file.StashFileType;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static tara.intellij.stash.StashToJson.createJson;

public class StashEditor implements TextEditor {
	private final Project project;
	private final VirtualFile stash;
	private StashEditorComponent myComponent;


	public StashEditor(Project project, VirtualFile stash) {
		this.project = project;
		this.stash = stash;
		try {
			final Path path = createJson(stash, new File(FileUtilRt.getTempDirectory(), "__temp" + stash.getName() + ".json"));
			final VirtualFile fileByURL = VfsUtil.findFileByIoFile(path.toFile(), true);
			VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
			ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
			if (fileByURL != null) myComponent = createEditorComponent(project, fileByURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@NotNull
	@Override
	public JComponent getComponent() {
		return myComponent;
	}

	@NotNull
	protected StashEditorComponent createEditorComponent(final Project project, final VirtualFile file) {
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
//		final ArrayList<RadComponent> selection = FormEditingUtil.getSelectedComponents(myEditor);
		final String[] ids = new String[0];
//		for (int i = ids.length - 1; i >= 0; i--) {
//			ids[i] = selection.get(i).getId();
//		}
		return new MyEditorState(modificationStamp, ids);
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
		return FileDocumentManager.getInstance().getDocument(stash) != null &&
			stash.getFileType() == StashFileType.INSTANCE;
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
