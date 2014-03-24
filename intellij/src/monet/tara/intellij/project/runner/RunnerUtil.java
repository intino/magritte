package monet.tara.intellij.project.runner;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public class RunnerUtil {

	private RunnerUtil() {
	}

	@Nullable
	public static PsiClass getRunningClass(@Nullable PsiElement element) {
		if (element == null) return null;

		final PsiFile file = element.getContainingFile();
		if (!(file instanceof TaraFileImpl)) return null;

		for (PsiClass clazz = PsiTreeUtil.getParentOfType(element, PsiClass.class);
		     clazz != null;
		     clazz = PsiTreeUtil.getParentOfType(clazz, PsiClass.class)) {
		}
		return null;
	}
}
