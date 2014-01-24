package monet.tara.transpiler.intellij.metamodel.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import monet.tara.transpiler.intellij.metamodel.TaraLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TaraFile extends PsiFileBase {
	public TaraFile(@NotNull com.intellij.psi.FileViewProvider viewProvider) {
		super(viewProvider, TaraLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return TaraFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "Tara File";
	}

	@Override
	public Icon getIcon(int flags) {
		return super.getIcon(flags);
	}
}