package tara.intellij.lang;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.annotator.fix.LanguageRefactor;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.io.refactor.Refactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class LanguageManager {
	public static final String DSL = "dsl";
	public static final String FRAMEWORK = "framework";
	public static final String REFACTORS = "refactors";
	public static final String MISC = "misc";
	public static final String TARA = ".tara";
	public static final String LANGUAGE_EXTENSION = ".dsl";
	public static final String LANGUAGES_PACKAGE = "tara.dsl";
	public static final String JSON = ".json";
	public static final String INFO_JSON = "info" + JSON;
	public static final String PROTEO_KEY = "000.000.000";
	static final Map<String, Language> languages = new HashMap<>();

	static {
		LanguageManager.languages.put(TaraLanguage.PROTEO, new Proteo());
	}

	@Nullable
	public static Language getLanguage(@NotNull PsiFile file) {
		final Module module = ModuleProvider.getModuleOf(file);
		if (module == null) return null;
		return getLanguage(module);
	}

	@Nullable
	public static Language getLanguage(@NotNull Module module) {
		TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		return getLanguage(configuration.getDsl(), module.getProject());
	}

	@Nullable
	public static Language getLanguage(String dsl, Project project) {
		if (dsl.equals(TaraLanguage.PROTEO) || dsl.isEmpty()) return languages.get(TaraLanguage.PROTEO);
		if (project == null) return null;
		return loadLanguage(dsl, project);
	}

	private static Language loadLanguage(String dsl, Project project) {
		if (project == null) return null;
		if (isLoaded(dsl)) return languages.get(dsl);
		else reloadLanguage(dsl, project);
		return languages.get(dsl);
	}

	public static void reloadLanguage(String dsl, Project project) {
		final File languageDirectory = getLanguageDirectory(dsl, project);
		if (!languageDirectory.exists()) return;
		Language language = LanguageLoader.load(dsl, languageDirectory.getPath());
		if (language == null) return;
		languages.put(dsl, language);
		Notifications.Bus.notify(new Notification("Language Reload", "", "Language " + dsl + " reloaded", NotificationType.INFORMATION), project);
	}

	public static void applyRefactors(String dsl, Project project) {
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
			if (conf == null) continue;
			if (conf.getDsl().equals(dsl)) {
				final Refactors[] refactors = TaraUtil.getRefactors(module);
				if (refactors.length == 0) continue;
				new LanguageRefactor(refactors, conf.getEngineRefactorId(), conf.getDomainRefactorId()).apply(module);
				if (refactors[0] != null && !refactors[0].isEmpty()) conf.setEngineRefactorId(refactors[0].size() - 1);
				if (refactors[1] != null && !refactors[1].isEmpty()) conf.setDomainRefactorId(refactors[1].size() - 1);
			}
		}
	}

	public static File getLanguageDirectory(String dsl, Project project) {
		return new File(getTaraDirectory(project).getPath(), DSL + separator + dsl);
	}

	public static File getMiscDirectory(Project project) {
		return new File(getTaraDirectory(project).getPath(), MISC);
	}

	public static File getRefactorsDirectory(Project project) {
		return new File(getTaraDirectory(project).getPath(), REFACTORS + separator);
	}

	public static Map<String, Object> getImportedLanguageInfo(String dsl, Project project) {
		try {
			final File languageDirectory = getLanguageDirectory(dsl, project);
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(new File(languageDirectory, INFO_JSON)), new TypeToken<Map<String, String>>() {
			}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.emptyMap();
	}

	public static VirtualFile getTaraDirectory(Project project) {
		final VirtualFile baseDir = project.getBaseDir();
		final VirtualFile[] tara = {baseDir.findChild(TARA)};
		if (tara[0] == null)
			ApplicationManager.getApplication().runWriteAction(() -> {
				try {
					tara[0] = baseDir.createChildDirectory(null, TARA);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		return tara[0];
	}

	private static boolean isLoaded(String language) {
		return languages.get(language) != null;
	}
}
