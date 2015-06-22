package siani.tara.intellij.lang;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.Language;
import siani.tara.dsl.Proteo;
import siani.tara.intellij.lang.semantic.LanguageLoader;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TaraLanguage extends com.intellij.lang.Language {

	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String DSL = "dsl";
	public static final String LANGUAGES_PACKAGE = "siani.tara.dsl";
	public static final String PROTEO = "Proteo";
	private static final Map<String, Language> languages = new HashMap<>();

	static {
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
		return getLanguage(configuration.getDsl(), file.getProject());
	}

	@Nullable
	public static Language getLanguage(String dsl, Project project) {
		if (dsl.equals(PROTEO) || dsl.isEmpty()) return languages.get(PROTEO);
		return loadLanguage(dsl, project);
	}

	private static Language loadLanguage(String dsl, Project project) {
		if (project == null) return null;
		if (isLoaded(dsl, project)) return languages.get(dsl);
		final File languageDirectory = getLanguageDirectory(dsl, project);
		if (languageDirectory == null) return null;
		Language language = LanguageLoader.load(dsl, languageDirectory.getPath());
		if (language == null) return null;
		languages.put(dsl, language);
		return language;
	}

	public static File getLanguageDirectory(String dsl, Project project) {
		final VirtualFile languagesDirectory = getLanguagesDirectory(project);
		if (languagesDirectory == null) return null;
		return new File(languagesDirectory.getPath(), dsl);
	}

	public static VirtualFile getLanguagesDirectory(Project project) {
		return project.getBaseDir().findChild(DSL);
	}

	private static boolean isLoaded(String parent, Project project) {
		return languages.get(parent) != null && !haveToReload(parent, project);
	}

	private static boolean haveToReload(String language, Project project) {
		VirtualFile reload = getLanguagesDirectory(project).findChild(language + ".reload");
		return reload != null && reload.exists() && new File(reload.getPath()).delete();
	}
}