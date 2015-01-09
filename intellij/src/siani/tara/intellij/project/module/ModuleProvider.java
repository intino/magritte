package siani.tara.intellij.project.module;

import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiElement;

public class ModuleProvider {

	public static com.intellij.openapi.module.Module getModuleOfFile(PsiElement element) {
		if (element == null) return null;
		return ProjectRootManager.getInstance(element.getProject()).getFileIndex().getModuleForFile(element.getContainingFile().getVirtualFile());
	}

}
