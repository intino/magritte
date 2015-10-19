package tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import tara.intellij.lang.TaraLanguage;

import java.io.IOException;

public class LanguageFileChooserDescriptor extends FileChooserDescriptor {
	public LanguageFileChooserDescriptor() {
		super(true, false, false, true, false, false);
	}

	@Override
	public void validateSelectedFiles(VirtualFile[] files) throws IOException {
		if (!isLanguageFile(files[0]))
			throw new IOException("File have to be of 'language' extension");
	}

	@Override
	public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
		return super.isFileVisible(file, showHiddenFiles) && (isLanguageFile(file) || file.isDirectory());
	}

	private boolean isLanguageFile(VirtualFile file) {
		return file.getName().endsWith(TaraLanguage.LANGUAGE_EXTENSION);
	}


}
