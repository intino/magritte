package io.intino.tara.plugin.project;

import com.intellij.ProjectTopics;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiPackage;
import com.intellij.refactoring.openapi.impl.JavaRenameRefactoringImpl;
import com.intellij.spellchecker.SpellCheckerManager;
import com.intellij.util.Function;
import com.intellij.util.messages.MessageBusConnection;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.plugin.highlighting.TaraSyntaxHighlighter;
import io.intino.tara.plugin.lang.LanguageManager;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.project.configuration.ConfigurationManager;
import io.intino.tara.plugin.project.configuration.MavenConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import static io.intino.tara.compiler.shared.Configuration.Level.Solution;

public class TaraModuleListener implements BaseComponent {

	private final Project project;
	private final ModuleListener listener;
	private final MessageBusConnection connection;

	public TaraModuleListener(Project project) {
		this.project = project;
		connection = project.getMessageBus().connect();
		listener = newModuleListener();
	}


	@Override
	public void initComponent() {
		TaraSyntaxHighlighter.setProject(this.project);
		connection.subscribe(ProjectTopics.MODULES, listener);
		addDSLNameToDictionary();
		for (Module module : ModuleManager.getInstance(project).getModules())
			if (!module.isLoaded() || !project.isInitialized())
				StartupManager.getInstance(project).registerPostStartupActivity(() -> registerTaraModule(module));
			else registerTaraModule(module);
	}

	private void addDSLNameToDictionary() {
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			final Configuration conf = TaraUtil.configurationOf(module);
			if (conf != null) for (Configuration.LanguageLibrary lang : conf.languages())
				if (!lang.name().isEmpty())
					SpellCheckerManager.getInstance(this.project).acceptWordAsCorrect(lang.name(), project);
		}
	}

	@Override
	public void disposeComponent() {
		TaraSyntaxHighlighter.setProject(null);
		connection.disconnect();
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
				registerTaraModule(module);
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
					if (facetConfiguration != null && (Solution.equals(facetConfiguration.level())))
						ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
							final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
							progressIndicator.setText("Refactoring Java");
							progressIndicator.setIndeterminate(true);
							runRefactor(project, module.getName(), oldNameProvider.fun(module));
						}, "Refactoring Java", true, project, null);
				}
			}
		};
	}

	private void registerTaraModule(@NotNull Module module) {
		if (ConfigurationManager.configurationOf(module) != null) return;
		Configuration configuration = ConfigurationManager.newSuitableProvider(module);
		if (configuration != null) ConfigurationManager.register(module, configuration);
		else {
			configuration = new MavenConfiguration(module);
			if (configuration.isSuitable()) ConfigurationManager.register(module, configuration);
		}
	}

	private void runRefactor(Project project, String newName, String oldName) {
		final JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(project);
		final PsiPackage aPackage = psiFacade.findPackage(oldName);
		if (aPackage != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, aPackage, newName.toLowerCase(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
		final File miscDirectory = LanguageManager.getTaraLocalDirectory(project);
		if (miscDirectory == null || !miscDirectory.exists()) return;
		final File[] files = miscDirectory.listFiles();
		if (files == null) return;
		for (File file : files)
			if (file.getName().startsWith(oldName + "."))
				file.renameTo(new File(miscDirectory, newName + "." + FileUtilRt.getExtension(file.getName())));

	}
}
