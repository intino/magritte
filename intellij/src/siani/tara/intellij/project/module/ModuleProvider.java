package siani.tara.intellij.project.module;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import siani.tara.intellij.lang.psi.TaraFile;

public class ModuleProvider {

	public static com.intellij.openapi.module.Module getModuleOfDocument(Project project, String fileText) {
		try {
			String module = fileText.substring("package ".length(), fileText.indexOf(":"));
			String[] split = module.split("\\.");
			module = "";
			for (String s1 : split) module += s1;
			return ModuleManager.getInstance(project).findModuleByName(module);
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("error getting module of document");
			return null;
		}
	}

	public static com.intellij.openapi.module.Module getModuleOfDocument(TaraFile file) {
		return getModuleOfDocument(file.getProject(), file.getText());
	}


}
