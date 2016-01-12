package tara.intellij.lang;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class LanguageManager {
	public static final String DSL = "dsl";
	public static final String FRAMEWORK = "framework";
	public static final String REFACTORS = "refactors";
	public static final String TARA = ".tara";
	public static final String LANGUAGE_EXTENSION = ".dsl";
	public static final String LANGUAGES_PACKAGE = "tara.dsl";
	public static final String PROTEO_LIB = "Proteo.jar";
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
		if (languageDirectory == null) return;
		Language language = LanguageLoader.load(dsl, languageDirectory.getPath());
		if (language == null) return;
		languages.put(dsl, language);
		Notifications.Bus.notify(new Notification("Language Reload", "", "Language " + dsl + " reloaded", NotificationType.INFORMATION), project);
		applyRefactors(dsl, project);
	}

	private static void applyRefactors(String dsl, Project project) {
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			if (!module.isDisposed()) continue;
			final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
			if (conf == null) continue;
			if (conf.getDsl().equals(dsl)) {
				final Refactors refactors = TaraUtil.getRefactors(module);
				if (refactors == null) continue;
				new LanguageRefactor(refactors, conf.getRefactorId()).apply(module);
				conf.setRefactorId(refactors.size() - 1);
			}
		}
	}

	public static File getLanguageDirectory(String dsl, Project project) {
		final VirtualFile taraDirectory = getTaraDirectory(project);
		return new File(taraDirectory.getPath(), DSL + separator + dsl);
	}

	public static File getRefactorsDirectory(Project project) {
		final VirtualFile taraDirectory = getTaraDirectory(project);
		return new File(taraDirectory.getPath(), REFACTORS + separator);
	}

	public static VirtualFile getTaraDirectory(Project project) {
		final VirtualFile baseDir = project.getBaseDir();
		VirtualFile tara = baseDir.findChild(TARA);
		if (tara == null) try {
			tara = baseDir.createChildDirectory(null, TARA);
		} catch (IOException e) {
			e.printStackTrace();
			return project.getBaseDir();
		}
		return tara;
	}

	private static boolean isLoaded(String language) {
		return languages.get(language) != null;
	}
}
