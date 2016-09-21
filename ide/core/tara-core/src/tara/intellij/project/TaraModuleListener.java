package tara.intellij.project;

import com.intellij.ProjectTopics;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiPackage;
import com.intellij.refactoring.openapi.impl.JavaRenameRefactoringImpl;
import com.intellij.spellchecker.SpellCheckerManager;
import com.intellij.util.Function;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import tara.intellij.highlighting.TaraSyntaxHighlighter;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.configuration.Configuration;
import tara.intellij.project.configuration.ConfigurationManager;
import tara.intellij.project.configuration.MavenConfiguration;

import java.io.File;
import java.util.List;

import static tara.intellij.project.configuration.Configuration.ModuleType.System;

public class TaraModuleListener implements com.intellij.openapi.module.ModuleComponent {

	private final Project project;
	private final ModuleListener handler;
	private final MessageBusConnection connection;

	public TaraModuleListener(Project project) {
		this.project = project;
		connection = project.getMessageBus().connect();
		handler = newModuleListener();
	}

	@Override
	public void projectOpened() {
		TaraSyntaxHighlighter.setProject(this.project);
		connection.subscribe(ProjectTopics.MODULES, handler);
		addDSLNameToDictionary();
	}

	private void addDSLNameToDictionary() {
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			final Configuration conf = TaraUtil.configurationOf(module);
			if (conf != null) {
				final SpellCheckerManager checker = SpellCheckerManager.getInstance(this.project);
				if (!conf.platformDsl().isEmpty()) checker.acceptWordAsCorrect(conf.platformDsl(), project);
				if (!conf.applicationDsl().isEmpty()) checker.acceptWordAsCorrect(conf.applicationDsl(), project);
				if (!conf.systemDsl().isEmpty()) checker.acceptWordAsCorrect(conf.systemDsl(), project);
			}
		}
	}

	@Override
	public void projectClosed() {
		connection.disconnect();
	}

	@Override
	public void moduleAdded() {
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void disposeComponent() {
		TaraSyntaxHighlighter.setProject(null);
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "TaraModuleListener";
	}

	@NotNull
	private ModuleListener newModuleListener() {
		return new ModuleListener() {
			@Override
			public void moduleAdded(@NotNull Project project, @NotNull Module module) {
				if (!TaraModuleType.isTara(module)) return;
				final MavenConfiguration maven = new MavenConfiguration(module);
				if (!ConfigurationManager.hasExternalProviders() && maven.isSuitable()) ConfigurationManager.register(module, maven);
			}

			@Override
			public void beforeModuleRemoved(@NotNull Project project, @NotNull Module module) {
				connection.disconnect();
			}

			@Override
			public void moduleRemoved(@NotNull Project project, @NotNull Module module) {
				ConfigurationManager.unregister(module);
			}

			@Override
			public void modulesRenamed(@NotNull Project project, @NotNull List<Module> modules, @NotNull Function<Module, String> oldNameProvider) {
				for (Module module : modules) {
					final Configuration facetConfiguration = TaraUtil.configurationOf(module);
					if (facetConfiguration != null && (facetConfiguration.type().equals(System))) {
						ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
							final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
							progressIndicator.setText("Refactoring Java");
							progressIndicator.setIndeterminate(true);
							runRefactor(project, module.getName(), oldNameProvider.fun(module));
						}, "Refactoring Java", true, project, null);
					}
				}
			}
		};
	}

	private void runRefactor(Project project, String newName, String oldName) {
		final JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(project);
		final PsiPackage aPackage = psiFacade.findPackage(oldName);
		if (aPackage != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, aPackage, newName.toLowerCase(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
		final File miscDirectory = LanguageManager.getMiscDirectory(project);
		final File importsFile = new File(miscDirectory, oldName + ".json");
		if (importsFile.exists()) importsFile.renameTo(new File(miscDirectory, newName + ".json"));

	}
}
