package tara.intellij.project.module;

import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;

public class ModuleProvider {

	private ModuleProvider() {
	}

	public static com.intellij.openapi.module.Module moduleOf(PsiElement element) {
		if (element == null || (!(element instanceof PsiDirectory) && element.getContainingFile().getVirtualFile() == null && element.getContainingFile().getOriginalFile().getVirtualFile() == null))
			return null;
		return ModuleUtil.findModuleForPsiElement(element);
	}
}
