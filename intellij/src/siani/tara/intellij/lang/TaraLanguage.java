package siani.tara.intellij.lang;

import com.intellij.openapi.application.PathManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.Language;
import siani.tara.dsls.Proteo;
import siani.tara.intellij.lang.semantic.LanguageLoader;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

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
		TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(file));
		if (facet == null) return null;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		return getLanguage(configuration.getDsl());
	}

	@Nullable
	public static Language getLanguage(String parent) {
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