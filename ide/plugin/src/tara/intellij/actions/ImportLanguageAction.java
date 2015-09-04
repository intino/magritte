package tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;

public class ImportLanguageAction extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ImportLanguageAction.class.getName());
	public static final String LANGUAGE_EXTENSION = ".language";

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		try {
			importLanguage(e.getProject());
		} catch (IOException ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public File importLanguage(Project project) throws IOException {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), project, project.getBaseDir());
		return importLanguage(project, file);
	}

	public File importLanguage(Project project, VirtualFile file) throws IOException {
		ZipUtil.unzip(null, new File(project.getBaseDir().getPath()), new File(file.getPath()), null, null, false);
		final File languagesPath = TaraLanguage.getLanguagesDirectory(project);
		reload(file.getName(), languagesPath.getPath());
		return new File(languagesPath.getPath(), getPresentableName(file.getName()));
	}

	private void reload(String fileName, String languagesPath) {
		File reload = new File(languagesPath, getPresentableName(fileName) + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@NotNull
	private String getPresentableName(String fileName) {
		return fileName != null ? fileName.substring(0, fileName.lastIndexOf('.')) : "";
	}
}
