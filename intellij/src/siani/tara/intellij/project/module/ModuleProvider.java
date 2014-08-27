package siani.tara.intellij.project.module;

import com.intellij.openapi.roots.ProjectRootManager;
import siani.tara.intellij.lang.psi.TaraBoxFile;

public class ModuleProvider {

	public static com.intellij.openapi.module.Module getModuleOfDocument(TaraBoxFile file) {
		return ProjectRootManager.getInstance(file.getProject()).getFileIndex().getModuleForFile(file.getVirtualFile());
	}

}
