package siani.tara.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.project.sdk.TaraJdk;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.io.File.separator;

public class TaraLanguage extends Language {

	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String MODELS_PATH = PathManager.getPluginsPath() + separator + "tara_models" + separator;
	public static final Map<String, Model> models = new HashMap<>();
	private static final Set<String> modelPaths = new HashSet<>();

	static {
		modelPaths.add(MODELS_PATH);
	}

	private TaraLanguage() {
		super("Tara");
	}

	public static Model getMetaModel(String parent, Project project) {
		addModelFromSdk(project);
		return getModel(parent);
	}

	private static Model getModel(String parent) {
		if (parent == null) return null;
		Model model;
		String[] splitName = parent.split("\\.");
		for (String modelPath : modelPaths) {
			String basePath = modelPath + splitName[0] + separator;
			if ((model = models.get(parent)) != null && !haveToReload(basePath, parent))
				return model;
			model = ModelLoader.load(basePath, parent);
			if (model == null) continue;
			models.put(parent, model);
			return model;
		}
		return null;
	}

	public static Model getMetaModel(PsiFile file) {
		addModelFromSdk(file.getProject());
		return getModel(((TaraBoxFile) file).getParentModel());
	}

	private static void addModelFromSdk(Project project) {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance()))
			addModelRoot(projectSdk.getHomePath() + File.separator + "model" + File.separator);
	}


	public static void addModelRoot(String path) {
		modelPaths.add(path);
	}

	private static boolean haveToReload(String path, String parent) {
		File reload = new File(path, parent + ".reload");
		if (reload.exists()) {
			reload.delete();
			return true;
		}
		return false;
	}
}