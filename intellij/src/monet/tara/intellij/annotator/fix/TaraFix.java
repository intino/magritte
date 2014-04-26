package monet.tara.intellij.annotator.fix;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public abstract class TaraFix implements LocalQuickFix {

	public static final TaraFix[] EMPTY_ARRAY = new TaraFix[0];

	//to appear in "Apply Fix" statement when multiple Quick Fixes exist
	@NotNull
	public String getFamilyName() {
		return "";
	}

	public void applyFix(@NotNull Project project,
	                     @NotNull ProblemDescriptor descriptor) {
		final PsiElement problemElement = descriptor.getPsiElement();
		if (problemElement == null || !problemElement.isValid()) {
			return;
		}
		if (isQuickFixOnReadOnlyFile(problemElement)) {
			return;
		}
		try {
			doFix(project, descriptor);
		} catch (IncorrectOperationException e) {
			final Class<? extends TaraFix> aClass = getClass();
			final String className = aClass.getName();
			final Logger logger = Logger.getInstance(className);
			logger.error(e);
		}
	}

	protected abstract void doFix(Project project, ProblemDescriptor descriptor)
		throws IncorrectOperationException;

	protected static boolean isQuickFixOnReadOnlyFile(PsiElement problemElement) {
		final PsiFile containingPsiFile = problemElement.getContainingFile();
		if (containingPsiFile == null) {
			return false;
		}
		final VirtualFile virtualFile = containingPsiFile.getVirtualFile();
		final JavaPsiFacade facade = JavaPsiFacade.getInstance(problemElement.getProject());
		final Project project = facade.getProject();
		final ReadonlyStatusHandler handler = ReadonlyStatusHandler.getInstance(project);
		final ReadonlyStatusHandler.OperationStatus status = handler.ensureFilesWritable(virtualFile);
		return status.hasReadonlyFiles();
	}


}