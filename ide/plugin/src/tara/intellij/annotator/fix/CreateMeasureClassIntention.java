package tara.intellij.annotator.fix;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codegeneration.MetricClassCreator;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;

public class CreateMeasureClassIntention extends ClassCreationIntention{

	private final String className;
	private final String destinyPath;


	public CreateMeasureClassIntention(String className, String destinyPath) {
		this.className = className;
		this.destinyPath = destinyPath;
	}

	@NotNull
	@Override
	public String getText() {
		return "Create " + className + " class";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create measure class";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file instanceof TaraModel;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(file));
		PsiDirectoryImpl srcPsiDirectory = new PsiDirectoryImpl((PsiManagerImpl) file.getManager(), srcDirectory);
		PsiDirectory destiny = findDestiny(file, srcPsiDirectory, destinyPath);
		MetricClassCreator creator = new MetricClassCreator(destiny, className);
		creator.createClass();
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
