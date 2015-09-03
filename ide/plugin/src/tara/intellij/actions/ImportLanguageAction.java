package tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.actions.dialog.ImportLanguageDialog;
import tara.intellij.lang.LanguageFactory;
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

	public LanguageFactory.ImportedLanguage importLanguage(Project project) throws IOException {
		ImportLanguageDialog dialog = new ImportLanguageDialog(project, false);
		dialog.show();
		if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
			importLanguage(project, new File(dialog.getLanguagePath()));
			Messages.showInfoMessage(project, MessageProvider.message("successful.import.message"), MessageProvider.message("successful.import.title"));
			return new LanguageFactory.ImportedLanguage(new File(dialog.getLanguagePath()), dialog.getSourcePath().equals("") ? null : new File(dialog.getSourcePath()));
		}
		return null;
	}

	private File importLanguage(Project project, File file) throws IOException {
		final File languagesPath = TaraLanguage.getLanguagesDirectory(project.getBaseDir().getPath());
		ZipUtil.unzip(null, new File(languagesPath.getPath()), new File(file.getPath()), null, null, false);
		reload(file.getName(), languagesPath.getPath());
		return new File(languagesPath.getPath(), getPresentableName(file.getName()));
	}

	private void reload(String fileName, String languagesPath) {
		File reload = new File(languagesPath + getPresentableName(fileName) + ".reload");
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
