package tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class SourceProjectChooserDescriptor extends FileChooserDescriptor {
	public SourceProjectChooserDescriptor() {
		super(true, false, false, false, false, false);
	}

	@Override
	public void validateSelectedFiles(VirtualFile[] files) throws IOException {
		if (!isSourcePath(files[0]))
			throw new IOException("File have to be of 'iml' extension");
	}

	@Override
	public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
		return super.isFileVisible(file, showHiddenFiles) && (isSourcePath(file) || file.isDirectory());
	}

	private boolean isSourcePath(VirtualFile file) {
		return file.getName().endsWith(".iml");
	}


}
