package siani.tara.intellij.project.runner;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public class RunnerUtil {

	private RunnerUtil() {
	}

	@Nullable
	public static PsiClass getRunningClass(@Nullable PsiElement element) {
		if (element == null) return null;

		final PsiFile file = element.getContainingFile();
		if (!(file instanceof TaraFileImpl)) return null;

		return null;
	}
}
