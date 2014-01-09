package monet.tara.metamodel.codeinspection;

import com.intellij.codeInspection.*;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oroncal on 09/01/14.
 */
public class DuplicateConceptInspection extends GlobalSimpleInspectionTool {
	@Override
	public void checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, @NotNull ProblemsHolder problemsHolder, @NotNull GlobalInspectionContext globalContext, @NotNull ProblemDescriptionsProcessor problemDescriptionsProcessor) {

	}
}
