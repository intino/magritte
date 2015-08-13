package tara.intellij.project;

import com.intellij.compiler.server.CustomBuilderMessageHandler;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.impl.file.PsiJavaDirectoryImpl;
import com.intellij.util.messages.MessageBusConnection;
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
				VirtualFile outDir = VfsUtil.findFileByIoFile(new File(messageText), true);
				if (outDir == null || !outDir.isValid()) return;
				outDir.refresh(true, true, () -> reformatGeneratedCode(outDir));
			}
		}

		private void reformatGeneratedCode(VirtualFile outDir) {
			if (!outDir.isValid()) return;
			final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
			for (Project project : openProjects) {
				final PsiDirectory[] psiOutDirectory = new PsiDirectory[1];
				ApplicationManager.getApplication().runReadAction(() -> {
					psiOutDirectory[0] = PsiManager.getInstance(project).findDirectory(outDir);
				});
				if (psiOutDirectory[0] == null || !psiOutDirectory[0].isDirectory()) continue;
				reformatAllFiles(project, psiOutDirectory[0]);
			}
		}
	}

	private void reformatAllFiles(Project project, PsiDirectory directory) {
		List<PsiFile> psiFiles = new ArrayList<>();
		try {
			ApplicationManager.getApplication().runReadAction(() -> {
				Collections.addAll(psiFiles, ((PsiJavaDirectoryImpl) directory.getFirstChild()).getFiles());
			});
			for (PsiFile file : psiFiles) {
				WriteCommandAction.Simple<String> command = new WriteCommandAction.Simple<String>(project, file) {
					@Override
					protected void run() throws Throwable {
						assert ensureFilesWritable(project, Collections.singletonList(file));
						if (file != null) CodeStyleManager.getInstance(project).reformat(file, false);
					}
				};
				command.execute();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}

