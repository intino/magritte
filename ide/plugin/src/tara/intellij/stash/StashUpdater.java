package tara.intellij.stash;

import com.google.gson.Gson;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import tara.io.Stash;
import tara.io.StashSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class StashUpdater implements ProjectComponent {


	private final Project project;
	private MessageBusConnection connection;

	protected StashUpdater(Project project, FileEditorManager fileEditorManager) {
		this.project = project;
	}

	private final FileEditorManagerListener myListener = new FileEditorManagerListener() {
		@Override
		public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
		}

		@Override
		public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile sourceFile) {
			File tempFile = new File(FileUtilRt.getTempDirectory(), "__temp" + sourceFile.getName() + ".json");
			final VirtualFile file = VfsUtil.findFileByIoFile(tempFile, true);
			if (tempFile.exists() && file != null) {
				final Document document = FileDocumentManager.getInstance().getDocument(file);
				if (document == null) return;
				final Stash stash = new Gson().fromJson(document.getText(), Stash.class);
				if (stash != null) {
					final byte[] serialize = StashSerializer.serialize(stash);
					try {
						Files.write(new File(sourceFile.getPath()).toPath(), serialize, StandardOpenOption.CREATE);
					} catch (IOException e) {
						e.printStackTrace();
					}
					source.closeFile(file);
					ApplicationManager.getApplication().runWriteAction(() -> {
						try {
							file.delete(project);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				}
			}
		}

		@Override
		public void selectionChanged(@NotNull FileEditorManagerEvent event) {
		}
	};

	@Override
	public void projectOpened() {
		StartupManager.getInstance(project).runWhenProjectIsInitialized(this::initListener);
	}

	private void initListener() {
		connection = project.getMessageBus().connect(project);
		connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, myListener);
	}

	@Override
	public void projectClosed() {
		disposeComponent();
	}

	@Override
	public void initComponent() {

	}

	@Override
	public void disposeComponent() {

	}

	@NotNull
	@Override
	public String getComponentName() {
		return "Stash Updater";
	}
}
