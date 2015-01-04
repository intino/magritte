package siani.tara.intellij.project.module;

import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;

public class ModuleProvider {

	public static com.intellij.openapi.module.Module getModuleOfFile(PsiFile file) {
		if(file == null) return null;
		return ProjectRootManager.getInstance(file.getProject()).getFileIndex().getModuleForFile(file.getVirtualFile());
	}

}
