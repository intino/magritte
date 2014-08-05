package siani.tara.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class TaraLanguage extends Language {

	public static final Map<String, Model> models = new HashMap<>();
	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String MODELS_PATH = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator;


	private TaraLanguage() {
		super("Tara");
	}

	public static Model getMetaModel(String parent) {
		if (parent == null) return null;
		Model model;
		String[] splitName = parent.split("\\.");
		String basePath = MODELS_PATH + splitName[0] + separator;
		if ((model = models.get(parent)) != null && !haveToReload(basePath, parent))
			return model;
		model = ModelLoader.load(basePath, parent);
		models.put(parent, model);
		return model;
	}

	public static Model getMetaModel(PsiFile file) {
		return getMetaModel(((TaraFile) file).getParentModel());
	}


	private static boolean haveToReload(String path, String parent) {
		File reload = new File(path, parent + ".model_reload");
		if (reload.exists()) {
			reload.delete();
			return true;
		}
		return false;
	}
}