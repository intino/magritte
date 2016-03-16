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
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.util.List;

public class TaraModuleListener implements com.intellij.openapi.module.ModuleComponent {

	private final Project project;
	private final ModuleListener handler;
	private final MessageBusConnection connection;

	public TaraModuleListener(Project project) {
		this.project = project;
		connection = project.getMessageBus().connect();
		handler = newModuleListener();
	}

	private void runRefactor(Project project, String newName, String oldName) {
		final JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(project);
		final PsiPackage aPackage = psiFacade.findPackage(oldName);
		if (aPackage != null) {
			final JavaRenameRefactoringImpl refactoring = new JavaRenameRefactoringImpl(project, aPackage, newName.toLowerCase(), false, false);
			refactoring.doRefactoring(refactoring.findUsages());
		}
	}

	@Override
	public void projectOpened() {
		TaraSyntaxHighlighter.setProject(this.project);
		connection.subscribe(ProjectTopics.MODULES, handler);
		addDslNameToDictionary();
	}

	public void addDslNameToDictionary() {
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			final TaraFacetConfiguration facetConfiguration = TaraUtil.getFacetConfiguration(module);
			if (facetConfiguration != null)
				SpellCheckerManager.getInstance(this.project).acceptWordAsCorrect(facetConfiguration.dsl(), project);
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
		return "dslListener";
	}

	@NotNull
	public ModuleListener newModuleListener() {
		return new ModuleListener() {
			@Override
			public void moduleAdded(@NotNull Project project, @NotNull Module module) {

			}

			@Override
			public void beforeModuleRemoved(@NotNull Project project, @NotNull Module module) {
				connection.disconnect();
			}

			@Override
			public void moduleRemoved(@NotNull Project project, @NotNull Module module) {

			}

			@Override
			public void modulesRenamed(@NotNull Project project, @NotNull List<Module> modules, @NotNull Function<Module, String> oldNameProvider) {
				for (Module module : modules) {
					final TaraFacetConfiguration facetConfiguration = TaraUtil.getFacetConfiguration(module);
					if (facetConfiguration != null && (facetConfiguration.isM0() || facetConfiguration.isTest())) {
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
}
