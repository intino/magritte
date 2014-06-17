package siani.tafat.intellij.metamodel.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import siani.tafat.intellij.metamodel.TafatLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TafatFile extends PsiFileBase {
	public TafatFile(@NotNull com.intellij.psi.FileViewProvider viewProvider) {
		super(viewProvider, TafatLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return TafatFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "Tafat File";
	}

	@Override
	public Icon getIcon(int flags) {
		return super.getIcon(flags);
	}
}