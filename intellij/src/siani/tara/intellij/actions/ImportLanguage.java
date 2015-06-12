package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import siani.tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ImportLanguage extends AnAction implements DumbAware {

	private static final Logger LOG = Logger.getInstance(ImportLanguage.class.getName());

	public static final String LANGUAGE_EXTENSION = ".language";

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		importLanguage(e.getProject());
	}

	public void importLanguage(Project project) {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), project, project.getBaseDir());
		if (file == null) return;
		if (file.isDirectory()) return;
		importLanguage(file);
	}

	public void importLanguage() {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, VfsUtil.findFileByIoFile(new File(System.getenv("HOME")), true));
		if (file == null) return;
		if (file.isDirectory()) return;
		importLanguage(file);
	}

	private void importLanguage(VirtualFile file) {
		try {
			String path = file.getPath();
			ZipFile zipFile = new ZipFile(path);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().toLowerCase().endsWith(LANGUAGE_EXTENSION))
					extractLanguage(entry, zipFile.getInputStream(entry));
			}
			addLanguageDependency(entries);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void addLanguageDependency(Enumeration<? extends ZipEntry> entries) {

	}

	private void extractLanguage(ZipEntry entry, InputStream zis) throws IOException {
		String fileName = entry.getName().substring(entry.getName().lastIndexOf(File.separator) + 1);
		File newFile = new File(TaraLanguage.LANGUAGES_PATH + fileName.split("\\.")[0], fileName);
		newFile.getParentFile().mkdirs();
		extract(zis, newFile);
		reload(fileName);
	}

	private void extract(InputStream zis, File newFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(newFile, false);
		int len;
		byte[] buffer = new byte[1024];
		while ((len = zis.read(buffer)) > 0) fos.write(buffer, 0, len);
		fos.close();
	}

	private void reload(String fileName) throws IOException {
		File reload = new File(TaraLanguage.LANGUAGES_PATH + fileName.split("\\.")[0], fileName + ".reload");
		reload.createNewFile();
	}
}
