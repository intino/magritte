package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

public class LanguageFileChooserDescriptor extends FileChooserDescriptor {
	public LanguageFileChooserDescriptor() {
		super(true, false, false, false, false, false);
	}

	@Override
	public void validateSelectedFiles(VirtualFile[] files) throws Exception {
		if (isLanguageFile(files[0]))
			throw new Exception("File have to be of 'language' extension");
	}

	@Override
	public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
		return super.isFileVisible(file, showHiddenFiles) && (isLanguageFile(file) || file.isDirectory());
	}

	private boolean isLanguageFile(VirtualFile file) {
		return file.getName().endsWith(".jar");
	}


}
