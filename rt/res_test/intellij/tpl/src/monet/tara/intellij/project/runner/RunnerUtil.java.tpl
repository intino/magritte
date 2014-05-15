package monet.::projectName::.intellij.project.runner;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.Nullable;

public class RunnerUtil {

	private RunnerUtil() {
	}

	\@Nullable
	public static PsiClass getRunningClass(\@Nullable PsiElement element) {
		if (element == null) return null;

		final PsiFile file = element.getContainingFile();
		if (!(file instanceof ::projectProperName::FileImpl)) return null;

		return null;
	}
}
