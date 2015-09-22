package tara.intellij.project;

import com.intellij.compiler.server.CustomBuilderMessageHandler;
import com.intellij.ide.DataManager;
import com.intellij.ide.SaveAndSyncHandlerImpl;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import tara.compiler.constants.TaraBuildConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraCompilerListener extends AbstractProjectComponent {
	private static final Logger LOG = Logger.getInstance(TaraCompilerListener.class.getName());

	private MessageBusConnection messageBusConnection;

	public TaraCompilerListener(Project project) {
		super(project);
	}

	@Override
	public void initComponent() {
		super.initComponent();
		messageBusConnection = myProject.getMessageBus().connect();
		messageBusConnection.subscribe(CustomBuilderMessageHandler.TOPIC, new FileInvalidationListener());
	}

	@Override
	public void disposeComponent() {
		messageBusConnection.disconnect();
		super.disposeComponent();
	}

	private class FileInvalidationListener implements CustomBuilderMessageHandler {
		@Override
		public void messageReceived(String builderId, String messageType, String messageText) {
			if (TaraBuildConstants.TARAC.equals(builderId) && TaraBuildConstants.FILE_INVALIDATION_BUILDER_MESSAGE.equals(messageType)) {
				refreshOut(new File(messageText));
				refreshRes(new File(new File(messageText).getParentFile(), "res"));
			}
		}

		public void refreshOut(File file) {
			VirtualFile outDir = VfsUtil.findFileByIoFile(file, true);
			if (outDir == null || !outDir.isValid()) return;
			outDir.refresh(true, true, () -> reformatGeneratedCode(outDir));
		}

		private void refreshRes(File res) {
			VirtualFile resDir = VfsUtil.findFileByIoFile(res, true);
			if (resDir == null || !resDir.isValid()) return;
			resDir.refresh(true, true);
		}

		private void reformatGeneratedCode(VirtualFile outDir) {
			if (!outDir.isValid()) return;
			FileDocumentManager.getInstance().saveAllDocuments();
			ProjectManagerEx.getInstanceEx().blockReloadingProjectOnExternalChanges();
			final DataContext result = DataManager.getInstance().getDataContextFromFocus().getResult();
			Project project = result != null ? (Project) result.getData(PlatformDataKeys.PROJECT.getName()) : null;
			if (project == null) return;
			final PsiDirectory[] psiOutDirectory = new PsiDirectory[1];
			ApplicationManager.getApplication().runReadAction(() -> {
				psiOutDirectory[0] = PsiManager.getInstance(project).findDirectory(outDir);
			});
			if (psiOutDirectory[0] == null || !psiOutDirectory[0].isDirectory()) return;
			project.save();
			reformatAllFiles(project, (PsiDirectory) psiOutDirectory[0].getFirstChild());
			reloadProjects();
		}

		private void reloadProjects() {
			SaveAndSyncHandlerImpl.getInstance().refreshOpenFiles();
			VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
			ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
		}


		private void reformatAllFiles(Project project, PsiDirectory directory) {
			List<PsiElement> psiFiles = new ArrayList<>();
			try {
				ApplicationManager.getApplication().runReadAction(() -> {
					if (directory.getChildren().length != 0)
						Collections.addAll(psiFiles, directory.getChildren());
				});
				for (PsiElement file : psiFiles) {
					if (file instanceof PsiFile) reformat(project, (PsiFile) file).executeSilently();
					else if (file instanceof PsiDirectory) reformatAllFiles(project, (PsiDirectory) file);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		@NotNull
		private WriteCommandAction.Simple<String> reformat(final Project project, final PsiFile file) {
			return new WriteCommandAction.Simple<String>(project, file) {
				@Override
				protected void run() throws Throwable {
					assert ensureFilesWritable(project, Collections.singletonList(file));
					if (file != null) CodeStyleManager.getInstance(project).reformat(file, true);
				}
			};
		}
	}
}

