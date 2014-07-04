package siani.tara.intellij.project.module;

import com.intellij.openapi.roots.ProjectRootManager;
import siani.tara.intellij.lang.psi.TaraFile;

public class ModuleProvider {

	public static com.intellij.openapi.module.Module getModuleOfDocument(TaraFile file) {
		return ProjectRootManager.getInstance(file.getProject()).getFileIndex().getModuleForFile(file.getVirtualFile());
	}

}
