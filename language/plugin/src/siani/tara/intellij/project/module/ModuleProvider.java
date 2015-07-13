package siani.tara.intellij.project.module;

import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;

public class ModuleProvider {

	private ModuleProvider() {
	}

	public static com.intellij.openapi.module.Module getModuleOf(PsiElement element) {
		if (element == null || (!(element instanceof PsiDirectory) && element.getContainingFile().getVirtualFile() == null)) return null;
		return ModuleUtil.findModuleForPsiElement(element);
	}
}
