package monet.tara.intellij.actions;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vcs.changes.BackgroundFromStartOption;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.ExceptionUtil;

import java.util.Set;

public class GenerateAction extends AnAction implements DumbAware {
	public static final NotificationGroup LOG_GROUP = NotificationGroup.logOnlyGroup("Parser Generator Log");

	private static final Logger LOG = Logger.getInstance("org.intellij.grammar.actions.GenerateAction");

	@Override
	public void update(AnActionEvent e) {
		Project project = getEventProject(e);
		VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		boolean grammarFound = false;
		if (project != null && files != null) {
			PsiManager manager = PsiManager.getInstance(project);
			for (VirtualFile virtualFile : files) {
				PsiFile psiFile = manager.findFile(virtualFile);
				grammarFound = psiFile instanceof BnfFile;
				if (grammarFound) break;
			}
		}
		e.getPresentation().setEnabled(grammarFound);
		e.getPresentation().setVisible(grammarFound);
	}

	@Override
	public void actionPerformed(final AnActionEvent e) {
		final Project project = getEventProject(e);
		final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		if (project == null || files == null) return;
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();

		final Set<File> pathsToRefresh = new HashSet<File>();
		ProgressManager.getInstance().run(new Task.Backgroundable(project, "Parser Generation", true, new BackgroundFromStartOption()) {
			@Override
			public void onSuccess() {
				refreshFiles(pathsToRefresh);
			}

			@Override
			public void onCancel() {
				refreshFiles(pathsToRefresh);
			}

			@Override
			public void run(@NotNull ProgressIndicator indicator) {
				indicator.setIndeterminate(true);
				ApplicationManager.getApplication().runReadAction(new Runnable() {
					@Override
					public void run() {
						PsiManager psiManager = PsiManager.getInstance(project);
						for (VirtualFile file : files) {
							PsiFile bnfFile = psiManager.findFile(file);
							if (!(bnfFile instanceof BnfFile)) continue;
							VirtualFile virtualFile = bnfFile.getVirtualFile();
							String sourcePath = virtualFile == null ? "" : VfsUtil.virtualToIoFile(virtualFile).getParentFile().getAbsolutePath();

							VirtualFile content = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(file);
							VirtualFile parentDir = content == null ? file.getParent() : content;
							String outputPath = new File(VfsUtil.virtualToIoFile(parentDir), "gen").getAbsolutePath();
							pathsToRefresh.add(new File(outputPath));
							try {
								new ParserGenerator((BnfFile) bnfFile, sourcePath, outputPath).generate();
								Notifications.Bus.notify(new Notification(BnfConstants.GENERATION_GROUP,
									file.getName() + " parser generated", "to " + outputPath,
									NotificationType.INFORMATION), project);
							} catch (Exception ex) {
								Notifications.Bus.notify(new Notification(BnfConstants.GENERATION_GROUP,
									file.getName() + " parser generation failed",
									ExceptionUtil.getUserStackTrace(ex, ParserGenerator.LOG),
									NotificationType.ERROR), project);
								LOG.warn(ex);
							}
						}
					}
				});
			}
		});
	}

	private static void refreshFiles(Set<File> pathsToRefresh) {
		LocalFileSystem.getInstance().refreshIoFiles(pathsToRefresh, true, true, null);
	}
}
