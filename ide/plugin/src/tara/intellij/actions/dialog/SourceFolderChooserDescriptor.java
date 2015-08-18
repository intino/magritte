package tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import tara.intellij.lang.file.TaraFileType;

import java.io.IOException;

public class SourceFolderChooserDescriptor extends FileChooserDescriptor {
	public SourceFolderChooserDescriptor() {
		super(false, true, false, false, false, false);
	}

	@Override
	public void validateSelectedFiles(VirtualFile[] files) throws IOException {
		if (!isSourcePath(files[0]))
			throw new IOException("Folder does not contains tara files");
	}

	@Override
	public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
		return super.isFileVisible(file, showHiddenFiles) && (isSourcePath(file) || file.isDirectory());
	}

	private boolean isSourcePath(VirtualFile file) {
		return file.isDirectory() && containsTara(file);
	}

	private boolean containsTara(VirtualFile file) {
		for (VirtualFile virtualFile : file.getChildren())
			if (TaraFileType.INSTANCE.getDefaultExtension().equals(virtualFile.getExtension())) return true;
		return false;

	}


}
