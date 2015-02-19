package siani.tara.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.intellij.project.sdk.TaraJdk;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static java.io.File.separator;

public class TaraLanguage extends Language {

	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String MODELS_PATH = PathManager.getPluginsPath() + separator + "tara_models" + separator;
	public static final Map<String, Model> models = new HashMap<>();
	private static final Set<String> modelPaths = new LinkedHashSet<>();

	static {
		modelPaths.add(MODELS_PATH);
	}

	private static final String PROTEO = "Proteo";
	public static final String DSL = "dsl";

	private TaraLanguage() {
		super("Tara");
	}

	public static Model getMetaModel(@NotNull PsiFile file) {
		ModuleConfiguration configuration = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(file));
		if (configuration == null) return null;
		return getMetaModel(configuration.getMetamodelName(), file.getProject());
	}

	public static Model getMetaModel(String parent, Project project) {
		addSdkToModelRoots(project);
		if (parent.equals(PROTEO) || parent.isEmpty()) return null;
		return getModel(parent);
	}

	private static Model getModel(String parent) {
		if (parent == null) return null;
		Model model;
		if ((model = models.get(parent)) != null && !haveToReload(parent))
			return model;
		for (String modelPath : modelPaths) {
			model = ModelLoader.load(modelPath, parent);
			if (model == null) continue;
			models.put(parent, model);
			return model;
		}
		return null;
	}

	private static void addSdkToModelRoots(Project project) {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance()))
			addModelRoot(projectSdk.getHomePath() + File.separator + DSL + File.separator);
	}

	public static void addModelRoot(String path) {
		modelPaths.add(path);
	}

	private static boolean haveToReload(String parent) {
		for (String modelPath : modelPaths) {
			File reload = new File(modelPath, parent + ".reload");
			if (reload.exists()) {
				reload.delete();
				return true;
			}
		}
		return false;
	}
}