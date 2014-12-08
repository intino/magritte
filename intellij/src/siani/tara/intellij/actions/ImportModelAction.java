package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import siani.tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ImportModelAction extends AnAction implements DumbAware {
	private static final String JSON_EXTENSION = ".json";

	@Override
	public void actionPerformed(AnActionEvent e) {
		importModel(e.getProject());
	}

	public void importModel(Project project) {
		try {
			VirtualFile file = FileChooser.chooseFile(new FileChooserDescriptor(true, false, true, true, true, false),
				project, project.getBaseDir());
			if (file == null) return;
			if (file.isDirectory()) return;
			String path = file.getPath();
			ZipFile zipFile = new ZipFile(path);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().toLowerCase().endsWith(JSON_EXTENSION))
					extractModel(entry, zipFile.getInputStream(entry));
			}
		} catch (IOException ignored) {
		}
	}

	private void extractModel(ZipEntry entry, InputStream zis) throws IOException {
		String fileName = entry.getName().substring(entry.getName().lastIndexOf(File.separator) + 1);
		File newFile = new File(TaraLanguage.MODELS_PATH + fileName.split("\\.")[0], fileName);
		newFile.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(newFile, false);
		int len;
		byte[] buffer = new byte[1024];
		while ((len = zis.read(buffer)) > 0) fos.write(buffer, 0, len);
		fos.close();
		File reload = new File(TaraLanguage.MODELS_PATH + fileName.split("\\.")[0], fileName + ".reload");
		reload.createNewFile();
	}
}
