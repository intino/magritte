package tara.intellij.project.module;

import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class ModuleProvider {

	private ModuleProvider() {
	}

	public static com.intellij.openapi.module.Module moduleOf(PsiElement element) {
		if (element == null || (!(element instanceof PsiDirectory) && element.getContainingFile().getVirtualFile() == null && element.getContainingFile().getOriginalFile().getVirtualFile() == null))
			return null;
		return ModuleUtil.findModuleForPsiElement(element);
	}

	public static com.intellij.openapi.module.Module moduleOf(PsiFile element) {
		return ModuleUtil.findModuleForFile(element.getOriginalFile().getVirtualFile(), element.getProject());
	}
}
