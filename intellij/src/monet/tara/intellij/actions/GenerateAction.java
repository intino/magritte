package monet.tara.intellij.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vcs.changes.BackgroundFromStartOption;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import monet.tara.intellij.metamodel.file.TaraFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateAction extends AnAction implements DumbAware {
	public static final NotificationGroup LOG_GROUP = NotificationGroup.logOnlyGroup("Plugin Generator Log");
	private static final Logger LOG = Logger.getInstance("GenerateAction");

	@Override
	public void update(AnActionEvent e) {
		Project project = getEventProject(e);
		VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		boolean metaModelFound = false;
		if (project != null && files != null) {
			PsiManager manager = PsiManager.getInstance(project);
			for (VirtualFile virtualFile : files) {
				PsiFile psiFile = manager.findFile(virtualFile);
				metaModelFound = psiFile instanceof TaraFile;
				if (metaModelFound) break;
			}
		}
		e.getPresentation().setEnabled(metaModelFound);
		e.getPresentation().setVisible(metaModelFound);
	}

	@Override
	public void actionPerformed(final AnActionEvent e) {
		final Set<File> pathsToRefresh = new HashSet<>();
		final Project project = getEventProject(e);
		final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		if (project == null || files == null) return;
		Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(files[0]);
		if (module == null) return;
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		final TaraFile[] taraFiles = filterFiles(ModuleRootManager.getInstance(module).getSourceRoots(), project);
		createGenPath(project,taraFiles[0]);
		ProgressManager.getInstance().run(new Task.Backgroundable(project, "Plugin Generation", true, new BackgroundFromStartOption()) {
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
						VirtualFile content = ProjectRootManager.getInstance(project).getFileIndex().
							getContentRootForFile(taraFiles[0].getVirtualFile());
						VirtualFile parentDir = content == null ? taraFiles[0].getVirtualFile().getParent() : content;
						String outputPath = new File(VfsUtil.virtualToIoFile(parentDir), "gen").getAbsolutePath();
						pathsToRefresh.add(new File(outputPath));
						try {
//							new PluginGenerator(taraFiles, outputPath).generate();
							Notifications.Bus.notify(new Notification("Plugin Generator",
								taraFiles[0].getProject().getName() + " plugin generated", "to " + outputPath,
								NotificationType.INFORMATION), project);
						} catch (Exception ex) {
							Notifications.Bus.notify(new Notification("Plugin Generator",
								taraFiles[0].getProject().getName() + " plugin generation failed",null,null),project);
//								ExceptionUtil.getUserStackTrace(ex, PluginGenerator.LOG),
//								NotificationType.ERROR), project);
							LOG.warn(ex);
						}
					}
				});
			}
		});
	}

	private TaraFile[] filterFiles(VirtualFile[] sourceRoots, Project project) {
		PsiManager psiManager = PsiManager.getInstance(project);
		List<TaraFile> filteredFiles = new ArrayList<>();
		for (VirtualFile sourceRoot : sourceRoots)
			for (VirtualFile file : sourceRoot.getChildren()) {
				PsiFile taraFile = psiManager.findFile(file);
				if ((taraFile instanceof TaraFile)) filteredFiles.add((TaraFile) taraFile);
			}
		return filteredFiles.toArray(new TaraFile[filteredFiles.size()]);
	}

	private static void refreshFiles(Set<File> pathsToRefresh) {
		LocalFileSystem.getInstance().refreshIoFiles(pathsToRefresh, true, true, null);
	}

	private static void createGenPath(Project project, TaraFile taraFile){
		final Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(taraFile.getVirtualFile());
		assert module != null;
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				try {
					ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
					VirtualFile gen = module.getModuleFile().getParent().createChildDirectory(this, "gen");
					model.addContentEntry(model.getSourceRoots()[0].getParent()).addSourceFolder(gen, false);
					model.commit();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		});
	}
}
