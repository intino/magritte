package monet.::projectName::.intellij.metamodel.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ::projectProperName::File extends PsiFileBase {
	public ::projectProperName::File(\@NotNull com.intellij.psi.FileViewProvider viewProvider) {
		super(viewProvider, ::projectProperName::Language.INSTANCE);
	}

	\@NotNull
	\@Override
	public FileType getFileType() {
		return ::projectProperName::FileType.INSTANCE;
	}

	\@Override
	public String toString() {
		return "::projectProperName:: File";
	}

	\@Override
	public Icon getIcon(int flags) {
		return super.getIcon(flags);
	}
}