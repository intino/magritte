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
import tara.compiler.shared.Configuration;
import tara.dsl.Proteo;
import tara.dsl.Verso;
import tara.intellij.annotator.fix.LanguageRefactor;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
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
import static tara.dsl.ProteoConstants.VERSO;

public class LanguageManager {
	public static final String DSL = "dsl";
	public static final String DSL_GROUP_ID = "tara.dsl";
	public static final String FRAMEWORK = "framework";
	public static final String TARA = ".tara";
	public static final String JSON = ".json";
	private static final Map<Project, Map<String, Language>> languages = new HashMap<>();
	private static final Map<String, Language> core = new HashMap<>();
	private static final String REFACTORS = "refactors";
	private static final String INFO_JSON = "info" + JSON;
	private static final String MISC = "misc";
	private static final String LATEST = "LATEST";

	static {
		core.put(PROTEO, new Proteo());
		core.put(VERSO, new Verso());
	}

	@Nullable
	public static Language getLanguage(@NotNull PsiFile file) {
		if (file.getFileType() instanceof TaraFileType) {
			final Configuration configuration = TaraUtil.configurationOf(file);
			return getLanguage(file.getProject(), ((TaraModel) file).dsl(), configuration == null ? LATEST : configuration.dslVersion());
		} else return null;
	}

	@Nullable
	public static Language getLanguage(Project project, String dsl) {
		return getLanguage(project, dsl, LATEST);
	}

	@Nullable
	public static Language getLanguage(Project project, String dsl, String version) {
		if (dsl == null) return null;
		if (core.containsKey(dsl)) return core.get(dsl);
		if (dsl.isEmpty()) return core.get(VERSO);
		if (project == null) return null;
		return loadLanguage(project, dsl, version);
	}

	private static Language loadLanguage(Project project, String dsl, String version) {
		if (isLoaded(project, dsl)) return languages.get(project).get(dsl);
		else if (LATEST.equals(version)) reloadLanguage(project, dsl);
		else reloadLanguage(project, dsl, version);
		return languages.get(project) == null ? null : languages.get(project).get(dsl);
	}

	public static void reloadLanguageForProjects(Project myProject, String dsl) {
		languages.keySet().stream().filter(project -> myProject.equals(project) || languages.get(project).containsKey(dsl)).forEach(project -> reloadLanguage(project, dsl));
	}

	public static void register(Language language) {
		core.put(language.languageName(), language);
	}

	@SuppressWarnings("WeakerAccess")
	public static boolean reloadLanguage(Project project, String dsl) {
		final File languageDirectory = getLanguageDirectory(dsl);
		if (!languageDirectory.exists()) return false;
		Language language = LanguageLoader.loadLatest(dsl, languageDirectory.getPath());
		if (language == null) return false;
		addLanguage(project, dsl, language);
		PsiManager.getInstance(project).dropResolveCaches();
		Notifications.Bus.notify(new Notification("Language Reload", "", "Language " + dsl + " reloaded", NotificationType.INFORMATION), project);
		return true;
	}

	@SuppressWarnings("WeakerAccess")
	public static boolean reloadLanguage(Project project, String dsl, String version) {
		final File languageDirectory = getLanguageDirectory(dsl);
		if (!languageDirectory.exists()) return false;
		Language language = LanguageLoader.load(dsl, version, languageDirectory.getPath());
		if (language == null) return false;
		addLanguage(project, dsl, language);
		PsiManager.getInstance(project).dropResolveCaches();
		Notifications.Bus.notify(new Notification("Language Reload", "", "Language " + dsl + " reloaded", NotificationType.INFORMATION), project);
		return true;
	}

	public static void applyRefactors(String dsl, Project project) {
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			final Configuration conf = TaraUtil.configurationOf(module);
			if (conf == null || conf.dsl() == null) continue;
			if (conf.dsl().equals(dsl)) {
				final Refactors[] refactors = TaraUtil.getRefactors(module);
				if (refactors.length == 0) continue;
				new LanguageRefactor(refactors, conf.refactorId()).apply(module);
				if (refactors[0] != null && !refactors[0].isEmpty()) conf.refactorId(refactors[0].size() - 1);
				if (refactors[1] != null && !refactors[1].isEmpty())
					conf.refactorId(refactors[1].size() - 1);
			}
		}
	}

	@SuppressWarnings("unused")
	public static File getLanguageFile(String dsl, String version) {
		return LanguageLoader.composeLanguagePath(getLanguageDirectory(dsl).getPath(), dsl, version);
	}

	public static File getLanguageDirectory(String dsl) {
		return new File(getLanguagesDirectory().getPath(), DSL_GROUP_ID.replace(".", File.separator) + File.separator + dsl.toLowerCase());
	}

	public static File getMiscDirectory(Project project) {
		final File taraLocalDirectory = getTaraLocalDirectory(project);
		final File misc = new File(taraLocalDirectory.getPath(), MISC);
		misc.mkdirs();
		return misc;
	}

	public static File getRefactorsDirectory(Project project) {
		return new File(getTaraLocalDirectory(project).getPath(), REFACTORS + separator);
	}

	public static Map<String, Object> getImportedLanguageInfo(String dsl) {
		try {
			final File languageDirectory = getLanguageDirectory(dsl);
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(new File(languageDirectory, INFO_JSON)), new TypeToken<Map<String, String>>() {
			}.getType());
		} catch (FileNotFoundException ignored) {
		}
		return Collections.emptyMap();
	}

	public static File getTaraLocalDirectory(Project project) {
		final VirtualFile baseDir = project.getBaseDir();
		final VirtualFile tara = baseDir.findChild(TARA);
		return tara == null ? createTaraFile(baseDir) : new File(tara.getPath());
	}

	private static VirtualFile getTaraDirectory() {
		final File baseDir = new File(System.getProperty("user.home"));
		final File tara = new File(baseDir, TARA);
		if (!tara.exists()) tara.mkdirs();
		return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(tara);
	}

	public static VirtualFile getLanguagesDirectory() {
		final VirtualFile taraDirectory = getTaraDirectory();
		final File dslDirectory = new File(taraDirectory.getPath(), "repository");
		dslDirectory.mkdirs();
		return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(dslDirectory);
	}

	private static File createTaraFile(VirtualFile baseDir) {
		final File file = new File(baseDir.getPath(), TARA);
		file.mkdirs();
		return file;
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
