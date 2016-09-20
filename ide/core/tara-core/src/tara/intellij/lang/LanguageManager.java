package tara.intellij.lang;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.annotator.fix.LanguageRefactor;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.io.refactor.Refactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.ide.util.DirectoryUtil.createSubdirectories;
import static java.io.File.separator;
import static tara.dsl.ProteoConstants.PROTEO;
import static tara.dsl.ProteoConstants.PROTEO_ONTOLOGY;

public class LanguageManager {
	public static final String DSL = "dsl";
	public static final String FRAMEWORK = "framework";
	public static final String TARA = ".tara";
	public static final String LANGUAGE_EXTENSION = ".dsl";
	public static final String JSON = ".json";
	private static final Map<Project, Map<String, Language>> languages = new HashMap<>();
	private static final Map<String, Language> proteo = new HashMap<>();
	private static final String REFACTORS = "refactors";
	private static final String INFO_JSON = "info" + JSON;
	private static final String MISC = "misc";

	static {
		proteo.put(PROTEO, new Proteo(false));
		proteo.put(PROTEO_ONTOLOGY, new Proteo(true));
	}

	@Nullable
	public static Language getLanguage(@NotNull PsiFile file) {
		final Module module = ModuleProvider.getModuleOf(file);
		if (module == null) return null;
		final TaraFacetConfiguration facetConfiguration = TaraFacet.isOfType(module) ? TaraUtil.getFacetConfiguration(module) : null;
		final String dsl = ((TaraModel) file).dsl();
		if (file.getFileType() instanceof TaraFileType)
			return getLanguage(file.getProject(), dsl, PROTEO.equals(dsl) && facetConfiguration != null && facetConfiguration.applicationDsl().equals(dsl));
		else return null;
	}

	@Nullable
	public static Language getLanguage(Project project, String dsl, boolean ontology) {
		if (dsl == null) return null;
		if (PROTEO.equals(dsl) || dsl.isEmpty()) return proteo.get(ontology ? PROTEO_ONTOLOGY : PROTEO);
		if (project == null) return null;
		return loadLanguage(project, dsl);
	}

	private static Language loadLanguage(Project project, String dsl) {
		if (isLoaded(project, dsl)) return languages.get(project).get(dsl);
		else reloadLanguage(project, dsl);
		return languages.get(project) == null ? null : languages.get(project).get(dsl);
	}

	public static void reloadLanguageForProjects(Project myProject, String dsl) {
		languages.keySet().stream().filter(project -> myProject.equals(project) || languages.get(project).containsKey(dsl)).forEach(project -> reloadLanguage(project, dsl));
	}

	public static void reloadLanguage(Project project, String dsl) {
		final File languageDirectory = getLanguageDirectory(dsl);
		if (!languageDirectory.exists()) return;
		Language language = LanguageLoader.load(dsl, languageDirectory.getPath());
		if (language == null) return;
		addLanguage(project, dsl, language);
		PsiManager.getInstance(project).dropResolveCaches();
		Notifications.Bus.notify(new Notification("Language Reload", "", "Language " + dsl + " reloaded", NotificationType.INFORMATION), project);
	}

	public static void applyRefactors(String dsl, Project project) {
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
			if (conf == null) continue;
			if (conf.platformDsl().equals(dsl) || conf.applicationDsl().equals(dsl) || conf.systemDsl().equals(dsl)) {
				final Refactors[] refactors = TaraUtil.getRefactors(module);
				if (refactors.length == 0) continue;
				new LanguageRefactor(refactors, conf.platformRefactorId(), conf.applicationRefactorId()).apply(module);
				if (refactors[0] != null && !refactors[0].isEmpty()) conf.platformRefactorId(refactors[0].size() - 1);
				if (refactors[1] != null && !refactors[1].isEmpty())
					conf.applicationRefactorId(refactors[1].size() - 1);
			}
		}
	}

	public static File getLanguageDirectory(String dsl) {
		return new File(getTaraDirectory().getPath(), DSL + separator + dsl);
	}

	public static File getMiscDirectory(Project project) {
		final VirtualFile taraLocalDirectory = getTaraLocalDirectory(project);

		return taraLocalDirectory == null ? null : new File(taraLocalDirectory.getPath(), MISC);
	}

	public static File getRefactorsDirectory(Project project) {
		return new File(getTaraLocalDirectory(project).getPath(), REFACTORS + separator);
	}

	public static Map<String, Object> getImportedLanguageInfo(String dsl, Project project) {
		try {
			final File languageDirectory = getLanguageDirectory(dsl);
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(new File(languageDirectory, INFO_JSON)), new TypeToken<Map<String, String>>() {
			}.getType());
		} catch (FileNotFoundException ignored) {
		}
		return Collections.emptyMap();
	}

	public static VirtualFile getTaraLocalDirectory(Project project) {
		final VirtualFile baseDir = project.getBaseDir();
		final VirtualFile tara = baseDir.findChild(TARA);
		return tara == null ? createTaraDirectory(project, baseDir) : tara;
	}

	public static VirtualFile getTaraDirectory() {
		final File baseDir = new File(System.getProperty("user.home"));
		final File tara = new File(baseDir, TARA);
		if (!tara.exists()) tara.mkdirs();
		return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(tara);
	}

	private static VirtualFile createTaraDirectory(Project project, VirtualFile baseDir) {
		final PsiDirectory basePsiDir = PsiManager.getInstance(project).findDirectory(baseDir);
		final com.intellij.openapi.application.Application application = ApplicationManager.getApplication();
		if (application.isWriteAccessAllowed())
			return application.<VirtualFile>runWriteAction(() -> {
				final PsiDirectory subdirectory = DirectoryUtil.createSubdirectories(TARA, basePsiDir, "-");
				return subdirectory == null ? null : subdirectory.getVirtualFile();
			});
		else {
			PsiDirectory[] directory = new PsiDirectory[1];
			application.invokeLater(() -> directory[0] = application.<PsiDirectory>runWriteAction(() -> createSubdirectories(TARA, basePsiDir, "-")));
			return directory[0] == null ? null : directory[0].getVirtualFile();
		}
	}

	private static boolean isLoaded(Project project, String language) {
		return languages.get(project) != null && languages.get(project).get(language) != null;
	}

	public static void remove(Project project) {
		languages.remove(project);
	}

	private static void addLanguage(Project project, String dsl, Language language) {
		if (!languages.containsKey(project)) languages.put(project, new HashMap<>());
		languages.get(project).put(dsl, language);
	}
}
