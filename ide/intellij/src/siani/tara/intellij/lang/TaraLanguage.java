package siani.tara.intellij.lang;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
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
		if (project == null) return null;
		return loadLanguage(dsl, project.getBaseDir().getPath());
	}

	private static Language loadLanguage(String dsl, String projectPath) {
		if (projectPath == null) return null;
		if (isLoaded(dsl, projectPath)) return languages.get(dsl);
		final File languageDirectory = getLanguageDirectory(dsl, projectPath);
		if (languageDirectory == null) return null;
		Language language = LanguageLoader.load(dsl, languageDirectory.getPath());
		if (language == null) return null;
		languages.put(dsl, language);
		return language;
	}

	public static File getLanguageDirectory(String dsl, String project) {
		final File languagesDirectory = getLanguagesDirectory(project);
		return new File(languagesDirectory.getPath(), dsl);
	}

	public static File getLanguagesDirectory(String project) {
		return new File(project, DSL);
	}

	private static boolean isLoaded(String parent, String projectPath) {
		return languages.get(parent) != null && !haveToReload(parent, projectPath);
	}

	private static boolean haveToReload(String language, String projectPath) {
		File languagesDirectory = getLanguagesDirectory(projectPath);
		if (languagesDirectory == null) return false;
		File reload = new File(languagesDirectory.getPath(), language + ".reload");
		if (reload.exists()) {
			if (!reload.delete())
				Notifications.Bus.notify(new Notification("Model Reload", "", "Reload File cannot be deleted", NotificationType.ERROR), null);
			return true;
		}
		return false;
	}
}