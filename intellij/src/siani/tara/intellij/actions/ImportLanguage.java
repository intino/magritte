package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import siani.tara.intellij.actions.utils.FileSystemUtils;
import siani.tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;

public class ImportLanguage extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ImportLanguage.class.getName());

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		try {
			importLanguage(e.getProject());
		} catch (Exception ignored) {
		}
	}

	public File importLanguage(Project project) throws Exception {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), project, project.getBaseDir());
		return importLanguage(file, project);
	}

	private File importLanguage(VirtualFile file, Project project) throws Exception {
		final String languagesPath = TaraLanguage.LANGUAGES_PATH;
		final Boolean aBoolean = FileSystemUtils.copyFile(file.getPath(), languagesPath + file.getName());
		if (!aBoolean) throw new Exception("unable import");
		reload(file.getName(), languagesPath);
		return new File(languagesPath, file.getName());
	}

	private void reload(String fileName, String languagesPath) {
		File reload = new File(languagesPath + fileName.substring(0, fileName.lastIndexOf(".")) + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
