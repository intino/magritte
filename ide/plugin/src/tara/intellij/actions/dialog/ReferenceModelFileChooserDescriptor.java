package tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

public class ReferenceModelFileChooserDescriptor extends FileChooserDescriptor {
	public ReferenceModelFileChooserDescriptor() {
		super(true, false, false, true, false, false);
	}

	@Override
	public void validateSelectedFiles(VirtualFile[] files) throws Exception {
		if (!isModuleFile(files[0]))
			throw new Exception("File have to be of 'iml' extension");
	}

	@Override
	public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
		return super.isFileVisible(file, showHiddenFiles) && (isModuleFile(file) || file.isDirectory());
	}

	private boolean isModuleFile(VirtualFile file) {
		return file.getName().endsWith(".iml");
	}


}
