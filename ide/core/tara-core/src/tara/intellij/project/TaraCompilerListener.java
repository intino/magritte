package tara.intellij.project;

import com.intellij.compiler.CompilerConfigurationImpl;
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
import com.intellij.openapi.fileEditor.FileEditorManager;
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
import tara.intellij.lang.LanguageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaraCompilerListener extends AbstractProjectComponent {
	private static final Logger LOG = Logger.getInstance(TaraCompilerListener.class.getName());
	private static final String TARA_PATTERN = "!?*.tara";
	private static final String TABLE_PATTERN = "!?*.table";

	private MessageBusConnection messageBusConnection;

	public TaraCompilerListener(Project project) {
		super(project);
	}

	@Override
	public void initComponent() {
		super.initComponent();
		messageBusConnection = myProject.getMessageBus().connect();
		messageBusConnection.subscribe(CustomBuilderMessageHandler.TOPIC, new FileInvalidationListener());
		fillResourcePatterns(new CompilerConfigurationImpl(myProject));
	}

	private void fillResourcePatterns(CompilerConfigurationImpl configuration) {
		final List<String> patterns = Arrays.asList(configuration.getResourceFilePatterns());
		if (!patterns.contains(TARA_PATTERN)) configuration.addResourceFilePattern(TARA_PATTERN);
		if (!patterns.contains(TABLE_PATTERN)) configuration.addResourceFilePattern(TABLE_PATTERN);
		configuration.convertPatterns();
	}

	@Override
	public void disposeComponent() {
		messageBusConnection.disconnect();
		super.disposeComponent();
	}

	private class FileInvalidationListener implements CustomBuilderMessageHandler {
		@Override
		public void messageReceived(String builderId, String messageType, String messageText) {
			if (TaraBuildConstants.TARAC.equals(builderId) && TaraBuildConstants.REFRESH_BUILDER_MESSAGE.equals(messageType)) {
				final String[] parameters = messageText.split(TaraBuildConstants.REFRESH_BUILDER_MESSAGE_SEPARATOR);
				refreshLanguages(parameters);
				refreshOut(parameters[0], new File(parameters[parameters.length - 1]));
				refreshDirectory(new File(new File(parameters[parameters.length - 1]).getParentFile(), "test-res"));
				refreshDirectory(new File(new File(parameters[parameters.length - 1]).getParentFile(), "res"));
				refreshDirectory(new File(new File(parameters[parameters.length - 1]).getParentFile(), "src"));
				refreshDirectory(new File(new File(parameters[parameters.length - 1]).getParentFile(), "test"));
			}
		}

		private void refreshLanguages(String[] language) {
			for (int i = 0; i < language.length - 1; i++) {
				LanguageManager.reloadLanguageForProjects(myProject, language[i]);
				LanguageManager.applyRefactors(language[i], myProject);
			}
		}

		private void refreshOut(String outDsl, File file) {
			VirtualFile outDir = VfsUtil.findFileByIoFile(file, true);
			if (outDir == null || !outDir.isValid()) return;
			outDir.refresh(true, true/*,() -> reformatGeneratedCode(VfsUtil.findFileByIoFile(new File(file, outDSLFromInput.toLowerCase() + File.separator + "natives"), true))*/);
		}

		private void refreshDirectory(File res) {
			VirtualFile resDir = VfsUtil.findFileByIoFile(res, true);
			if (resDir == null || !resDir.isValid()) return;
			resDir.refresh(true, true);
		}

		private void reformatGeneratedCode(VirtualFile outDir) {
			if (outDir == null || !outDir.isValid()) return;
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
			reformatAllFiles(project, psiOutDirectory[0]);
			reloadProject(project);
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

		private void reloadProject(Project project) {
			SaveAndSyncHandlerImpl.getInstance().refreshOpenFiles();
			VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
			ProjectManagerEx.getInstanceEx().unblockReloadingProjectOnExternalChanges();
			refreshFiles(project);
		}

		private void refreshFiles(Project project) {
			for (VirtualFile file : FileEditorManager.getInstance(project).getOpenFiles()) file.refresh(true, false);
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
