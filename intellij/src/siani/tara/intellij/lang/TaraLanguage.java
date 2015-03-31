package siani.tara.intellij.lang;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.Language;
import siani.tara.dsls.Proteo;
import siani.tara.intellij.lang.semantic.LanguageLoader;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.intellij.project.sdk.TaraJdk;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static java.io.File.separator;

public class TaraLanguage extends com.intellij.lang.Language {

	private static final String LANGUAGES_DIR = "tara_languages";
	private static final String PROTEO = "Proteo";
	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String MODELS_PATH = PathManager.getPluginsPath() + separator + LANGUAGES_DIR + separator;
	private static final Map<String, Language> languages = new HashMap<>();
	public static final String DSL = "dsl";

	private static final Set<String> languagesPaths = new LinkedHashSet<>();

	static {
		languagesPaths.add(MODELS_PATH);
		languages.put(PROTEO, new Proteo());
	}

	private TaraLanguage() {
		super("Tara");
	}

	@Nullable
	public static Language getLanguage(@NotNull PsiFile file) {
		ModuleConfiguration configuration = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(file));
		if (configuration == null) return null;
		return getLanguage(configuration.getMetamodelName(), file.getProject());
	}

	@Nullable
	public static Language getLanguage(String parent, Project project) {
		addSdkToModelRoots(project);
		if (parent.equals(PROTEO) || parent.isEmpty()) return languages.get(PROTEO);
		return loadLanguage(parent);
	}

	private static Language loadLanguage(String parent) {
		if (isLoaded(parent)) return languages.get(parent);
		for (String modelPath : languagesPaths) {
			Language language = LanguageLoader.load(parent, modelPath);
			if (language == null) continue;
			languages.put(parent, language);
			return language;
		}
		return null;
	}

	private static boolean isLoaded(String parent) {
		return languages.get(parent) != null && !haveToReload(parent);
	}

	private static void addSdkToModelRoots(Project project) {
		Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance()))
			addModelRoot(projectSdk.getHomePath() + File.separator + DSL + File.separator);
	}

	public static void addModelRoot(String path) {
		languagesPaths.add(path);
	}

	private static boolean haveToReload(String language) {
		for (String modelPath : languagesPaths) {
			File reload = new File(modelPath, language + ".reload");
			if (reload.exists()) {
				reload.delete();
				return true;
			}
		}
		return false;
	}
}